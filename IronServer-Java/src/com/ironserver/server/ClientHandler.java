/*
    ==========================================
            🔥 CLIENT HANDLER FULL GUIDE
    ==========================================

    ❓ DOUBT: Runnable kaha use hota hai?
    👉 Jab hume multithreading karni ho.
    👉 Jab har client ko alag worker dena ho.
    👉 Jab background task chalana ho.

    ❓ Kab use karte hain?
    👉 Jab ek se zyada kaam same time pe chalane ho.
    👉 Jab main thread ko block nahi karna ho.

    Example:
    Server me:
        Har client = alag thread
        Isliye Runnable use kiya.

    -----------------------------------------

    ❓ Interface kya hai?
    👉 Rule book.
    👉 Sirf method ka naam batata hai.
    👉 Code nahi deta.

    Runnable interface ke andar:
        void run();

    Isliye jo bhi Runnable implement karega,
    use run() method likhna padega.

    -----------------------------------------

    ❓ @Override kya hai?
    👉 Safety check.
    👉 Java ko bolta hai ki main interface ka method override kar raha hoon.
*/

package com.ironserver.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    /*
        ❓ Yaha implements Runnable kyun likha?

        👉 Taaki is class ko Thread me pass kar sake.
        👉 Thread jab start hoga to run() call karega.

        Agar Runnable implement nahi karte,
        to Thread ko kaise pata chalega kya run karna hai?
    */

    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {

        /*
            🔥 DRY RUN START

            Step 1:
            Thread start hua.

            Step 2:
            Thread automatically run() method call karega.
        */

        try {

            /*
                ❓ Stream kya hoti hai?

                👉 Data ka pipe.
                👉 InputStream = receive data
                👉 OutputStream = send data
            */

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            OutputStream outputStream = clientSocket.getOutputStream();

            /*
                ❓ readLine() kya karta hai?

                👉 Ek line read karta hai jab tak newline na mile.
            */

            String requestLine = reader.readLine();

            System.out.println("Incoming Request: " + requestLine);

            /*
                ❓ split(" ") kya karta hai?

                👉 String ko space ke basis pe tod deta hai.
            */
            String[] parts = requestLine.split(" ");

            String method = parts[0];
            String path = parts[1];

            System.out.println("Method: " + method);
            System.out.println("Path: " + path);

            /*
                ❓ Response format kyu aisa hai?

                HTTP me structure hota hai:
                Status Line
                Headers
                Blank line
                Body
            */

            String body = "Hello from IronServer 🚀";

            String response =
                    "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: " + body.length() + "\r\n" +
                    "\r\n" +
                    body;

            /*
                ❓ getBytes() kyu?

                Stream bytes bhejti hai.
                Isliye String ko bytes me convert karte hain.
            */
            outputStream.write(response.getBytes());

            /*
                ❓ flush() kab use hota hai?

                Jab hume ensure karna ho ki data turant bheja jaye.
                Nahi to kabhi kabhi buffer me ruk sakta hai.
            */
            outputStream.flush();

            /*
                ❓ close() kyu important hai?

                👉 Resource free karta hai.
                👉 Connection band karta hai.
            */
            clientSocket.close();

        } catch (Exception e) {

            /*
                ❓ Exception kab aayega?

                👉 Client disconnect ho gaya
                👉 Network issue
                👉 Null request
            */
            e.printStackTrace();
        }

        /*
            🔥 FINAL FLOW

            Client connect
                ↓
            Thread start
                ↓
            run() execute
                ↓
            Request read
                ↓
            Method & path extract
                ↓
            Response create
                ↓
            Send via OutputStream
                ↓
            flush()
                ↓
            close()
        */
    }
}

        /*
            🔥 FINAL FLOW SUMMARY

            Thread start
                ↓
            run() method
                ↓
            InputStream read
                ↓
            Request line read
                ↓
            split into method & path
                ↓
            Response string create
                ↓
            write() to output stream
                ↓
            flush()
                ↓
            close socket
        */


    