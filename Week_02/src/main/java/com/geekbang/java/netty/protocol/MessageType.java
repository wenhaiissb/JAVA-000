package com.geekbang.java.netty.protocol;

public enum MessageType {
    LOGIN_REQ((byte) 0), LOGIN_RESP((byte) 1);
    private byte  value;
    MessageType(byte value) {
        this.value = value;
    }

    public byte value() {
        return value;
    }
}
