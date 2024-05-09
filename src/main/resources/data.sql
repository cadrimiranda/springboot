-- Insert data into `muscular_group` table
INSERT INTO muscular_group (id, name)
VALUES ('aa111111-1111-1111-1111-111111111111', 'Quadriceps');

INSERT INTO muscular_group (id, name)
VALUES ('aa222222-2222-2222-2222-222222222222', 'Posterior de Perna');

INSERT INTO muscular_group (id, name)
VALUES ('aa333333-3333-3333-3333-333333333333', 'Biceps');

INSERT INTO muscular_group (id, name)
VALUES ('aa444444-4444-4444-4444-444444444444', 'Triceps');

INSERT INTO muscular_group (id, name)
VALUES ('aa555555-5555-5555-5555-555555555555', 'Ombro');

INSERT INTO muscular_group (id, name)
VALUES ('aa666666-6666-6666-6666-666666666666', 'Peito');

-- noinspection SqlNoDataSourceInspection
INSERT INTO users (id, email, name, password, active_workout_id)
VALUES ('22222222-2222-2222-2222-222222222222', 'jane.doe@example.com', 'Personal Trainer', 'encryptedpassword2', null);

INSERT INTO users (id, email, name, password)
VALUES ('11111111-1111-1111-1111-111111111111', 'example@domain.com', 'Carlos Adriano',
        '$2a$10$KUT1.LmQPRvek0nbL74pKOn23fKR52.7Cmr6vOtomD5zaoZ8eNC4C');

-- Insert data into `workout` table
INSERT INTO workout (id, created_by_id, name, description)
VALUES ('55555555-5555-5555-5555-555555555555', '22222222-2222-2222-2222-222222222222', 'Base.3', 'Peso com controle'),
       ('66666666-6666-6666-6666-666666666666', '22222222-2222-2222-2222-222222222222', 'Evening Routine',
        'A relaxing evening workout routine');

UPDATE users
SET active_workout_id = '55555555-5555-5555-5555-555555555555'
WHERE id = '11111111-1111-1111-1111-111111111111';

-- Insert data into `user_workout` table
INSERT INTO user_workout (user_id, workout_id)
VALUES ('11111111-1111-1111-1111-111111111111', '55555555-5555-5555-5555-555555555555'),
       ('11111111-1111-1111-1111-111111111111', '66666666-6666-6666-6666-666666666666');

-- Insert data into `exercise` table
INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a1111111-1111-1111-1111-111111111111', 'aa111111-1111-1111-1111-111111111111', 'Extensora Bl.',
        '4 series of 20-18-15-15 reps with 60s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a2222222-2222-2222-2222-222222222222', 'aa444444-4444-4444-4444-444444444444', 'Triceps polia barra',
        '4 series of 15-12-10-8 reps with 120-180s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a3333333-3333-3333-3333-333333333333', 'aa333333-3333-3333-3333-333333333333', 'Rosca polia c/ barra',
        '4 series of 15-12-10-8 reps with 120-180s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a4444444-4444-4444-4444-444444444444', 'aa444444-4444-4444-4444-444444444444', 'Triceps francês halter',
        '4 series of 10 to 12 reps with 90s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a5555555-5555-5555-5555-555555555555', 'aa333333-3333-3333-3333-333333333333', 'Scott maq. uni.',
        '3 series of 10 to 12 reps with 30s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a6666666-6666-6666-6666-666666666666', 'aa444444-4444-4444-4444-444444444444', 'Triceps testa halter',
        '4 series of 10 to 12 reps with 90s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a7777777-7777-7777-7777-777777777777', 'aa333333-3333-3333-3333-333333333333',
        'Rosca bíceps banco inclinado alternado', '4 series of 10 to 12 reps with 90s rest');

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a8888888-8888-8888-8888-888888888888', 'aa444444-4444-4444-4444-444444444444', 'Triceps polia',
        '2 series of 15 to 20 reps with 30s rest');

-- Insert data into `exercise_set` table
INSERT INTO exercise_set (id, workout_id, name, description)
VALUES ('99999999-9999-9999-9999-999999999999', '55555555-5555-5555-5555-555555555555', 'Base.3 Bracos',
        'The first set of the routine'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '66666666-6666-6666-6666-666666666666', 'Set 2',
        'The second set of the routine');

