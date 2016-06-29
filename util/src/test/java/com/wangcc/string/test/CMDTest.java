package com.wangcc.string.test;

import java.io.IOException;

import org.junit.Test;

public class CMDTest {
	@Test
	public void test() throws IOException {
		try {
			Runtime rt = Runtime.getRuntime();
			Process exec = rt.exec("cmd.exe /c ipconfig /all");
			System.out.println(exec);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
