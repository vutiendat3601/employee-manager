package com.dat.console;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private Map<String, String> params;
    private String method;

    public Request() {
        params = new HashMap<>();
    }

    public void setMethod(String name) {
        method = name;
    }

    public String getMethod() {
        return method;
    }

    public void addParameter(String name, String value) {
        params.put(name, value);
    }

    public String getParameter(String name) {
        return params.get(name);
    }

    public void clearAllParameter(){
        params.clear();;
    }
}
