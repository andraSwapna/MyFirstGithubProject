package commonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil {

	// METHOD FOR LOGIN
	public static boolean verifyLogin(String username, String password) throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys("username");
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys("password");
		driver.findElement(By.xpath(config.getProperty("ObjLogin"))).click();
		String expected= "adminflow";
		String actual=driver.getCurrentUrl();
		if (actual.toLowerCase().contains(expected))
		{
			Reporter.log("Login success::"+expected+"          "+actual, true);
			return true;
		}
		else 
		{
			Reporter.log("Login Fail::"+expected+"             "+actual, true);
			return false;
		}

	}

	// METHOD FOR BRANCH CLICK
	public static void clickBranches() throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjBranch"))).click();
		Thread.sleep(3000);
	}
	public static boolean verifyBranch(String BranchName, String Address1, String Address2, 
			String Address3, String Area, String Zipcode, String Country, String State, String City)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Objnewbranchbtn"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("Objbranchname"))).sendKeys("BranchName");
		driver.findElement(By.xpath(config.getProperty("ObjAd1"))).sendKeys("Address1");
		driver.findElement(By.xpath(config.getProperty("ObjAd2"))).sendKeys("Address2");
		driver.findElement(By.xpath(config.getProperty("ObjAd3"))).sendKeys("Address3");
		driver.findElement(By.xpath(config.getProperty("ObjArea"))).sendKeys("Area");
		driver.findElement(By.xpath(config.getProperty("Zipcode"))).sendKeys("Zipcode");
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCountry")))).selectByValue(Country);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjState")))).selectByVisibleText(State);
		Thread.sleep(2000);
		new Select(driver.findElement(By.xpath(config.getProperty("ObjCity")))).selectByVisibleText(City);
		Thread.sleep(3000);
		String expectedBranchalert=driver.switchTo().alert().getText();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		String actualBranchalert="created Sucessfully";
		if (expectedBranchalert.toLowerCase().contains(actualBranchalert.toLowerCase()))
		{
			Reporter.log(expectedBranchalert, true);
			return true;

		}
		else 
		{
			Reporter.log("New branch creation fail", true);
			return false;

		}
	}
	// METHOD FOR BRANCH UPDATION
	public static boolean verifyBranchupdate(String Branchname, String Adress, String Zipcode)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("ObjEdit"))).click();
		Thread.sleep(3000);
		WebElement bname = driver.findElement(By.xpath(config.getProperty("Branchname")));
		bname.clear();
		bname.sendKeys("Branchname");
		Thread.sleep(2000);
		WebElement address =driver.findElement(By.xpath(config.getProperty("ObjAdd1")));
		address.clear();
		address.sendKeys(Adress);
		Thread.sleep(3000);
		WebElement zipcode =driver.findElement(By.xpath(config.getProperty("ObjZcode")));
		zipcode.clear();
		zipcode.sendKeys(Zipcode);
		Thread.sleep(2000);
		driver.findElement(By.xpath(config.getProperty("ObjUpdate"))).click();
		String expectedBupate=driver.switchTo().alert().getText();
		Thread.sleep(3000);
		driver.switchTo().alert().accept();
		Thread.sleep(3000);
		String actualUpdatealert="updated Sucessfully";
		if(expectedBupate.toLowerCase().contains(actualUpdatealert.toLowerCase()))
		{
			Reporter.log("expectedBupate", true);
			return true;
		}
		else
		{
			Reporter.log("Branch update fail", true);
			return false;


		}
	}
	// METHOD FOR LOGOUT

	public static boolean verifyLogout()  throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Objlogout"))).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath(config.getProperty("ObjLogin"))).isDisplayed())
		{
			Reporter.log("Logout success",true);
			return true;


		}
		else
		{
			Reporter.log("Logout fail",true);
			return false;

		}

	}

}























