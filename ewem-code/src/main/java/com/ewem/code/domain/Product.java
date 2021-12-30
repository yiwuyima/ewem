package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.compress.utils.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 产品管理对象 ewem_product
 *
 * @author ewem
 * @date 2021-08-06
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ewem_product", autoResultMap = true)
public class Product extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品编码
     */
    @Excel(name = "产品编码")
    private String code;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String name;

    /**
     * 产品条码
     */
    @Excel(name = "产品条码")
    private String barCode;

    /**
     * 产品分类
     */
    private Long categoryId;

    /**
     * 产品分类
     */
    @TableField(exist = false)
    private Category category;

    /**
     * 动态属性
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<BaseAttrs> attrs;

    /**
     * 富文本描述
     */
    private String content;

    /**
     * 产品图片
     */
    private String img;

    /**
     * 产品视频
     */
    private String video;


    /**
     * 备注
     */
    private String remark;

}
