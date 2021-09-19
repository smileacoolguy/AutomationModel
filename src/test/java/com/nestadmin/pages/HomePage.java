package com.nestadmin.pages;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class HomePage {

	WebDriver driver;
	@FindBy(xpath = "//img[@alt='nest_logo' and @src='/nest_adm/img/admin/signin_img.png']")
	public WebElement LoginButton;
	//@FindBy(xpath = "//*[@id='signupModalButton']")
	//WebElement getStarted;

	public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
	}

	public void LoginButtonHeader() {
	String getheadertext = LoginButton.getText();
	Reporter.log(getheadertext);    //equalant of sysout
	assertEquals(getheadertext,"");   //since its image source hence cannot provide get text
	}
	

	
}
