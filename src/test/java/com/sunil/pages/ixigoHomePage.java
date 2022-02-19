package com.sunil.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sunil.generic.GenericClass;



public class ixigoHomePage extends GenericClass {
	
	
	@FindBy(xpath="//img[@class='ixigo-logo']")
	WebElement ixigoLogo;
	
	@FindBy(xpath="//div[text()='From']/following-sibling::input")
	WebElement FromCity;
	
	@FindBy(xpath="//div[text()='From']/../..//div[text()='DEL - New Delhi, India']") 
	WebElement SelectFromDelhi;
	
	@FindBy(xpath="//div[text()='To']/following-sibling::input")
	WebElement ToCity;
	
	@FindBy(xpath="//div[text()='To']/../..//div[text()='BLR - Bengaluru, India']") 
	WebElement SelectToBangalore;
	
	@FindBy(xpath="//div[text()='Departure']/following-sibling::input")
	WebElement Departure;
	
	@FindBy(xpath="//div[contains(@style,'inline-block')]//td[@data-date='27042021']")
	WebElement FromDate;
	
	@FindBy(xpath="//div[contains(@style,'inline-block')]//button[@class='ixi-icon-arrow rd-next']")
	WebElement ClickArrow;	
	
	@FindBy(xpath="//div[text()='Return']/following-sibling::input")
	WebElement Return;
	
	@FindBy(xpath="//div[contains(@style,'inline-block')]//td[@data-date='24062021']")
	WebElement ReturnDate;
	
	@FindBy(xpath="//div[text()='Travellers | Class']/following-sibling::input")
	WebElement traveller;
	
	@FindBy(xpath="//div[text()='Adult']/../following-sibling::div/span[text()='2']")
	WebElement select2Adults;
	
	@FindBy(xpath="//button[text()='Search']")
	WebElement Search;
	
			
	public ixigoHomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String validateLoginPageTitle()
	{
		return driver.getTitle();
	}
	
	public void selectFromCity()
	{
		FromCity.click();
		SelectFromDelhi.click();
	}
	
	public void selectToCity()
	{
		ToCity.click();
		SelectToBangalore.click();
	}
	
	public void selectfromDate()
	{
		Departure.click();
		FromDate.click();
	}
	
	public void selectReturnDate()
	{
		Return.click();
		ClickArrow.click();
		ReturnDate.click();
	}
	
	public void selectTravellers()
	{
		traveller.click();
		select2Adults.click();
	}
	
	public void searchForFlight()
	{
		selectFromCity();
		selectToCity();
		selectfromDate();
		selectReturnDate();
		selectTravellers();	
		Search.click();
		
	}
}
