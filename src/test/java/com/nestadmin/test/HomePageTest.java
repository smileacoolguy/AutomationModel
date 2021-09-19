package com.nestadmin.test;

import com.nestadmin.*;
import com.nestadmin.pages.HomePage;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test; 

public class HomePageTest extends BaseTestClass{

	@Test(enabled = true)
	public void verifyTitle() {
		logger = extent.createTest("To verify Nest Title");
		Assert.assertEquals(driver.getTitle(), "Nest Login");
	}


	
	@Test(enabled = true,dataProvider = "getExcelProperty")
	public void verifyNestLogin(String user,String pwd) {
		logger = extent.createTest("To verify Nest Login");
		//String locator = "//img[@alt='nest_logo' and @src='/nest_adm/img/admin/signin_img.png']";
		System.out.println(driver.getTitle());

		//WebElement ele = driver.findElement(By.xpath(locator));   //Kept sample
		
		//System.out.println(Arrays.deepToString(getExcelProperty()));
		String s= Thread.currentThread().getStackTrace()[1].getMethodName();
		System.out.println("Data provider Selected in " +s+":: User:"+user+" pwd:"+pwd);
		log4j2.info("Data provider Selected in " +s+":: User:"+user+" pwd:"+pwd);
		
		HomePage hp=new HomePage(driver);         //waiting explicitly
		WebElement elem=hp.LoginButton;
		waitMethod(driver,elem);
		
		hp.LoginButtonHeader();     //verification button text
		//driver.findElement(By.xpath(locator)).click();
		clickMethod(driver,elem);              //click the login button
		//customImplicitwait(driver,2);    //method to wait by passing wait time in argument
		implicitWaitBaseTestClass();       // method to wait, wait time mentioned in property file
		
	}
	
}
