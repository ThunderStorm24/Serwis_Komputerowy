package com.example.projekt_serwis_komputerowy;

public class Uslugi {
    private Integer id;
    private String name;
    private String desc;
    private Integer price;

    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPrice(Integer price){
        this.price = price;
    }
    public Integer getPrice(){
        return price;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

}
