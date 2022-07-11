package com.plf.common.chain;

/**
 * 将传入的数字乘3
 * @author panlf
 * @date 2022/7/11
 */
public class MultiThreeHandler extends AbstractHandler<Integer>{
    public MultiThreeHandler(String name) {
        super(name);
    }

    @Override
    public Integer process(Integer t) {
        t = t * 3;
        if(hasNextHandler()) {
            return nextHandler.process(t);
        }
        return t;
    }
}
