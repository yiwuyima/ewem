package com.ewem.code.service.impl;

import com.ewem.code.domain.*;
import com.ewem.code.domain.vo.TraceVo;
import com.ewem.code.service.*;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.exception.CustomException;
import com.ewem.common.utils.JsonUtils;
import com.ewem.common.utils.StringUtils;
import com.ewem.common.utils.file.ImageUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 溯源Service业务层处理
 *
 * @author ewem
 * @date 2021-08-06
 */
@Service
public class TraceServiceImpl implements ITraceService {

    private static final Logger log = LoggerFactory.getLogger(TraceServiceImpl.class);

    @Autowired
    ICodeService codeService;

    @Autowired
    IBatchService batchService;

    @Autowired
    IProductService productService;

    @Autowired
    IBatchLinkService batchLinkService;

    @Autowired
    IScanLogService scanLogService;


    @Override
    @Transactional
    public AjaxResult trace(String c) {
        Code code = codeService.selectCodeByCode(c);
        if (StringUtils.isEmpty(code)) {
            log.error("错误码 {}", code);
            return AjaxResult.error("错误码");
        }
        if (StringUtils.isEmpty(code.getBatch()) || StringUtils.isEmpty(code.getBatch().getProductId())) {
            log.error("批次产品错误,code:{},code info:{}", c, JsonUtils.toJsonString(code));
            return AjaxResult.success();
        }
        BatchLink batchLink = new BatchLink();
        batchLink.setBatchId(code.getBatchId());
        List<BatchLink> links = batchLinkService.queryList(batchLink);
        links = links.stream().filter(l -> l.getLink().getVisible().equals("0")).collect(Collectors.toList());
        links.sort(Comparator.comparing(o -> o.getLink().getOrderNum()));

        // 设置扫码次数及时间
        code.setScanNum(code.getScanNum() + 1);
        code.setFirstScanTime(StringUtils.isEmpty(code.getFirstScanTime()) ? new Date() : code.getFirstScanTime());
        codeService.updateById(code);

        TraceVo traceVo = new TraceVo();
        traceVo.setProduct(productService.queryById(code.getBatch().getProductId()));
        traceVo.setBatch(code.getBatch());
        traceVo.setLinks(links);
        traceVo.setScanNum(code.getScanNum());
        traceVo.setFirstScanTime(code.getFirstScanTime());
        traceVo.setUseAnti(StringUtils.isNotEmpty(code.getAntiCode()));

        ScanLog scanLog = new ScanLog();
        scanLog.setCode(c);
        scanLog.setScanTime(new Date());
        scanLogService.save(scanLog);

        return AjaxResult.success(traceVo);

    }

    @Override
    public AjaxResult antiCheck(String c, String antiCode) {
        Code code = codeService.selectCodeByCode(c);
        return AjaxResult.success(StringUtils.isNotEmpty(code) && antiCode.equals(code.getAntiCode()));
    }
}
