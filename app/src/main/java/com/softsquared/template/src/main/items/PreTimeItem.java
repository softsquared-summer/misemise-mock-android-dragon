package com.softsquared.template.src.main.items;

public class PreTimeItem {
    String time, state;

    public PreTimeItem(String time, String state) {
        this.time = time;
        this.state = state;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
