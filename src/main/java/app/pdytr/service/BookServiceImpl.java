package app.pdytr.service;

import app.pdytr.BookServiceGrpc;
import app.pdytr.BookServiceProto.AddBookRequest;
import app.pdytr.BookServiceProto.AddBookResponse;
import app.pdytr.BookServiceProto.Book;
import app.pdytr.BookServiceProto.ListBooksRequest;
import app.pdytr.BookServiceProto.ListBooksResponse;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl extends BookServiceGrpc.BookServiceImplBase {
    private final List<Book> bookList = new ArrayList<>();
    private int id = 1;

    @Override
    public void addBook(AddBookRequest request, StreamObserver<AddBookResponse> responseObserver) {
        Book book = request.getBook();
        book = book.toBuilder().setId(id++).build();
        bookList.add(book);
        AddBookResponse response = AddBookResponse.newBuilder().setBook(book).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listBooks(ListBooksRequest request, StreamObserver<ListBooksResponse> responseObserver) {
        ListBooksResponse response = ListBooksResponse.newBuilder().addAllBooks(bookList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
