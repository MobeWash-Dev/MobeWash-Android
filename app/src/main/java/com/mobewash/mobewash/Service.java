package com.mobewash.mobewash;

/**
 * Created by Luyin on 05/27/17.
 */

public class Service {
    private String title = "";
    private String description = "";
    private String detail = "";

    public Service() {
    }

    public Service(String title, String description, String detail) {
        this.title = title;
        this.description = description;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
