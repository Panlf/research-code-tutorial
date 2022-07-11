package com.plf.common.chain;

/**
 * @author panlf
 * @date 2022/7/11
 */
public class DecreaseOneHandler extends AbstractHandler<Integer>{
    public DecreaseOneHandler(String name) {
        super(name);
    }

    @Override
    public Integer process(Integer t) {
        t = t - 1;
        if(hasNextHandler()) {
            return nextHandler.process(t);
        }
        return t;
    }
}
