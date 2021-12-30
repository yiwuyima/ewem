package com.ewem.code.mapper;

import com.ewem.code.domain.Product;
import com.ewem.common.core.mybatisplus.BaseMapperPlus;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 产品管理Mapper接口
 *
 * @author ewem
 * @date 2021-08-06
 */
public interface ProductMapper extends BaseMapperPlus<Product> {


    @Select({"<script>",
            " SELECT * FROM ewem_product WHERE del_flag = 0 ",
            "<if test=\"null != code and '' != code\">",
            "   AND code = #{code}  ",
            "</if>",
            "<if test=\"null != name and '' != name\">",
            "   AND name like '%${name}%' ",
            "</if>",
            "<if test=\"null != categoryId\">",
            "   AND category_id = #{categoryId}",
            "</if>",
            "</script>"})
    @Results({@Result(property = "id", column = "id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "category", column = "category_id",
                    one = @One(select = "com.ewem.code.mapper.CategoryMapper.selectById", fetchType = FetchType.EAGER))})
    List<Product> selectProductList(Product product);

    Product selectOneById(Long productId);
}
