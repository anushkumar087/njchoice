# 🐳 Running NJChoice Tests in Docker

This project includes Docker support for running TestNG tests in an isolated, reproducible environment.

## Quick Start

### Option 1: Using the Helper Script (Recommended)

```bash
# Run smoke tests (default)
./run-docker-tests.sh smoke

# Run regression tests
./run-docker-tests.sh regression

# Run comprehensive tests
./run-docker-tests.sh comprehensive

# Run with custom XML file
./run-docker-tests.sh custom src/test/resources/my_custom.xml
```

### Option 2: Using Docker Compose

```bash
# Run smoke tests
docker-compose up --build njchoice-smoke

# Run comprehensive tests
docker-compose up --build njchoice-comprehensive

# Run regression tests
docker-compose up --build njchoice-regression

# Run dynamic tests
docker-compose up --build njchoice-dynamic

# Run specific tests
docker-compose up --build njchoice-specific

# Run basic tests
docker-compose up --build njchoice-basic
```

### Option 3: Using Docker Directly

```bash
# Build the image
docker build -t njchoice-test .

# Run with default settings (smoke tests)
docker run --rm njchoice-test

# Run with custom TestNG XML
docker run --rm \
  -e TESTNG_SUITE_XML=src/test/resources/testng_comprehensive.xml \
  -e IS_DOCKER_RUNTIME=true \
  njchoice-test

# Run with volume mounts to save reports
docker run --rm \
  -e TESTNG_SUITE_XML=src/test/resources/testng_comprehensive_regression.xml \
  -e IS_DOCKER_RUNTIME=true \
  -v "$(pwd)/reports:/app/reports" \
  -v "$(pwd)/target/surefire-reports:/app/target/surefire-reports" \
  njchoice-test
```

## Environment Variables

| Variable | Description | Default | Required |
|----------|-------------|---------|----------|
| `TESTNG_SUITE_XML` | Path to TestNG XML file | `src/test/resources/testng_comprehensive_smoke.xml` | No |
| `IS_DOCKER_RUNTIME` | Flag indicating Docker execution | `true` | Yes (auto-set) |
| `CHROME_HEADLESS` | Run Chrome in headless mode | `true` | No |

## Available Test Suites

| Suite | XML File | Description |
|-------|----------|-------------|
| Smoke | `testng_comprehensive_smoke.xml` | Quick smoke tests |
| Comprehensive | `testng_comprehensive.xml` | Full comprehensive tests |
| Regression | `testng_comprehensive_regression.xml` | Regression test suite |
| Dynamic | `testng_comprehensive_dynamic.xml` | Dynamic test cases |
| Specific | `testng_specific.xml` | Specific targeted tests |
| Basic | `testng.xml` | Basic TestNG suite |

## Reports & Artifacts

Test reports are automatically saved to:
- `./reports/` - Custom test reports
- `./target/surefire-reports/` - Maven Surefire reports

When using Docker Compose or the helper script, these directories are automatically mounted as volumes so reports persist after the container exits.

## Docker Runtime Behavior

When `IS_DOCKER_RUNTIME=true`, the test framework:
- ✅ Automatically uses the verification code input from console
- ✅ Skips interactive prompts that require manual browser interaction
- ✅ Configures headless browser execution
- ✅ Optimizes for CI/CD pipeline execution

## Troubleshooting

### Maven Clean Plugin Error

If you see: `Failed to delete /app/target/surefire-reports`

**Solution 1 (Automatic):** The helper script now cleans directories before running
```bash
./run-docker-tests.sh smoke
```

**Solution 2:** Manually clean before running
```bash
rm -rf target/surefire-reports/* reports/*
docker-compose up --build njchoice-smoke
```

**Solution 3:** Run without volume mounts (reports won't persist)
```bash
docker run --rm -e TESTNG_SUITE_XML=src/test/resources/testng_comprehensive_smoke.xml njchoice-test
```

**Note:** The Dockerfile now uses `mvn test` instead of `mvn clean test` to avoid conflicts with mounted volumes.

### Build Issues

```bash
# Clean build with no cache
docker build --no-cache -t njchoice-test .
```

### Permission Issues with Reports

```bash
# Fix permissions on Linux/Mac
sudo chown -R $USER:$USER reports/ target/

# Or give full permissions
chmod -R 777 reports/ target/
```

### View Container Logs

```bash
# View logs from docker-compose
docker-compose logs njchoice-smoke

# Follow logs in real-time
docker-compose logs -f njchoice-smoke
```

### Clean Up

```bash
# Remove all stopped containers
docker-compose down

# Remove containers and volumes
docker-compose down -v

# Remove the built image
docker rmi njchoice-test
```

## CI/CD Integration

### GitHub Actions Example

```yaml
name: Run Tests
on: [push]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Run Smoke Tests
        run: docker-compose up --build --exit-code-from njchoice-smoke njchoice-smoke
      - name: Upload Reports
        uses: actions/upload-artifact@v2
        with:
          name: test-reports
          path: reports/
```

### Jenkins Pipeline Example

```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh './run-docker-tests.sh regression'
            }
        }
        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
```

## Advanced Usage

### Custom Environment Variables

```bash
docker run --rm \
  -e TESTNG_SUITE_XML=src/test/resources/my_custom.xml \
  -e IS_DOCKER_RUNTIME=true \
  -e CHROME_HEADLESS=false \
  -e MY_CUSTOM_VAR=value \
  njchoice-test
```

### Interactive Mode

```bash
# Run container with interactive shell
docker run -it --rm njchoice-test /bin/bash

# Inside container, run tests manually
mvn clean test -Dtestng.suite.xml=src/test/resources/testng_comprehensive.xml
```

## Notes

- The `IS_DOCKER_RUNTIME` environment variable is automatically set to `true` in the Dockerfile
- The `-Ddocker.runtime=true` system property is passed to Maven in the CMD
- Your test code checks both the environment variable and system property for maximum flexibility
- Verification code input is handled differently when running in Docker vs. locally

---

For more information, see the main project README or contact the development team.
