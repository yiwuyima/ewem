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
import com.ewem.code.domain.Link;
import com.ewem.code.service.ILinkService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 环节模板Controller
 *
 * @author ewem
 * @date 2021-08-01
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/link")
public class LinkController extends BaseController {

    private final ILinkService iLinkService;

    /**
     * 查询环节模板列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:list')")
    @GetMapping("/list")
    public TableDataInfo list(Link link) {
        startPage();
        List<Link> list = iLinkService.queryList(link);
        return getDataTable(list);
    }

    /**
     * 导出环节模板列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:export')")
    @Log(title = "环节模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Link link) {
        List<Link> list = iLinkService.queryList(link);
        return new ExcelUtil<>(Link.class).exportExcel(list, "环节模板");

    }

    /**
     * 获取环节模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iLinkService.queryById(id));
    }

    /**
     * 新增环节模板
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:add')")
    @Log(title = "环节模板", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody Link link) {
        return toAjax(iLinkService.insertBy(link) ? 1 : 0);
    }

    /**
     * 修改环节模板
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:edit')")
    @Log(title = "环节模板", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody Link link) {
        return toAjax(iLinkService.updateBy(link) ? 1 : 0);
    }

    /**
     * 删除环节模板
     */
    @PreAuthorize("@ss.hasPermi('ewem:link:remove')")
    @Log(title = "环节模板" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iLinkService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
