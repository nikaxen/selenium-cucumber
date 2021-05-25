@predefined
  Feature: CK12 predefined steps test
    
    Background: User navigates to a homepage
      Given Navigate to "https://www.ck12.org/"
      
    Scenario: Verify home page title
      Then Page title is "Free Online Textbooks, Flashcards, Adaptive Practice, Real World Examples, Simulations"

    Scenario: Test sign in popup
      When Click on element with XPath "//span[@data-i18n='c.h.sign.in']"
      Then Element with XPath "//div[@class='row collapse sign-in-button-wrapper']" should be present

    Scenario: Find element inside the iframe
      When Click on element with XPath "//a[@class='goog-te-menu-value']"
      Then Element with XPath "//iframe[@title='Language Translate Widget']" should be present
      Then Switch to iframe with XPath "//iframe[@title='Language Translate Widget']"
      Then Element with XPath "//span[normalize-space()='Select Language']" should be present
      Then Switch to default content
      Then Element with XPath "//footer[@id='footer']" should be present
      
    Scenario: Make a search
      When Clear element with XPath "//input[@id='searchBox']"
      And Type "Math" into element with xpath "//input[@id='searchBox']"
      And Click on element with XPath "//div[@class='hide-for-small hide-for-medium sc-epnACN eyjVxf']//div//i[@title='Search']"
      Then Element with XPath "//span[@class='search-header-query']/span[2]" should contain text "Math"

    Scenario Outline: Google translate test (iframe)
      When Scroll to the element with XPath "//footer[@id='footer']" with offset 0
      When Click on element with XPath "//a[@class='goog-te-menu-value']"
      Then Element with XPath "//iframe[@title='Language Translate Widget']" should be present
      Then Switch to iframe with XPath "//iframe[@title='Language Translate Widget']"
      And Click on element with XPath "//span[normalize-space()='<language>']"
      Then Switch to default content
      And Wait for 3 sec
      Then Element with XPath "//a[@href='#science']//span" should contain text "<filters_word>"
      Examples:
        | language | filters_word |
        | Dutch    | Wetenschap   |
        | Hawaiian | ʻEpekema     |
        | Polish   | Nauka        |
        | Spanish  | Ciencias     |