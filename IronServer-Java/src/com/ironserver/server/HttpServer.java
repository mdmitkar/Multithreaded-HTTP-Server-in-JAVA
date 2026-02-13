/*
  

    🧠 SERVER SOCKET KYA HOTA HAI?

    ServerSocket = Ek darwaza (gate) jo kisi port pe khada rehta hai
    Aur clients ka wait karta hai.
    Jaise:
    Ghar ka gate pe guard khada ho.
    Jab koi aata hai, gate kholta hai.

    Yaha:
    Guard = ServerSocket
    Gate number = Port (jaise 8080)


    🧠 SOCKET KYA HOTA HAI?

    Socket = Ek connection pipe
    Jiske through client aur server baat karte hain.

    ServerSocket sirf connection accept karta hai.
    Real baat-cheet Socket object se hoti hai.

    --------------------------------

    🧠 THREAD KYA HOTA HAI?

    Thread = Program ke andar ek worker.

    Agar 1 thread:
        Ek client handle karega
        Baaki sab wait karenge

    Agar multiple threads:
        Har client ka apna worker
        Sab parallel handle honge

    --------------------------------

    🧠 IOEXCEPTION KYA HOTA HAI?

    IO = Input/Output

    Ye error tab aata hai jab:
        ❌ Port already use me ho
        ❌ Network issue ho
        ❌ Client achanak disconnect ho jaye
        ❌ ServerSocket create na ho paaye

    Isliye try-catch lagaya hai.
*/

package com.ironserver.server;

import java.io.IOException;     // IO related errors handle karne ke liye
import java.net.ServerSocket;   // Server banane ke liye
import java.net.Socket;         // Client connection represent karta hai

public class HttpServer {
    // Ye variable port number store karega (jaise 8080)
    private int port;
    // Constructor: Jab object banega tab port set hoga
    public HttpServer(int port) {
        this.port = port;
    }
    public void start() {

        /*
            🔥 DRY RUN START

            Step 1:
            ServerSocket create hoga given port pe.

            Agar port free hai:
                Server start ho jayega

            Agar port busy hai:
                IOException aayega
        */

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started on port " + port);

            /*
                Step 2:
                Infinite loop start hoga.

                Matlab server kabhi band nahi hoga.
                Ye continuously clients ka wait karega.
            */
            while (true) {

                /*
                    Step 3:
                    accept() call hota hai.

                    Ye blocking method hai.

                    Matlab:
                    Yaha program ruk jayega
                    Jab tak koi client connect na kare.

                    Jaise:
                    Guard gate pe khada wait kar raha ho.
                */
                Socket clientSocket = serverSocket.accept();

                /*
                    Step 4:
                    Jaise hi client connect karega:

                    accept() ek Socket return karega.
                    Ye socket actual connection hai.

                    Ab server aur client baat kar sakte hain.
                */

                System.out.println("New client connected: " 
                        + clientSocket.getInetAddress());

                /*
                    Step 5:
                    Ab hum naya Thread bana rahe hain.

                    Kyun?

                    Agar hum thread na banaye:
                        Ek client handle karte waqt
                        doosra client wait karega.

                    Thread banane se:
                        Har client ka alag worker banega.

                    Flow:
                        Client 1 → Thread 1
                        Client 2 → Thread 2
                        Client 3 → Thread 3
                */

                new Thread(new ClientHandler(clientSocket)).start();

                /*
                    Step 6:
                    Thread start hone ke baad

                    Main server fir se loop me chala jayega
                    Aur next client ka wait karega.

                    Ye process chalta rahega.
                */
            }

        } catch (IOException e) {

            /*
                Agar upar kahin bhi network error aaye
                to yaha control aayega.

                e.printStackTrace():
                Ye pura error detail print karta hai
                taaki hume pata chale problem kya hai.
            */

            e.printStackTrace();
        }

        /*
            🔥 FINAL FLOW SUMMARY:

            start()
                ↓
            ServerSocket create
                ↓
            while(true)
                ↓
            accept() (wait)
                ↓
            Client connect
                ↓
            New Thread start
                ↓
            Back to accept()

            Ye cycle kabhi rukta nahi.
        */
    }
}
