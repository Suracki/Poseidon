package com.poseidon.pta.services;

import com.poseidon.pta.domain.Rating;
import com.poseidon.pta.repositories.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService extends BaseService<Rating>{
    public RatingService(RatingRepository ratingRepository) {
        super(ratingRepository);
    }
}
