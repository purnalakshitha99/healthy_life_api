package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.BloodPressureLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BloodPressureLevelRepository extends JpaRepository<BloodPressureLevel,Long> {

    BloodPressureLevel findBloodPressureLevelByUserIdAndDate(Long userId, LocalDate date);
}
