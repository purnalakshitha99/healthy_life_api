package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "water_levels")
public class WaterLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float litters;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    private User user;
}
