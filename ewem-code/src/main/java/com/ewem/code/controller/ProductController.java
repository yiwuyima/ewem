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
import com.ewem.code.domain.Product;
import com.ewem.code.service.IProductService;
import com.ewem.common.core.page.TableDataInfo;

/**
 * 产品管理Controller
 *
 * @author ewem
 * @date 2021-08-06
 */
@Validated
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping("/ewem/product")
public class ProductController extends BaseController {

    private final IProductService iProductService;

    /**
     * 查询产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(Product product) {
        startPage();
        List<Product> list = iProductService.queryList(product);
        return getDataTable(list);
    }

    /**
     * 导出产品管理列表
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:export')")
    @Log(title = "产品管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Product product) {
        List<Product> list = iProductService.queryList(product);
        return new ExcelUtil<>(Product.class).exportExcel(list, "产品管理");

    }

    /**
     * 获取产品管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return AjaxResult.success(iProductService.queryById(id));
    }

    /**
     * 新增产品管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:add')")
    @Log(title = "产品管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody Product product) {
        return toAjax(iProductService.insertBy(product) ? 1 : 0);
    }

    /**
     * 修改产品管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:edit')")
    @Log(title = "产品管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public AjaxResult edit(@Validated @RequestBody Product product) {
        return toAjax(iProductService.updateBy(product) ? 1 : 0);
    }

    /**
     * 删除产品管理
     */
    @PreAuthorize("@ss.hasPermi('ewem:product:remove')")
    @Log(title = "产品管理" , businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iProductService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
