package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise,Long> {
}
