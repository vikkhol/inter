package Dmitry;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserScript {

	WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.firefox.marionette", "C:\\Users\\vkholin\\Automation\\browsers\\geckodriver-v0.17.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","C:\\Users\\vkholin\\Automation\\browsers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else{
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testParameterWithXML() throws InterruptedException{
		driver.get("http://newtours.demoaut.com/");
	}

	@AfterTest
	public void done(){
		driver.quit();
	}

}