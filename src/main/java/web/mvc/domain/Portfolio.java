package web.mvc.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "portfolio")
    private List<Favorite> favorites;

    /**
     * 선택적 필드 업데이트: null인 필드는 덮어쓰지 않음
     */
    public void update(String title, String description, String content,
                       String thumbnailImg, String githubUrl, String demoUrl) {
        if (title != null) this.title = title;
        if (description != null) this.description = description;
        if (content != null) this.content = content;
        if (thumbnailImg != null) this.thumbnailImg = thumbnailImg;
        if (githubUrl != null) this.githubUrl = githubUrl;
        if (demoUrl != null) this.demoUrl = demoUrl;
    }

    @OneToMany(mappedBy = "portfolio")
    private List<Recommendation> recommendations;

    @OneToMany(mappedBy = "portfolio")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "portfolio")
    private List<Image> images;

    @ManyToMany
    @JoinTable(name = "portfolio_tag",
            joinColumns = @JoinColumn(name = "portfolio_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;


}
