package com.poseidon.pta.repositories;

import com.poseidon.pta.domain.DomainElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class RepositoryFactory {
    @Autowired
    BidListRepository bidListRepository;
    @Autowired
    CurvePointRepository curvePointRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RuleNameRepository ruleNameRepository;
    @Autowired
    TradeRepository tradeRepository;

    public JpaRepository<? extends DomainElement, Integer> getRepository(String type){
        if (type.equals("bidList")) {
            return bidListRepository;
        }
        else if (type.equals("curvePoint")) {
            return curvePointRepository;
        }
        else if (type.equals("rating")) {
            return ratingRepository;
        }
        else if (type.equals("ruleName")) {
            return ruleNameRepository;
        }
        else {
            return tradeRepository;
        }
    }

}
