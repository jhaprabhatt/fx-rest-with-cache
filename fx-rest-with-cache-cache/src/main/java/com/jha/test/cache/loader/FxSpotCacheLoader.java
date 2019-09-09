package com.jha.test.cache.loader;

import com.hazelcast.core.MapLoader;
import com.jha.test.cache.repository.SpotRepository;
import com.jha.test.model.Spot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FxSpotCacheLoader implements MapLoader<String, List<Spot>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxSpotCacheLoader.class);

    @Autowired
    private SpotRepository spotRepository;

    @Override
    public List<Spot> load(String key) {
        LOGGER.info("Loading for key {} : " , key);
        return this.spotRepository.findByCurrencyPair(key);
    }

    @Override
    public Map<String, List<Spot>> loadAll(Collection<String> keys) {
        LOGGER.info("Loading all for keys {} : " , keys);
        Map<String, List<Spot>> returnVal = this.spotRepository
                .findAll()
                .stream()
                .filter(spot -> keys.contains(spot.getCurrencyPair()))
                .collect(Collectors.groupingBy(Spot::getCurrencyPair)
                );
        return returnVal;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        LOGGER.info("Loading all keys : ");
        return this.spotRepository.findDistinctByCurrencyPair();
    }
}
