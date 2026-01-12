package com.testcloud.common.exception;


/**
 * 专门用于在 Filter 链路中包装 ScopedValue 无法直接抛出的受检异常
 */
public class UncheckedFilterException extends RuntimeException {
    public UncheckedFilterException(Throwable cause) {
        super(cause);
    }
}