#!/bin/bash

# NJChoice Docker Test Runner
# This script helps you run TestNG tests in Docker with various configurations

echo "🐳 NJChoice Docker Test Runner"
echo "================================"
echo ""

# Color codes
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

show_usage() {
    echo -e "${BLUE}Usage:${NC}"
    echo "  ./run-docker-tests.sh [test-suite]"
    echo ""
    echo -e "${BLUE}Available test suites:${NC}"
    echo "  smoke         - Run smoke tests (default)"
    echo "  comprehensive - Run comprehensive tests"
    echo "  regression    - Run regression tests"
    echo "  dynamic       - Run dynamic tests"
    echo "  specific      - Run specific tests"
    echo "  basic         - Run basic tests"
    echo "  custom        - Specify custom XML file"
    echo ""
    echo -e "${BLUE}Examples:${NC}"
    echo "  ./run-docker-tests.sh smoke"
    echo "  ./run-docker-tests.sh regression"
    echo "  ./run-docker-tests.sh custom src/test/resources/my_custom.xml"
    echo ""
}

# Check if help is requested
if [ "$1" == "-h" ] || [ "$1" == "--help" ]; then
    show_usage
    exit 0
fi

# Default to smoke tests
TEST_SUITE=${1:-smoke}

# Clean target and reports directories before running (optional)
echo -e "${BLUE}Cleaning previous test artifacts...${NC}"
rm -rf target/surefire-reports/* 2>/dev/null || true
rm -rf reports/* 2>/dev/null || true

case $TEST_SUITE in
    smoke)
        echo -e "${GREEN}Running Smoke Tests...${NC}"
        docker-compose up --build njchoice-smoke
        ;;
    comprehensive)
        echo -e "${GREEN}Running Comprehensive Tests...${NC}"
        docker-compose up --build njchoice-comprehensive
        ;;
    regression)
        echo -e "${GREEN}Running Regression Tests...${NC}"
        docker-compose up --build njchoice-regression
        ;;
    dynamic)
        echo -e "${GREEN}Running Dynamic Tests...${NC}"
        docker-compose up --build njchoice-dynamic
        ;;
    specific)
        echo -e "${GREEN}Running Specific Tests...${NC}"
        docker-compose up --build njchoice-specific
        ;;
    basic)
        echo -e "${GREEN}Running Basic Tests...${NC}"
        docker-compose up --build njchoice-basic
        ;;
    custom)
        if [ -z "$2" ]; then
            echo -e "${YELLOW}Error: Please specify the XML file path${NC}"
            echo "Example: ./run-docker-tests.sh custom src/test/resources/my_tests.xml"
            exit 1
        fi
        echo -e "${GREEN}Running Custom Tests: $2${NC}"
        docker build -t njchoice-test .
        docker run --rm \
            -e TESTNG_SUITE_XML="$2" \
            -e IS_DOCKER_RUNTIME=true \
            -v "$(pwd)/reports:/app/reports" \
            -v "$(pwd)/target/surefire-reports:/app/target/surefire-reports" \
            njchoice-test
        ;;
    *)
        echo -e "${YELLOW}Unknown test suite: $TEST_SUITE${NC}"
        echo ""
        show_usage
        exit 1
        ;;
esac

echo ""
echo -e "${GREEN}✓ Test execution completed!${NC}"
echo -e "${BLUE}Reports available in:${NC}"
echo "  - ./reports/"
echo "  - ./target/surefire-reports/"
