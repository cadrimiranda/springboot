create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group (id uuid not null, name varchar(255), primary key (id));
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
create table anthropometric_report
(
    id                                 uuid      not null,
    abdomen_circumference              float(53) not null,
    abdominal_skinfold                 float(53) not null,
    arm_fat_area                       float(53) not null,
    arm_muscle_area                    float(53) not null,
    bmi                                float(53) not null,
    body_density                       float(53) not null,
    body_fat_mass                      float(53) not null,
    chest_circumference                float(53) not null,
    chest_skinfold                     float(53) not null,
    contracted_left_arm_circumference  float(53) not null,
    contracted_right_arm_circumference float(53) not null,
    height                             float(53) not null,
    ideal_weight                       float(53) not null,
    lean_mass                          float(53) not null,
    left_calf_circumference            float(53) not null,
    left_forearm_circumference         float(53) not null,
    left_thigh_circumference           float(53) not null,
    mid_axillary_skinfold              float(53) not null,
    percentage_body_fat                float(53) not null,
    percentage_lean_mass               float(53) not null,
    relaxed_left_arm_circumference     float(53) not null,
    relaxed_right_arm_circumference    float(53) not null,
    report_date                        date      not null,
    right_calf_circumference           float(53) not null,
    right_forearm_circumference        float(53) not null,
    right_thigh_circumference          float(53) not null,
    shoulder_circumference             float(53) not null,
    subscapular_skinfold               float(53) not null,
    sum_of_skinfolds                   float(53) not null,
    suprailiac_skinfold                float(53) not null,
    thigh_skinfold                     float(53) not null,
    triceps_skinfold                   float(53) not null,
    waist_circumference                float(53) not null,
    weight                             float(53) not null,
    users_id                           uuid      not null,
    primary key (id)
);
create table exercise
(
    id                uuid        not null,
    description       varchar(255),
    name              varchar(56) not null,
    muscular_group_id uuid        not null,
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    workout_id  uuid,
    primary key (id)
);
create table exercise_setup
(
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    exercise_id uuid,
    primary key (id)
);
create table muscular_group
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table set_setup
(
    exercise_set_id   uuid not null,
    exercise_setup_id uuid not null
);
create table user_workout
(
    user_id    uuid not null,
    workout_id uuid not null
);
create table users
(
    id                uuid not null,
    email             varchar(255),
    name              varchar(255),
    password          varchar(255),
    active_workout_id uuid,
    primary key (id)
);
create table workout
(
    id            uuid not null,
    description   varchar(255),
    name          varchar(255),
    created_by_id uuid,
    primary key (id)
);
alter table if exists anthropometric_report add constraint FKfc21ne651huxw9yc6dy4femig foreign key (users_id) references users;
alter table if exists exercise add constraint FKdhly9nwp8agqc8e1k4e1h2vt6 foreign key (muscular_group_id) references muscular_group;
alter table if exists exercise_set add constraint FKhahvdyx4s9kxgjj163cb67qj7 foreign key (workout_id) references workout;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists set_setup add constraint FKldwse75jicssm7hv2ndsxv3jr foreign key (exercise_setup_id) references exercise_setup;
alter table if exists set_setup add constraint FKrk82rw83m5c0vswm8afsiesmb foreign key (exercise_set_id) references exercise_set;
alter table if exists user_workout add constraint FK8ijj3n51qashihgce2xp61r5t foreign key (workout_id) references workout;
alter table if exists user_workout add constraint FKdtpk4f8xe1dbf40bq3gkmageb foreign key (user_id) references users;
alter table if exists users add constraint FKmccddlum1f6tmdre7vnx6ilaa foreign key (active_workout_id) references workout;
alter table if exists workout add constraint FKlfu214l35p571pvmwwu9okowh foreign key (created_by_id) references users;
