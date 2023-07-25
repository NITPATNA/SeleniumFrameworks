package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id='top']//span[text()='My Account']")
	private WebElement myAccountOption;
	
	@FindBy(linkText="Login")
	private WebElement loginLink;
	
	@FindBy(linkText="Register")
	private WebElement registerLink;
	
	public WebElement myAccountOption() {
		return myAccountOption;
	}
	
	public WebElement loginLink() {
		return loginLink;
	}
	
	public WebElement registerLink() {
		return registerLink;
	}

}
