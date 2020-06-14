package com.selenium.testCasesMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCasesHardAssert {
	public static WebDriver driver;
	public static Properties properties;
	public static ChromeOptions chromeOptions;

	/*
	 * 
	 * the hard assert, once the condition is false, it leaves the test block
	 */
	@Test(priority = 1)
	public static void loginTest() throws IOException, ATUTestRecorderException {
		System.out.println("i will execute last");

		// extentTest = extentreports.startTest(properties.getProperty("url"));
		WebElement username = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("pass"));
		WebElement connect = driver.findElement(By.xpath("//input[@value='Connexion']"));
		username.sendKeys(properties.getProperty("username"));
		password.sendKeys(properties.getProperty("password"));
		connect.click();

		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);

		driver.manage().deleteAllCookies();
	}

	public static void loadFile() throws IOException {
		properties = new Properties();
		// don't forget to change the path of the file according to the location on your
		// computer
		FileInputStream inputStream = new FileInputStream(
				"D:\\Training-WS\\selenium-testNg-partI\\src\\main\\java\\com\\application\\configuration\\config.properties");
		properties.load(inputStream);
	}

	public static void initilzation(String URL) {
		chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);

		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 2)
	public static void getUrlTest() {
		System.out.println("it will execute second");
		String actual = driver.getCurrentUrl();
		String expected = "https://www.facebook.com/";
		Assert.assertEquals(expected, actual, "failed");

		// you can use: Assert.EqualsFalse(false) will be print into console, result is
		// false

	}

	@Test(priority = 3)
	public static void checkButtonTest() {
		System.out.println("i will execute last");
		WebElement connect = driver.findElement(By.xpath("//input[@value='Connexion']"));
		boolean actual = connect.isDisplayed();

		Assert.assertTrue(actual, "logo is not display");

		// you can use: Assert.EqualsFalse(false) will be print into console, result is
		// false

	}

	@BeforeMethod
	public static void startMethodTest() throws IOException {
		System.out.println("it will run befor each method of the class itself");
		loadFile();

		initilzation(properties.getProperty("url"));
	}

	@AfterMethod
	public static void endMethodTest() {
		System.out.println("it will run after each method of the class itself");
		driver.quit();
	}

}
