package com.jha.test.cache.cucumber;

import com.jha.test.cache.repository.SpotRepository;
import com.jha.test.model.Spot;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class FxSpotRepositoryStepdefs extends CucumberSpringIntegration{

    @Autowired
    private SpotRepository spotRepository;

    private List<Spot> prices = null;

    private List<String> currencyPairs = null;

    @Given("^None$")
    public void None() {

    }


    @When("I load data from repository for {string}")
    public void I_load_data_from_repository_for_currency_pair(final String currencyPair) {
        prices = spotRepository.findByCurrencyPair(currencyPair);
        assertNotNull(prices);
        assertNotEquals(0, prices.size());
    }

    @Then("I should get a list of objects")
    public void I_should_get_a_list_of_objects() {
        assertNotNull(prices);
        assertNotEquals(0, prices.size());
    }

    @When("I load all distinct currency pairs")
    public void I_load_all_distinct_currency_pairs() {
        currencyPairs = spotRepository.findDistinctByCurrencyPair();
    }

    @Then("I should get a list of string")
    public void I_should_get_a_list_of_string() {
        assertNotNull(currencyPairs);
        assertNotEquals(0, currencyPairs.size());
    }
}
