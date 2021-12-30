package com.ewem.code.service.impl;

import com.ewem.common.utils.StringUtils;
import org.springframework.stereotype.Service;
import com.ewem.common.core.mybatisplus.ServicePlusImpl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ewem.code.domain.ScanLog;
import com.ewem.code.mapper.ScanLogMapper;
import com.ewem.code.service.IScanLogService;

import java.util.List;
import java.util.Collection;

/**
 * 扫码记录Service业务层处理
 *
 * @author ewem
 * @date 2021-08-16
 */
@Service
public class ScanLogServiceImpl extends ServicePlusImpl<ScanLogMapper, ScanLog> implements IScanLogService {

    @Override
    public ScanLog queryById(Long id){
        return getById(id);
    }

    @Override
    public List<ScanLog> queryList(ScanLog scanLog) {
        return list(buildQueryWrapper(scanLog));
    }

    private LambdaQueryWrapper<ScanLog> buildQueryWrapper(ScanLog scanLog) {
        //Map<String, Object> params = scanLog.getParams();
        LambdaQueryWrapper<ScanLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(scanLog.getCode()), ScanLog::getCode, scanLog.getCode());
        return lqw;
    }

    @Override
    public Boolean insertBy(ScanLog scanLog) {
        validEntityBeforeSave(scanLog);
        return save(scanLog);
    }

    @Override
    public Boolean updateBy(ScanLog scanLog) {
        validEntityBeforeUpdate(scanLog);
        return updateById(scanLog);
    }

    /**
     * 保存前的数据校验
     *
     * @param scanLog 实体类数据
     */
    private void validEntityBeforeSave(ScanLog scanLog){
    }

    /**
     * 修改前的数据校验
     *
     * @param scanLog 实体类数据
     */
    private void validEntityBeforeUpdate(ScanLog scanLog) {
    }

    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
        }
        return removeByIds(ids);
    }
}
