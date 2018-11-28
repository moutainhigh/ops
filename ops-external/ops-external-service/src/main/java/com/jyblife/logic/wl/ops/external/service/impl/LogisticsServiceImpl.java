package com.jyblife.logic.wl.ops.external.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jyblife.logic.wl.ops.entity.*;
import com.jyblife.logic.wl.ops.external.dto.req.*;
import com.jyblife.logic.wl.ops.external.dto.resp.*;
import com.jyblife.logic.wl.ops.external.mapper.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jyblife.logic.wl.ops.common.enums.PhotoAttachTypeEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.enums.VehicleApproveStatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.OutRespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.external.service.LogisticsService;
import com.jyblife.logic.wl.ops.message.dto.PushLogisticsCompanyDto;
import com.jyblife.logic.wl.ops.message.mq.contants.MqContant;
import com.jyblife.logic.wl.ops.message.mq.entity.MqMessage;
import com.jyblife.logic.wl.ops.message.mq.producer.MqProducer;

@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {

    protected Logger logger = LoggerFactory.getLogger(LogisticsServiceImpl.class);

    @Autowired
    private LogisticsCompanyMapper mapper;
    @Autowired
    private LogisticsCreditQuotaMapper creditQuotaMapper;
    @Autowired
    private LogisticsQuotaMapper quotaMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private DriverVehicleRelationMapper relationMapper;
    @Autowired
    private VehicleDailyQuotaMapper vDailyQuotaMapper;
    @Autowired
    private VehicleQuotaLimitMapper vQuotaLimitMapper;
    @Autowired
    private PhotoAttachmentMapper photoAttachmentMapper;
    @Autowired
    private MqProducer mqProducer;
    @Autowired
    private LogisticsDailyQuotaMapper logisticsDailyQuotaMapper;
    @Autowired
    private LogisticsRepaymentLogMapper repaymentLogMapper;
    @Autowired
    private LogisticsQuotaLogMapper logisticsQuotaLogMapper;

    @Override
    public OutRespJson addLogiCompany(LogisticsCompanyAddDto dto) throws OpsException {
        LogisticsCompany record = mapper.selectByIdentity(dto.getIdentity());
        if (record != null) {
            return OutRespJson.error(ResultCodeEnum.OUT_LOGISTICSCOMPANY_EXIST);
        }

        //新增物流企业
        LogisticsCompany company = new LogisticsCompany();
        Date curDate = new Date();
        company.setCreateUserId(0);
        company.setCreateTime(curDate);
        company.setUpdateUserId(0);
        company.setUpdateTime(curDate);
        company.setRemark("添加物流企业");
        company.setStatus(StatusEnum.ENABLE.getValue());
        company.setStatusTime(curDate);
        company.setName(dto.getName());
        company.setOutStatus(dto.getStatus());
        company.setOutIdentity(dto.getIdentity());
        mapper.insertLogisticsCompany(company);

        //新增授信额度
        LogisticsCreditQuota creditQuota = new LogisticsCreditQuota();
        creditQuota.setCreateUserId(0);
        creditQuota.setCreateTime(curDate);
        creditQuota.setUpdateUserId(0);
        creditQuota.setUpdateTime(curDate);
        creditQuota.setLogisticsId(Integer.valueOf(company.getLogisticsId().toString()));
        creditQuota.setCreditQuota(new BigDecimal(dto.getCreditQuota()));
        creditQuota.setStartDate(DateUtil.parse(dto.getStartDate(), "yyyy-MM-dd"));
        creditQuota.setEndDate(DateUtil.parse(dto.getEndDate(), "yyyy-MM-dd"));
        creditQuotaMapper.insertSelective(creditQuota);

        //新增企业额度
        LogisticsQuota quota = new LogisticsQuota();
        quota.setCreateUserId(0);
        quota.setCreateTime(curDate);
        quota.setUpdateUserId(0);
        quota.setUpdateTime(curDate);
        quota.setLogisticsId(Integer.valueOf(company.getLogisticsId().toString()));
        quota.setCreditQuota(new BigDecimal(dto.getCreditQuota()));
        quotaMapper.insertSelective(quota);

        JSONObject json = new JSONObject();
        json.put("logistics_id", String.valueOf(company.getLogisticsId()));

        try {
            //推送物流企业信息到财务系统
            PushLogisticsCompanyDto req = new PushLogisticsCompanyDto();
            req.setLogi_code(company.getLogisticsId().toString());
            req.setLogistics_name(company.getName());
            req.setStatus(company.getStatus() == 1 ? "1" : "2");

            MqMessage mqMessage = new MqMessage();
            mqMessage.setRetryTime(3000l);
            mqMessage.setContent(JSON.toJSONString(req));
            mqMessage.setExchange(MqContant.LOGISTICS_COMPANY_EXCHANGE);
            mqMessage.setRoutingKey(MqContant.LOGISTICS_COMPANY_ROUTINGKEY);
            mqProducer.sendDelayMessage(mqMessage);
        } catch (Exception e) {
            logger.error("发送推送物流企业mq信息异常：" + e.getMessage());
        }

        return OutRespJson.success(json);
    }

    @Override
    public OutRespJson addDriver(ReqDriverAddDto dto) throws OpsException {
        if (!StrUtils.isNumber(dto.getPassword())) {
            return OutRespJson.paramError("Password交易密码必须是6位数字");
        }
        LogisticsCompany logisticsCompany = mapper.get(LogisticsCompany.class, dto.getLogisticsId());
        if (logisticsCompany == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_LOGISTICSCOMPANY_NULL);
        }

        Driver driver = driverMapper.selectByPhone(dto.getPhone());
        if (driver != null) {
            return OutRespJson.error(ResultCodeEnum.OUT_PHONE_EXIST);
        }

        //新增客户信息
        Customer customer = new Customer();
        Date curDate = new Date();
        customer.setAccount(dto.getPhone());
        customer.setPhone(dto.getPhone());
        customer.setRegisterTime(curDate);
        customer.setLoginTime(curDate);
        customer.setLoginCount(0);
        customer.setStatusTime(curDate);
        customer.setStatus(dto.getStatus());
        customer.setCreateUserId(0);
        customer.setCreateTime(curDate);
        customer.setUpdateUserId(0);
        customer.setUpdateTime(curDate);
        customerMapper.insertCustomer(customer);

        //新增司机信息
        Driver record = new Driver();
        record.setStatusTime(curDate);
        record.setStatus(dto.getStatus());
        record.setCreateUserId(0);
        record.setCreateTime(curDate);
        record.setUpdateUserId(0);
        record.setUpdateTime(curDate);
        record.setRemark("添加司机信息");
        record.setPhone(dto.getPhone());
        record.setPassword(dto.getPassword());
        record.setName(dto.getName());
        record.setLogisticsId(Integer.valueOf(dto.getLogisticsId().toString()));
        record.setCustomerId(customer.getId());
        driverMapper.insertDriver(record);

        if (dto.getFiles() != null && dto.getFiles().size() > 0) {
            List<PhotoAttachment> attachmentList = new ArrayList<>();
            for (ReqDriverAddDto.File file : dto.getFiles()) {
                PhotoAttachment attachMent = new PhotoAttachment();
                attachMent.setStatus(StatusEnum.ENABLE.getValue());
                attachMent.setCreateTime(curDate);
                attachMent.setUpdateTime(curDate);
                attachMent.setBaseId(Long.valueOf(record.getDriverId()));
                attachMent.setType(PhotoAttachTypeEnum.DRIVER.getValue());
                attachMent.setOutId(file.getFileId());
                attachMent.setFilePath(file.getFileUrl());
                attachMent.setFileUrl(file.getFileUrl());
                attachmentList.add(attachMent);
            }
            photoAttachmentMapper.insertBatch(attachmentList);
        }

        JSONObject json = new JSONObject();
        json.put("customer_id", String.valueOf(customer.getId()));

        return OutRespJson.success(json);
    }

    @Override
    public OutRespJson addVehicle(ReqVehicleAddDto dto) throws OpsException {
        LogisticsCompany logisticsCompany = mapper.get(LogisticsCompany.class, Long.valueOf(dto.getLogisticsId()));
        if (logisticsCompany == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_LOGISTICSCOMPANY_NULL);
        }

        Vehicle record = vehicleMapper.selectByNumber(dto.getNumber());
        if (record != null) {
            return OutRespJson.error(ResultCodeEnum.OUT_VEHICLE_EXIST);
        }

        Date curDate = new Date();
        //添加车辆信息
        Vehicle vehicle = new Vehicle();
        vehicle.setCreateUserId(0);
        vehicle.setCreateTime(curDate);
        vehicle.setUpdateUserId(0);
        vehicle.setUpdateTime(curDate);
        vehicle.setLogisticsId(dto.getLogisticsId());
        vehicle.setRemark("添加车辆信息");
        vehicle.setStartDate(DateUtil.parse(dto.getStartDate(), "yyyy-MM-dd"));
        vehicle.setEndDate(DateUtil.parse(dto.getEndDate(), "yyyy-MM-dd"));
        vehicle.setStatusTime(curDate);
        vehicle.setStatus(VehicleApproveStatusEnum.APPROVED.getValue());
        vehicle.setCapacity(new BigDecimal(dto.getCapacity()));
        vehicle.setModel(dto.getModel());
        vehicle.setNumber(dto.getNumber());
        vehicle.setOptor(dto.getOperator());
        vehicleMapper.insertVehicle(vehicle);

        if (dto.getFiles() != null && dto.getFiles().size() > 0) {
            List<PhotoAttachment> attachmentList = new ArrayList<>();
            for (ReqVehicleAddDto.File file : dto.getFiles()) {
                PhotoAttachment attachMent = new PhotoAttachment();
                attachMent.setStatus(StatusEnum.ENABLE.getValue());
                attachMent.setCreateTime(curDate);
                attachMent.setUpdateTime(curDate);
                attachMent.setBaseId(Long.valueOf(vehicle.getVehicleId()));
                attachMent.setType(PhotoAttachTypeEnum.VEHICLE.getValue());
                attachMent.setOutId(file.getFileId());
                attachMent.setFilePath(file.getFileUrl());
                attachMent.setFileUrl(file.getFileUrl());
                attachmentList.add(attachMent);
            }
            photoAttachmentMapper.insertBatch(attachmentList);
        }

        JSONObject json = new JSONObject();
        json.put("vehicle_id", String.valueOf(vehicle.getVehicleId()));

        return OutRespJson.success(json);
    }

    @Override
    public OutRespJson bindDriverVehicle(ReqBindDriverVehicleDto dto) throws OpsException {
        Driver driver = driverMapper.selectByCustomerId(dto.getCustomerId());
        if (driver == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_CUSTOMER_NULL);
        }

        Date curDate = new Date();
        List<DriverVehicleRelation> dataList = new ArrayList<>();
        DriverVehicleRelation dv = null;

        List<ReqBindDriverVehicleDto.Vehicle> vehicles = dto.getVehicles();
        List<Integer> vehicleIds = new ArrayList<>();
        for (ReqBindDriverVehicleDto.Vehicle v : vehicles) {
            vehicleIds.add(v.getVehicleId());

            dv = new DriverVehicleRelation();
            dv.setCreateTime(curDate);
            dv.setDriverId(driver.getDriverId());
            dv.setVehicleId(v.getVehicleId());
            dataList.add(dv);
        }

        List<Integer> errorList = null;
        List<Vehicle> list = vehicleMapper.selectByIds(vehicleIds);
        if (list == null || list.size() == 0) {
            errorList = vehicleIds;
        } else {
            if (list.size() != vehicleIds.size()) {
                errorList = new ArrayList<>();
                temp:
                for (Integer id : vehicleIds) {
                    for (Vehicle ve : list) {
                        if (ve.getVehicleId().equals(id)) {
                            continue temp;
                        }
                    }

                    errorList.add(id);
                }
            }
        }

        if (errorList != null && errorList.size() > 0) {
            return OutRespJson.error("车辆id:" + errorList + "车辆信息不存在");
        }

        //删除原有
        relationMapper.deleteByDriverId(driver.getDriverId());
        //保存最新
        relationMapper.insertBatch(dataList);

        return OutRespJson.success();
    }

    @Override
    public RespDriverVehicleInfoDto getDriverVehicleInfo(ReqDriverVehicleInfoDto dto) throws OpsException {
        Driver driver = driverMapper.selectByCustomerId(dto.getCustomerId());
        if (driver == null) {
            return null;
        }

        LogisticsCompany company = mapper.get(LogisticsCompany.class, Long.valueOf(driver.getLogisticsId()));
        RespDriverVehicleInfoDto infoDto = new RespDriverVehicleInfoDto();
        infoDto.setId(String.valueOf(driver.getDriverId()));
        infoDto.setCustomerId(String.valueOf(driver.getCustomerId()));
        infoDto.setLogisticsId(String.valueOf(driver.getLogisticsId()));
        infoDto.setLogisticsName(company.getName());
        infoDto.setName(driver.getName());
        infoDto.setPassword(driver.getPassword());
        infoDto.setPhone(driver.getPhone());
        infoDto.setStatus(String.valueOf(driver.getStatus()));
        infoDto.setStatusName(StatusEnum.getText(driver.getStatus()));

        //查询图片
        PhotoAttachment photoAttachment = new PhotoAttachment();
        photoAttachment.setBaseId(driver.getDriverId().longValue());
        photoAttachment.setType(PhotoAttachTypeEnum.DRIVER.getValue());
        List<RespFileItemDto> fileItems = photoAttachmentMapper.selectFileItems(photoAttachment);
        infoDto.setFiles(fileItems);

        //每日限额
        VehicleQuotaLimit quotaLimit = vQuotaLimitMapper.selectCurrent();
        BigDecimal dailyLimit = (quotaLimit == null) ? new BigDecimal("1.00") : quotaLimit.getRate();

        BigDecimal dayCapacity = null;
        BigDecimal balanceCapacity = null;
        List<RespDriverVehicleInfoDto.Vehicle> list = vehicleMapper.selectByDriverIdAndState(driver.getDriverId(),StatusEnum.ENABLE.getValue());
        for (RespDriverVehicleInfoDto.Vehicle v : list) {
            VehicleDailyQuota dailyQuota = vDailyQuotaMapper.selectCurDateByVehicleId(StrUtils.str2Int(v.getVehicleId()));
            if (dailyQuota == null) {
                dailyQuota = new VehicleDailyQuota();
                dailyQuota.setFrozenQuota(new BigDecimal("0"));
                dailyQuota.setUsedQuota(new BigDecimal("0"));
            }

            //计算每日额度及可用额度
            dayCapacity = new BigDecimal(v.getCapacity()).multiply(dailyLimit);
            balanceCapacity = dayCapacity.subtract(dailyQuota.getFrozenQuota()).subtract(dailyQuota.getUsedQuota());

            v.setDayCapacity(dayCapacity.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            v.setBalanceCapacity((balanceCapacity.compareTo(new BigDecimal("0")) > 0) ? balanceCapacity.setScale(2, BigDecimal.ROUND_HALF_UP).toString() : "0.00");

            //查询图片
            photoAttachment.setBaseId(Long.valueOf(v.getVehicleId()));
            photoAttachment.setType(PhotoAttachTypeEnum.VEHICLE.getValue());
            fileItems = photoAttachmentMapper.selectFileItems(photoAttachment);

            v.setFiles(fileItems);
            v.setStatusName(VehicleApproveStatusEnum.getText(StrUtils.str2Int(v.getStatus())));
        }
        infoDto.setVehicles(list);

        return infoDto;
    }

    @Override
    public OutRespJson updateLogiCompanyStatus(ReqUpdateLogiCompanyStatusDto dto) {
        LogisticsCompany record = mapper.selectByIdentity(dto.getIdentity());
        if (record == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_LOGISTICSCOMPANY_NULL);
        }

        Date curDate = new Date();
        LogisticsCompany up = new LogisticsCompany();
        up.setOutIdentity(dto.getIdentity());
        up.setOutStatus(dto.getStatus());
        up.setUpdateTime(curDate);
        up.setLogisticsId(record.getLogisticsId());
        mapper.updateWithoutNull(up);

        return OutRespJson.success();
    }

    @Override
    public OutRespJson updateDriverInfo(ReqUpdateDriverDto dto) {
        Driver driver = driverMapper.selectByCustomerId(dto.getCustomerId());
        if (driver == null) {
            return OutRespJson.error(ResultCodeEnum.OUT_CUSTOMER_NULL);
        }

        Date curDate = new Date();
        Driver updateDriver = new Driver();
        updateDriver.setStatus(dto.getStatus());
        updateDriver.setPassword(dto.getPassword());
        updateDriver.setDriverId(driver.getDriverId());
        updateDriver.setStatusTime(curDate);
        updateDriver.setUpdateTime(curDate);
        updateDriver.setUpdateUserId(0);
        updateDriver.setRemark("添加/修改司机信息");
        driverMapper.updateByPrimaryKeySelective(updateDriver);

        JSONObject json = new JSONObject();
        json.put("customer_id", String.valueOf(dto.getCustomerId()));

        if (dto.getFiles() != null && dto.getFiles().size() > 0) {
            PhotoAttachment del = new PhotoAttachment();
            del.setBaseId(driver.getDriverId().longValue());
            del.setType(PhotoAttachTypeEnum.DRIVER.getValue());
            photoAttachmentMapper.deleteByBaseIdAndType(del);

            List<PhotoAttachment> attachmentList = new ArrayList<>();
            for (ReqUpdateDriverDto.File file : dto.getFiles()) {
                PhotoAttachment attachMent = new PhotoAttachment();
                attachMent.setStatus(StatusEnum.ENABLE.getValue());
                attachMent.setCreateTime(curDate);
                attachMent.setUpdateTime(curDate);
                attachMent.setBaseId(driver.getDriverId().longValue());
                attachMent.setType(PhotoAttachTypeEnum.DRIVER.getValue());
                attachMent.setOutId(file.getFileId());
                attachMent.setFilePath(file.getFileUrl());
                attachMent.setFileUrl(file.getFileUrl());
                attachmentList.add(attachMent);
            }
            photoAttachmentMapper.insertBatch(attachmentList);
        }

        return OutRespJson.success(json);
    }

    @Override
    public RespLogisticsQuotaDto getLogicQuota(ReqLogisticsQuotaDto dto) {
        if (dto == null || dto.getLogisticsId() == null) {
            throw new OpsException(ResultCodeEnum.OUT_PARAM_ERROR);
        }
        RespLogisticsQuotaDto resultDto = new RespLogisticsQuotaDto();
        resultDto.setLogisticsId(dto.getLogisticsId());
        LogisticsCreditQuota creditQuota = creditQuotaMapper.selectByLogisticsId(dto.getLogisticsId());
        //是否授信
        if (creditQuota == null) {
            return resultDto;
        }
        //额度是否过期判断
        Date currentDate = new Date();
        if (creditQuota.getStatus().intValue() == -1 ||
                currentDate.compareTo(creditQuota.getStartDate()) < 0 ||
                currentDate.compareTo(creditQuota.getEndDate()) > 0) {
            return resultDto;
        }
        //查询已使用
        LogisticsQuota logisticsQuota = quotaMapper.selectByLogisticsId(dto.getLogisticsId());
        BigDecimal mode = new BigDecimal(100);
        if (logisticsQuota != null) {
            BigDecimal creditQuotaValue = logisticsQuota.getCreditQuota().divide(mode);
            BigDecimal frozenQuota = logisticsQuota.getFrozenQuota().divide(mode);
            BigDecimal usedQuota = logisticsQuota.getUsedQuota().divide(mode);
            BigDecimal surplusQutota = creditQuotaValue.subtract(frozenQuota).subtract(usedQuota);

            resultDto.setCreditQuota(creditQuotaValue.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            resultDto.setFrozenQuota(frozenQuota.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            resultDto.setUsedQuota(usedQuota.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            resultDto.setSurplusQutota(surplusQutota.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        } else {
            BigDecimal creditQuotaValue = creditQuota.getCreditQuota().divide(mode);
            resultDto.setCreditQuota(creditQuotaValue.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        }
        return resultDto;
    }


    @Override
    public RespLogicQuotaUsedDto getLogicQuotaUsed(ReqLogicQuotaUsedDto dto) {
        RespLogicQuotaUsedDto respDto = new RespLogicQuotaUsedDto();
        respDto.setLogisticsId(dto.getLogisticsId());
        List<LogisticsDailyQuota> quotaList = logisticsDailyQuotaMapper.selectLogicQuotaUsed(dto.getLogisticsId(), dto.getStartDate(), dto.getEndDate());

        List<RespLogicQuotaUsedDto.LogicQuotaUseItem> itemList = new ArrayList<RespLogicQuotaUsedDto.LogicQuotaUseItem>();
        for (LogisticsDailyQuota quota : quotaList) {
            RespLogicQuotaUsedDto.LogicQuotaUseItem item = new RespLogicQuotaUsedDto.LogicQuotaUseItem();
            item.setDate(DateUtil.format(quota.getCurrentDate(), "yyyy-MM-dd"));
            BigDecimal amount = quota.getUsedQuota().add(quota.getFrozenQuota()).divide(new BigDecimal(100));
            item.setAmount(amount.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            itemList.add(item);
        }
        respDto.setItems(itemList);
        return respDto;
    }

	@Override
	public OutRespJson repayment(ReqLogisticsRepaymentDto dto) throws OpsException {
		LogisticsCompany logisticsCompany = mapper.selectByPrimaryKey(dto.getLogisticsId().longValue());
		if(logisticsCompany == null) {
			return OutRespJson.paramError("物流企业不存在");
		}
		
		List<LogisticsRepaymentLog> logList = repaymentLogMapper.selectByTxno(dto.getTxNo());
            if(logList != null && logList.size() > 0) {
                for(LogisticsRepaymentLog item : logList) {
                    if(item.getStatus().intValue() == 1) {
                        return OutRespJson.success();
                    }
                }
		}
		
		LogisticsQuota logisticsQuota = quotaMapper.selectByLogisticsId(dto.getLogisticsId());
		BigDecimal totalRepayMent = logisticsQuota.getUsedQuota().add(logisticsQuota.getFrozenQuota()); //总共需要还的额度
		BigDecimal frozenQuota = new BigDecimal("0"); //还款后的冻结额度
		BigDecimal usedQuota = new BigDecimal("0"); //还款后的已使用额度
		BigDecimal quotaTotal = logisticsQuota.getCreditQuota(); //还款后当前总额度 
		
		if(dto.getAmount().compareTo(totalRepayMent) < 0) {
			if (dto.getAmount().compareTo(logisticsQuota.getUsedQuota()) <= 0) {
				frozenQuota = logisticsQuota.getFrozenQuota();
				usedQuota = logisticsQuota.getUsedQuota().subtract(dto.getAmount());
			} else {
				frozenQuota = logisticsQuota.getFrozenQuota().subtract(dto.getAmount().subtract(logisticsQuota.getUsedQuota()));
			}
			quotaTotal = logisticsQuota.getCreditQuota().subtract(frozenQuota).subtract(usedQuota);
		}
		
		Date cur_date = new Date();
		LogisticsQuota updateQuota = new LogisticsQuota();
		updateQuota.setUpdateTime(cur_date);
		updateQuota.setFrozenQuota(frozenQuota);
		updateQuota.setUsedQuota(usedQuota);
		updateQuota.setId(logisticsQuota.getId());
		quotaMapper.updateByPrimaryKeySelective(updateQuota);
		
		LogisticsQuotaLog quotaLog = new LogisticsQuotaLog();
		quotaLog.setLogisticsId(dto.getLogisticsId());
		quotaLog.setRelationId(0l);
		quotaLog.setCreateTime(cur_date);
		quotaLog.setUpdateTime(cur_date);
		quotaLog.setCategory(20);
		quotaLog.setMethod(1);
		quotaLog.setRemark("物流还款");
		quotaLog.setQuota(dto.getAmount().compareTo(totalRepayMent) <= 0 ? dto.getAmount().intValue() : totalRepayMent.intValue());
		quotaLog.setQuotaTotal(quotaTotal.intValue());
		logisticsQuotaLogMapper.insertSelective(quotaLog);
		
		LogisticsRepaymentLog repaymentLog = new LogisticsRepaymentLog();
		repaymentLog.setRemark(dto.getRemark());
		repaymentLog.setAmount(dto.getAmount());
		repaymentLog.setLogisticsId(dto.getLogisticsId());
		repaymentLog.setStatus(1);
		repaymentLog.setStatusTime(cur_date);
		repaymentLog.setTxNo(dto.getTxNo());
		if(logList != null && logList.size() > 0) {
			repaymentLogMapper.updateByTxno(repaymentLog);
		} else {
			repaymentLog.setCreateTime(cur_date);
			repaymentLogMapper.insertSelective(repaymentLog);
		}
		
		return OutRespJson.success();
	}

	@Override
	public OutRespJson updateLogiVehicle(ReqUpdateVehicleDto dto) throws OpsException {
		if(StatusEnum.getText(dto.getState()) == null) {
			return OutRespJson.paramError("请填写正确的state");
		}
		
		Vehicle update = new Vehicle();
		update.setCapacity(dto.getCapacity());
		update.setState(dto.getState());
		update.setVehicleId(dto.getVehicleId());
		update.setUpdateTime(new Date());
		update.setRemark(StringUtils.isBlank(dto.getRemark()) ? "添加/修改车辆信息" : dto.getRemark());
		
		int i = vehicleMapper.updateByPrimaryKeySelective(update);
		
		return i == 1 ? OutRespJson.success() : OutRespJson.paramError("车辆信息不存在");
	}
}
