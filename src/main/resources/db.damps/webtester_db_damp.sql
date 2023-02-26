DROP TABLE IF EXISTS result;
DROP TABLE IF EXISTS answer;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS test;
DROP TABLE IF EXISTS account_registration;
DROP TABLE IF EXISTS account_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS account;

CREATE TABLE account
(
    id          bigint primary key      not null,
    login       varchar(50) unique      not null,
    password    varchar(255)            not null,
    first_name  varchar(100)            not null,
    middle_name varchar(100),
    last_name   varchar(100)            not null,
    email       varchar(100) unique     not null,
    active      boolean   default true  not null,
    created     timestamp default now() not null
);
CREATE SEQUENCE IF NOT EXISTS public.account_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.account_seq OWNER TO webtester;

CREATE TABLE role
(
    id   smallint primary key not null,
    name varchar(15)          not null
);

CREATE TABLE account_role
(
    id         bigint primary key not null,
    id_account bigint             not null,
    id_role    smallint           not null,

    foreign key (id_account) references account (id)
        on delete cascade on update cascade,
    foreign key (id_role) references role (id)
        on delete cascade on update cascade
);
CREATE SEQUENCE IF NOT EXISTS public.account_role_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.account_role_seq OWNER TO webtester;
CREATE UNIQUE INDEX IF NOT EXISTS account_role_inx ON account_role USING btree (id_account, id_role);



CREATE TABLE account_registration
(
    id   bigint primary key  not null,
    code varchar(255) unique not null,

    foreign key (id) references account (id)
        on delete cascade on update cascade
);

CREATE TABLE test
(
    id                bigint primary key not null,
    id_account        bigint             not null,
    name              varchar(100)       not null,
    description       text               not null,
    time_per_question integer default 30 not null,

    foreign key (id_account) references account (id)
        on delete cascade on update cascade
);
CREATE SEQUENCE IF NOT EXISTS public.test_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.test_seq OWNER TO webtester;

CREATE TABLE question
(
    id      bigint primary key not null,
    id_test bigint             not null,
    name    varchar(100)       not null,

    foreign key (id_test) references test (id)
        on delete cascade on update cascade
);
CREATE SEQUENCE IF NOT EXISTS public.question_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.question_seq OWNER TO webtester;

CREATE TABLE answer
(
    id          bigint primary key    not null,
    id_question bigint                not null,
    name        varchar(100)          not null,
    correct     boolean default false not null,

    foreign key (id_question) references question (id)
        on delete cascade on update cascade
);
CREATE SEQUENCE IF NOT EXISTS public.answer_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.answer_seq OWNER TO webtester;

CREATE TABLE result
(
    id               bigint primary key      not null,
    id_account       bigint                  not null,
    id_test          bigint,
    percent          double precision        not null,
    test_name        varchar(100)            not null,
    test_description text                    not null,
    created          timestamp default now() not null,

    foreign key (id_account) references account (id)
        on delete cascade on update cascade,
    foreign key (id_test) references test (id)
        on delete set null on update cascade
);
CREATE SEQUENCE IF NOT EXISTS public.result_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER SEQUENCE public.result_seq OWNER TO webtester;