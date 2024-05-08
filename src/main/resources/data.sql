-- Insert data into `users` table
INSERT INTO users (id, email, name, password)
VALUES ('11111111-1111-1111-1111-111111111111', 'example@domain.com', 'Carlos Adriano',
        '$2a$10$KUT1.LmQPRvek0nbL74pKOn23fKR52.7Cmr6vOtomD5zaoZ8eNC4C'),
       ('22222222-2222-2222-2222-222222222222', 'jane.doe@example.com', 'Jane Doe', 'encryptedpassword2');

-- Insert data into `muscular_group` table
INSERT INTO muscular_group (id, name)
VALUES ('33333333-3333-3333-3333-333333333333', 'Upper Body'),
       ('44444444-4444-4444-4444-444444444444', 'Lower Body');

-- Insert data into `workout` table
INSERT INTO workout (id, users_id, created_by_id, name, description)
VALUES ('55555555-5555-5555-5555-555555555555', '11111111-1111-1111-1111-111111111111',
        '11111111-1111-1111-1111-111111111111', 'Morning Routine', 'A complete morning workout routine'),
       ('66666666-6666-6666-6666-666666666666', '22222222-2222-2222-2222-222222222222',
        '11111111-1111-1111-1111-111111111111', 'Evening Routine', 'A relaxing evening workout routine');

-- Insert data into `exercise` table
INSERT INTO exercise (id, muscular_group_id, name, description)
VALUES ('77777777-7777-7777-7777-777777777777', '33333333-3333-3333-3333-333333333333', 'Push Ups',
        'Standard push-ups exercise'),
       ('88888888-8888-8888-8888-888888888888', '44444444-4444-4444-4444-444444444444', 'Squats',
        'Basic squats exercise');

-- Insert data into `exercise_set` table
INSERT INTO exercise_set (id, workout_id, name, description)
VALUES ('99999999-9999-9999-9999-999999999999', '55555555-5555-5555-5555-555555555555', 'Set 1',
        'The first set of the routine'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '66666666-6666-6666-6666-666666666666', 'Set 2',
        'The second set of the routine');

-- Insert data into `exercise_setup` table
INSERT INTO exercise_setup (id, exercise_id, exercise_set_id, series, repetitions, rest, observation)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '77777777-7777-7777-7777-777777777777',
        '99999999-9999-9999-9999-999999999999', '3', '20', '30s', 'Focus on form'),
       ('cccccccc-cccc-cccc-cccc-cccccccccccc', '88888888-8888-8888-8888-888888888888',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '3', '15', '45s', 'Keep back straight');

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
