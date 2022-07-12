package com.plf.boot.strategy.handler;

import org.springframework.stereotype.Component;

/**
 * @author panlf
 * @date 2022/7/12
 */
@Component
public class OneHandler implements StrategyHandler<Integer,Integer> {
    @Override
    public Integer apply(Integer param) {
        System.out.println("one handler is active");
        return param * 3;
    }
}
