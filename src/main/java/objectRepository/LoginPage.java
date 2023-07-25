package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//ul[@class='breadcrumb']//a[text()='Login']")
	private WebElement loginBreadcrumb;
	
	@FindBy(id="input-email")
	private WebElement emailInputField;
	
	@FindBy(id="input-password")
	private WebElement passwordInputField;
	
	@FindBy(css="input[value='Login']")
	private WebElement loginButton;
	
	public WebElement loginBreadcrumb() {
		return loginBreadcrumb;
	}
	
	public WebElement emailInputFied() {
		return emailInputField;
	}
	
	public WebElement passwordInputField() {
		return passwordInputField;
	}
	
	public WebElement loginButton() {
		return loginButton;
	}

}
