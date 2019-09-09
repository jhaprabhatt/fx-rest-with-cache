package com.jha.test;

import com.jha.test.cache.FxRateCache;
import com.jha.test.cache.utils.CacheUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = {HazelcastAutoConfiguration.class})
public class CacheApplication {
    @Autowired
    FxRateCache fxRateCache;

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        LOGGER.info("Started cache with : {}", fxRateCache.cacheCount(CacheUtils.FX_SPOT_CACHE));
    }
}
