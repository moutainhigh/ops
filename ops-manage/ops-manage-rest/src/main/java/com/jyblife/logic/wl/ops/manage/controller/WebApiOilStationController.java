package com.jyblife.logic.wl.ops.manage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.enums.StatusEnum;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.entity.OilStationAttachment;
import com.jyblife.logic.wl.ops.manage.dto.OilStationApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RepOilStationListDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.OilStationAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.service.OilStationService;

@RestController
@RequestMapping("/webAPI/oilStation")
public class WebApiOilStationController extends BaseController {
	
	@Autowired
	private OilStationService service;
	@Autowired
    private OilStationAttachmentMapper attMapper;
	
	@Permission(value="oil-station-list")
	@RequestMapping("/list")
    public RespJson list(@RequestBody(required = false) OilStationApplyListDto dto) {
		if (dto == null) {
			dto = new OilStationApplyListDto();
        }
		
		try {
			Page<RepOilStationListDto.OilStationItem> page = service.listPage(dto.getSearch(), dto.getPage(), dto.getPageSize());
			RepOilStationListDto respData = new RepOilStationListDto();
			List<RepOilStationListDto.OilStationItem> items = page.getResult();
			for(RepOilStationListDto.OilStationItem item : items) {
				item.setStatusName(StatusEnum.getText(StrUtils.str2Int(item.getStatus())));
			}
			
			respData.setData(items);
			respData.setPage(page.getPageNum());
			respData.setPageSize(page.getPageSize());
			respData.setTotalPages(page.getPages());
			respData.setTotalRows((int) page.getTotal());
			respData.setSearchItems(dto.getSearch());
			return RespJson.success(respData);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@Permission(value="oil-station-detail")
	@RequestMapping("/detail")
	public RespJson detail(@RequestParam("station_id") Integer stationId) {
		if(stationId == null) {
			return RespJson.error("参数station_id不能为空");
		}
		
		try {
			return service.detail(stationId);
		} catch (Exception e) {
			logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
		}
		
	}
	
	@RequestMapping("/getFile")
    public void getFile(@RequestParam("id") Integer id, @RequestParam("fileName") String fileName,
            				HttpServletRequest request, HttpServletResponse response) {

    	boolean downLoad = false;
        try {
        	if(id != null) {
        		OilStationAttachment attachment = attMapper.selectByPrimaryKey(id);
                if (attachment == null || attachment.getStatus().intValue() == -1) {
                    throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
                }
                
                downLoad = this.baseDownLoad(attachment.getFileUrl(), (StringUtils.isBlank(fileName) ? attachment.getName() : fileName), request, response);
        	}
        	
        } catch (Exception e) {
            logger.error("下载文件异常:{}", e.getMessage());
        } finally {
        	 if(!downLoad) {
				try {
					response.setContentType("application/json;charset=UTF-8");
	         		PrintWriter out = response.getWriter();
	         		RespJson resp = (id == null) ? RespJson.error(ResultCodeEnum.PARAM_ERROR) : RespJson.error("文件不存在");
					out.write(JSON.toJSONString(resp));
	         		out.close();
				} catch (IOException e) {
					logger.error("异常:{}", e.getMessage());
				}
         	}
		}

    }

}
