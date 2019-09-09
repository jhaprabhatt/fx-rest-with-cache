package com.jha.fx.cache.loader;

import com.jha.fx.CacheApplication;
import com.jha.fx.cache.loader.FxSpotCacheLoader;
import com.jha.fx.model.Spot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CacheApplication.class)
@ActiveProfiles("tests")
public class TestFxCacheLoader {
    @Autowired
    private FxSpotCacheLoader fxSpotCacheLoader;

    @Test
    public void load() {
        List<Spot> data = this.fxSpotCacheLoader.load("EUR/USD");
        assertNotNull(data);
        assertEquals(422, data.size());
    }

    @Test
    public void loadAll() {
        Map<String, List<Spot>> data = this.fxSpotCacheLoader.loadAll(Stream.of("EUR/USD").collect(Collectors.toList()));
        assertNotNull(data);
    }

    @Test
    public void loadAllKeys() {
        Iterable<String> keys = this.fxSpotCacheLoader.loadAllKeys();
        assertNotNull(keys);
    }
}
