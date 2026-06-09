package web.mvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import web.mvc.domain.Feedback;
import web.mvc.domain.Portfolio;
import web.mvc.domain.User;
import web.mvc.repository.PortfolioRepository;
import web.mvc.repository.UserRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RefolioBeApplicationTests {
    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createPortfolios() {
        User user = userRepository.findById(1L).orElseThrow();
        for(int i = 0; i < 30; i++) {
            Portfolio portfolio = Portfolio.builder()
                    .title("test" + i)
                    .description("test" + i)
                    .content("test" + i)
                    .thumbnailImg("test" + i)
                    .githubUrl("test" + i)
                    .demoUrl("test" + i)
                    .user(user)
                    .build();
            portfolioRepository.save(portfolio);
        }
    }
}
