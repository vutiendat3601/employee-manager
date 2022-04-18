package com.dat.console;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private Map<String, Object> attributes;

    public Response() {
        attributes = new HashMap<>();
    }

    public void setAttribute(String name, Object object) {
        attributes.put(name, object);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }
}