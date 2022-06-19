
package constant;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class AppUtil {

	public static WebDriver driver;
    public static Properties config;

	@BeforeSuite 

	public void setup() throws Throwable
	{
      		
		config= new Properties();
		config.load(new FileInputStream("D:\\Swapna_Selenium\\Hybrid_FrameWork\\Properties\\primus.properties"));
		if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else if(config.getProperty("Browser").equalsIgnoreCase("Firefox"))
		{
			driver= new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.get(config.getProperty("Url"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		else
		{
			System.out.println("Browser value is not matching");
		}
	}
	@AfterSuite

	public void tearDown()
	{
		driver.close();

	}





}
