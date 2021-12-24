package com.example.hingolifood.models;

public class OrderHistryModel {

    String username;
    String foodName;
    String foodHotel;
    String location;
    String quantity;
    String totalprice;
    String phone;

    public OrderHistryModel(String username, String foodName, String foodHotel, String location, String quantity, String totalprice, String phone) {
        this.username = username;
        this.foodName = foodName;
        this.foodHotel = foodHotel;
        this.location = location;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.phone = phone;
    }

    public OrderHistryModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodHotel() {
        return foodHotel;
    }

    public void setFoodHotel(String foodHotel) {
        this.foodHotel = foodHotel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
