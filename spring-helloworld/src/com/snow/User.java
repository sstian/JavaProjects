package com.snow;

public class User {

    private String name;
    private String nick;
    private String addr;

    public void setName(String name) {
        this.name = name;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public void add() {
        System.out.println("User.add()");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", nick='" + nick + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    public void test() {
        System.out.println("name = " + name);
        System.out.println("nick = " + nick);
        System.out.println("addr = " + addr);
    }
}
