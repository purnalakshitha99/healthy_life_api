package lk.purna.healthy_life.repository;

import lk.purna.healthy_life.model.FoodDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodDetailsRepository extends JpaRepository<FoodDetails,Long> {

    @Query("SELECT f FROM FoodDetails f WHERE LOWER(f.name) LIKE LOWER(CONCAT(:prefix, '%') ) ")
    List<FoodDetails> findByNameStartingWithIgnoreCase(@Param("prefix")String prefix);
}
