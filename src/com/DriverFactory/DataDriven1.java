package com.DriverFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Utilities.ExcelFileUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
public class DataDriven1 {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;
	String inputpath="C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\TestInput\\Registerdata.xlsx";
	String outputpath="C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\TestOutput\\Register.xlsx";
	File screen;
	@BeforeTest
	public void setUp()
	{
	report=new ExtentReports("./Reports/Webreg.html");
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\CommonDrivers\\chromedriver.exe");
	driver=new ChromeDriver();
	}
	@Test
	public void verifyRegister()throws Throwable
	{
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
    int rc=xl.rowCount("Register");
	int cc=xl.colCount("Register");
	Reporter.log(rc+"   "+cc,true);
	for(int i=1;i<=rc;i++)
	{
		test=report.startTest("Verify Register");
	String fname=xl.getCellData("Register", i, 0);
	String lname=xl.getCellData("Register", i, 1);
	String phone=xl.getCellData("Register", i, 2);
	String email=xl.getCellData("Register", i, 3);
	String address1=xl.getCellData("Register", i, 4);
	String address2=xl.getCellData("Register", i, 5);
	String city=xl.getCellData("Register", i, 6);
	String state=xl.getCellData("Register", i, 7);
	String pcode=xl.getCellData("Register", i, 8);
	String country=xl.getCellData("Register", i, 9);
	String user=xl.getCellData("Register", i, 10);
	String password=xl.getCellData("Register", i, 11);
	String cpassword=xl.getCellData("Register", i, 12);
	driver.get("http://newtours.demoaut.com/");
	driver.manage().window().maximize();
	driver.findElement(By.partialLinkText("REG")).click();
	Thread.sleep(3000);
	driver.findElement(By.name("firstName")).sendKeys(fname);
	driver.findElement(By.name("lastName")).sendKeys(lname);
	driver.findElement(By.name("phone")).sendKeys(phone);
	driver.findElement(By.name("userName")).sendKeys(email);
	driver.findElement(By.name("address1")).sendKeys(address1);
	driver.findElement(By.name("address2")).sendKeys(address2);
	driver.findElement(By.name("city")).sendKeys(city);
	driver.findElement(By.name("state")).sendKeys(state);
	driver.findElement(By.name("postalCode")).sendKeys(pcode);
	driver.findElement(By.name("country")).sendKeys(country);
	driver.findElement(By.name("email")).sendKeys(user);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("confirmPassword")).sendKeys(cpassword);
	driver.findElement(By.name("register")).click();
	if(password.equals(cpassword))
	{
	screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(screen, new File("C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworksScreens\\Iteration"+i+"register.png"));
      String message=driver.findElement(By.xpath("//font[contains(text(),'Thank you for registering.')]")).getText();	
		Reporter.log(message,true);
		test.log(LogStatus.PASS, message);
		xl.setCellData("Register", i, 13, message, outputpath);
		xl.setCellData("Register", i, 14, "Pass", outputpath);
		
	}
	else {
		screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworksScreens\\Iteration"+i+"register.png"));	
		Reporter.log("Register Unsuccess",true);
		test.log(LogStatus.FAIL, "Register Unsuccess");
		xl.setCellData("Register", i, 13, "Register Unsuccess", outputpath);
		xl.setCellData("Register", i, 14, "Fail", outputpath);	
	}
	report.endTest(test);
	report.flush();
	}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
	
}
