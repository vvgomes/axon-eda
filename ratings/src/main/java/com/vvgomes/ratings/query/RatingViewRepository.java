package com.vvgomes.ratings.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingViewRepository extends JpaRepository<RatingView, String> {}
