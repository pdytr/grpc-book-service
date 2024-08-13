# gRPC Book Management Service

This is a simple gRPC service for managing books, including adding and listing books.

## Prerequisites

- Java 17
- Maven

## Setup

1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean install` to build the project

## Running the Server

Run the following command to start the gRPC server:

```
mvn exec:java -Dexec.mainClass=app.pdytr.server.BookServer
```

## Running the Client

Run the following command to start the gRPC client:

```
mvn exec:java -Dexec.mainClass=app.pdytr.client.BookClient
```
