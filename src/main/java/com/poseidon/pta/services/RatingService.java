package com.poseidon.pta.services;

import com.poseidon.pta.domain.Rating;
import com.poseidon.pta.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class RatingService extends BaseService<Rating>{
    @Autowired
    private RatingRepository ratingRepository;

    public RatingService() {
        super("rating");
    }

    public String update(Integer id, Rating rating,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }

        rating.setId(id);
        ratingRepository.save(rating);
        model.addAttribute("ratings", ratingRepository.findAll());
        return "redirect:/rating/list";
    }
}
