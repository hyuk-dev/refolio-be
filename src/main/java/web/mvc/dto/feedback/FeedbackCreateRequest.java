package web.mvc.dto.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class FeedbackCreateRequest {
    private String content;
}
