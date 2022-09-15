package com.io.petstore;

public enum Status {
    available("available"),
    sold("sold");

    private String value;

    Status(String value){
        this.value = value;
    }
}
