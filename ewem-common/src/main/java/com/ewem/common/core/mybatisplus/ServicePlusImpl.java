package com.ewem.common.core.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ewem.common.core.page.PagePlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * IServicePlus 实现类
 *
 * @author ewem
 * @since 2021-08-20
 */
@Slf4j
@SuppressWarnings("unchecked")
public class ServicePlusImpl<M extends BaseMapperPlus<T>, T> extends ServiceImpl<M, T> implements IServicePlus<T> {

    @Autowired
    protected M baseMapper;

    @Override
    public M getBaseMapper() {
        return baseMapper;
    }


    protected Class<T> entityClass = currentModelClass();

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    protected Class<T> mapperClass = currentMapperClass();

    protected Class<T> voClass = currentVoClass();

    public Class<T> getVoClass() {
        return voClass;
    }

    @Override
    protected Class<T> currentMapperClass() {
        return (Class<T>) this.getResolvableType().as(ServicePlusImpl.class).getGeneric(0).getType();
    }

    @Override
    protected Class<T> currentModelClass() {
        return (Class<T>) this.getResolvableType().as(ServicePlusImpl.class).getGeneric(1).getType();
    }

    protected Class<T> currentVoClass() {
        return (Class<T>) this.getResolvableType().as(ServicePlusImpl.class).getGeneric(1).getType();
    }

    @Override
    protected ResolvableType getResolvableType() {
        return ResolvableType.forClass(ClassUtils.getUserClass(getClass()));
    }

    /**
     * 单条执行性能差 适用于列表对象内容不确定
     */
    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return super.saveBatch(entityList, batchSize);
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        return super.saveOrUpdate(entity);
    }

    /**
     * 单条执行性能差 适用于列表对象内容不确定
     */
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return super.saveOrUpdateBatch(entityList, batchSize);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        return super.updateBatchById(entityList, batchSize);
    }

    /**
     * 单sql批量插入( 全量填充 无视数据库默认值 )
     * 适用于无脑插入
     */
    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        return saveOrUpdateBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean updateBatchById(Collection<T> entityList) {
        return updateBatchById(entityList, DEFAULT_BATCH_SIZE);
    }

    /**
     * 单sql批量插入( 全量填充 无视数据库默认值 )
     * 适用于无脑插入
     */
    @Override
    public boolean saveAll(Collection<T> entityList) {
        return baseMapper.insertAll(entityList) == entityList.size();
    }

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    @Override
    public T getById(Serializable id) {
        return getBaseMapper().selectById(id);
    }

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return getBaseMapper().selectByMap(columnMap);
    }

    /**
     * 根据 Wrapper，查询一条记录 <br/>
     * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    @Override
    public T getOne(Wrapper<T> queryWrapper) {
        return getOne(queryWrapper, true);
    }

    /**
     * 查询列表
     *
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     */
    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return getBaseMapper().selectList(queryWrapper);
    }

    /**
     * 翻页查询
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类
     */
    @Override
    public PagePlus<T> page(PagePlus<T> page, Wrapper<T> queryWrapper) {
        return getBaseMapper().selectPage(page, queryWrapper);
    }

}
