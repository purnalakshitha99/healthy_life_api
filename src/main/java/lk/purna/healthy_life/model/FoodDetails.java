package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "food_details")
public class FoodDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float calories;
    private Float carbs;
    private Float protein;
    private Float fat;
    private Float amount;
}
