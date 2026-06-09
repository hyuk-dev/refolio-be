package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioDetailResponse;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
import web.mvc.dto.portfolio.PortfolioUpdateRequest;
import web.mvc.service.PortfolioService;

import java.util.List;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping
    public ResponseEntity<?> createPortfolio(@RequestBody PortfolioCreateRequest req) {
        portfolioService.createPortfolio(req);
        return ResponseEntity.ok("포트폴리오 생성 성공");
    }

    @GetMapping
    public ResponseEntity<?> getPortfolios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<PortfolioSummaryResponse> result = portfolioService.getPortfolios(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<?> getPortfolioById(@PathVariable Long portfolioId) {
        PortfolioDetailResponse portfolio = portfolioService.getPortfolioById(portfolioId);
        return ResponseEntity.ok(portfolio);
    }

    @PatchMapping("/{portfolioId}")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long portfolioId,
                                             @RequestBody PortfolioUpdateRequest req) {
        portfolioService.updatePortfolio(portfolioId, req);
        return ResponseEntity.ok("포트폴리오 수정 성공");
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.ok("포트폴리오 삭제 성공");
    }
}
