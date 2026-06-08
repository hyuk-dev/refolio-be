package web.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
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
    public ResponseEntity<?> getPortfolios() {
        List<PortfolioSummaryResponse> list = portfolioService.getPortfolios();
        return ResponseEntity.ok(list);
    }
}
