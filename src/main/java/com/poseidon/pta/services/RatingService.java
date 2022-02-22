package com.poseidon.pta.services;

import com.poseidon.pta.domain.Rating;
import com.poseidon.pta.repositories.RatingRepository;
import org.springframework.stereotype.Service;

/**
 * RatingService performs operations for the RatingController endpoints
 *
 * Extends BaseService<Rating>, passes a RatingRepository object to this super.
 * All functionality is contained within BaseService.
 */
@Service
public class RatingService extends BaseService<Rating>{
    public RatingService(RatingRepository ratingRepository) {
        super(ratingRepository);
    }
}
