package com.caphephin.voucher.model;

public class News {

    private String title;
    private String thumbnail;
    private String description;
    private String content;

    public News(String title, String description, String thumbnail, String content) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
