package com.ewem.code.controller;

import com.ewem.code.service.ITraceService;
import com.ewem.common.core.controller.BaseController;
import com.ewem.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 溯源
 *
 * @author ewem
 * @date 2021-08-14
 */
@RestController
@RequestMapping("/trace")
public class TraceController extends BaseController {
    @Autowired
    private ITraceService traceService;


    /**
     * 查询溯源
     */
    @GetMapping("/{code}")
    public AjaxResult list(@PathVariable("code") String code) {
        return traceService.trace(code);
    }


    /**
     * 防伪查询
     *
     * @param code
     * @return
     */
    @GetMapping("/anti/{code}")
    public AjaxResult antiCheck(@PathVariable("code") String code,
                                @RequestParam("antiCode") String antiCode) {
        return traceService.antiCheck(code, antiCode);
    }

}
