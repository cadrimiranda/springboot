-- Insert data into `muscular_group` table
INSERT INTO muscular_group (id, name)
VALUES ('aa111111-1111-1111-1111-111111111111', 'QUADRICEPS'),
       ('aa222222-2222-2222-2222-222222222222', 'HAMSTRINGS'),
       ('aa333333-3333-3333-3333-333333333333', 'BICEPS'),
       ('aa444444-4444-4444-4444-444444444444', 'TRICEPS'),
       ('aa555555-5555-5555-5555-555555555555', 'SHOULDERS'),
       ('aa555555-5555-5555-5554-455555555555', 'ABS'),
       ('aa555555-5555-5555-4444-555555555555', 'CALVES'),
       ('aa666666-6666-6666-6666-666666666666', 'CHEST');

-- noinspection SqlNoDataSourceInspection
INSERT INTO users (id, email, name, last_name, password, active_workout_id)
VALUES ('22222222-2222-2222-2222-222222222222', 'jane.doe@example.com', 'Personal', 'Trainer', 'encryptedpassword2',
        null);

INSERT INTO users (id, email, name, last_name, password, plan_start, plan_expiration, next_appointment,
                   workout_expiration)
VALUES ('22222222-2222-2222-2222-222222222223', 'doe.doe@example.com', 'User sem plano', 'ativo', 'encryptedpassword3',
        '2024-02-17', '2024-4-17', '2024-4-16', '2024-07-12');

INSERT INTO users (id, email, name, last_name, password, plan_start, plan_expiration, next_appointment,
                   workout_expiration)
VALUES ('22222222-2222-2222-2222-222222222233', 'doe.doe@example.com', 'Debora', 'Reitembach', 'encryptedpassword3',
        '2024-02-17', '2024-12-17', '2024-06-28', '2024-07-12');

INSERT INTO users (id, email, name, last_name, password, plan_start, plan_expiration, next_appointment,
                   workout_expiration)
VALUES ('11111111-1111-1111-1111-111111111111', 'example@domain.com', 'Carlos Adriano', 'Miranda',
        '$2a$10$KUT1.LmQPRvek0nbL74pKOn23fKR52.7Cmr6vOtomD5zaoZ8eNC4C', '2024-02-17', '2024-12-17', '2024-06-28',
        '2024-07-12');

-- Insert data into `workout` table
INSERT INTO workout (id, created_by_id, name, description, start_date, end_date)
VALUES ('55555555-5555-5555-5555-555555555555', '22222222-2222-2222-2222-222222222222', 'Base.3', 'Peso com controle',
        '2024-05-01', '2024-10-25'),
       ('66666666-6666-6666-6666-666666666666', '22222222-2222-2222-2222-222222222222', 'Evening Routine',
        'A relaxing evening workout routine', '2024-05-01', '2024-10-25');

UPDATE users
SET active_workout_id = '55555555-5555-5555-5555-555555555555'
WHERE id = '11111111-1111-1111-1111-111111111111';

UPDATE users
SET active_workout_id = '66666666-6666-6666-6666-666666666666'
WHERE id = '22222222-2222-2222-2222-222222222223';

-- Insert data into `user_workout` table
INSERT INTO user_workout (user_id, workout_id)
VALUES ('11111111-1111-1111-1111-111111111111', '55555555-5555-5555-5555-555555555555'),
       ('11111111-1111-1111-1111-111111111111', '66666666-6666-6666-6666-666666666666');

-- Insert data into `exercise_set` table
INSERT INTO exercise_set (id, workout_id, name, description)
VALUES ('99999999-9999-9999-9999-999999999999', '55555555-5555-5555-5555-555555555555', 'Base.3 Bracos',
        'Arms set'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '55555555-5555-5555-5555-555555555555', 'Base.3 Ombros',
        'Deltoids set');

