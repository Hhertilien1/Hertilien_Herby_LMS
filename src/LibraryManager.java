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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> books;

    public LibraryManager() {
        this.books = new ArrayList<>();
    }

    public void addBookFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                Book book = new Book(id, title, author);
                if (!isBookIdDuplicate(id)) {
                    books.add(book);
                    System.out.println("Book added successfully.");
                } else {
                    System.out.println("Book with ID " + id + " already exists. Skipped.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void removeBookById(int id) {
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed successfully.");
    }

    public void removeBookByTitle(String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
        System.out.println("Book removed successfully.");
    }

    public List<Book> listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the collection.");
            return null;
        }
        System.out.println("Books in the collection:");
        for (Book book : books) {
            System.out.println(book);
        }
        return null;
    }

    public void checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book checked out successfully.");
                return;
            }
        }
        System.out.println("Book not available for checkout.");
    }

    public void checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book checked in successfully.");
                return;
            }
        }
        System.out.println("Book not found or already checked in.");
    }

    private boolean isBookIdDuplicate(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
