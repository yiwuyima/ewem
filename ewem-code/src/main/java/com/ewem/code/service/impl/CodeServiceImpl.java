package com.ewem.code.service.impl;

import com.ewem.code.domain.Code;
import com.ewem.code.mapper.CodeMapper;
import com.ewem.code.service.ICodeService;
import com.ewem.common.config.EwemConfig;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.ewem.common.exception.CustomException;
import com.ewem.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 码管理Service业务层处理
 *
 * @author ewem
 * @date 2021-08-14
 */
@Service
public class CodeServiceImpl extends ServicePlusImpl<CodeMapper, Code> implements ICodeService {

    @Autowired
    CodeMapper codeMapper;

    /**
     * 查询码管理
     *
     * @param id 码管理主键
     * @return 码管理
     */
    @Override
    public Code selectCodeById(Long id) {
        return codeMapper.selectCodeById(id);
    }

    @Override
    public Code selectCodeByCode(String code) {
        return codeMapper.selectOneByCode(code);
    }

    /**
     * 查询码管理列表
     *
     * @param code 码管理
     * @return 码管理
     */
    @Override
    public List<Code> selectCodeList(Code code) {
        return codeMapper.selectCodeList(code);
    }

    @Override
    public List<Code> export(Code code) {
        if (StringUtils.isEmpty(EwemConfig.getQrCodeDomain())) {
            throw new CustomException("未配置码域名");
        }
        List<Code> codeList = this.selectCodeList(code);
        if (CollectionUtils.isEmpty(codeList)) {
            return null;
        }
        codeList.forEach(c -> c.setCode(StringUtils.format("{}c?c={}", EwemConfig.getQrCodeDomain(), c.getCode())));
        return codeList;
    }

    /**
     * 新增码管理
     *
     * @param code 码管理
     * @return 结果
     */
    @Override
    public int insertCode(Code code) {
        code.setCreateTime(new Date());
        return codeMapper.insertCode(code);
    }

    /**
     * 修改码管理
     *
     * @param code 码管理
     * @return 结果
     */
    @Override
    public int updateCode(Code code) {
        code.setUpdateTime(new Date());
        return codeMapper.updateCode(code);
    }

    /**
     * 批量删除码管理
     *
     * @param ids 需要删除的码管理主键
     * @return 结果
     */
    @Override
    public int deleteCodeByIds(Long[] ids) {
        return codeMapper.deleteCodeByIds(ids);
    }

    /**
     * 删除码管理信息
     *
     * @param id 码管理主键
     * @return 结果
     */
    @Override
    public int deleteCodeById(Long id) {
        return codeMapper.deleteCodeById(id);
    }
}
