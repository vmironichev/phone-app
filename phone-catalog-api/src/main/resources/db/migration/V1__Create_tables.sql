create table `phone` (
    `uuid` binary(16) not null,
    `price_uuid` binary(16) not null,
    `image_url` varchar(2048) not null,
    `name` varchar(128) not null,
    `description` tinytext not null,
    `created_ts` timestamp not null default current timestamp,
    `updated_ts` timestamp not null default current timestamp on update current timestamp,
    primary key(`uuid`)
) engine=InnoDB default charset=utf8;

create table `price` (
    `uuid` binary(16) not null,
    `amount` decimal(10,2) not null,
    `currency_code` varchar(2048) not null,
    `created_ts` timestamp not null default current timestamp,
    `updated_ts` timestamp not null default current timestamp on update current timestamp,
    primary key(uuid)
) engine=InnoDB default charset=utf8;