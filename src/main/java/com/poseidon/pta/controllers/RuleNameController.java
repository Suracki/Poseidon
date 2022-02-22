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

/**
 * RestController for /ruleName endpoint
 *
 */
@Controller
public class RuleNameController {
    @Autowired
    RuleNameService ruleNameService;

    private static final Logger logger = LogManager.getLogger("RuleNameController");

    /**
     * Mapping for /list
     *
     * Calls ruleNameService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        logger.info("User connected to /ruleName/list endpoint");
        return ruleNameService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls ruleNameService.addForm method to get redirect for form to add new RuleName element
     *
     * @param ruleName RuleName object
     * @return url string
     */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName ruleName) {
        logger.info("User connected to /ruleName/add endpoint");
        return ruleNameService.addForm(ruleName);
    }

    /**
     * Mapping for POST /add
     *
     * Calls ruleNameService.validate method to validate provided RuleName element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param ruleName RuleName object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        logger.info("User connected to /ruleName/validate endpoint");
        return ruleNameService.validate(ruleName,result,model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls ruleNameService.showUpdateForm method to get redirect for form to update existing RuleName element
     *
     * @param id RuleName's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /ruleName/update/ GET endpoint for ruleName with id " + id);
        return ruleNameService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls ruleNameService.update method to validate provided RuleName element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id RuleName's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        logger.info("User connected to /ruleName/update/ POST endpoint for ruleName with id " + id);
        return ruleNameService.update(id, ruleName, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls ruleNameService.delete method to delete RuleName element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id RuleName's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /ruleName/delete/ endpoint for ruleName with id " + id);
        return ruleNameService.delete(id, model);
    }
}
