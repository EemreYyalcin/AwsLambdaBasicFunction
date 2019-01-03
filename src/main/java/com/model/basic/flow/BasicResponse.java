package com.model.basic.flow;

public class BasicResponse {

    private String value;

    public BasicResponse setValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }
}
