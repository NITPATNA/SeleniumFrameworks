package tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import objectRepository.AccountPage;
import objectRepository.LandingPage;
import objectRepository.LoginPage;
import sources.Base;

public class Login extends Base {
	
	public WebDriver driver;
	LandingPage landinPage;
	LoginPage loginPage;
	AccountPage accountPage;
	Logger log;
	
	@BeforeMethod
	public void openApplication() throws Exception {
		driver= initializeDriver();
		String application_url=properties.getProperty("url");
		driver.get(application_url);
		log= LogManager.getLogger(Login.class.getName());
		log.debug("Application url opened");
		
	}
	
	@Test(dataProvider="getLoginCredentials")
	public void loginApplication(String username, String password, String loginStatus) throws InterruptedException{
		System.out.println("some random sop statement just for test purpose");
		
		landinPage= new LandingPage(driver);
		
		landinPage.myAccountOption().click();
		log.debug("MyAccount Option in the top link clicked");
		landinPage.loginLink().click();
		log.debug("Login link clicked");
		
		loginPage=new LoginPage(driver);
		Assert.assertTrue(loginPage.loginBreadcrumb().isDisplayed());
		log.debug("Navigated to Login Page suuccessfully");
		loginPage.emailInputFied().sendKeys(username);
		log.debug("Email Address entered in email address field");
		loginPage.passwordInputField().sendKeys(password);
		log.debug("Password entered in Password input field");
		loginPage.loginButton().click();
		log.debug("Login button clicked");
		
		accountPage= new AccountPage(driver);
		String actual_login_status=null;
		try {
			if(accountPage.accountBreadcrumb().isDisplayed()) {
				actual_login_status="Success";
				log.info("Login is successfull");}
		}catch(Exception e) {
			 actual_login_status="Fail";
			 log.error("Login failed");
			
		}
		
		Assert.assertEquals(actual_login_status, loginStatus);
		log.debug("Login functionality checked");
		
		
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
		log.debug("Browser Closed");
	}
	
	@DataProvider
	public Object[][] getLoginCredentials() {
		Object[][] loginCredentials= {
				{"kumari_Mamta186@gmail.com","pass1234","Success"},
				{"Test","test","Fail"},
				{"sumanprakash.i81@gmail.com","pass1234","Success"}
				};
		return loginCredentials;
	}
	
	
	

}
