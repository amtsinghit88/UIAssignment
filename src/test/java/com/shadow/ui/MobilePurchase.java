package com.shadow.ui;


import com.shadow.base.Base;
import static com.shadow.common.CommonMethod.*;

import com.shadow.testData.MobilePurchaseTestData;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class MobilePurchase extends Base {

	RemoteWebDriver driver;
	ArrayList<String> tabs;


	@Test(dataProviderClass = MobilePurchaseTestData.class, dataProvider = "mobilePurchase")
	public void mobilePurchase(String mobileCompany,String mobileBrand) throws InterruptedException {
		tabs = selectMobileBrand(driver,mobileCompany,mobileBrand);
		cartToPaymentVerification(driver,mobileBrand,applicationProp.getProperty("bankName"));
	}




	@Parameters({ "browser"})
	@BeforeTest
	public void setUp(String browser) {
		driver = setUpBrowser(browser);
		driver.get(applicationProp.getProperty("url"));
	}
	


	@AfterTest(alwaysRun = true)
	public void tearDown() {
		driver.switchTo().window(tabs.get(0));
		driver.close();
		driver.quit();
	}
}
