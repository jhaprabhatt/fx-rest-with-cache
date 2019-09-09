package com.jha.fx.rest.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxCacheClientConfig {
    public static final String FX_SPOT_CACHE = "FX_SPOT_CACHE";

    @Bean
    public HazelcastInstance getClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.getNetworkConfig().addAddress("localhost:5701");
        clientConfig.getNetworkConfig().setConnectionAttemptLimit(500);
        clientConfig.addNearCacheConfig(getNearCacheConfig());
        GroupConfig groupConfig = clientConfig.getGroupConfig();
        groupConfig.setName("dev");
        groupConfig.setPassword("dev-pass");
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

    private NearCacheConfig getNearCacheConfig() {
        NearCacheConfig nearCacheConfig = new NearCacheConfig()
                .setEvictionConfig(new EvictionConfig())
                .setName(FX_SPOT_CACHE);
        return nearCacheConfig;
    }
}
