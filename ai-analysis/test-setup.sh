#!/bin/bash
# Quick test script for AI analysis setup

echo "🔍 AI Analysis Setup Checker"
echo "=============================="
echo ""

# Check if we're in the right directory
if [ ! -f "requirements.txt" ]; then
    echo "❌ Error: Please run this from the ai-analysis directory"
    exit 1
fi

# Check virtual environment
if [ ! -d "venv" ]; then
    echo "❌ Virtual environment not found"
    exit 1
fi
echo "✅ Virtual environment found"

# Check .env file
if [ ! -f ".env" ]; then
    echo "❌ .env file not found"
    echo "   Run: cp .env.example .env"
    echo "   Then add your OpenAI API key"
    exit 1
fi
echo "✅ .env file exists"

# Check if API key is set
source .env
if [ -z "$OPENAI_API_KEY" ] || [ "$OPENAI_API_KEY" = "your-openai-api-key-here" ]; then
    echo "❌ OPENAI_API_KEY not configured in .env"
    echo "   Get your key from: https://platform.openai.com/api-keys"
    exit 1
fi
echo "✅ OPENAI_API_KEY is configured"

# Activate venv and test
source venv/bin/activate

echo ""
echo "🧪 Testing Python scripts..."
echo ""

# Create test log
echo "Test log entry at $(date)" > temp_logs.txt
echo "Sample warning: Field X could not be filled" >> temp_logs.txt
echo "Sample success: Section A completed" >> temp_logs.txt
echo "✅ Created test log"

# Test embeddings
echo ""
echo "Testing embeddings.py..."
python3 embeddings.py
if [ $? -eq 0 ]; then
    echo "✅ embeddings.py works!"
else
    echo "❌ embeddings.py failed"
    deactivate
    exit 1
fi

# Test analysis
echo ""
echo "Testing analysis.py..."
python3 analysis.py "Summarize the test logs briefly"
if [ $? -eq 0 ]; then
    echo "✅ analysis.py works!"
else
    echo "❌ analysis.py failed"
    deactivate
    exit 1
fi

deactivate

echo ""
echo "=============================="
echo "🎉 All tests passed!"
echo "=============================="
echo ""
echo "You're ready to run your Java tests with AI analysis!"
echo ""
echo "Run tests with:"
echo "  mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml \\"
echo "    -Dusername=your@email.com -Dpassword=yourPassword"
echo ""
