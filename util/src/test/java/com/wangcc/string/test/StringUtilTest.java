package com.wangcc.string.test;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.wangcc.util.string.StringUtil;

public class StringUtilTest {
	@Test
	public void empty() {
		String str = " ";
		boolean empty = StringUtils.isEmpty(str);
		System.out.println(empty);
		boolean empty2 = str.isEmpty();
		System.out.println(empty2);

	}

	@Test
	public void blank() {
		boolean empty = StringUtils.isBlank("\t");
		System.out.println(empty);
	}

	@Test
	public void strip() {
		String strip = StringUtils.strip("fasdaffadsafdsadsfa", "fa");
		System.out.println(strip);
	}

	@Test
	public void split() {
		String contrivedExampleString = "one.two.three.four";
		String[] result = contrivedExampleString.split("\\.");
		System.out.println(result.length); // 0
		String[] split = StringUtil.split(contrivedExampleString, ".");
		System.out.println(split.length);
	}

	@Test
	public void test() {
		String format = String.format("%tc", new Date());
		System.out.println(format);
		String str = null;
		str = String.format("Hi, %s", "林计钦"); // 格式化字符串
		System.out.println(str); // 输出字符串变量str的内容
		System.out.printf("3>7的结果是：%b %n", 3 > 7);
		System.out.printf("100的一半是：%d %n", 100 / 2);
		System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
		System.out.printf("上面的折扣是%d%% %n", 85);
	}

}
