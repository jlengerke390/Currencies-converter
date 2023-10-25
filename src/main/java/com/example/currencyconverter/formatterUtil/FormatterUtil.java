package com.example.currencyconverter.formatterUtil;

import java.text.DecimalFormat;

public class FormatterUtil {
    private static final DecimalFormat FORMATO = new DecimalFormat("#,###.####");
    public static String formatNumber(double number) {
        return FORMATO.format(number);
    }
}
