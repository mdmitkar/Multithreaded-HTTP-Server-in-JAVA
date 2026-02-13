/*
    ==========================================
            🔥 HTTP RESPONSE MODEL
    ==========================================

    Ab tak hum response manually string bana rahe the.

    Ab hum ek proper structure banayenge.

    Ye class response ko represent karegi:
        - Status code
        - Status message
        - Headers
        - Body

    Fir isko string me convert karenge.
*/

package com.ironserver.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {

    /*
        Status code kya hota hai?

        200 = OK
        404 = Not Found
        500 = Server Error
    */
    private int statusCode;

    /*
        Status message human readable hota hai.
        Example:
            OK
            Not Found
    */
    private String statusMessage;

    /*
        Headers store karne ke liye Map use kar rahe hain.
        Map = key-value structure.

        Example:
            Content-Type → text/plain
    */
    private Map<String, String> headers = new HashMap<>();

    /*
        Body actual content hota hai.
    */
    private String body;

    /*
        Constructor bana rahe hain.
        Jab object banega tab status set karenge.
    */
    public HttpResponse(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /*
        Header add karne ka method.
    */
    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    /*
        Body set karne ka method.
    */
    public void setBody(String body) {
        this.body = body;
    }

    /*
        Ab sabse important method.

        Ye pura HTTP formatted string return karega.
    */
    public String buildResponse() {

        /*
            StringBuilder use karte hain
            kyunki multiple strings add karni hain.
            Ye memory efficient hota hai.
        */
        StringBuilder response = new StringBuilder();

        /*
            Step 1: Status line
        */
        response.append("HTTP/1.1 ")
                .append(statusCode)
                .append(" ")
                .append(statusMessage)
                .append("\r\n");

        /*
            Step 2: Headers
        */
        for (Map.Entry<String, String> entry : headers.entrySet()) {

            response.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\r\n");
        }

        /*
            Blank line
        */
        response.append("\r\n");

        /*
            Body add karo agar null nahi hai
        */
        if (body != null) {
            response.append(body);
        }

        return response.toString();
    }
}
