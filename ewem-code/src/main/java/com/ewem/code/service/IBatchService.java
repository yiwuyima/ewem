package com.ewem.code.service;

import com.ewem.code.domain.Batch;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 批次Service接口
 *
 * @author ewem
 * @date 2021-08-07
 */
public interface IBatchService extends IServicePlus<Batch> {

	/**
	 * 查询单个
	 * @return
	 */
	Batch queryById(Long id);


	/**
	 * 查询列表
	 */
	List<Batch> queryList(Batch batch);

	/**
	 * 根据新增业务对象插入批次
	 * @param batch 批次新增业务对象
	 * @return
	 */
	Boolean insertBy(Batch batch);

	/**
	 * 根据编辑业务对象修改批次
	 * @param batch 批次编辑业务对象
	 * @return
	 */
	Boolean updateBy(Batch batch);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
