package com.qa.darksky.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.darksky.base.BasePage;
import com.qa.darksky.util.ElementUtil;
import com.qa.darksky.util.JavaScriptUtil;

public class MainPage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	
	By today = By.xpath("(//*[@class='toggle'])[1]/span[1]");
	By searchBox = By.xpath("(//input[@type='text' and @value])[1]");
	By searchButton = By.cssSelector(".searchButton");
	By currentTemp = By.cssSelector(".summary.swap");
	By allTemps = By.xpath("((//*[@class='temps'])[2]/span)/span[contains(text(), '°')]");
	By firstTemp = By.xpath("((//*[@class='temps'])[2]/span)/span[contains(text(), '°')][1]");
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	
	public void allActions() throws InterruptedException {
		elementUtil.doSendKeys(searchBox, "Dayton");
		elementUtil.waitForElementClickable(searchButton);
		elementUtil.doClick(searchButton);
		elementUtil.waitForElementVisible(currentTemp);
		System.out.println(elementUtil.doGetText(currentTemp));
		javaScriptUtil.specificScrollPageDown(driver);
		Thread.sleep(2000);
		elementUtil.waitForElementClickable(today);
		elementUtil.doClick(today);
		Thread.sleep(2000);
		String stringFirstTemp = elementUtil.doGetText(firstTemp);
		elementUtil.waitForElementPresent(firstTemp);
		String  text = stringFirstTemp.replaceAll("°", "");
		int intFirstTemp = Integer.parseInt(text);
		int lowest = intFirstTemp;
		int highest = intFirstTemp;
		List<WebElement> allDHourlyTemps = driver.findElements(allTemps);
		for (int i = 0; i < allDHourlyTemps.size(); i++) {
			String stringTepms = allDHourlyTemps.get(i).getText();
			String text1 = stringTepms.replaceAll("°", "");
			int intTemp = Integer.parseInt(text1);
			if (lowest > intTemp) {
				lowest = intTemp;
			}
			if (highest < intTemp) {
				highest = intTemp;
			}
		}
		System.out.println("Lowest " + lowest + "°");
		System.out.println("Highest " + highest + "°");
		
	}
	
	

}
