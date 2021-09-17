/*Sample change test
*/
package com.nestadmin;

//import com.test.A;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstAutoSel {
	public WebDriver driver;
	
	@Test(priority=0)
	public void FirstAutoSel() {
		// TODO Auto-generated constructor stub
		//A a=new A();
		//a.sampleString("ajaz");    //imported method run 
		
		System.setProperty("webdriver.chrome.driver","/test/src/test/resources/chromedriver.exe");

		//System.setProperty("webdriver.gecko.driver","/path/to/firefoxdriver")

		ChromeOptions chromeOptions = new ChromeOptions();
		//WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
				
				// Navigate to the demoqa website
				driver.get("https://www.demoqa.com");
				System.out.println(driver.getTitle());
				try {
					Assert.assertEquals("ToolsQA", driver.getTitle());
				} finally {
					System.out.println("Test Failed : Closing Driver");
				}


	}
	


@AfterMethod(alwaysRun = true)

	public void close() {
		driver.close();
		}	
	


}
