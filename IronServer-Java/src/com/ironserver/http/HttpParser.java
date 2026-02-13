package com.ironserver.http;
// Ye batata hai ki ye class kis package ke andar hai.
// Package project ko logical structure deta hai.

import java.io.BufferedReader;
// BufferedReader use hota hai socket ya input stream se text line-by-line read karne ke liye.

import java.io.IOException;
// IOException handle karne ke liye import kiya.
// Jab input/output me error aata hai tab ye exception throw hota hai.


/*
    ==========================================================
                    🔥 HTTP PARSER CLASS
    ==========================================================

    Ye class raw HTTP data ko parse karegi.

    Browser se jo raw text aata hai:
        GET /home HTTP/1.1
        Host: localhost
        Content-Length: 5

        Hello

    Us raw text ko convert karegi
    ek structured HttpRequest object me.
*/

public class HttpParser {

    // static method → object banaye bina call kar sakte hain
    // parse() → BufferedReader lega aur HttpRequest return karega
    // throws IOException → agar reading me error aaye to exception throw karega
    public static HttpRequest parse(BufferedReader reader) throws IOException {

        // Naya HttpRequest object create kar rahe hain
        // Ye object me parsed data store hoga
        HttpRequest request = new HttpRequest();


        /*
            ==========================================================
                    STEP 1️⃣ : REQUEST LINE PARSE KARNA
            ==========================================================

            Request line format hota hai:

            GET /home HTTP/1.1
        */

        String requestLine = reader.readLine();
        // reader.readLine() → ek line read karega input se
        // Ye first line hogi (method path version)

        if (requestLine == null || requestLine.isEmpty()) {
            // Agar koi data nahi mila
            // ya connection close ho gaya
            // to null return kar denge
            return null;
        }

        // Line ko space ke basis pe split karenge
        String[] parts = requestLine.split(" ");
        // Example:
        // "GET /home HTTP/1.1"
        // parts[0] = GET
        // parts[1] = /home
        // parts[2] = HTTP/1.1

        request.setMethod(parts[0]);
        // HTTP method set ho gaya

        request.setPath(parts[1]);
        // Path set ho gaya

        request.setVersion(parts[2]);
        // Version set ho gaya



        /*
            ==========================================================
                    STEP 2️⃣ : HEADERS PARSE KARNA
            ==========================================================

            Headers tab tak read karenge
            jab tak blank line na mile.

            Blank line ka matlab:
            headers khatam.
        */

        String line;

        // Loop tab tak chalega jab tak:
        // 1) line null na ho
        // 2) line empty na ho
        while ((line = reader.readLine()) != null && !line.isEmpty()) {

            // Header format:
            // Host: localhost

            String[] headerParts = line.split(":", 2);
            // ":" ke basis pe split
            // 2 ka matlab max 2 parts me split karo

            String key = headerParts[0].trim();
            // Header name (extra spaces hata diye trim se)

            String value = headerParts[1].trim();
            // Header value (spaces remove)

            request.addHeader(key, value);
            // Header ko request object me store kar diya
        }



        /*
            ==========================================================
                    STEP 3️⃣ : BODY PARSE KARNA
            ==========================================================

            Body tab hi hoti hai jab:
            Content-Length header present ho.
        */

        String contentLengthHeader = request.getHeaders().get("Content-Length");
        // Headers map se Content-Length value nikal rahe hain

        if (contentLengthHeader != null) {
            // Agar Content-Length exist karta hai

            int contentLength = Integer.parseInt(contentLengthHeader);
            // String ko integer me convert kiya
            // Ye batayega kitne characters read karne hain

            char[] bodyChars = new char[contentLength];
            // Utne size ka char array banaya

            reader.read(bodyChars);
            // Exactly utne characters read kiye input se

            request.setBody(new String(bodyChars));
            // Char array ko String me convert karke body me store kar diya
        }

        // Finally fully parsed request return kar diya
        return request;
    }



    /*
    ==========================================================
    🔥 COMPLETE DRY RUN EXPLANATION
    ==========================================================

    Suppose input aata hai:

        POST /login HTTP/1.1
        Host: localhost
        Content-Length: 5

        Hello

    STEP 1:
        requestLine = "POST /login HTTP/1.1"

        parts:
            parts[0] = POST
            parts[1] = /login
            parts[2] = HTTP/1.1

        Object state:
            method = POST
            path = /login
            version = HTTP/1.1

    STEP 2:
        Header line:
            Host: localhost

        store:
            headers = {Host=localhost}

        Next:
            Content-Length: 5

        store:
            headers = {
                Host=localhost,
                Content-Length=5
            }

        Blank line milte hi loop stop.

    STEP 3:
        Content-Length = 5

        char array size = 5
        reader.read(bodyChars)

        bodyChars = ['H','e','l','l','o']

        Body set:
            body = "Hello"

    ==========================================================
    FINAL OBJECT STATE
    ==========================================================

        method  = POST
        path    = /login
        version = HTTP/1.1
        headers = {Host=localhost, Content-Length=5}
        body    = Hello

    ==========================================================
    IMPORTANT UNDERSTANDING
    ==========================================================

    Ye class:
        ✔ Raw HTTP text ko structured object me convert karti hai
        ✔ Sirf parsing ka kaam karti hai
        ✔ Business logic nahi karti
        ✔ Custom Java web server ka core component hai

    Ye tumhare IronServer ka brain hai 🔥
    */
}
