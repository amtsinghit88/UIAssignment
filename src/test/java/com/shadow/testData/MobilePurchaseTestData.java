package com.shadow.testData;

import org.testng.annotations.DataProvider;

public class MobilePurchaseTestData {

    @DataProvider(name = "mobilePurchase")
    public static Object[][] mobilePurchaseData() {

        return new Object[][] {{ "OPPO", "OPPO A5s (Gold, 64 GB)"}};

    }
}
