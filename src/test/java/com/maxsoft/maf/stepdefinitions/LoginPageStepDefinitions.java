package com.maxsoft.maf.stepdefinitions;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.openqa.selenium.support.PageFactory;
import com.maxsoft.maf.pages.LoginPage;
import com.maxsoft.maf.util.DriverSetup;
import java.io.IOException;
import java.util.List;
import static com.maxsoft.maf.pages.LoginPage.*;
import static com.maxsoft.maf.util.DriverSetup.androidDriver;
import static com.maxsoft.maf.util.DriverSetup.iosDriver;


/**
 * Created by Osanda on 4/29/2017.
 */


public class LoginPageStepDefinitions {

    private LoginPage loginPage;
    public static final String LOGIN_ERROR_TITLE = "Authentication Failed";
    public static final String LOGIN_ERROR_MESSAGE = "invalid username or password";
    public static final String LOGIN_SUCCESS_TITLE = "";
    public static final String LOGIN_SUCCESS_MESSAGE = "";

    public LoginPage pageObject(){
        if(DriverSetup.PLATFORM.toLowerCase().equals(DriverSetup.IOS.toLowerCase())){
            loginPage = PageFactory.initElements(iosDriver, LoginPage.class);
            return loginPage;
        } else {
            loginPage = PageFactory.initElements(androidDriver, LoginPage.class);
            return loginPage;
        }
    }

    @Step("Verify that the Login page title is <pageTitle>")
    public void verifyLoginPageTitle(String pageTitle) throws IOException {
        pageObject().isPageTitleEqualTo(LOGIN_PAGE, LOGIN_TITLE_TEXTVIEW, pageTitle);
    }

    @Step("Verify that a user can login into the application using valid username and password <table>")
    public void verifyLogin(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            pageObject().login(row.getCell(columnNames.get(0)), row.getCell(columnNames.get(1)));
        }
    }

    @Step("Clear text fields in Login page")
    public void clearTextFields() throws IOException {
        pageObject().clearTextFields();
    }

    @Step("Verify that the Create Account button is visible")
    public void isCreateAccountButtonVisible() throws IOException {
        pageObject().isCreateAccountButtonVisible();
    }

    @Step("On Create Account Page")
    public void navigateToCreateAccount() throws IOException {
        pageObject().navigateToCreateAccount();
    }

    @Step("Verify that the login is success")
    public void isLoginSuccess() throws IOException {
//        pageObject().isPageTitleEqualTo(EXPERT_DECKS_PAGE, EXPERT_DECKS_TITLE_TEXTVIEW, "Expert Decks");
//        pageObject().verifyElementTextIsVisible(LOGIN_PAGE, ALERT_MESSAGE, LOGIN_SUCCESS_MESSAGE);
//        pageObject().clickElement(LOGIN_PAGE, OK_BUTTON);
    }

    @Step("Verify that the login is fail")
    public void isLoginFail() throws IOException {
        pageObject().freeze(3);
        pageObject().verifyElementAccessibilityIdTextIsVisibleAs(LOGIN_PAGE, ALERT_TITLE, LOGIN_ERROR_TITLE);
        pageObject().verifyElementAccessibilityIdTextIsVisibleAs(LOGIN_PAGE, ALERT_MESSAGE, LOGIN_ERROR_MESSAGE);
//        pageObject().tapElement(LOGIN_PAGE, OK_BUTTON);
        pageObject().isPageTitleEqualTo(LOGIN_PAGE, LOGIN_TITLE_TEXTVIEW, "Login");
    }

    @Step("Verify that the user lands to the <pageTitle> page")
    public void verifyLandingPage(String pageTitle) throws IOException {
//        pageObject().isPageTitleEqualTo(EXPERT_DECKS_PAGE, ExpertDecksPage.EXPERT_DECKS_TITLE_TEXTVIEW, pageTitle);
    }


}
