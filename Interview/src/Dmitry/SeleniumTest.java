package Dmitry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class SeleniumTest {

	WebDriver driver;
	UserData dummyUser;
	
	By countrySelectLocator = By.tagName("select");
	By loginSelector = By.id("email");
	By passwordSelector = By.name("password");
	By confirmPasswordSelector = By.name("confirmPassword");
	By registerButtonSelector = By.name("register");

	
	@BeforeTest
	public void setup() throws Exception{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\vkholin\\Automation\\browsers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		
		
		dummyUser = new UserData();
		dummyUser.setUsername("dummy");
		dummyUser.setPassword("dummy");
		dummyUser.setCountry("AMERICAN SAMOA ");
	}
	
	@Test
	public void testParameterWithXML() throws InterruptedException
	{
		driver.get("http://newtours.demoaut.com/");

	}
	

	@Test
	public void testRegisteredPageIsOpened() throws InterruptedException{
		navigateToRegistationPage();
		
		String currentUrl = driver.getCurrentUrl();
		String expectedUrlPart = "mercuryregister";
		
		Assert.assertTrue("Wrong url after navigation to register section. \n Expected: "+currentUrl+" to contain "+expectedUrlPart,
				currentUrl.contains(expectedUrlPart) );
		
	}
	
	@Test
	public void testUserCanBeRegistered() throws InterruptedException{
		navigateToRegistationPage();
		
		WebElement usernameTextBox = driver.findElement(loginSelector);
		WebElement passwordTextBox = driver.findElement(passwordSelector);
		WebElement confirmPasswordTextBox = driver.findElement(confirmPasswordSelector);
		Select countrySelect = new Select( driver.findElement(countrySelectLocator));
		WebElement registerButton = driver.findElement(registerButtonSelector);
		
		usernameTextBox.sendKeys(dummyUser.getUsername());
		passwordTextBox.sendKeys(dummyUser.getPassword());
		confirmPasswordTextBox.sendKeys(dummyUser.getPassword());
		
		countrySelect.selectByVisibleText(dummyUser.getCountry());
		
		
		registerButton.click();
	}
	
	@Test
	public void testNavigationBack() throws InterruptedException{
		navigateToRegistationPage();
		
		String title = driver.getTitle();
		
		driver.navigate().back();
	}
	
	
	@AfterTest
	public void done(){
		driver.quit();
	}
	
	private void navigateToRegistationPage(){
		driver.get("http://newtours.demoaut.com/");
		
		By registerButtonLocator = By.xpath("//a[contains(text(), 'REGISTER')]");
		
		WebElement registerBtn = driver.findElement(registerButtonLocator);
		registerBtn.click();
	}
	
	

}