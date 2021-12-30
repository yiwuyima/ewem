package com.ewem.generator.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import com.ewem.generator.domain.GenTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务 数据层
 *
 * @author ruoyi
 */
public interface GenTableMapper extends BaseMapperPlus<GenTable> {


    Page<GenTable> selectPageGenTableList(@Param("page") Page<GenTable> page, @Param("genTable") GenTable genTable);

    Page<GenTable> selectPageDbTableList(@Param("page") Page<GenTable> page, @Param("genTable") GenTable genTable);

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 查询表ID业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 查询表名称业务信息
     *
     * @param tableName 表名称
     * @return 业务信息
     */
    GenTable selectGenTableByName(String tableName);

}
