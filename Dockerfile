# Multi-platform Dockerfile (works on ARM64 and AMD64)
# Use Maven base image with Java 11 (supports both architectures)
FROM maven:3.8.6-openjdk-11

# Switch to root
USER root

# Install Chromium and dependencies (works on both ARM64 and AMD64)
# Chromium is open-source and has native ARM64 support
RUN apt-get update && \
    apt-get install -y \
    chromium \
    chromium-driver \
    fonts-liberation \
    fonts-ipafont-gothic \
    fonts-wqy-zenhei \
    fonts-thai-tlwg \
    fonts-kacst \
    fonts-freefont-ttf \
    libatk-bridge2.0-0 \
    libatk1.0-0 \
    libatspi2.0-0 \
    libcups2 \
    libdbus-1-3 \
    libdrm2 \
    libgbm1 \
    libgtk-3-0 \
    libnspr4 \
    libnss3 \
    libwayland-client0 \
    libxcomposite1 \
    libxdamage1 \
    libxfixes3 \
    libxkbcommon0 \
    libxrandr2 \
    xdg-utils \
    libu2f-udev \
    libvulkan1 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Create symlinks so Selenium finds chromium as google-chrome
RUN ln -s /usr/bin/chromium /usr/bin/google-chrome && \
    ln -s /usr/bin/chromium /usr/bin/chrome

# Verify installation and set up ChromeDriver
RUN echo "Platform: $(uname -m)" && \
    echo "Chromium version:" && chromium --version && \
    echo "ChromeDriver version:" && chromedriver --version && \
    echo "ChromeDriver location: $(which chromedriver)" && \
    echo "Java version:" && java -version

# Set ChromeDriver path for Selenium
ENV CHROMEDRIVER_PATH=/usr/bin/chromedriver
ENV PATH="${PATH}:/usr/bin"

# Set working directory
WORKDIR /app

# Copy pom.xml first for better caching
COPY pom.xml .

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy the entire project
COPY . .

# Set default environment variables
ENV TESTNG_SUITE_XML=src/test/resources/testng_comprehensive_smoke.xml
ENV IS_DOCKER_RUNTIME=true
ENV CHROME_HEADLESS=true

# Expose any ports if needed
EXPOSE 8080

# Create report directories with proper permissions
RUN mkdir -p /app/reports /app/target/surefire-reports && \
    chmod -R 777 /app/reports /app/target

# Increase shared memory for Chrome (prevents OOM crashes)
RUN mkdir -p /dev/shm && chmod 1777 /dev/shm

# Default command: Run tests
CMD mvn test \
    -Dtestng.suite.xml=${TESTNG_SUITE_XML} \
    -Ddocker.runtime=${IS_DOCKER_RUNTIME} \
    -Dmaven.test.failure.ignore=false \
    -DforkCount=1 \
    -DreuseForks=true
