package com.geekbrains.lesson1;

public class Wall implements Barrier {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getBarrierValue() {
        return height;
    }
}
