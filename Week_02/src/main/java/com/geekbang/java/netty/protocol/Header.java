package com.geekbang.java.netty.protocol;

import java.util.HashMap;
import java.util.Map;

public class Header {
    /**
     * 消息的校验码 三部分：1.0xABEF:固定值 2.主版本号：1~255，1个字节；3.次版本号：1~255,1个字节
     */
    private int crcCode = 0xabef0101;

    /**
     * 消息长度
     */
    private int length;

    /**
     * 会话 ID
     */
    private long sessionId;

    /**
     * 消息类型
     */
    private byte type;

    /**
     * 消息优先级
     */
    private byte priority;

    /**
     * 附件
     */
    private Map<String, Object> attament = new HashMap<>();


    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getPriority() {
        return priority;
    }

    public void setPriority(byte priority) {
        this.priority = priority;
    }

    public Map<String, Object> getAttament() {
        return attament;
    }

    public void setAttament(Map<String, Object> attament) {
        this.attament = attament;
    }


    @Override
    public String toString() {
        return "Header{" +
                "crcCode=" + crcCode +
                ", length=" + length +
                ", sessionId=" + sessionId +
                ", type=" + type +
                ", priority=" + priority +
                ", attament=" + attament +
                '}';
    }
}
