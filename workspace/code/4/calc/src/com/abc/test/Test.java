package com.abc.test;

import com.abc.calc.Calc; //导入完整的类名定义

public class Test {
	public static void main(String[] args) {
		Calc c = new Calc(); // 去掉包名冗余了

		System.out.println("5 + 6 = " + c.add(5, 6));
		System.out.println("11 - 5 = " + c.sub(11, 5));
	}
}
