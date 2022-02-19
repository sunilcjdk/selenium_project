package com.sunil.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class GenericClass {
	public static WebDriver driver;
	public static ExtentHtmlReporter extentHtmlreport=null;
	public static  ExtentReports extentreport=null;
	public static  ExtentTest extenttest=null;
	public static  String Browser="";
	public static  String URL="";
	
	public static String readProperty(String propertyName) throws FileNotFoundException, IOException
	{
		Properties prop=new Properties();
		prop.load(new FileReader(System.getProperty("user.dir")+"\\Data.properties"));
		String PropertyValue=prop.getProperty(propertyName);
		return PropertyValue;
	}
	
	@BeforeSuite
	public static void initialization() throws FileNotFoundException, IOException
	{
		
		Browser = readProperty("Browser");
		URL= readProperty("url");
		
		extentHtmlreport=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"\\target\\TestReport"+currentDate("yyyy_MM_dd_hh_mm_ss")+".html"));	
		extentHtmlreport.config().setDocumentTitle("Ixigo Title");
		extentHtmlreport.config().setReportName("Ixigo report");
		extentreport= new ExtentReports();
		extentreport.attachReporter(extentHtmlreport);
		extentreport.setSystemInfo("Browser", Browser);
		
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 	    
	    driver.get(URL); 
	    driver.manage().window().maximize();
		
}

	@AfterSuite
    // Test cleanup 
    public void TeardownTest() { 
        driver.quit(); 
        extentreport.flush();
    } 
	public String SaveScreenShot(String TestName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination =System.getProperty("user.dir")+"\\target\\ScreenShot\\Error_"+TestName+"_"+currentDate("yyyy_MM_dd_hh_mm_ss")+".jpg";
		System.out.println(destination);
		File destFile=new File(destination);
		FileUtils.copyFile(source, destFile);
		return destination;
	}
	
	public static String currentDate(String format)
	{
		SimpleDateFormat smpform=new SimpleDateFormat(format);
		Date date=new Date();
		return smpform.format(date);
	}

}
