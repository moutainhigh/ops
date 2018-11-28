package com.jyblife.logic.wl.ops.external.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.BankKeeperStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.OrderStatusEnum;
import com.jyblife.logic.wl.ops.common.enums.OrderTypeEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.PoolUtils;
import com.jyblife.logic.wl.ops.common.utils.PrintUtils;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.core.redis.RedissLock;
import com.jyblife.logic.wl.ops.entity.Driver;
import com.jyblife.logic.wl.ops.entity.DriverVehicleRelation;
import com.jyblife.logic.wl.ops.entity.LogisticsCompany;
import com.jyblife.logic.wl.ops.entity.LogisticsCreditQuota;
import com.jyblife.logic.wl.ops.entity.LogisticsDailyQuota;
import com.jyblife.logic.wl.ops.entity.LogisticsDailyQuotaLog;
import com.jyblife.logic.wl.ops.entity.LogisticsQuota;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLimit;
import com.jyblife.logic.wl.ops.entity.LogisticsQuotaLog;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.entity.OilContact;
import com.jyblife.logic.wl.ops.entity.OilGoods;
import com.jyblife.logic.wl.ops.entity.OilPrice;
import com.jyblife.logic.wl.ops.entity.OilStation;
import com.jyblife.logic.wl.ops.entity.Order;
import com.jyblife.logic.wl.ops.entity.Vehicle;
import com.jyblife.logic.wl.ops.entity.VehicleDailyQuota;
import com.jyblife.logic.wl.ops.entity.VehicleDailyQuotaLog;
import com.jyblife.logic.wl.ops.entity.VehicleQuotaLimit;
import com.jyblife.logic.wl.ops.external.dto.req.ReqLogicConsumeStatisticsDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqOrderSubmitDto;
import com.jyblife.logic.wl.ops.external.dto.req.ReqUserOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespDriverVehicleInfoDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicConsumeStatisticsDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespLogicOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilCompanyOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOilOrderPrintDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOrderDetailDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespOrderSubmitDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderListDto;
import com.jyblife.logic.wl.ops.external.dto.resp.RespUserOrderVehicleDto;
import com.jyblife.logic.wl.ops.external.mapper.DriverMapper;
import com.jyblife.logic.wl.ops.external.mapper.DriverVehicleRelationMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsCompanyMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsCreditQuotaMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsDailyQuotaLogMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsDailyQuotaMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsQuotaLimitMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsQuotaLogMapper;
import com.jyblife.logic.wl.ops.external.mapper.LogisticsQuotaMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilCompanyMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilContactMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilGoodsMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilPriceMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilPrintMapper;
import com.jyblife.logic.wl.ops.external.mapper.OilStationMapper;
import com.jyblife.logic.wl.ops.external.mapper.OrderMapper;
import com.jyblife.logic.wl.ops.external.mapper.VehicleDailyQuotaLogMapper;
import com.jyblife.logic.wl.ops.external.mapper.VehicleDailyQuotaMapper;
import com.jyblife.logic.wl.ops.external.mapper.VehicleMapper;
import com.jyblife.logic.wl.ops.external.mapper.VehicleQuotaLimitMapper;
import com.jyblife.logic.wl.ops.external.service.OrderService;
import com.jyblife.logic.wl.ops.message.constants.MessageTplEnum;
import com.jyblife.logic.wl.ops.message.dto.OrderSmsDto;
import com.jyblife.logic.wl.ops.message.dto.PushOilOrderDto;
import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;
import com.jyblife.logic.wl.ops.message.task.SmsMessageTask;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    public static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OilPriceMapper oilPriceMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private OilStationMapper oilStationMapper;
    @Autowired
    private OilGoodsMapper oilGoodsMapper;
    @Autowired
    private DriverVehicleRelationMapper dvrMapper;
    @Autowired
    private VehicleDailyQuotaMapper vDailyQuotaMapper;
    @Autowired
    private VehicleDailyQuotaLogMapper vDailyQuotaLogMapper;
    @Autowired
    private VehicleQuotaLimitMapper vQuotaLimitMapper;
    @Autowired
    private LogisticsCompanyMapper logCompanyMapper;
    @Autowired
    private LogisticsQuotaMapper logQuotaMapper;
    @Autowired
    private LogisticsCreditQuotaMapper creditQuotaMapper;
    @Autowired
    private LogisticsQuotaLogMapper logQuotaLogMapper;
    @Autowired
    private LogisticsQuotaLimitMapper logQuotaLimitMapper;
    @Autowired
    private LogisticsDailyQuotaMapper logDailyQuotaMapper;
    @Autowired
    private LogisticsDailyQuotaLogMapper logDailyQuotaLogMapper;
    @Autowired
    private OilCompanyMapper oilCompanyMapper;
    @Autowired
    private OilContactMapper oilContactMapper;
    @Autowired
    private OilPrintMapper oilPrintMapper;

    @Autowired
    private MqProducer mqProducer;
    @Autowired
    private RedissLock redisLock;
    @Autowired
    private RedisTemplates redisTemplates;

    @Override
    public RespOrderDetailDto selectOrderDetail(Long orderId) {
        RespOrderDetailDto respOrderDetailDto = orderMapper.selectOrderDetail(orderId);
        if (respOrderDetailDto != null) {
            respOrderDetailDto.setStatusDesc(OrderStatusEnum.getText(StrUtils.str2Int(respOrderDetailDto.getStatus())));
            respOrderDetailDto.setOrderType(OrderTypeEnum.getText(StrUtils.str2Int(respOrderDetailDto.getOrderType())));
        }
        return respOrderDetailDto;
    }

    @Override
    public RespUserOrderListDto selectPageByCustomerId(ReqUserOrderListDto dto) {
        Page<RespUserOrderListDto.OrderItem> pager = PageHelper.startPage(dto.getPage(), dto.getPageSize());
        orderMapper.selectPageByCustomerId(dto);
        pager.getResult().stream().forEach(new Consumer<RespUserOrderListDto.OrderItem>() {
            @Override
            public void accept(RespUserOrderListDto.OrderItem orderItem) {
                orderItem.setStatusDesc(OrderStatusEnum.getText(StrUtils.str2Int(orderItem.getStatus())));
            }
        });
        //统计每月加油量
        JSONObject monthlySum = new JSONObject();
        if(StringUtils.isNotBlank(dto.getStatus()) && StrUtils.str2Int(dto.getStatus()).intValue() == OrderStatusEnum.SUCCESS.getValue().intValue()) {
        	List<Map<String,Object>> list = orderMapper.selectMonthSumByCustomerId(dto.getCustomerId());
        	for(Map<String,Object> m : list) {
        		monthlySum.put(m.get("month").toString(), m.get("totalQuantity").toString());
        	}
        }
        
        RespUserOrderListDto respUserOrderListDto = new RespUserOrderListDto();
        respUserOrderListDto.setPage(pager.getPageNum());
        respUserOrderListDto.setPageCount(pager.getPages());
        respUserOrderListDto.setTotal(pager.getTotal());
        respUserOrderListDto.setRows(pager.getResult());
        respUserOrderListDto.setMonthlySum(monthlySum);
        
        return respUserOrderListDto;
    }

    @Override
    public List<RespUserOrderVehicleDto> selectOrderVehicles(Integer customerId, Integer stationId, Integer goodsId) throws OpsException {
        Driver driver = driverMapper.selectByCustomerId(customerId);
        //检查司机信息
        if (driver == null) {
            throw new OpsException(ResultCodeEnum.OUT_DRIVER_NULL);
        }
        Integer driverId = driver.getDriverId();
        //检查加油站是否有效
        OilStation oilStation = oilStationMapper.selectByPrimaryKey(stationId);
        if (oilStation == null || oilStation.getStatus() == null || oilStation.getStatus().intValue() == 0) {
            throw new OpsException(ResultCodeEnum.OUT_STATION_DISABLED);
        }
        //检查油企信息
        OilCompany oilCompany = oilCompanyMapper.selectByPrimaryKey(oilStation.getCompanyId());
        if (oilCompany == null || oilCompany.getStatus() == null || oilCompany.getStatus().intValue() == 0) {
            throw new OpsException(ResultCodeEnum.OUT_STATION_DISABLED);
        }

        //物流企业额度
        BigDecimal zero = new BigDecimal("0");
        LogisticsQuota logisticsQuota = logQuotaMapper.selectByLogisticsId(driver.getLogisticsId());
        //物流企业每日额度
        LogisticsDailyQuota logisticsDailyQuota = logDailyQuotaMapper.selectCurdateByLogisticsId(driver.getLogisticsId());
        LogisticsQuotaLimit logisticsQuotaLimit = logQuotaLimitMapper.selectCurrent(); //每日限额
        BigDecimal quotadailyLimitRate = (logisticsQuotaLimit == null) ? new BigDecimal("1.00") : logisticsQuotaLimit.getRate();
        if (logisticsDailyQuota == null) {
            logisticsDailyQuota = new LogisticsDailyQuota();
            logisticsDailyQuota.setFrozenQuota(zero);
            logisticsDailyQuota.setUsedQuota(zero);
        }
        //物流企业每日可用额度
        BigDecimal balanceDailyQuota = logisticsQuota.getCreditQuota().multiply(quotadailyLimitRate)
        										.subtract(logisticsDailyQuota.getFrozenQuota()).subtract(logisticsDailyQuota.getUsedQuota()); 
        //物流企业可用总额度
        BigDecimal balanceTotalQuota = logisticsQuota.getCreditQuota().subtract(logisticsQuota.getFrozenQuota()).subtract(logisticsQuota.getUsedQuota());
        //司机车辆信息
        List<RespDriverVehicleInfoDto.Vehicle> vehicles = vehicleMapper.selectByDriverIdAndState(driver.getDriverId(),StatusEnum.ENABLE.getValue());
        List<RespUserOrderVehicleDto.VehicleItem> vehicleItems = new ArrayList<RespUserOrderVehicleDto.VehicleItem>();
        //查询车辆每日限额比例
        VehicleQuotaLimit vehicleQuotaLimit = vQuotaLimitMapper.selectCurrent();
        BigDecimal limitRate = new BigDecimal(1);
        if (vehicleQuotaLimit != null && vehicleQuotaLimit.getRate() != null) {
            limitRate = vehicleQuotaLimit.getRate();
        }
        //计算每个车辆的最大容量
        for (RespDriverVehicleInfoDto.Vehicle vehicle : vehicles) {
            RespUserOrderVehicleDto.VehicleItem vehicleItem = new RespUserOrderVehicleDto.VehicleItem();
            vehicleItem.setVehicleId(StrUtils.str2Int(vehicle.getVehicleId()));
            vehicleItem.setVehicleNumber(vehicle.getNumber());
            vehicleItem.setVehicleModel(vehicle.getModel());
            //车辆的每日限额容量
            BigDecimal limitCapacity = limitRate.multiply(new BigDecimal(vehicle.getCapacity()));
            //查询车辆的单日已使用容量
            VehicleDailyQuota vehicleDailyQuota = vDailyQuotaMapper.selectCurDateByVehicleId(StrUtils.str2Int(vehicle.getVehicleId()));
            if (vehicleDailyQuota != null) {
                limitCapacity = limitCapacity.subtract(vehicleDailyQuota.getUsedQuota()).subtract(vehicleDailyQuota.getFrozenQuota());
            }
            vehicleItem.setMaxAvailableQuantity((limitCapacity.compareTo(zero) > 0) ? limitCapacity.setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "0.00");
            vehicleItems.add(vehicleItem);
        }
        //查询当前加油站的有效油品价格
        BigDecimal quantity = new BigDecimal("0");
        List<OilPrice> oilPriceList = oilPriceMapper.getEffectListByStationIdAndGoodsId(stationId, goodsId);
        List<RespUserOrderVehicleDto> respDtos = new ArrayList<RespUserOrderVehicleDto>();
        for (OilPrice oilPrice : oilPriceList) {
        	if(oilPrice.getAgreedPrice().compareTo(zero) <= 0 || oilPrice.getDiscountPrice().compareTo(zero) <= 0 || oilPrice.getRetailPrice().compareTo(zero) <= 0) {
        		continue;
        	}
            RespUserOrderVehicleDto dto = new RespUserOrderVehicleDto();
            dto.setGoodsId(oilPrice.getGoodsId());
            OilGoods oilGoods = oilGoodsMapper.selectByPrimaryKey(oilPrice.getGoodsId());
            if (oilGoods != null) {
                dto.setGoodsName(oilGoods.getName());
            }
            
            List<RespUserOrderVehicleDto.VehicleItem> items = new ArrayList<>();
            for (RespUserOrderVehicleDto.VehicleItem item : vehicleItems) {
            	RespUserOrderVehicleDto.VehicleItem cur_item = new RespUserOrderVehicleDto.VehicleItem();
            	BeanUtils.copyProperties(item, cur_item);
            	quantity = balanceDailyQuota.divide(oilPrice.getDiscountPrice(), 2, BigDecimal.ROUND_HALF_UP);
            	if(quantity.compareTo(new BigDecimal(cur_item.getMaxAvailableQuantity())) < 0) {
            		cur_item.setMaxAvailableQuantity((quantity.compareTo(zero) > 0) ? quantity.toString() : "0.00");
            	}
            	quantity = balanceTotalQuota.divide(oilPrice.getDiscountPrice(), 2, BigDecimal.ROUND_HALF_UP);
            	if(quantity.compareTo(new BigDecimal(cur_item.getMaxAvailableQuantity())) < 0) {
            		cur_item.setMaxAvailableQuantity((quantity.compareTo(zero) > 0) ? quantity.toString() : "0.00");
            	}
            	
            	items.add(cur_item);
            }
            dto.setItems(items);
            respDtos.add(dto);
        }
        
        return respDtos;
    }

    /**
     * 下单操作
     */
    @Override
    public OutRespJson submitOrder(ReqOrderSubmitDto dto) {
        int status = OrderStatusEnum.INIT.getValue(); //订单状态：0新建，-1交易失败，10交易成功
        String failedReason = ""; //订单失败原因

        //司机用户是否存在
        Driver driver = driverMapper.selectByCustomerId(dto.getCustomerId());
        if (driver == null) {
            return OutRespJson.error("当前司机信息不存在");
        }
        //用户密码是否正确
        if (!dto.getPassword().equals(driver.getPassword())) {
            return OutRespJson.error("交易密码不正确");
        }
        //车辆是否存在
        Vehicle vehicle = vehicleMapper.selectByPrimaryKey(dto.getVehicleId());
        if (vehicle == null) {
            return OutRespJson.error("当前车辆信息不存在");
        }
        if (vehicle.getState() == null || vehicle.getState().intValue() == 0) {
            return OutRespJson.error("当前车辆不可用");
        }
        //油站是否存在
        OilStation oilStation = oilStationMapper.selectByPrimaryKey(dto.getStationId());
        if (oilStation == null) {
            return OutRespJson.error("当前油站信息不存在");
        }
        if (oilStation.getStatus().intValue() == 0) {
            return OutRespJson.error("当前油品不可售");
        }
        //油品是否存在及是否可用
        OilGoods oilGoods = oilGoodsMapper.selectByPrimaryKey(dto.getGoodsId());
        if (oilGoods == null) {
            return OutRespJson.error("当前油品信息不存在");
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("stationId", dto.getStationId());
        param.put("goodsId", dto.getGoodsId());
        OilPrice oilPrice = oilPriceMapper.getEffectByStationIdAndGoodsId(param); //最新油价
        if (oilGoods.getStatus() == 0 || oilPrice == null) {
            return OutRespJson.error("当前油品不可售");
        }

        RespOrderSubmitDto respOrderSubmitDto = new RespOrderSubmitDto();
        Date cur_date = new Date();
        Order order = new Order();

        //添加分布式锁-控制并发额度
        boolean hasLock = false;
        String lockKey = Constants.LOGISTICS_COMPANY_QUOTA_LOCK_PREFIX_ + driver.getLogisticsId();
        try {
            hasLock = redisLock.tryLock(lockKey, Constants.REDIS_TRY_LOCK_WAIT_TIME, Constants.REDIS_LOCK_LEASE_TIME);
            if (hasLock) {
            	if (status == 0) {
            		param.clear();
                    param.put("driverId", driver.getDriverId());
                    param.put("vehicleId", dto.getVehicleId());
                    DriverVehicleRelation vehicleRelation = dvrMapper.getByVehicleIdAndDriverId(param);
                    if (vehicleRelation == null) {
                        failedReason = "当前司机与车辆" + vehicle.getNumber() + "未绑定";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
            	}
                if (status == 0 && driver.getStatus().intValue() == 0) {
                    failedReason = "司机账号失效，请联系所在物流公司";
                    status = OrderStatusEnum.FAIL.getValue();
                }
                if (status == 0 && vehicle.getStatus().intValue() == 0) {
                    failedReason = "车辆失效，请联系所在物流公司";
                    status = OrderStatusEnum.FAIL.getValue();
                }
                if (status == 0 && vehicle.getCapacity().compareTo(new BigDecimal(dto.getQuantity())) < 0) {
                    failedReason = "本次加油量超过汽车油箱的最大容量";
                    status = OrderStatusEnum.FAIL.getValue();
                }
                if (status == 0) {
                    OilCompany oilCompany = oilCompanyMapper.selectByPrimaryKey(oilStation.getCompanyId());
                    if (oilCompany.getStatus().intValue() == 0) {
                        failedReason = "当前油企不可用";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
                }

                //车辆当日加油量是否允许
                VehicleDailyQuota vehicleDailyQuota = null;
                BigDecimal dayCapacity = null;
                if (status == 0) {
                    VehicleQuotaLimit quotaLimit = vQuotaLimitMapper.selectCurrent();//每日限额
                    BigDecimal dailyLimitRate = (quotaLimit == null) ? new BigDecimal("1.00") : quotaLimit.getRate();
                    vehicleDailyQuota = vDailyQuotaMapper.selectCurDateByVehicleId(dto.getVehicleId());//当天车辆容量
                    if (vehicleDailyQuota == null) {
                        Date curDate = new Date();
                        vehicleDailyQuota = new VehicleDailyQuota();
                        vehicleDailyQuota.setCreateTime(curDate);
                        vehicleDailyQuota.setUpdateTime(curDate);
                        vehicleDailyQuota.setCurrentDate(curDate);
                        vehicleDailyQuota.setFrozenQuota(new BigDecimal("0"));
                        vehicleDailyQuota.setUsedQuota(new BigDecimal("0"));
                        vehicleDailyQuota.setVehicleId(dto.getVehicleId());
                        vDailyQuotaMapper.insertVehicleDailyQuota(vehicleDailyQuota);
                    }
                    dayCapacity = vehicle.getCapacity().multiply(dailyLimitRate); //每日容量
                    BigDecimal balanceCapacity = dayCapacity.subtract(vehicleDailyQuota.getFrozenQuota()).subtract(vehicleDailyQuota.getUsedQuota()); //每日可用容量
                    if (balanceCapacity.compareTo(new BigDecimal(dto.getQuantity())) < 0) {
                        failedReason = "当前车辆加油升数已超过当日可用升数";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
                }

                //企业额度及当日可用额度是否允许
                if (status == 0) {
                    LogisticsCompany logisticsCompany = logCompanyMapper.selectByPrimaryKey(Long.valueOf(driver.getLogisticsId().toString()));
                    if (logisticsCompany == null || logisticsCompany.getStatus().intValue() == 0) {
                        failedReason = "物流企业账号失效，请联系所在物流公司";
                        status = OrderStatusEnum.FAIL.getValue();
                    } else if (BankKeeperStatusEnum.DISABLE.getValue().equals(logisticsCompany.getOutStatus())) {
                        failedReason = "物流企业银管家状态冻结，请联系所在物流公司";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
                }
                LogisticsQuota logisticsQuota = null;
                BigDecimal zero = new BigDecimal("0");
                Date date = DateUtil.parse(DateUtil.format(cur_date, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
                if (status == 0) {
                    logisticsQuota = logQuotaMapper.selectByLogisticsId(driver.getLogisticsId());
                    LogisticsCreditQuota creditQuota = creditQuotaMapper.selectByLogisticsId(driver.getLogisticsId());
                    BigDecimal totalBalance = logisticsQuota.getCreditQuota().subtract(logisticsQuota.getUsedQuota()).subtract(logisticsQuota.getFrozenQuota());
                    if (totalBalance.compareTo(zero) <= 0) {
                        failedReason = "物流企业额度已使用完，请联系所在物流公司";
                        status = OrderStatusEnum.FAIL.getValue();
                    } else if (date.before(creditQuota.getStartDate()) || date.after(creditQuota.getEndDate())) {
                        failedReason = "当前车俩加油时间不在当前物流企业授信时间范围内";
                        status = OrderStatusEnum.FAIL.getValue();
                    } else if (totalBalance.compareTo(oilPrice.getDiscountPrice().multiply(new BigDecimal(dto.getQuantity()))) < 0) {
                        failedReason = "当前车辆加油升数已超过当日可用升数";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
                }
                //每日额度
                LogisticsDailyQuota logisticsDailyQuota = null;
                BigDecimal dayQuota = null;
                if (status == 0) {
                    LogisticsQuotaLimit logisticsQuotaLimit = logQuotaLimitMapper.selectCurrent();
                    BigDecimal quotadailyLimitRate = (logisticsQuotaLimit == null) ? new BigDecimal("1.00") : logisticsQuotaLimit.getRate();
                    logisticsDailyQuota = logDailyQuotaMapper.selectCurdateByLogisticsId(driver.getLogisticsId()); //当天额度
                    if (logisticsDailyQuota == null) {
                        Date curDate = new Date();
                        logisticsDailyQuota = new LogisticsDailyQuota();
                        logisticsDailyQuota.setCreateTime(curDate);
                        logisticsDailyQuota.setUpdateTime(curDate);
                        logisticsDailyQuota.setCurrentDate(curDate);
                        logisticsDailyQuota.setFrozenQuota(new BigDecimal("0"));
                        logisticsDailyQuota.setUsedQuota(new BigDecimal("0"));
                        logisticsDailyQuota.setLogisticsId(driver.getLogisticsId());
                        logDailyQuotaMapper.insertLogisticsDailyQuota(logisticsDailyQuota);
                    }
                    dayQuota = logisticsQuota.getCreditQuota().multiply(quotadailyLimitRate); //每日额度
                    BigDecimal balanceQuota = dayQuota.subtract(logisticsDailyQuota.getFrozenQuota()).subtract(logisticsDailyQuota.getUsedQuota()); //每日可用额度
                    if (balanceQuota.compareTo(oilPrice.getDiscountPrice().multiply(new BigDecimal(dto.getQuantity()))) < 0) {
                        failedReason = "当前车辆加油升数已超过当日可用升数";
                        status = OrderStatusEnum.FAIL.getValue();
                    }
                }

                //下单
                order.setCode(generateOrderCode());
                order.setOrderType(OrderTypeEnum.NORMAL.getValue());
                order.setCreateTime(cur_date);
                order.setCustomerId(dto.getCustomerId());
                order.setVehicleId(dto.getVehicleId());
                order.setGoodsId(dto.getGoodsId());
                order.setQuantity(new BigDecimal(dto.getQuantity()));
                order.setStationId(dto.getStationId());
                order.setStatus(status);
                order.setStatusTime(cur_date);
                order.setUpdateTime(cur_date);
                if (status == 0) {
                    status = OrderStatusEnum.SUCCESS.getValue();
                    order.setStatus(status);
                    order.setEffectTime(cur_date);
                }
                order.setLogisticsId(driver.getLogisticsId());
                order.setFailedReason(failedReason);
                order.setOilCompanyId(oilStation.getCompanyId());
                order.setPriceBuy(oilPrice.getAgreedPrice().intValue());
                order.setPriceRetail(oilPrice.getRetailPrice().intValue());
                order.setPriceSell(oilPrice.getDiscountPrice().intValue());
                orderMapper.insertOrder(order);

                respOrderSubmitDto.setOrderId(order.getOrderId().toString());
                respOrderSubmitDto.setFailedReason(failedReason);
                respOrderSubmitDto.setStatus(status);

                //冻结，或者扣减企业加油量，车辆加油量
                if (status == OrderStatusEnum.SUCCESS.getValue()) {
                    //添加变更记录
                    LogisticsQuotaLog lol = new LogisticsQuotaLog();
                    lol.setRemark("订单支付");
                    lol.setCategory(10);
                    lol.setCreateTime(cur_date);
                    lol.setUpdateTime(cur_date);
                    lol.setLogisticsId(driver.getLogisticsId());
                    lol.setMethod(-1);
                    lol.setQuota(oilPrice.getDiscountPrice().multiply(new BigDecimal(dto.getQuantity())).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
                    lol.setQuotaTotal(logisticsQuota.getCreditQuota().subtract(logisticsQuota.getFrozenQuota()).subtract(logisticsQuota.getUsedQuota())
                            .subtract(new BigDecimal(lol.getQuota())).intValue());
                    lol.setRelationId(order.getOrderId());
                    logQuotaLogMapper.insertOne(lol);

                    //变更可用额度
                    logisticsQuota.setUsedQuota(logisticsQuota.getUsedQuota().add(new BigDecimal(lol.getQuota())));
                    logisticsQuota.setUpdateTime(cur_date);
                    logQuotaMapper.updateByPrimaryKeySelective(logisticsQuota);

                    logisticsDailyQuota.setUsedQuota(logisticsDailyQuota.getUsedQuota().add(oilPrice.getDiscountPrice().multiply(new BigDecimal(dto.getQuantity())).setScale(0,BigDecimal.ROUND_HALF_UP)));
                    logDailyQuotaMapper.updateByPrimaryKeySelective(logisticsDailyQuota);

                    LogisticsDailyQuotaLog ldql = new LogisticsDailyQuotaLog();
                    ldql.setRemark("订单支付");
                    ldql.setCategory(10);
                    ldql.setCreateTime(cur_date);
                    ldql.setUpdateTime(cur_date);
                    ldql.setCurrentDate(cur_date);
                    ldql.setLogisticsId(driver.getLogisticsId());
                    ldql.setMethod(-1);
                    ldql.setQuota(oilPrice.getDiscountPrice().multiply(new BigDecimal(dto.getQuantity())).setScale(0,BigDecimal.ROUND_HALF_UP).intValue());
                    ldql.setQuotaTotal(dayQuota.subtract(logisticsDailyQuota.getFrozenQuota()).subtract(logisticsDailyQuota.getUsedQuota()).intValue());
                    ldql.setRelationId(order.getOrderId());
                    logDailyQuotaLogMapper.insertSelective(ldql);

                    //变更车辆额度
                    vehicleDailyQuota.setUsedQuota(vehicleDailyQuota.getUsedQuota().add(new BigDecimal(dto.getQuantity())));
                    vehicleDailyQuota.setUpdateTime(cur_date);
                    vDailyQuotaMapper.updateByPrimaryKeySelective(vehicleDailyQuota);

                    VehicleDailyQuotaLog vdql = new VehicleDailyQuotaLog();
                    vdql.setRemark("订单支付");
                    vdql.setCategory(10);
                    vdql.setCreateTime(cur_date);
                    vdql.setUpdateTime(cur_date);
                    vdql.setCurrentDate(cur_date);
                    vdql.setMethod(-1);
                    vdql.setVehicleId(dto.getVehicleId());
                    vdql.setQuota(new BigDecimal(dto.getQuantity()));
                    vdql.setQuotaTotal(dayCapacity.subtract(vehicleDailyQuota.getFrozenQuota()).subtract(vehicleDailyQuota.getUsedQuota()));
                    vdql.setRelationId(order.getOrderId());
                    vDailyQuotaLogMapper.insertSelective(vdql);

                }
            } else {
                return OutRespJson.error(ResultCodeEnum.NETWORK_ERROR);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (hasLock) {
                redisLock.unlock(lockKey);
            }
        }

        if (status == OrderStatusEnum.SUCCESS.getValue()) {
            try {
                //推送订单信息到财务系统
                PushOilOrderDto req = new PushOilOrderDto();
                req.setStatus("1");
                req.setOrd_id(order.getCode());
                req.setOil_code(StrUtils.int2Str(oilStation.getStationId()));
                req.setLogi_code(StrUtils.int2Str(driver.getLogisticsId()));
                req.setCustomer_id(StrUtils.int2Str(dto.getCustomerId()));
                req.setCustomer_name(driver.getName());
                req.setCar_model(vehicle.getModel());
                req.setPlate_info(vehicle.getNumber());
                req.setOrd_create_time(DateUtil.format(cur_date, "yyyy/MM/dd HH:mm:ss"));
                req.setOrd_pay_time(DateUtil.format(cur_date, "yyyy/MM/dd HH:mm:ss"));
                req.setOil_type(oilGoods.getName());
                req.setRefuel_number(dto.getQuantity().toString());
                req.setRetail_price(order.getPriceRetail().toString());
                req.setOils_price(order.getPriceBuy().toString());
                req.setLogi_price(order.getPriceSell().toString());

                MqMessage mqMessage = new MqMessage();
                mqMessage.setRetryTime(3000l);
                mqMessage.setContent(JSON.toJSONString(req));
                mqMessage.setExchange(MqContant.ORDER_EXCHANGE);
                mqMessage.setRoutingKey(MqContant.ORDER_ROUTINGKEY);
                mqProducer.sendDelayMessage(mqMessage);
            } catch (Exception e) {
                logger.error("发送推送订单mq信息异常：" + e.getMessage());
            }

            try {
                //发送短信提醒
                OrderSmsDto osd = new OrderSmsDto();
                osd.setOrder_code(order.getCode());
                osd.setQuantity(dto.getQuantity().toString());
                osd.setTrans_time(DateUtil.format(order.getEffectTime(), "yyyy-MM-dd HH:mm"));
                osd.setVehicle_number(vehicle.getNumber());

                OilContact record = new OilContact();
                record.setStatus(1);
                record.setCompanyId(oilStation.getCompanyId());
                record.setType(1);
                record.setStationId(oilStation.getStationId());

                OilContact oilContact = oilContactMapper.getByStationIdAndType(record);
                if (oilContact != null && StringUtils.isNotBlank(oilContact.getPhone())) {
                    PoolUtils.pool.execute(new SmsMessageTask(oilContact.getPhone(), (JSONObject) JSON.toJSON(osd), MessageTplEnum.ORDER_MESSAGE_TEMPLATE));
                }
            } catch (Exception e) {
                logger.error("发送补单成功短信提醒信息异常：" + e.getMessage());
            }
        }

        return OutRespJson.success(respOrderSubmitDto);
    }

    /**
     * 生成订单编号
     *
     * @return
     */
    @Override
    public String generateOrderCode() {
        String day = DateUtil.format(new Date(), "yyyyMMdd");
        Long num = redisTemplates.incrementAndGet(Constants.OPS_ORDER_CODE_PREFIX + day, 1, 1 * 24 * 60 * 60L);
        String orderCode = Constants.ORDER_CODE_PREFIX + day + StrUtils.getfixedNum(num.intValue(), 6);
        return orderCode;
    }

    @Override
    public Page<RespLogicOrderListDto.OrderItem> selectLogicPageList(ReqLogicOrderListDto dto) {
        Page<RespLogicOrderListDto.OrderItem> pager = PageHelper.startPage(dto.getPage(), dto.getPageSize());
        orderMapper.selectLogicPageList(dto);
        return pager;
    }

    @Override
    public RespLogicConsumeStatisticsDto getLogicConsumeStatistics(ReqLogicConsumeStatisticsDto dto) {
        if (dto == null || dto.getLogisticsId() == null) {
            throw new OpsException(ResultCodeEnum.OUT_PARAM_ERROR);
        }

        RespLogicConsumeStatisticsDto respDto = new RespLogicConsumeStatisticsDto();
        respDto.setLogisticsId(dto.getLogisticsId());

        String startDate = dto.getDate() + " 00:00:00";
        String endDate = dto.getDate() + " 23:59:59";
        //统计加油车辆数
        Integer vehicleNum = orderMapper.selectVehicleCount(dto.getLogisticsId(), startDate, endDate);
        //统计订单数
        Integer orderNum = orderMapper.selectOrderCount(dto.getLogisticsId(), startDate, endDate);
        //统计消费总额
        BigDecimal amount = orderMapper.selectConsumeAmount(dto.getLogisticsId(), startDate, endDate);
        respDto.setVehicleNum(vehicleNum==null?0:vehicleNum);
        respDto.setOrderNum(orderNum==null?0:orderNum);
        if(amount!=null) {
            //转换为元
            respDto.setAmount(amount.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }else{
            respDto.setAmount("0.00");
        }

        return respDto;
    }


    @Override
    public Page<RespOilCompanyOrderListDto.OilCompanyOrderItem> selectOilCompanyPageList(ReqOilCompanyOrderListDto dto) {
        Page<RespOilCompanyOrderListDto.OilCompanyOrderItem> pager = PageHelper.startPage(dto.getPage(), dto.getPageSize());
        orderMapper.selectOilCompanyPageList(dto);
        return pager;
    }

	@Override
	public RespOilOrderPrintDto oilOrderPrint(ReqOilOrderPrintDto dto) {
		
		RespOilOrderPrintDto oilOrder = orderMapper.selectPrintOilOrder(dto);
		if(null == oilOrder) {
			throw new IllegalArgumentException("订单不存在！");
		}

		String printSn = oilPrintMapper.selectByStationId(oilOrder.getOilCode());
		if(StringUtils.isEmpty(printSn)) {
			throw new OpsException(ResultCodeEnum.OUT_PRINT_SN_NULL);
		}
		String printContent = printContent(oilOrder,printSn);
		
		PrintUtils.printMethod(printContent,printSn);

		return oilOrder;
	}
	
	private String printContent(RespOilOrderPrintDto oilOrder,String printSn) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		byte[] spaces = new byte[3];
		spaces[0] = 0x1b;
		spaces[1] = 0x33;
		spaces[2] = 0x52;//7f => 50   行距距离设置最小值为\x50 最大值为\x7f
		String ls = new String(spaces);//行距开始
		byte[] spacee = new byte[2];
		spacee[0] = 0x1b;
		spacee[1] = 0x32;
		String le = new String(spacee);
		
		Date ordPayTime = oilOrder.getOrdPayTime();
		if(null == ordPayTime) {
			ordPayTime = new Date();
		}

		String content;
		content =  "<C><BOLD>欢迎光临"+oilOrder.getOilName()+"</BOLD></C><BR>";
		content += "<C>销售小票</C><BR>";
		content += "<BR>";
		content += ls+"交易日期： "+dateFormat.format(ordPayTime)+"<BR>"+le;
		content += ls+"订单编号： "+oilOrder.getOrdId()+"<BR>"+le;
		content += ls+"车牌号： "+oilOrder.getPlateInfo()+"<BR>"+le;
		content += ls+"打印机编号： "+printSn+"<BR>"+le;
		content += "--------------------------------<BR>";
		content += "品名　　　　　 升数（L）<BR>";
		content += "--------------------------------<BR>";
		content += ""+oilOrder.getOilType()+"         "+oilOrder.getRefuelNumber()+"L<BR>";
		content += "--------------------------------<BR>";
		content += ls+"总计升数："+oilOrder.getRefuelNumber()+"L<BR>"+le;
		content += ls+"谢谢惠顾！欢迎下次再来！<BR>"+le;
		content += ls+"该小票是销售凭据，请留意保存！<BR>"+le;
		content += "<BR>";
		content += "<BR>";
		content += ls+"打印日期："+dateFormat.format(new Date())+"<BR>"+le;
		content += "打印人： 系统管理员";
		
		return content;
	}

	
	
}
