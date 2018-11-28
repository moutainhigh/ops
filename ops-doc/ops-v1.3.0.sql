##加油站加油员信息表
CREATE TABLE `t_oil_attendant` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '姓名',
  `code` varchar(20) NOT NULL COMMENT '工号',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT '是否有效：1,有效；0,无效',
  `station_id` int(11) NOT NULL COMMENT '加油站id',
  `oil_company_id` int(11) NOT NULL COMMENT '油企id',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='加油站加油员信息表';

##额度，有效期调整记录表
CREATE TABLE `t_logistics_quota_adjust` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `logistics_id` int(11) NOT NULL COMMENT '物流企业id',
  `quota` decimal(20,0) NOT NULL DEFAULT '0' COMMENT '调整的额度值，单位（分），续期时额度值为0',
  `type` tinyint(2) NOT NULL COMMENT '类型：1，额度调整，2，授信延期',
  `start_date` date NOT NULL COMMENT '有效期开始时间',
  `end_date` date NOT NULL COMMENT '有效期结束时间',
  `audit_time` datetime NOT NULL COMMENT '审批时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='额度，有效期调整记录表';

##新增企业额度类型
ALTER TABLE `t_logistics_credit_quota`
ADD COLUMN `basic_quota_type`  tinyint(2) NULL DEFAULT 1 COMMENT '企业额度类型:1，授信额度；2，预充值额度' AFTER `credit_quota`;