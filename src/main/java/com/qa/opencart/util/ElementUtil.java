package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil 
	{
	
		private WebDriver driver;
		
		private Actions act;
		
		JavaScriptUtil jsUtil;
		
		public ElementUtil(WebDriver driver)
		{
			this.driver=driver;
			act=new Actions(driver);
			jsUtil=new JavaScriptUtil(driver);
		}
		
		public void conditionForFlashingElement(WebElement element)
		{
			if(Boolean.parseBoolean(DriverFactory.isHighLight))
			{	
				jsUtil.flashElements(element);
			}
		}
		
		
		public WebElement getElement(By locator)			//1
		{
			
			WebElement element= driver.findElement(locator);
			conditionForFlashingElement(element);
			return element;
		}
		
		
		public void dosendKeys(By locator,String value)   	//2
		{
			getElement(locator).sendKeys(value);
		}
		
		
		public void waitAndDoSendKeys(long waitingTime,By locator,String value)
		{
			explicitWaitForVisiblityOfElement(waitingTime,locator).sendKeys(value);
		}
		
		public void waitAndClearOldText(By locator,long waitingTime)
		{
			explicitWaitForVisiblityOfElement(waitingTime,locator).clear();
		}
		
		public void doClick(By locator)						//3
		{
			getElement(locator).click();
		}
		
		public void doClick(WebElement element)
		{
			element.click();
		}
		
		public boolean isitDisplayed(By locator)			//4
		{
			try 
			{
				return getElement(locator).isDisplayed();
				
			}
			catch(NoSuchElementException e)
			{
				System.out.println("Element not found");
				return false;
			}
		}
		
		 public String printText(By locator)				//5
			{
//		    	String text= getElements(locator).getText();
//		    	System.out.println(text);
				String text=getElement(locator).getText();
				
				if(text!=null)
			{
					return text;
				}
				else
				{
					System.out.println("Element is null"+text);
					return null;
				}
			}
		 
		 
		 public void doGetAttribute(By locator,String attribute)		//6
			{
				 String attributeValue=getElement(locator).getAttribute(attribute);
				 System.out.println(attributeValue);
			}
		 
		 
		 
		 public List<WebElement> getElements(By locator)		//7
			{
				return driver.findElements(locator);
			}
			
			public int giveListCount(By locator)				//8
			{
				return getElements(locator).size();
			}
			
			
			

			public List<String> fetchTextOfLinks(By locator)			//9
			{
				List<WebElement> listOfLinks=getElements(locator);
				List<String> webElementToStringList=new ArrayList<String>();
				for(int i=0;i<=giveListCount(locator)-1;i++)
				{
					String listElement= listOfLinks.get(i).getText();
					if(listElement.length()>0)
					{
							webElementToStringList.add(listElement);
					}
				}
		
				return webElementToStringList;
			}
			
			public void printElementList(By locator)					//10
			{
				List<String> printStringList=fetchTextOfLinks(locator);
				
				for(String e:printStringList)
				{
					System.out.println(e);
				}
			}
			
			//Below two functions are for checking whether element/elements are present in a web page or not
			//They are used as an efficient approach over isDisplayed() method as we know the limitation of isDiplayed() method in false....
			//......case scenerio where instead of giving false it throws an exception NoSuchElementException
			
			public int elementsCount(By locator)
			{	
				return getElements(locator).size();
			}
			
			public boolean isElementPresent(By locator,int expectedElementCount)
			{
				if(elementsCount(locator)==expectedElementCount)
				{
					return true;
				}
					return false;
			}
			
			
			
			
			
			
		//	-------------Select DropDown Utils-----------------------------------
			
//			private Select selectObjectCreation(By locator)
//			{
//				WebElement webElement=getElement(locator);
//				return new Select(webElement);
//			}
			
			
			public void selectByValueDropdown(By locator,String value)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				select.selectByValue(value);
//				Instead of above 3 lines we've created an utility method which creates an object of Select class and call that method here below 
//				Doing same in all the select related utilities because above starting two lines are common everywhere in select based utility method			
//				selectObjectCreation(locator).selectByValue(value);	
			}
			
			public void selectByIndexDropdown(By locator,int index)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				select.selectByIndex(index);	
