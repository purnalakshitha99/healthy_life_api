package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private float burnCalories;
    @Enumerated(EnumType.STRING)
    private TimeType timeType;
    private Integer timeAmount;
    private LocalDate date;



    @ManyToMany(mappedBy = "exerciseList")
    private List<User> userList = new ArrayList<>();
}
