package com.jyblife.logic.wl.ops.manage.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.jyblife.logic.wl.ops.common.contants.Constants;
import com.jyblife.logic.wl.ops.common.enums.OilPriceApplyStatusEnum;
import com.jyblife.logic.wl.ops.common.exception.OpsException;
import com.jyblife.logic.wl.ops.common.result.RespJson;
import com.jyblife.logic.wl.ops.common.result.ResultCodeEnum;
import com.jyblife.logic.wl.ops.common.utils.DateUtil;
import com.jyblife.logic.wl.ops.common.utils.ExcelUtil;
import com.jyblife.logic.wl.ops.common.utils.StrUtils;
import com.jyblife.logic.wl.ops.common.utils.ValidatorUtil;
import com.jyblife.logic.wl.ops.core.redis.RedisTemplates;
import com.jyblife.logic.wl.ops.entity.*;
import com.jyblife.logic.wl.ops.manage.dto.DelFileDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyDetailDto;
import com.jyblife.logic.wl.ops.manage.dto.OilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceApplyListDto;
import com.jyblife.logic.wl.ops.manage.dto.resp.RespOilPriceAttachementSaveDto;
import com.jyblife.logic.wl.ops.manage.exception.RestException;
import com.jyblife.logic.wl.ops.manage.export.ImportOilPriceVo;
import com.jyblife.logic.wl.ops.manage.factory.TencentCloudFactory;
import com.jyblife.logic.wl.ops.manage.intercept.Permission;
import com.jyblife.logic.wl.ops.manage.mapper.OilPriceApplyAttachmentMapper;
import com.jyblife.logic.wl.ops.manage.service.OilCompanyService;
import com.jyblife.logic.wl.ops.manage.service.OilGoodsService;
import com.jyblife.logic.wl.ops.manage.service.OilPriceApplyService;
import com.jyblife.logic.wl.ops.manage.service.OilStationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

@RestController
@RequestMapping("/webAPI/oilPriceApply")
public class WebApiOilPriceApplyController extends BaseController {

    @Autowired
    private OilPriceApplyAttachmentMapper applyAttachmentMapper;
    @Autowired
    private OilPriceApplyService oilPriceApplyService;
    @Autowired
    private OilGoodsService oilGoodsService;
    @Autowired
    private OilCompanyService oilCompanyService;
    @Autowired
    private OilStationService oilStationService;
    @Autowired
    private RedisTemplates redisTemplates;

