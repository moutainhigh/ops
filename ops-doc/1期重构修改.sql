ALTER TABLE `t_order`
ADD COLUMN `order_type` int(4) NULL DEFAULT 1 COMMENT '订单类型：1正常，2退单，3补单' AFTER `code`;

ALTER TABLE `t_oil_contact`
ADD COLUMN `type` int(4) NULL DEFAULT 0 COMMENT '用途 1：订单 2：价格' AFTER `station_id`;

ALTER TABLE `t_oil_company`
MODIFY COLUMN `build_date` date NULL DEFAULT NULL COMMENT '成立日期' AFTER `ownership`;

ALTER TABLE `t_system_user`
ADD COLUMN `user_center_id` int(11) NULL DEFAULT 0 COMMENT '用户中心编号' AFTER `user_id`;

-- 修改补单数据
UPDATE t_order SET order_type = 3 WHERE remark LIKE '人工补单:%';

-- 上传原附件数据到腾讯云
