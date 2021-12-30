package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.Product;
import com.ewem.code.mapper.CategoryMapper;
import com.ewem.code.mapper.ProductMapper;
import com.ewem.code.service.IProductService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 产品管理Service业务层处理
 *
 * @author ewem
 * @date 2021-08-06
 */
@Service
public class ProductServiceImpl extends ServicePlusImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryMapper categoryMapper;


    @Override
    public Product queryById(Long id) {
        Product product = getById(id);
        product.setCategory(categoryMapper.selectById(product.getCategoryId()));
        return product;
    }

    @Override
    public List<Product> queryList(Product product) {
        return productMapper.selectProductList(product);
    }

    private LambdaQueryWrapper<Product> buildQueryWrapper(Product product) {
        //Map<String, Object> params = product.getParams();
        LambdaQueryWrapper<Product> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(product.getCode()), Product::getCode, product.getCode());
        lqw.like(StringUtils.isNotBlank(product.getName()), Product::getName, product.getName());
        return lqw;
    }

    @Override
    public Boolean insertBy(Product product) {
        validEntityBeforeSave(product);
        return save(product);
    }

    @Override
    public Boolean updateBy(Product product) {
        validEntityBeforeUpdate(product);
        return updateById(product);
    }

    /**
     * 保存前的数据校验
     *
     * @param product 实体类数据
     */
    private void validEntityBeforeSave(Product product) {
    }

    /**
     * 修改前的数据校验
     *
     * @param product 实体类数据
     */
    private void validEntityBeforeUpdate(Product product) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
        }
        return removeByIds(ids);
    }
}
