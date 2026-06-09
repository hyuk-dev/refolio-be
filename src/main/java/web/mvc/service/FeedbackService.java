package web.mvc.service;

import web.mvc.dto.feedback.FeedbackCreateRequest;
import web.mvc.dto.feedback.FeedbackResponse;
import web.mvc.dto.feedback.FeedbackUpdateRequest;

import java.util.List;

public interface FeedbackService {
    void createFeedback(Long portfolioId, FeedbackCreateRequest request); // 유저 id 기반

    List<FeedbackResponse> getFeedbacks(); // 유저 id 기반

    void deleteFeedback(Long feedbackId);

    void updateFeedback(Long feedbackId, FeedbackUpdateRequest request);
}
