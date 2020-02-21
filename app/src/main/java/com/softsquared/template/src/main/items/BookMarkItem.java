package com.softsquared.template.src.main.items;

public class BookMarkItem {
    private String Location, Status;

    public BookMarkItem(String location, String status) {
        Location = location;
        Status = status;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
