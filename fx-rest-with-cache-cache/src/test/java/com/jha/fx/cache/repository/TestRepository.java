package com.jha.fx.cache.repository;

import com.jha.fx.CacheApplication;
import com.jha.fx.cache.repository.SpotRepository;
import com.jha.fx.model.Spot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CacheApplication.class)
@ActiveProfiles("tests")
public class TestRepository {

    @Autowired
    private SpotRepository spotRepository;


    @Test
    public void test_repo_get_by_currency_pair() {
        List<Spot> data = spotRepository.findByCurrencyPair("EUR/USD");
        assertNotNull(data);
        assertEquals(422, data.size());
    }

    @Test
    public void test_repo_get_currency_pairs() {
        List<String> keys = spotRepository.findDistinctByCurrencyPair();
        assertNotNull(keys);
        assertEquals(2, keys.size());
    }
}
