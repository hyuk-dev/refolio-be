package web.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Feedback;
import web.mvc.domain.User;
import web.mvc.dto.feedback.FeedbackCreateRequest;
import web.mvc.dto.feedback.FeedbackResponse;
import web.mvc.dto.feedback.FeedbackUpdateRequest;
import web.mvc.exception.CommonException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.FeedbackRepository;
import web.mvc.repository.PortfolioRepository;
import web.mvc.security.CustomUserDetails;
import web.mvc.util.SecurityUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final PortfolioRepository portfolioRepository;

    @Override
    @Transactional
    public void createFeedback(Long portfolioId, FeedbackCreateRequest request) {
        CustomUserDetails userDetails = SecurityUtils.currentUser();
        User user = userDetails.getUser();

        feedbackRepository.save(Feedback.builder()
                .content(request.getContent())
                .user(user)
                .portfolio(
                        portfolioRepository
                        .findById(portfolioId)
                        .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND))
                )
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeedbackResponse> getFeedbacks() {
        UserDetails userDetails = SecurityUtils.currentUser();
        String username = userDetails.getUsername();

        return feedbackRepository.findByUsername(username)
                .stream()
                .map(feedback -> FeedbackResponse.builder()
                        .feedbackId(feedback.getFeedbackId())
                        .content(feedback.getContent())
                        .createdAt(feedback.getCreatedAt())
                        .updatedAt(feedback.getUpdatedAt())
                        .writer(feedback.getUser().getUsername())
                        .portfolioId(feedback.getPortfolio().getPortfolioId())
                        .build())
                .toList();
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        CustomUserDetails userDetails = SecurityUtils.currentUser();
        User user = userDetails.getUser();

        if(feedbackRepository.findById(feedbackId).get().getUser().getUserId() != user.getUserId()) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }

        feedbackRepository.deleteById(feedbackId);
    }

    @Override
    public void updateFeedback(Long feedbackId, FeedbackUpdateRequest request) {
        CustomUserDetails userDetails = SecurityUtils.currentUser();
        User user = userDetails.getUser();

        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));

        if(feedback.getUser().getUserId() != user.getUserId()) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }

        feedback.setContent(request.getContent());

        feedbackRepository.save(feedback);

    }
}
