package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate date;
    private Float amount;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;

    @ManyToMany(mappedBy = "foodList")
    private List<User> userList;

}
