package com.plf.agent;

import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.Instrumentation;

/**
 * @author panlf
 * @date 2021/11/10
 */
@Slf4j
public class AgentCode {
    public static void main(String[] args) {
        log.info("hello");
    }
    /**
     * 该方法在main方法之前运行，与main方法运行在同一个JVM中
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation){
        log.info("================premain方法执行1================");
        log.info("agentArgs:{}",agentArgs);
        //添加Transformer
        instrumentation.addTransformer(new AgentCodeTransformer());
    }

    /**
     * 如果不存在premain(String agentArgs, Instrumentation instrumentation)
     * 则会执行premain(String agentArgs)
     * @param agentArgs
     */
    public static void premain(String agentArgs){
        log.info("================premain方法执行2================");
        log.info("agentArgs:{}",agentArgs);
    }
}
