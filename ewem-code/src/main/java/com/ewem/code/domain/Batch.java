package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import com.ewem.common.core.domain.BaseEntityPlus;

/**
 * 批次对象 ewem_batch
 *
 * @author ewem
 * @date 2021-08-07
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName("ewem_batch")
public class Batch extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 批号
     */
    private String number;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private Product product;

}
