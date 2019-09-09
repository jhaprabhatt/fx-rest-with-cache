package com.jha.test.cache;

import com.hazelcast.core.IMap;
import com.jha.test.model.Spot;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestMockedCache {

    private static IMap<String, List<Spot>> mockedCache = mock(IMap.class);

    @BeforeClass
    public static void prepareData() {
        final String currencyPair = "EUR/USD";
        final List<Spot> data = new ArrayList<>();
        data.add(Spot.builder()
                .id(1)
                .currencyPair("EUR/USD")
                .buy(new BigDecimal(1.2234))
                .sell(new BigDecimal(1.2236))
                .priceOn(new Date()).build());
        when(mockedCache.get(currencyPair)).thenReturn(data);
    }

    @Test
    public void test_map_get_data() {
        List<Spot> data = mockedCache.get("EUR/USD");
        assertNotNull(data);
        assertEquals(1, data.size());
    }
}
