package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import util.DataFile;

public class Base {
	
	public static String OSType = null;
	public static String configFile = DataFile.ConfigFile;
	public static Properties prop;
	public static String opSys = System.getProperty("os.name");
	public static String root = System.getProperty("user.dir");
	public static File filePath=null;
	
	public static WebDriver driver;
	
	public Base(String dataFile)
	{
		try 
        {
            prop = new Properties();
            FileInputStream file = new FileInputStream(configFile);
            prop.load(file);
            
            filePath = new File(dataFile);
    		
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	public void initialize()
    {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		// Check if running in Docker environment
		boolean IS_DOCKER_RUNTIME = Boolean.parseBoolean(
			System.getProperty("docker.runtime", System.getenv("IS_DOCKER_RUNTIME"))
		);
		
		if (IS_DOCKER_RUNTIME) {
			System.out.println("🐳 Running in Docker - Configuring Chrome for headless execution");
			
			// Set ChromeDriver path explicitly for Docker
			String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
			if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				System.out.println("✓ ChromeDriver path set to: " + chromeDriverPath);
			}
			
			// Set Chromium binary location
			chromeOptions.setBinary("/usr/bin/chromium");
			System.out.println("✓ Chrome binary set to: /usr/bin/chromium");
			
			chromeOptions.addArguments("--headless");       // Use old headless (more stable)
			chromeOptions.addArguments("--no-sandbox");     // REQUIRED in Docker
			chromeOptions.addArguments("--disable-dev-shm-usage"); // avoids /dev/shm crash
			chromeOptions.addArguments("--disable-gpu");
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--window-size=1920,1080");
			chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--disable-background-timer-throttling");
			chromeOptions.addArguments("--disable-backgrounding-occluded-windows");
			chromeOptions.addArguments("--disable-renderer-backgrounding");
			chromeOptions.addArguments("--disable-features=IsolateOrigins,site-per-process");
			chromeOptions.setAcceptInsecureCerts(true);
			
			// Enable verbose logging
			System.setProperty("webdriver.chrome.verboseLogging", "true");
		}
		
		// Selenium 4.6+ has built-in Selenium Manager - WebDriverManager not needed
    	driver = new ChromeDriver(chromeOptions);

    	String url = prop.getProperty("loginURL");
    	driver.get(url);
    	
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	
    	
    }

}
