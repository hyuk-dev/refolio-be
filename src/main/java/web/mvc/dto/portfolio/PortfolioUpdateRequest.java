package web.mvc.dto.portfolio;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioUpdateRequest {
    private String title;

    private String description;

    private String content;

    private String thumbnailImg;

    private String githubUrl;

    private String demoUrl;
    
    // 태그 리스트는 별도의 DTO로 처리할 수 있습니다.
}
