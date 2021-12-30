package com.ewem.generator.mapper;


import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import com.ewem.generator.domain.GenTableColumn;

import java.util.List;

/**
 * 业务据层
 *
 * @author ewem
 */
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumn> {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @return 列信息
     */
    List<GenTableColumn> selectDbTableColumnsByName(String tableName);

}
