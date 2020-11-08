package com.geekbang.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @ClassName InetAddressDemo
 * @Description: {@link java.net.InetAddress} 方法示例
 * @Author 谢文海
 * @Date 2020/11/7 23:16
 * @Version 1.0
 **/
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        // getLocalHost();
        // getByAddress();
        canonicalHostName();
    }

    private static void canonicalHostName() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("www.ibm.com");
        System.out.println(inetAddress.getCanonicalHostName());
        System.out.println(inetAddress.getHostName());
    }

    private static void getByAddress() throws UnknownHostException {
        byte[] addrArr = {-64, -88, 31, -68};
        InetAddress inetAddress = InetAddress.getByAddress(addrArr);
        System.out.println("HostName = " + inetAddress.getHostName());
        System.out.println("CanonicalHostName = " + inetAddress.getCanonicalHostName());
        System.out.println("HostAddress = " + inetAddress.getHostAddress());
        System.out.println("Address = " + Arrays.toString(inetAddress.getAddress()));

    }

    private static void getLocalHost() throws UnknownHostException {
        // 获得本地 IP 地址 (若多个返回第一个)
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println("HostName = " + localHost.getHostName());
        System.out.println("CanonicalHostName = " + localHost.getCanonicalHostName());
        System.out.println("HostAddress = " + localHost.getHostAddress());
        System.out.println("Address = " + Arrays.toString(localHost.getAddress()));
    }
}
