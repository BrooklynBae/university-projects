package org.example;

import com.fastcgi.FCGIInterface;

public class Main {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            long startTime = System.nanoTime();
            try {
                RequestHandler.handleRequest(startTime);
            } catch (Exception e) {
                RequestHandler.handleError(e);
            }
        }
    }
}