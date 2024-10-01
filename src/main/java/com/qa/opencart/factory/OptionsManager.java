package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager 
{
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop)
	{
		prop=this.prop;
	}

	//1.Passing headless and incognito from config.properties by changing their values to true or false there in config.properties file
	//2.Passing headless and incognito from command prompt(mvn clean install -DENV="prod" -Dincognito="true" -Dheadless="true") by changing their values to.............
	//.........true or false there in config.properties file
	
	//Comment/uncomment for 1 and 2 switch for whether you want to send headless and incognito or either of the one using Command prompt or config.properties
	
	//1.....	
	
//	public ChromeOptions getChromeOptions()
//	{
//		co=new ChromeOptions();
//		if(Boolean.parseBoolean(prop.getProperty("headless")))
//		{
//			co.addArguments("--headless");
//		}
//		if(Boolean.parseBoolean(prop.getProperty("incognito")))
//		{
//			co.addArguments("--incognito");
//		}
//		return co;
//	}
//
//	public FirefoxOptions getFirefoxOptions()
//	{
//		fo=new FirefoxOptions();
//		if(Boolean.parseBoolean(prop.getProperty("headless")))
//		{
//			fo.addArguments("--headless");
//		}
//		if(Boolean.parseBoolean(prop.getProperty("incognito")))
//		{
//			fo.addArguments("--incognito");
//		}
//		return fo;
//	}
//	
//	public EdgeOptions getEdgeOptions()
//	{
//		eo=new EdgeOptions();
//		if(Boolean.parseBoolean(prop.getProperty("headless")))
//		{
//			eo.addArguments("--headless");
//		}
//		if(Boolean.parseBoolean(prop.getProperty("incognito")))
//		{
//			eo.addArguments("--inPrivate");
//		}
//		return eo;
//	}
	
	
	
	
	//2.
	
	public ChromeOptions getChromeOptions()
	{
		co=new ChromeOptions();
		if(Boolean.parseBoolean(System.getProperty("headless")))
		{
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(System.getProperty("incognito")))
		{
			co.addArguments("--incognito");
		}
		return co;
	}	
		
		public FirefoxOptions getFirefoxOptions()
		{
			fo=new FirefoxOptions();
			if(Boolean.parseBoolean(System.getProperty("headless")))
			{
				fo.addArguments("--headless");
			}
			if(Boolean.parseBoolean(System.getProperty("incognito")))
			{
				fo.addArguments("--incognito");
			}
			return fo;
		}
		
		public EdgeOptions getEdgeOptions()
		{
			eo=new EdgeOptions();
			if(Boolean.parseBoolean(System.getProperty("headless")))
			{
				eo.addArguments("--headless");
			}
			if(Boolean.parseBoolean(System.getProperty("incognito")))
			{
				eo.addArguments("--inPrivate");
			}
			return eo;
		}	
	
	
}

