package com.qa.darksky.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.page.MainPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Epic - 101 : Checking temperature features")
@Feature("US - 501 : Check test for temperature on Darksky")
public class MainPageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties properties;
	MainPage mainPage;
	
	Logger log = Logger.getLogger(MainPageTest.class);
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		properties = basePage.initializeProperties();
		String browserName = properties.getProperty("browser");
		driver = basePage.initializeDriver(browserName);
		driver.get(properties.getProperty("url"));
		log.info("url is launched " + properties.getProperty("url"));
		mainPage = new MainPage(driver);
	}
	
	@Test
	@Description("Verify Lowest and Highest Temperature today")
	@Severity(SeverityLevel.NORMAL)
	public void allActionsTest() throws InterruptedException {
		log.info("starting ------->>>>>> allActionsTest");
		mainPage.allActions();
		log.info("ending ------->>>>>> allActionsTest");
		log.warn("some warning");
		log.error("some error");
		log.fatal("fatal error");
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
