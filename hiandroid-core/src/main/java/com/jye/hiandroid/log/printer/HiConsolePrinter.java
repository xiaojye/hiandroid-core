package com.jye.hiandroid.log.printer;

import android.util.Log;

import androidx.annotation.NonNull;

import com.jye.hiandroid.log.HiLogConfig;

/**
 * 控制台打印
 *
 * @author jye
 * @since 1.0
 */
public class HiConsolePrinter implements HiLogPrinter {
    private static final int MAX_LEN = 512;

    @Override
    public void print(@NonNull HiLogConfig config, int level, String tag, @NonNull String printString) {
        int len = printString.length();
        int countOfSub = len / MAX_LEN;
        if (countOfSub > 0) {
            StringBuilder log = new StringBuilder();
            int index = 0;
            for (int i = 0; i < countOfSub; i++) {
                log.append(printString.substring(index, index + MAX_LEN));
                index += MAX_LEN;
            }
            if (index != len) {
                log.append(printString.substring(index, len));
            }
            Log.println(level, tag, log.toString());
        } else {
            Log.println(level, tag, printString);
        }
    }
}
