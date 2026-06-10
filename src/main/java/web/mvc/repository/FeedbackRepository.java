package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.mvc.domain.Feedback;

import java.util.Collection;
import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    long countByPortfolio_PortfolioId(Long portfolioId);

    @Query("SELECT f FROM Feedback f JOIN f.user u WHERE u.username = :username")
    List<Feedback> findByUsername(String username);

    List<Feedback> findByPortfolio_PortfolioId(Long portfolioPortfolioId);

}
