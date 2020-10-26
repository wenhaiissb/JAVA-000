package com.geekbang.java.classloader;


import java.io.*;

/**
 * 自定义类加载器
 *
 * @author wenhai
 * @date 2020/10/21
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) {
        String filePath = System.getProperty("user.dir") + "/Week_01/src/main/Resources/META-INF/Hello.xlass";
        try (InputStream in = new FileInputStream(filePath)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            byte[] bytes = out.toByteArray();
            this.decode(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
    }
}
