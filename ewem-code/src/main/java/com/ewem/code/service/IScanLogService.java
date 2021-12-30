package com.ewem.code.service;

import com.ewem.code.domain.ScanLog;
import com.ewem.common.core.mybatisplus.IServicePlus;


import java.util.Collection;
import java.util.List;

/**
 * 扫码记录Service接口
 *
 * @author ewem
 * @date 2021-08-16
 */
public interface IScanLogService extends IServicePlus<ScanLog> {

	/**
	 * 查询单个
	 * @return
	 */
	ScanLog queryById(Long id);


	/**
	 * 查询列表
	 */
	List<ScanLog> queryList(ScanLog scanLog);

	/**
	 * 根据新增业务对象插入扫码记录
	 * @param scanLog 扫码记录新增业务对象
	 * @return
	 */
	Boolean insertBy(ScanLog scanLog);

	/**
	 * 根据编辑业务对象修改扫码记录
	 * @param scanLog 扫码记录编辑业务对象
	 * @return
	 */
	Boolean updateBy(ScanLog scanLog);

	/**
	 * 校验并删除数据
	 * @param ids 主键集合
	 * @param isValid 是否校验,true-删除前校验,false-不校验
	 * @return
	 */
	Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
