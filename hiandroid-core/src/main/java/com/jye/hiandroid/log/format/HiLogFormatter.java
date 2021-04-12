package com.jye.hiandroid.log.format;

/**
 * 内容格式化结构
 *
 * @param <T>
 * @author jye
 * @since 1.0
 */
public interface HiLogFormatter<T> {
    String format(T data);
}