-- Insert Bse.3 Arms
INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('a1111111-1111-1111-1111-111111111111', 'aa111111-1111-1111-1111-111111111111', 'Extensora Bl.',
        '4 series of 20-18-15-15 reps with 60s rest'),
       ('a2222222-2222-2222-2222-222222222222', 'aa444444-4444-4444-4444-444444444444', 'Triceps polia barra',
        '4 series of 15-12-10-8 reps with 120-180s rest'),
       ('a3333333-3333-3333-3333-333333333333', 'aa333333-3333-3333-3333-333333333333', 'Rosca polia c/ barra',
        '4 series of 15-12-10-8 reps with 120-180s rest'),
       ('a4444444-4444-4444-4444-444444444444', 'aa444444-4444-4444-4444-444444444444', 'Triceps francês halter',
        '4 series of 10 to 12 reps with 90s rest'),
       ('a5555555-5555-5555-5555-555555555555', 'aa333333-3333-3333-3333-333333333333', 'Scott maq. uni.',
        '3 series of 10 to 12 reps with 30s rest'),
       ('a6666666-6666-6666-6666-666666666666', 'aa444444-4444-4444-4444-444444444444', 'Triceps testa halter',
        '4 series of 10 to 12 reps with 90s rest'),
       ('a7777777-7777-7777-7777-777777777777', 'aa333333-3333-3333-3333-333333333333',
        'Rosca bíceps banco inclinado alternado', '4 series of 10 to 12 reps with 90s rest'),
       ('a8888888-8888-8888-8888-888888888888', 'aa444444-4444-4444-4444-444444444444', 'Triceps polia',
        '2 series of 15 to 20 reps with 30s rest');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('b1111111-1111-1111-1111-111111111111', 'a1111111-1111-1111-1111-111111111111', '4', '20-18-15-15', '60s',
        'Increase weight each set'),
       ('b2222222-2222-2222-2222-222222222222', 'a2222222-2222-2222-2222-222222222222', '4', '15-12-10-8', '120-180s',
        'Keep elbows fixed'),
       ('b3333333-3333-3333-3333-333333333333', 'a3333333-3333-3333-3333-333333333333', '4', '15-12-10-8', '120-180s',
        'Focus on form'),
       ('b4444444-4444-4444-4444-444444444444', 'a4444444-4444-4444-4444-444444444444', '4', '10-12', '90s',
        'Full extension'),
       ('b5555555-5555-5555-5555-555555555555', 'a5555555-5555-5555-5555-555555555555', '3', '10-12', '30s',
        'Isolate biceps'),
       ('b6666666-6666-6666-6666-666666666666', 'a6666666-6666-6666-6666-666666666666', '4', '10-12', '90s',
        'Do not lock elbows'),
       ('b7777777-7777-7777-7777-777777777777', 'a7777777-7777-7777-7777-777777777777', '4', '10-12', '90s',
        'Alternate arms, focus on form'),
       ('b8888888-8888-8888-8888-888888888888', 'a8888888-8888-8888-8888-888888888888', '2', '15-20', '30s',
        'Fast pace, less weight');

INSERT INTO set_setup (exercise_set_id, exercise_setup_id)
VALUES ('99999999-9999-9999-9999-999999999999', 'b1111111-1111-1111-1111-111111111111'),
       ('99999999-9999-9999-9999-999999999999', 'b2222222-2222-2222-2222-222222222222'),
       ('99999999-9999-9999-9999-999999999999', 'b3333333-3333-3333-3333-333333333333'),
       ('99999999-9999-9999-9999-999999999999', 'b4444444-4444-4444-4444-444444444444'),
       ('99999999-9999-9999-9999-999999999999', 'b5555555-5555-5555-5555-555555555555'),
       ('99999999-9999-9999-9999-999999999999', 'b6666666-6666-6666-6666-666666666666'),
       ('99999999-9999-9999-9999-999999999999', 'b7777777-7777-7777-7777-777777777777'),
       ('99999999-9999-9999-9999-999999999999', 'b8888888-8888-8888-8888-888888888888');

