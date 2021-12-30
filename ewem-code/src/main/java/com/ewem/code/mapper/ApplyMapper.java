package com.ewem.code.mapper;

import com.ewem.code.domain.Apply;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 码申请Mapper接口
 *
 * @author ewem
 * @date 2021-08-01
 */
public interface ApplyMapper extends BaseMapperPlus<Apply> {

    @Select({"<script>",
            " SELECT * FROM ewem_apply WHERE del_flag = 0 ",
            "<if test=\"null != name and '' != name\">",
            "   AND name like '%${name}%'  ",
            "</if>",
            "<if test=\"null != batchId\">",
            "   AND batch_id = #{batchId}",
            "</if>",
            "<if test=\"null != applyStatus and '' != applyStatus\">",
            "   AND apply_status = #{applyStatus}",
            "</if>",
            "</script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "batchId", column = "batch_id"),
            @Result(property = "batch", column = "batch_id",
                    one = @One(select = "com.ewem.code.mapper.BatchMapper.selectById", fetchType = FetchType.EAGER))})
    List<Apply> selectApplyList(Apply apply);

}
