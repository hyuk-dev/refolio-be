package web.mvc.dto.portfolio;

import lombok.*;
import web.mvc.domain.Tag;
import web.mvc.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class PortfolioSummaryResponse {
    private Long portfolioId;

    private String title;

    private String description;

    private String content;

    private String thumbnailImg;

    private String githubUrl;

    private String demoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String writer; // 작성자

    private Long feedbackCount; // 피드백 수

//    private Long favoriteCount; // 찜하기 수
//
//    private boolean isRecommendation; // 찜하기 여부
//
//    private Long recommendationCount; // 추천 수

    // 태그 리스트 DTO 기반
}
