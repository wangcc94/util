package com.wangcc.io.test;

import org.junit.Test;

public class IOTest {
	@Test
	public void temp() {
		System.out.println(System.getProperty("java.io.tmpdir"));// Z:\Windows\TEMP\
		System.out.println(System.getProperty("java.compiler"));// null
		System.out.println(System.getProperty("java.class.path"));// ...
	}
}
