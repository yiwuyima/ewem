package com.ewem.code.controller;

import com.ewem.code.domain.Code;
import com.ewem.code.service.ICodeService;
import com.ewem.common.annotation.Log;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import com.ewem.common.core.page.TableDataInfo;
import com.ewem.common.enums.BusinessType;
import com.ewem.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 码管理Controller
 *
 * @author ewem
 * @date 2021-08-14
 */
@RestController
@RequestMapping("/ewem/code")
public class CodeController extends BaseController {
    @Autowired
    private ICodeService codeService;


    /**
     * 查询码管理列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:list')")
    @GetMapping("/list")
    public TableDataInfo list(Code code) {
        startPage();
        List<Code> list = codeService.selectCodeList(code);
        return getDataTable(list);
    }

    /**
     * 导出码管理列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:export')")
    @Log(title = "码管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Code code) {
        List<Code> list = codeService.export(code);
        ExcelUtil<Code> util = new ExcelUtil<Code>(Code.class);
        return util.exportExcel(list, "码管理数据");
    }

    /**
     * 获取码管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(codeService.selectCodeById(id));
    }

    /**
     * 新增码管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:add')")
    @Log(title = "码管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Code code) {
        return toAjax(codeService.insertCode(code));
    }

    /**
     * 修改码管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:edit')")
    @Log(title = "码管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Code code) {
        return toAjax(codeService.updateCode(code));
    }

    /**
     * 删除码管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:code:remove')")
    @Log(title = "码管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(codeService.deleteCodeByIds(ids));
    }

}
