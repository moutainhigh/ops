-- ops二期sql

-- 添加开启定位
ALTER TABLE `t_driver`
ADD COLUMN `open_location` tinyint(2) DEFAULT '1' COMMENT '是否开启定位：0，不开启，1，开启';

-- 新增支付渠道费率字段
ALTER TABLE `t_oil_station_apply`
ADD COLUMN `rate`  decimal(20,6) NULL DEFAULT 0.000000 COMMENT '支付渠道费率' AFTER `contact_phone`;

ALTER TABLE `t_oil_station`
ADD COLUMN `rate`  decimal(20,6) NULL DEFAULT 0.000000 COMMENT '支付渠道费率' AFTER `contact_phone`;

ALTER TABLE `t_vehicle`
ADD COLUMN `state`  int(2) NULL DEFAULT 1 COMMENT '是否启用: 0禁用，1启用' AFTER `status`;


-- 价格审批记录表
CREATE TABLE `t_oil_price_approval_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apply_id` int(11) NOT NULL DEFAULT '0' COMMENT '申请id',
  `operator_id` int(11) NOT NULL DEFAULT '0' COMMENT '审批人id',
  `operator_name` varchar(64) DEFAULT '' COMMENT '审批人姓名',
  `create_time` datetime NOT NULL COMMENT '审批时间',
  `action` int(4) NOT NULL DEFAULT '0' COMMENT '审批动作：7-同意，3-驳回',
  `remark` varchar(512) DEFAULT '' COMMENT '审批建议',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='价格审批记录';

## 油价调整信息表
CREATE TABLE `t_oil_price_adjust_msg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) NOT NULL DEFAULT '' COMMENT '油价调整的内容',
  `price_id` int(11) NOT NULL COMMENT '关联t_oil_price表字段price_id',
  `company_id` int(11) NOT NULL COMMENT '油企业id',
  `station_id` int(11) NOT NULL COMMENT '油站id',
  `effect_time` datetime NOT NULL COMMENT '生效时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='油价调整信息表';

-- 物流还款记录表
CREATE TABLE `t_logistics_repayment_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `logistics_id` int(11) DEFAULT '0' COMMENT '物流企业id',
  `tx_no` varchar(128) DEFAULT '' COMMENT '财务唯一流水号',
  `amount` decimal(20,2) DEFAULT '0.00' COMMENT '还款金额（单位分）',
  `status` int(4) DEFAULT '0' COMMENT '状态：0还款失败，1还款成功',
  `status_time` datetime DEFAULT NULL COMMENT '状态时间',
  `remark` varchar(256) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tx_no` (`tx_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物流企业还款记录';

-- 打印机
CREATE TABLE `t_oil_print` (
  `print_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `station_id` int(11) NOT NULL COMMENT '油站编码',
  `station_name` varchar(255) DEFAULT '' COMMENT '油站名称',
  `print_sn` varchar(32) NOT NULL COMMENT '打印机编号',
  `print_name` varchar(64) DEFAULT '' COMMENT '打印机名称',
  `sim_card` varchar(32) DEFAULT '' COMMENT '流量卡号码',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` int(11) DEFAULT '0' COMMENT '创建用户',
  `update_user_id` int(11) DEFAULT '0' COMMENT '更新用户',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`print_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='油站打印机管理';