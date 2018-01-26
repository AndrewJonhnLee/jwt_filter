package com.cloud.service.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class SessionController {

    @RequestMapping("/set")
    String set(HttpServletRequest req) {
        req.getSession().setAttribute("testKey", "testValue");
        return "设置session:testKey=testValue";
    }
    @RequestMapping("/query")
    String query(HttpServletRequest req) {
        Object value = req.getSession().getAttribute("testKey");
        return "查询Session：\"testKey\"=" + value+"\nsessionid:"+req.getSession().getId();
    }

}
