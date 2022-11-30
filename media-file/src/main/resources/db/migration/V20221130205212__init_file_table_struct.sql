-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `id`                 varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '文件ID',
    `name`               varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '文件名',
    `file_key`           varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '文件hash值，即ObjectKey',
    `ext`                varchar(12) COLLATE utf8mb4_bin NOT NULL COMMENT '文件后缀名',
    `size`               int                             NOT NULL DEFAULT '0' COMMENT '文件大小；单位byte',
    `type`               int                             NOT NULL DEFAULT '0' COMMENT '文件类型，1-AUDIO-音频，2-IMAGE-图片，3-VIDEO-视频，0-OTHER-其他',
    `storage`            varchar(16) COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '存储供应商，1-COS-腾讯云存储，2-OSS-阿里云存储 3-Minio-自建文件存储服务',
    `status`             int                             NOT NULL DEFAULT '1' COMMENT '文件状态，1-UPLOADING-上传中，2-UPLOADED-已上传，3-CANCEL-已取消',
    `created_by_user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '创建者用户ID',
    `updated_by_user_id` varchar(32) COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '更新者用户ID',
    `created_date_time`  datetime(6)                     NOT NULL COMMENT '创建时间',
    `updated_date_time`  datetime(6)                     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `c_created_by_user_id` (`created_by_user_id`),
    KEY `c_updated_by_user_id` (`updated_by_user_id`),
    CONSTRAINT `c_created_by_user_id` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `c_updated_by_user_id` FOREIGN KEY (`updated_by_user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='文件表';
