package com.poseidon.pta.services;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.repositories.BidListRepository;
import org.springframework.stereotype.Service;

/**
 * BidListService performs operations for the BidListController endpoints
 *
 * Extends BaseService<BidList>, passes a BidListRepository object to this super.
 * All functionality is contained within BaseService.
 */
@Service
public class BidListService extends BaseService<BidList>{
    public BidListService(BidListRepository bidListRepository) {
        super (bidListRepository);
    }
}
