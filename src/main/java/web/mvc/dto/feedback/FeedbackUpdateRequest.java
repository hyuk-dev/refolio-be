package web.mvc.dto.feedback;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class FeedbackUpdateRequest {
    private String content;
}
