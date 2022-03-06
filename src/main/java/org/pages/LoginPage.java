package org.pages;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

	// With out page factory
//	public WebElement getUser() {
//		WebElement txtUsername = driver.findElement(By.id("email"));
//		return txtUsername;
//	}
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBys({ @FindBy(id = "email"), @FindBy(xpath = "//input[@id='email']") })
	private List<WebElement> txtUserName;

	@FindAll({ @FindBy(id = "pas"), @FindBy(xpath = "//input[@id='pass']") })
	private WebElement txtPassword;

	@FindBy(name = "login")
	private WebElement btnLogin;

	public List<WebElement> getTxtUserName() {
		return txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

	public void login(String userName,String password) {

		sendKeys(getTxtUserName().get(0),userName);

		sendKeys(getTxtPassword(),password);

		btnClick(getBtnLogin());
	}

}
