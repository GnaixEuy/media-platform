CREATE TABLE `media`.`user`
(
    `id`                VARCHAR(32)  NOT NULL COMMENT '用户id',
    `user_nickname`     varchar(32)  NULL COMMENT '用户昵称',
    `user_birthday`     datetime     NULL COMMENT '用户生日',
    `user_gender`       int          NOT NULL DEFAULT 0 COMMENT '用户性别，UNKNOWN - 0 - 未知 MALE - 1 - 男 FEMALE - 2 - 女',
    `user_phone`        varchar(64)  NULL COMMENT '用户手机号',
    `password`          varchar(128) NULL COMMENT '用户密码',
    `user_city`         varchar(128) NULL COMMENT '用户城市',
    `locked`            tinyint      NOT NULL DEFAULT 0 COMMENT '账户是否被锁定',
    `enabled`           tinyint      NOT NULL DEFAULT 1 COMMENT '账户是否启用',
    `created_date_time` datetime     NULL COMMENT '创建时间',
    `updated_date_time` datetime     NULL COMMENT '更新时间',
    `last_login_ip`     varchar(128) NULL COMMENT '最后登陆ip',
    `last_login_time`   datetime     NULL COMMENT '最后登陆时间',
    `open_id_qq`        varchar(255) NULL COMMENT 'Qq open id',
    PRIMARY KEY (`id`)
) COMMENT = '用户基础信息表';