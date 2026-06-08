package web.mvc.dto.portfolio;

import lombok.*;
import web.mvc.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Builder
public class PortfolioDetailResponse {
    private Long portfolioId;

    private String title;

    private String description;

    private String content;

    private String thumbnailImg;

    private String githubUrl;

    private String demoUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String writer;

//    private User user;

//    private List<Favorite> favoriteList;

//    private List<Recommendation> recommendationList;

//    private List<Feedback> feedbackList;

//    private List<Image> images;

//    private List<Tag> tags;
}
