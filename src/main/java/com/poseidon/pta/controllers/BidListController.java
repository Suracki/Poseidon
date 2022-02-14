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


@Controller
public class BidListController {
    @Autowired
    private BidListService bidListService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("User connected to /bidList/list endpoint");
        return bidListService.home(model);
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("User connected to /bidList/add endpoint");
        return bidListService.addForm(bid);
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        logger.info("User connected to /bidList/validate endpoint");
        return bidListService.validate(bid, result, model);
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /bidList/update/ GET endpoint for bidList with id " + id);
        return bidListService.showUpdateForm(id, model);
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        logger.info("User connected to /bidList/update/ POST endpoint for bidList with id " + id);
        return bidListService.update(id, bidList, result, model);
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /bidList/delete/ endpoint for bidList with id " + id);
        return bidListService.delete(id, model);
    }
}
