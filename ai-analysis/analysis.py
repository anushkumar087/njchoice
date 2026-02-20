#!/usr/bin/env python3
"""
AI Analysis of Test Logs
Reads embeddings from ChromaDB and answers queries about test execution
"""

import os
import sys
from datetime import datetime
from dotenv import load_dotenv
from llama_index.core import VectorStoreIndex, Settings
from llama_index.vector_stores.chroma import ChromaVectorStore
from llama_index.embeddings.huggingface import HuggingFaceEmbedding
from llama_index.llms.openai import OpenAI
import chromadb

def create_html_report(query, response, output_path):
    """Create HTML report with AI analysis"""
    
    # Get current timestamp
    timestamp = datetime.now().strftime("%B %d, %Y at %H:%M:%S")
    
    html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AI Test Analysis Report</title>
    <style>
        * {{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }}
        
        body {{
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 20px;
            line-height: 1.6;
        }}
        
        .container {{
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            overflow: hidden;
        }}
        
        .header {{
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 30px;
            text-align: center;
        }}
        
        .header h1 {{
            font-size: 2.5em;
            margin-bottom: 10px;
            text-shadow: 2px 2px 4px rgba(0,0,0,0.3);
        }}
        
        .header .subtitle {{
            font-size: 1.1em;
            opacity: 0.9;
        }}
        
        .timestamp {{
            background: #f8f9fa;
            padding: 15px 30px;
            border-bottom: 3px solid #667eea;
            color: #666;
            font-size: 0.95em;
        }}
        
        .content {{
            padding: 30px;
        }}
        
        .section {{
            margin-bottom: 30px;
        }}
        
        .section-title {{
            font-size: 1.5em;
            color: #667eea;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #e0e0e0;
            display: flex;
            align-items: center;
        }}
        
        .section-title::before {{
            content: '🔍';
            margin-right: 10px;
            font-size: 1.2em;
        }}
        
        .query-box {{
            background: #f8f9fa;
            border-left: 4px solid #667eea;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
        }}
        
        .query-box p {{
            color: #333;
            font-size: 1.05em;
        }}
        
        .response-box {{
            background: #ffffff;
            border: 1px solid #e0e0e0;
            padding: 25px;
            border-radius: 5px;
            line-height: 1.8;
        }}
        
        .response-box h2 {{
            color: #667eea;
            margin-top: 20px;
            margin-bottom: 10px;
            font-size: 1.3em;
        }}
        
        .response-box h2:first-child {{
            margin-top: 0;
        }}
        
        .response-box p {{
            color: #444;
            margin-bottom: 15px;
        }}
        
        .response-box ul, .response-box ol {{
            margin-left: 25px;
            margin-bottom: 15px;
        }}
        
        .response-box li {{
            color: #444;
            margin-bottom: 8px;
        }}
        
        .response-box strong {{
            color: #667eea;
        }}
        
        .footer {{
            background: #f8f9fa;
            padding: 20px 30px;
            text-align: center;
            color: #666;
            border-top: 1px solid #e0e0e0;
        }}
        
        .badge {{
            display: inline-block;
            background: #667eea;
            color: white;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 0.85em;
            margin-left: 10px;
        }}
        
        @media print {{
            body {{
                background: white;
                padding: 0;
            }}
            
            .container {{
                box-shadow: none;
            }}
        }}
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🤖 AI Test Analysis Report</h1>
            <div class="subtitle">Powered by OpenAI GPT-4o-mini & LlamaIndex</div>
        </div>
        
        <div class="timestamp">
            <strong>Generated:</strong> {timestamp}
            <span class="badge">Automated Analysis</span>
        </div>
        
        <div class="content">
            
            <div class="section">
                <div class="section-title" style="border-bottom-color: #764ba2;">AI Analysis Response</div>
                <div class="response-box">
                    {format_response_as_html(response)}
                </div>
            </div>
        </div>
        
        <div class="footer">
            <p>This report was automatically generated from test execution logs using AI-powered analysis.</p>
            <p style="margin-top: 5px; font-size: 0.9em;">NJChoice Test Automation Framework</p>
        </div>
    </div>
