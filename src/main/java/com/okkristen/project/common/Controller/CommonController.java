package com.okkristen.project.common.Controller;


import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    // Match everything without a suffix (so not a static resource)
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect(HttpServletRequest request, HttpServletResponse response) {
        // Forward to home page so that route is preserved.
        return "forward:/";
    }

    @RequestMapping(value = "/404", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AjaxResult pageNotFound() {
        return AjaxResult.createErrorResult(MessageCode.PAGE_NOT_FOUND);
    }

}
