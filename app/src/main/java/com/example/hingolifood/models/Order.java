package com.example.hingolifood.models;

public class Order {

    String dishName;
    String quantity;
    String time;
    String userId;

    public Order(String dishName, String quantity, String time, String userId) {
        this.dishName = dishName;
        this.quantity = quantity;
        this.time = time;
        this.userId = userId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
