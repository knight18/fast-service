DROP TABLE IF EXISTS `fs_sys_user`;
CREATE TABLE IF NOT EXISTS `fs_sys_user` (
    `USER_CODE` varchar(32) NOT NULL,
    `USER_NAME` varchar(100) DEFAULT NULL,
    `MOBILE` varchar(32) DEFAULT NULL,
    `PHONE` varchar(20) DEFAULT NULL,
    `EMAIL` varchar(256) DEFAULT NULL,
    `ADDRESS` varchar(256) DEFAULT NULL,
    `USER_TYPE` varchar(8) DEFAULT NULL,
    `LAST_LOGIN_TIME` datetime DEFAULT NULL,
    `CANCEL_DATE` datetime DEFAULT NULL,
    `GMT_CREATED` datetime DEFAULT NULL,
    `GMT_MODIFIED` datetime DEFAULT NULL,
    `CREATOR` varchar(32) DEFAULT NULL,
    `OPERATOR` varchar(32) DEFAULT NULL,
    `USER_STATUS` varchar(2) DEFAULT NULL,
    `LOCK_STATUS` varchar(8) DEFAULT NULL,
    `USER_PWD` varchar(100) DEFAULT NULL,
    `CARD_TYPE` varchar(255) DEFAULT NULL,
    `CARD_NO` varchar(255) DEFAULT NULL,
    `REMARK` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`USER_CODE`),
    UNIQUE KEY `idx_username` (`USER_NAME`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表';
