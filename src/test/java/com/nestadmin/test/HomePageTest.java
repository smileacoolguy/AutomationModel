package com.nestadmin.test;

import com.nestadmin.*;
import com.nestadmin.pages.HomePage;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test; 

public class HomePageTest extends BaseTestClass{

	@Test(priority=0,enabled = false)
	public void verifyTitle() {
		logger = extent.createTest("To verify Nest Title");
		Assert.assertEquals(driver.getTitle(), "Nest Login");
	}


	
	
	@Test(priority=1,enabled = true,dataProvider = "getExcelProperty")
	public void verifyNestLogin(String user,String pwd) {
		logger = extent.createTest("To verify Nest Login "+Browser);
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
		
		hp.LoginButtonHeader();              //verification button text
		//driver.findElement(By.xpath(locator)).click();
		clickMethod(driver,elem);           //click the login button
		//customImplicitwait(driver,2);    //method to wait by passing wait time in argument
		implicitWaitBaseTestClass();       // method to wait, wait time mentioned in property file
		
		/*
		WebElement elemUser=hp.Username;
		waitMethod(driver,elemUser);
		elemUser.sendKeys(user);
		customImplicitwait(driver,1); 
		elemUser.sendKeys(Keys.chord(Keys.ENTER));
		//driver.findElement(By.xpath("//div[@id='identifierNext']/div/button/span")).click();
		//driver.findElement(By.xpath("//input[@id='identifierId']")).click();
		*/
		
		/*
		 * try { throw new Exception("fails"); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally{
		 * System.out.println("closed"); }
		 */
		 /*
		  WebElement elemPwd=hp.Password; 
		  elemPwd.sendKeys(pwd); customImplicitwait(driver,1);
		  elemPwd.sendKeys(Keys.chord(Keys.ENTER));
		 */
		log4j2.info(driver.getTitle());
		driver.findElement(By.xpath("//input[@id='isbn']")).sendKeys("9788726590418");
		Keys.chord(Keys.CONTROL);
		driver.findElement(By.xpath("//input[@type='submit' and @value='Search Products']")).click();
		WebElement isbnelem=driver.findElement(By.xpath("//a[text()='9788726590418']"));
		waitMethod(driver,isbnelem);
		//isbnelem.sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		isbnelem.click();
		customImplicitwait(driver,3);
		String mainpage= driver.getWindowHandle();
		Set<String> pagess= driver.getWindowHandles();
		System.out.println(pagess);
		System.out.println(mainpage);
		System.out.println(driver.switchTo().window(mainpage).getTitle());
		for(String windowhandle:pagess) {
			if(!windowhandle.equals(mainpage)){
				System.out.println(driver.switchTo().window(windowhandle).getTitle());
			}
			
		}
		process.destroy();
		
	}
	
}
