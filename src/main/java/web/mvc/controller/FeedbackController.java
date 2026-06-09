package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.mvc.dto.feedback.FeedbackCreateRequest;
import web.mvc.dto.feedback.FeedbackResponse;
import web.mvc.dto.feedback.FeedbackUpdateRequest;
import web.mvc.service.FeedbackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping
    public ResponseEntity<?> getFeedbacks() {
        List<FeedbackResponse> feedbacks = feedbackService.getFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping("/{portfolioId}")
    public ResponseEntity<?> createFeedback(@PathVariable Long portfolioId,
                                            @RequestBody FeedbackCreateRequest request) {
        feedbackService.createFeedback(portfolioId, request);
        return ResponseEntity.ok("feedback created");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateFeedback(@PathVariable Long id
    , @RequestBody FeedbackUpdateRequest request) {
        feedbackService.updateFeedback(id, request);
        return ResponseEntity.ok("feedback updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("feedback deleted");
    }
}
