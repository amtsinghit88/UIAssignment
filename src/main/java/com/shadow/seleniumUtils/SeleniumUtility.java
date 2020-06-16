package com.shadow.seleniumUtils;

import com.shadow.base.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;


public class SeleniumUtility extends Base {
	

	public static void seleniumClick(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
				String val = getXpathByReplace(loc, replaceVal);
				WebElement we = driver.findElement(By.xpath(val));
				waitForElementVisibility(driver,we,10);
				we.click();
		} catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value" + loc);
		}

	}
	
	public static void seleniumClick(WebElement we) {
		try {
			if(we.isDisplayed())
			we.click();
			else {
				Reporter.log("No such element found::locator value" + we + " ", true);
				Assert.fail("No such element found::locator value" + we);
			}	
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException exc) {
			Reporter.log("No such element found::locator value " + we, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value " + we);
		}
	}


	
	public static void seleniumType(WebElement we, String type) {
		try {
			we.clear();
			we.sendKeys(type);
		} catch (NoSuchElementException | StaleElementReferenceException | InvalidElementStateException exc) {
			Reporter.log("No such element found::locator value" + we, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value" + we);
		}
	}

	public static String seleniumGetText(WebElement we) {
		try {
			return we.getText();
		}catch(NoSuchElementException | StaleElementReferenceException exc){
			Reporter.log("No such element found::locator value" + we.toString(), true);
			//Assert.fail();
		}
		return null;
	}

	public static String getXpathByReplace(String loc, String replacedVal) {
		return loc.replaceAll("(?i)" + "nameLbl", replacedVal);
	}
	

	public static boolean isElementPresent(WebElement we) {
		try {
			we.isDisplayed();
				return true;
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			return false;
		}
	}

	public static boolean isElementPresent(RemoteWebDriver driver, String loc, String replaceVal) {
		try {
			String val = getXpathByReplace(loc, replaceVal);
			driver.findElement(By.xpath(val)).isDisplayed();
			return true;
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public static boolean waitForElementVisibility(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.visibilityOf(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element:"+" is not visible after waitng after " + Time + " seconds.");
			Reporter.log(""+we);
			return false;
		}

	}
	
	public static boolean waitForElementInVisibility(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.invisibilityOf(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element:"+" is not visible after waitng after " + Time + " seconds.");
			Reporter.log(""+we);
			return false;
		}

	}
	
	public static boolean waitForElementClickable(RemoteWebDriver driver, WebElement we , int Time){
		try {
			new WebDriverWait(driver, Time).until(ExpectedConditions.elementToBeClickable(we));
			return true;
		} catch (Exception e) {
			Reporter.log("<p>Element: "+" isnot clickable after waitng after " + Time + " seconds.</p>");
			return false;
		}

	}

	public static void mouseOver(RemoteWebDriver driver,WebElement we){
		Actions actions = new Actions(driver);
		actions.moveToElement(we).perform();
	}

	public static void selectItemFromList(WebElement we, String text) {
		try {
			Select select = new Select(we);
			List<WebElement> list = select.getOptions();
			for (WebElement e : list) {
				if (e.getText().contains(text)) {
					select.selectByVisibleText(e.getText());
					return;
				}
			}
		} catch (NoSuchElementException | StaleElementReferenceException | ElementNotVisibleException e) {
			Reporter.log("No such element found::locator value" + we, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value" + we);
		}
	}

	public static void scrollToViewElement(RemoteWebDriver driver, String loc, String replacedVal) {
		WebElement scroll = null;
		String str=null;
		try {
			str = loc.replaceAll("(?i)" + "nameLbl", replacedVal);
			scroll = driver.findElement(By.xpath(str));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
		}catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + scroll, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value :: " + str);
		}
	}

	public static void scrollToViewElement(RemoteWebDriver driver, WebElement scroll) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
		}catch (NoSuchElementException | StaleElementReferenceException exc) {
			Reporter.log("No such element found::locator value" + scroll, true);
			captureScreenShotOnFailure();
			Assert.fail("No such element found::locator value" + scroll);
		}
	}

	public static void jsClick(RemoteWebDriver driver, String loc, String replaceVal) {
		String val;
		try {
			if (!replaceVal.isEmpty()) {
				val = getXpathByReplace(loc, replaceVal);
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(val)));
			} else {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath(loc)));
			}
		} catch (NoSuchElementException exc) {
			Reporter.log("No such element found::locator value" + loc, true);
			Assert.fail("No such element found::locator value" + loc);
		}
	}

}
