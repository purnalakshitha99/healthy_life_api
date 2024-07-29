package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "weight_levels")
public class WeightLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Float newWeightLevel;

    @ManyToOne
    private User user;

}
