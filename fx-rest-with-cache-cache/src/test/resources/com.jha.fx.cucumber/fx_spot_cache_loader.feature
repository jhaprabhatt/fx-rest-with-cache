@fxspotrest
Feature: Load data for cache
  Scenario: Load Spot Price By Currency Pair
    Given None
    When I load data from cache loader for "EUR/USD"
    Then I should get a list of prices

  Scenario: Load all data
    Given None
    When I load all keys
    Then I should get all keys

  Scenario: Load all data for a collection of keys
    Given A list of keys:
      | EUR/USD |
    When I load all data for the given list of keys
    Then I should get data for the given keys
