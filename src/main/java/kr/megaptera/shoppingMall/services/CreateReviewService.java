package kr.megaptera.shoppingMall.services;

import kr.megaptera.shoppingMall.dtos.CreateReviewDto;
import kr.megaptera.shoppingMall.exceptions.OrderNotFoundException;
import kr.megaptera.shoppingMall.exceptions.UserNotFoundException;
import kr.megaptera.shoppingMall.models.Order;
import kr.megaptera.shoppingMall.models.Recommendation;
import kr.megaptera.shoppingMall.models.Review;
import kr.megaptera.shoppingMall.models.ReviewImage;
import kr.megaptera.shoppingMall.models.User;
import kr.megaptera.shoppingMall.repositoies.OrderRepository;
import kr.megaptera.shoppingMall.repositoies.ReviewRepository;
import kr.megaptera.shoppingMall.repositoies.UserRepository;
import kr.megaptera.shoppingMall.utils.AwsS3Uploader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CreateReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AwsS3Uploader awsS3Uploader;
    private final OrderRepository orderRepository;

    public CreateReviewService(
        ReviewRepository reviewRepository,
        UserRepository userRepository,
        AwsS3Uploader awsS3Uploader,
        OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.awsS3Uploader = awsS3Uploader;
        this.orderRepository = orderRepository;
    }

    public void createReview(
        Long productId,
        Long userId,
        CreateReviewDto createReviewDto,
        List<MultipartFile> multipartFiles) throws IOException {

        User user = userRepository.findById(userId)
            .orElse(null);

        if(user == null) {
            user = userRepository.findBySocialLoginId(String.valueOf(userId))
                .orElseThrow(UserNotFoundException::new);
        }

        List<Recommendation> recommendations = new ArrayList<>();
        List<ReviewImage> reviewImages = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            String reviewImage = awsS3Uploader.uploadFiles(multipartFile, "ReviewImageFile");
            reviewImages.add(new ReviewImage(reviewImage));
        }

        Review review = new Review(
            productId,
            createReviewDto.getRating(),
            userId,
            createReviewDto.getDescription(),
            createReviewDto.getContent(),
            user.name(),
            recommendations,
            reviewImages,
            false
            );

        Order order = orderRepository.findById(createReviewDto.getOrderId())
            .orElseThrow(() -> new OrderNotFoundException(createReviewDto.getOrderId()));

        order.changeState();

        reviewRepository.save(review);
    }
}
