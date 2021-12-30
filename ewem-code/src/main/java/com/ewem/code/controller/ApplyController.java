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
import com.ewem.code.domain.Apply;
import com.ewem.code.service.IApplyService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 码申请Controller
 *
 * @author ewem
 * @date 2021-08-01
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/apply")
public class ApplyController extends BaseController {

    private final IApplyService iApplyService;

    /**
     * 查询码申请列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(Apply apply) {
        startPage();
        List<Apply> list = iApplyService.queryList(apply);
        return getDataTable(list);
    }

    /**
     * 导出码申请列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:export')")
    @Log(title = "码申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Apply apply) {
        List<Apply> list = iApplyService.queryList(apply);
        return new ExcelUtil<>(Apply.class).exportExcel(list, "码申请");

    }

    /**
     * 获取码申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iApplyService.queryById(id));
    }

    /**
     * 新增码申请
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:add')")
    @Log(title = "码申请", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody Apply apply) {
        return toAjax(iApplyService.insertBy(apply) ? 1 : 0);
    }

    /**
     * 修改码申请
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:edit')")
    @Log(title = "码申请", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody Apply apply) {
        return toAjax(iApplyService.updateBy(apply) ? 1 : 0);
    }

    /**
     * 删除码申请
     */
    @PreAuthorize("@ss.hasPermi('ewem:apply:remove')")
    @Log(title = "码申请" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iApplyService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
