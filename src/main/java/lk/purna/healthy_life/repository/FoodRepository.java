package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {
}
