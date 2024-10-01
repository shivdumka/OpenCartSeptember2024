package com.qa.opencart.tests;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;


public class AccountsPageTest extends BaseTest
{
		@BeforeClass
		public void accountPageInitialSetup()
		{
			accPage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		}
		
		@Test
		public void accountPageTitleTest()
		{
			String actualTitle=accPage.accountPageTitle();
			Assert.assertEquals(actualTitle,AppConstants.LOGIN_PAGE_ACCOUNTS_PAGE_TITLE);
		}
		
		@Test
		public void accountPageLogoutLinkDisplayTest()
		{
			boolean logoutLinkDisplayed=accPage.accountPageLogoutLinkDisplay();
			Assert.assertTrue(logoutLinkDisplayed);
		}
		
		@Test
		public void accountPageHeadersCountTest()
		{
			int actualHeaderCount=accPage.accountPageHeadersCount();
			Assert.assertEquals(actualHeaderCount,AppConstants.ACCOUNTS_PAGE_TOTAL_HEADERS_COUNT);

		}
		
		@Test
		public void accountPageHeadersListTest()
		{
			List<String> actualHeadersList=accPage.accountPageHeadersList();
			Assert.assertEquals(actualHeadersList,AppConstants.ACOOUNTS_PAGE_EXPECTED_HEADERS_LIST);

		}

//		@Test
//		public void serachPageProductHeaderTest()
//		{
//			searchResults=accPage.SearchFromAccountPage("macbook");
//			String actualHeader=searchResults.serachPageProductHeader();
//			Assert.assertEquals(actualHeader,AppConstants.SEARCH_MACBOOK_RESULTS_HEADER);
//			
//
//		}
//		
//
//		@Test
//		public void productSearchListCountTest()
//		{
//			searchResults=accPage.SearchFromAccountPage("macbook");
//			int actualCount=searchResults.getProductSearchListCount();
//			Assert.assertEquals(actualCount, AppConstants.SEARCH_MACBOOK_RESULTS_COUNT);
//		}

	//	@Test
//		public void selectProductFromListTest()
//		{
//			String actualHeaderOfProductPage=productInfo.getHeaderOfProductInfoPage();
//			Assert.assertEquals(actualHeaderOfProductPage,AppConstants.PRODUCT_PAGE_MACBOOK_PRO);
//			
//		}
}
