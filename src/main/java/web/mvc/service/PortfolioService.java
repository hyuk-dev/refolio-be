package web.mvc.service;

import org.springframework.data.domain.Page;
import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioDetailResponse;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
import web.mvc.dto.portfolio.PortfolioUpdateRequest;

import java.util.List;

public interface PortfolioService {
    public void createPortfolio(PortfolioCreateRequest req);

    public Page<PortfolioSummaryResponse> getPortfolios(int page, int size);

    public PortfolioDetailResponse getPortfolioById(Long portfolioId);

    public void updatePortfolio(Long portfolioId, PortfolioUpdateRequest req);

    public void deletePortfolio(Long portfolioId);
}
