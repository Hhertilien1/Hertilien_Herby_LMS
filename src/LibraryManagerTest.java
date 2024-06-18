import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryManagerTest {
    private LibraryManager libraryManager;
    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        libraryManager = new LibraryManager();
        book1 = new Book(1, "To Kill a Mockingbird", "Harper Lee");
        book2 = new Book(2, "1984", "George Orwell");
    }

    @Test
    public void testAddBook() {
        libraryManager.addBook(book1);
        List<Book> books = libraryManager.listAllBooks();
        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }

    @Test
    public void testRemoveBookById() {
        libraryManager.addBook(book1);
        libraryManager.addBook(book2);
        libraryManager.removeBookById(1);
        List<Book> books = libraryManager.listAllBooks();
        assertEquals(1, books.size());
        assertEquals(book2, books.get(0));
    }

    @Test
    public void testRemoveBookByTitle() {
        libraryManager.addBook(book1);
        libraryManager.addBook(book2);
        libraryManager.removeBookByTitle("To Kill a Mockingbird");
        List<Book> books = libraryManager.listAllBooks();
        assertEquals(1, books.size());
        assertEquals(book2, books.get(0));
    }

    @Test
    public void testCheckOutBook() {
        libraryManager.addBook(book1);
        libraryManager.checkOutBook("To Kill a Mockingbird");
        List<Book> books = libraryManager.listAllBooks();
        assertFalse(books.get(0).isAvailable());
        assertNotNull(books.get(0).getDueDate());
    }

    @Test
    public void testCheckInBook() {
        libraryManager.addBook(book1);
        libraryManager.checkOutBook("To Kill a Mockingbird");
        libraryManager.checkInBook("To Kill a Mockingbird");
        List<Book> books = libraryManager.listAllBooks();
        assertTrue(books.get(0).isAvailable());
        assertNull(books.get(0).getDueDate());
    }
}
