package com.io.petstore;

public class Category {
    private Integer id;
    private String name;

    public Category (Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Category(){}


    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
