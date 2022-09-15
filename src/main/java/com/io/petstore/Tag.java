package com.io.petstore;

public class Tag {
    private Integer id;
    private String name;

    public Tag(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Tag(){}


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
