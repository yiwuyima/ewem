package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.Batch;
import com.ewem.code.mapper.BatchMapper;
import com.ewem.code.service.IBatchService;
import com.ewem.code.service.IProductService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 批次Service业务层处理
 *
 * @author ewem
 * @date 2021-08-07
 */
@Service
public class BatchServiceImpl extends ServicePlusImpl<BatchMapper, Batch> implements IBatchService {

    @Autowired
    BatchMapper batchMapper;

    @Autowired
    IProductService productService;

    @Override
    public Batch queryById(Long id) {
        Batch batch = getById(id);
        batch.setProduct(productService.queryById(batch.getProductId()));
        return batch;
    }

    @Override
    public List<Batch> queryList(Batch batch) {
        return batchMapper.selectBatchList(batch);
    }

    @Override
    public Boolean insertBy(Batch batch) {
        validEntityBeforeSave(batch);
        return save(batch);
    }

    @Override
    public Boolean updateBy(Batch batch) {
        validEntityBeforeUpdate(batch);
        return updateById(batch);
    }

    /**
     * 保存前的数据校验
     *
     * @param batch 实体类数据
     */
    private void validEntityBeforeSave(Batch batch) {
    }

    /**
     * 修改前的数据校验
     *
     * @param batch 实体类数据
     */
    private void validEntityBeforeUpdate(Batch batch) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
        }
        return removeByIds(ids);
    }
}
