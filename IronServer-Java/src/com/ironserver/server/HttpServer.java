package com.ironserver.server;

// Ye class humara server represent karti hai
// Abhi ye sirf basic structure hai (networking nahi hai abhi)

public class HttpServer {

    // Ye variable store karega ki server kis port pe chalega
    private int port;

    // Ye constructor hai
    // Jab hum new HttpServer(8080) karte hain
    // Tab ye automatically call hota hai
    public HttpServer(int port) {

        // this.port ka matlab hai class ka variable
        // port (right side wala) constructor ka parameter hai
        this.port = port;
    }

    // Ye method server start karega
    // Abhi sirf print karega (real server baad me banayenge)
    public void start() {

        // Yaha hum console me print kar rahe hain
        System.out.println("Server starting on port " + port);
    }
}
