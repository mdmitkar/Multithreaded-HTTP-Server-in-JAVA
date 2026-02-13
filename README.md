# IronServer-Java

Multithreaded HTTP Server in Java.

## Structure

- `IronServer-Java/src/com/ironserver/Main.java` - Entry point
- `IronServer-Java/src/com/ironserver/server/` - core server logic
- `IronServer-Java/src/com/ironserver/http/` - HTTP protocol handling
- `IronServer-Java/src/com/ironserver/routing/` - Request routing
- `IronServer-Java/src/com/ironserver/storage/` - Data storage


🧠 IronServer – Multithreaded HTTP Server in Java
📌 Problem Statement

Most beginners use frameworks like Spring Boot without understanding how a web server actually works internally.

The problem is:

How does a server listen on a port?

How are HTTP requests parsed?

How does routing work?

How does multithreading handle multiple clients?

How are responses constructed and sent back?

This project aims to build a custom HTTP server from scratch using pure Java to understand:

Sockets

Threading

HTTP protocol basics

Request parsing

Routing mechanism

🎯 Project Goal

To build a Multithreaded HTTP Server in Java (from scratch) without using any external frameworks.

We are building:

A basic HTTP server

Custom request parser

Custom router

Thread pool for handling multiple clients

Structured project architecture

🏗 What We Have Built So Far
✅ 1. Project Structure

Organized into proper packages:

com.ironserver
 ├── http        → Request & Response handling
 ├── routing     → Route mapping logic
 ├── server      → Core server logic
 ├── storage     → Future data storage
 └── Main.java   → Entry point

✅ 2. Entry Point (Main Class)

Starts the server

Passes port number

Calls server.start()

✅ 3. Basic HttpServer Class

Stores port number

Has start() method

Currently prints:

Server starting on port 8080


This confirms:

Package structure works

Compilation works

Execution works

✅ 4. Modular Folder Structure Ready

We have created placeholders for:

HttpParser

HttpRequest

HttpResponse

Router

RouteHandler

ClientHandler

ThreadPool

Architecture is ready for expansion.

🚧 What Is Remaining

Now the real server building begins.

🔥 1. Add Real ServerSocket Logic

Inside HttpServer.start():

Create ServerSocket

Listen on port

Accept client connections

Pass socket to ClientHandler

🔥 2. Implement ClientHandler

Read input stream

Parse HTTP request

Create HttpRequest object

Pass to Router

Send HttpResponse back

🔥 3. Implement HTTP Parsing

Inside HttpParser:

Parse:

Method (GET/POST)

Path

Headers

Body

🔥 4. Implement Routing System

Router should:

Map path → handler

Example:

GET /hello → return "Hello World"

🔥 5. Implement Thread Pool

Instead of:

new Thread() for every client


We will:

Create fixed thread pool

Reuse threads

Improve performance

🔥 6. Return Proper HTTP Response

Instead of plain text:

We must send proper HTTP format:

HTTP/1.1 200 OK
Content-Type: text/plain
Content-Length: 12

Hello World

🛠 Tech Stack

Java (Core)

ServerSocket

Threads

No frameworks

No libraries

▶ How To Run
1️⃣ Compile

From project root:

javac -d out src/com/ironserver/Main.java src/com/ironserver/server/*.java src/com/ironserver/http/*.java src/com/ironserver/routing/*.java

2️⃣ Run
java -cp out com.ironserver.Main

🧠 What We Are Actually Building

We are building a mini version of:

Tomcat

Netty

Jetty

But simplified for learning.

🚀 Future Scope

After completing base server:

Static file serving

Middleware support

JSON support

REST API support

Logging system

Error handling system

Simple database integration

Configuration file support

Mini framework layer

📍 Current Status
Feature	Status
Project structure	✅ Done
Compilation setup	✅ Done
Basic server class	✅ Done
Socket handling	❌ Pending
HTTP parsing	❌ Pending
Routing	❌ Pending
Thread pool logic	❌ Pending
Proper response formatting	❌ Pending
🔥 In One Line

Right now we have built the skeleton of the server.

Now we will build the engine.