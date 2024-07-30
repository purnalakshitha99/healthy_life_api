package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "cholesterol_levels")
public class CholesterolLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float newCholesterolLevel;
    private LocalTime time;
    private LocalDate date;

    @ManyToOne
    private User user;
}
