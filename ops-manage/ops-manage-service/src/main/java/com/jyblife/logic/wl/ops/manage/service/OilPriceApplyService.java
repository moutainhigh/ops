package com.jyblife.logic.wl.ops.manage.service;

import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.entity.OilPriceApply;
import com.jyblife.logic.wl.ops.entity.OilPriceApplyAttachment;
import com.jyblife.logic.wl.ops.entity.OilPriceItem;
import com.jyblife.logic.wl.ops.entity.SystemUser;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceAuditListDto;
import com.jyblife.logic.wl.ops.manage.dto.req.ReqOilPriceAuditDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceAuditListDto;

import java.util.List;

public interface OilPriceApplyService {

    int insertImportPrices(List<OilPriceItem> importList,OilPriceApply oilPriceApply, OilPriceApplyAttachment applyAttachment);

    int updateOilPriceApply(List<OilPriceItem> importList,OilPriceApply oilPriceApply, OilPriceApplyAttachment applyAttachment);

    Page<RespOilPriceApplyListDto.OilPriceApplyItem> selectPageList(OilPriceApplyListDto.Search search, int page, int pageSize);
    
    OilPriceApplyDetailDto selectDetailByApplyId(Integer applyId);
    
    Page<RespOilPriceAuditListDto.OilPriceAuditItem> selectAuditList(OilPriceAuditListDto.Search search, int pageNum, int pageSize);

    OilPriceApply selectByApplyId(Integer applyId);
    
    RespJson audit(ReqOilPriceAuditDto dto, SystemUser user);
    
}