    @Permission(value = "oil-price-apply-saveFile")
    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public RespJson saveFile(@RequestParam(value = "files[]") MultipartFile[] files,
                             @RequestParam(value = "type") Integer type,
                             @RequestParam(value = "id") Integer id,
                             @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId,
                             HttpServletRequest request) {

        if (files == null || files.length == 0) {
            throw new RestException(ResultCodeEnum.FAIL);
        }
        //只让支持一个文件
        try {
            MultipartFile file = files[0];
            //文件上传
            String key = this.baseUpload(file, type, Constants.OIL_PRICE_APPLY_ATTACHMENT_PATH, request);

            //保存文件地址到数据库
            OilPriceApplyAttachment oilPriceApplyAttachment = new OilPriceApplyAttachment();
            oilPriceApplyAttachment.setBaseId(id);
            oilPriceApplyAttachment.setType(type);
            oilPriceApplyAttachment.setCreateTime(new Date());
            oilPriceApplyAttachment.setFilePath(key);
            oilPriceApplyAttachment.setFileUrl(key);
            oilPriceApplyAttachment.setName(file.getOriginalFilename());
            oilPriceApplyAttachment.setStatus(1);
            oilPriceApplyAttachment.setUpdateTime(new Date());
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
                oilPriceApplyAttachment.setCreateUserId(systemUser.getUserId());
                oilPriceApplyAttachment.setUpdateUserId(systemUser.getUserId());
            }
            applyAttachmentMapper.insertAttachment(oilPriceApplyAttachment);

            //解析文件
            ExcelUtil excelUtil = new ExcelUtil(ImportOilPriceVo.class);
            String fileName = file.getOriginalFilename();
            String temp = fileName.substring(fileName.lastIndexOf('.'));
            List<ImportOilPriceVo> importList = excelUtil.getExcelToList(null, file.getInputStream(), temp.equals(".xlsx"));

            List<RespOilPriceAttachementSaveDto.RespOilPriceItem> items = new ArrayList<RespOilPriceAttachementSaveDto.RespOilPriceItem>();
            //验证数据
            if (!importList.isEmpty()) {
                //油品数据
                Map<String, OilGoods> oilMap = this.oilGoodsService.getAllOilGoodsMap();

                //油企油站数据
                Map<String, OilCompany> oilCompanyMap = oilCompanyService.getAllOilCompanyMap();
                Map<String, OilStation> OilStationMap = oilStationService.getAllOilStationMap(oilCompanyMap);

                for (ImportOilPriceVo vo : importList) {
                    RespOilPriceAttachementSaveDto.RespOilPriceItem respOilPriceItem = new RespOilPriceAttachementSaveDto.RespOilPriceItem();
                    BeanUtils.copyProperties(vo, respOilPriceItem);
                    StringBuffer result = new StringBuffer();

                    //1.验证非空字段以及数据正确性
                    List<String> errors = ValidatorUtil.validate(vo);
                    if (errors.size() > 0) {
                        String errorMsg = StringUtils.join(errors, ";");
                        result.append(";").append(errorMsg);

                    }
                    //必须大于0 且 零售价>=优惠价>=协议价 ？
                    if (vo.getRetailPrice() != null && vo.getDiscountPrice() != null && vo.getAgreedPrice() != null) {
                        BigDecimal zero = new BigDecimal("0.00");
                        if (vo.getRetailPrice().compareTo(zero) <= 0 || vo.getDiscountPrice().compareTo(zero) <= 0 || vo.getAgreedPrice().compareTo(zero) <= 0) {
                            result.append(";").append("所有价格必须大于0元");

                        }
                        if (vo.getDiscountPrice().compareTo(vo.getRetailPrice()) > 0 || vo.getAgreedPrice().compareTo(vo.getDiscountPrice()) > 0) {
                            result.append(";").append("零售价必须大于等于优惠价且优惠价必须大于等于协议价");

                        }
                    }

                    //2.验证油品
                    if (StringUtils.isNoneBlank(vo.getGoodsName())) {
                        if (!oilMap.containsKey(vo.getGoodsName().trim())) {
                            result.append(";").append("油品" + vo.getGoodsName() + "不存在");

                        }
                    }

                    //3.验证油企油站
                    if (StringUtils.isNoneBlank(vo.getOilCompanyName())) {
                        if (oilCompanyMap.containsKey(vo.getOilCompanyName().trim())) {
                            if (!OilStationMap.containsKey(vo.getOilCompanyName().trim() + "_" + vo.getOilStationName().trim())) {
                                result.append(";").append("油企" + vo.getOilCompanyName() + ":" + vo.getOilStationName() + "不存在");
                            }
                        } else {
                            result.append(";").append("油企" + vo.getOilCompanyName() + "不存在");
                        }
                    }

                    //设置验证结果
                    if (StringUtils.isNotEmpty(result.toString())) {
                        respOilPriceItem.setRemark(result.toString().substring(1));
                    }else{
                        respOilPriceItem.setRemark("成功");
                    }

                    items.add(respOilPriceItem);
                }
            }

            //返回文件id
            RespOilPriceAttachementSaveDto saveDto = new RespOilPriceAttachementSaveDto();
            saveDto.setId(StrUtils.int2Str(oilPriceApplyAttachment.getId()));
            saveDto.setName(oilPriceApplyAttachment.getName());
            saveDto.setStatus(0);
            saveDto.setType(StrUtils.int2Str(type));
            saveDto.setUrl(oilPriceApplyAttachment.getFileUrl());
            saveDto.setData(items);

            return RespJson.success(saveDto);
        } catch (Exception e) {
            logger.error("异常: " + e.getMessage(), e);
            throw new RestException(ResultCodeEnum.FAIL);
        }

    }

    @Permission(value = "oil-price-apply-delFile")
    @RequestMapping("/delFile")
    public RespJson delFile(@RequestBody @Validated DelFileDto dto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        TencentCloudFactory tencentCloudFactory = null;
        try {
            OilPriceApplyAttachment temp = applyAttachmentMapper.selectByPrimaryKey(dto.getId());
            if (temp == null || temp.getStatus().intValue() == -1) {
                throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
            }
            //删除文件服务器文件
            tencentCloudFactory = new TencentCloudFactory();
            tencentCloudFactory.deleteFile(temp.getFileUrl());

            //设置数据库文件的删除标记为-1
            OilPriceApplyAttachment oilPriceApplyAttachment = new OilPriceApplyAttachment();
            oilPriceApplyAttachment.setId(temp.getId());
            oilPriceApplyAttachment.setStatus(-1);
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            if (systemUser != null) {
                oilPriceApplyAttachment.setUpdateTime(new Date());
                oilPriceApplyAttachment.setUpdateUserId(systemUser.getUserId());
            }

            applyAttachmentMapper.updateByPrimaryKeySelective(oilPriceApplyAttachment);
            //返回删除结果
            return RespJson.success("操作成功！");
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        } finally {
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }

    }

    @Permission(value = "oil-price-apply-submit")
    @RequestMapping("/submit")
    public RespJson submit(@RequestBody Map<String, Integer> ids,
                           @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId,
                           HttpServletRequest request) {

        Integer fileId = ids.get("file_id");
        if (fileId == null) {
            throw new RestException(ResultCodeEnum.PARAM_ERROR);
        }

        //带了apply_id表明需要重新修改
        Integer applyId = null;
        if (ids.containsKey("apply_id")) {
            applyId = ids.get("apply_id");
        }

        OilPriceApply oilPriceApply = new OilPriceApply();
        if (applyId != null) {
            oilPriceApply = oilPriceApplyService.selectByApplyId(applyId);
            if (oilPriceApply == null) {
                //需要修改的申请记录不存在
                throw new RestException(ResultCodeEnum.OILPRICEAPPLY_NOT_EXIST);
            } else if (oilPriceApply.getStatus().intValue() != OilPriceApplyStatusEnum.REJECTED.getValue().intValue()) {
                //驳回状态才可以修改
                throw new RestException(ResultCodeEnum.OILPRICEAPPLY_EDIT_ERROR);
            }
        }

        TencentCloudFactory tencentCloudFactory = null;
        File file1 = null;
        try {
            //获取文件路劲
            OilPriceApplyAttachment applyAttachment = applyAttachmentMapper.selectByPrimaryKey(fileId);
            if (applyAttachment == null || applyAttachment.getStatus().intValue() == -1) {
                throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
            }

            String path = request.getServletContext().getRealPath("/");
            file1 = new File(path + applyAttachment.getFileUrl());
            tencentCloudFactory = new TencentCloudFactory();
            tencentCloudFactory.downLoadFile(file1, applyAttachment.getFileUrl());

            //下载文件
            if (!file1.exists()) {
                throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
            }
            FileInputStream fileInputStream = new FileInputStream(file1);

            //解析文件
            ExcelUtil excelUtil = new ExcelUtil(ImportOilPriceVo.class);
            String fileName = applyAttachment.getName();
            String temp = fileName.substring(fileName.lastIndexOf('.'));
            List<ImportOilPriceVo> importList = excelUtil.getExcelToList(null, fileInputStream, temp.equals(".xlsx"));
            if (importList.isEmpty()) {
                throw new RestException(ResultCodeEnum.FAIL, "文件内容不能为空");
            }

            //验证文件数据的合法性
            SystemUser systemUser = this.getCurSystemUser(sessionId);
            List<OilPriceItem> oilPriceItems = this.checkAndGetPriceItem(importList, systemUser);

            if (systemUser != null) {
                oilPriceApply.setCreateUserId(systemUser.getUserId());
                oilPriceApply.setUpdateUserId(systemUser.getUserId());
            }
            Date cur_date = new Date();
            if (applyId == null) {
                oilPriceApply.setCreateTime(cur_date);
            }
            oilPriceApply.setUpdateTime(cur_date);
            oilPriceApply.setEffectTime(cur_date);
            //暂时设置成审核通过
            oilPriceApply.setStatus(OilPriceApplyStatusEnum.SUBMITED.getValue());
            oilPriceApply.setStatusTime(cur_date);

            //设置编码
            String priceApplyCode = null;
            if (applyId == null) {
                String day = DateUtil.format(new Date(), "yyyyMMdd");
                Long value = redisTemplates.incrementAndGet(Constants.OPS_OIL_PRICE_APPLY_CODE_PREFIX + day, 1, 1 * 24 * 60 * 60L);
                priceApplyCode = Constants.PRICE_APPLY_CODE_PREFIX + day + StrUtils.getfixedNum(value.intValue(), 4);
                oilPriceApply.setCode(priceApplyCode);
            } else {
                priceApplyCode = oilPriceApply.getCode();
            }
            //将文件价格数据插入数据库
            if (applyId == null) {
                oilPriceApplyService.insertImportPrices(oilPriceItems, oilPriceApply, applyAttachment);
            } else {
                oilPriceApplyService.updateOilPriceApply(oilPriceItems, oilPriceApply, applyAttachment);
            }

            //返回成功
            return RespJson.success(priceApplyCode);
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        } finally {
            if (file1 != null && file1.exists()) {
                file1.delete();
            }
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }

    }

    private List<OilPriceItem> checkAndGetPriceItem(List<ImportOilPriceVo> importList, SystemUser systemUser) {
        //1.验证非空字段以及数据正确性
        for (ImportOilPriceVo vo : importList) {
            List<String> errors = ValidatorUtil.validate(vo);
            if (errors.size() > 0) {
                String errorMsg = StringUtils.join(errors, ";");
                throw new RestException(ResultCodeEnum.FAIL.getCode(), errorMsg);
            }
            //必须大于0 且 零售价>=优惠价>=协议价 ？
            BigDecimal zero = new BigDecimal("0.00");
            if (vo.getRetailPrice().compareTo(zero) <= 0 || vo.getDiscountPrice().compareTo(zero) <= 0 || vo.getAgreedPrice().compareTo(zero) <= 0) {
                throw new RestException(ResultCodeEnum.FAIL.getCode(), "所有价格必须大于0元");
            }
            if (vo.getDiscountPrice().compareTo(vo.getRetailPrice()) > 0 || vo.getAgreedPrice().compareTo(vo.getDiscountPrice()) > 0) {
                throw new RestException(ResultCodeEnum.FAIL.getCode(), "零售价必须大于等于优惠价且优惠价必须大于等于协议价");
            }
        }

        //2.验证油品
        Map<String, OilGoods> oilMap = this.oilGoodsService.getAllOilGoodsMap();
        for (ImportOilPriceVo vo : importList) {
            if (oilMap.containsKey(vo.getGoodsName().trim())) {
                continue;
            } else {
                throw new RestException(ResultCodeEnum.FAIL.getCode(), vo.getGoodsName() + "不存在");
            }
        }

        //3.验证油企油站
        Map<String, OilCompany> oilCompanyMap = oilCompanyService.getAllOilCompanyMap();
        Map<String, OilStation> OilStationMap = oilStationService.getAllOilStationMap(oilCompanyMap);
        for (ImportOilPriceVo vo : importList) {
            if (oilCompanyMap.containsKey(vo.getOilCompanyName().trim())) {
                if (OilStationMap.containsKey(vo.getOilCompanyName().trim() + "_" + vo.getOilStationName().trim())) {
                    continue;
                } else {
                    throw new RestException(ResultCodeEnum.FAIL.getCode(), vo.getOilCompanyName() + ":" + vo.getOilStationName() + "不存在");
                }
            } else {
                throw new RestException(ResultCodeEnum.FAIL.getCode(), vo.getOilCompanyName() + "不存在");
            }
        }

        //转为数据库实体类
        List<OilPriceItem> oilPriceItems = new ArrayList<OilPriceItem>();
        for (ImportOilPriceVo vo : importList) {
            OilPriceItem oilPriceItem = new OilPriceItem();
            oilPriceItem.setDiscountPrice(vo.getDiscountPrice().multiply(new BigDecimal("100")));
            oilPriceItem.setAgreedPrice(vo.getAgreedPrice().multiply(new BigDecimal("100")));
            oilPriceItem.setRetailPrice(vo.getRetailPrice().multiply(new BigDecimal("100")));
            //设置油品
            OilGoods oilGoods = oilMap.get(vo.getGoodsName().trim());
            oilPriceItem.setGoodsId(oilGoods.getGoodsId());
            //设置油企
            OilCompany oilCompany = oilCompanyMap.get(vo.getOilCompanyName().trim());
            oilPriceItem.setCompanyId(oilCompany.getCompanyId());
            //设置油站
            OilStation oilStation = OilStationMap.get(vo.getOilCompanyName().trim() + "_" + vo.getOilStationName().trim());
            oilPriceItem.setStationId(oilStation.getStationId());

            //设置用户
            if (systemUser != null) {
                oilPriceItem.setCreateUserId(systemUser.getUserId());
                oilPriceItem.setUpdateUserId(systemUser.getUserId());
            }
            oilPriceItem.setCreateTime(new Date());
            oilPriceItem.setUpdateTime(new Date());

            //加入列表
            oilPriceItems.add(oilPriceItem);
        }

        return oilPriceItems;
    }


    @Permission(value="oil-price-apply-getFile")
    @RequestMapping("/getFile")
    public void getFile(@RequestParam("id") Integer id, @RequestParam("fileName") String fileName,
                        HttpServletRequest request, HttpServletResponse response) {

        boolean downLoad = false;
        try {
            if (id != null) {
                OilPriceApplyAttachment attachment = applyAttachmentMapper.selectByPrimaryKey(id);
                if (attachment == null || attachment.getStatus().intValue() == -1) {
                    throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
                }

                downLoad = this.baseDownLoad(attachment.getFileUrl(), (StringUtils.isBlank(fileName) ? attachment.getName() : fileName), request, response);
            }

        } catch (Exception e) {
            logger.error("下载文件异常:{}", e.getMessage());
        } finally {
            if (!downLoad) {
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

    @Permission(value = "oil-price-apply-excelData")
    @RequestMapping("/excelData")
    public RespJson excelData(@RequestParam("file_id") Integer fileId,
                              HttpServletRequest request) {
        TencentCloudFactory tencentCloudFactory = null;
        File file1 = null;
        try {
            OilPriceApplyAttachment applyAttachment = applyAttachmentMapper.selectByPrimaryKey(fileId);
            if (applyAttachment == null || applyAttachment.getStatus().intValue() == -1) {
                throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
            }

            String path = request.getServletContext().getRealPath("/");
            file1 = new File(path + applyAttachment.getFileUrl());
            tencentCloudFactory = new TencentCloudFactory();
            tencentCloudFactory.downLoadFile(file1, applyAttachment.getFileUrl());

            //下载文件
            if (!file1.exists()) {
                throw new RestException(ResultCodeEnum.PRICEAPPLY_ATTACHMENT_NOT_EXIST);
            }
            FileInputStream fileInputStream = new FileInputStream(file1);

            //解析文件
            ExcelUtil excelUtil = new ExcelUtil(ImportOilPriceVo.class);
            String fileName = applyAttachment.getName();
            String temp = fileName.substring(fileName.lastIndexOf('.'));
            List<ImportOilPriceVo> importList = excelUtil.getExcelToList(null, fileInputStream, temp.equals(".xlsx"));

            Map<String, Object> data = new HashMap<String, Object>();
            data.put("data", importList);
            data.put("is_can_submit", false);
            return RespJson.success(data);
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw new RestException(ResultCodeEnum.FAIL);
        } finally {
            if (file1 != null && file1.exists()) {
                file1.delete();
            }
            if (tencentCloudFactory != null) {
                tencentCloudFactory.shutdown();
            }
        }

    }


    /**
     * 价格申请列表
     *
     * @return
     */
    @Permission(value = "oil-price-apply-list")
    @RequestMapping("/list")
    @ResponseBody
    public RespJson list(@RequestBody(required = false) OilPriceApplyListDto oilPriceApplyListDto, @CookieValue(Constants.USER_LOGIN_COOKIE_KEY) String sessionId) {
        try {
            if (oilPriceApplyListDto == null) {
                oilPriceApplyListDto = new OilPriceApplyListDto();
            }

            boolean isCanEdit = isHadPermission(sessionId, "oil-price-apply-submit");
            boolean isCanView = isHadPermission(sessionId, "oil-price-apply-detail");

            Page<RespOilPriceApplyListDto.OilPriceApplyItem> page = oilPriceApplyService.selectPageList(oilPriceApplyListDto.getSearch(), oilPriceApplyListDto.getPage(), oilPriceApplyListDto.getPageSize());

            page.getResult().stream().forEach(new Consumer<RespOilPriceApplyListDto.OilPriceApplyItem>() {
                @Override
                public void accept(RespOilPriceApplyListDto.OilPriceApplyItem oilPriceApplyItem) {
                    boolean edited = isCanEdit&&(oilPriceApplyItem.getStatus().intValue() == OilPriceApplyStatusEnum.REJECTED.getValue().intValue());
                    oilPriceApplyItem.setIsCanEdit(edited);
                    oilPriceApplyItem.setIsCanView(isCanView);
                }
            });

            RespOilPriceApplyListDto respDto = new RespOilPriceApplyListDto();
            respDto.setData(page.getResult());
            respDto.setPage(page.getPageNum());
            respDto.setPageSize(page.getPageSize());
            respDto.setSearchItems(oilPriceApplyListDto.getSearch());
            respDto.setTotalPages(page.getPages());
            respDto.setTotalRows((int) page.getTotal());
            return RespJson.success(respDto);
        } catch (OpsException e) {
            throw e;
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw e;
        }
    }


    @Permission(value = "oil-price-apply-detail")
    @RequestMapping("/detail")
    @ResponseBody
    public RespJson detail(@RequestParam("apply_id") Integer applyId) {
        try {
            OilPriceApplyDetailDto oilPriceApplyDetailDto = oilPriceApplyService.selectDetailByApplyId(applyId);

            return RespJson.success(oilPriceApplyDetailDto);
        } catch (Exception e) {
            logger.error("异常:{}", e.getMessage());
            throw e;
        }
    }
}
