create table exercise
(
    id                uuid        not null,
    muscular_group_id uuid        not null,
    name              varchar(56) not null,
    description       varchar(255),
    primary key (id)
);
create table exercise_set
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
create table exercise_setup
(
    exercise_id uuid,
    id          uuid not null,
    observation varchar(255),
    repetitions varchar(255),
    rest        varchar(255),
    series      varchar(255),
    primary key (id)
);
create table musculargroup
(
    id   uuid not null,
    name varchar(255),
    primary key (id)
);
create table sets
(
    exercise_set_id uuid not null,
    workout_id      uuid not null
);
create table setups
(
    exercise_set_id  uuid not null,
    exercse_setup_id uuid not null
);
create table users
(
    id       uuid not null,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    primary key (id)
);
create table workout
(
    id          uuid not null,
    description varchar(255),
    name        varchar(255),
    primary key (id)
);
alter table if exists exercise add constraint FKiyd6mjtyrrkar3o66imf2g5c2 foreign key (muscular_group_id) references musculargroup;
alter table if exists exercise_setup add constraint FKkmwm8mr90kshiyab3qvfhb1ug foreign key (exercise_id) references exercise;
alter table if exists sets add constraint FK4pnf33wnvdyxrrr3r5o6lppkp foreign key (exercise_set_id) references exercise_set;
alter table if exists sets add constraint FKsma7f5gd7tnr5s3himppb69nx foreign key (workout_id) references workout;
alter table if exists setups add constraint FKl61gdm70j59wutiv3e23hi2h3 foreign key (exercse_setup_id) references exercise_setup;
alter table if exists setups add constraint FK75awtjpkyvwrvbyg478xk44g foreign key (exercise_set_id) references exercise_set;
