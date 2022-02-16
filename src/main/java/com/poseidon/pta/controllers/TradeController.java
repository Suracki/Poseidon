package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.services.TradeService;
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
public class TradeController {
    @Autowired
    TradeService tradeService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        logger.info("User connected to /trade/list endpoint");
        return tradeService.home(model);
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade) {
        logger.info("User connected to /trade/add endpoint");
        return tradeService.addForm(trade);
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        logger.info("User connected to /trade/validate endpoint");
        return tradeService.validate(trade, result, model);
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /trade/update/ GET endpoint for trade with id " + id);
        return tradeService.showUpdateForm(id, model);
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        logger.info("User connected to /trade/update/ POST endpoint for trade with id " + id);
        return tradeService.update(id, trade, result, model);
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /trade/delete/ endpoint for trade with id " + id);
        return tradeService.delete(id, model);
    }
}
