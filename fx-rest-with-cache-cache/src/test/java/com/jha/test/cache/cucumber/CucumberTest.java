package com.jha.test.cache.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"}
        , features = {"classpath:com.jha.test.cucumber"}
        , glue = {"com.jha.test.cache.cucumber"}
        , tags = "@fxspotrest")
public class CucumberTest {
}