</body>
</html>"""
    
    # Write HTML file
    with open(output_path, 'w', encoding='utf-8') as f:
        f.write(html_content)
    
    print(f"✓ HTML report saved: {output_path}")

def format_response_as_html(response):
    """Format response text as HTML with basic markdown-style formatting"""
    import re
    
    text = str(response)
    
    # Convert numbered lists (1. 2. 3.)
    text = re.sub(r'^(\d+)\.\s+(.+)$', r'<li>\2</li>', text, flags=re.MULTILINE)
    text = re.sub(r'(<li>.*?</li>\n?)+', r'<ol>\g<0></ol>', text, flags=re.DOTALL)
    
    # Convert bullet points (- or *)
    text = re.sub(r'^[\-\*]\s+(.+)$', r'<li>\1</li>', text, flags=re.MULTILINE)
    text = re.sub(r'(<li>.*?</li>\n?)(?!</?ol>)', r'<ul>\g<0></ul>', text, flags=re.DOTALL)
    
    # Convert **bold** to <strong>
    text = re.sub(r'\*\*(.+?)\*\*', r'<strong>\1</strong>', text)
    
    # Convert headers (## Header)
    text = re.sub(r'^##\s+(.+)$', r'<h2>\1</h2>', text, flags=re.MULTILINE)
    
    # Convert line breaks to paragraphs
    paragraphs = text.split('\n\n')
    formatted_paragraphs = []
    for para in paragraphs:
        para = para.strip()
        if para and not para.startswith('<') and not para.endswith('>'):
            formatted_paragraphs.append(f'<p>{para}</p>')
        else:
            formatted_paragraphs.append(para)
    
    return '\n'.join(formatted_paragraphs)

def analyze_test_logs(query=None, report_timestamp=None):
    """Analyze test logs from ChromaDB and answer query"""
    
    # Load environment variables
    load_dotenv()
    
    # Get OpenAI API key
    api_key = os.getenv("OPENAI_API_KEY")
    if not api_key:
        print("❌ Error: OPENAI_API_KEY not set in .env file")
        sys.exit(1)
    
    os.environ["OPENAI_API_KEY"] = api_key
    
    # Setup ChromaDB persistent client
    chroma_client = chromadb.PersistentClient(path="./chroma_db")
    
    # Collection name for test logs
    collection_name = "test_logs_collection"
    
    try:
        # Get existing collection
        chroma_collection = chroma_client.get_collection(collection_name)
        embedding_count = chroma_collection.count()
        
        if embedding_count == 0:
            print(f"⚠️  Warning: Collection '{collection_name}' is empty")
            print("Run embeddings.py first to create embeddings from logs")
            sys.exit(1)
        
        print(f"✓ Loaded collection '{collection_name}' with {embedding_count} embeddings")
        
    except Exception as e:
        print(f"❌ Error: Collection '{collection_name}' not found")
        print("Run embeddings.py first to create embeddings from logs")
        sys.exit(1)
    
    # Setup vector store
    vector_store = ChromaVectorStore(chroma_collection=chroma_collection)
    
    # Configure embedding model and LLM
    Settings.embed_model = HuggingFaceEmbedding(
        model_name="sentence-transformers/all-MiniLM-L6-v2"
    )
    Settings.llm = OpenAI(model="gpt-4o", temperature=0.1)
    
    # Load index from existing embeddings
    print("🔄 Loading index from ChromaDB...")
    index = VectorStoreIndex.from_vector_store(vector_store=vector_store)
    print("✓ Index loaded successfully")
    
    curr_timestamp = datetime.now().strftime("%B %d, %Y at %H:%M:%S")
    
    # Query the index
    print(f"\n{'='*80}")
    print("AI ANALYSIS OF TEST EXECUTION")
    print(f"{'='*80}\n")
    
    try:
        # Calculate similarity_top_k - limit to avoid token overflow
        # GPT-4o has 30K tokens/min limit, so we need to retrieve fewer documents
        if embedding_count < 25:
            top_k = 5
        elif embedding_count < 100:
            top_k = 15  # Small collection - retrieve 15 docs
        elif embedding_count < 200:
            top_k = 25  # Medium collection - retrieve 25 docs
        else:
            top_k = 30  # Large collection - cap at 30 docs to avoid token limits
        
        print(f"📊 Collection stats: {embedding_count} total embeddings")
        print(f"🔍 Retrieving top {top_k} most relevant documents (to stay within token limits)")
        
        # First, get all execution timestamps from metadata to find the absolute latest
        print(f"\n🔍 Finding the most recent execution...")
        all_data = chroma_collection.get(include=['metadatas'])
        
        # Extract all unique execution timestamps
        execution_times = {}
        for metadata in all_data['metadatas']:
            if 'log_timestamp' in metadata and 'execution_id' in metadata:
                log_ts = metadata['log_timestamp']
                exec_id = metadata['execution_id']
                exec_status = metadata.get('execution_status', 'UNKNOWN')
                
                if exec_id not in execution_times:
                    execution_times[exec_id] = {
                        'log_timestamp': log_ts,
                        'execution_status': exec_status,
                        'human_timestamp': metadata.get('human_timestamp', 'Unknown')
                    }
        
        # Sort by log_timestamp to find the latest
        sorted_executions = sorted(execution_times.items(), 
                                   key=lambda x: x[1]['log_timestamp'], 
                                   reverse=True)
        
        if sorted_executions:
            latest_exec_id, latest_exec_info = sorted_executions[0]
            print(f"✓ Latest execution found:")
            print(f"  - Execution ID: {latest_exec_id}")
            print(f"  - Timestamp: {latest_exec_info['log_timestamp']}")
            print(f"  - Status: {latest_exec_info['execution_status']}")
            print(f"  - Embedded on: {latest_exec_info['human_timestamp']}")
            
            # Now create a targeted query for this specific execution
            latest_timestamp = latest_exec_info['log_timestamp']
            latest_status = latest_exec_info['execution_status']
        else:
            print("⚠️  No executions found with timestamps")
            latest_timestamp = None
            latest_status = None
        
        from llama_index.core.vector_stores import MetadataFilters, ExactMatchFilter
        
        # Create metadata filter for latest execution only
        if latest_timestamp:
            metadata_filters = MetadataFilters(
                filters=[ExactMatchFilter(key="execution_id", value=latest_exec_id)]
            )
        else:
            metadata_filters = None
        
        query_engine = index.as_query_engine(
            similarity_top_k=top_k,  # Capped to avoid token overflow
            response_mode="compact",  # Changed from tree_summarize to compact for efficiency
            filters=metadata_filters  # Filter to latest execution only
        )
        
        # Default query if none provided
        # if not query:
        #     query = f"""
        #     Get the 10 latest report details chronologically by timestamp compared to {curr_timestamp} and perform the following as stated below:
            
        #     Analyze test execution logs for the latest (most recent) execution out of these 10
        #     """
            
        # response = query_engine.query(query)
        
        if not query:
            if latest_timestamp:
                query = f"""
                TASK: Analyze the MOST RECENT test execution with timestamp: {latest_timestamp}
                
                CRITICAL: This execution has status: {latest_status}
                Only analyze documents from this specific execution. Ignore all other executions.
                
                STEP 1 - VERIFY EXECUTION:
                Confirm you found the execution with timestamp: {latest_timestamp}
                
                STEP 2 - DETERMINE EXECUTION TYPE:
                Check if this latest execution is:
                - TYPE A (COMPLETE FAILURE): Contains "FAILED EXECUTION:" or "⊘ Test SKIPPED"
                - TYPE B (PARTIAL/FULL SUCCESS): Contains "Validations Passed:" and "✅ Passed Scales/CAPs"
                
                STEP 3 - ANALYZE BASED ON TYPE:
                
                IF TYPE A (COMPLETE FAILURE - NO caps/scales passed):
                1. **Execution Timestamp**: {latest_timestamp}
                2. **Data Provider**: [Extract from "DataProvider: Returning..." line]
                - If "first 30 rows out of 30" → Say "Tried to execute all rows"
                - If "first/random X rows out of 30" where X<30 → Say "Tried to execute X rows"
                3. **Failure Reason**: [Extract from "FAILED EXECUTION:" message]
                4. **Result**: All 49 caps/scales FAILED (0/49, 0%)
                5. **Historical Context**: Were there any previous successful/partial executions?
                6. **Observations**: Overall test stability analysis
                
                IF TYPE B (PARTIAL/FULL SUCCESS - SOME caps/scales passed):
                1. **Execution Timestamp**: {latest_timestamp}
                2. **Data Provider**: [Extract from "DataProvider: Returning..." line]
                - If "first 30 rows out of 30" → Say "Executed all rows"
                - If "first/random X rows out of 30" where X<30 → Say "Executed X rows"
                3. **Validation Results**: [Extract from "Validations Passed: X/49"]
                4. **Pass Rate**: Calculate (X/49) * 100%
                5. **✅ Passed Scales/CAPs**: [List ALL items from "Passed Scales/CAPs (X):" array]
                6. **❌ Failed Scales/CAPs**: [List ALL items from "Failed Scales/CAPs (Y):" array, include failure reasons if available]
                7. **Failure Analysis**: Why did these specific caps/scales fail?
                8. **Historical Comparison**: 
                - Are these failures consistent with previous runs?
                - How many times have these specific caps/scales failed before?
                - Provide numerical data (e.g., "Cap X failed in 5 out of 8 executions")
                9. **Test Stability**: Overall observations across all executions
                10. **Recommendations**: What should be investigated?
                
                FORMATTING RULES:
                - Keep questions SHORT and concise in the response
                - Provide ELABORATE answers with specific details
                - Do NOT show "Section A" or "Section B" labels
                - Use bullet points and numbered lists for clarity
                - Include actual timestamps, numbers, and names from the logs
                
                CRITICAL: Analyze ONLY the execution with timestamp {latest_timestamp}. Do not mix data from different executions.
                """
            else:
                query = f"""
                TASK: Find and analyze the MOST RECENT test execution from the embeddings.
                
                STEP 1 - IDENTIFY LATEST EXECUTION:
                Look at ALL embeddings with "TEST EXECUTION LOG" timestamps.
                The MOST RECENT execution is the one with the timestamp CLOSEST to: {curr_timestamp}
                
                STEP 2 - DETERMINE EXECUTION TYPE:
            Check if this latest execution is:
            - TYPE A (COMPLETE FAILURE): Contains "FAILED EXECUTION:" or "⊘ Test SKIPPED"
            - TYPE B (PARTIAL/FULL SUCCESS): Contains "Validations Passed:" and "✅ Passed Scales/CAPs"
            
            STEP 3 - ANALYZE BASED ON TYPE:
            
            IF TYPE A (COMPLETE FAILURE - NO caps/scales passed):
            1. **Execution Timestamp**: [Extract from "TEST EXECUTION LOG - ..." line]
            2. **Data Provider**: [Extract from "DataProvider: Returning..." line]
            - If "first 30 rows out of 30" → Say "Tried to execute all rows"
            - If "first/random X rows out of 30" where X<30 → Say "Tried to execute X rows"
            3. **Failure Reason**: [Extract from "FAILED EXECUTION:" message]
            4. **Result**: All 49 caps/scales FAILED (0/49, 0%)
            5. **Historical Context**: Were there any previous successful/partial executions?
            6. **Observations**: Overall test stability analysis
            
            IF TYPE B (PARTIAL/FULL SUCCESS - SOME caps/scales passed):
            1. **Execution Timestamp**: [Extract from "TEST EXECUTION LOG - ..." line]
            2. **Data Provider**: [Extract from "DataProvider: Returning..." line]
            - If "first 30 rows out of 30" → Say "Executed all rows"
            - If "first/random X rows out of 30" where X<30 → Say "Executed X rows"
            3. **Validation Results**: [Extract from "Validations Passed: X/49"]
            4. **Pass Rate**: Calculate (X/49) * 100%
            5. **✅ Passed Scales/CAPs**: [List ALL items from "Passed Scales/CAPs (X):" array]
            6. **❌ Failed Scales/CAPs**: [List ALL items from "Failed Scales/CAPs (Y):" array, include failure reasons if available]
            7. **Failure Analysis**: Why did these specific caps/scales fail?
            8. **Historical Comparison**: 
            - Are these failures consistent with previous runs?
            - How many times have these specific caps/scales failed before?
            - Provide numerical data (e.g., "Cap X failed in 5 out of 8 executions")
            9. **Test Stability**: Overall observations across all executions
            10. **Recommendations**: What should be investigated?
            
            FORMATTING RULES:
            - Keep questions SHORT and concise in the response
            - Provide ELABORATE answers with specific details
            - Do NOT show "Section A" or "Section B" labels
            - Use bullet points and numbered lists for clarity
            - Include actual timestamps, numbers, and names from the logs
            
            CRITICAL: Analyze ONLY the execution with the NEWEST timestamp. Do not mix data from different executions.
            """
        
        response = query_engine.query(query)
        
        print(f"Response:\n{response}\n")
        print(f"{'='*80}\n")
        
        # Generate HTML report
        # Create reports-ai directory if it doesn't exist
        project_root = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
        reports_dir = os.path.join(project_root, "reports-ai")
        os.makedirs(reports_dir, exist_ok=True)
        
        # Generate filename with timestamp
        # Use provided timestamp if available (from Java Excel report), otherwise generate new one
        if report_timestamp:
            timestamp = report_timestamp
        else:
            timestamp = datetime.now().strftime("%B %d, %Y at %H:%M:%S")
        
        html_filename = f"AI_Analysis_Report_{timestamp}.html"
        html_path = os.path.join(reports_dir, html_filename)
        
        # Create HTML report
        create_html_report(query, str(response), html_path)
        
        return str(response)
        
    except Exception as e:
        print(f"❌ Error during query: {e}")
        sys.exit(1)

if __name__ == "__main__":
    # Parse command line arguments
    # Format: python3 analysis.py [timestamp] [query...]
    # - If first arg looks like timestamp (contains underscores), use it as timestamp
    # - Everything else is treated as query
    
    report_timestamp = None
    query = None
    
    if len(sys.argv) > 1:
        # Check if first argument is a timestamp (contains underscores like "16_02_2026__20_14_53")
        first_arg = sys.argv[1]
        if "_" in first_arg and first_arg.replace("_", "").replace(":", "").isdigit():
            # First argument is timestamp
            report_timestamp = first_arg
            if len(sys.argv) > 2:
                # Remaining arguments are query
                query = " ".join(sys.argv[2:])
        else:
            # First argument is part of query
            query = " ".join(sys.argv[1:])
    
    analyze_test_logs(query, report_timestamp)
