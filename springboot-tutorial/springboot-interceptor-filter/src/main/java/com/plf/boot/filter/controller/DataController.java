package com.plf.boot.filter.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {


    @GetMapping("getData")
    public String getData(){

       // throw new RuntimeException("Test--");
        return "data test ";
    }

}
