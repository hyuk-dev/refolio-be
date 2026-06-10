package web.mvc.repository;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;

import java.util.List;

import static web.mvc.domain.QFeedback.feedback;
import static web.mvc.domain.QPortfolio.portfolio;

@RequiredArgsConstructor
public class PortfolioRepositoryCustomImpl implements PortfolioRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PortfolioSummaryResponse> findPortfolioWithFeedbacks(Pageable pageable) {
        // 1. 데이터 조회 (leftJoin + groupBy)
        List<PortfolioSummaryResponse> portfolioList = queryFactory
                .select(Projections.constructor(PortfolioSummaryResponse.class,
                        portfolio.portfolioId,
                        portfolio.title,
                        portfolio.description,
                        portfolio.content,
                        portfolio.thumbnailImg,
                        portfolio.githubUrl,
                        portfolio.demoUrl,
                        portfolio.createdAt,
                        portfolio.updatedAt,
                        portfolio.user.nickname,
                        feedback.count()
                ))
                .from(portfolio)
                .leftJoin(portfolio.feedbacks, feedback)
                .groupBy(portfolio.portfolioId)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        // 2. 전체 데이터 개수 조회
        long totalCount = queryFactory
                .select(portfolio.count())
                .from(portfolio)
                .fetchOne();
        //Page로 반환 로직
        return new PageImpl<>(portfolioList, pageable, totalCount != 0 ? totalCount : 1);
    }
}
