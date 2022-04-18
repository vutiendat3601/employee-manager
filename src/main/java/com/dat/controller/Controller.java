package com.dat.controller;

import com.dat.console.Request;
import com.dat.console.Response;

public interface Controller {

    default Response service(Request req) {
        String methodName = req.getMethod();
        if (methodName.equalsIgnoreCase("get")) {
            return doGet(req);
        } else if (methodName.equalsIgnoreCase("post")) {
            return doPost(req);
        } else if (methodName.equalsIgnoreCase("put")){
            return doPut(req);
        }
        return null;
    }

    Response doGet(Request req);

    Response doPost(Request req);

    Response doPut(Request req);
}
