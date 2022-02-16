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

@Controller
public class RatingController {
    @Autowired
    RatingService ratingService;

    private static final Logger logger = LogManager.getLogger("BidListController");

    @RequestMapping("/rating/list")
    public String home(Model model)
    {
        logger.info("User connected to /rating/list endpoint");
        return ratingService.home(model);
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        logger.info("User connected to /rating/add endpoint");
        return ratingService.addForm(rating);
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        logger.info("User connected to /rating/validate endpoint");
        return ratingService.validate(rating, result, model);
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /rating/update/ GET endpoint for rating with id " + id);
        return ratingService.showUpdateForm(id, model);
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        logger.info("User connected to /rating/update/ POST endpoint for rating with id " + id);
        return ratingService.update(id, rating, result, model);
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        logger.info("User connected to /rating/delete/ endpoint for rating with id " + id);
        return ratingService.delete(id, model);
    }
}
