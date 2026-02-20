#!/usr/bin/env python3
"""
Production-Grade Embeddings Generator for Test Logs
Best practices for RAG + Vector DB + Test Automation
"""

import os
import sys
import uuid
import datetime
import re
from dotenv import load_dotenv

from llama_index.core import Document, VectorStoreIndex, Settings
from llama_index.vector_stores.chroma import ChromaVectorStore
from llama_index.core.storage.storage_context import StorageContext
from llama_index.embeddings.huggingface import HuggingFaceEmbedding

import chromadb


# ==============================
# CONFIGURATION
# ==============================

LOG_FILE = "temp_logs.txt"
CHROMA_PATH = "./chroma_db"
COLLECTION_NAME = "test_logs_collection"

EMBED_MODEL = "sentence-transformers/all-MiniLM-L6-v2"


# ==============================
# LOG ANALYZER
# ==============================

def detect_execution_failure(log_text):
    """
    Detect if the execution completely failed before tests could run.
    Returns (is_failed, failure_reason)
    """
    # Check for complete execution failure
    failed_execution_match = re.search(r'FAILED EXECUTION: (.+)', log_text)
    if failed_execution_match:
        return True, failed_execution_match.group(1).strip()
    
    # Check for test skipped
    if '⊘ Test SKIPPED:' in log_text:
        return True, "Test was skipped due to setup/configuration failure"
    
    return False, None


def extract_execution_metadata(log_text):
    """
    Extract key metrics from the test execution log.
    Handles both successful executions and complete failures.
    """
    metadata = {
        'execution_timestamp': None,
        'data_provider_info': None,
        'execution_status': 'UNKNOWN',
        'failure_reason': None,
        'test_instances': [],
        'passed_scales': set(),
        'failed_scales': set(),
        'total_validations': 49,
        'avg_passed': 0,
        'avg_pass_rate': 0.0
    }
    
    # Extract execution timestamp
    timestamp_match = re.search(r'TEST EXECUTION LOG - (.+)', log_text)
    if timestamp_match:
        metadata['execution_timestamp'] = timestamp_match.group(1)
    
    # Check for complete execution failure first
    is_failed, failure_reason = detect_execution_failure(log_text)
    if is_failed:
        metadata['execution_status'] = 'COMPLETE_FAILURE'
        metadata['failure_reason'] = failure_reason
        metadata['avg_passed'] = 0
        metadata['avg_pass_rate'] = 0.0
    else:
        metadata['execution_status'] = 'COMPLETED'
    
    # Extract DataProvider info
    dataprovider_match = re.search(r'DataProvider: Returning (\d+) random rows? out of (\d+) total rows', log_text)
    if dataprovider_match:
        metadata['data_provider_info'] = {
            'rows_executed': int(dataprovider_match.group(1)),
            'total_rows': int(dataprovider_match.group(2))
        }
    
    # Only extract test results if execution was not a complete failure
    if not is_failed:
        # Extract test execution tracker entries (for duration)
        tracker_pattern = r'Test Execution Tracker: Test #(\d+) recorded - (\w+) \((\d+)/(\d+)\) - ([\d.]+)s'
        for match in re.finditer(tracker_pattern, log_text):
            test_num = int(match.group(1))
            status = match.group(2)
            passed = int(match.group(3))
            total = int(match.group(4))
            duration = float(match.group(5))
            
            metadata['test_instances'].append({
                'test_number': test_num,
                'status': status,
                'passed': passed,
                'total': total,
                'pass_rate': round((passed / total) * 100, 2),
                'duration_seconds': duration
            })
        
        # Extract passed scales/CAPs
        passed_section_pattern = r'✅ Passed Scales/CAPs \(\d+\):(.*?)(?=❌|DEBUG|$)'
        passed_match = re.search(passed_section_pattern, log_text, re.DOTALL)
        if passed_match:
            passed_items = re.findall(r'- (.+)', passed_match.group(1))
            metadata['passed_scales'] = set(passed_items)
        
        # Extract failed scales/CAPs
        failed_section_pattern = r'❌ Failed Scales/CAPs \(\d+\):(.*?)(?=DEBUG|$)'
        failed_match = re.search(failed_section_pattern, log_text, re.DOTALL)
        if failed_match:
            failed_items = re.findall(r'- (.+)', failed_match.group(1))
            metadata['failed_scales'] = set(failed_items)
        
        # Calculate averages
        if metadata['test_instances']:
            total_passed = sum(t['passed'] for t in metadata['test_instances'])
            metadata['avg_passed'] = round(total_passed / len(metadata['test_instances']), 2)
            metadata['avg_pass_rate'] = round(
                sum(t['pass_rate'] for t in metadata['test_instances']) / len(metadata['test_instances']),
                2
            )
    
    return metadata


