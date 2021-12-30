package com.ewem.code.service;

import com.ewem.code.domain.Link;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 环节模板Service接口
 *
 * @author ewem
 * @date 2021-08-01
 */
public interface ILinkService extends IServicePlus<Link> {

	/**
	 * 查询单个
	 * @return
	 */
	Link queryById(Long id);


	/**
	 * 查询列表
	 */
	List<Link> queryList(Link link);

	/**
	 * 根据新增业务对象插入环节模板
	 * @param link 环节模板新增业务对象
	 * @return
	 */
	Boolean insertBy(Link link);

	/**
	 * 根据编辑业务对象修改环节模板
	 * @param link 环节模板编辑业务对象
	 * @return
	 */
	Boolean updateBy(Link link);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
