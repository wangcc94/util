package com.wangcc.util.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IPUtil {
	private static Logger logger = LoggerFactory.getLogger(IPUtil.class);

	/**
	 * 获取本地网络
	 * 
	 * @return
	 */
	public static InetAddress getInetAddress() {
		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			logger.error("unknown host!");
		}
		return null;
	}

	/**
	 * 获取ip
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getIp(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress(); // get the ip address
		return ip;
	}

	/**
	 * 获取本地ip
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getHostIp() {
		InetAddress netAddress = getInetAddress();
		if (null == netAddress) {
			return null;
		}
		String ip = netAddress.getHostAddress(); // get the ip address
		return ip;
	}

	/**
	 * 获取主机名
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getName(InetAddress netAddress) {
		if (null == netAddress) {
			return null;
		}
		String name = netAddress.getHostName(); // get the host address
		return name;
	}

	/**
	 * 获取本地主机名
	 * 
	 * @param netAddress
	 * @return
	 */
	public static String getHostName() {
		InetAddress netAddress = getInetAddress();
		if (null == netAddress) {
			return null;
		}
		String name = netAddress.getHostName(); // get the host address
		return name;
	}
}
