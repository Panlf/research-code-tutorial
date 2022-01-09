package com.plf.stream.service.impl;

import cn.hutool.core.date.StopWatch;
import com.plf.stream.mapper.UserInfoMapper;
import com.plf.stream.pojo.UserInfo;
import com.plf.stream.service.UserInfoService;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author panlf
 * @date 2022/1/8
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final static int BATCH_SIZE = 1000;
    private int size = 0;
    private Set<UserInfo> userInfoSet = new HashSet<>();

    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public void dealUserInfo() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        userInfoMapper.fetchUserInfo("select id,age,name,createtime,price,address from user_info",
                resultContext -> {
                    userInfoSet.add(resultContext.getResultObject());
                    size++;
                    if (size == BATCH_SIZE) {
                        handle();
                    }
                });
        handle();
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }

    @Override
    public void findAllUserInfo() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<UserInfo> list = userInfoMapper.findAllUserInfo();
        System.out.println(list.size());
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeSeconds());
    }


    /**
     * 数据处理
     */
    private void handle(){
        try{
            // 在这里可以对你获取到的批量结果数据进行需要的业务处理
            System.out.println("开始处理数据===>"+ new Date());
            System.out.println(userInfoSet.size());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 处理完每批数据后后将临时清空
            size = 0;
            userInfoSet.clear();
        }
    }

}
