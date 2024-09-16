package lk.purna.healthy_life.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private ROLES roles;

    @JoinTable(name = "user_foods",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "food_id"))
    @ManyToMany
    private List<Food> foodList;

    @JoinTable(name = "user_exercise",joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "exercise_id"))
    @ManyToMany
    private List<Exercise> exerciseList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Answer answer;

    @OneToMany(mappedBy = "user")
    private List<WeightLevel> weightLevel;

    @OneToMany(mappedBy = "user")
    private List<WaterLevel> waterLevelList;

    @OneToMany(mappedBy = "user")
    private List<SugarLevel> sugarLevelList;

    @OneToMany(mappedBy = "user")
    private List<CholesterolLevel> cholesterolLevelList;

    @OneToMany(mappedBy = "user")
    private List<BloodPressureLevel> bloodPressureLevelList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
