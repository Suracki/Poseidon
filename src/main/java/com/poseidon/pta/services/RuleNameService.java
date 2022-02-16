package com.poseidon.pta.services;

import com.poseidon.pta.domain.RuleName;
import com.poseidon.pta.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

/**
 * RuleNameService performs operations for the RuleNameController endpoints
 *
 * Extends BaseService<RuleName>, passes a RuleNameRepository object to this super.
 * All functionality is contained within BaseService.
 */
@Service
public class RuleNameService extends BaseService<RuleName>{
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        super(ruleNameRepository);
    }
}