package io.zuolizhu.sportswatchapp.Models.API;

public class Details {
    private String name;
    private String slug;
    private String startDate;
    private String endDate;
    private String intervalType;

    public Details() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    @Override
    public String toString() {
        return "Details{" +
                "name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", intervalType='" + intervalType + '\'' +
                '}';
    }
}
