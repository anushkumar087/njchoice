# NJ Choice Test Automation - Commands Reference

## Table of Contents
- [Prerequisites Installation](#prerequisites-installation)
- [Project Setup](#project-setup)
- [AI Analysis Setup](#ai-analysis-setup)
- [Maven Test Commands](#maven-test-commands)
- [Troubleshooting](#troubleshooting)

---

## Prerequisites Installation

### 1. Install Java (JDK 11)

**macOS:**
```bash
# Using Homebrew
brew install openjdk@11

# Add to PATH (add to ~/.zshrc or ~/.bash_profile)
export PATH="/opt/homebrew/opt/openjdk@11/bin:$PATH"

# Verify installation
java -version
```

**Windows:**
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
2. Run the installer
3. Set `JAVA_HOME` environment variable:
   - Right-click "This PC" → Properties → Advanced System Settings
   - Environment Variables → New System Variable
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-11` (your JDK path)
4. Add to PATH: `%JAVA_HOME%\bin`
5. Verify: Open Command Prompt and run `java -version`

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jdk

# Verify installation
java -version
```

### 2. Install Maven

**macOS:**
```bash
# Using Homebrew
brew install maven

# Verify installation
mvn --version
```

**Windows:**
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to `C:\Program Files\Apache\maven`
3. Set `MAVEN_HOME` environment variable:
   - Variable name: `MAVEN_HOME`
   - Variable value: `C:\Program Files\Apache\maven`
4. Add to PATH: `%MAVEN_HOME%\bin`
5. Verify: Open Command Prompt and run `mvn --version`

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install maven

# Verify installation
mvn --version
```

### 3. Install Git

**macOS:**
```bash
# Using Homebrew
brew install git

# Verify installation
git --version
```

**Windows:**
1. Download from [Git for Windows](https://git-scm.com/download/win)
2. Run installer (use default settings)
3. Verify: Open Command Prompt and run `git --version`

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install git

# Verify installation
git --version
```

### 4. Install Python 3.8+

**macOS:**
```bash
# Using Homebrew
brew install python3

# Verify installation
python3 --version
pip3 --version
```

**Windows:**
1. Download from [Python.org](https://www.python.org/downloads/)
2. Run installer
3. ✅ **IMPORTANT:** Check "Add Python to PATH" during installation
4. Verify: Open Command Prompt and run `python --version` and `pip --version`

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install python3 python3-pip python3-venv

# Verify installation
python3 --version
pip3 --version
```

---

## Project Setup

### Clone Repository
```bash
# Navigate to the folder where you want to clone the project
cd /path/to/your/desired/folder

# Clone the repository
git clone https://github.com/anushkumar087/njchoice.git
cd njchoice
```

### Verify Prerequisites
```bash
java -version    # Should show Java 11
mvn --version    # Should show Maven 3.x
python3 --version # Should show Python 3.8+
```

---

## AI Analysis Setup

### One-Time Setup (Run Once)

**From project root directory:**

```bash
# 1. Navigate to project root (if not already there)
cd /path/to/njchoice

# 2. Make setup script executable
chmod +x setup-ai.sh

# 3. Run setup script
./setup-ai.sh
```

The script will:
- ✅ Create Python virtual environment in `ai-analysis/venv`
- ✅ Install all required Python packages
- ✅ Create `.env` file from template
- ✅ Set up necessary directories

### Configure OpenAI API Key

After running setup, you MUST add your OpenAI API key:

```bash
# 1. Edit the .env file
nano ai-analysis/.env
# OR
vim ai-analysis/.env
# OR open in your text editor

# 2. Add your API key
OPENAI_API_KEY=sk-your-actual-openai-api-key-here

# 3. Save and close
```

### Manual Setup (Alternative Method)

If you prefer manual setup:

```bash
# Navigate to ai-analysis directory
cd ai-analysis

# Create virtual environment
python3 -m venv venv

# Activate virtual environment
source venv/bin/activate  # macOS/Linux
# OR
venv\Scripts\activate     # Windows

# Upgrade pip
pip install --upgrade pip

# Install dependencies
pip install -r requirements.txt

# Create .env file
cp .env.example .env

# Edit .env and add your OpenAI API key
nano .env
```
---

## Maven Test Commands

### Run All Available Test Suites

**Note:** All test commands can be run in two ways:

1. **Using credentials from `config.properties`** (default):
   ```bash
   mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml
   ```

2. **Overriding credentials via command line**:
   ```bash
   mvn test \
     -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml \
     -Dusername=your.email@example.com \
     -Dpassword=YourPassword123
   ```

---

#### 1. **First 3 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 2. **First 5 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_first5rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_first5rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 3. **First 10 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_first10rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_first10rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 4. **Random 3 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_random3rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_random3rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 5. **Random 5 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_random5rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_random5rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 6. **Random 10 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_random10rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_random10rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 7. **Random 20 Rows Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_random20rows.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_random20rows.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 8. **Single Random Row Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_singlerandomrow.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_singlerandomrow.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

#### 9. **All Sections Data Test**
```bash
# Using credentials from config.properties
mvn test -Dtestng.suite.xml=src/test/resources/testng_allsectionsdata.xml

# OR with custom credentials
mvn test -Dtestng.suite.xml=src/test/resources/testng_allsectionsdata.xml -Dusername=your.email@example.com -Dpassword=YourPassword123
```

### View Test Reports

After running tests, reports are generated in:
- **Excel Report:** `reports/ComprehensiveTestReport_[timestamp].xlsx`
- **AI Analysis Report (Open in browser):** `reports-ai/AI_Analysis_Report_[timestamp].html`

---

## Troubleshooting

### Common Issues

#### Issue: `mvn: command not found`
**Solution:** Maven not installed or not in PATH
```bash
# Verify installation
which mvn

# If not found, install Maven (see Prerequisites section)
```

#### Issue: `JAVA_HOME not set`
**Solution:**
```bash
# macOS/Linux - Add to ~/.zshrc or ~/.bash_profile
export JAVA_HOME=$(/usr/libexec/java_home -v 11)

# Windows - Set environment variable
# JAVA_HOME = C:\Program Files\Java\jdk-11
```

#### Issue: `python3: command not found`
**Solution:** Python not installed or not in PATH
```bash
# Verify installation
which python3

# If not found, install Python (see Prerequisites section)
```

#### Issue: ChromeDriver version mismatch
**Solution:** Update ChromeDriver in the project or let the test auto-download the correct version

#### Issue: Tests failing with "Element not found"
**Solution:** 
- Check your internet connection
- Verify Salesforce credentials in `config.properties`
- Increase wait times in `OptimizedCommands.java` if needed

#### Issue: AI Analysis not generating reports
**Solution:**
1. Verify `.env` file has valid OpenAI API key
2. Check `ai-analysis/temp_logs.txt` exists and has content
3. Verify Python packages installed: `pip list | grep llama-index`
4. Check for errors in console output

#### Issue: `Permission denied` when running `./setup-ai.sh`
**Solution:**
```bash
chmod +x setup-ai.sh
```

#### Issue: Virtual environment activation fails
**Solution:**
```bash
# Recreate virtual environment
rm -rf ai-analysis/venv
./setup-ai.sh
```

---

## Quick Reference

### Most Common Commands

```bash
# 1. Setup AI Analysis (one-time)
./setup-ai.sh

# 2. Run small test (3 rows)
mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml

# 3. Run with custom credentials
mvn test \
  -Dtestng.suite.xml=src/test/resources/testng_random5rows.xml \
  -Dusername=your@email.com \
  -Dpassword=YourPassword

# 4. View latest AI analysis report
open reports-ai/*.html  # macOS
start reports-ai/*.html # Windows
xdg-open reports-ai/*.html # Linux
```

---

## Need Help?

- **Test Framework Issues:** Check `README.md` for detailed documentation
- **AI Analysis Issues:** See `ai-analysis/README.md` and `ai-analysis/QUICKSTART.md`
- **Maven Issues:** Run `mvn clean install` to rebuild project
- **Python Issues:** Recreate virtual environment with `./setup-ai.sh`

---

**Last Updated:** February 15, 2026
