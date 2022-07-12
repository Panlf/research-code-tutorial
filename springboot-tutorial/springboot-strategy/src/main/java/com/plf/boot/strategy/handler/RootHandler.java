package com.plf.boot.strategy.handler;

import com.plf.boot.strategy.config.AbstractStrategyRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author panlf
 * @date 2022/7/12
 */
@Component
public class RootHandler
        extends AbstractStrategyRouter<Integer,Integer> {

    @Autowired
    private OneHandler oneHandler;

    @Autowired
    private TwoHandler twoHandler;

    @Override
    protected StrategyMapper<Integer, Integer> registerStrategyMapper() {
        System.out.println("root handler dispatcher");
        return param -> {
            if(param>10){
                return oneHandler;
            }
            return twoHandler;
        };
    }
}
