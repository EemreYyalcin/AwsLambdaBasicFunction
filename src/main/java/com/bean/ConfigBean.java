package com.bean;


public class ConfigBean {


    private String dummyValue = this.getClass().getName() + " DUMMY";


    public String getDummyValue() {
        return dummyValue;
    }

    public void setDummyValue(String dummyValue) {
        this.dummyValue = dummyValue;
    }
}