-- Insert Base.3 Ombros exercises and setups
INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('79934692-a240-4c0b-b9ce-a34c351c7021', 'aa555555-5555-5555-5555-555555555555', 'Desenvolvimento no smith',
        'Exercise with smith machine'),
       ('75e01be6-ef3a-4ce8-b554-ed59e7197834', 'aa555555-5555-5555-5555-555555555555',
        'Elevação frontal pronada com halter', 'Frontal elevation with dumbbell'),
       ('c81981d2-db5a-4353-a481-c938f0755deb', 'aa555555-5555-5555-5555-555555555555', 'Elevação lateral polia baixa',
        'Low pulley side lift'),
       ('8c2fea55-4ada-41e9-b9fe-2dbaada60325', 'aa555555-5555-5555-5555-555555555555', 'Crucifixo invertido peck deck',
        'Reverse flyes on pec deck machine'),
       ('b46c52f9-5b09-4c24-9f83-23cb776da9bf', 'aa555555-5555-5555-5555-555555555555', 'Posterior polia',
        'Rear delt rope pull'),
       ('ab8c090f-a35f-4b6d-8769-6d9786352bbb', 'aa555555-5555-5555-5555-555555555555', 'Elevação lateral máquina',
        'Machine lateral raise');

INSERT INTO exercise_setup (id, exercise_id, series, repetitions, rest, observation)
VALUES ('af65f4ca-0cb9-422a-bde5-a5071785fe29', '79934692-a240-4c0b-b9ce-a34c351c7021', '4', '15-12-10-8', '3-4min.',
        'Increase weight each set'),
       ('bb55c6e7-4619-4aaf-a4b6-694a1ee6ed91', '75e01be6-ef3a-4ce8-b554-ed59e7197834', '4', '12 a 15', '90s',
        'Keep form tight'),
       ('687bc951-640c-48c1-a72f-13271f81749c', 'c81981d2-db5a-4353-a481-c938f0755deb', '4', '10 a 12', '60s',
        'Do not swing arms'),
       ('cfef7c94-7049-4f59-a1db-390ec59cb975', '8c2fea55-4ada-41e9-b9fe-2dbaada60325', '4', '15-12-10-8', '90s',
        'Focus on contraction'),
       ('72a72b19-77aa-4e90-ad8e-1f90902c5295', 'b46c52f9-5b09-4c24-9f83-23cb776da9bf', '4', '8 a 12', '30-45s',
        'Controlled movements'),
       ('4d3c30a5-c080-4fd6-9233-911c5bb49e65', 'ab8c090f-a35f-4b6d-8769-6d9786352bbb', '6', '10 a 12', '60-90s',
        'Adjust machine properly');

INSERT INTO set_setup (exercise_set_id, exercise_setup_id)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'af65f4ca-0cb9-422a-bde5-a5071785fe29'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'bb55c6e7-4619-4aaf-a4b6-694a1ee6ed91'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '687bc951-640c-48c1-a72f-13271f81749c'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'cfef7c94-7049-4f59-a1db-390ec59cb975'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '72a72b19-77aa-4e90-ad8e-1f90902c5295'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '4d3c30a5-c080-4fd6-9233-911c5bb49e65');

-- Exercicios de peito

INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('271ec29e-6cf6-4ea3-85c9-43a65b7e93ba', 'aa666666-6666-6666-6666-666666666666', 'Crucifixo máquina',
        'Exercise with smith machine'),
       ('bf8279d2-968a-4fd0-9138-0f6ecb666b26', 'aa666666-6666-6666-6666-666666666666',
        'Supino reto com halter', 'Frontal elevation with dumbbell'),
       ('5a22cd44-69d8-4d5e-be0a-bae3b3008dc0', 'aa666666-6666-6666-6666-666666666666', 'Supino inclinado com halter',
        'Supino inclinado com halter');


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
