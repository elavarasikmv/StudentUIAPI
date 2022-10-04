package stepDefinitions;


import org.testng.Assert;
import org.testng.AssertJUnit;

import com.qa.helper.LoggerHelper;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jdk.internal.org.jline.utils.Log;


public class WebLoginStepDefinition {
	
 WebDriver driver;
 WebDriverWait wait;
 Logger log = LoggerHelper.getLogger(LoggerHelper.class);
	
	
	//Using By locators
	By FORMAUTHLNK = By.linkText("Form Authentication");
	By USERNAME = By.id("username");
	By PASSWORD = By.id("password");
	By LOGINBTN = By.xpath("//button/i");
	By WELCOMETXT = By.className("subheader");
	By LOGOUTBTN = By.xpath("//a/i");
	By ERRORTXT = By.cssSelector(".flash.error");
	
	

	@Given("^User is on herokuapp.com website$")
	public void User_is_on_herokuapp_com_website()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\eladh\\eclipse-workspace\\elaTechTest\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com");
		log.info("*******driver is intialized and the browser is opened*****");
		
	}
	
	  @When("^User clicks on Form Authentication$")
	  public void User_clicks_on_Form_Authentication()
		{
			WebElement e = driver.findElement(FORMAUTHLNK);
			e.click();
			log.info("****************Authentication link is clicked**********");
		}
	    @Then ("^User is displayed with Login Page$")
	    public void User_is_displayed_with_Login_Page()
		{
			String title= driver.getTitle();
			String expected ="The Internet";
			AssertJUnit.assertEquals(expected, title);
			log.info("***Title of the page displayed as******"+title);
		}
	    
	    @And("user enters {string} and {string}")
	    public void User_enters_username_and_password(String username,String password) throws Throwable
	    {
	    	try {
	    		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
	    		driver.findElement(USERNAME).sendKeys(username);
	    		
	    	}
	    	catch (Exception e)
	    	{
	    		e.printStackTrace();
	    	}

    		driver.findElement(PASSWORD).sendKeys(password);
    		driver.findElement(LOGINBTN).click();
    		
    		log.info("****************Login button is clicked**********");
	    			
	    }
	    
	    @Then("^User Logged into the Secure Area Page with the Logout button$")
	    public void User_Logged_into_the_Secure_Area_Page_with_the_Logout_button()
	    {
	    	//retrieve the welcome message content
	    	String message = driver.findElement(WELCOMETXT).getText();
	    	log.info("****Welcome message received as***"+message);
	    	
	    	//verifying the welcome content
	    	Assert.assertEquals(message, "Welcome to the Secure Area. When you are done click logout below.");
	    	
	    	//retrieve the logout web element
	    	Boolean flag= driver.findElement(LOGOUTBTN).isDisplayed();
	    	
	    	//verifying the logout button is displayed
	    	Assert.assertTrue(flag);
	    }
	    

@And("User enters invalid {string} and {string}")
public void user_enters_invalid_username_and_password(String username, String password) {
	
	try {
		wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
		driver.findElement(USERNAME).sendKeys(username);
		
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}

	    driver.findElement(PASSWORD).sendKeys(password);
	    
	    log.info("****Invalid input passed to the login page ******");
	    driver.findElement(LOGINBTN).click();
	    
	    log.info("***Login button is clicked*****");
    
}
@Then("User Logged into the Error Page")
public void user_logged_into_the_error_page() {
	
	//retrieve the error message
	String invalidmessage = driver.findElement(ERRORTXT).getText();
	boolean expected=invalidmessage.contains("Your username is invalid!");
	
	//verifying the error message
	if(expected)
	{
		System.out.println("Error Message matches");
	}
	else
	{
		System.out.println("Not matches");
	}
    log.info("*****Error message validated as*****"+invalidmessage);
}

@And("Browser is closed")
public void browser_is_closed() {
	driver.quit();
	log.info("****the web browser  is closed*****");
}

	    
} 
