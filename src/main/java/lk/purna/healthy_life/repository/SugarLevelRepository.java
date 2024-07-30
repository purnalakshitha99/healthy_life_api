package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.SugarLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface SugarLevelRepository extends JpaRepository<SugarLevel,Long> {

    SugarLevel findSugarLevelByUserIdAndDate(Long userId, LocalDate date);
}
