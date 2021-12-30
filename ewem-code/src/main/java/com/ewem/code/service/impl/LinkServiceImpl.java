package com.ewem.code.service.impl;

import com.ewem.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.Link;
import com.ewem.code.mapper.LinkMapper;
import com.ewem.code.service.ILinkService;

import java.util.List;
import java.util.Collection;

/**
 * 环节模板Service业务层处理
 *
 * @author ewem
 * @date 2021-08-01
 */
@Service
public class LinkServiceImpl extends ServicePlusImpl<LinkMapper, Link> implements ILinkService {

    @Override
    public Link queryById(Long id){
        return getById(id);
    }

    @Override
    public List<Link> queryList(Link link) {
        return list(buildQueryWrapper(link));
    }

    private LambdaQueryWrapper<Link> buildQueryWrapper(Link link) {
        //Map<String, Object> params = link.getParams();
        LambdaQueryWrapper<Link> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(link.getName()), Link::getName, link.getName());
        lqw.eq(StringUtils.isNotBlank(link.getStatus()), Link::getStatus, link.getStatus());
        return lqw;
    }

    @Override
    public Boolean insertBy(Link link) {
        validEntityBeforeSave(link);
        return save(link);
    }

    @Override
    public Boolean updateBy(Link link) {
        validEntityBeforeUpdate(link);
        return updateById(link);
    }

    /**
     * 保存前的数据校验
     *
     * @param link 实体类数据
     */
    private void validEntityBeforeSave(Link link){
    }

    /**
     * 修改前的数据校验
     *
     * @param link 实体类数据
     */
    private void validEntityBeforeUpdate(Link link) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
        }
        return removeByIds(ids);
    }
}
