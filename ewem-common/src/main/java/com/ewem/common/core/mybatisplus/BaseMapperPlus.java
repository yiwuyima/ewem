package com.ewem.common.core.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * 自定义 Mapper 接口, 实现 自定义扩展
 *
 * @author ewem
 * @since 2021-08-20
 */
public interface BaseMapperPlus<T> extends BaseMapper<T> {

    /**
     * 单sql批量插入( 全量填充 无视数据库默认值 )
     * 适用于无脑插入
     */
    int insertAll(@Param("list") Collection<T> batchList);

}
