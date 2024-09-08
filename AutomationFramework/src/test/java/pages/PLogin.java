package pages;

import java.time.Duration;

import org.openqa.selenium.By;

import base.BaseTest;

public class PLogin extends BaseTest{
	
	public void loginM(String username, String password) {

		driver.get(prop.getProperty("testurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath(loc.getProperty("username_field"))).sendKeys(username);
		driver.findElement(By.xpath(loc.getProperty("password_feild"))).sendKeys(password);
		driver.findElement(By.xpath(loc.getProperty("login_button"))).click();
	}

}
