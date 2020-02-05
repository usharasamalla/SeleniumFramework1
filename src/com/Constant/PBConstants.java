package com.Constant;

import java.io.FileInputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
public class PBConstants {
	public static WebDriver driver;
	public static Properties p;
	public static FileInputStream fi;
	@BeforeMethod
	public void setUp() throws Throwable
	{
		p=new Properties();
		fi=new FileInputStream("C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\PropertyFile\\Environment.properties");
		p.load(fi);
		if(p.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			System.out.println("executing on chrome driver");
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\CommonDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.get(p.getProperty("objurl"));
		}
		else if(p.getProperty("browser").equalsIgnoreCase("firefox"))
		{
			System.out.println("executing on firefox driver");
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\STUDENT\\eclipse-workspace\\SeleniumFrameworks\\CommonDrivers\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.get("objurl");
		}
		else
		{

			System.out.println("browser is not matching");
		}

	}

	@AfterMethod
	public void teardown()
	{
		driver.quit();
	}
}
