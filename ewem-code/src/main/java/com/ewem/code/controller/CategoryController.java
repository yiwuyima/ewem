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
import com.ewem.code.domain.Category;
import com.ewem.code.service.ICategoryService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 产品分类Controller
 *
 * @author ewem
 * @date 2021-08-06
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/category")
public class CategoryController extends BaseController {

    private final ICategoryService iCategoryService;

    /**
     * 查询产品分类列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(Category category) {
        startPage();
        List<Category> list = iCategoryService.queryList(category);
        return getDataTable(list);
    }

    /**
     * 导出产品分类列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:export')")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Category category) {
        List<Category> list = iCategoryService.queryList(category);
        return new ExcelUtil<>(Category.class).exportExcel(list, "产品分类");

    }

    /**
     * 获取产品分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iCategoryService.queryById(id));
    }

    /**
     * 新增产品分类
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:add')")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody Category category) {
        return toAjax(iCategoryService.insertBy(category) ? 1 : 0);
    }

    /**
     * 修改产品分类
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:edit')")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody Category category) {
        return toAjax(iCategoryService.updateBy(category) ? 1 : 0);
    }

    /**
     * 删除产品分类
     */
    @PreAuthorize("@ss.hasPermi('ewem:category:remove')")
    @Log(title = "产品分类" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iCategoryService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
