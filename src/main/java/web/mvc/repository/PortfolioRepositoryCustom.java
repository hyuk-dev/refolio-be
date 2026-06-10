package web.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;

public interface PortfolioRepositoryCustom {
    Page<PortfolioSummaryResponse> findPortfolioWithFeedbacks(Pageable pageable);
}
