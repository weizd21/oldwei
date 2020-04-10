## 数据库脚本

### 商品表 d_product

```
CREATE TABLE `d_product` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `description` varchar(255) NOT NULL COMMENT '商品介绍',
  `leave_num` bigint(20) NOT NULL COMMENT '剩余数量',
  `create_by` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(10) unsigned DEFAULT '1' COMMENT '版本号',
  `deleted` int(10) unsigned DEFAULT '0' COMMENT '是否删除标志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### 用户表

```
CREATE TABLE `d_user` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_code` varchar(100) NOT NULL COMMENT '用户编码',
  `username` varchar(100) NOT NULL COMMENT '用户名称',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `phone` varchar(11) DEFAULT '' COMMENT '用户手机号',
  `mobile` varchar(11) DEFAULT '' COMMENT '用户电话',
  `email` varchar(100) DEFAULT '' COMMENT '用户邮箱',
  `description` varchar(255) NOT NULL COMMENT '用户介绍',
  `account` decimal(20,2) DEFAULT '0.00' COMMENT '账户余额',
  `create_by` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_by` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int(10) unsigned DEFAULT '1' COMMENT '版本号',
  `deleted` int(10) unsigned DEFAULT '0' COMMENT '是否删除标志',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```


