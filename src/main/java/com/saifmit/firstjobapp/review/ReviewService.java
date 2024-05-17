package com.saifmit.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    void createReview(Long companyId, Review review);

    boolean updateReview(Review review, Long companyId, Long reviewId);
    Review getReview(Long companyId,Long reviewId);

    boolean deleteReview(Long companyId, Long reviewId);
}
