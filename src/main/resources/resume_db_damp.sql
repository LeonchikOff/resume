drop table if exists skill_category;
drop table if exists skill;
drop table if exists profile_restore;
drop table if exists practice;
drop table if exists language;
drop table if exists hobby;
drop table if exists education;
drop table if exists course;
drop table if exists certificate;
drop table if exists profile;

drop sequence if exists certificate_seq;
drop sequence if exists course_seq;
drop sequence if exists education_seq;
drop sequence if exists hobby_seq;
drop sequence if exists language_seq;
drop sequence if exists practice_seq;
drop sequence if exists profile_seq;
drop sequence if exists skill_seq;
------------------------------------------------------------------------------------
create table profile
(
    id            bigint primary key      not null,
    uid           varchar(100) unique     not null,
    first_name    varchar(50)             not null,
    last_name     varchar(50)             not null,
    birth_day     date,
    phone         varchar(20) unique,
    email         varchar(100) unique,
    county        varchar(60),
    city          varchar(100),
    objective     text,
    summary       text,
    large_photo   varchar(255),
    small_photo   varchar(255),
    info          text,
    password      varchar(255)            not null,
    completed     boolean                 not null,
    created       timestamp default now() not null,
    skype         varchar(80),
    vkontakte     varchar(255),
    facebook      varchar(255),
    linkedin      varchar(255),
    github        varchar(255),
    stackoverflow varchar(255)
);
create sequence public.profile_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.profile_seq owner to resume;
------------------------------------------------------------------------------------
create table certificate
(
    id         bigint primary key not null,
    id_profile bigint             not null,
    name       varchar(50)        not null,
    large_url  varchar(255)       not null,
    small_url  varchar(255)       not null,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.certificate_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.certificate_seq owner to resume;
------------------------------------------------------------------------------------
create table course
(
    id          bigint primary key not null,
    id_profile  bigint             not null,
    name        varchar(60)        not null,
    school      varchar(60)        not null,
    finish_date date,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.course_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.course_seq owner to resume;
------------------------------------------------------------------------------------
create table education
(
    id          bigint primary key not null,
    id_profile  bigint             not null,
    summary     varchar(255)       not null,
    begin_year  integer            not null,
    finish_year integer,
    university  text               not null,
    faculty     varchar(255)       not null,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.education_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.education_seq owner to resume;
------------------------------------------------------------------------------------
create table hobby
(
    id         bigint primary key not null,
    id_profile bigint             not null,
    name       varchar(30)        not null,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.hobby_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.hobby_seq owner to resume;
------------------------------------------------------------------------------------
create table language
(
    id         bigint primary key   not null,
    id_profile bigint               not null,
    name       varchar(30)          not null,
    level      varchar(18)          not null,
    type       varchar(7) default 0 not null,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.language_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.language_seq owner to resume;
------------------------------------------------------------------------------------
create table practice
(
    id               bigint primary key not null,
    id_profile       bigint             not null,
    position         varchar(100)       not null,
    company          varchar(100)       not null,
    begin_date       date               not null,
    finish_date      date,
    responsibilities text               not null,
    demo             varchar(255),
    src              varchar(255),

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.practice_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.practice_seq owner to resume;
------------------------------------------------------------------------------------
create table skill
(
    id         bigint primary key not null,
    id_profile bigint             not null,
    category   varchar(50)        not null,
    value      text               not null,

    foreign key (id_profile) references profile (id)
        on delete cascade on update cascade
);
create sequence public.skill_seq
    increment 1
    start 1
    minvalue 1
    maxvalue 9223372036854775807
    cache 1;
alter sequence public.skill_seq owner to resume;
------------------------------------------------------------------------------------
create table skill_category
(
    id       bigint primary key not null,
    category varchar(50) unique not null
);
------------------------------------------------------------------------------------
create table profile_restore
(
    id    bigint primary key  not null,
    token varchar(255) unique not null,
    foreign key (id) references profile (id)
        on delete cascade on update cascade
);
------------------------------------------------------------------------------------
create index certificate_idx on certificate using btree (id_profile);
create index course_idx on course using btree (finish_date);
create index course_idx1 on course using btree (id_profile);
create index education_idx on education using btree (id_profile);
create index education_idx1 on education using btree (finish_year);
create index hobby_idx on hobby using btree (id_profile);
create index language_idx on language using btree (id_profile);
create index practice_idx on practice using btree (id_profile);
create index practice_idx1 on practice using btree (finish_date);
create index skill_idx on skill using btree (id_profile);
------------------------------------------------------------------------------------

















