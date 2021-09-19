package com.nestadmin;
import java.util.concurrent.TimeUnit;
import com.nestadmin.utils.ExcelReadClassApachePOI;

import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter ;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nestadmin.configs.ConfigFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Reporter;

public class BaseTestClass {

	
	public WebDriver driver;
	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	// public String BaseURL="https://www.google.com/";
	//public String BaseURL = "http://apptest.nextory.se/nest_adm/login";
	public String BaseURL = getProperty("url");
	public long implicitProperty=Long.parseLong(getProperty("implicitWait")); 
	// img[@alt="nest_logo" and @src="/nest_adm/img/admin/signin_img.png"]
    //logger log4j2 can be used in every child class
	public static final Logger log4j2 = org.apache.logging.log4j.LogManager.getLogger();
	
	public static String getProperty(String s) {
		ConfigFileReader cf=new ConfigFileReader();
		System.out.println(cf.getProperty(s));
		return cf.getProperty(s);
	}
	
	@DataProvider(name="getExcelProperty")
	//return two dimensional array, used for data provider
	public Object[][] getExcelProperty() throws IOException {
		Reporter.log("Excel File Reading Started by @DataProvider",true);
		ExcelReadClassApachePOI objExcelFile=new ExcelReadClassApachePOI();
		String filePath = System.getProperty("user.dir") + "\\src\\excelExportAndFileIO";
		Object[][] arr =
				objExcelFile.readExcel(filePath, "AutomationInput.xlsx", "Automation").entrySet().stream()
		                .map(e -> new Object[]{e.getKey(), e.getValue()})
		                .toArray(Object[][]::new);
		return arr;
		 
	}
	
	
	@BeforeTest
	public void startReport() {
		Reporter.log("ExtentReports Set up completed", true);

		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
		// Create an object of Extent Reports
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial by Ismail");
		extent.setSystemInfo("Environment", "Acpt");
		extent.setSystemInfo("User Name", "Ismail");
		htmlReporter.config().setDocumentTitle("Title of the Report by Ismail");
		// Name of the report
		htmlReporter.config().setReportName("Ismail Test reports ");
		// Dark Theme
		htmlReporter.config().setTheme(Theme.DARK);

	}
	

@SuppressWarnings("deprecation")
@BeforeTest(enabled=false)
public void setup1() {
System.setProperty("webdriver.chrome.driver", "C:\\BrowserStack\\chromedriver.exe");
driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get("https://www.browserstack.com/");
}

@BeforeClass
public void info() {
	Reporter.log("Base URL Selected :"+BaseURL,true);
}

@BeforeMethod
public void setup() {
	System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");
	ChromeOptions chromeOptions = new ChromeOptions();
	// WebDriverManager.chromedriver().driverVersion("93.0.4577.63").setup();
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver(chromeOptions);

	driver.manage().window().maximize();
	implicitWaitBaseTestClass();
	driver.get(BaseURL);
	implicitWaitBaseTestClass();
}


// This method is to capture the screenshot and return the path of the
// screenshot.
public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	// after execution, you could see a folder "FailedTestsScreenshots" under src
	// folder
	String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
}

public void implicitWaitBaseTestClass() {
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitProperty));
}

public static void waitMethod(WebDriver driver,WebElement elem) {
	WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
	w.until(ExpectedConditions.elementToBeClickable(elem));
}

public void clickMethod(WebDriver driver,WebElement elem) {
	elem.click();
}

public void customImplicitwait(WebDriver driver,int i) {
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(i));
}

@AfterMethod
public void getResult(ITestResult result) throws Exception {
	if (result.getStatus() == ITestResult.FAILURE) {
		// MarkupHelper is used to display the output in different colors
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
		// To capture screenshot path and store the path of the screenshot in the string
		// "screenshotPath"
		// We do pass the path captured by this method in to the extent reports using
		// "logger.addScreenCapture" method.
		// String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
		String screenshotPath = getScreenShot(driver, result.getName());
		// To add it in the extent report
		logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
	} else if (result.getStatus() == ITestResult.SKIP) {
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
	} else if (result.getStatus() == ITestResult.SUCCESS) {
		logger.log(Status.PASS,
				MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
	}
	driver.quit();
}

@AfterTest(alwaysRun = true)
public void endReport() {
	extent.flush();
}

}