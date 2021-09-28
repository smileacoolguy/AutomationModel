package com.nestadmin.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class SampleTestNormalAngular {
	String url="https://www.browserstack.com/users/sign_in";
	public WebDriver driver;
	String Angularurl = "https://juliemr.github.io/protractor-demo/";

	@Test(testName = "sample for all use ", enabled = false)
	public void login() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
	   // driver = new ChromeDriver();
		ChromeOptions chromeOptions = new ChromeOptions();
		//WebDriverManager.chromedriver().driverVersion("93.0.4577.82").setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get(url);
		WebElement username = driver.findElement(By.id("user_email_Login"));
		WebElement password = driver.findElement(By.id("user_password"));
		WebElement login = driver.findElement(By.name("commit"));
		username.sendKeys("abc@gmail.com");
		password.sendKeys("your_password");
		login.click();
		String actualUrl = "https://live.browserstack.com/dashboard";
		String expectedUrl = driver.getCurrentUrl();
		Assert.assertEquals(expectedUrl, actualUrl);
	}

	@SuppressWarnings("deprecation")
	@Test(testName = "rough sample for all use ", enabled = true)
	public void loginCheckAngularWebPages() throws InterruptedException {
		//Reference https://blog.clairvoyantsoft.com/automating-angular-applications-using-selenium-with-ngwebdriver-102500f105c0
		
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		//WebDriverManager.chromedriver().driverVersion("93.0.4577.82").setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		NgWebDriver ng=new NgWebDriver(js);
		ng.waitForAngularRequestsToFinish();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
		driver.get(Angularurl);
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
		ng.waitForAngularRequestsToFinish();
		
		WebElement username = driver.findElement(ByAngular.model("first"));
		WebElement password = driver.findElement(ByAngular.model("second"));
		WebElement login = driver.findElement(ByAngular.buttonText("Go!"));
		username.sendKeys("2");
		password.sendKeys("4");
		login.click();
		driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS); 
		Thread.sleep(5000);
		driver.quit();
		//System.exit(0);
//String actualUrl="https://live.browserstack.com/dashboard";
//String expectedUrl= driver.getCurrentUrl();
//Assert.assertEquals(expectedUrl,actualUrl);
	}
}
