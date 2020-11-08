package com.geekbang.java.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @ClassName NetworkInterfaceDemo
 * @Description {@link java.net.NetworkInterface} 方法示例
 * @Author 谢文海
 * @Date 2020/11/7 22:18
 * @Version 1.0
 **/
public class NetworkInterfaceDemo {

    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(" getName ( ) = " + networkInterface.getName());
            System.out.println(" getDisplayName (获得网络设备显示名称) = " + networkInterface.getDisplayName());
            System.out.println(" isUp (是否已经开启并运行) = " + networkInterface.isUp());
            System.out.println(" getIndex (获得网络接口的索引) = " + networkInterface.getIndex());
            System.out.println(" isLoopback (是否为回调接口) = " + networkInterface.isLoopback());
            System.out.println(" getMTU (获得最大传输单元) = " + networkInterface.getMTU());
            System.out.println(" isPointToPoint (是否是点对点设备) = " + networkInterface.isPointToPoint());

            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress inetAddress = inetAddresses.nextElement();
                System.out.println(" getCanonicalHostName (获得此 IP 地址的完全限定域名) = "+inetAddress.getCanonicalHostName());
                System.out.println(" getHostName (获取此 IP 的主机名) = " + inetAddress.getHostName());
                System.out.println(" getHostAddress (获取此 IP 地址字符串) = " + inetAddress.getHostAddress());
                System.out.println(" getAddress (返回此 InetAddress 对象的原始 IP 地址) = " + Arrays.toString(inetAddress.getAddress()));
            }
            System.out.println("=======================");
        }

    }
}
