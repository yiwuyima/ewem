package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 码申请对象 ewem_apply
 *
 * @author ewem
 * @date 2021-08-01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("ewem_apply")
public class Apply extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 申请名称
     */
    @Excel(name = "申请名称")
    private String name;

    /**
     * 申请数量
     */
    @Excel(name = "申请数量")
    private Long quantity;

    /**
     * 申请状态
     */
    @Excel(name = "申请状态")
    private String applyStatus;

    /**
     * 批次ID
     */
    @Excel(name = "批次ID")
    private Long batchId;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    @TableField(exist = false)
    private Batch batch;

    /**
     * 生成防伪码
     */
    @TableField(exist = false)
    private Boolean useAnti;

    /**
     * 防伪码长度
     */
    private Integer antiLength;

}
