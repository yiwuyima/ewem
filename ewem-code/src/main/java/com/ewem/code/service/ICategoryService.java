package com.ewem.code.service;

import com.ewem.code.domain.Category;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 产品分类Service接口
 *
 * @author ewem
 * @date 2021-08-06
 */
public interface ICategoryService extends IServicePlus<Category> {

	/**
	 * 查询单个
	 * @return
	 */
	Category queryById(Long id);


	/**
	 * 查询列表
	 */
	List<Category> queryList(Category category);

	/**
	 * 根据新增业务对象插入产品分类
	 * @param category 产品分类新增业务对象
	 * @return
	 */
	Boolean insertBy(Category category);

	/**
	 * 根据编辑业务对象修改产品分类
	 * @param category 产品分类编辑业务对象
	 * @return
	 */
	Boolean updateBy(Category category);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
