package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory
{
	/**
	 * @author Shivam
	 */
	WebDriver driver;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	
	public static String isHighLight;
	
	OptionsManager optionsManager;
	/**
	 * This method is used to initialize the driver on the basis of given browser name
	 * @param Properties prop because our url and browser comes from config.properties to avoid hardcoding
	 * @return	it returns WebDriver driver
	 */
	
	public WebDriver initDriver(Properties prop)
	{
		optionsManager=new OptionsManager(prop);
		
		isHighLight=prop.getProperty("highlight");
		
		String browserName=prop.getProperty("browser");
		System.out.println("Browser name is :"+browserName);
		switch(browserName.trim().toLowerCase())
		{
			case "chrome":
				
				if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
					initRemoteDriver("chrome");
				}
				//driver=new ChromeDriver(optionsManager.getChromeOptions());
				
				else 
				{
					tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
					
				}
				break;
				
			case "firefox":
				
				if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
					initRemoteDriver("firefox");
				}
				
				//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
				
				else
				{
					tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
				
				}
				break;
			case "edge":
				if(Boolean.parseBoolean(prop.getProperty("remote")))
				{
					initRemoteDriver("edge");
				}
				//driver=new EdgeDriver(optionsManager.getEdgeOptions());
				else 
				{
					tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
				
				}
				break;
			default:
				System.out.println(AppError.BROWSER_ERROR_MESSAGE+browserName);
				throw new BrowserException(AppError.BROWSER_EXCEPTION_MESSAGE);
		}
		
		
		//driver.manage().window().maximize();
		getThreadLocalDriver().manage().window().maximize();
		
		//driver.manage().deleteAllCookies();
		getThreadLocalDriver().manage().deleteAllCookies();
		
		//driver.get(prop.getProperty("url"));
		getThreadLocalDriver().get(prop.getProperty("url"));
		
		
		return getThreadLocalDriver();
		
	}
	
	private void initRemoteDriver(String browserName) 
	{
		System.out.println("Running test cases in remote isolated containers acting as individual machines");
		try
		{
			switch(browserName.toLowerCase().trim())
			{
				case "chrome":
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
					break;
		
				case "firefox":
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
					break;
		
				case "edge":
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getEdgeOptions()));
					break;
		
				default:
					System.out.println("please pass the correct browser :"+browserName);
					break;
			}
		}	
		catch (MalformedURLException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}

	public static WebDriver getThreadLocalDriver()
	{
		return tlDriver.get();
	}
	
	Properties prop;
	
	public Properties initConfigProp()
	{
		prop=new Properties();										//Empty Properties object created
		String envName=System.getProperty("ENV");
		FileInputStream fis=null;

		try 
		{
			System.out.println("Running Tests in--> "+envName+" <--environment");
			if(envName==null)
			{
				System.out.println("No Environment passed,therefore running the test cases in QA environment");
				fis=new FileInputStream("./src/test/resource/config/qa.config.properties");
			}
		
			else 
			{
				switch(envName.trim().toLowerCase())
				{
					case "qa":
					fis=new FileInputStream("./src/test/resource/config/qa.config.properties");
					break;
					
					case "dev":
					fis = new FileInputStream("./src/test/resource/config/dev.config.properties");
					break;
					
					case "stage":
					fis = new FileInputStream("./src/test/resource/config/stage.config.properties");
					break;
					
					case "uat":
					fis = new FileInputStream("./src/test/resource/config/uat.config.properties");
					break;
					
					case "prod":
					fis = new FileInputStream("./src/test/resource/config/config.properties");

					break;
					
					default:
						System.out.println("Passed invalid environment,please pass the correct environment,you entered: "+envName);
						throw new FrameworkException("....Wrong environment passed,looking for a valid environment to pass....");
						
				}
				
			}
			
			prop.load(fis);
		}
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			catch (IOException e) {
				
				e.printStackTrace();
			} 
		
		
		return prop;
		
//		try 
//		{	
//			//FileInputStream establishes connection with config.properties and creating an object of FileInputStream will import all the key-value pair in its bubble
//			FileInputStream connectandLoadConfigProp=new FileInputStream("./src/test/resource/config/config.properties"); 
//			
//			//.load() method loads the file from fileinputstream object to the properties object
//			prop.load(connectandLoadConfigProp);
//			
//			
//		} 
//		
//		catch (FileNotFoundException e) 
//		{
//			e.printStackTrace();
//		} 
//		catch (IOException e) {
//	
//			e.printStackTrace();
//		}
//		return prop;
		
	}

	public static String getScreenshot(String methodName) 
	{	
		
			File srcFile=((TakesScreenshot)getThreadLocalDriver()).getScreenshotAs(OutputType.FILE);
			
			String path=System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
			
			File destination=new File(path);
		
		try {
			
			FileHandler.copy(srcFile, destination);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;						

	}
}


