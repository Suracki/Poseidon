package com.poseidon.pta.repositories;

import com.poseidon.pta.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
