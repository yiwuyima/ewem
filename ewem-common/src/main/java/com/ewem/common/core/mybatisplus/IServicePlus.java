package com.ewem.common.core.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ewem.common.core.page.PagePlus;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 自定义 Service 接口, 实现 数据库实体与 vo 对象转换返回
 *
 * @author ewem
 * @since 2021-08-20
 */
public interface IServicePlus<T> extends IService<T> {

    /**
     * @param id 主键id
     * @return T对象
     */
    T getById(Serializable id);


    /**
     * @param convertor 自定义转换器
     */
    default T getVoById(Serializable id, Function<T, T> convertor) {
        return convertor.apply(getById(id));
    }

    /**
     * @param idList id列表
     * @return T对象
     */
    List<T> listByIds(Collection<? extends Serializable> idList);


    /**
     * @param convertor 自定义转换器
     */
    default List<T> listVoByIds(Collection<? extends Serializable> idList,
                                Function<Collection<T>, List<T>> convertor) {
        List<T> list = listByIds(idList);
        if (list == null) {
            return null;
        }
        return convertor.apply(list);
    }

    /**
     * @param columnMap 表字段 map 对象
     * @return T对象
     */
    List<T> listByMap(Map<String, Object> columnMap);


    /**
     * @param convertor 自定义转换器
     */
    default List<T> listVoByMap(Map<String, Object> columnMap,
                                Function<Collection<T>, List<T>> convertor) {
        List<T> list = listByMap(columnMap);
        if (list == null) {
            return null;
        }
        return convertor.apply(list);
    }

    /**
     * @param queryWrapper 查询条件
     * @return T对象
     */
    T getOne(Wrapper<T> queryWrapper);


    /**
     * @param convertor 自定义转换器
     */
    default T getVoOne(Wrapper<T> queryWrapper, Function<T, T> convertor) {
        return convertor.apply(getOne(queryWrapper, true));
    }

    /**
     * @param queryWrapper 查询条件
     * @return T对象
     */
    List<T> list(Wrapper<T> queryWrapper);


    /**
     * @param convertor 自定义转换器
     */
    default List<T> listVo(Wrapper<T> queryWrapper, Function<Collection<T>, List<T>> convertor) {
        List<T> list = list(queryWrapper);
        if (list == null) {
            return null;
        }
        return convertor.apply(list);
    }

    default List<T> listVo() {
        return list(Wrappers.emptyWrapper());
    }

    /**
     * @param convertor 自定义转换器
     */
    default List<T> listVo(Function<Collection<T>, List<T>> convertor) {
        return listVo(Wrappers.emptyWrapper(), convertor);
    }

    /**
     * @param page         分页对象
     * @param queryWrapper 查询条件
     * @return T对象
     */
    PagePlus<T> page(PagePlus<T> page, Wrapper<T> queryWrapper);


    /**
     * @param convertor 自定义转换器
     */
    default PagePlus<T> pageVo(PagePlus<T> page, Wrapper<T> queryWrapper,
                               Function<Collection<T>, List<T>> convertor) {
        PagePlus<T> result = getBaseMapper().selectPage(page, queryWrapper);
        return result.setRecordsVo(convertor.apply(result.getRecords()));
    }

    default PagePlus<T> pageVo(PagePlus<T> page) {
        return page(page, Wrappers.emptyWrapper());
    }

    /**
     * @param convertor 自定义转换器
     */
    default PagePlus<T> pageVo(PagePlus<T> page, Function<Collection<T>, List<T>> convertor) {
        return pageVo(page, Wrappers.emptyWrapper(), convertor);
    }

    boolean saveAll(Collection<T> entityList);
}

