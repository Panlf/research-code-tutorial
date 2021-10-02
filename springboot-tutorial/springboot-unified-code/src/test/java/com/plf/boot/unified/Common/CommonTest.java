package com.plf.boot.unified.Common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author panlf
 * @date 2021/10/3
 */
public class CommonTest {

    @Test
    @DisplayName("测试断言equals")
    void testEquals() {
        assertTrue(3 < 4);
    }

    @Test
    @DisplayName("测试断言NotNull")
    void testNotNull() {
        assertNotNull(new Object());
    }

    @Test
    @DisplayName("测试断言抛异常")
    void testThrows() {
        ArithmeticException ardithExcel = assertThrows(ArithmeticException.class, () -> {
            int m = 5/0;
        });
        assertEquals("/ by zero", ardithExcel.getMessage());
    }


    @Test
    @DisplayName("测试断言超时")
    void testTimeOut() {
        String actualResult = assertTimeout(ofSeconds(2), () -> {
            Thread.sleep(1000);
            return "a result";
        });
        System.out.println(actualResult);
    }


    @Test
    @DisplayName("测试组合断言")
    void testAll() {
        assertAll("测试item商品下单",
                () -> {
                    //模拟用户余额扣减
                    assertTrue(1 < 2, "余额不足");
                },
                () -> {
                    //模拟item数据库扣减库存
                    assertTrue(3 < 4);
                },
                () -> {
                    //模拟交易流水落库
                    assertNotNull(new Object());
                }
        );
    }


    @RepeatedTest(3)
    @DisplayName("重复测试")
    void repeatedTest() {
        System.out.println("调用");
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("参数化测试")
    void paramTest(int a) {
        assertTrue(a > 0 && a < 4);
    }
}
