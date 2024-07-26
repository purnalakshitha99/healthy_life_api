package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "activity_levels")
public class ActivityLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String activityLevelType;
    private Float level;
}