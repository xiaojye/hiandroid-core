package com.jye.hiandroid.log.format;

/**
 * 线程信息格式化
 *
 * @author jye
 * @since 1.0
 */
public class HiThreadFormatter implements HiLogFormatter<Thread> {

    @Override
    public String format(Thread data) {
        return "Thread:" + data.getName();
    }
}
