package com.poseidon.pta.controllers;

import com.poseidon.pta.domain.Rating;
import com.poseidon.pta.services.RatingService;
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
 * RestController for /rating endpoint
 *
 */
@Controller
public class RatingController {
    @Autowired
    RatingService ratingService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    /**
     * Mapping for /list
     *
     * Calls ratingService.home method to populate model and get redirect for list
     *
     * @param model Model object to hold data loaded from repo
     * @return url string
     */
    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        logger.info("User connected to /rating/list endpoint");
        return ratingService.home(model);
    }

    /**
     * Mapping for GET /add
     *
     * Calls ratingService.addForm method to get redirect for form to add new Rating element
     *
     * @param rating Rating object
     * @return url string
     */
    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("User connected to /rating/add endpoint");
        return ratingService.addForm(rating);
    }

    /**
     * Mapping for POST /add
     *
     * Calls ratingService.validate method to validate provided Rating element
     * Adds element to repo if valid & updates model
     * Returns to add form if not valid
     *
     * @param rating Rating object
     * @param result BindingResult for validation
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        logger.info("User connected to /rating/validate endpoint");
        return ratingService.validate(rating, result, model);
    }

    /**
     * Mapping for GET /update/{id}
     *
     * Calls ratingService.showUpdateForm method to get redirect for form to update existing Rating element
     *
     * @param id Rating's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /rating/update/ GET endpoint for rating with id " + id);
        return ratingService.showUpdateForm(id, model);
    }

    /**
     * Mapping for POST /update/{id}
     *
     * Calls ratingService.update method to validate provided Rating element
     * Updates existing element in repo if valid & updates model
     * Returns to update form if not valid
     *
     * @param id Rating's ID value
     * @param model Model model object
     * @return url string
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        logger.info("User connected to /rating/update/ POST endpoint for rating with id " + id);
        return ratingService.update(id, rating, result, model);
    }

    /**
     * Mapping for GET /delete/{id}
     *
     * Calls ratingService.delete method to delete Rating element with provided ID
     * Deletes existing element in repo if exists & updates model
     *
     * @param id Rating's ID value
     * @param model Model model object
     * @return url string
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /rating/delete/ endpoint for rating with id " + id);
        return ratingService.delete(id, model);
    }
}
