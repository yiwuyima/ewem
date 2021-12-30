package com.ewem.code.service;

import com.ewem.code.domain.Code;
import com.ewem.common.core.mybatisplus.IServicePlus;

import java.util.List;

/**
 * 码管理Service接口
 *
 * @author ewem
 * @date 2021-08-14
 */
public interface ICodeService extends IServicePlus<Code> {
    /**
     * 查询码管理
     *
     * @param id 码管理主键
     * @return 码管理
     */
    public Code selectCodeById(Long id);

    /**
     * 查询码
     *
     * @param code 码
     * @return 码管理
     */
    Code selectCodeByCode(String code);

    /**
     * 查询码管理列表
     *
     * @param code 码管理
     * @return 码管理集合
     */
    public List<Code> selectCodeList(Code code);
    /**
     * 导出码管理列表
     *
     * @param code 码管理
     * @return 码管理集合
     */
    public List<Code> export(Code code);

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
     * 批量删除码管理
     *
     * @param ids 需要删除的码管理主键集合
     * @return 结果
     */
    public int deleteCodeByIds(Long[] ids);

    /**
     * 删除码管理信息
     *
     * @param id 码管理主键
     * @return 结果
     */
    public int deleteCodeById(Long id);

}
