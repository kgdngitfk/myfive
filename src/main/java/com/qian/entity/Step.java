package com.qian.entity;

/**
 * Created by wuhuaiqian on 17-3-12.
 * 落字走法
 */
public class Step {
    private int x;
    private int y;
    private int who;

    public Step(int x, int y, int who) {
        this.x = x;
        this.y = y;
        this.who = who;
    }

    public Step() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }
}
