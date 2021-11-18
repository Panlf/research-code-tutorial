package com.plf.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author panlf
 * @date 2021/11/10
 */
@Slf4j
public class AgentCodeTransformer implements ClassFileTransformer {

    public final String localClassName = "com.plf.agent.test.DemoAgentCodeTest";
    public final String localMethodName = "run";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/",".");
        if(className.equals(localClassName)){
            CtClass ctClass;
            try{
                ctClass = ClassPool.getDefault().get(className);
                CtMethod ctMethod = ctClass.getDeclaredMethod(localMethodName);
                ctMethod.insertBefore(" System.out.println(\"字节码添加成功!\");");
                return ctClass.toBytecode();
            }catch (Exception e){
                log.error("AgentCodeTransformer error : {}",e.getMessage());
            }
        }
        return null;
    }
}
