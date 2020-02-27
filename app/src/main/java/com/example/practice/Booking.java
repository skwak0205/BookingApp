package com.example.practice;

public class Booking {

    private String id;
    private String date;
    private String startTime;
    private String endTime;
    private String numOfPeople;
    private  String comments;

    public Booking () {}

    public Booking(String id, String date, String startTime, String endTime, String numOfPeople, String comments) {
        this.setId(id);
        this.setDate(date);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setNumOfPeople(numOfPeople);
        this.setComments(comments);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(String numOfPeople) {
        this.numOfPeople = numOfPeople;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
