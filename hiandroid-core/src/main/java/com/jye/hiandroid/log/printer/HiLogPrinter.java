package com.jye.hiandroid.log.printer;

import androidx.annotation.NonNull;

import com.jye.hiandroid.log.HiLogConfig;

/**
 * 日志打印接口
 *
 * @author jye
 * @since 1.0
 */
public interface HiLogPrinter {

    void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString);
}
