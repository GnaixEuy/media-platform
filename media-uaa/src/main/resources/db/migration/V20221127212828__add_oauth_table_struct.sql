-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户端标识',
    `resource_ids`            varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接入资源列表',
    `client_secret`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端秘钥',
    `scope`                   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权范围',
    `authorized_grant_types`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '允许授权类型',
    `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端的重定向URI',
    `authorities`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端所拥有的权限值',
    `access_token_validity`   int(11)                                                 NULL DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒)',
    `refresh_token_validity`  int(11)                                                 NULL DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒',
    `additional_information`  text CHARACTER SET utf8 COLLATE utf8_general_ci         NULL COMMENT '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据,',
    `create_time`             timestamp(0)                                            NULL DEFAULT NULL,
    `archived`                tinyint(1)                                              NULL DEFAULT NULL COMMENT '用于标识客户端是否已存档(即实现逻辑删除),默认值为’0’(即未存档)',
    `trusted`                 tinyint(1)                                              NULL DEFAULT NULL COMMENT '设置客户端是否为受信任的,默认为’0’(即不受信任的,1为受信任的)',
    `autoapprove`             varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设置用户是否自动Approval操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’. ',
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details`
VALUES ('c1', 'res1', '$2a$10$UmGvnYkVcMwvv.Tki2hd2.1TwSbB3FmQuJDduq0cnIVoCYkvAh5Ey', 'ROLE_ADMIN,ROLE_USER,ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 259200,
        31536000, NULL, '2020-07-12 21:55:48', 0, 0, 'false');
INSERT INTO `oauth_client_details`
VALUES ('c2', 'res2', '$2a$10$NlBC84MVb7F95EXYTXwLneXgCca6/GipyWR5NHm8K0203bSQMLpvm', 'ROLE_API',
        'client_credentials,password,authorization_code,implicit,refresh_token', 'http://www.baidu.com', NULL, 259200,
        31536000, NULL, '2020-07-12 21:55:48', 0, 0, 'false');

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `create_time`    timestamp(0)                                            NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据的创建时间',
    `code`           varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存储服务端系统生成的code的值(未加密)',
    `authentication` blob                                                    NULL COMMENT '存储将AuthorizationRequestHolder.java对象序列化后的二进制数据.',
    INDEX `code_index` (`code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;
