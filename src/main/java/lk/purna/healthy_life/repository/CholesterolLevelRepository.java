package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.CholesterolLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CholesterolLevelRepository extends JpaRepository<CholesterolLevel,Long> {

    CholesterolLevel findCholesterolLevelByUserIdAndDate(Long userId, LocalDate date);
}
