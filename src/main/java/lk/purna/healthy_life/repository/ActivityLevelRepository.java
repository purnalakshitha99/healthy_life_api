package lk.purna.healthy_life.repository;


import lk.purna.healthy_life.model.ActivityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLevelRepository extends JpaRepository<ActivityLevel,Long> {

    ActivityLevel findActivityLevelsByActivityLevelTypeContainingIgnoreCase(String activityLevelType);
}
