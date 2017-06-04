package com.mobewash.mobewash.models;

/**
 * Created by sidney on 6/3/17.
 */

public class SlotTime {
    private int count;
    private int max;
    private int time;
    public SlotTime(int time, int count, int max) {
        this.count = count;
        this.time = time;
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public int getMax() {
        return max;
    }

    public int getTime() {
        return time;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
