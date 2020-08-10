package com.qa.darksky.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static ThreadLocal<WebDriver> tl = new ThreadLocal<WebDriver>();
	
	public static synchronized WebDriver getDriver() {
		return tl.get();
	}
	//WebDriver driver;
	Properties properties;
	OptionsManager optionsManager;
	public static boolean highlightElement;
	
	public WebDriver initializeDriver(String browserName) {
		highlightElement = properties.getProperty("highlight").equals("yes") ? true : false;
		System.out.println(browserName);
		optionsManager = new OptionsManager(properties);
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tl.set(new ChromeDriver(optionsManager.getChromeOptions()));	
		}else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tl.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tl.set(new SafariDriver());
		}else {
			System.out.println("browser name " + browserName + " could not found");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		return getDriver();
	}
	public Properties initializeProperties() {
		properties = new Properties();
		String path = null;
		String env = null;
		try {
			env = System.getProperty("env");
			if (env.equalsIgnoreCase("qa")) {
				path = "./src/main/java/com/qa/darksky/config/config.qa.properties";
			}else if (env.equalsIgnoreCase("stg")) {
				path = "./src/main/java/com/qa/darksky/config/config.stg.properties";
			}
		} catch (Exception e) {
			path = "./src/main/java/com/qa/darksky/config/config.properties" ;
		}
		try {
			FileInputStream ip = new FileInputStream(path);
			properties.load(ip);
		} catch (FileAlreadyExistsException e) {
			System.out.println("Because of some of the config files, some problem occured. Please correct them");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

}
