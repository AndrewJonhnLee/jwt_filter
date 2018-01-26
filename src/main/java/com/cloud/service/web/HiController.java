package com.cloud.service.web;

import com.cloud.service.domain.Hotel;
import com.cloud.service.jwt.JSONResult;
import com.cloud.service.service.SchedualServiceHi;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HiController {

    private static org.slf4j.Logger log = LoggerFactory.getLogger(HiController.class);
    @Autowired
    SchedualServiceHi schedualServiceHi;

//    @Value("${name}")
    private String myName;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public Hotel sayHi(@RequestParam String name){
        return new Hotel(4545L,name);
//        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping("/")
    Map<String, String> index() {
        Map<String,String> map=new HashMap<String,String>();
        map.put("content", "hello freewolf~");
        return map;
    }

    // 路由映射到/users
    @RequestMapping(value = "/users", produces="application/json;charset=UTF-8")
    public String usersList() {

        ArrayList<String> users =  new ArrayList<String>();
        users.add("freewolf");
        users.add("tom");
        users.add("jerry");
        log.info("=========="+users.get(0));
        return JSONResult.fillResultString(0, "", users);
    }

    @RequestMapping(value = "/hello", produces="application/json;charset=UTF-8")
    public String hello() {
        ArrayList<String> users =  new ArrayList<String>();
        users.add("hello");
        return JSONResult.fillResultString(0, "", users);
    }

    @RequestMapping(value = "/world", produces="application/json;charset=UTF-8")
    public String world() {
        ArrayList<String> users =  new ArrayList<String>();
        users.add("world");
//        users.stream().parallel().filter()
        return JSONResult.fillResultString(0, "", users);
    }
//    @RequestMapping(value = "/login", produces="application/json;charset=UTF-8")
//    public String login() {
//        ArrayList<String> users =  new ArrayList<String>(){{ add("login"); }};
//        return JSONResult.fillResultString(0, "", users);
//    }


    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
