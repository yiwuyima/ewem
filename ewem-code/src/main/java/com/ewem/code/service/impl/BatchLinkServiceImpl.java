package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.BatchLink;
import com.ewem.code.mapper.BatchLinkMapper;
import com.ewem.code.service.IBatchLinkService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 批次环节记录Service业务层处理
 *
 * @author ewem
 * @date 2021-08-01
 */
@Service
public class BatchLinkServiceImpl extends ServicePlusImpl<BatchLinkMapper, BatchLink> implements IBatchLinkService {

    @Autowired
    BatchLinkMapper batchLinkMapper;

    @Override
    public BatchLink queryById(Long id){
        return getById(id);
    }

    @Override
    public List<BatchLink> queryList(BatchLink batchLink) {
        return batchLinkMapper.selectBatchLinkList(batchLink);
    }

    private LambdaQueryWrapper<BatchLink> buildQueryWrapper(BatchLink batchLink) {
        //Map<String, Object> params = batchLink.getParams();
        LambdaQueryWrapper<BatchLink> lqw = Wrappers.lambdaQuery();
        lqw.eq(batchLink.getBatchId() != null, BatchLink::getBatchId, batchLink.getBatchId());
        lqw.eq(batchLink.getLinkId() != null, BatchLink::getLinkId, batchLink.getLinkId());
        lqw.eq(StringUtils.isNotBlank(batchLink.getStatus()), BatchLink::getStatus, batchLink.getStatus());
        return lqw;
    }

    @Override
    public Boolean insertBy(BatchLink batchLink) {
        validEntityBeforeSave(batchLink);
        return save(batchLink);
    }

    @Override
    public Boolean updateBy(BatchLink batchLink) {
        validEntityBeforeUpdate(batchLink);
        return updateById(batchLink);
    }

    /**
     * 保存前的数据校验
     *
     * @param batchLink 实体类数据
     */
    private void validEntityBeforeSave(BatchLink batchLink){
    }

    /**
     * 修改前的数据校验
     *
     * @param batchLink 实体类数据
     */
    private void validEntityBeforeUpdate(BatchLink batchLink) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
        }
        return removeByIds(ids);
    }
}
