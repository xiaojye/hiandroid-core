package com.jye.hiandroid.log;

import com.jye.hiandroid.log.format.HiStackTraceFormatter;
import com.jye.hiandroid.log.format.HiThreadFormatter;
import com.jye.hiandroid.log.printer.HiLogPrinter;

/**
 * @author jye
 * @since 1.0
 */
public abstract class HiLogConfig {
    private static final HiThreadFormatter THREAD_FORMATTER = new HiThreadFormatter();
    private static final HiStackTraceFormatter STACK_TRACE_FORMATTER = new HiStackTraceFormatter();

    private HiLogConfig copy;

    public HiLogConfig() {
    }

    public HiLogConfig(HiLogConfig copy) {
        this.copy = copy;
    }

    public boolean enable() {
        if (copy != null) {
            return copy.enable();
        }
        return true;
    }

    public String globalTag() {
        if (copy != null) {
            return copy.globalTag();
        }
        return "HI-LOG";
    }

    public boolean includeThread() {
        if (copy != null) {
            return copy.includeThread();
        }
        return true;
    }

    public int stackTraceDepth() {
        if (copy != null) {
            return copy.stackTraceDepth();
        }
        return 5;
    }

    public static HiThreadFormatter getThreadFormatter() {
        return THREAD_FORMATTER;
    }

    public static HiStackTraceFormatter getStackTraceFormatter() {
        return STACK_TRACE_FORMATTER;
    }

    public HiLogPrinter[] printers() {
        if (copy != null) {
            return copy.printers();
        }
        return null;
    }

    public JsonParser jsonParser() {
        if (copy != null) {
            return copy.jsonParser();
        }
        return null;
    }

    public interface JsonParser {
        String toJson(Object src);
    }
}
