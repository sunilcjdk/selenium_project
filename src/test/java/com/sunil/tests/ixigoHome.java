package com.sunil.tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.sunil.generic.GenericClass;
import com.sunil.pages.*;

public class ixigoHome extends GenericClass {

	ixigoHomePage userPage=null;
	SearchResultPage SearchResultPage=null;
	@BeforeMethod()
	public void setUp()
	{	
		userPage = new ixigoHomePage();
		SearchResultPage=new SearchResultPage();
	}
	
	@Test(priority=1)
	public void PageTitleTest() throws FileNotFoundException, IOException
	{
		extenttest=extentreport.createTest("Page Title Test");
		String title = userPage.validateLoginPageTitle();
		Assert.assertEquals(title, readProperty("title"));
	}
	
	@Test(priority=2)
	public void SearchFlightandVerify() throws FileNotFoundException, IOException
	{
		extenttest=extentreport.createTest("Search Flight and Verify");
		userPage.searchForFlight();
		Assert.assertTrue(SearchResultPage.VerifyNonStopChkDisaplyed());
		Assert.assertEquals(SearchResultPage.GetSearchPageTitle(), readProperty("SearchPageTitle"));
		Assert.assertEquals(SearchResultPage.VerifyNonStopFilterCheckBox(), "Non stop");
		Assert.assertEquals(SearchResultPage.VerifyOneStopFilterCheckBoxes(), "1 stop");
		Assert.assertEquals(SearchResultPage.VerifyOneStopPlusFilterCheckBoxes(), "1+ stops");
		
		Assert.assertEquals(SearchResultPage.VerifyEarlyMorningTime(), readProperty("DepartureEarlyMorning"));
		Assert.assertEquals(SearchResultPage.VerifyMorningTime(),readProperty("DepartureMorning"));
		Assert.assertEquals(SearchResultPage.VerifyMidDayTime(),readProperty("DepartureMidDay"));
		Assert.assertEquals(SearchResultPage.VerifyNightTime(), readProperty("DepartureNight"));
		
		Assert.assertTrue(SearchResultPage.Airline1FilterPresent());
		Assert.assertTrue(SearchResultPage.Airline2FilterPresent());
		Assert.assertTrue(SearchResultPage.Airline3FilterPresent());
		Assert.assertTrue(SearchResultPage.Airline4FilterPresent());		
		
	}
	
	@Test(priority=3)
	public void PrintAirlineDetails() throws FileNotFoundException, IOException
	{
		extenttest=extentreport.createTest("Print Airline Details");
		int maxfare=7000;
		int count=0;
		List<String> airDetails=SearchResultPage.printAirlineDetails(maxfare);
		for(String details:airDetails)
		{
			count++;
			System.out.println(details);
		}
		
		System.out.println("total flights:"+count);
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			extenttest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			extenttest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
			extenttest.skip(result.getThrowable());
			
		}
		
		else if(result.getStatus()==ITestResult.FAILURE)
		{
			extenttest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
			extenttest.fail(result.getThrowable());
			String imagePath=SaveScreenShot(result.getName());
			extenttest.addScreenCaptureFromPath(imagePath);
		}
	}

}
