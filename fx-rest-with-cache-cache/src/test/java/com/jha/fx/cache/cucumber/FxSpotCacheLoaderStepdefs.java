package com.jha.fx.cache.cucumber;

import com.jha.fx.cache.loader.FxSpotCacheLoader;
import com.jha.fx.model.Spot;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
public class FxSpotCacheLoaderStepdefs {

    @Autowired
    private FxSpotCacheLoader fxSpotCacheLoader;

    private List<Spot> prices = null;

    private Iterable<String> currencyPairs = null;

    private Map<String, List<Spot>> mapForKeys = null;

    private List<String> givenKeys = null;

    @When("I load data from cache loader for {string}")
    public void I_load_data_from_cache_loader_for_currency_pair(final String currencyPair) {
        prices = fxSpotCacheLoader.load(currencyPair);
        assertNotNull(prices);
        assertNotEquals(0, prices.size());
    }

    @Then("I should get a list of prices")
    public void I_should_get_a_list_of_prices() {
        assertNotNull(prices);
        assertNotEquals(0, prices.size());
    }

    @When("I load all keys")
    public void I_load_all_keys() {
        currencyPairs = fxSpotCacheLoader.loadAllKeys();
    }

    @Then("I should get all keys")
    public void I_should_get_all_keys() {
        assertNotNull(currencyPairs);
    }

    @Given("A list of keys:")
    public void a_list_of_keys(List<String> keys) {
        givenKeys = keys;
    }

    @When("I load all data for the given list of keys")
    public void I_load_all_data_for_a_collection_of_keys() {
        mapForKeys = this.fxSpotCacheLoader.loadAll(givenKeys);
    }

    @Then("I should get data for the given keys")
    public void I_should_get_data_for_the_given_keys() {
        assertNotNull(mapForKeys);
        assertEquals(1, mapForKeys.entrySet().size());
    }
}
