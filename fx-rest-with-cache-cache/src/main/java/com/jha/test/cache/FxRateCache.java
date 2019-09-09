package com.jha.test.cache;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class FxRateCache {
    private HazelcastInstance hazelcastInstance;

    public FxRateCache(@Autowired Config hazelCastConfig) {
        this.hazelcastInstance = Hazelcast.newHazelcastInstance(hazelCastConfig);
    }

    public int cacheCount(final String mapName) {
        return this.hazelcastInstance.getMap(mapName).size();
    }


    @PreDestroy
    public void destroy() {
        if (this.hazelcastInstance != null) {
            this.hazelcastInstance.shutdown();
        }
    }
}
