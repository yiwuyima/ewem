package com.ewem.code.controller;

import java.util.List;
import java.util.Arrays;

import com.ewem.code.domain.Batch;
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
import com.ewem.code.service.IBatchService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 批次Controller
 *
 * @author ewem
 * @date 2021-08-07
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/batch")
public class BatchController extends BaseController {

    private final IBatchService iBatchService;

    /**
     * 查询批次列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:list')")
    @GetMapping("/list")
    public TableDataInfo list(Batch batch) {
        startPage();
        List<Batch> list = iBatchService.queryList(batch);
        return getDataTable(list);
    }

    /**
     * 查询批次列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:select')")
    @GetMapping("/select")
    public TableDataInfo select(Batch batch) {
        List<Batch> list = iBatchService.queryList(batch);
        return getDataTable(list);
    }

    /**
     * 导出批次列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:export')")
    @Log(title = "批次", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Batch batch) {
        List<Batch> list = iBatchService.queryList(batch);
        return new ExcelUtil<>(Batch.class).exportExcel(list, "批次");

    }

    /**
     * 获取批次详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iBatchService.queryById(id));
    }

    /**
     * 新增批次
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:add')")
    @Log(title = "批次", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody Batch batch) {
        return toAjax(iBatchService.insertBy(batch) ? 1 : 0);
    }

    /**
     * 修改批次
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:edit')")
    @Log(title = "批次", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody Batch batch) {
        return toAjax(iBatchService.updateBy(batch) ? 1 : 0);
    }

    /**
     * 删除批次
     */
    @PreAuthorize("@ss.hasPermi('ewem:batch:remove')")
    @Log(title = "批次" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iBatchService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
