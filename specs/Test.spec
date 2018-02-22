Login Specification
===================
Date Created    : 02/19/2018
Version   		: 1.0.0
Owner      		: Osanda Deshan
Description  	: This is an executable specification file which follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.


tags: login



Targeted platform information
-----------------------------
* Testing platform information



Verify that the Login page contains all the expected elements
-------------------------------------------------------------
* Validate the elements' visibility on the screen
    |Step Name                                              |Sheet Name             |Element Name       |Is Visible?        |
    |-------------------------------------------------------|-----------------------|-------------------|-------------------|
    |Username should be visible                             |LoginPage              |txt_username       |y                  |
    |Password should be visible                             |LoginPage              |txt_password       |y                  |
    |Sign in button should be visible                       |LoginPage              |btn_sign_in        |y                  |
    |Create account button should be visible                |LoginPage              |btn_create_account |y                  |



Scenario Heading
----------------
* Set the text as
    |Step Name                                              |Sheet Name             |Element Name       |Text               |
    |-------------------------------------------------------|-----------------------|-------------------|-------------------|
    |Set username as                                        |LoginPage              |txt_username       |osanda12           |
    |Set password as                                        |LoginPage              |txt_password       |Password1          |
* Tap the element
    |Step Name                                              |Sheet Name             |Element Name       |
    |-------------------------------------------------------|-----------------------|-------------------|
    |Tap on Sign in button                                  |LoginPage              |btn_sign_in        |