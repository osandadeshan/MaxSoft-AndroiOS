package com.maxsoft.maf.pages;

import com.maxsoft.maf.common.Base;
import org.openqa.selenium.support.PageFactory;
import java.io.IOException;
import static com.maxsoft.maf.util.DriverSetup.androidDriver;
import static com.maxsoft.maf.util.DriverSetup.iosDriver;


/**
 * Created by Osanda on 5/14/2017.
 */


public class LoginPage extends Base {

    public static final String LOGIN_PAGE = "LoginPage";
    public static final String LOGIN_TITLE_TEXTVIEW = "login_title_textview";
    private static final String USERNAME_TEXTFIELD = "username_textfield";
    private static final String PASSWORD_TEXTFIELD = "password_textfield";
    private static final String SIGN_IN_BUTTON = "sign_in_button";
    private static final String CREATE_ACCOUNT_BUTTON = "create_account_button";
    private static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";
    public static final String ALERT_TITLE = "alert_title";
    public static final String ALERT_MESSAGE = "alert_message";
    public static final String OK_BUTTON = "ok_button";
    private static final String FORGOT_PASSWORD_BUTTON = "forgot_password_button";

    public LoginPage() {
        PageFactory.initElements(androidDriver, this);
        PageFactory.initElements(iosDriver, this);
    }

    public void login(String username, String password) throws IOException {
//        setTextAs(LOGIN_PAGE, USERNAME_TEXTFIELD, username);
//        setTextAs(LOGIN_PAGE, PASSWORD_TEXTFIELD, password);
//        hideKeyboard();
//        tapElement(LOGIN_PAGE, SIGN_IN_BUTTON);
    }

    public void clearTextFields() throws IOException {
        clearText(LOGIN_PAGE, USERNAME_TEXTFIELD);
        clearText(LOGIN_PAGE, PASSWORD_TEXTFIELD);
    }

    public void navigateToCreateAccount() throws IOException {
//        tapElement(LOGIN_PAGE, CREATE_ACCOUNT_BUTTON);
    }

    public void isCreateAccountButtonVisible() throws IOException {
        isElementNameEqualTo(LOGIN_PAGE, CREATE_ACCOUNT_BUTTON, CREATE_ACCOUNT_BUTTON_LABEL);
    }


}
