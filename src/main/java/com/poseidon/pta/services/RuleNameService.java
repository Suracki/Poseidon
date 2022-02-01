package com.poseidon.pta.services;

import com.poseidon.pta.domain.RuleName;
import com.poseidon.pta.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class RuleNameService extends BaseService<RuleName>{
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public RuleNameService() {
        super("ruleName");
    }

    public String update(Integer id, RuleName ruleName,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }

        ruleName.setId(id);
        ruleNameRepository.save(ruleName);
        model.addAttribute("ruleNames", ruleNameRepository.findAll());
        return "redirect:/ruleName/list";
    }
}