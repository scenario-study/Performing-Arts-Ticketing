CREATE TABLE performance_info
(
    id         bigint auto_increment primary key,
    perf_name  varchar(255) not null,
    perf_desc  text         not null,
    perf_venue varchar(30),
    start_date timestamp,
    end_date   timestamp,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE performance_date
(
    id         bigint auto_increment primary key,
    perf_id    bigint    not null,
    perf_time  timestamp not null,
    created_at timestamp,
    updated_at    timestamp
);

CREATE TABLE performance_price
(
    id         bigint auto_increment primary key,
    perf_id    bigint not null,
    perf_price int unsigned not null,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE performance_discount
(
    id            bigint auto_increment primary key,
    perf_id       bigint not null,
    perf_dc_price int unsigned not null,
    created_at    timestamp,
    updated_at    timestamp
);

CREATE TABLE history_performance_discount
(
    id            bigint auto_increment primary key,
    perf_id       bigint not null,
    perf_dc_id    bigint not null,
    seq           int unsigned not null,
    perf_dc_price int unsigned not null,
    created_at    timestamp
);
