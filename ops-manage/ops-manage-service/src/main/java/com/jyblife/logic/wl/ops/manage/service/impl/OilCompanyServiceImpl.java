package com.jyblife.logic.wl.ops.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jyblife.logic.wl.ops.common.enums.OilCompanyOwnershipEnum;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.OilCompany;
import com.jyblife.logic.wl.ops.entity.OilCompanyAttachment;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanyListDto.Search;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanySaveDto;
import com.jyblife.logic.wl.ops.manage.dto.OilCompanySaveDto.File;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyListDto;
import com.jyblife.logic.wl.ops.manage.exception.ManageServiceException;
import com.jyblife.logic.wl.ops.manage.mapper.OilCompanyAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.mapper.OilCompanyMapper;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyService;

@Service
@Transactional
public class OilCompanyServiceImpl implements OilCompanyService {

    @Autowired
    private OilCompanyMapper mapper;
    @Autowired
    private OilCompanyAttachmentMapper attMapper;

    @Override
    public Page<RepOilCompanyListDto.OilCompanyItem> listPage(Search search, int page, int pageSize) {
        Page<RepOilCompanyListDto.OilCompanyItem> pager = PageHelper.startPage(page, pageSize);
        mapper.listPage(search);
        return pager;
    }

    @Override
    public List<OilCompany> listAllEnable() {
        return mapper.selectCompanyByStatus(1);
    }

    @Override
    public RespJson detail(Integer companyId) {
        RepOilCompanyDetailDto detailDto = mapper.selectByCompanyId(companyId);
        if(detailDto == null) {
            return RespJson.success(detailDto);
        }

        detailDto.setStatusName(StatusEnum.getText(detailDto.getStatus()));
        if(StringUtils.isNotBlank(detailDto.getOwnership())) {
        	detailDto.setOwnershipName(OilCompanyOwnershipEnum.getText(StrUtils.str2Int(detailDto.getOwnership())));
		}

        Map<String, Object> param = new HashMap<>();
        param.put("baseId", detailDto.getCompanyId());
        param.put("status", 1);
        List<OilCompanyAttachment> dataList = attMapper.list(OilCompanyAttachment.class, param);

        if(dataList != null && dataList.size() > 0) {
            for(OilCompanyAttachment at : dataList) {
                com.jyblife.logic.wl.ops.manage.dto.resp.RepOilCompanyDetailDto.File f = new RepOilCompanyDetailDto.File();
                BeanUtils.copyProperties(at, f);
                f.setUrl(at.getFileUrl());
                detailDto.getFiles().add(f);
            }

        }

        return RespJson.success(detailDto);
    }

    @Override
    public RespJson save(OilCompanySaveDto dto, int userId) throws ManageServiceException {
        OilCompany company = new OilCompany();
        BeanUtils.copyProperties(dto, company);
        Date curDate = new Date();
        company.setUpdateUserId(userId);
        company.setUpdateTime(curDate);
        company.setStatusTime(curDate);
        company.setEffectTime(curDate);
        if(StringUtils.isNotBlank(dto.getBuildDate())) {
        	company.setBuildDate(DateUtil.parse(dto.getBuildDate(), "yyyy-MM-dd"));
        }

        //检查企业名称，纳税号，短名称是否已经存在
        OilCompany paramCompany = new OilCompany();
        paramCompany.setName(dto.getName());
        paramCompany.setShortName(dto.getShortName());
        paramCompany.setTaxCode(dto.getTaxCode());
        List<OilCompany> existList = mapper.selectExistCompany(paramCompany);

        if(company.getCompanyId() == null) {
            if(existList.isEmpty()) {
                company.setCreateUserId(userId);
                company.setCreateTime(curDate);
                mapper.insertOilCompany(company);
            }else{
                for(OilCompany oilCompany : existList){
                    if(StringUtils.equalsIgnoreCase(oilCompany.getName(),dto.getName())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_NAME_ERROR);
                    }
                    if(StringUtils.equalsIgnoreCase(oilCompany.getShortName(),dto.getShortName())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_SHORTNAME_ERROR);
                    }
                    if(StringUtils.equalsIgnoreCase(oilCompany.getTaxCode(),dto.getTaxCode())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_TAXCODE_ERROR);
                    }
                }
            }
        } else {
            for(OilCompany oilCompany : existList){
                if(oilCompany.getCompanyId().intValue() != dto.getCompanyId().intValue()){
                    if(StringUtils.equalsIgnoreCase(oilCompany.getName(),dto.getName())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_NAME_ERROR);
                    }
                    if(StringUtils.equalsIgnoreCase(oilCompany.getShortName(),dto.getShortName())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_SHORTNAME_ERROR);
                    }
                    if(StringUtils.equalsIgnoreCase(oilCompany.getTaxCode(),dto.getTaxCode())){
                        throw new ManageServiceException(ResultCodeEnum.OILCOMPANY_TAXCODE_ERROR);
                    }
                }

            }
        	mapper.updateByPrimaryKeySelective(company);
        }
        
        List<File> files = dto.getFiles();
        if(files != null && files.size() > 0) {
            for(File f : files) {
                OilCompanyAttachment att = new OilCompanyAttachment();
                BeanUtils.copyProperties(f, att);
                att.setBaseId(company.getCompanyId());
                att.setStatus(1);
                att.setFileUrl(f.getUrl());
                attMapper.updateByPrimaryKeySelective(att);
            }
        }

        return RespJson.success(company.getCompanyId());
    }

    @Override
    public Map<String, OilCompany> getAllOilCompanyMap() {
        List<OilCompany> list = mapper.list(OilCompany.class, new HashMap<String, Object>(0));
        Map<String, OilCompany> oilGoodsMap = new HashMap<String, OilCompany>();
        for (OilCompany oilCompany : list) {
            oilGoodsMap.put(oilCompany.getName().trim(), oilCompany);
        }
        return oilGoodsMap;
    }

	@Override
	public RespJson delFile(int fileId, int userId) throws ManageServiceException {
		OilCompanyAttachment att = new OilCompanyAttachment();
		att.setUpdateUserId(userId);
		att.setUpdateTime(new Date());
		att.setId(fileId);
		att.setStatus(-1);
		attMapper.updateByPrimaryKeySelective(att);
		return RespJson.success();
	}

}
