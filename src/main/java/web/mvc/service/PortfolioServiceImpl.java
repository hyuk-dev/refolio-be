package web.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Portfolio;
import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
import web.mvc.repository.PortfolioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;

    @Override
    @Transactional
    public void createPortfolio(PortfolioCreateRequest req) {
        Portfolio portfolio = Portfolio.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .content(req.getContent())
                .thumbnailImg(req.getThumbnailImg())
                .githubUrl(req.getGithubUrl())
                .demoUrl(req.getDemoUrl())
                .build();

        portfolioRepository.save(portfolio);
    }

    @Override
    public List<PortfolioSummaryResponse> getPortfolios() {
        return portfolioRepository.findAll().stream()
                .map(portfolio -> PortfolioSummaryResponse.builder()
                .portfolioId(portfolio.getPortfolioId())
                .title(portfolio.getTitle())
                .description(portfolio.getDescription())
                .content(portfolio.getContent())
                .thumbnailImg(portfolio.getThumbnailImg())
                .githubUrl(portfolio.getGithubUrl())
                .demoUrl(portfolio.getDemoUrl())
                .createdAt(portfolio.getCreatedAt())
                .updatedAt(portfolio.getUpdatedAt())
                // 작성자, 찜하기 수, 찜하기 여부, 추천 수 등 추가 정보는 추후 구현
                .build()).toList();
    }

    @Override
    public void getPortfolioById() {

    }

    @Override
    public void updatePortfolio() {

    }

    @Override
    public void deletePortfolio() {

    }
}