-- Insert data into `exercise_setup` table
INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b1111111-1111-1111-1111-111111111111', 'a1111111-1111-1111-1111-111111111111', '4', '20-18-15-15', '60s',
        'Increase weight each set');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b2222222-2222-2222-2222-222222222222', 'a2222222-2222-2222-2222-222222222222', '4', '15-12-10-8', '120-180s',
        'Keep elbows fixed');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b3333333-3333-3333-3333-333333333333', 'a3333333-3333-3333-3333-333333333333', '4', '15-12-10-8', '120-180s',
        'Focus on form');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b4444444-4444-4444-4444-444444444444', 'a4444444-4444-4444-4444-444444444444', '4', '10-12', '90s',
        'Full extension');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b5555555-5555-5555-5555-555555555555', 'a5555555-5555-5555-5555-555555555555', '3', '10-12', '30s',
        'Isolate biceps');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b6666666-6666-6666-6666-666666666666', 'a6666666-6666-6666-6666-666666666666', '4', '10-12', '90s',
        'Do not lock elbows');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b7777777-7777-7777-7777-777777777777', 'a7777777-7777-7777-7777-777777777777', '4', '10-12', '90s',
        'Alternate arms, focus on form');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b8888888-8888-8888-8888-888888888888', 'a8888888-8888-8888-8888-888888888888', '2', '15-20', '30s',
        'Fast pace, less weight');

-- Insert data into `set_setup` table
INSERT INTO set_setup (exercise_set_id, exercise_setup_id)
VALUES ('99999999-9999-9999-9999-999999999999', 'b1111111-1111-1111-1111-111111111111'),
       ('99999999-9999-9999-9999-999999999999', 'b2222222-2222-2222-2222-222222222222'),
       ('99999999-9999-9999-9999-999999999999', 'b3333333-3333-3333-3333-333333333333'),
       ('99999999-9999-9999-9999-999999999999', 'b4444444-4444-4444-4444-444444444444'),
       ('99999999-9999-9999-9999-999999999999', 'b5555555-5555-5555-5555-555555555555'),
       ('99999999-9999-9999-9999-999999999999', 'b6666666-6666-6666-6666-666666666666'),
       ('99999999-9999-9999-9999-999999999999', 'b7777777-7777-7777-7777-777777777777'),
       ('99999999-9999-9999-9999-999999999999', 'b8888888-8888-8888-8888-888888888888');

-- Insert data into `anthropometric_report` table
INSERT INTO anthropometric_report (id, users_id, report_date, height, weight, ideal_weight, bmi, body_fat_mass,
                                   percentage_body_fat, lean_mass, percentage_lean_mass, body_density, sum_of_skinfolds,
                                   arm_muscle_area, arm_fat_area, shoulder_circumference, chest_circumference,
                                   waist_circumference, abdomen_circumference, right_calf_circumference,
                                   left_calf_circumference, right_thigh_circumference, left_thigh_circumference,
                                   relaxed_right_arm_circumference, relaxed_left_arm_circumference,
                                   contracted_right_arm_circumference, contracted_left_arm_circumference,
                                   right_forearm_circumference, left_forearm_circumference, triceps_skinfold,
                                   mid_axillary_skinfold, chest_skinfold, abdominal_skinfold, suprailiac_skinfold,
                                   subscapular_skinfold, thigh_skinfold)
VALUES ('dddddddd-dddd-dddd-dddd-dddddddddddd', '11111111-1111-1111-1111-111111111111', '2024-01-01', 1.80, 75.0, 68.0,
        23.1, 16.0, 21.3, 59.0, 78.7, 1.06, 215.0, 26.5, 5.7, 103.0, 93.0, 83.0, 86.0, 37.0, 37.5, 53.0, 52.5, 30.0,
        31.0, 35.0, 36.0, 29.0, 29.5, 10.0, 8.0, 3.0, 14.0, 11.0, 15.0, 19.0),
       ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', '22222222-2222-2222-2222-222222222222', '2024-01-02', 1.75, 70.0, 65.0,
        22.9, 15.0, 21.4, 55.0, 78.6, 1.05, 210.0, 25.2, 5.3, 102.0, 92.0, 82.0, 85.0, 36.0, 36.5, 52.0, 51.5, 29.0,
        30.0, 34.0, 35.0, 28.5, 29.0, 9.0, 7.0, 2.5, 13.0, 10.0, 14.0, 18.0);
