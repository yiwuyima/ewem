package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批次环节记录对象 ewem_batch_link
 *
 * @author ewem
 * @date 2021-08-01
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "ewem_batch_link", autoResultMap = true)
public class BatchLink extends BaseEntityPlus implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 批次ID
     */
    private Long batchId;

    @TableField(exist = false)
    private Batch batch;

    /**
     * 环节ID
     */
    private Long linkId;

    @TableField(exist = false)
    private Link link;

    /**
     * 动态参数
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<BaseAttrs> attrs;

    /**
     * 备注
     */
    private String remark;

}
