package com.geekbang.java.netty;

/**
 * 使用 Netty 实现 阻塞 I/0 示例
 *
 * @author wenhai
 * @date   2020/10/27
 */
public class NettyOioServerDemo {
    public static void main(String[] args) throws Exception {
        new NettyOioServer().server(8080);
    }
}
