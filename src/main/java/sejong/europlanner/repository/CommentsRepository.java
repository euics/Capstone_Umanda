package sejong.europlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.europlanner.entity.CommentsEntity;

public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {

}
