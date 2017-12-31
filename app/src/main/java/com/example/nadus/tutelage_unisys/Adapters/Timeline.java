package com.example.nadus.tutelage_unisys.Adapters;

/**
 * Created by HP on 12/21/2017.
 */

public class Timeline {
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    int duration;
    public Timeline(int count)
    {
        this.duration=count;
    }

}
