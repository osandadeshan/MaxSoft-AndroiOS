package com.maxsoft.androios.common;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.DataStoreFactory;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.maxsoft.androios.util.DriverSetup;
import com.maxsoft.androios.util.FileReadWrite;
import com.maxsoft.androios.util.ReadLocatorsFromExcel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.*;
import java.util.List;

import static com.maxsoft.androios.util.DriverSetup.PROJECT_ROOT;
import static com.maxsoft.androios.util.DriverSetup.androidDriver;
import static com.maxsoft.androios.util.DriverSetup.iosDriver;
import static jdk.nashorn.internal.objects.Global.println;


/**
 * Created by Osanda on 5/14/2017.
 */


public class Base {

    public String PLATFORM = System.getenv("testing_platform");
    public final String LOCATORS_FILE_PATH = "\\" + System.getenv("locators_file_path");
    public final String ANDROID = "android";
    public final String IOS = "ios";
    public final String WEBVIEW = System.getenv("webview");
    public final String NATIVE_APP = System.getenv("native_app");
    private WebElement element;
    private Dimension size;
    List<WebElement> elements;
    List <String> elementNameList = new ArrayList();

    public WebElement getElement(String sheetName, String elementName) throws IOException {
        if (PLATFORM.toLowerCase().equals(IOS)) {
            switch (ReadLocatorsFromExcel.getIosHow(sheetName, elementName).toLowerCase()) {
                case "id":
                    element = iosDriver.findElement(By.id(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "xpath":
                    element = iosDriver.findElement(By.xpath(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "accessibilityId":
                    element = iosDriver.findElementByAccessibilityId(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName));
                    return element;
                case "classname":
                    element = iosDriver.findElement(By.className(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "cssselector":
                    element = iosDriver.findElement(By.cssSelector(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "linktext":
                    element = iosDriver.findElement(By.linkText(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "partiallinktext":
                    element = iosDriver.findElement(By.partialLinkText(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "name":
                    element = iosDriver.findElement(By.name(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
                case "tagname":
                    element = iosDriver.findElement(By.tagName(ReadLocatorsFromExcel.getIosLocator(sheetName, elementName)));
                    return element;
            }
        } else {
            switch (ReadLocatorsFromExcel.getAndroidHow(sheetName, elementName).toLowerCase()) {
                case "id":
                    element = androidDriver.findElement(By.id(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "xpath":
                    element = androidDriver.findElement(By.xpath(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "classname":
                    element = androidDriver.findElement(By.className(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "cssselector":
                    element = androidDriver.findElement(By.cssSelector(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "linktext":
                    element = androidDriver.findElement(By.linkText(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "partiallinktext":
                    element = androidDriver.findElement(By.partialLinkText(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "name":
                    element = androidDriver.findElement(By.name(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
                case "tagname":
                    element = androidDriver.findElement(By.tagName(ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName)));
                    return element;
            }
        }
        return null;
    }

    public AndroidDriver androidDriver(){
            return androidDriver;
    }

    public IOSDriver iosDriver(){
            return iosDriver;
    }

    public MobileDriver getDriver(){
        if(DriverSetup.PLATFORM.toLowerCase().equals(DriverSetup.IOS.toLowerCase())){
            return iosDriver();
        } else {
            return androidDriver();
        }
    }

    public String getLocatorFilePath(){
        return PROJECT_ROOT + LOCATORS_FILE_PATH.substring(1);
    }

    public void waitForElementClickable(String sheetName, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(getElement(sheetName, elementName)));
    }

    public void waitForElementVisible(String sheetName, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.visibilityOf(getElement(sheetName, elementName)));
    }

    public void tapElement(String sheetName, String elementName) throws IOException {
        waitForElementVisible(sheetName, elementName);
        getElement(sheetName, elementName).click();
    }

    public void setTextAs(String sheetName, String elementName, String text) throws IOException {
        waitForElementClickable(sheetName, elementName);
        getElement(sheetName, elementName).sendKeys(text);
    }

    public void clearText(String sheetName, String elementName) throws IOException {
        waitForElementClickable(sheetName, elementName);
        getElement(sheetName, elementName).clear();
    }

    public WebElement getElementByReplacingXpathContent(String sheetName, String elementName, String textToBeReplaced, String replacementText) throws IOException {
        String modifiedXpath = "";
        if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())){
            modifiedXpath = ReadLocatorsFromExcel.getIosLocator(sheetName, elementName).replace(textToBeReplaced, replacementText);
        } else {
            modifiedXpath = ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName).replace(textToBeReplaced, replacementText);
        }
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(modifiedXpath)));
        return getDriver().findElement(By.xpath(modifiedXpath));
    }

    public void replaceXpathContentAndClickElement(String sheetName, String elementName, String textToBeReplaced,String replacementText) throws IOException {
        String modifiedXpath = "";
        if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())){
            modifiedXpath = ReadLocatorsFromExcel.getIosLocator(sheetName, elementName).replace(textToBeReplaced, replacementText);
        } else {
            modifiedXpath = ReadLocatorsFromExcel.getAndroidLocator(sheetName, elementName).replace(textToBeReplaced, replacementText);
        }
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(modifiedXpath)));
        getDriver().findElement(By.xpath(modifiedXpath)).click();
    }

    public void hideKeyboard(){
        try {
            if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())){
                iosDriver.hideKeyboard();
            } else {
                androidDriver.hideKeyboard();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showKeyboard(){
        try {
            if (PLATFORM.toLowerCase().equals(IOS.toLowerCase())){
                iosDriver.getKeyboard();
            } else {
                androidDriver.getKeyboard();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void isPageTitleEqualTo(String sheetName, String elementName, String expectedPageTitle) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Page title locator is invalid.");
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("name"), expectedPageTitle, "Page title mismatched.");
    }

    public void isElementNameEqualTo(String sheetName, String elementName, String elementText) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Element cannot be found.");
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("name"), elementText, "Element text mismatched.");
    }

    public void isElementValueEqualTo(String sheetName, String elementName, String attributeValue) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Element cannot be found.");
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("value"), attributeValue, "Element's value is mismatched.");
    }

    public void isElementLabelEqualTo(String sheetName, String elementName, String attributeValue) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Element cannot be found.");
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("label"), attributeValue, "Element's value is mismatched.");
    }

    public void isElementAccessibilityIdEquals(String sheetName, String elementName, String elementText) throws IOException {
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("name"), elementText, "Element text mismatched.");
    }

    public void isElementTextEquals(String sheetName, String elementName, String elementText) throws IOException {
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute("text"), elementText, "Element text mismatched.");
    }

    public void isElementAttributeValueEqualTo(String sheetName, String elementName, String attributeName, String attributeValue) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Element cannot be found.");
        Assert.assertEquals(getElement(sheetName, elementName).getAttribute(attributeName), attributeValue, "Element's attribute \""+attributeName+"\" is mismatched.");
    }

    public void isButtonVisible(String sheetName, String elementName) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Button cannot be found.");
    }

    public void isButtonVisible(String sheetName, String elementName, String buttonText) throws IOException {
        if (PLATFORM.toLowerCase().equals(ANDROID.toLowerCase())) {
            isElementNameEqualTo(sheetName, elementName, buttonText);
        } else {
            isElementLabelEqualTo(sheetName, elementName, buttonText);
        }
    }

    public void isTextVisible(String sheetName, String elementName) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "Text cannot be found.");
    }

    public void isTextVisible(String sheetName, String elementName, String buttonText) throws IOException {
        if (PLATFORM.toLowerCase().equals(ANDROID.toLowerCase())) {
            isElementNameEqualTo(sheetName, elementName, buttonText);
        } else {
            isElementLabelEqualTo(sheetName, elementName, buttonText);
        }
    }

    public void isElementVisible(String sheetName, String elementName) throws IOException {
        Assert.assertTrue(getElement(sheetName, elementName).isDisplayed(), "\"" + elementName + "\"" + " Element cannot be found.");
    }

    public void isElementNotVisible(String sheetName, String elementName) throws IOException {
        try {
            Assert.assertFalse(getElement(sheetName, elementName).isDisplayed(), "\"" + elementName + "\"" + " Element has found");
            Assert.fail("\"" + elementName + "\"" + " Element has found");
        } catch (NoSuchElementException ex){
            ex.printStackTrace();
        }
    }

    public void isElementTextNotEquals(String sheetName, String elementName, String elementText) throws IOException {
        try{
            scrollTo(elementText);
            Assert.fail("\"" + elementText + "\"" + " Element has found");
        } catch (NoSuchElementException ex) {
            ex.printStackTrace();
        }
    }

    public static void saveValueForScenario(String variableName, String value) {
        // Adding value to the Data Store
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        scenarioStore.put(variableName, value);
    }

    public static String getSavedValueForScenario(String variableName) {
        // Fetching Value from the Data Store
        DataStore scenarioStore = DataStoreFactory.getScenarioDataStore();
        return (String) scenarioStore.get(variableName);
    }

    public void scrollDown() {
        Dimension size = getDriver().manage().window().getSize();
        int startY = (int) (size.height * 0.7);
        int endY = (int) (size.height * 0.2);
        int startX = size.height / 2;
        getDriver().swipe(startX, startY, startX, endY, 800);
    }

    public void scrollTo(String visibleText) {
        if(PLATFORM.toLowerCase().equals(ANDROID)) {
            androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
        }
    }

    public void isTextVisible(String visibleText) {
        if(PLATFORM.toLowerCase().equals(ANDROID)) {
            androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))");
        } else {
            iosDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name=\""+visibleText+"\"]"));
        }
    }

    public void scrollAndClick(String visibleText) {
        if(PLATFORM.toLowerCase().equals(ANDROID)) {
            androidDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
        } else {
            //iosDriver().findElementByIosUIAutomation("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").click();
            iosDriver().findElement(By.xpath("//XCUIElementTypeStaticText[@name=\""+visibleText+"\"]")).click();
        }
    }

    public void pressKeyboardEnter() {
        TouchAction touchAction = new TouchAction(androidDriver);
        touchAction.tap(750, 1150).perform();
    }

    public void isWebViewTextEquals(String text) {
        if(PLATFORM.toLowerCase().equals(ANDROID)) {
            Assert.assertTrue(androidDriver.findElement(By.xpath("//android.view.View[@content-desc=\""+ text +"\"]")).isDisplayed(), "\"" + text + "\" cannot be found in webview");
        } else {
            Assert.assertTrue(iosDriver.findElement(By.xpath("//android.view.View[@content-desc=\""+ text +"\"]")).isDisplayed(), "\"" + text + "\" cannot be found in webview");
        }
    }

    public void SwipeToElement(String sheetName, String elementName, int duration) throws IOException {
        int topY = getElement(sheetName, elementName).getLocation().getY();
        int bottomY = topY + getElement(sheetName, elementName).getSize().getHeight();
        int centerX = getElement(sheetName, elementName).getLocation().getX() + (getElement(sheetName, elementName).getSize().getWidth()/2);
        getDriver().swipe(centerX, bottomY, centerX, topY, duration);
    }

    public void swipeHorizontallyLeftToRight() throws InterruptedException {
        //Get the size of screen.
        size = getDriver().manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.
        int startx = (int) (size.width * 0.90);
        //Find endx point which is at left side of screen.
        int endx = (int) (size.width * 0.10);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

        //Swipe from Left to Right.
        getDriver().swipe(endx, starty, startx, starty, 500);
        freeze(2);
    }

    public void swipeHorizontallyRightToLeft() throws InterruptedException {
        //Get the size of screen.
        size = getDriver().manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startx point which is at right side of screen.
        int startx = (int) (size.width * 0.99);
        //Find endx point which is at left side of screen.
        int endx = (int) (size.width * 0.01);
        //Find vertical point where you wants to swipe. It is in middle of screen height.
        int starty = size.height / 2;
        System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

        //Swipe from Right to Left.
        getDriver().swipe(startx, starty, endx, starty, 500);
        freeze(2);
    }

    public void swipeVerticallyTopToBottom() throws InterruptedException {
        //Get the size of screen.
        size = getDriver().manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startY point which is at bottom side of screen.
        int startY = (int) (size.height * 0.90);
        //Find endY point which is at top side of screen.
        int endY = (int) (size.height * 0.10);
        //Find horizontal point where you wants to swipe. It is in middle of screen width.
        int startX = size.width / 2;
        System.out.println("startY = " + startY + " ,endY = " + endY + " , startX = " + startX);

        //Swipe from Top to Bottom.
        getDriver().swipe(startX, endY, startX, startY, 500);
        freeze(2);
    }

    public void swipeVerticallyBottomToTop() throws InterruptedException {
        //Get the size of screen.
        size = getDriver().manage().window().getSize();
        System.out.println(size);

        //Find swipe start and end point from screen's with and height.
        //Find startY point which is at bottom side of screen.
        int startY = (int) (size.height * 0.90);
        //Find endY point which is at top side of screen.
        int endY = (int) (size.height * 0.10);
        //Find horizontal point where you wants to swipe. It is in middle of screen width.
        int startX = size.width / 2;
        System.out.println("startY = " + startY + " ,endY = " + endY + " , startX = " + startX);

        //Swipe from Bottom to Top.
        getDriver().swipe(startX, startY, startX, endY, 500);
        freeze(2);
    }

    public List<WebElement> getWebElements(String classNameOfElementList, String elementId){
        if (PLATFORM.toLowerCase().equals(ANDROID)){
            elements = androidDriver.findElementByClassName(classNameOfElementList).findElements(By.id(elementId));
            return elements;
        } else {
            elements = iosDriver.findElementByClassName(classNameOfElementList).findElements(By.id(elementId));
            return elements;
        }
    }

    public void getWebElementsNameList(String classNameOfElementList, String elementId){
        System.out.println("Items found:");
        Gauge.writeMessage("Items found:");
        int i = 1;
        for(WebElement element : getWebElements(classNameOfElementList, elementId)) {
            System.out.println(i + ") " + element.getText());
            Gauge.writeMessage(i + ") " + element.getText());
            elementNameList.add(element.getText());
            i++;
        }
    }

    public void addElementNamesToList(List<WebElement> webElementList, List <String> listToAddElementNames){
        for(WebElement element : webElementList) {
            listToAddElementNames.add(element.getText());
        }
    }

    public void isElementEnable(String sheetName, String elementName, boolean isEnable) throws IOException {
        if (getElement(sheetName, elementName).isEnabled() == Boolean.TRUE) {
            System.out.println("Element is enabled");
            Gauge.writeMessage("Element is enabled");
        } else {
            System.out.println("Element is disabled");
            Gauge.writeMessage("Element is disabled");
        }
        Assert.assertEquals(getElement(sheetName, elementName).isEnabled(), isEnable, "The actual enable/disable status of the element is not match with the expected status.");
    }

    public void isRadioButtonActive(String sheetName, String elementName, String textToBeReplaced, String replacementText, Boolean expectedStatus) throws IOException {
        Assert.assertEquals(getElementByReplacingXpathContent(sheetName, elementName, textToBeReplaced, replacementText).getAttribute("checked").toLowerCase(), expectedStatus.toString(), "Radio button's active/en-active status is differ from the expected.");
    }

    public String getHTMLPageSource(){
        switchContextTo(WEBVIEW);
        return getDriver().getPageSource();
    }

    public String getHTMLPageSource(String url){
        switchContextTo(WEBVIEW);
        getDriver().navigate().to(url);
        return getDriver().getPageSource();
    }

    public void saveHTMLPageSource(String filePath){
        String FILE_PATH = DriverSetup.PROJECT_ROOT.concat(filePath);
        FileReadWrite.writeToFile(getHTMLPageSource().replaceAll("’", "'").replaceAll("é", "e"), FILE_PATH);
    }

    public void getContextNames(){
        Set<String> contextNames = getDriver().getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextNames); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
    }

    public void switchContextTo(String context){
        if (context.toLowerCase().equals(WEBVIEW.toLowerCase())) {
            getDriver().context(WEBVIEW); // set context to WEBVIEW_1
        } else {
            getDriver().context(NATIVE_APP); // set context to NATIVE_APP
        }
    }

    // Pass monthName param as "August"
    public int getMonthNumber(String monthName) throws ParseException {
        Date date = new SimpleDateFormat("MMMM").parse(monthName);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.get(Calendar.MONTH) + 1);
        return calendar.get(Calendar.MONTH) + 1;
    }

    // Pass date param as "Sun, Jul 1"
    public String getMonthNameInThreeChars(String date) {
        return date.substring(5, 8);
    }

    public void freeze(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigateBackFromDevice(){
        if (PLATFORM.toLowerCase().equals(ANDROID)) {
            androidDriver.pressKeyCode(AndroidKeyCode.BACK);
        } else {
            iosDriver.navigate().back();
        }
    }

    public void printText(String text){
        System.out.println(text);
        Gauge.writeMessage(text);
    }


}
