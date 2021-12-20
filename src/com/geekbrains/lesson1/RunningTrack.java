package com.geekbrains.lesson1;

public class RunningTrack implements Barrier{

    private int length;

    public RunningTrack(int length) {
        this.length = length;
    }

    public int getBarrierValue() {
        return length;
    }
}
