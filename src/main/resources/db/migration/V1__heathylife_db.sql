-- Table: answers
CREATE TABLE answers (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         goal VARCHAR(255),
                         gender VARCHAR(255),
                         age INTEGER,
                         height FLOAT,
                         weight FLOAT,
                         activity_level_type VARCHAR(255),
                         goal_weight FLOAT,
                         gym_status VARCHAR(255),
                         user_id BIGINT
);

-- Table: activity_levels
CREATE TABLE activity_levels (
                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 activity_level_type VARCHAR(255),
                                 level FLOAT
);

-- Table: blood_pressure_levels
CREATE TABLE blood_pressure_levels (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       new_blood_pressure_level FLOAT,
                                       date DATE,
                                       time TIME,
                                       user_id BIGINT
);

-- Table: cholesterol_levels
CREATE TABLE cholesterol_levels (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    new_cholesterol_level FLOAT,
                                    date DATE,
                                    time TIME,
                                    user_id BIGINT
);

-- Table: exercise
CREATE TABLE exercise (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255),
                          burn_calories FLOAT,
                          time_type VARCHAR(255),
                          time_amount INTEGER,
                          date DATE
);

-- Table: exercise_details
CREATE TABLE exercise_details (
                                  id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                  name VARCHAR(255),
                                  burn_calories FLOAT,
                                  time_type VARCHAR(255),
                                  time_amount INTEGER
);

-- Table: foods
CREATE TABLE foods (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       date DATE,
                       amount FLOAT,
                       food_type VARCHAR(255),
                       calories FLOAT,
                       carbs FLOAT,
                       protein FLOAT,
                       fat FLOAT
);

-- Table: food_details
CREATE TABLE food_details (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255),
                              calories FLOAT,
                              carbs FLOAT,
                              protein FLOAT,
                              fat FLOAT,
                              amount FLOAT
);

-- Table: sugar_levels
CREATE TABLE sugar_levels (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              new_sugar_level FLOAT,
                              date DATE,
                              time TIME,
                              user_id BIGINT
);

-- Table: users
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255),
                       username VARCHAR(255),
                       roles VARCHAR(255)

);

-- Table: water_levels
CREATE TABLE water_levels (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              litters FLOAT,
                              date DATE,
                              time TIME,
                              user_id BIGINT
);

-- Table: weight_levels
CREATE TABLE weight_levels (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               date DATE,
                               new_weight_level FLOAT,
                               user_id BIGINT
);

-- Junction table: user_foods
CREATE TABLE user_foods (
                            user_id BIGINT,
                            food_id BIGINT,
                            PRIMARY KEY (user_id, food_id)
);

-- Junction table: user_exercise
CREATE TABLE user_exercise (
                               user_id BIGINT,
                               exercise_id BIGINT,
                               PRIMARY KEY (user_id, exercise_id)
);

-- Foreign Keys
ALTER TABLE answers
    ADD CONSTRAINT fk_answers_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE blood_pressure_levels
    ADD CONSTRAINT fk_blood_pressure_levels_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE cholesterol_levels
    ADD CONSTRAINT fk_cholesterol_levels_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE sugar_levels
    ADD CONSTRAINT fk_sugar_levels_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE water_levels
    ADD CONSTRAINT fk_water_levels_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE weight_levels
    ADD CONSTRAINT fk_weight_levels_user_id FOREIGN KEY (user_id) REFERENCES users(id);

ALTER TABLE user_foods
    ADD CONSTRAINT fk_user_foods_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    ADD CONSTRAINT fk_user_foods_food_id FOREIGN KEY (food_id) REFERENCES foods(id);

ALTER TABLE user_exercise
    ADD CONSTRAINT fk_user_exercise_user_id FOREIGN KEY (user_id) REFERENCES users(id),
    ADD CONSTRAINT fk_user_exercise_exercise_id FOREIGN KEY (exercise_id) REFERENCES exercise(id);
