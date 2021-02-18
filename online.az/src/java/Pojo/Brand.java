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
public class Brand {

    private int id;
    private String name;
    private String description;
    private String register;
    private int user_id;
    private int statu;

    public Brand() {
    }

    public Brand(int id, String name, String description, String register, int user_id, int statu) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.register = register;
        this.user_id = user_id;
        this.statu = statu;
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Brand(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

}