//				selectObjectCreation(locator).selectByIndex(index);
			}
			
			public void selectByVisibleTextDropdown(By locator,String value)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				select.selectByVisibleText(value);	
			//	selectObjectCreation(locator).selectByVisibleText(value);
			}
			
			

			
			
			//getOptions() returns a List<WebELements>
			public int getDropdownOptionsCount(By locator)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				List<WebElement> countOptionsInDropdown=select.getOptions();
				return countOptionsInDropdown.size();
			}
			
			
			
			
			
			
			
			/*
			 * Drop down options printing and clicking to a specific option without select class
			 */
			
			
			public List<String> printDropdownOptions(By locator)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				List<WebElement> printOptionsInDropdown=select.getOptions();
				List<String> printOptionsOfDropdown=new ArrayList<String>();
				for(WebElement e:printOptionsInDropdown)
				{
					String text=e.getText();
					printOptionsOfDropdown.add(text);
				}
				return printOptionsOfDropdown;
			}
			
			
			
			
			
			
			
			private void iteratingAndSelectingTheDropdownOption(List<WebElement> listWebElement,String value)
			{
				for(WebElement e:listWebElement)
				{
					String text=e.getText();
					System.out.println(text);
					if(text.contains(value))
					{
						e.click();
						break;
					}
				}
			}
			
			
			
			
			
			
			/*Using Dropdown select using select class and its respective method,here getOptions()
			 */
			
			public void ClickOnDropdownUsingSelectClass(By locator,String value)
			{
				WebElement webElement=getElement(locator);
				Select select=new Select(webElement);
				List<WebElement> printOptionsInDropdown=select.getOptions();
//				for(WebElement e:printOptionsInDropdown)
//				{
//					String text=e.getText();
//					if(text.contains(value))
//					{
//						e.click();
//						break;
//					}
//				}
//				in place of above lines of code we commented, we have created an utility method for iterating and clicking on an option
				iteratingAndSelectingTheDropdownOption(printOptionsInDropdown,value);
			}
			
			
			
			

			/*Drop down options printing and clicking to a specific option without select class
			 */

			//Below method handles drop down without select class of selenium and its methods
			
			public void getDropdownOptionsWithoutSelectClass(By locator,String value)
			{
				List<WebElement> getOptionsDropdown=getElements(locator);
				System.out.println(getOptionsDropdown.size());
//				for(WebElement e:getOptionsDropdown)
//				{
//					String text=e.getText();
//					System.out.println(text);
//					if(text.contains(value))
//					{
//						e.click();
//						break;
//					}
//					
//				}
//				in place of above lines of code we have created an utility method for iterating and clicking on an option
				iteratingAndSelectingTheDropdownOption(getOptionsDropdown,value);
			}
			
			
