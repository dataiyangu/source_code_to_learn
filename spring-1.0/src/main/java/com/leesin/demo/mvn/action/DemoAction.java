package com.leesin.demo.mvn.action;

import com.leesin.demo.service.impl.IDemoService;
import com.leesin.mvcframework.annotation.Autowired;
import com.leesin.mvcframework.annotation.Controller;
import com.leesin.mvcframework.annotation.RequestMapping;
import com.leesin.mvcframework.annotation.RequestParam;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/demo")

public class DemoAction {
    @Autowired
    private IDemoService demoService;
    @RequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp , @RequestParam("name") String name) {
        String result = demoService.get(name);
    }

    @RequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp, @RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a+"+"+b+"="+(a+b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/remove")
    public void remove(HttpServletRequest req,HttpServletResponse resp,
                       @RequestParam("id") Integer id){
    }
}
