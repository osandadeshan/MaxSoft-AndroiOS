package com.maxsoft.androios.stepdefinitions;

import com.maxsoft.androios.common.Base;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import org.testng.Assert;
import com.maxsoft.androios.util.DriverSetup;
import com.maxsoft.androios.util.ToastMessage;
import java.io.IOException;
import java.util.List;


/**
 * Created by Osanda on 4/29/2017.
 */


public class CommonStepDefinitions {

    private static final String PLATFORM = DriverSetup.PLATFORM.toLowerCase();
    private static final String ANDROID = "android";
    private static final String IOS = "ios";
    private static Base baseObj = new Base();

    @Step("Configurations of the testing environment")
    public void platformInfo() {
        switch (PLATFORM) {
            case ANDROID:
                Gauge.writeMessage("Platform Name: Android");
                Gauge.writeMessage("Android Version: v" + DriverSetup.ANDROID_VERSION);
                Gauge.writeMessage("Android Device: " + DriverSetup.ANDROID_DEVICE_NAME);
                Gauge.writeMessage("Android App Package Name: " + DriverSetup.ANDROID_APP_PACKAGE);
                Gauge.writeMessage("Appium Server Host: " + DriverSetup.APPIUM_HOST);
                Gauge.writeMessage("Appium Port: " + DriverSetup.APPIUM_PORT);
                break;
            case IOS:
                Gauge.writeMessage("Platform Name: iOS");
                Gauge.writeMessage("iOS Version: v" + DriverSetup.IOS_VERSION);
                Gauge.writeMessage("iOS Device: " + DriverSetup.IOS_DEVICE_NAME);
                Gauge.writeMessage("iOS App Package Name: " + DriverSetup.BUNDLE_ID);
                Gauge.writeMessage("Appium Server Host: " + DriverSetup.APPIUM_HOST);
                Gauge.writeMessage("Appium Port: " + DriverSetup.APPIUM_PORT);
                break;
            default:
                Gauge.writeMessage("Targeted platform is not supported");
                break;
        }
    }

    @Step("Tap the element <table>")
    public void tapElement(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            baseObj.tapElement(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)));
        }
    }

    @Step("Set the text as <table>")
    public void setText(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            baseObj.setTextAs(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), row.getCell(columnNames.get(3)));
        }
    }

    @Step("Validate the elements' visibility on the screen <table>")
    public void isElementVisible(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            if (row.getCell(columnNames.get(3)).toLowerCase().equals("y") || row.getCell(columnNames.get(3)).toLowerCase().equals("yes")) {
                baseObj.isElementVisible(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)));
            } else {
                baseObj.isElementNotVisible(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)));
            }

        }
    }

    @Step("Validate the elements' attributes on the screen <table>")
    public void isElementAttributeEquals(Table table) throws IOException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            String attributeName = row.getCell(columnNames.get(3)).toLowerCase();
            switch (attributeName) {
                case "name":
                    baseObj.isElementAttributeValueEqualTo(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), attributeName, row.getCell(columnNames.get(4)));
                    break;

                case "text":
                    baseObj.isElementAttributeValueEqualTo(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), attributeName, row.getCell(columnNames.get(4)));
                    break;

                case "value":
                    baseObj.isElementAttributeValueEqualTo(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), attributeName, row.getCell(columnNames.get(4)));
                    break;

                case "label":
                    baseObj.isElementAttributeValueEqualTo(row.getCell(columnNames.get(1)), row.getCell(columnNames.get(2)), attributeName, row.getCell(columnNames.get(4)));
                    break;

                default: {
                    baseObj.printText("Implementation for \"" + attributeName + "\" attribute is not currently available");
                    break;
                }
            }
        }
    }

    @Step("Verify that the toast message is <toastMessage>")
    public void verifyToastMessage(String toastMessage) throws Exception {
        //Assert.assertTrue(ToastMessage.getToastMessage().contains(toastMessage), "Invalid Toast Message");
        Assert.assertEquals( ToastMessage.getToastMessageContent(), toastMessage, "Invalid toast message!" );
    }

    @Step("Wait <seconds> seconds")
    public void wait(int seconds) throws Exception {
        Thread.sleep( seconds * 1000 );
    }

    @Step("Verify that the page title is <pageTitle>")
    public void verifyPageTitle(String pageTitle) throws IOException {
        if (PLATFORM.equals( ANDROID )) {
//            baseObj.isPageTitleEqualTo(SETTINGS_PAGE, SETTINGS_TITLE_TEXTVIEW, pageTitle);
        }
    }

    @Step("Swipe the device screen horizontally right to left")
    public void swipeHorizontallyRightToLeft() throws InterruptedException {
        baseObj.swipeHorizontallyRightToLeft();
    }

    @Step("Swipe the device screen horizontally left to right")
    public void swipeHorizontallyLeftToRight() throws InterruptedException {
        baseObj.swipeHorizontallyLeftToRight();
    }

    @Step("Swipe the device screen vertically top to bottom")
    public void swipeVerticallyTopToBottom() throws InterruptedException {
        baseObj.swipeVerticallyTopToBottom();
    }

    @Step("Swipe the device screen vertically bottom to top")
    public void swipeVerticallyBottomToTop() throws InterruptedException {
        baseObj.swipeVerticallyBottomToTop();
    }

    @Step("Scroll down to the bottom of the screen")
    public void scrollDownToBottom() throws InterruptedException {
        baseObj.scrollDown();
    }

    @Step("Scroll to the text of <text>")
    public void scrollTo(String text) throws InterruptedException {
        baseObj.scrollTo( text );
    }

    @Step("Navigate back from the device")
    public void navigateBackFromDevice() throws InterruptedException {
        baseObj.navigateBackFromDevice();
    }

    @Step("Verify the webview contains <text>")
    public void isWebViewTextEquals(String text) throws InterruptedException {
        baseObj.isWebViewTextEquals( text );
    }

    @Step("Verify the webview contains the following text <table>")
    public void isWebViewTextEquals(Table table) throws InterruptedException {
        List<TableRow> rows = table.getTableRows();
        List<String> columnNames = table.getColumnNames();
        for (TableRow row : rows) {
            baseObj.isWebViewTextEquals( row.getCell( columnNames.get( 0 ) ) );
        }
    }

    @Step("Tap on <text>")
    public void tapOnVisibleText(String text) {
        baseObj.scrollAndClick( text );
    }

    @Step("Press Enter button on the Keyboard")
    public void pressKeyboardEnter() {
        baseObj.pressKeyboardEnter();
    }

    @Step("Hide keyboard")
    public void hideKeyboard() {
        baseObj.hideKeyboard();
    }

    @Step("Show keyboard")
    public void showKeyboard() {
        baseObj.hideKeyboard();
    }

    @Step("Quit from the application")
    public void quit() {
        DriverSetup.tearDown();
    }


}
