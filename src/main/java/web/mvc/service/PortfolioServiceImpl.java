package web.mvc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mvc.domain.Portfolio;
import web.mvc.domain.User;
import web.mvc.dto.feedback.FeedbackResponse;
import web.mvc.dto.portfolio.PortfolioCreateRequest;
import web.mvc.dto.portfolio.PortfolioDetailResponse;
import web.mvc.dto.portfolio.PortfolioSummaryResponse;
import web.mvc.dto.portfolio.PortfolioUpdateRequest;
import web.mvc.exception.CommonException;
import web.mvc.exception.ErrorCode;
import web.mvc.repository.FeedbackRepository;
import web.mvc.repository.PortfolioRepository;
import web.mvc.repository.PortfolioRepositoryCustom;
import web.mvc.security.CustomUserDetails;
import web.mvc.util.SecurityUtils;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final FeedbackRepository feedbackRepository;

    @Override
    @Transactional
    public void createPortfolio(PortfolioCreateRequest req) {
        CustomUserDetails userDetails = SecurityUtils.currentUser();
        User user = userDetails.getUser();
        Portfolio portfolio = Portfolio.builder()
                .title(req.getTitle())
                .description(req.getDescription())
                .content(req.getContent())
                .thumbnailImg(req.getThumbnailImg())
                .githubUrl(req.getGithubUrl())
                .demoUrl(req.getDemoUrl())
                .user(user)
                .build();

        portfolioRepository.save(portfolio);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PortfolioSummaryResponse> getPortfolios(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return portfolioRepository.findPortfolioWithFeedbacks(pageable);

//        return portfolioRepository.findAll(pageable)
//                .map(portfolio -> PortfolioSummaryResponse.builder()
//                .portfolioId(portfolio.getPortfolioId())
//                .title(portfolio.getTitle())
//                .description(portfolio.getDescription())
//                .content(portfolio.getContent())
//                .thumbnailImg(portfolio.getThumbnailImg())
//                .githubUrl(portfolio.getGithubUrl())
//                .demoUrl(portfolio.getDemoUrl())
//                .createdAt(portfolio.getCreatedAt())
//                .updatedAt(portfolio.getUpdatedAt())
//                .writer(portfolio.getUser().getNickname())
//                .feedbackCount(feedbackRepository.countByPortfolio_PortfolioId(portfolio.getPortfolioId()))
//                 // 작성자, 찜하기 수, 찜하기 여부, 추천 수 등 추가 정보는 추후 구현
//                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public PortfolioDetailResponse getPortfolioById(Long portfolioId) {
        if(portfolioId == null) {
            throw new CommonException(ErrorCode.INVALID_REQUEST);
        }

        List<FeedbackResponse> feedbackList = feedbackRepository.findByPortfolio_PortfolioId(portfolioId).stream()
                .map(feedback -> FeedbackResponse.builder()
                        .feedbackId(feedback.getFeedbackId())
                        .content(feedback.getContent())
                        .createdAt(feedback.getCreatedAt())
                        .updatedAt(feedback.getUpdatedAt())
                        .writer(feedback.getUser().getNickname())
                        .build()).toList();

        return portfolioRepository.findById(portfolioId).stream().map(portfolio -> PortfolioDetailResponse.builder()
                .portfolioId(portfolio.getPortfolioId())
                .title(portfolio.getTitle())
                .description(portfolio.getDescription())
                .content(portfolio.getContent())
                .thumbnailImg(portfolio.getThumbnailImg())
                .githubUrl(portfolio.getGithubUrl())
                .demoUrl(portfolio.getDemoUrl())
                .createdAt(portfolio.getCreatedAt())
                .updatedAt(portfolio.getUpdatedAt())
                .writer(portfolio.getUser().getNickname())
                .feedbackList(feedbackList)
                .feedbackCount((long) feedbackList.size())
                //찜하기 수, 찜하기 여부, 추천 수 등 추가 정보는 추후 구현
                .build()).findFirst().orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
    }

    @Override
    @Transactional
    public void updatePortfolio(Long portfolioId, PortfolioUpdateRequest req) {
        User user = SecurityUtils.currentUser().getUser();
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
        if(!Objects.equals(portfolio.getUser().getUserId(), user.getUserId())) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
        portfolio.update(
                req.getTitle(),
                req.getDescription(),
                req.getContent(),
                req.getThumbnailImg(),
                req.getGithubUrl(),
                req.getDemoUrl()
        );
        portfolioRepository.save(portfolio);
    }

    @Override
    @Transactional
    public void deletePortfolio(Long portfolioId) {
        User user = SecurityUtils.currentUser().getUser();
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
        if(!Objects.equals(portfolio.getUser().getUserId(), user.getUserId())) {
            throw new CommonException(ErrorCode.FORBIDDEN);
        }
        portfolioRepository.deleteById(portfolioId);
    }
}
