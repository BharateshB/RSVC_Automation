package com.tyss.demo.baseutil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDriver {
	
	private static CreateDriver instance = null;
	
	private ThreadLocal <WebDriver> webDriver =
			 new ThreadLocal<WebDriver>();
	

	/*constructor*/
	 private CreateDriver() {
	 }
	 
	 /**
	  * getInstance method to retrieve active driver instance
	  *
	  * @return CreateDriver
	  */
	  public static CreateDriver getInstance() {
	  if ( instance == null ) {
	  instance = new CreateDriver();
	  }
	  return instance;
	  }
	  
	  @SuppressWarnings("deprecation")
	public  WebDriver setdriver(String browser)
	  {
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			return  new ChromeDriver();
		}
		else if(browser=="Firefox")
		{
			WebDriverManager.firefoxdriver().setup();
			return  new FirefoxDriver();
		}
		else
		{
		
		}
		return null;
     }
	  
	  
		/**
		 * Description :Sets the web driver according to the 
		 * 
		 * @author Manikandan A
		 * @param browser
		 * @param capabilities
		 * @param LOCAL_HUB_URL
		 * @param chromeOptions
		 * @param firefoxOptions
		 * 
		 */
	  public WebDriver setWebDriver(String browser , DesiredCapabilities capabilities,String LOCAL_HUB_URL, ChromeOptions chromeOptions,FirefoxOptions firefoxOptions ) throws IOException {

		    switch (browser) {
		        case "Ie":
		            capabilities = new DesiredCapabilities().internetExplorer();
		            break;
		        case "Firefox":
		        	firefoxOptions=new  FirefoxOptions();
		        	firefoxOptions.setHeadless(true);
		        	ProfilesIni profile = new ProfilesIni();
		        	FirefoxProfile testprofile = profile.getProfile("ShreyaU");
		        	testprofile.setPreference("dom.webnotifications.enabled", false);
		        	testprofile.setPreference("dom.push.enabled", false);
		            capabilities = new DesiredCapabilities();
		            capabilities.setBrowserName(BrowserType.FIREFOX);
		            capabilities.setCapability(FirefoxDriver.PROFILE, testprofile);
		            capabilities.setCapability(firefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
		            firefoxOptions.merge(capabilities);
		            break;
		        case "Chrome":
		        	chromeOptions = new ChromeOptions(); 
		        	//chromeOptions.addArguments("--headless");
		        	chromeOptions.addArguments("--disable-popup-blocking");
		        	chromeOptions.addArguments("--disable-infobars");
		        	chromeOptions.addArguments("--disable-notifications");
		            capabilities = new DesiredCapabilities();
		            capabilities.setBrowserName(BrowserType.CHROME);
		        	capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
		        	chromeOptions.merge(capabilities);
		            break;
		        case "Edge":
		        	EdgeOptions edgeoptions=new EdgeOptions();
		            capabilities = new DesiredCapabilities();
		            capabilities.setBrowserName(BrowserType.EDGE);
		            capabilities.setPlatform(Platform.WINDOWS);
		            break;
		        case "Safari":
		            capabilities = new DesiredCapabilities().safari();
		            break;
		        default:
		            capabilities = null;
		    }

		    try { 
		    	return new RemoteWebDriver(new URL(LOCAL_HUB_URL),capabilities);
			
			 } catch
			  (MalformedURLException e) {
			  
			 BaseTest.logger.info("The given HUB URL is Malformed"); 
			
				 }
			return null;
	  }
	  
	  /**
		 * Description :Sets the driver instance
		 *
		 * @author Manikandan A
		 * @param browser
		 *
		 */
		public WebDriver launchBrowser(String browser) {
			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				return new ChromeDriver();
			} else if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				return new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("Edge")) {
				System.setProperty("webdriver.edge.driver", "./drivers/EdgeDriver.exe");
				return new EdgeDriver();
			} else {
				return null;
			}

		}

}