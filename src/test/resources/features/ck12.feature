@ck12_feature
  Feature: Some CK12 tests

    Background: Go to homepage
      Given Navigate to the "Main" page

    Scenario: Verify title of a home page
      Then Page title is "Free Online Textbooks, Flashcards, Adaptive Practice, Real World Examples, Simulations"

    Scenario: Verify 'SIGN IN' popup opens by clicking on the link in header
      When I click on the sign in button in the header
      Then Verify Sign In popup should be visible

    Scenario: Find language inside the Google Translate iframe
      When Click on the Google Translate widget button
      Then Google Translate iframe should be displayed
      Then Switch to the Google Translate iframe
      Then Verify "Select Language" text presents
      Then Switch to default content
      Then Verify Footer presents on a page

    Scenario Outline: Make a search
      When I type "<query>" to the search field in header
      And I click on the search button in header
      Then Verify search query header contains text "<query>"
      Examples:
        | query |
        | Math  |

    Scenario Outline: Google Translate widget (iframe) test
      When Scroll to the footer with offset 0
      Then Click on the Google Translate widget button
      And Switch to the Google Translate iframe
      Then Set the language "<language>" in the Google Translate iframe
      Then Switch to default content
      And Wait for 3 sec
      Then Verify Science span contains text "<text>"
      Examples:
        | language | text         |
        | Dutch    | Wetenschap   |
        | Hawaiian | ʻEpekema     |
        | Polish   | Nauka        |
        | Spanish  | Ciencias     |