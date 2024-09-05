package com.sample.middleware.utils;


public enum Responses {
    SUCCESSFUL("200"),
    FAILED("400");


    final String val;
    Responses(String value){this.val=value;}

    public String getCode() {
        return val;
    }
}
