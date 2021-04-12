package com.jye.hiandroid.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 精准计算工具类
 *
 * @author jye
 * @since 1.0
 */
public class HiDecimalUtils {

    /**
     * 格式化金额（默认保留两位小数）
     *
     * @param amount 金额
     * @return 格式化后的金额字符串
     */
    public static String format(long amount) {
        return format(String.valueOf(amount));
    }

    /**
     * 格式化金额（默认保留两位小数）
     *
     * @param amount 金额
     * @return 格式化后的金额字符串
     */
    public static String format(double amount) {
        return format(String.valueOf(amount));
    }

    /**
     * 格式化金额（默认保留两位小数）
     *
     * @param amount 金额
     * @return 格式化后的金额字符串
     */
    public static String format(String amount) {
        return format(amount, 2);
    }

    /**
     * 格式化金额
     *
     * @param amount 金额
     * @param digit  小数位数
     * @return 格式化后的金额字符串
     */
    public static String format(long amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额
     *
     * @param amount 金额
     * @param digit  小数位数
     * @return 格式化后的金额字符串
     */
    public static String format(double amount, int digit) {
        return format(String.valueOf(amount), digit);
    }

    /**
     * 格式化金额
     *
     * @param amount 金额
     * @param digit  小数位数
     * @return 格式化后的金额字符串
     */
    public static String format(String amount, int digit) {
        StringBuilder pattern = new StringBuilder("0.");
        for (int i = 0; i < digit; i++) {
            pattern.append("0");
        }
        try {
            DecimalFormat df = new DecimalFormat(pattern.toString());
            return df.format(Double.parseDouble(amount));
        } catch (Exception e) {
            e.printStackTrace();
            return pattern.toString();
        }
    }

    /**
     * 分转元 （默认保留两位小数，向下取整/截取模式）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(long amount) {
        return changeF2Y(String.valueOf(amount));
    }

    /**
     * 分转元 （默认保留两位小数，向下取整/截取模式）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(double amount) {
        return changeF2Y(String.valueOf(amount));
    }

    /**
     * 分转元 （默认保留两位小数，向下取整/截取模式）
     *
     * @param amount 分
     * @return 元
     */
    public static String changeF2Y(String amount) {
        return changeF2Y(amount, 2);
    }

    /**
     * 分转元
     *
     * @param amount 分
     * @param digit  小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(long amount, int digit) {
        return changeF2Y(String.valueOf(amount), digit);
    }

    /**
     * 分转元
     *
     * @param amount 分
     * @param digit  小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(double amount, int digit) {
        return changeF2Y(String.valueOf(amount), digit);
    }

    /**
     * 分转元
     *
     * @param amount 分
     * @param digit  小数位数（默认向下取整/截取模式）
     * @return 元
     */
    public static String changeF2Y(String amount, int digit) {
        return changeF2Y(amount, digit, BigDecimal.ROUND_DOWN);
    }

    /**
     * 分转元
     *
     * @param amount       分
     * @param digit        小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(long amount, int digit, int roundingMode) {
        return changeF2Y(String.valueOf(amount), digit, roundingMode);
    }

    /**
     * 分转元
     *
     * @param amount       分
     * @param digit        小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(double amount, int digit, int roundingMode) {
        return changeF2Y(String.valueOf(amount), digit, roundingMode);
    }

    /**
     * 分转元
     *
     * @param amount       分
     * @param digit        小数位数
     * @param roundingMode 格式化模式
     * @return 元
     */
    public static String changeF2Y(String amount, int digit, int roundingMode) {
        return format(new BigDecimal(amount).divide(new BigDecimal("100"), digit, roundingMode).toPlainString(), digit);
    }

    /**
     * 元转分
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(long amount) {
        return changeY2F(String.valueOf(amount));
    }

    /**
     * 元转分
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(double amount) {
        return changeY2F(String.valueOf(amount));
    }

    /**
     * 元转分
     *
     * @param amount 元
     * @return 分
     */
    public static String changeY2F(String amount) {
        return new BigDecimal(amount).multiply(new BigDecimal("100")).stripTrailingZeros().toPlainString();
    }


    /**
     * 加法
     */
    public static BigDecimal add(long arg1, long arg2) {
        return add(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(double arg1, double arg2) {
        return add(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(String arg1, String arg2) {
        if (!HiCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!HiCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return add(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 加法
     */
    public static BigDecimal add(BigDecimal arg1, BigDecimal arg2) {
        return arg1.add(arg2);
    }

    /**
     * 减法
     */
    public static BigDecimal sub(long arg1, long arg2) {
        return sub(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(double arg1, double arg2) {
        return sub(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(String arg1, String arg2) {
        if (!HiCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!HiCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return sub(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 减法
     */
    public static BigDecimal sub(BigDecimal arg1, BigDecimal arg2) {
        return arg1.subtract(arg2);
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(long arg1, long arg2) {
        return mul(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(double arg1, double arg2) {
        return mul(String.valueOf(arg1), String.valueOf(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(String arg1, String arg2) {
        if (!HiCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!HiCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return mul(new BigDecimal(arg1), new BigDecimal(arg2));
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(BigDecimal arg1, BigDecimal arg2) {
        return arg1.multiply(arg2);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(long arg1, long arg2, int scale) {
        return divide(String.valueOf(arg1), String.valueOf(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(double arg1, double arg2, int scale) {
        return divide(String.valueOf(arg1), String.valueOf(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(String arg1, String arg2, int scale) {
        if (!HiCheckUtils.checkNumber(arg1)) {
            arg1 = "0.0";
        }
        if (!HiCheckUtils.checkNumber(arg2)) {
            arg2 = "0.0";
        }
        return divide(new BigDecimal(arg1), new BigDecimal(arg2), scale);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(BigDecimal arg1, BigDecimal arg2, int scale) {
        return arg1.divide(arg2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 除法
     */
    public static BigDecimal divide(BigDecimal arg1, BigDecimal arg2, int scale, int roundingMode) {
        return arg1.divide(arg2, scale, roundingMode);
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(long val) {
        return stripTrailingZeros(String.valueOf(val));
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(double val) {
        return stripTrailingZeros(String.valueOf(val));
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(String val) {
        //使用正则替换方式去除
        if (val.indexOf(".") > 0) {
            val = val.replaceAll("0+?$", "");// 去掉多余的0
            val = val.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return val;
    }

    /**
     * 去除末尾多余的0【如：1.100=1.1，1.00=1，1.=1】
     *
     * @param val 需要精简的数字
     * @return 精简后的数字字符串
     */
    public static String stripTrailingZeros(BigDecimal val) {
        return val.stripTrailingZeros().toPlainString();
    }
}