package com.sreMake.common.exception;

/**
 * 业务异常使用这个
 */
public interface BusinessException {
    /**
     * 实现该方法返回业务错误吗
     */
    int code();
}
