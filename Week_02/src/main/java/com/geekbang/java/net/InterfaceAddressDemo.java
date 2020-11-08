package com.geekbang.java.net;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

/**
 * @ClassName InterfaceAddressDemo
 * @Description :{@link java.net.InterfaceAddress} 方法示例
 * @Author 谢文海
 * @Date 2020/11/8 0:30
 * @Version 1.0
 **/
public class InterfaceAddressDemo {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            System.out.println("=================");
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            System.out.println(" getName 获得网络设备名称 = "+networkInterface.getName());
            System.out.println(" getDisplayName 获得网络设备显示名称 = "+networkInterface.getDisplayName());

            List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
            for (int i = 0; i < interfaceAddresses.size(); i++) {
                System.out.println("------------------");
                InterfaceAddress interfaceAddress = interfaceAddresses.get(i);
                System.out.println("地址 = " + interfaceAddress.getAddress());
                System.out.println("广播地址 = " + interfaceAddress.getBroadcast());
                System.out.println(" 网络前缀长度 = " +interfaceAddress.getNetworkPrefixLength());
            }

        }
    }
}
