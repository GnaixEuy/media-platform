CREATE TABLE `media`.`user_role`
(
    `user_id` VARCHAR(32) NOT NULL COMMENT '用户id',
    `role_id` varchar(32) NOT NULL COMMENT '角色id',
    CONSTRAINT `c_user_id` FOREIGN KEY (`user_id`) REFERENCES `media`.`user` (`id`),
    CONSTRAINT `c_role_id` FOREIGN KEY (`role_id`) REFERENCES `media`.`role` (`id`)
) COMMENT = '角色关联表';