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



