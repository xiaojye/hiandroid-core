package com.jye.hiandroid.log;

import androidx.annotation.NonNull;

import com.jye.hiandroid.log.printer.HiLogPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jye
 * @since 1.0
 */
public class HiLogManager {

    private HiLogConfig config;
    private static HiLogManager instance;
    private List<HiLogPrinter> printers = new ArrayList<>();

    private HiLogManager(HiLogConfig config, HiLogPrinter[] printers) {
        this.config = config;
        this.printers.addAll(Arrays.asList(printers));
    }

    public static HiLogManager getInstance() {
        return instance;
    }

    public static void init(@NonNull HiLogConfig config, HiLogPrinter... printers) {
        instance = new HiLogManager(config, printers);
    }

    public HiLogConfig getConfig() {
        return config;
    }

    public List<HiLogPrinter> getPrinters() {
        return printers;
    }

    public void addPrinter(HiLogPrinter printer) {
        printers.add(printer);
    }

    public void removePrinter(HiLogPrinter printer) {
        if (printers != null) {
            printers.remove(printer);
        }
    }
}