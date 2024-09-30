package app.pdytr.client;

import app.pdytr.BookServiceGrpc;
import app.pdytr.BookServiceProto.AddBookRequest;
import app.pdytr.BookServiceProto.AddBookResponse;
import app.pdytr.BookServiceProto.Book;
import app.pdytr.BookServiceProto.ListBooksRequest;
import app.pdytr.BookServiceProto.ListBooksResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class BookClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        BookServiceGrpc.BookServiceBlockingStub stub = BookServiceGrpc.newBlockingStub(channel);

                Book book = Book.newBuilder()
                        .setId(1)
                        .setTitle("Effective Java")
                        .setAuthor("Joshua Bloch")
                        .build();
                        Book book2 = Book.newBuilder()
                        .setId(2)
                        .setTitle("Clean Code")
                        .setAuthor("Robert C. Martin")
                        .build();

                Book book3 = Book.newBuilder()
                        .setId(3)
                        .setTitle("The Pragmatic Programmer")
                        .setAuthor("Andrew Hunt, David Thomas")
                        .build();

                Book book4 = Book.newBuilder()
                        .setId(4)
                        .setTitle("Head First Java")
                        .setAuthor("Kathy Sierra, Bert Bates")
                        .build();

                AddBookRequest addBookRequest2 = AddBookRequest.newBuilder()
                        .setBook(book2)
                        .build();

                AddBookResponse addBookResponse2 = stub.addBook(addBookRequest2);
                System.out.println("Added book: " + addBookResponse2.getBook());

                AddBookRequest addBookRequest3 = AddBookRequest.newBuilder()
                        .setBook(book3)
                        .build();

                AddBookResponse addBookResponse3 = stub.addBook(addBookRequest3);
                System.out.println("Added book: " + addBookResponse3.getBook());

                AddBookRequest addBookRequest4 = AddBookRequest.newBuilder()
                        .setBook(book4)
                        .build();

                AddBookResponse addBookResponse4 = stub.addBook(addBookRequest4);
                System.out.println("Added book: " + addBookResponse4.getBook());
        AddBookRequest addBookRequest = AddBookRequest.newBuilder()
                .setBook(book)
                .build();

        AddBookResponse addBookResponse = stub.addBook(addBookRequest);
        System.out.println("Added book: " + addBookResponse.getBook());

        ListBooksRequest listBooksRequest = ListBooksRequest.newBuilder().build();
        ListBooksResponse listBooksResponse = stub.listBooks(listBooksRequest);

        System.out.println("List of books:");
        listBooksResponse.getBooksList().forEach(System.out::println);

        channel.shutdown();
    }
}
