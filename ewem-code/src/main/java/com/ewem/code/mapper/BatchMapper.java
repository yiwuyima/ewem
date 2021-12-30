package com.ewem.code.mapper;

import com.ewem.code.domain.Batch;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 批次Mapper接口
 *
 * @author ewem
 * @date 2021-08-07
 */
public interface BatchMapper extends BaseMapperPlus<Batch> {


    @Select({"<script>",
            " SELECT * FROM ewem_batch WHERE del_flag = 0 ",
            "<if test=\"null != number and '' != number\">",
            "   AND number like '%${number}%'  ",
            "</if>",
            "<if test=\"null != productId\">",
            "   AND product_id = #{productId}",
            "</if>",
            "<if test=\"null != status and '' != status\">",
            "   AND status = #{status}",
            "</if>",
            "</script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "product", column = "product_id",
                    one = @One(select = "com.ewem.code.mapper.ProductMapper.selectById", fetchType = FetchType.EAGER))})
    List<Batch> selectBatchList(Batch batch);

}
