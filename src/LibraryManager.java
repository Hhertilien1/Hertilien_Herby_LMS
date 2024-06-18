/*Herby Heertlien
 * CEN 3024C-33022
 * Prof. Walauskis
 * 5/19/24
 * Softwarre Development One
 *
 * LibraryManager Class:Manages a collection of Book objects.
 * Provides methods to add books from a file, remove books by ID, and list all books.
 * Relation to Overall Program: Acts as the main component for managing the library's book collection.
 * It handles operations related to maintaining the list of books, ensuring no duplicate IDs, and interacting with external data sources (files).
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> books;

    public LibraryManager() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (!isBookIdDuplicate(book.getId())) {
            books.add(book);
        }
    }

    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public List<Book> listAllBooks() {
        return new ArrayList<>(books);
    }

    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                book.setDueDate(LocalDate.now().plusWeeks(2)); // Example due date: 2 weeks from now
                return;
            }
        }
    }

    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                book.setDueDate(null);
                return;
            }
        }
    }

    private boolean isBookIdDuplicate(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void addBookFromFile(String fileName) {
    }
}
