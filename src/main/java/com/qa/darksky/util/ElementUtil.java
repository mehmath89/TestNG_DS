package com.qa.darksky.util;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.darksky.base.BasePage;



/**
 * 
 * @author mehmetgun
 *
 */

public class ElementUtil extends BasePage{
	
WebDriver driver;
WebDriverWait wait;
JavaScriptUtil javaScriptUtil;
Properties prop;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME);
		javaScriptUtil = new JavaScriptUtil(driver);
	}
	/**
	 * Title wait method
	 * @param title
	 * @return
	 */
	public boolean waitForTitlePresent(String title) {
		wait.until(ExpectedConditions.titleIs(title));
		return true;
	}
	/**
	 * wait element 
	 * @param locator
	 * @return
	 */
	public boolean waitForElementPresent(By locator){
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return true;
	}
	/**
	 * Visiblity element
	 * @param locator
	 * @return
	 */
	public boolean waitForElementVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return true;
	}
	
	/**
	 *  Clickable Element
	 * @param locator
	 * @return
	 */
	
	public boolean waitForElementClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		return true;
	}
	
	/**
	 * Get title
	 * @return
	 */
	public String doGetPageTitle() {
		
		try {
			return driver.getTitle();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting the title...");
		}
		return null;
	}
	
	/**
	 * This method is used to create the webelement on the basis of by locator
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		
		WebElement element = null;
		
		try {
			// if (waitForElementPresent(locator)) ;
			element = driver.findElement(locator);
			if (highlightElement) {
				javaScriptUtil.flash(element);
			}
		} catch (Exception e) {
			System.out.println("some exception got occured while creating the web element" + locator);
		}
		return element;
	}
	
	
	/**
	 * This method is used to click the webelement on the basis of by locator
	 * @param locator
	 */
	public void doClick(By locator) {
		
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("some exception got occured while clicking the web element" + locator);
		}
	}
	
	/**
	 * This method is used to send value in a field
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		
		try {
			WebElement element = getElement(locator);
			element.clear();
			element.sendKeys(value);
		} catch (Exception e) {
			System.out.println("some exception got occured while entering values in a field" + locator);
		}
	}
	
	/**
	 * isDisplayed
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("some exception got occured isDisplayed method" + locator);
		}
		return false;
	}
	
	/**
	 * isEnabled
	 * @param locator
	 * @return
	 */
	public boolean doIsEnabled(By locator) {
		
		try {
			return getElement(locator).isEnabled();
		} catch (Exception e) {
			System.out.println("some exception got occured isEnabled method" + locator);
		}
		return false;
	}
	
	/**
	 * isSelected
	 * @param locator
	 * @return
	 */
	public boolean doIsSelected(By locator) {
		
		try {
			return getElement(locator).isSelected();
		} catch (Exception e) {
			System.out.println("some exception got occured isSelected method" + locator);
		}
		return false;
	}
	
	
	/**
	 * GetText()
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		
		try {
			return getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("some exception got occured while getting text..." + locator);
		}
		return null;
	}

}

