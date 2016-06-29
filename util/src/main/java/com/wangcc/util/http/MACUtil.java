package com.wangcc.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MACUtil {
	private static Logger logger = LoggerFactory.getLogger(MACUtil.class);

	public static String getMac(InetAddress netAddress) {
		if (netAddress == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		// 获取网卡，获取地址
		try {
			byte[] mac = NetworkInterface.getByInetAddress(netAddress).getHardwareAddress();
			for (int i = 0; i < mac.length; i++) {
				// if (i != 0) {
				// sb.append("-");
				// }
				// 字节转换为整数
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
		} catch (SocketException e) {
			logger.error("获取网卡地址失败:{}", e);
		}
		return sb.toString();
	}

	public static String getHostMac() {
		InetAddress netAddress = IPUtil.getInetAddress();
		String mac = getMac(netAddress);
		return mac.toString().toUpperCase();
	}

	public static String getMacByIP(String ip) {
		InetAddress netAddress = null;
		try {
			netAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
			logger.error("ip地址未找到:{}", e);
		}
		String mac = getMac(netAddress);
		return mac.toString().toUpperCase();
	}

	/**
	 * 按照"XX-XX-XX-XX-XX-XX"格式，获取本机MAC地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getMacAddress() {
		Enumeration<NetworkInterface> ni;
		try {
			ni = NetworkInterface.getNetworkInterfaces();
			while (ni.hasMoreElements()) {
				NetworkInterface netI = ni.nextElement();

				byte[] bytes = netI.getHardwareAddress();
				if (netI.isUp() && netI != null && bytes != null && bytes.length == 6) {
					StringBuffer sb = new StringBuffer();
					for (byte b : bytes) {
						// 与11110000作按位与运算以便读取当前字节高4位
						sb.append(Integer.toHexString((b & 240) >> 4));
						// 与00001111作按位与运算以便读取当前字节低4位
						sb.append(Integer.toHexString(b & 15));
						sb.append("-");
					}
					sb.deleteCharAt(sb.length() - 1);
					// return sb.toString().toUpperCase();
					System.out.println(sb.toString().toUpperCase());
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前操作系统名称. return 操作系统名称 例如:windows xp,linux 等.
	 */
	public static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.未测
	 * 
	 * @return mac地址
	 */
	@Deprecated
	public static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡
																	// 显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
				if (index >= 0) {// 找到了
					mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	/**
	 * 获取widnows网卡的mac地址.暂时无效
	 * 
	 * @return mac地址
	 */
	@Deprecated
	public static String getWindowsMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			// System.setProperty("sun.jnu.encoding", "utf-8");
			process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical
																		// address]
				if (index >= 0) {// 找到了
					index = line.indexOf(":");// 寻找":"的位置
					if (index >= 0) {
						mac = line.substring(index + 1).trim();// 取出mac地址并去除2边空格
					}
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac;
	}

	/**
	 * 获取MAC地址，暂时无效
	 */
	@Deprecated
	public static String getMACAddress() {
		String address = "";
		String os = getOSName();
		// 根据操作系统类型获取MAC地址
		if (os.toLowerCase().startsWith("windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), "gbk"));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					}
				}
				br.close();
				return address.trim();
			} catch (IOException e) {
			}
		} else if (os.startsWith("Linux")) {
			String command = "/bin/sh -c ifconfig -a";
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("HWaddr") > 0) {
						int index = line.indexOf("HWaddr") + "HWaddr".length();
						address = line.substring(index);
						break;
					}
				}
				br.close();
			} catch (IOException e) {
			}
		}
		address = address.trim();
		return address;
	}

	/**
	 * 根据IP地址获取MAC地址,暂时无效
	 */
	@Deprecated
	public static String getMACAddress(String ipAddress) {
		String str = "", strMAC = "", macAddress = "";
		try {
			Process pp = Runtime.getRuntime().exec("nbtstat -a " + ipAddress);
			InputStreamReader ir = new InputStreamReader(pp.getInputStream(), "gbk");
			LineNumberReader input = new LineNumberReader(ir);
			for (int i = 1; i < 100; i++) {
				str = input.readLine();
				System.out.println(str);
				if (str != null) {
					if (str.indexOf("MAC Address") > 1) {
						strMAC = str.substring(str.indexOf("MAC Address") + 14, str.length());
						break;
					}
				}
			}
		} catch (IOException ex) {
			return "Can't Get MAC Address!";
		}
		if (strMAC.length() < 17) {
			return "Error!";
		}
		macAddress = strMAC.substring(0, 2) + ":" + strMAC.substring(3, 5) + ":" + strMAC.substring(6, 8) + ":"
				+ strMAC.substring(9, 11) + ":" + strMAC.substring(12, 14) + ":" + strMAC.substring(15, 17);
		return macAddress;
	}
}
