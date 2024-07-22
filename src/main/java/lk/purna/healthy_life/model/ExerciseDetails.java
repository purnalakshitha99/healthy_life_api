package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "exercise_details")
public class ExerciseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float burnCalories;

    private String timeType;
    private Integer timeAmount;
}
