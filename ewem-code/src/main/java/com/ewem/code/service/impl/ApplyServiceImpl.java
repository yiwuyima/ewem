package com.ewem.code.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.Apply;
import com.ewem.code.mapper.ApplyMapper;
import com.ewem.code.service.IApplyService;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.enums.ApplyStatus;
import com.ewem.common.exception.CustomException;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 码申请Service业务层处理
 *
 * @author ewem
 * @date 2021-08-01
 */
@Service
public class ApplyServiceImpl extends ServicePlusImpl<ApplyMapper, Apply> implements IApplyService {

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public Apply queryById(Long id) {
        return getById(id);
    }

    @Override
    public List<Apply> queryList(Apply apply) {
        return applyMapper.selectApplyList(apply);
    }

    private LambdaQueryWrapper<Apply> buildQueryWrapper(Apply apply) {
        //Map<String, Object> params = apply.getParams();
        LambdaQueryWrapper<Apply> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(apply.getName()), Apply::getName, apply.getName());
        lqw.eq(StringUtils.isNotBlank(apply.getApplyStatus()), Apply::getApplyStatus, apply.getApplyStatus());
        return lqw;
    }

    @Override
    public Boolean insertBy(Apply apply) {
        validEntityBeforeSave(apply);
        return save(apply);
    }

    @Override
    public Boolean updateBy(Apply apply) {
        validEntityBeforeUpdate(apply);
        return updateById(apply);
    }

    /**
     * 保存前的数据校验
     *
     * @param apply 实体类数据
     */
    private void validEntityBeforeSave(Apply apply) {
        apply.setApplyStatus(ApplyStatus.INIT.getCode());
    }

    /**
     * 修改前的数据校验
     *
     * @param apply 实体类数据
     */
    private void validEntityBeforeUpdate(Apply apply) {
        Apply applyDTO = queryById(apply.getId());
        if (!ApplyStatus.INIT.getCode().equals(applyDTO.getApplyStatus())) {
            throw new CustomException("当前申请状态不可修改");
        }
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
        }
        return removeByIds(ids);
    }

    @Override
    public Apply selectOneByApplyStatus(ApplyStatus applyStatus) {
        LambdaQueryWrapper<Apply> lqw = Wrappers.lambdaQuery();
        lqw.eq(Apply::getApplyStatus, applyStatus.getCode()).last(" limit 1");
        return getOne(lqw);
    }
}
