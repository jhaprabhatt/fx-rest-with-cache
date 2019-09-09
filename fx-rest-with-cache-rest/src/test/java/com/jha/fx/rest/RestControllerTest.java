package com.jha.fx.rest;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.jha.fx.model.Spot;
import com.jha.fx.rest.config.FxCacheClientConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FxRestController.class, secure = false)
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static HazelcastInstance hazelcastInstance;

    @Test
    public void test_get_price_by_currency_pair() throws Exception {

        RequestBuilder requestBuilder = getRequestBuilder("/fx/spotbycurrpair?currencyPair=USD/CAD");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(result.getResponse().getContentAsString());
        assertEquals(false, result.getResponse().getContentAsString().isEmpty());
        assertNotEquals(0, result.getResponse().getContentAsString().indexOf("USD/CAD"));
    }

    @Test
    public void test_get_price_by_currency_pair_no_parameter() throws Exception {

        RequestBuilder requestBuilder = getRequestBuilder("/fx/spotbycurrpair?currencyPair=");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(result.getResponse().getContentAsString());
        assertEquals(false, result.getResponse().getContentAsString().isEmpty());
        assertNotEquals(0, result.getResponse().getContentAsString().indexOf("Bad Request"));
    }

    @Test
    public void test_get_price_by_currency_pair_value_not_found() throws Exception {

        RequestBuilder requestBuilder = getRequestBuilder("/fx/spotbycurrpair?currencyPair=USD/JPY");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertNotNull(result.getResponse().getContentAsString());
        assertEquals(false, result.getResponse().getContentAsString().isEmpty());
        assertNotEquals(0, result.getResponse().getContentAsString().indexOf("Not Found"));
    }

    private RequestBuilder getRequestBuilder(String url) {
        return MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON);
    }

    @TestConfiguration
    static class TestContextConfiguration {

        @Bean
        public HazelcastInstance client() {
            final String currencyPair = "EUR/USD";
            final List data = new ArrayList<>();
            data.add(Spot.builder()
                    .id(1)
                    .currencyPair(currencyPair)
                    .buy(new BigDecimal(1.2234))
                    .sell(new BigDecimal(1.2236))
                    .priceOn(new Date()).build());

            Config config = new Config();
            config.setInstanceName("FxCacheTest");
            config.addMapConfig(
                    new MapConfig().setName(FxCacheClientConfig.FX_SPOT_CACHE)
            );
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            hazelcastInstance.getMap(FxCacheClientConfig.FX_SPOT_CACHE).put(currencyPair, data);
            return hazelcastInstance;
        }
    }
}
