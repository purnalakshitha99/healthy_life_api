package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.FoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodDetailsRepository extends JpaRepository<FoodDetails,Long> {
}
