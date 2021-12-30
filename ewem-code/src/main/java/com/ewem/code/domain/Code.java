package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ewem.common.annotation.Excel;
import com.ewem.common.core.domain.BaseEntityPlus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 码管理对象 ewem_code
 *
 * @author ewem
 * @date 2021-08-14
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("ewem_code")
public class Code extends BaseEntityPlus {
    private static final long serialVersionUID = 1L;

    /**
     * 码
     */
    @Excel(name = "码")
    private String code;

    /**
     * 防伪码
     */
    private String antiCode;

    private Long batchId;

    @TableField(exist = false)
    private Batch batch;

    private Integer scanNum;

    private Date firstScanTime;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

}
