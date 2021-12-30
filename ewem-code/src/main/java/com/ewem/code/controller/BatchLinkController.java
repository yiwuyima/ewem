package com.ewem.code.controller;

import java.util.List;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;
import javax.validation.constraints.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ewem.common.annotation.RepeatSubmit;
import com.ewem.common.annotation.Log;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.enums.BusinessType;
import com.ewem.common.utils.poi.ExcelUtil;
import com.ewem.code.domain.BatchLink;
import com.ewem.code.service.IBatchLinkService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 批次环节记录Controller
 *
 * @author ewem
 * @date 2021-08-01
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/batchLink")
public class BatchLinkController extends BaseController {

    private final IBatchLinkService iBatchLinkService;

    /**
     * 查询批次环节记录列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:list')")
    @GetMapping("/list")
    public TableDataInfo list(BatchLink batchLink) {
        startPage();
        List<BatchLink> list = iBatchLinkService.queryList(batchLink);
        return getDataTable(list);
    }

    /**
     * 导出批次环节记录列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:export')")
    @Log(title = "批次环节记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BatchLink batchLink) {
        List<BatchLink> list = iBatchLinkService.queryList(batchLink);
        return new ExcelUtil<>(BatchLink.class).exportExcel(list, "批次环节记录");

    }

    /**
     * 获取批次环节记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iBatchLinkService.queryById(id));
    }

    /**
     * 新增批次环节记录
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:add')")
    @Log(title = "批次环节记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody BatchLink batchLink) {
        return toAjax(iBatchLinkService.insertBy(batchLink) ? 1 : 0);
    }

    /**
     * 修改批次环节记录
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:edit')")
    @Log(title = "批次环节记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody BatchLink batchLink) {
        return toAjax(iBatchLinkService.updateBy(batchLink) ? 1 : 0);
    }

    /**
     * 删除批次环节记录
     */
    @PreAuthorize("@ss.hasPermi('ewem:batchLink:remove')")
    @Log(title = "批次环节记录" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iBatchLinkService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
