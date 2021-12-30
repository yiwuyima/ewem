package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.ewem.common.annotation.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;

import lombok.EqualsAndHashCode;
import com.ewem.common.core.domain.BaseEntityPlus;

/**
 * 环节模板对象 ewem_link
 *
 * @author ewem
 * @date 2021-08-01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("ewem_link")
public class Link extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 环节名称
     */
    @Excel(name = "环节名称")
    private String name;

    /**
     * 前端显示名称
     */
    @Excel(name = "前端显示名称")
    private String showName;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Integer orderNum;

    /**
     * 是否显示
     */
    @Excel(name = "是否显示")
    private String visible;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

}
