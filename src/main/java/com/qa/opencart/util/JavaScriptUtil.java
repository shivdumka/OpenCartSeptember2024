package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private JavascriptExecutor js;
	
	private WebDriver driver;
	
	public JavaScriptUtil(WebDriver driver)
	{
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	
	public String getTitle()
	{
		return js.executeScript("return document.title;").toString();
	}
	public String getURL()
	{
		return js.executeScript("return document.URL;").toString();
	}
	
	public void generateAlertPopUP(String alertMsg)
	{
		js.executeScript("alert('"+alertMsg+"');");
	}
	
	public String getWholeWebPageText()
	{
		return js.executeScript("return document.documentElement.innerText").toString();
	}
	
	public void goBackwithJS()
	{
		js.executeScript("history.go(-1)");
	}
	
	public void refreshWebPagewithJS()
	{
		js.executeScript("history.go(0)");
	}
	
	public void goForwardwithJS()
	{
		js.executeScript("history.go(+1)");
	}
	
	public void zoomWithJS(int zoomPercentage)
	{
		js.executeScript("document.body.style.zoom='"+zoomPercentage+"%'");
	}
	
	public void zoomWithJSMozillaFirefox(int zoomingPointValue)
	{
		js.executeScript("document.body.style.zoom='scale("+zoomingPointValue+")'");
	}

	
	public void scrollDownToPageCustom(int heightToBeScrolledInPixels)
	{
		js.executeScript("window.scrollTo(0,'"+heightToBeScrolledInPixels+"');");
	}
	
	public void scrollDownToMiddleOfPage()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
	}
	
	public void scrollDownToEndOfPage()
	{
		js.executeScript("window.scrollTo(0,document.body.scollHeight)");
	}
	
	public void scrollUpToBeginningOfPage()
	{
		js.executeScript("window.scrollTo(document.body.scollHeight,0)");
	}
	
	public void scrollToElement(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	public void drawBorderAroundElement(WebElement element)
	{
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}
	
	public void flashElements(WebElement element)
	{
		String defaultColorOfTextField=element.getCssValue("backgroundValue");
		for(int i=0;i<=5;i++)
		{
			changeColor("rgb(0,300,0)",element);
			changeColor(defaultColorOfTextField,element);
		}
	}
	
	public void changeColor(String color,WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'",element);
		try
		{
			Thread.sleep(20);
		}
		catch(InterruptedException e)
		{
			System.out.println("Exception thrown");
			e.printStackTrace();
		}
	}
	
}
