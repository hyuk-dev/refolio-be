package web.mvc.service;

import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioDetailResponse;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
import web.mvc.dto.portfolio.PortfolioUpdateRequest;

import java.util.List;

public interface PortfolioService {
    public void createPortfolio(PortfolioCreateRequest req);

    public List<PortfolioSummaryResponse> getPortfolios();

    public PortfolioDetailResponse getPortfolioById(Long portfolioId);

    public void updatePortfolio(Long portfolioId, PortfolioUpdateRequest req);

    public void deletePortfolio(Long portfolioId);
}
