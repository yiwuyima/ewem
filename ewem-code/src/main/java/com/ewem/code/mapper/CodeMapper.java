package com.ewem.code.mapper;

import com.ewem.code.domain.Code;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 码管理Mapper接口
 *
 * @author ewem
 * @date 2021-08-14
 */
public interface CodeMapper extends BaseMapperPlus<Code> {
    /**
     * 查询码管理
     *
     * @param id 码管理主键
     * @return 码管理
     */
    public Code selectCodeById(Long id);

    /**
     * 查询码管理列表
     *
     * @param code 码管理
     * @return 码管理集合
     */
    @Select({"<script>",
            " SELECT * FROM ewem_code WHERE del_flag = 0 ",
            "<if test=\"null != batchId\">",
            "   AND batch_id = #{batchId}  ",
            "</if>",
            "<if test=\"null != code and '' != code\">",
            "   AND code = #{code}",
            "</if>",
            "</script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "batchId", column = "batch_id"),
            @Result(property = "batch", column = "batch_id",
                    one = @One(select = "com.ewem.code.mapper.BatchMapper.selectById", fetchType = FetchType.EAGER))})
    public List<Code> selectCodeList(Code code);

    @Select({"<script>",
            " SELECT * FROM ewem_code WHERE del_flag = 0 ",
            "   AND code = #{code}",
            "</script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "batchId", column = "batch_id"),
            @Result(property = "batch", column = "batch_id",
                    one = @One(select = "com.ewem.code.mapper.BatchMapper.selectById", fetchType = FetchType.EAGER))})
    Code selectOneByCode(String code);

    /**
     * 新增码管理
     *
     * @param code 码管理
     * @return 结果
     */
    public int insertCode(Code code);

    /**
     * 修改码管理
     *
     * @param code 码管理
     * @return 结果
     */
    public int updateCode(Code code);

    /**
     * 删除码管理
     *
     * @param id 码管理主键
     * @return 结果
     */
    public int deleteCodeById(Long id);

    /**
     * 批量删除码管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCodeByIds(Long[] ids);
}
