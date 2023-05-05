package sejong.europlanner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sejong.europlanner.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
