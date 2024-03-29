package com.example.project;

public class UserGame {

    String username;
    String timeframe;

    String gametitle;

    String price;

  //constructors

    public UserGame(String username, String timeframe, String gametitle, String price) {
        this.username = username;
        this.timeframe = timeframe;
        this.gametitle = gametitle;
        this.price = price;
    }
   //generate getter methods
    public String getUsername() {
        return username;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public String getGametitle() {
        return gametitle;
    }

    public String getPrice() {
        return price;
    }
}