def parse_log_into_sections(log_text):
    """
    Split logs into meaningful sections based on test execution flow.
    """
    sections = []
    
    # Split by test instances (each "► Starting Test" marks a new test)
    test_splits = re.split(r'(► Starting Test: testAllSectionsComprehensive)', log_text)
    
    # First section is the header
    if test_splits[0].strip():
        sections.append(("execution_header", test_splits[0]))
    
    # Process each test instance
    for i in range(1, len(test_splits), 2):
        if i+1 < len(test_splits):
            test_content = test_splits[i] + test_splits[i+1]
            sections.append((f"test_instance_{(i//2)+1}", test_content))
    
    return sections


# ==============================
# MAIN EMBEDDING FUNCTION
# ==============================

def create_embeddings():

    load_dotenv()

    if not os.path.exists(LOG_FILE):
        print(f"❌ Log file not found: {LOG_FILE}")
        sys.exit(1)

    with open(LOG_FILE, "r", encoding="utf-8") as f:
        log_content = f.read()

    if not log_content.strip():
        print("❌ Log file is empty")
        sys.exit(1)

    print("✓ Log file loaded")

    # Extract execution metadata from logs
    exec_metadata = extract_execution_metadata(log_content)
    
    print(f"\n📊 EXECUTION ANALYSIS:")
    print(f"   Timestamp: {exec_metadata['execution_timestamp']}")
    print(f"   Status: {exec_metadata['execution_status']}")
    
    if exec_metadata['execution_status'] == 'COMPLETE_FAILURE':
        print(f"   ⚠️  FAILURE REASON: {exec_metadata['failure_reason']}")
    
    if exec_metadata['data_provider_info']:
        dp = exec_metadata['data_provider_info']
        print(f"   Data Provider: {dp['rows_executed']}/{dp['total_rows']} rows")
    
    if exec_metadata['execution_status'] != 'COMPLETE_FAILURE':
        print(f"   Test Instances: {len(exec_metadata['test_instances'])}")
        print(f"   Average Passed: {exec_metadata['avg_passed']}/{exec_metadata['total_validations']}")
        print(f"   Average Pass Rate: {exec_metadata['avg_pass_rate']}%")
        print(f"   Passed Scales/CAPs: {len(exec_metadata['passed_scales'])}")
        print(f"   Failed Scales/CAPs: {len(exec_metadata['failed_scales'])}")
        
        # Print test instance details
        for test_inst in exec_metadata['test_instances']:
            print(f"   Test #{test_inst['test_number']}: {test_inst['status']} ({test_inst['passed']}/{test_inst['total']}) - {test_inst['duration_seconds']}s")

    # Generate system metadata
    execution_id = str(uuid.uuid4())[:8]
    system_timestamp = datetime.datetime.now().isoformat()
    human_readable_timestamp = datetime.datetime.now().strftime("%B %d, %Y at %I:%M:%S %p")

    print(f"\n✓ Execution ID: {execution_id}")
    
    # Create a comprehensive summary document based on execution status
    if exec_metadata['execution_status'] == 'COMPLETE_FAILURE':
        summary_text = f"""
════════════════════════════════════════════════════════════════
TEST EXECUTION COMPLETED ON: {human_readable_timestamp}
════════════════════════════════════════════════════════════════

EXECUTION TIMESTAMP: {exec_metadata['execution_timestamp']}
EXECUTION ID: {execution_id}
EXECUTION STATUS: ⚠️  COMPLETE FAILURE ⚠️

FAILURE REASON:
{exec_metadata['failure_reason']}

DATA PROVIDER CONFIGURATION:
- Rows Executed: {exec_metadata['data_provider_info']['rows_executed'] if exec_metadata['data_provider_info'] else 'N/A'}
- Total Available Rows: {exec_metadata['data_provider_info']['total_rows'] if exec_metadata['data_provider_info'] else 'N/A'}

EXECUTION SUMMARY:
- Status: COMPLETE FAILURE
- No tests were executed successfully
- All Caps and Scales: FAILED (0/49 = 0%)
- Reason: {exec_metadata['failure_reason']}

CRITICAL ISSUE:
This execution failed before any test validations could be performed. 
The failure occurred during setup or initial navigation phase.
No scale/CAP validations were attempted.

IMPACT ASSESSMENT:
- Total Validations Attempted: 0
- Total Validations Passed: 0
- Pass Rate: 0.0%
- All 49 scales and CAPs are considered FAILED due to execution failure

TROUBLESHOOTING REQUIRED:
This is a critical failure that prevented all tests from running.
Common causes:
- Login credentials issues
- Network connectivity problems  
- Application unavailable or down
- Browser/driver compatibility issues
- Configuration/setup errors

"""
    else:
        summary_text = f"""
════════════════════════════════════════════════════════════════
TEST EXECUTION COMPLETED ON: {human_readable_timestamp}
════════════════════════════════════════════════════════════════

EXECUTION TIMESTAMP: {exec_metadata['execution_timestamp']}
EXECUTION ID: {execution_id}
EXECUTION STATUS: ✓ COMPLETED

DATA PROVIDER CONFIGURATION:
- Rows Executed: {exec_metadata['data_provider_info']['rows_executed'] if exec_metadata['data_provider_info'] else 'N/A'}
- Total Available Rows: {exec_metadata['data_provider_info']['total_rows'] if exec_metadata['data_provider_info'] else 'N/A'}

TEST EXECUTION SUMMARY:
- Total Test Instances: {len(exec_metadata['test_instances'])}
- Total Validations per Test: {exec_metadata['total_validations']}
- Average Validations Passed: {exec_metadata['avg_passed']}/{exec_metadata['total_validations']}
- Average Pass Rate: {exec_metadata['avg_pass_rate']}%

TEST INSTANCE DETAILS:
"""
    
        for test_inst in exec_metadata['test_instances']:
            summary_text += f"""
Test #{test_inst['test_number']}:
  - Status: {test_inst['status']}
  - Passed: {test_inst['passed']}/{test_inst['total']}
  - Pass Rate: {test_inst['pass_rate']}%
  - Duration: {test_inst['duration_seconds']} seconds
"""
        
        summary_text += f"""
PASSED SCALES/CAPS ({len(exec_metadata['passed_scales'])}):
"""
        for scale in sorted(exec_metadata['passed_scales']):
            summary_text += f"  ✅ {scale}\n"
        
        summary_text += f"""
FAILED SCALES/CAPS ({len(exec_metadata['failed_scales'])}):
"""
        for scale in sorted(exec_metadata['failed_scales']):
            summary_text += f"  ❌ {scale}\n"
    
    summary_text += "\n" + "="*68 + "\n"
    summary_text += "FULL TEST EXECUTION LOG:\n"
    summary_text += "="*68 + "\n\n"
    summary_text += log_content

    # Parse logs into sections
    sections = parse_log_into_sections(log_content)

    documents = []
    
    # Create the main summary document with comprehensive metadata
    summary_metadata = {
        "execution_id": execution_id,
        "timestamp": system_timestamp,
        "human_timestamp": human_readable_timestamp,
        "log_timestamp": exec_metadata['execution_timestamp'],
        "execution_status": exec_metadata['execution_status'],
        "section": "execution_summary",
        "chunk_index": 0,
        "source": "automation_test_logs",
        "test_count": len(exec_metadata['test_instances']),
        "avg_passed": exec_metadata['avg_passed'],
        "avg_pass_rate": exec_metadata['avg_pass_rate'],
        "passed_scales_count": len(exec_metadata['passed_scales']),
        "failed_scales_count": len(exec_metadata['failed_scales']),
        "log_size": len(log_content)
    }
    
    # Add failure reason if execution failed
    if exec_metadata['execution_status'] == 'COMPLETE_FAILURE':
        summary_metadata['failure_reason'] = exec_metadata['failure_reason']
    
    # Add data provider info if available
    if exec_metadata['data_provider_info']:
        summary_metadata.update({
            "rows_executed": exec_metadata['data_provider_info']['rows_executed'],
            "total_rows": exec_metadata['data_provider_info']['total_rows']
        })
    
    # Add test instance details only if execution was successful
    if exec_metadata['execution_status'] != 'COMPLETE_FAILURE':
        for i, test_inst in enumerate(exec_metadata['test_instances']):
            summary_metadata[f"test_{i+1}_status"] = test_inst['status']
            summary_metadata[f"test_{i+1}_passed"] = test_inst['passed']
            summary_metadata[f"test_{i+1}_pass_rate"] = test_inst['pass_rate']
            summary_metadata[f"test_{i+1}_duration"] = test_inst['duration_seconds']
    
    documents.append(
        Document(
            text=summary_text,
            metadata=summary_metadata
        )
    )

    # Create individual section documents
    for i, (section_type, content) in enumerate(sections, start=1):

        if not content.strip():
            continue

        metadata = {
            "execution_id": execution_id,
            "timestamp": system_timestamp,
            "human_timestamp": human_readable_timestamp,
            "log_timestamp": exec_metadata['execution_timestamp'],
            "execution_status": exec_metadata['execution_status'],
            "section": section_type,
            "chunk_index": i,
            "source": "automation_test_logs"
        }
        
        # Add failure reason if execution failed
        if exec_metadata['execution_status'] == 'COMPLETE_FAILURE':
            metadata['failure_reason'] = exec_metadata['failure_reason']

        documents.append(
            Document(
                text=content,
                metadata=metadata
            )
        )

    print(f"\n✓ Created {len(documents)} document chunks")
    print(f"  - 1 comprehensive summary document")
    print(f"  - {len(documents)-1} section-specific documents")

    # ==============================
    # VECTOR STORE SETUP
    # ==============================

    chroma_client = chromadb.PersistentClient(path=CHROMA_PATH)

    try:
        collection = chroma_client.get_collection(COLLECTION_NAME)
        print("\n✓ Using existing collection")
    except:
        collection = chroma_client.create_collection(COLLECTION_NAME)
        print("\n✓ Created new collection")

    vector_store = ChromaVectorStore(chroma_collection=collection)
    storage_context = StorageContext.from_defaults(vector_store=vector_store)

    # Embedding model ONLY (no LLM needed)
    Settings.embed_model = HuggingFaceEmbedding(model_name=EMBED_MODEL)

    # ==============================
    # CREATE EMBEDDINGS
    # ==============================

    print("\n🔄 Creating embeddings...")

    VectorStoreIndex.from_documents(
        documents,
        storage_context=storage_context,
        show_progress=True
    )

    print("\n✅ EMBEDDINGS STORED SUCCESSFULLY")
    print(f"✓ Collection now contains {collection.count()} total embeddings")
    print(f"✓ Latest execution: {human_readable_timestamp}")
    print(f"✓ Execution ID: {execution_id}")


# ==============================
# ENTRY POINT
# ==============================

if __name__ == "__main__":
    create_embeddings()
