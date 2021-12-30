package com.ewem.code.service;

import com.ewem.common.core.domain.AjaxResult;

/**
 * 溯源Service接口
 *
 * @author ewem
 * @date 2021-08-14
 */
public interface ITraceService {
    /**
     * 查询溯源
     *
     * @param code 码
     * @return 溯源信息
     */
    AjaxResult trace(String code);


    /**
     * 防伪校验
     * @param code
     * @param antiCode 防伪码
     * @return
     */
    AjaxResult antiCheck(String code, String antiCode);


}
