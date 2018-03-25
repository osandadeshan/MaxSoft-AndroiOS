Create My Deck Specification
============================
Date Created    : 03/25/2018
Version   		: 1.0.0
Owner      		: Osanda Deshan
Description  	: This is an executable specification file which follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.


tags: create_my_deck



Navigate to Create My Deck page
-------------------------------
* Tap the element
    |Step Name                                              |Sheet Name             |Element Name                    |
    |-------------------------------------------------------|-----------------------|--------------------------------|
    |Tap on "My Decks" button on the navigation bar         |NavigationBar          |my_decks_nav_bar_icon           |
* Validate the elements' attributes on the screen
    |Step Name                                              |Sheet Name             |Element Name       |Attribute Name   |Expected Attribute Value     |
    |-------------------------------------------------------|-----------------------|-------------------|-----------------|-----------------------------|
    |Page title is "My Decks"                               |CommonLocators         |toolbar            |name             |My Decks                     |
* Tap the element
    |Step Name                                              |Sheet Name             |Element Name                    |
    |-------------------------------------------------------|-----------------------|--------------------------------|
    |Tap on Create My Deck button                           |MyDecksPage            |create_new_deck_button          |
* Validate the elements' attributes on the screen
    |Step Name                                              |Sheet Name             |Element Name       |Attribute Name   |Expected Attribute Value     |
    |-------------------------------------------------------|-----------------------|-------------------|-----------------|-----------------------------|
    |Page title is "Create Deck"                            |CommonLocators         |toolbar            |name             |Create Deck                  |



Verify that a new deck can be added
-----------------------------------
* Tap the element
    |Step Name                                              |Sheet Name             |Element Name                    |
    |-------------------------------------------------------|-----------------------|--------------------------------|
    |Tap on Exam Date Picker                                |CreateNewDeckPage      |exam_date_date_picker           |
* Set date as "27 January 2019"
* Tap the element
    |Step Name                                              |Sheet Name             |Element Name                    |
    |-------------------------------------------------------|-----------------------|--------------------------------|
    |Tap on Exam Time Picker                                |CreateNewDeckPage      |timer_icon                      |
* Set time as "07:35 PM"