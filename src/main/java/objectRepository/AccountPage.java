package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	
	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='breadcrumb']/li[last()]//a[text()='Account']")
	private WebElement accountBreadcrumb;
	
	public WebElement accountBreadcrumb() {
		return accountBreadcrumb;
	}
	
	

}
