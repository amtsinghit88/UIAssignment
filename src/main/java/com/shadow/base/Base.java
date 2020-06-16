package com.shadow.base;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.shadow.constant.CommonConstant;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.shadow.javaUtils.ReadFiles;
import com.shadow.pages.CommonLocator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static Properties applicationProp;
	public static LocalDate currentDate = LocalDate.now();
	public static LocalTime time = LocalTime.now();
	public static WebDriverWait wait;
	public static CommonLocator commomLoc;
	public static RemoteWebDriver captureDriver;
	

	@BeforeSuite
	public static void getPropertyFile() {
		applicationProp = ReadFiles.loadPropertyFile(CommonConstant.APPLICATIONPROP);
	}

	public RemoteWebDriver setUpBrowser(String browser) {
		RemoteWebDriver driver = getDriver(browser);
		locatorInitialize(driver);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
		captureDriver = driver;
		return driver;
		

	}

	public void locatorInitialize(RemoteWebDriver driver) {
		commomLoc = PageFactory.initElements(driver,CommonLocator.class);
		}

	public void closeBrowser(RemoteWebDriver driver) {
		driver.close();
		driver.quit();
	}

	public RemoteWebDriver getDriver(String browser) {
		RemoteWebDriver driver = null;
		String path;
		switch (browser) {
		case "chrome":
			driver = setUpChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", true);
			driver = new FirefoxDriver(options);
			break;
		}

		driver.manage().window().maximize();
		return driver;

	}
	
	public ChromeDriver setUpChromeDriver() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> images = new HashMap<>();
		images.put("images", 2);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);
		WebDriverManager.chromedriver().setup();
		options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		return new ChromeDriver(options);

	}

	public static void captureScreenShotOnFailure() {
		LocalTime time = LocalTime.now();
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		File file = new File(CommonConstant.SCREENSHOTPATH + File.separator + currentDate.toString());
		file.mkdir();
		try {
			//Reporter.log(captureDriver.getCurrentUrl(),true);
			File source = ((TakesScreenshot) captureDriver).getScreenshotAs(OutputType.FILE);
			File filePath = new File(file.toString() + File.separator + "snap" + "-TimeStamp-" + time.getHour() + "-"
					+ time.getMinute() +"-"+time.getSecond()+ ".jpg");
			FileUtils.copyFile(source, filePath);
			//Reporter.log("Error Image path : \n", true);
			Reporter.log("<a href='" + filePath.toString() + "' target=blank><img src='" + filePath.toString() + "' height=" + 200 + " width="
					+ 400 + " /></a>", true);
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot: " + e.getMessage(), true);
		}
	}
}
