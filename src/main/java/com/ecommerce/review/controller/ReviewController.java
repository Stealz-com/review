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
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public Review createReview(@RequestBody Review review,
            @RequestHeader(value = "X-User-Email", required = false) String email) {
        if (email != null) {
            review.setUsername(email);
        }
        return reviewRepository.save(review);
    }

    @GetMapping
    @ResponseStatus(org.springframework.http.HttpStatus.OK)
    public List<Review> getReviewsByProduct(@RequestParam("productId") Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable Long id,
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        if (!"ADMIN".equals(role) && (email == null || !email.equals(review.getUsername()))) {
            throw new RuntimeException("Unauthorized to delete this review");
        }
        reviewRepository.deleteById(id);
    }
}
