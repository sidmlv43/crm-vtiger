package org.comcast.crm.generic.objectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    @FindBy(name = "user_name")
    private WebElement usernameInputField;

    @FindBy(name = "user_password")
    private WebElement userPasswordInputField;

    @FindBy(id = "submitButton")
    private WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameInputField.sendKeys(username);
        userPasswordInputField.sendKeys(password);
        loginButton.click();
    }


}
