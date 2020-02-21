package com.leesin.demo.service.impl.impl;

import com.leesin.demo.service.impl.IDemoService;
import com.leesin.mvcframework.annotation.Service;

@Service
public class DemoService implements IDemoService {
    public String get(String name) {
        return "my name is " + name;
    }
}
