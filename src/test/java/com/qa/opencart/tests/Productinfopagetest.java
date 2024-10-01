package com.qa.opencart.tests;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.listeners.TestAllureListener;

@Listeners({AnnotationTransformer.class,ExtentReportListener.class,TestAllureListener.class})
public class Productinfopagetest extends BaseTest
{
	// Softassert softAssert=new SoftAssert);
	@BeforeClass
	public void productinfoPageInitialsetup() {
		accPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider(name = "getProductHeaderTestData")
	public Object[][] getProductHeaderTestData() {
		return new Object[][] { { "macbook", "MacBook Pro", "MacBook Pro" },
				{ "macbook", "MacBook Air", "MacBook Air" }, { "macbook", "MacBook", "MacBook" },
				{ "samsung", "Samsung SyncMaster 941BW", "Samsung SyncMaster 941BW" },
				{ "samsung", "Samsung Galaxy Tab 10.1", "Samsung Galaxy Tab 10.1" }, { "imac", "iMac", "iMac" } };
	}

	@Test(dataProvider = "getProductHeaderTestData")
	public void productinfoPageHeaderTestString(String searchProduct, String selectProductFromSearch,
			String expectedSelectedProductHeader) {
		searchResults=accPage.SearchFromAccountPage(searchProduct);
		productInfo = searchResults.selectProductFromList(selectProductFromSearch);
		String actualHeaderOfProductInfoPage = productInfo.getHeaderOfProductInfoPage();
		Assert.assertEquals(actualHeaderOfProductInfoPage, expectedSelectedProductHeader);
	}

	@DataProvider(name="getProductDetailsTestData") 
	public Object[][] getProductDetailsTestData() 	
	{		
  			return new Object[][]
			{
  				{"macbook", "MacBook Pro", "Brand", "Apple", "Product Code", "Product 18", "Reward Points", "800", "Availability", "In Stock", "priceProduct", "$2,000.00"
  					,"ExTaxPriceOfProduct" ,"$2,000.00"},
				{"samsung", "Samsung SyncMaster 941BW", null, null, "Product Code", "Product 6", null, null, "Availability", "2-3 Days", "priceProduct", "$242.00",
					"ExTaxPriceOfProduct" ,"$200.00"} 
			};
	}			
				
	@Test(dataProvider="getProductDetailsTestData") 
	public void productDetailsTest(String searchProduct,String selectProductFromSearch, String brand,String expectedBrandName, String productCode,String expectedProductCode, String rewardPoints,
									String expectedRewardPoints, String availability,String expectedAvailability, String priceProduct,String expectedPriceOfProduct,
									String extaxPriceOfProduct,String expectedExtaxPriceOfProduct)
	{
				SoftAssert softAssert=new SoftAssert();       //if you will place this line outside this method then this method shows an abnormal behaviour.......
				//...of when from dataprovider if you provide correct for both line 40 and 43 it will give right output but(once i'll check then i'll write here)
				searchResults=accPage.SearchFromAccountPage(searchProduct);
				productInfo=searchResults.selectProductFromList(selectProductFromSearch);
				Map<String,String>productMap=productInfo.productDetailsAndPrice();
				softAssert.assertEquals(productMap.get(brand),expectedBrandName);
				softAssert.assertEquals(productMap.get(productCode),expectedProductCode);
				softAssert.assertEquals(productMap.get(rewardPoints),expectedRewardPoints);
				softAssert.assertEquals(productMap.get(availability),expectedAvailability);
				softAssert.assertEquals(productMap.get(priceProduct),expectedPriceOfProduct);
				softAssert.assertEquals (productMap.get(extaxPriceOfProduct),expectedExtaxPriceOfProduct); 
				softAssert.assertAll();
	}

}