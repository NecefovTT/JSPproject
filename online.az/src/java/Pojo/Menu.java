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
public class Menu {

    private int id;
    private String name;
    private String page;
    private int user_id;
    private int statu;

    public Menu() {
    }

    public Menu(int id, String name, String page, int user_id, int statu) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.user_id = user_id;
        this.statu = statu;
    }

    public Menu(int id, String name, String page) {
        this.id = id;
        this.name = name;
        this.page = page;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