//------------------------------------------------------------***ACTIONS CLASS UTILS***--------------------------------------------------
			
			
			
			/**
			 * These method handle the parent menu sub menu using Actions class
			 *Overloaded method,second one is for providing xpath by yourself as per the need in two level parent-sub menu handling
			*/
			public void parentChildMenuHandling(String HoverToParentMenu,String clickOnSubMenu) throws InterruptedException
			{
				//Actions act=new Actions(driver);            //created private Actions class object on top and fed it driver also in constructor.
				act.moveToElement(getElement(By.xpath("//div[text()='"+HoverToParentMenu+"']"))).perform();
				Thread.sleep(2000);
				getElement(By.xpath("//div[text()='"+clickOnSubMenu+"']")).click();		//can replace this by doClick(locator) but lets leave it for now
			}
			
			public void parentChildMenuHandling(By HoverToParentMenu,By clickOnSubMenu) throws InterruptedException
			{
				//Actions act=new Actions(driver);
				act.moveToElement(getElement(HoverToParentMenu)).perform();
				Thread.sleep(2000);
				(getElement(clickOnSubMenu)).click();					//can replace this by doClick(locator) but lets leave it for now
			}
			
			/**This method below handles 4 level of parent-sub menus,1-->click 2->hover 3->hover 4->click
			 * 
			 */
			public void shopByCategory(By levelOne,By levelTwo,By levelThree,By levelFour) throws InterruptedException
			{
				WebElement clickFirst=getElement(levelOne);
				clickFirst.click();										//can replace this by doClick(locator) but lets leave it for now
				
				Thread.sleep(1500);
				
				//Actions act=new Actions(driver);
				
				WebElement firstHover=getElement(levelTwo);
				act.moveToElement(firstHover).perform();
				
				Thread.sleep(1500);
				
				WebElement secondHover=getElement(levelThree);
				act.moveToElement(secondHover);
				
				Thread.sleep(1500);
				
				WebElement clickLast=getElement(levelFour);
				clickLast.click();					                   	//can replace this by doClick(locator) but lets leave it for now				
			}
			
			
			
			public void doActionsSendKeys(By locator,String value)
			{
				//Actions act=new Actions(driver);
				act.sendKeys(getElement(locator),value).perform();;
			}
			public void doActionsClick(By locator)
			{
				//Actions act=new Actions(driver);
				act.click(getElement(locator)).perform();
			}
			
			
			
			
			
			
			//-----------------------------------*****Wait Utilities****------------------------------------------------------
			
			public WebElement explicitWaitPresenceOfElement(long waitingTime,By locator)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				conditionForFlashingElement(element);
				return element;
			}
			
			public WebElement explicitWaitPresenceOfElement(long waitingTime,long pollingTime,By locator)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime),Duration.ofSeconds(pollingTime));
				return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			}
			
			
			public WebElement explicitWaitForVisiblityOfElement(long waitingTime,By locator)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				conditionForFlashingElement(element);
				return element;
				
			}
			

			public WebElement explicitWaitVisiblityOfElement(long waitingTime,long pollingTime,By locator)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime),Duration.ofSeconds(pollingTime));
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				
			}
			
			public WebElement visibilityOfElementUsingFluentFeatures(int timeout,int pollingTime,By locator)
			{
				Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				         						.withTimeout(Duration.ofSeconds(timeout))
				         						.pollingEvery(Duration.ofSeconds(pollingTime))
				         						.ignoring(NoSuchElementException.class)
				         						.ignoring(StaleElementReferenceException.class)
				         						.ignoring(TimeoutException.class)
				         						.withMessage("===Element Not Found   ===");
				return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			}
			
			//Will try to find atleast one element present out of list of weblement
			
			public List<WebElement> WaitForElementsPresence(By locator,long waitingTime)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			}
			
			
			//An expectation for checking that all elements present on the web page that match the locator visible. 
			
			public List<WebElement> WaitForElementsVisisbility(By locator,int waitingTime)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			}
			

			public void waitForElementandClick(int time,By locator)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(time));
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			}
			
			
			//Below Utility (i) TitleIS (ii)wrapper of titleIs and Seleniums getTitle() based upon the boolean value of (i) method 
			
			
			
			public boolean waitForTitleIs(String title,long waitingTime) 
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				
	
				
				try 
				{
					wait.until(ExpectedConditions.titleIs(title));
					//flag=true;
					return true;
				
				}
				catch(TimeoutException e)
				{
					System.out.println("----Wrong Title-----");
					return false;
				}
			}
			
			
			public void getTitleIs(String title,long waitingTime)
			{
				boolean flag=waitForTitleIs(title,waitingTime);
				if(flag==true)
				{
					System.out.println(driver.getTitle());
				}
				else 
				{
					System.out.println("Incorrect Title,Please check your title once again of webpage");
				}
			}
			
			//Below Utility (i) TitleContains -->fractional title(just a part of title need to be given here) (ii)wrapper of titleIs and Selenium's getTitle() based upon the boolean value of (i) method 
			
			
			public boolean waitForTitleContains(String fractionalTitle,long waitingTime) 
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				
				
				//boolean flag=false;
				
				try 
				{
					wait.until(ExpectedConditions.titleContains(fractionalTitle));
					//flag=true;
					return true;
				
				}
				catch(TimeoutException e)
				{
					System.out.println("----Wrong Title-----");
					return false;
				}
			}
			
			public  String getTitleContains(String title,long waitingTime)
			{
				boolean flag=waitForTitleContains(title,waitingTime);
				if(flag==true)
				{
					//System.out.println(driver.getTitle());
					return driver.getTitle();
				}
				else 
				{
					System.out.println("Incorrect Title,Please check your webpage title once again");
				}
				return "-1";
			}
			
			//Similarly like titleIs and TitleContains() we also have urlIs() ad UrlCOntains()
			public boolean waitForURLContains(String fractionalURL,long waitingTime) 
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(waitingTime));
				
	
				
				try 
				{
					wait.until(ExpectedConditions.urlContains(fractionalURL));
					//flag=true;
					return true;
				
				}
				catch(TimeoutException e)
				{
					System.out.println("----Wrong URL-----");
					return false;
				}
			}
			
			
			public String getURLContains(String fractionalURL,long waitingTime)
			{
				boolean flag=waitForURLContains(fractionalURL,waitingTime);
				if(flag==true)
				{
					//System.out.println(driver.getCurrentUrl());
					return driver.getCurrentUrl();
				}
				else 
				{
					System.out.println("Incorrect URL,Please check your URL once again in browser");
				}
				return "-1";
			}
			
			//------Handling Alert with wait,1st function below is used as wrapper for other 4 Alert functionalities
			

			public Alert waitForAlertAndSwitchUsingAlertIsPresent(long timeout)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.alertIsPresent());
			}
			
			public void getAlertTextUsingAlertIsPresent(long timeout)
			{
				Alert alert=waitForAlertAndSwitchUsingAlertIsPresent(timeout);
				String text=alert.getText();
				System.out.println(text);
			}
			
			public void acceptAlertUsingAlertIsPresent(long timeout)
			{
				Alert alert=waitForAlertAndSwitchUsingAlertIsPresent(timeout);
				alert.accept();
			}
			
			public void dismissAlertUsingAlertIsPresent(long timeout)
			{
				Alert alert=waitForAlertAndSwitchUsingAlertIsPresent(timeout);
				alert.dismiss();
			}
			
			public void promptSendKeysAlertUsingAlertIsPresent(long timeout,String promptTextValueSendKeys)
			{
				Alert alert=waitForAlertAndSwitchUsingAlertIsPresent(timeout);
				alert.sendKeys(promptTextValueSendKeys);
			}
			
			
			
			
			//************************************************************************88
			//Handling alert with FLuent class features
			
			public Alert waitForAlertUsingFluentFeatures(int timeout,int pollingTime)
			{
				Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				         						.withTimeout(Duration.ofSeconds(timeout))
				         						.pollingEvery(Duration.ofSeconds(pollingTime))
				         						.ignoring(NoSuchElementException.class)
				         						.ignoring(StaleElementReferenceException.class)
				         						.ignoring(TimeoutException.class)
				         						.withMessage("===JS Alert is not present===");
				return wait.until(ExpectedConditions.alertIsPresent());
			}
			
			
			
			
			//Handling frame with wait using--->1.name or id 2.locator 3.frame Index  4.FrameElement
			
			public WebDriver waitForFrameUsingNameOrID(String nameOrID,int timeout)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
			
			}
			

			public WebDriver waitForFrameUsingLocator(By locator,int timeout)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
			}
	

			public WebDriver waitForFrameUsingFrameIndex(int frmaeIndex,int timeout)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frmaeIndex));
			}
			
			public WebDriver waitForFrameUsingFrameElement(WebElement frameElement,int timeout)
			{
				WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
				return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
			}
			
	}

