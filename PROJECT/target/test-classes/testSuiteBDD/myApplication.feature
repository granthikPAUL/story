Feature: Validating the functionality of Risk Module in Mainspring Application

  @smoke @regression
  Scenario: To Open the Browser
    Given Driver Setup
    When Open Homepage
   Then Open Project
   Then Click SubmitButton
   Then Save Report
   Then Close Browser
