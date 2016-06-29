package com.wangcc.http.test;

import org.junit.Test;

import com.wangcc.util.http.IPUtil;
import com.wangcc.util.http.MACUtil;

public class HttpTest {
	@Test
	public void ip() {
		String hostIp = IPUtil.getHostIp();
		String hostName = IPUtil.getHostName();
		String hostMac = MACUtil.getHostMac();
		String macAddress = MACUtil.getMacAddress();
		System.out.println(hostIp);
		System.out.println(hostName);
		System.out.println(hostMac);
		System.out.println(macAddress);
	}

	@Test
	public void e() {
		System.getProperties().list(System.out);// 得到当前的系统属性。并将属性列表输出到控制台
		System.out.println("************************");
		String encoding = System.getProperty("file.encoding");
		System.out.println("Encoding:" + encoding);
	}
}
