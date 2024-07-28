package lk.purna.healthy_life.service.impl;

import lk.purna.healthy_life.controller.response.CalculationResponse;
import lk.purna.healthy_life.exception.UserNotFoundException;
import lk.purna.healthy_life.model.Answer;
import lk.purna.healthy_life.model.User;
import lk.purna.healthy_life.repository.ActivityLevelRepository;
import lk.purna.healthy_life.repository.AnswerRepository;
import lk.purna.healthy_life.repository.UserRepository;
import lk.purna.healthy_life.service.CalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalculationServiceImpl implements CalculationService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final ActivityLevelRepository activityLevelRepository;


    @Override
    public CalculationResponse calculateAdjustedCalories(Long userId) throws UserNotFoundException {
        System.out.println("helooooooooooooooooooooooooooooooooooooo");
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFoundException("That User Not in a db")
        );

        Answer answers = user.getAnswer();

        Integer age = answers.getAge();
        String gender = String.valueOf(answers.getGender());
        Float weight = answers.getWeight();
        Float height = answers.getHeight();
        String activityLevelType = answers.getActivityLevelType();
        Float goalWeight = answers.getGoalWeight();
        String goal = answers.getGoal();

        float BMR;

        if (gender.equalsIgnoreCase("MALE")) {
            BMR = (float) (10 * weight + 6.25 * height - 5 * age + 5);
        } else {
            BMR = (float) (10 * weight + 6.25 * height - 5 * age - 161);
        }

        Float activityLevelValue = activityLevelRepository.findActivityLevelsByActivityLevelTypeContainingIgnoreCase(activityLevelType).getLevel();

        float TDEE = BMR * activityLevelValue;

        System.out.println("TDEE ::::::::::::::: "+TDEE);

        float adjustedDailyCalories = 0;

        if(goal.equals("loss weight")){
            System.out.println("goal is loss");
            adjustedDailyCalories = TDEE-550;
        } else if (goal.equals("gain weight")) {
            adjustedDailyCalories = TDEE+550;
        } else if (goal.equals("maintain weight")) {
            adjustedDailyCalories = TDEE;
        }

        float healthyRate = 0.5F;

        float weeks = goalWeight/healthyRate;

        CalculationResponse calculationResponse = new CalculationResponse();
        calculationResponse.setAdjustedDailyCalories(adjustedDailyCalories);
        calculationResponse.setNumOfWeeks(weeks+" Number of weeks");

        return calculationResponse;

    }
}
