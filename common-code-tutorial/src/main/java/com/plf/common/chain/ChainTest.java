package com.plf.common.chain;

/**
 * @author panlf
 * @date 2022/7/11
 */
public class ChainTest {
    public static void main(String[] args) {
        AddTwoHandler two = new AddTwoHandler("加2");
        DecreaseOneHandler one = new DecreaseOneHandler("减1");
        MultiThreeHandler three = new MultiThreeHandler("乘3");

        two.nextHandler = one;
        one.nextHandler = three;

        Integer result = two.process(5);
        System.out.println(result);
    }
}
