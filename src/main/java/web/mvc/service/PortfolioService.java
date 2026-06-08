package web.mvc.service;

import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;

import java.util.List;

public interface PortfolioService {
    public void createPortfolio(PortfolioCreateRequest req);

    public List<PortfolioSummaryResponse> getPortfolios();

    public void getPortfolioById();

    public void updatePortfolio();

    public void deletePortfolio();
}
