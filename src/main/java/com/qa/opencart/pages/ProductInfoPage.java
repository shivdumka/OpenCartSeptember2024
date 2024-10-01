package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage 

{
	WebDriver driver;
	ElementUtil elementUtil;
	
	Map<String,String> productMap;
	
		public ProductInfoPage(WebDriver driver)
		{
			this.driver=driver;
			elementUtil=new ElementUtil(driver);
		}
		
		private By productInfoPageHeader=By.xpath("//div[@id='content']//h1");
		private By productDetails=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
		private By productPrice=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
		
		public String getHeaderOfProductInfoPage()
		{
			String header=elementUtil.explicitWaitForVisiblityOfElement(AppConstants.DEFAULT_MEDIUM_TIME_OUT, productInfoPageHeader).getText();
			System.out.println("Product header name is:"+header);
			return header;
		}
		
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		public void getProductCompleteData()
		{
			List<WebElement> productData=elementUtil.WaitForElementsVisisbility(productDetails, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
			for(WebElement e:productData)
			{
				String metaText=e.getText();
				String metaData[]=metaText.split(":");
				String metaKey=metaData[0].trim();
				String metaValue=metaData[1].trim();
				productMap.put(metaKey, metaValue);
			}
			
		}
		
		
//		Here we will create our own Key of the map
//		self made key priceProduct=$2,000.00(index 0)
//		self made key exTaxPrice: $2,000.00(index 1)
		
		public void getProductPricingDetails()
		{
			List<WebElement> priceData=elementUtil.WaitForElementsVisisbility(productPrice, AppConstants.DEFAULT_MEDIUM_TIME_OUT);
			String priceOfProduct=priceData.get(0).getText().trim();
			String exTaxPriceOfProduct=priceData.get(1).getText().split(":")[1].trim();
			productMap.put("priceProduct", priceOfProduct);
			productMap.put("ExTaxPriceOfProduct", exTaxPriceOfProduct);
		}
		
		
		public Map<String,String> productDetailsAndPrice()
		{
			productMap=new HashMap<String,String>();
			getProductCompleteData();
			getProductPricingDetails();
			System.out.println("Complete product details are listed here,take a look: "+productMap);
			return productMap;
		}
		

}

