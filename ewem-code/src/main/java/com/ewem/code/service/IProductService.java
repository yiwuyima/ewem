package com.ewem.code.service;

import com.ewem.code.domain.Product;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 产品管理Service接口
 *
 * @author ewem
 * @date 2021-08-06
 */
public interface IProductService extends IServicePlus<Product> {

	/**
	 * 查询单个
	 * @return
	 */
	Product queryById(Long id);


	/**
	 * 查询列表
	 */
	List<Product> queryList(Product product);

	/**
	 * 根据新增业务对象插入产品管理
	 * @param product 产品管理新增业务对象
	 * @return
	 */
	Boolean insertBy(Product product);

	/**
	 * 根据编辑业务对象修改产品管理
	 * @param product 产品管理编辑业务对象
	 * @return
	 */
	Boolean updateBy(Product product);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
