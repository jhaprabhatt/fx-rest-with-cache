package com.jha.fx.rest;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.jha.fx.model.Spot;
import com.jha.fx.rest.config.FxCacheClientConfig;
import com.jha.fx.rest.exception.ValueNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/fx")
public class FxRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxRestController.class);

    @Autowired
    private HazelcastInstance client;

    @GetMapping(value = "/spotbycurrpair", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Spot> getSpotPriceByCurrencyPair(@RequestParam("currencyPair") final String currencyPair) {
        LOGGER.info("Recevied requet with currencypair : {}  ", currencyPair);
        if (StringUtils.isEmpty(currencyPair)) {
            LOGGER.warn("Invalid request");
            throw new IllegalArgumentException("Invalid currency pair");
        }
        if (currencyPair.split("/").length != 2) {
            LOGGER.warn("Invalid request");
            throw new IllegalArgumentException("Did you forget to place quote currency ?");
        }
        IMap<String, List<Spot>> priceMap = this.client.getMap(FxCacheClientConfig.FX_SPOT_CACHE);

        if (Objects.isNull(priceMap) || !priceMap.containsKey(currencyPair) || priceMap.get(currencyPair).size() == 0) {
            LOGGER.warn("Unable to full fill request, currency pair not found. Received currency pair : {} ", currencyPair);
            throw new ValueNotFoundException("No record found for the given currency pair");
        }
        return priceMap.get(currencyPair);
    }
}
