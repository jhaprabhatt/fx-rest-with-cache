package com.jha.test.rest;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import static com.jha.test.rest.config.FxCacheClientConfig.FX_SPOT_CACHE;

@SpringBootApplication(exclude = {HazelcastAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class RestApplication {

    @Autowired
    private HazelcastInstance client;
    private static final Logger LOGGER = LoggerFactory.getLogger(RestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("Started cache with near cache keys {} : ", client.getMap(FX_SPOT_CACHE).size());
    }
}
