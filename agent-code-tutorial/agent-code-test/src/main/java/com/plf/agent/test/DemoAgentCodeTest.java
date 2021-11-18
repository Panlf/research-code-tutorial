package com.plf.agent.test;

/**
 * @author panlf
 * @date 2021/11/10
 */
public class DemoAgentCodeTest {
    public static void main(String[] args) {
        DemoAgentCodeTest test = new DemoAgentCodeTest();
        test.run("Alice");
    }

    public void run(String name){
        System.out.println(name+" am running!");
    }

}
