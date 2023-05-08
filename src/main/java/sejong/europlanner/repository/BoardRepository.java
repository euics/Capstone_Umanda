package sejong.europlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.europlanner.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
