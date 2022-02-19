package com.sunil.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sunil.generic.GenericClass;



public class SearchResultPage extends GenericClass {
	
	
	
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Stops']/..//div[@data-checkboxindex='0']/span/div")
	WebElement NonStopChk;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Stops']/..//div[@data-checkboxindex='1']/span/div")
	WebElement oneStopChk;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Stops']/..//div[@data-checkboxindex='2']/span/div")
	WebElement OnePlusStopChk;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Airlines']/..//div[@data-checkboxindex='0']/span/div/div[1]")
	WebElement Airline1Filter;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Airlines']/..//div[@data-checkboxindex='1']/span/div/div[1]")
	WebElement Airline2Filter;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Airlines']/..//div[@data-checkboxindex='2']/span/div/div[1]")
	WebElement Airline3Filter;
	
	@FindBy(xpath="//div[@class='fltr-hdr' and text()='Airlines']/..//div[@data-checkboxindex='3']/span/div/div[1]")
	WebElement Airline4Filter;
	
	@FindBy(xpath="//div[text()='Departure from DEL']/..//div[text()='Early Morning']/../button")
	WebElement DepartureEarlyMorning;
	
	@FindBy(xpath="//div[text()='Departure from DEL']/..//div[text()='Morning']/../button")
	WebElement DepartureMorning;
	
	@FindBy(xpath="//div[text()='Departure from DEL']/..//div[text()='Mid Day']/../button")
	WebElement DepartureMidDay;
	
	@FindBy(xpath="//div[text()='Departure from DEL']/..//div[text()='Night']/../button")
	WebElement DepartureNight;
	
	
	@FindBy(xpath="//div[@class='result-wrpr']//div[contains(@class,'c-flight-listing-split-row')]")
	 List<WebElement> AirlineDeatils;
	
	@FindBy(xpath="//div[@class='result-wrpr']//div[contains(@class,'c-flight-listing-split-row')]//div[@class='airline-text']/div")
	 List<WebElement> AirlineNumber;
	
	@FindBy(xpath="//div[@class='result-wrpr']//div[contains(@class,'c-flight-listing-split-row')]//div[@class='time-group']/div[1]")
	 List<WebElement> DepartureTime;
	
	@FindBy(xpath="//div[@class='result-wrpr']//div[contains(@class,'c-flight-listing-split-row')]//div[@class='price']//span[2]")
	 List<WebElement> AirlineFare;
	
	
	public SearchResultPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String GetSearchPageTitle()
	{
		return driver.getTitle();
	}
	public boolean VerifyNonStopChkDisaplyed()
	{
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(NonStopChk));
		return NonStopChk.isDisplayed();
	}
	
	public String  VerifyNonStopFilterCheckBox()
	{
		return NonStopChk.getText();
	}
	
	public String  VerifyOneStopFilterCheckBoxes()
	{
		return oneStopChk.getText();
	}
	
	public String  VerifyOneStopPlusFilterCheckBoxes()
	{
		return OnePlusStopChk.getText();
	}
	public void selectNonStopCheckBox()
	{
		if(!NonStopChk.isSelected())
		{
		NonStopChk.click();
		}
	}
	
	public String  VerifyMorningTime()
	{
		return DepartureMorning.getText();
	}
	
	public String  VerifyMidDayTime()
	{
		return DepartureMidDay.getText();
	}
	
	public String  VerifyNightTime()
	{
		return DepartureNight.getText();
	}
	
	public String  VerifyEarlyMorningTime()
	{
		return DepartureEarlyMorning.getText();
	}
	
	public boolean  Airline1FilterPresent()
	{
		return Airline1Filter.isDisplayed();
	}
	
	public boolean  Airline2FilterPresent()
	{
		return Airline2Filter.isDisplayed();
	}
	
	public boolean  Airline3FilterPresent()
	{
		return Airline3Filter.isDisplayed();
	}
	
	public boolean  Airline4FilterPresent()
	{
		return Airline4Filter.isDisplayed();
	}
	
	
	public List<String> printAirlineDetails(int maximumfare)
	{
		List<String> list=new ArrayList();
		
		selectNonStopCheckBox();
		for(int i=0;i<AirlineDeatils.size();i++)
		{
			String StringFare=AirlineFare.get(i).getText();
			int fare=Integer.parseInt(StringFare);
			if(fare<maximumfare)
			{
				StringBuilder str=new StringBuilder();
				
				str.append("AirLine Number:"+AirlineNumber.get(i).getText()+" ");
				str.append("Airline departure:"+DepartureTime.get(i).getText()+" ");
				str.append("fare:"+fare);
				list.add(str.toString());
			}
		}
	
	return list;
}
}
