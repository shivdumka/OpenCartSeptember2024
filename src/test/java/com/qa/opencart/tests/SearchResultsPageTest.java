package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class SearchResultsPageTest extends BaseTest
{
	
	
	@BeforeClass
	public void searchResultsPageInitSetUp() 
	{
		accPage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		//searchResults=accPage.SearchFromAccountPage("macbook");
	}
	
	
	
	
	@Test
	public void serachPageProductHeaderTest()
	{
		searchResults=accPage.SearchFromAccountPage("macbook");
		String actualHeader=searchResults.serachPageProductHeader();
		Assert.assertEquals(actualHeader,AppConstants.SEARCH_MACBOOK_RESULTS_HEADER);
		

	}
	

	@Test
	public void productSearchListCountTest()
	{
		searchResults=accPage.SearchFromAccountPage("macbook");
		int actualCount=searchResults.getProductSearchListCount();
		Assert.assertEquals(actualCount, AppConstants.SEARCH_MACBOOK_RESULTS_COUNT);
	}
	
	
	@Test
	public void selectProductFromListTest()
	{
		searchResults=accPage.SearchFromAccountPage("macbook");
		productInfo=searchResults.selectProductFromList("MacBook Pro");
		String actualHeaderOfProductPage=productInfo.getHeaderOfProductInfoPage();
		Assert.assertEquals(actualHeaderOfProductPage,AppConstants.PRODUCT_PAGE_MACBOOK_PRO);
	}
	
	

}
