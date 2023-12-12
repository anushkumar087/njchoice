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

import io.github.bonigarcia.wdm.WebDriverManager;
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
		// Commenting the below line due to incompatibility with Chrome 116
		WebDriverManager.chromedriver().setup();
    	driver = new ChromeDriver(chromeOptions);

    	String url = prop.getProperty("loginURL");
    	driver.get(url);
    	
    	driver.manage().window().maximize();
    	driver.manage().deleteAllCookies();
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(80));
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    	
    	
    }

}
