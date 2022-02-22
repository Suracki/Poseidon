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

/**
 * RestController for /trade endpoint
 *
 */
@Controller
public class TradeController {
    @Autowired
    TradeService tradeService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    /**
     * Mapping for /list
     *
     * Calls tradeService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        logger.info("User connected to /trade/list endpoint");
        return tradeService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls tradeService.addForm method to get redirect for form to add new Trade element
     *
     * @param trade Trade object
     * @return url string
     */
    @GetMapping("/trade/add")
    public String addTradeForm(Trade trade) {
        logger.info("User connected to /trade/add endpoint");
        return tradeService.addForm(trade);
    }

    /**
     * Mapping for POST /add
     *
     * Calls tradeService.validate method to validate provided Trade element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param trade Trade object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        logger.info("User connected to /trade/validate endpoint");
        return tradeService.validate(trade, result, model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls tradeService.showUpdateForm method to get redirect for form to update existing Trade element
     *
     * @param id Trade's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /trade/update/ GET endpoint for trade with id " + id);
        return tradeService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls tradeService.update method to validate provided Trade element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id Trade's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        logger.info("User connected to /trade/update/ POST endpoint for trade with id " + id);
        return tradeService.update(id, trade, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls tradeService.delete method to delete Trade element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id Trade's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /trade/delete/ endpoint for trade with id " + id);
        return tradeService.delete(id, model);
    }
}
