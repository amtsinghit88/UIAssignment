package com.shadow.common;

import static com.shadow.seleniumUtils.SeleniumUtility.*;

import com.shadow.pages.CommonLocator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.shadow.base.Base;

import java.util.ArrayList;

public class CommonMethod extends Base {


    public static ArrayList<String> selectMobileBrand(RemoteWebDriver driver, String mobileCompany, String mobileBrand) throws InterruptedException {
        Boolean flag=false;
        seleniumClick(commomLoc.closeBtn);
        mouseOver(driver,commomLoc.electronicTab);
        Reporter.log("Mouse Over at eletronic tab",true);
        seleniumClick(driver, CommonLocator.mobileSelection,mobileCompany);
        Reporter.log(mobileCompany+" brand is selected from eletronic tab",true);
        flag = isElementPresent(driver,CommonLocator.mobileTypeSelection,mobileBrand);
        if(flag){
            scrollToViewElement(driver,CommonLocator.mobileTypeSelection,mobileBrand);
            jsClick(driver, CommonLocator.mobileTypeSelection,mobileBrand);
            Reporter.log(mobileBrand+" phone type is selected from "+mobileBrand,true);

        }else{
            seleniumClick(commomLoc.viewAllBtn);
            scrollToViewElement(driver,CommonLocator.mobileSelectionViewAll,mobileBrand);
            jsClick(driver, CommonLocator.mobileSelectionViewAll,mobileBrand);
            Reporter.log(mobileBrand+" phone type is selected from "+mobileBrand,true);
        }
        ArrayList<String> windwoId = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(windwoId.get(1));
        if(isElementPresent(commomLoc.unavailableStockErr)){
            seleniumType(commomLoc.enterDeliveryCode,"560068");
            commomLoc.enterDeliveryCode.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            if(isElementPresent(commomLoc.unavailableStockErr))
            Assert.fail(mobileBrand+ "mobile brand isn't available to cart page");
        }
        Thread.sleep(2000);
        seleniumClick(commomLoc.cartBtn);
        Reporter.log(mobileBrand+" added item into cart ",true);
        seleniumClick(commomLoc.flipkartMainLink);
        Reporter.log("Clicked on Flipkart main Link",true);
        return windwoId;

    }

    public static void cartToPaymentVerification(RemoteWebDriver driver,String mobileBrand,String bankName) throws InterruptedException {
        waitForElementVisibility(driver,commomLoc.mainCartBtn,10);
        seleniumClick(commomLoc.mainCartBtn);
        Reporter.log("Clicked on main cart link",true);
        driver.navigate().refresh();
        if(!isElementPresent(driver,CommonLocator.mobileTypeSelection,mobileBrand)){
            Assert.fail(mobileBrand+" isn't present at cart");
        }else
            Reporter.log(mobileBrand+" is present at cart",true);
        //System.out.println(driver.getCurrentUrl());

        seleniumClick(commomLoc.placeOrderBtn);
        seleniumType(commomLoc.userLoginInputBox,applicationProp.getProperty("email"));
        seleniumClick(commomLoc.contiuneBtn);
        seleniumType(commomLoc.userpwdInputBox,applicationProp.getProperty("password"));
        seleniumClick(commomLoc.loginBtn);
        Reporter.log("sucessful login with "+applicationProp.getProperty("email")+" password ::"+applicationProp.getProperty("password"),true);
        seleniumClick(commomLoc.deliverHereBtn);
        seleniumClick(commomLoc.orderPageContiuneBtn);
        scrollToViewElement(driver,commomLoc.netBankingBtn);
        seleniumClick(commomLoc.netBankingBtn);
        selectItemFromList(commomLoc.otherBankList,bankName);
        seleniumClick(commomLoc.netBankingPayBtn);
        Reporter.log("selected Netbanking :: bank name::"+bankName,true);
        Thread.sleep(4000);
        captureScreenShotOnFailure();
        driver.close();



    }
}
