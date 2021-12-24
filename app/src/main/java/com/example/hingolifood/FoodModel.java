package com.example.hingolifood;

public class FoodModel {

    String foodName, foodHotel, foodPrice;
    int foodImg;

    public FoodModel(String foodName, String foodHotel, String foodPrice, int foodImg) {
        this.foodName = foodName;
        this.foodHotel = foodHotel;
        this.foodPrice = foodPrice;
        this.foodImg = foodImg;
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

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(int foodImg) {
        this.foodImg = foodImg;
    }
}
