package com.selenium.testCasesMethods;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCasesMethod {
	public static WebDriver driver;
	public static Properties properties;

	@Test
	public static void loginTest() throws IOException, ATUTestRecorderException {
		System.out.println("i will execute loginTest Method!!");
		loadFile();

		initilzation(properties.getProperty("url"));
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
				"D:\\Training-WS\\selenium-testNg2\\src\\main\\java\\com\\application\\configuration\\config.properties");
		properties.load(inputStream);
	}

	public static void initilzation(String URL) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

}
