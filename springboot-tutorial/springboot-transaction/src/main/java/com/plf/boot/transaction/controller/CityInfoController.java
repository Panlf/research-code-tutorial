package com.plf.boot.transaction.controller;

import com.plf.boot.transaction.bean.CityInfo;
import com.plf.boot.transaction.service.CityInfoManualService;
import com.plf.boot.transaction.service.CityInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CityInfoController {
    @Resource
    private CityInfoManualService cityInfoManualService;

    @Resource
    private CityInfoService cityInfoService;

    @GetMapping("insert")
    public Boolean insert(CityInfo cityInfo){
        try{
            return cityInfoManualService.insert(cityInfo);
        }catch (Exception e){
            return false;
        }
    }

    @GetMapping("insertAuto")
    public Boolean insertAutoTransaction(CityInfo cityInfo){
        try{
            return cityInfoService.insert(cityInfo);
        }catch (Exception e){
            return false;
        }
    }
}
