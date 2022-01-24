package com.poseidon.pta.repositories;

import com.poseidon.pta.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
