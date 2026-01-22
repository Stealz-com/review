package com.ecommerce.review.controller;

import com.ecommerce.review.entity.Review;
import com.ecommerce.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewRepository reviewRepository;

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping
    public List<Review> getReviewsByProduct(@RequestParam("productId") Long productId) {
        return reviewRepository.findByProductId(productId);
    }
}
