package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 产品分类对象 ewem_category
 *
 * @author ewem
 * @date 2021-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("ewem_category")
public class Category extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @Excel(name = "名称")
    private String name;

    /**
     * 图片
     */
    @Excel(name = "图片")
    private String img;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

}
