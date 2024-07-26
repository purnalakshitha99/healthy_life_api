package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @JoinTable(name = "user_foods",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "food_id"))
    @ManyToMany
    private List<Food> foodList;

    @JoinTable(name = "user_exercise",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    @ManyToMany
    private List<Exercise> exerciseList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Answer answer;
}
