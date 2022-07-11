package com.plf.common.chain;

/**
 * 将传入的数字加2
 * @author panlf
 * @date 2022/7/11
 */
public class AddTwoHandler extends AbstractHandler<Integer>{

    public AddTwoHandler(String name) {
        super(name);
    }

    @Override
    public Integer process(Integer t) {
        t = t + 2;
        if(hasNextHandler()) {
            return nextHandler.process(t);
        }
        return t;
    }
}
