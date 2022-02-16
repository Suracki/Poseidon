package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.BidList;
import com.poseidon.pta.services.BidListService;
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
 * Controller for /bidList endpoint
 *
 */
@Controller
public class BidListController {
    @Autowired
    private BidListService bidListService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    /**
     * Mapping for /list
     *
     * Calls bidListService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("User connected to /bidList/list endpoint");
        return bidListService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls bidListService.addForm method to get redirect for form to add new BidList element
     *
     * @param bid BidList object
     * @return url string
     */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("User connected to /bidList/add endpoint");
        return bidListService.addForm(bid);
    }

    /**
     * Mapping for POST /add
     *
     * Calls bidListService.validate method to validate provided bidList element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param bid BidList object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        logger.info("User connected to /bidList/validate endpoint");
        return bidListService.validate(bid, result, model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls bidListService.showUpdateForm method to get redirect for form to update existing BidList element
     *
     * @param id BidList's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /bidList/update/ GET endpoint for bidList with id " + id);
        return bidListService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls bidListService.update method to validate provided bidList element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id BidList's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        logger.info("User connected to /bidList/update/ POST endpoint for bidList with id " + id);
        return bidListService.update(id, bidList, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls bidListService.delete method to delete BidList element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id BidList's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /bidList/delete/ endpoint for bidList with id " + id);
        return bidListService.delete(id, model);
    }
}
