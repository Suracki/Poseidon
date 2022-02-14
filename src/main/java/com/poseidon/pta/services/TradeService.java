package com.poseidon.pta.services;

import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.repositories.TradeRepository;
import org.springframework.stereotype.Service;


@Service
public class TradeService extends BaseService<Trade> {
    public TradeService(TradeRepository tradeRepository) {
        super(tradeRepository);
    }
}
