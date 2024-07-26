package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String goal;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer age;
    private Float height;
    private Float weight;
    private String activityLevel;
    private Float goalWeight;
    private String gymStatus;

    @OneToOne
    private User user;
}
