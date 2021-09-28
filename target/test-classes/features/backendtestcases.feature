Feature: Backend validation
  I want to use this template for my feature file

 @tag1
 Scenario: Retrieve the id Bitcoin(BTC), usd tether (USDT), and Ethereum (ETH)and convert into BOB
   Given user is on marketcap page
   When user hits cryptocurrencymap  call to get id of "BTC" and "USDT" and "ETH"
   Then user and convert them into "BOB" using price-conversion

  @tag1
  Scenario: Retrieve the etherium(ETH) technical documentation
   Given user is on marketcap page
    When user hits cryptocurrencyinfo  call to get info of ETH
    Then validate the logo, technicaldoc,symbol,date, tag
    
    
Scenario: Retrieve the info of first ten currencies 
    Given user is on marketcap page
    When user hits cryptocurrencyinfo  call to get info of first ten
    Then validate the tag and correct symbol associated with them