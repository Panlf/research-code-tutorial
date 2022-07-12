package com.plf.boot.strategy.handler;

/**
 * Handler 是接口，负责实现每个节点的业务逻辑
 * @param <T>
 * @param <R>
 */
public interface StrategyHandler<T, R> {

    @SuppressWarnings("rawtypes")
    StrategyHandler DEFAULT = t -> null;

    /**
     * apply strategy
     * @param param
     * @return
     */
    R apply(T param);
}
