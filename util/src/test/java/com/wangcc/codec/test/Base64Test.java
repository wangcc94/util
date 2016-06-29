package com.wangcc.codec.test;

import java.nio.charset.Charset;

import org.junit.Assert;
import org.junit.Test;

import com.wangcc.util.codec.Base64;

public class Base64Test {
	@Test
	public void strToByte() throws Exception {
		System.out.println(Charset.defaultCharset());
		String str = "中文";
		byte[] bytes = str.getBytes("utf-8");
		byte[] bytes2 = str.getBytes("iso-8859-1");
		byte[] bytes3 = str.getBytes();
		String encode3 = Base64.encode(bytes);
		System.out.println(encode3);
		byte[] encode = Base64.decode(encode3);
		System.out.println(new String(encode));
		System.out.println("-----end------");
	}

	@Test
	public void base64Check() throws Exception {
		System.out.println("默认编码:" + Charset.defaultCharset());
		String str = "中文";
		byte[] bytes = str.getBytes();
		String encode = Base64.encode(str.getBytes());
		byte[] decode = Base64.decode(encode);
		Assert.assertArrayEquals("Base64编解码不一致", bytes, decode);
	}

	@Test
	public void base64Chunked() {

		String str = "中文";

		// byte[] enbytes = base64.encode(str.getBytes());
		// byte[] enbytes = base64.encodeBase64(str.getBytes(),true);
		byte[] enbytes = org.apache.commons.codec.binary.Base64.encodeBase64Chunked(str.getBytes());

		// byte[] debytes = base64.decode(new String(enbytes).getBytes());
		// byte[] debytes = base64.decodeBase64(new String(enbytes).getBytes());
		byte[] debytes = org.apache.commons.codec.binary.Base64.decodeBase64(new String(enbytes).getBytes());

		System.out.println("编码前:" + str);
		System.out.println("编码后:" + new String(enbytes));
		System.out.println("解码后:" + new String(debytes));

	}

	@Test
	public void base64Test() {
		String str = "中文";
		Base64.myEncode(str.getBytes());
	}

}
