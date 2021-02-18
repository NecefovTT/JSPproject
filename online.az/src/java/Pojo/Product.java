/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojo;

/**
 *
 * @author TT
 */
public class Product {

    private int id;
    private String name;
    private String description;
    private String property;
    private String color;
    private String image;
    private double price;
    private String start_date;
    private String end_date;
    private String register;
    private int user_id;
    private int category_id;
    private int brand_id;
    private int statu;

    public Product() {
    }

    public Product(int id, String name, String description, String property, String color, String image, double price, String start_date, String end_date, String register, int user_id, int category_id, int brand_id, int statu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.property = property;
        this.color = color;
        this.image = image;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.register = register;
        this.user_id = user_id;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.statu = statu;
    }

    public Product(int id, String name, String image, double price, int category_id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.category_id = category_id;
    }

    public Product(int id, String name, String image, double price, int brand_id, int bos) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.brand_id = brand_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

}
