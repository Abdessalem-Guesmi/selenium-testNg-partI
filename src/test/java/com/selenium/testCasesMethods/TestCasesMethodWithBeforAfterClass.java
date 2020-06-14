package com.selenium.testCasesMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCasesMethodWithBeforAfterClass {
	public static WebDriver driver;
	public static Properties properties;

	@Test(priority = 1)
	public static void loginTest() throws IOException, ATUTestRecorderException {
		System.out.println("i will execute last");

		// extentTest = extentreports.startTest(properties.getProperty("url"));
		WebElement username = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("pass"));
		username.sendKeys(properties.getProperty("username"));
		password.sendKeys(properties.getProperty("password"));

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

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();

		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test(priority = 2)
	public static void getTitleTest() {
		System.out.println("i will execute last");

		// driver.get(properties.getProperty("url"));

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

	@BeforeClass
	public static void startClassTest() {
		System.out.println("only one time before all the test in the class");

	}

	@AfterClass
	public static void endClassTest() {
		System.out.println("only one time after all the test in the class");

	}
}
