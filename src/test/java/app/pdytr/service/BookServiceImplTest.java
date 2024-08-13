package app.pdytr.service;

import app.pdytr.BookServiceGrpc;
import app.pdytr.BookServiceProto.*;
import io.grpc.ManagedChannel;
import io.grpc.Server;
import io.grpc.inprocess.InProcessChannelBuilder;
import io.grpc.inprocess.InProcessServerBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceImplTest {
    private static Server inProcessServer;
    private static ManagedChannel inProcessChannel;

    @BeforeAll
    public static void setUp() throws IOException {
        String serverName = InProcessServerBuilder.generateName();
        inProcessServer = InProcessServerBuilder.forName(serverName)
                .addService(new BookServiceImpl())
                .build()
                .start();
        inProcessChannel = InProcessChannelBuilder.forName(serverName)
                .directExecutor()
                .build();
    }

    @AfterAll
    public static void tearDown() {
        inProcessChannel.shutdownNow();
        inProcessServer.shutdownNow();
    }

    @Test
    public void testAddBook() {
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(inProcessChannel);

        Book book = Book.newBuilder()
                .setId(1)
                .setTitle("Effective Java")
                .setAuthor("Joshua Bloch")
                .build();

        AddBookRequest addBookRequest = AddBookRequest.newBuilder()
                .setBook(book)
                .build();

        AddBookResponse addBookResponse = blockingStub.addBook(addBookRequest);

        assertEquals(book, addBookResponse.getBook());
    }

    @Test
    public void testListBooks() {
        BookServiceGrpc.BookServiceBlockingStub blockingStub = BookServiceGrpc.newBlockingStub(inProcessChannel);

        ListBooksRequest listBooksRequest = ListBooksRequest.newBuilder().build();
        ListBooksResponse listBooksResponse = blockingStub.listBooks(listBooksRequest);

        List<Book> books = listBooksResponse.getBooksList();
        assertEquals(1, books.size());
        assertEquals("Effective Java", books.get(0).getTitle());
    }
}
