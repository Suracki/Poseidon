package com.poseidon.pta.services;

import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.repositories.TradeRepository;
import org.springframework.stereotype.Service;

/**
 * TradeService performs operations for the TradeController endpoints
 *
 * Extends BaseService<Trade>, passes a TradeRepository object to this super.
 * All functionality is contained within BaseService.
 */
@Service
public class TradeService extends BaseService<Trade> {
    public TradeService(TradeRepository tradeRepository) {
        super(tradeRepository);
    }
}
