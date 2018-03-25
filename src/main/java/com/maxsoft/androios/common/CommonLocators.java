package com.maxsoft.androios.common;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.text.ParseException;
import static com.maxsoft.androios.util.DriverSetup.androidDriver;
import static com.maxsoft.androios.util.DriverSetup.iosDriver;


/**
 * Created by Osanda on 3/25/2018.
 */


public class CommonLocators extends Base {

    public static final String COMMON_LOCATORS = "CommonLocators";
    public static final String TOOL_BAR = "toolbar";
    // Date Picker Elements
    public static final String LBL_DATE_PICKER_HEADER_YEAR = "lbl_date_picker_header_year";
    public static final String LBL_DATE_PICKER_HEADER_DATE = "lbl_date_picker_header_date";
    public static final String BTN_DATE_PICKER_PREVIOUS = "btn_date_picker_previous";
    public static final String BTN_DATE_PICKER_NEXT = "btn_date_picker_next";
    public static final String DAY_ELEMENT = "day_element";
    public static final String BTN_DATE_PICKER_OK = "btn_date_picker_ok";
    public static final String BTN_DATE_PICKER_CANCEL = "btn_date_picker_cancel";
    // Time Picker Elements
    public static final String HOUR_ELEMENT = "hour_element";
    public static final String MINUTE_ELEMENT = "minute_element";
    public static final String LBL_AM = "lbl_am";
    public static final String LBL_PM = "lbl_pm";
    public static final String BTN_TIMER_OK = "btn_timer_ok";
    public static final String BTN_TIMER_CANCEL = "btn_timer_cancel";

    public void setDateForAndroidDatePicker(String date) throws IOException, ParseException {
        int thisYear = Integer.valueOf(getElement(COMMON_LOCATORS, LBL_DATE_PICKER_HEADER_YEAR).getAttribute("name"));
        String today = getElement(COMMON_LOCATORS, LBL_DATE_PICKER_HEADER_DATE).getAttribute("name");
        int thisMonth = getMonthNumber(getMonthNameInThreeChars(today));

        // Split the given date into date, month and year
        String[] splitdate = date.split("\\s+");

        int givenDay = Integer.valueOf(splitdate[0]);
        int givenMonth = getMonthNumber(splitdate[1]);
        int givenYear = Integer.valueOf(splitdate[2]);

        int forwardTaps = 0;
        int backwardTaps = 0;
        int yearFactor = 0;

            if (givenYear == thisYear)
            {
                if (givenMonth >= thisMonth)
                {
                    forwardTaps = givenMonth - thisMonth;
                } else {
                    backwardTaps = thisMonth - givenMonth;
                }
            }
                else if (givenYear > thisYear)
                {
                    yearFactor = (givenYear - thisYear) * 12;
                        if (givenMonth >= thisMonth)
                        {
                            forwardTaps = yearFactor + (givenMonth - thisMonth);
                        } else {
                            forwardTaps = yearFactor - (thisMonth - givenMonth);
                        }
                }
                    else {
                        yearFactor = (thisYear - givenYear) * 12;
                            if (givenMonth >= thisMonth)
                            {
                                backwardTaps = yearFactor - (givenMonth - thisMonth);
                            } else {
                                backwardTaps = yearFactor + (thisMonth - givenMonth);
                            }
                    }

        for (int i=1; i<=forwardTaps; i++) {
            tapElement(COMMON_LOCATORS, BTN_DATE_PICKER_NEXT);
        }

        for (int i=1; i<=backwardTaps; i++) {
            tapElement(COMMON_LOCATORS, BTN_DATE_PICKER_PREVIOUS);
        }

        replaceXpathContentAndClickElement(COMMON_LOCATORS, DAY_ELEMENT, "day", String.valueOf(givenDay));
        tapElement(COMMON_LOCATORS, BTN_DATE_PICKER_OK);
    }

    public void setDateForIOSDatePicker(){
        ((IOSElement)iosDriver.findElements(By.className("UIAPickerWheel")).get(0)).sendKeys("Sun 25 Jan");
        MobileElement el1 = (MobileElement) getDriver().findElementByAccessibilityId("examDate");
        el1.click();
        MobileElement el2 = (MobileElement) getDriver().findElements(By.className("UIAPickerWheel")).get(0);
        el2.sendKeys("December");
        MobileElement el3 = (MobileElement) getDriver().findElementByXPath("//XCUIElementTypeApplication[@name=\"SmartFlashcards\"]/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[2]");
        el3.click();
        el3.sendKeys("30");
        MobileElement el4 = (MobileElement) getDriver().findElementByXPath("//XCUIElementTypeApplication[@name=\"SmartFlashcards\"]/XCUIElementTypeWindow[4]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeDatePicker/XCUIElementTypeOther/XCUIElementTypePickerWheel[3]");
        el4.sendKeys("2006");
    }

    public void setTimeForAndroidTimePicker(String time) throws IOException {
        String[] splitTime = time.split("\\s+");
        String[] t = splitTime[0].split(":");
        String hour = t[0];
        if (Integer.valueOf(hour) < 10) {
            hour = hour.substring(1);
        }
        String minutes = t[1];
        String amPm = splitTime[1];
        androidDriver.findElementByAccessibilityId(hour).click();
        androidDriver.findElementByAccessibilityId(minutes).click();
        if (amPm.toLowerCase().equals("am")){
            tapElement(COMMON_LOCATORS, LBL_AM);
        } else if (amPm.toLowerCase().equals("pm")) {
            tapElement(COMMON_LOCATORS, LBL_PM);
        }
        tapElement(COMMON_LOCATORS, BTN_TIMER_OK);
    }

    public WebElement getIOSPageTitleElement(String pageTitle) throws IOException {
        return getElementByReplacingXpathContent(COMMON_LOCATORS, TOOL_BAR, "pageTitle", pageTitle);
    }


}
