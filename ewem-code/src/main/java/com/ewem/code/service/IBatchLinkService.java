package com.ewem.code.service;

import com.ewem.code.domain.BatchLink;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 批次环节记录Service接口
 *
 * @author ewem
 * @date 2021-08-01
 */
public interface IBatchLinkService extends IServicePlus<BatchLink> {

	/**
	 * 查询单个
	 * @return
	 */
	BatchLink queryById(Long id);


	/**
	 * 查询列表
	 */
	List<BatchLink> queryList(BatchLink batchLink);

	/**
	 * 根据新增业务对象插入批次环节记录
	 * @param batchLink 批次环节记录新增业务对象
	 * @return
	 */
	Boolean insertBy(BatchLink batchLink);

	/**
	 * 根据编辑业务对象修改批次环节记录
	 * @param batchLink 批次环节记录编辑业务对象
	 * @return
	 */
	Boolean updateBy(BatchLink batchLink);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
