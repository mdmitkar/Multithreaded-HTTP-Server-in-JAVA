package com.ironserver.http; 
// Ye batata hai ki ye class kis package ke andar belong karti hai.
// Package project ko organize karne ke liye use hota hai.

import java.util.HashMap; 
// HashMap class import kar rahe hain jo key-value data store karta hai.

import java.util.Map;      
// Map ek interface hai jo key-value structure define karta hai.


public class HttpRequest {  
// public → ye class kahin se bhi access ho sakti hai.
// class → blueprint hota hai object banane ke liye.
// HttpRequest → class ka naam (HTTP request represent karegi).



    private String method;  
    // private → direct access allowed nahi hai (Encapsulation).
    // String → text type variable.
    // method → HTTP method store karega (GET, POST etc).


    private String path;    
    // Ye URL ka path store karega.
    // Example: "/login" ya "/home"


    private String version; 
    // Ye HTTP version store karega.
    // Example: "HTTP/1.1"


    private Map<String, String> headers = new HashMap<>();  
    // Map<String, String> → key aur value dono String type honge.
    // headers → HTTP headers store karega.
    // = new HashMap<>() → memory me ek empty HashMap create ho raha hai.
    // Ye automatically object banate hi initialize ho jata hai.


    private String body;    
    // Ye request ka body store karega.
    // Mostly POST request me data hota hai.



    // ================================
    // 🔹 Getter & Setter Methods
    // ================================



    public String getMethod() {  
        // public → bahar se access kar sakte hain.
        // String → return type.
        // getMethod() → method ka naam.
        return method;  
        // Ye current object ka method return karega.
    }



    public void setMethod(String method) {  
        // void → kuch return nahi karega.
        // String method → parameter receive karega.
        this.method = method;  
        // this.method → current object ka variable.
        // method → parameter jo pass hua.
        // Ye assignment karta hai value ko.
    }



    public String getPath() {  
        // Path ko read karne ke liye getter method.
        return path;  
        // Current path value return karega.
    }



    public void setPath(String path) {  
        // Path set karne ke liye method.
        this.path = path;  
        // Parameter value ko object ke variable me store karta hai.
    }



    public String getVersion() {  
        // Version read karne ke liye.
        return version;  
        // Version return karega.
    }



    public void setVersion(String version) {  
        // Version set karne ke liye.
        this.version = version;  
        // Object ka version update karega.
    }



    public Map<String, String> getHeaders() {  
        // Ye pura headers map return karega.
        return headers;  
        // Caller ko pura key-value data mil jayega.
    }



    public void addHeader(String key, String value) {  
        // Ye method ek header add karega.
        headers.put(key, value);  
        // Map ke andar key-value pair insert karega.
        // Agar key same ho to value update ho jayegi.
    }



    public String getBody() {  
        // Body read karne ke liye.
        return body;  
        // Current body return karega.
    }



    public void setBody(String body) {  
        // Body set karne ke liye.
        this.body = body;  
        // Body variable me data store karega.
    }



    /*
    ==========================================================
    🔥 COMPLETE DRY RUN (Line by Line Memory Understanding)
    ==========================================================

    Jab hum likhte hain:

        HttpRequest request = new HttpRequest();

    🔹 Step 1:
        - JVM heap memory me ek object create karega.
        - Sab instance variables initialize honge:

            method  = null
            path    = null
            version = null
            headers = empty HashMap
            body    = null

    🔹 Step 2:
        request.setMethod("GET");

        → this.method = "GET"
        → method variable update ho gaya.

    🔹 Step 3:
        request.setPath("/home");

        → path = "/home"

    🔹 Step 4:
        request.setVersion("HTTP/1.1");

        → version = "HTTP/1.1"

    🔹 Step 5:
        request.addHeader("Host", "localhost");

        → headers map me entry add:
            { "Host" -> "localhost" }

    🔹 Step 6:
        request.setBody("Hello");

        → body = "Hello"

    ==========================================================
    FINAL OBJECT STATE
    ==========================================================

        method  = GET
        path    = /home
        version = HTTP/1.1
        headers = {Host=localhost}
        body    = Hello

    ==========================================================
    IMPORTANT UNDERSTANDING
    ==========================================================

    Ye class:
        ✔ Sirf data store karti hai
        ✔ Processing nahi karti
        ✔ HTTP parser ke saath use hogi
        ✔ Custom server architecture ka base model hai

    Ye ek clean OOP design example hai.
    */
}
