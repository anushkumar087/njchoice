#!/bin/bash

echo "🚀 Setting up AI Analysis environment..."
echo ""

# Navigate to ai-analysis directory
cd ai-analysis || { echo "❌ ai-analysis folder not found"; exit 1; }

# Check if Python is installed
if ! command -v python3 &> /dev/null; then
    echo "❌ Python 3 is not installed. Please install Python 3.8 or higher."
    exit 1
fi

echo "✓ Python 3 found: $(python3 --version)"

# Create virtual environment if it doesn't exist
if [ ! -d "venv" ]; then
    echo "📦 Creating virtual environment..."
    python3 -m venv venv
else
    echo "✓ Virtual environment already exists"
fi

# Activate virtual environment
echo "🔧 Activating virtual environment..."
source venv/bin/activate

# Upgrade pip
echo "⬆️  Upgrading pip..."
pip install --upgrade pip > /dev/null 2>&1

# Install requirements
echo "📥 Installing dependencies from requirements.txt..."
pip install -r requirements.txt

# Create .env if it doesn't exist
if [ ! -f ".env" ]; then
    echo "📝 Creating .env file from template..."
    cp .env.example .env
    echo "⚠️  Please edit ai-analysis/.env and add your OPENAI_API_KEY"
fi

# Create necessary directories
mkdir -p chroma_db
mkdir -p ../reports-ai

echo ""
echo "✅ Setup complete!"
echo ""
echo "📍 To use the AI analysis:"
echo "   1. Edit ai-analysis/.env and add your OPENAI_API_KEY"
echo "   2. Run tests - embeddings and analysis happen automatically"
echo ""
echo "💡 To manually activate the environment:"
echo "   cd ai-analysis && source venv/bin/activate"
echo ""
