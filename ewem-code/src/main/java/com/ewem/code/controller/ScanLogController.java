package com.ewem.code.controller;

import com.ewem.code.domain.ScanLog;
import com.ewem.code.service.IScanLogService;
import com.ewem.common.annotation.Log;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.core.page.TableDataInfo;
import com.ewem.common.enums.BusinessType;
import com.ewem.common.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 扫码记录Controller
 *
 * @author ewem
 * @date 2021-08-16
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/scanLog")
public class ScanLogController extends BaseController {

    private final IScanLogService iScanLogService;

    /**
     * 查询扫码记录列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:scanLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(ScanLog scanLog) {
        startPage();
        List<ScanLog> list = iScanLogService.queryList(scanLog);
        return getDataTable(list);
    }

    /**
     * 导出扫码记录列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:scanLog:export')")
    @Log(title = "扫码记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScanLog scanLog) {
        List<ScanLog> list = iScanLogService.queryList(scanLog);
        return new ExcelUtil<>(ScanLog.class).exportExcel(list, "扫码记录");

    }
}
