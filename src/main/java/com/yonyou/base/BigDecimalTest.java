package com.yonyou.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalTest {
	public static void main(String[] arg) {
		BigDecimal bd = new BigDecimal(1.01);
		System.out.println(parseNumber(",###,###", bd)); // out: 123,456,789
		System.out.println(parseNumber(",###,###.00", bd)); // out: 123,456,789.30
		System.out.println(parseNumber(",###,###.000000", bd)); // out: 123,456,789.300000
	}
	public static String parseNumber(String pattern, BigDecimal bd) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(bd);
	}
}
