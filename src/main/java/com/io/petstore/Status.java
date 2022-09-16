package com.io.petstore;

public enum Status {
    available("available"),
    pending("pending"),
    sold("sold");

    private String value;

    Status(String value){
        this.value = value;
    }
}
