package base;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Constants;

public class BaseTest {


		public static WebDriver driver;
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest logger;

		public static Properties prop = new Properties();
		public static Properties loc = new Properties();
		public static FileReader fr;
		public static FileReader frl;
		
		@BeforeTest
		public void BeforeTestMethod() {
			sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator+"reports"+File.separator+"SDETADDAExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			sparkReporter.config().setTheme(Theme.DARK);
			extent.setSystemInfo("HostName", "RHEL8");
			extent.setSystemInfo("UserName", "root");
			sparkReporter.config().setDocumentTitle("Automation Report");
			sparkReporter.config().setReportName("Automation Test Results by SDET ADDA");
		}
			
		@BeforeMethod
		public void setUp() throws IOException {
			
			if(driver==null) {
				fr = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\config.properties"); 
				frl = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\configfiles\\locators.properties");
				prop.load(fr);
				loc.load(frl);
			}
			
			if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
		}
		
		@AfterTest
		public void tearDown() {
			driver.close();
			System.out.print("TearDown Successfully");
		}
		
		@AfterMethod
		public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
		logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));}
		else if(result.getStatus() == ITestResult.SKIP){
		logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));}
		else if(result.getStatus() == ITestResult.SUCCESS){
		logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " - Test Case PASS", ExtentColor.GREEN));}
		driver.quit();
		}
		
	
}
