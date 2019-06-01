CREATE TABLE `pay_api` (
    `pay_api_id` bigint unsigned comment '支付实现ID',
    `pay_api_name` varchar(128) not null comment '支付名称',
    `pay_way` tinyint unsigned not null comment '支付类型',
    `merchant_id` bigint unsigned not null comment '商户ID,关联表pay_merchant.merchant_id',
    `notify_url` varchar(256) not null comment '回调通知地址',
    `return_url` varchar(256) comment '回调页面地址',
    `ctime` bigint unsigned not null comment '创建时间',
    `utime` bigint unsigned not null comment '更新时间',
    PRIMARY KEY (`pay_api_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付实现信息表';

CREATE TABLE `pay_merchant` (
    `merchant_id` bigint unsigned comment '主键ID',
    `merchant_name` varchar(128) not null comment '商户名称',
    `partner_id` varchar(128) not null comment '商户ID',
    `app_id` varchar(128) not null comment '商户ID,关联表pay_merchant.merchant_id',
    `merchant_status` tinyint unsigned not null comment '商户状态：1.正常 2.被封',
    `pay_way` tinyint unsigned not null comment '支付类型',
    `sign_type` varchar(32) comment '签名方式：rsa或rsa2 目前只用于支付宝',
    `private_key` mediumtext not null comment '支付密钥',
    `public_key` mediumtext comment '支付公钥 目前只用于支付宝',
    `ctime` bigint unsigned not null comment '创建时间',
    `utime` bigint unsigned not null comment '更新时间',
    PRIMARY KEY (`merchant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付商户信息表';

CREATE TABLE `pay_api_mapping` (
   `mapping_id` bigint unsigned comment '主键ID',
   `pay_api_id` bigint unsigned not null comment '支付实现ID,关联表pay_api',
   `pay_way` tinyint unsigned not null comment '支付类型',
   `pay_scene` tinyint unsigned not null comment '支付场景',
   `system_type` tinyint unsigned not null comment '系统类型：1.iOS 2.Android 3.PC',
   `sort` tinyint unsigned not null comment '页面排序',
   `remark` varchar(128) comment '备注',
   `ctime` bigint unsigned not null comment '创建时间',
   `utime` bigint unsigned not null comment '更新时间',
   PRIMARY KEY (`mapping_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付方式与支付场景映射表';

CREATE TABLE `order_base` (
    `order_id` bigint unsigned comment '订单ID',
    `uid` bigint unsigned not null comment '用户ID',
    `order_price` decimal(20,5) not null comment '订单价格，单位：元',
    `pay_price` decimal(20,5) not null comment '支付价格，单位：元',
    `order_type` tinyint unsigned not null comment '订单类型',
    `order_status` tinyint unsigned not null comment '订单状态 1.待付款 2.已付款，待处理 3.交易成功 4.交易失败',
    `deliver_status` tinyint unsigned not null comment '发货状态 1.待发货 2.发货中 3.已发货 4.发货失败',
    `pay_api_id` bigint unsigned not null comment '支付实现ID',
    `pay_way` tinyint unsigned not null comment '支付类型',
    `merchant_id` bigint unsigned not null comment 'pay_merchant主键ID',
    `pay_time` bigint unsigned comment '支付完成时间',
    `ctime` bigint unsigned not null comment '创建时间',
    `utime` bigint unsigned not null comment '更新时间',
    PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础订单表';

CREATE TABLE `pay_flow` (
    `flow_id` bigint unsigned NOT NULL COMMENT '支付流水id',
    `order_id` bigint unsigned NOT NULL COMMENT '交易订单id',
    `out_trade_id` varchar(128) DEFAULT NULL COMMENT '第三方订单id',
    `uid` bigint unsigned not null comment '用户ID',
    `pay_status` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '支付状态 1.未支付 2.支付中 3.支付成功 4.支付失败',
    `pay_price` decimal(20,5) NOT NULL COMMENT '支付金额，单位：元',
    `pay_way` tinyint unsigned NOT NULL COMMENT '支付方式',
    `pay_api_id` bigint unsigned NOT NULL COMMENT '支付实现',
    `merchant_id` bigint unsigned NOT NULL COMMENT '商户ID,关联表pay_merchant.merchant_id',
    `ctime` bigint unsigned not null comment '创建时间',
    `utime` bigint unsigned not null comment '更新时间',
    PRIMARY KEY (`flow_id`),
    UNIQUE KEY `uk_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付流水表';