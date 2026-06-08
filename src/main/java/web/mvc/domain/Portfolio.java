package web.mvc.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {
    @Id
    @Column(name = "portfolio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long portfolioId;

    private String title;

    private String description;

    private String content;

    @Column(name = "thumbnail_img")
    private String thumbnailImg;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "demo_url")
    private String demoUrl;

    @CreatedDate
    private String createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
