package web.mvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import web.mvc.domain.Portfolio;
import web.mvc.repository.PortfolioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PortfolioJpaTest {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("포트폴리오 생성")
    void createPortfolio() {
        Portfolio portfolio = Portfolio.builder()
                .title("test")
                .description("test")
                .content("test")
                .thumbnailImg("test")
                .githubUrl("test")
                .demoUrl("test")
                .build();
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        entityManager.flush();
        entityManager.clear();

        Portfolio foundPortfolio =
                portfolioRepository.findById(savedPortfolio.getPortfolioId()).orElseThrow();

        assertThat(foundPortfolio.getPortfolioId()).isEqualTo(savedPortfolio.getPortfolioId());
        assertThat(foundPortfolio.getTitle()).isEqualTo(savedPortfolio.getTitle());
        assertThat(savedPortfolio.getPortfolioId()).isNotNull();
    }
}
