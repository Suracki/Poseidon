package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.Trade;
import com.poseidon.pta.services.TradeService;
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

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        return tradeService.home(model);
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade) {
        return tradeService.addForm(trade);
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        return tradeService.validate(trade, result, model);
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        return tradeService.showUpdateForm(id, model);
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        return tradeService.update(id, trade, result, model);
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        return tradeService.delete(id, model);
    }
}
