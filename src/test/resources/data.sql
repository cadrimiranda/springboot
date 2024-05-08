INSERT INTO users(id, name, email, password)
VALUES ('c4c625e3-0f4b-4814-b45d-efa4531196c4', 'Carlos Adriano', 'example@domain.com',
        '$2a$10$KUT1.LmQPRvek0nbL74pKOn23fKR52.7Cmr6vOtomD5zaoZ8eNC4C');

INSERT INTO musculargroup(id, name)
VALUES ('018f2eac-4126-7711-86f4-2855ce971c34', 'biceps');

INSERT INTO exercise(id, name, muscular_group_id)
VALUES ('018f2eb0-a234-702a-ab7b-841ad5aa2e8a', 'Rosca Direta', '018f2eac-4126-7711-86f4-2855ce971c34');

INSERT INTO exercise_setup(id, series, repetitions, rest, observation, exercise_id)
VALUES ('018f2eb2-117c-74cf-bf95-c4385eb4672d', '4', '12-10-8-8', '120s', 'Sem balangar o braco',
        '018f2eb0-a234-702a-ab7b-841ad5aa2e8a');