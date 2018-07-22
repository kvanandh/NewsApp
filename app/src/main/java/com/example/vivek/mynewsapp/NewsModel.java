package com.example.vivek.mynewsapp;

/**
 * Created by Vivek on 20-07-2018.
 */

public class NewsModel {
    String status;
    String total_results;
    String source_name;
    String title;
    String description;
    String url;
    String url_to_image;
    String published_at;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl_to_image() {
        return url_to_image;
    }

    public void setUrl_to_image(String url_to_image) {
        this.url_to_image = url_to_image;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "status='" + status + '\'' +
                ", total_results='" + total_results + '\'' +
                ", source_name='" + source_name + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", url_to_image='" + url_to_image + '\'' +
                ", published_at='" + published_at + '\'' +
                '}';
    }
}
