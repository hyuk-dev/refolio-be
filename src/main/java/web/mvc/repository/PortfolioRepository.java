package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.mvc.domain.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

}
