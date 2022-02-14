package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.repositories.BidListRepository;
import org.springframework.stereotype.Service;

@Service
public class BidListService extends BaseService<BidList>{
    public BidListService(BidListRepository bidListRepository) {
        super (bidListRepository);
    }
}
