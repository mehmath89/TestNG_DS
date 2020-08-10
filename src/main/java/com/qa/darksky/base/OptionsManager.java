package com.qa.darksky.base;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	public ChromeOptions co;
	public FirefoxOptions fo;
	public Properties properties;
	
	public OptionsManager(Properties properties){
		this.properties = properties;
	}
	
	public ChromeOptions getChromeOptions(){
		co = new ChromeOptions();
		if (properties.getProperty("incognito").equals("yes")) co.addArguments("--incognito");
		if (properties.getProperty("headless").equals("yes")) co.addArguments("--headless");
		
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions(){
		fo = new FirefoxOptions();
		if (properties.getProperty("headless").equals("yes")) fo.addArguments("--headless");
		
		return fo;
	}

}
