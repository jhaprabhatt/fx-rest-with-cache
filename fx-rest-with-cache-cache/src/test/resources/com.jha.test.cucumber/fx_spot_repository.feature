@fxspotrest
Feature: Get Price by Currency Pair
  Scenario: Load Spot Price By Currency Pair
    Given None
    When I load data from repository for "EUR/USD"
    Then I should get a list of objects

  Scenario: Load distict currency pairs
    Given None
    When I load all distinct currency pairs
    Then I should get a list of string