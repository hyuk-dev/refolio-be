package web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.mvc.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
