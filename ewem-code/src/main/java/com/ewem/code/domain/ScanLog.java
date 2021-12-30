package com.ewem.code.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 扫码记录对象 ewem_scan_log
 *
 * @author ewem
 * @date 2021-08-16
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("ewem_scan_log")
public class ScanLog implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * $column.columnComment
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 码
     */
    private String code;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 维度
     */
    private String latitude;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 扫码时间
     */
    private Date scanTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
