package com.ewem.code.service.impl;

import com.ewem.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.Category;
import com.ewem.code.mapper.CategoryMapper;
import com.ewem.code.service.ICategoryService;

import java.util.List;
import java.util.Collection;

/**
 * 产品分类Service业务层处理
 *
 * @author ewem
 * @date 2021-08-06
 */
@Service
public class CategoryServiceImpl extends ServicePlusImpl<CategoryMapper, Category> implements ICategoryService {

    @Override
    public Category queryById(Long id){
        return getById(id);
    }

    @Override
    public List<Category> queryList(Category category) {
        return list(buildQueryWrapper(category));
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(Category category) {
        //Map<String, Object> params = category.getParams();
        LambdaQueryWrapper<Category> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(category.getName()), Category::getName, category.getName());
        lqw.eq(StringUtils.isNotBlank(category.getImg()), Category::getImg, category.getImg());
        lqw.eq(StringUtils.isNotBlank(category.getStatus()), Category::getStatus, category.getStatus());
        return lqw;
    }

    @Override
    public Boolean insertBy(Category category) {
        validEntityBeforeSave(category);
        return save(category);
    }

    @Override
    public Boolean updateBy(Category category) {
        validEntityBeforeUpdate(category);
        return updateById(category);
    }

    /**
     * 保存前的数据校验
     *
     * @param category 实体类数据
     */
    private void validEntityBeforeSave(Category category){
    }

    /**
     * 修改前的数据校验
     *
     * @param category 实体类数据
     */
    private void validEntityBeforeUpdate(Category category) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
        }
        return removeByIds(ids);
    }
}
