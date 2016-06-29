package com.wangcc.util.codec;

import java.lang.reflect.Method;

public class Base64 {
	@SuppressWarnings("unused")
	private static final char[] base = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
			'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', '0', '+', '/' };

	/**
	 * Base64解密
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] decode(String str) {
		return org.apache.commons.codec.binary.Base64.decodeBase64(str);
	}

	/**
	 * Base64加密
	 * 
	 * @param b
	 * @return
	 */
	public static String encode(byte[] b) {
		return org.apache.commons.codec.binary.Base64.encodeBase64String(b);
	}

	/***
	 * Base64加密
	 */
	public static String encodeBase64(byte[] input) throws Exception {
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("encode", byte[].class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, new Object[] { input });
		return (String) retObj;
	}

	/***
	 * Base64解密
	 */
	public static byte[] decodeBase64(String input) throws Exception {
		Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
		Method mainMethod = clazz.getMethod("decode", String.class);
		mainMethod.setAccessible(true);
		Object retObj = mainMethod.invoke(null, input);
		return (byte[]) retObj;
	}

	@Deprecated
	public static String myEncode(byte[] input) {
		StringBuilder sb = new StringBuilder();
		int length = input.length;
		int count = length / 3 + length % 3 == 0 ? 0 : 1;
		for (int i = 0; i < count; i++) {
			StringBuilder temp = new StringBuilder();
			for (int j = 0; j < 3; j++) {
				int index = i * 3 + j;
				if (index < length) {
					String binaryString = Integer.toBinaryString(input[index]);
					System.out.println(binaryString);
					temp.append(binaryString);
				}
			}
			System.out.println(temp);
		}
		return sb.toString();
	}
}
