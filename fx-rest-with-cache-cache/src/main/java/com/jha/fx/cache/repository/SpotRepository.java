package com.jha.fx.cache.repository;


import com.jha.fx.model.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Integer> {

    List<Spot> findByCurrencyPair(final String currencyPair);

    @Query("SELECT DISTINCT currencyPair FROM Spot")
    List<String> findDistinctByCurrencyPair();
}
