package com.saifmit.firstjobapp.review.Impl;

import com.saifmit.firstjobapp.company.Company;
import com.saifmit.firstjobapp.company.CompanyService;
import com.saifmit.firstjobapp.review.Review;
import com.saifmit.firstjobapp.review.ReviewRepository;
import com.saifmit.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
     private ReviewRepository reviewRepository;
     private CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository , CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public void createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
        }
    }

    @Override
    public boolean updateReview(Review review, Long companyId, Long reviewId) {

        if(companyService.getCompanyById(companyId)!=null){
            review.setCompany(companyService.getCompanyById(companyId));
            review.setId(reviewId);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews.stream().filter(review -> review.getId().equals(reviewId)).findFirst().orElse(null);

    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
