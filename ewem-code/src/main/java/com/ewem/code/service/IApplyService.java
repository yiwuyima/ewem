package com.ewem.code.service;

import com.ewem.code.domain.Apply;
import com.ewem.common.core.mybatisplus.IServicePlus;
import com.ewem.common.enums.ApplyStatus;


import java.util.Collection;
import java.util.List;

/**
 * 码申请Service接口
 *
 * @author ewem
 * @date 2021-08-01
 */
public interface IApplyService extends IServicePlus<Apply> {

	/**
	 * 查询单个
	 * @return
	 */
	Apply queryById(Long id);


	/**
	 * 查询列表
	 */
	List<Apply> queryList(Apply apply);

	/**
	 * 根据新增业务对象插入码申请
	 * @param apply 码申请新增业务对象
	 * @return
	 */
	Boolean insertBy(Apply apply);

	/**
	 * 根据编辑业务对象修改码申请
	 * @param apply 码申请编辑业务对象
	 * @return
	 */
	Boolean updateBy(Apply apply);

    /**
     * 校验并删除数据
     *
     * @param ids     主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 申请状态查询一条申请记录
     *
     * @param applyStatus
     * @return
     */
    Apply selectOneByApplyStatus(ApplyStatus applyStatus);
}
