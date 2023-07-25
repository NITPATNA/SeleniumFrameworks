package sources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public WebDriver driver;
	private String browser_name;
	public Properties properties;
	
	public WebDriver initializeDriver() throws Exception {
		String properties_file_path= System.getProperty("user.dir")+"\\src\\main\\java\\sources\\data.properties"; 
		FileInputStream fis= new FileInputStream(properties_file_path);
		properties= new Properties();
		properties.load(fis);
		browser_name=properties.getProperty("browser_name");
		if(browser_name.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browser_name.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}else if(browser_name.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();;
			driver= new EdgeDriver();
		}else if(browser_name.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver();
		}else if(browser_name.equalsIgnoreCase("Remote")) {
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setBrowserName("Chrome");
			driver= new RemoteWebDriver(dc);
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public void takeScreenShot(String testname,WebDriver driver) throws IOException {
		String pattern="dd-mm-yyyy hh-mm-ss";
		SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
		String date=dateFormat.format(new Date());
		File screenshot_file=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destination_Path= System.getProperty("user.dir")+"\\screenshots\\"+testname+"-"+date+".png";
		FileUtils.copyFile(screenshot_file, new File(destination_Path));
	}


}
