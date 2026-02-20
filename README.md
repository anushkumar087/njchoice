# NJChoice Test Automation - User Guide

## Overview
This project contains automated tests for the NJChoice Salesforce application. Tests are built using Selenium WebDriver, TestNG, and Maven.

---

## Prerequisites
- Java 11 or higher
- Maven 3.x
- Chrome browser (for local execution)
- Docker (optional, for containerized execution)

---

## Configuration

### Application URLs
All application URLs (Salesforce login URL, sandbox URL, etc.) are configured in:
```
src/test/java/config/config.properties
```

### Login Credentials
Username and password are configured in `src/test/java/config/config.properties`

**To override credentials at runtime**, use Maven command line parameters:
```bash
mvn test -Dusername=your@email.com -Dpassword=yourPassword -Dtestng.suite.xml=<test-suite-xml>
```

### Section A Test Data
Section A values (referral information, personal details, etc.) are configured in:
```
src/test/java/config/config_sectionA_data.properties
```

You can edit this file to change test data like:
- `firstName`, `lastName`
- `referralDate`, `birthdate`
- `socialSecurityNumber`
- `streetAddress1`, `city1`, `zipCode1`, `county`
- And other Section A related fields

### Master Test Data File
The master test data file is located at:
```
test_data/Master_Data.xlsx
```

**⚠️ IMPORTANT - Master Data File Guidelines:**

1. **File Name**: The file name must remain exactly as `Master_Data.xlsx` - DO NOT rename this file.

2. **Column Headers**: Column headers (first row) must NOT be changed under any circumstances. If column headers need modification, the Java code must be refactored accordingly.

3. **Data Modifications**: You can modify the data rows (below the header row), but follow these rules:
   - Keep the data structure intact
   - Do not add or remove columns

4. **Output Value Format**: Expected output values in the Excel file must follow one of these two formats:
   - **Number only**: `0`, `1`, `2`, etc.
   - **Number with description**: `0: Not triggered`, `1: Triggered`, `2: Moderate risk`, etc.
   
   Any other format may cause test failures or incorrect validations.

5. **No Changes Recommended**: This file is pre-configured with test data. Avoid making changes unless absolutely necessary.

---

## Running Tests Locally (Execution)

### Quick Start - Default Test (Random 3 Rows Test)
```bash
mvn test -Dusername=your@email.com -Dpassword=yourPassword
```

### Run Specific Test Suites

#### 1. First 3 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_first3rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 2. First 5 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_first5rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 3. First 10 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_first10rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 4. Random 3 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_random3rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 5. Random 5 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_random5rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 6. Random 10 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_random10rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 7. Random 20 Rows Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_random20rows.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 8. Single Random Row Test
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_singlerandomrow.xml -Dusername=your@email.com -Dpassword=yourPassword
```

#### 9. All Sections Data Test (Full Test Suite)
```bash
mvn test -Dtestng.suite.xml=src/test/resources/testng_allsectionsdata.xml -Dusername=your@email.com -Dpassword=yourPassword
```

---

## Test Reports

After test execution, reports are generated in:
- **TestNG HTML Report (This does not indicate CAPS / SCALES results)**: `target/surefire-reports/index.html`
- **Excel Report (This is actual report)**: `reports/ComprehensiveTestReport_<timestamp>.xlsx`
- **AI Generated Report (This is actual report)**: `reports/AI_Analysis_Report_<timestamp>.html`

Open the HTML report in your browser to view detailed test results. Same AI generated report details and logs can also be seen in console.

---

## Verification Code Entry

### Local Execution
When running tests locally, automation enters user email and password and click on Login and then a browser prompt will appear for 2 minutes on the next page to manually enter the verification code from your email. Check the received code on email and manually enter it.

---

## Data Providers

The test suite uses different data providers to control which test data rows are executed:

| Data Provider | Description | XML File |
|--------------|-------------|----------|
| First3Rows | First 3 rows from Excel | testng_first3rows.xml |
| First5Rows | First 5 rows from Excel | testng_first5rows.xml |
| First10Rows | First 10 rows from Excel | testng_first10rows.xml |
| Random3Rows | 3 random rows from Excel | testng_random3rows.xml |
| Random5Rows | 5 random rows from Excel | testng_random5rows.xml |
| Random10Rows | 10 random rows from Excel | testng_random10rows.xml |
| Random20Rows | 20 random rows from Excel | testng_random20rows.xml |
| SingleRandomRow | 1 random row from Excel | testng_singlerandomrow.xml |
| AllSectionsData | All rows from Excel | testng_allsectionsdata.xml |

---

## Troubleshooting

### Maven not found
Install Maven using Homebrew (macOS):
```bash
brew install maven
```

Verify installation:
```bash
mvn --version
```

### Chrome driver issues
The project uses Selenium Manager to automatically download and manage ChromeDriver. No manual installation required.

### Verification code timeout
Enter the verification code quickly after receiving it. The code typically expires in 2 minutes.
