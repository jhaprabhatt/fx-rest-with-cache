package com.jha.fx.cache.config;

import com.hazelcast.config.*;
import com.jha.fx.cache.utils.CacheUtils;
import com.jha.fx.cache.loader.FxSpotCacheLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FxCacheServerConfiguration {

    @Autowired
    FxSpotCacheLoader fxSpotCacheLoader;

    @Bean
    public Config getHazelCastConfig() {
        Config config = new Config();
        config.setInstanceName("FxCache");
        NetworkConfig networkConfig = config.getNetworkConfig();
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled(false);
        config.addMapConfig(createFxSpotMapConfig());
        config.setSecurityConfig(
                new SecurityConfig().setEnabled(false)
        );
        return config;
    }

    private MapConfig createFxSpotMapConfig() {
        MapConfig mapConfig = new MapConfig().setName(CacheUtils.FX_SPOT_CACHE).setEvictionPolicy(EvictionPolicy.NONE).setTimeToLiveSeconds(-1);
        mapConfig.setMapStoreConfig(getMapStoreConfig());
        return mapConfig;
    }

    private MapStoreConfig getMapStoreConfig() {
        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setEnabled(true);
        mapStoreConfig.setImplementation(fxSpotCacheLoader);
        mapStoreConfig.setInitialLoadMode(MapStoreConfig.InitialLoadMode.EAGER);
        return mapStoreConfig;
    }
}
