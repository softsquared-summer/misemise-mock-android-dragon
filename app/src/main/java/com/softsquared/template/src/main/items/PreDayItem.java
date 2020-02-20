package com.softsquared.template.src.main.items;

public class PreDayItem {
    String day, when, state;

    public PreDayItem(String day, String when, String state) {
        this.day = day;
        this.when = when;
        this.state = state;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
