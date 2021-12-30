package com.ewem.code.domain.vo;

import com.ewem.code.domain.Batch;
import com.ewem.code.domain.BatchLink;
import com.ewem.code.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ewem
 * <p>
 * 溯源信息
 */
@Data
public class TraceVo implements Serializable {

    private static final long serialVersionUID = -4545074749710427388L;

    private Integer scanNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date firstScanTime;

    private Batch batch;

    private List<BatchLink> links;

    private Product product;

    private Boolean useAnti;

}
