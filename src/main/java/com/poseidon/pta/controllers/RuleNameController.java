package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.RuleName;
import com.poseidon.pta.services.RuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RuleNameController {
    @Autowired
    RuleNameService ruleNameService;

    private static final Logger logger = LogManager.getLogger("RuleNameController");

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        logger.info("User connected to /ruleName/list endpoint");
        return ruleNameService.home(model);
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        logger.info("User connected to /ruleName/add endpoint");
        return ruleNameService.addForm(ruleName);
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        logger.info("User connected to /ruleName/validate endpoint");
        return ruleNameService.validate(ruleName,result,model);
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /ruleName/update/ GET endpoint for ruleName with id " + id);
        return ruleNameService.showUpdateForm(id, model);
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        logger.info("User connected to /ruleName/update/ POST endpoint for ruleName with id " + id);
        return ruleNameService.update(id, ruleName, result, model);
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /ruleName/delete/ endpoint for ruleName with id " + id);
        return ruleNameService.delete(id, model);
    }
}
