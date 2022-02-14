package com.poseidon.pta.services;

import com.poseidon.pta.domain.RuleName;
import com.poseidon.pta.repositories.RuleNameRepository;
import org.springframework.stereotype.Service;

@Service
public class RuleNameService extends BaseService<RuleName>{
    public RuleNameService(RuleNameRepository ruleNameRepository) {
        super(ruleNameRepository);
    }
}