package com.shadow.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CommonLocator {



	@FindBy(how = How.XPATH, using = "//button[text()='✕']")
	public WebElement closeBtn;

	@FindBy(how = How.XPATH, using = "//span[text()='Electronics']")
	public WebElement electronicTab;


	@FindBy(how = How.XPATH, using = "//button[text()='ADD TO CART']")
	public WebElement cartBtn;


	@FindBy(how = How.XPATH, using = "//a/img[@title='Flipkart']")
	public WebElement flipkartMainLink;


	@FindBy(how = How.XPATH, using = "//a[span[.='Cart']]")
	public WebElement mainCartBtn;

	@FindBy(how = How.XPATH, using = "//button[span[.='Place Order']]")
	public WebElement placeOrderBtn;

	@FindBy(how = How.XPATH, using = "//span[contains(.,'Mobile number')]/preceding::input")
	public WebElement userLoginInputBox;

	@FindBy(how = How.XPATH, using = "//input[@type='password']")
	public WebElement userpwdInputBox;

	@FindBy(how = How.XPATH, using = "//button[span[.='Login']]")
	public WebElement loginBtn;

	@FindBy(how = How.XPATH, using = "//button[span[.='CONTINUE']]")
	public WebElement contiuneBtn;

	@FindBy(how = How.XPATH, using = "//button[text()='Deliver Here']")
	public WebElement deliverHereBtn;

	@FindBy(how = How.XPATH, using = "//button[.='CONTINUE']")
	public WebElement orderPageContiuneBtn;

	@FindBy(how = How.XPATH, using = "//div[text()='Net Banking']/preceding::div[1]")
	public WebElement netBankingBtn;

	@FindBy(how = How.XPATH, using = "//h3[text()='Other Banks']/following::select")
	public WebElement otherBankList;

	@FindBy(how = How.XPATH, using = "//button[contains(.,'PAY')]")
	public WebElement netBankingPayBtn;

	@FindBy(how = How.XPATH, using = "//h2[text()='Oppo Mobiles under ₹15K']/following::a[1]")
	public WebElement viewAllBtn;

	@FindBy(how = How.XPATH, using = "//div[text()='Currently out of stock in this area.']")
	public WebElement unavailableStockErr;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Enter Delivery Pincode']")
	public WebElement enterDeliveryCode;




	public static String mobileSelection = "//span[text()='Electronics']/following::ul//li/a[text()='nameLbl']";
	public static String mobileTypeSelection = "//a[contains(text(),'NameLbl')]";
	public static String mobileSelectionViewAll = "//div[contains(text(),'NameLbl')]";











}
