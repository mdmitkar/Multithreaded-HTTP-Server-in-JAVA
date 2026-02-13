/*
    ==========================================
            🔥 CLIENT HANDLER (REFACTORED)
    ==========================================

    Ab ye class 3 main kaam karegi:

        1️⃣ Client se network connection handle karegi
        2️⃣ HttpParser ko call karegi request parse karne ke liye
        3️⃣ HttpResponse object bana ke response bhejegi

    Parsing logic yaha nahi hai.
    Response formatting bhi yaha manually nahi hai.

    Clean separation follow kar rahe hain.
*/

package com.ironserver.server;

import com.ironserver.http.HttpParser;
import com.ironserver.http.HttpRequest;
import com.ironserver.http.HttpResponse;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    /*
        Ye socket actual client connection represent karta hai.

        Har client ke liye:
            Ek naya ClientHandler object banega.
    */
    private Socket clientSocket;

    /*
        Constructor:
        Jab object banega tab socket assign hoga.
    */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    /*
        @Override safety check hai.

        Java verify karta hai ki
        hum Runnable ka run() properly override kar rahe hain.
    */
    @Override
    public void run() {

        /*
            ==========================================
                    🔥 DRY RUN START
            ==========================================

            Step 1:
                Thread start hota hai.

            Step 2:
                Thread automatically run() call karta hai.
        */

        try {

            /*
                ==========================================
                        STEP 1: STREAM SETUP
                ==========================================

                InputStream → client se data read karne ke liye
                OutputStream → client ko data bhejne ke liye
            */

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            OutputStream outputStream = clientSocket.getOutputStream();

            /*
                ==========================================
                        STEP 2: REQUEST PARSE
                ==========================================

                HttpParser.parse() raw HTTP ko
                structured HttpRequest object me convert karta hai.
            */

            HttpRequest request = HttpParser.parse(reader);

            /*
                Agar request null hai,
                matlab client ne kuch valid send nahi kiya.
            */
            if (request == null) {
                clientSocket.close();
                return;
            }

            /*
                Debug ke liye console print kar rahe hain.
            */
            System.out.println("=================================");
            System.out.println("New Request Received");
            System.out.println("Method  : " + request.getMethod());
            System.out.println("Path    : " + request.getPath());
            System.out.println("Version : " + request.getVersion());
            System.out.println("Headers : " + request.getHeaders());
            System.out.println("Body    : " + request.getBody());
            System.out.println("=================================");

            /*
                ==========================================
                        STEP 3: RESPONSE OBJECT CREATE
                ==========================================

                Ab manually string nahi banayenge.

                HttpResponse object banayenge.
            */

            HttpResponse response = new HttpResponse(200, "OK");

            /*
                Body define kar rahe hain.
            */
            String body = "Hello from IronServer 🚀";

            /*
                Headers add kar rahe hain.
            */
            response.addHeader("Content-Type", "text/plain");
            response.addHeader("Content-Length", String.valueOf(body.length()));

            /*
                Body set kar rahe hain.
            */
            response.setBody(body);

            /*
                buildResponse() pura formatted HTTP string bana deta hai.
            */
            String finalResponse = response.buildResponse();

            /*
                getBytes() string ko bytes me convert karta hai.
            */
            outputStream.write(finalResponse.getBytes());

            /*
                flush() ensure karta hai
                ki data turant client tak pahunch jaye.
            */
            outputStream.flush();

            /*
                Connection close kar rahe hain.
            */
            clientSocket.close();

        } catch (Exception e) {

            /*
                Exception cases:

                ❌ Client disconnect
                ❌ Parsing issue
                ❌ IO error
            */
            e.printStackTrace();
        }

        /*
            ==========================================
                    🔥 FINAL FLOW SUMMARY
            ==========================================

            Client connect
                ↓
            Thread start
                ↓
            run()
                ↓
            Stream setup
                ↓
            HttpParser.parse()
                ↓
            HttpRequest object
                ↓
            HttpResponse object create
                ↓
            buildResponse()
                ↓
            write()
                ↓
            flush()
                ↓
            close()
        */
    }
}
