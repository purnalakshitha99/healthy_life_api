package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.WeightLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


public interface WeightLevelRepository extends JpaRepository<WeightLevel,Long> {

    WeightLevel findWeightLevelByDateAndUserId(LocalDate date,Long userId);
}
