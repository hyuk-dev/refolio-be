package web.mvc.dto.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import web.mvc.domain.Portfolio;
import web.mvc.domain.User;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class FeedbackResponse {
    private Long feedbackId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String writer;
    private Long portfolioId;
}
