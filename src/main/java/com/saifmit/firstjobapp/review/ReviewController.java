package com.saifmit.firstjobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @GetMapping
    public ResponseEntity<List<Review>> getAll(@PathVariable Long companyId){
        return ResponseEntity.ok(reviewService.getAllReviews(companyId));
    }
    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review){
        reviewService.createReview(companyId,review);
        return new ResponseEntity<>("Review is Successfully done.", HttpStatus.CREATED);
    }
    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review review,@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean updated = reviewService.updateReview(review,companyId,reviewId);
        if(updated){
            return new ResponseEntity<>("Review Updated Successfully.",HttpStatus.OK);
        }
        return new ResponseEntity<>("Review Not Updated",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        return ResponseEntity.ok(reviewService.getReview(companyId,reviewId));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,@PathVariable Long reviewId){
        boolean deletedReview = reviewService.deleteReview(companyId,reviewId);
        if(deletedReview){
            return new ResponseEntity<>("The Review Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Something is wrong the review is not deleted",HttpStatus.BAD_REQUEST);
    }
}
