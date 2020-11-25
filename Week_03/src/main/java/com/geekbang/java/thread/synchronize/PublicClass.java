package com.geekbang.java.thread.synchronize;

/**
 * @ClassName PublicClass
 * @Description
 * @Author 谢文海
 * @Date 2020/11/25 23:24
 * @Version 1.0
 **/
public class PublicClass {
    private String username;
    private String password;


    static class PrivateClass {
        private String age;
        private String address;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
