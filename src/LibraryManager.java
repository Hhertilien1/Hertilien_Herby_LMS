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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LibraryManager {
    private List<Book> books;

    // Constructor
    public LibraryManager() {
        books = new ArrayList<>();
        Random random = new Random();
    }

    // Method to add books from a file
    public boolean addBookFromFile(String filePath) {
        boolean addedAtLeastOneBook = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookInfo = line.split(",");
                if (bookInfo.length >= 2) {
                    String title = bookInfo[0].trim();
                    String author = bookInfo[1].trim();
                    Book book = new Book(title, author);
                    books.add(book);
                    addedAtLeastOneBook = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return addedAtLeastOneBook;
    }

    // Method to remove a book by its barcode
    public boolean removeBookByBarcode(int barcode) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBarcode() == barcode) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Method to remove a book by its title
    public boolean removeBookByTitle(String title) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getTitle().equalsIgnoreCase(title)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Method to remove a book by its ID
    public boolean removeBookById(int bookId) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookId() == bookId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    // Method to check out a book by its title
    public boolean checkOutBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    book.setDueDate(LocalDate.now().plusDays(14)); // Example: Due date set to 14 days from now
                    return true;
                } else {
                    return false; // Book is already checked out
                }
            }
        }
        return false; // Book not found
    }

    // Method to check out a book by its ID
    public boolean checkOutBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    book.setDueDate(LocalDate.now().plusDays(14)); // Example: Due date set to 14 days from now
                    return true;
                } else {
                    return false; // Book is already checked out
                }
            }
        }
        return false; // Book not found
    }

    // Method to check out a book by its barcode
    public boolean checkOutBookByBarcode(int barcode) {
        for (Book book : books) {
            if (book.getBarcode() == barcode) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    book.setDueDate(LocalDate.now().plusDays(14)); // Example: Due date set to 14 days from now
                    return true;
                } else {
                    return false; // Book is already checked out
                }
            }
        }
        return false; // Book not found
    }

    // Method to check in a book by its title
    public boolean checkInBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    book.setDueDate(null); // Clear due date upon check-in
                    return true;
                } else {
                    return false; // Book is already available
                }
            }
        }
        return false; // Book not found
    }

    // Method to check in a book by its ID
    public boolean checkInBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    book.setDueDate(null); // Clear due date upon check-in
                    return true;
                } else {
                    return false; // Book is already available
                }
            }
        }
        return false; // Book not found
    }

    // Method to check in a book by its barcode
    public boolean checkInBookByBarcode(int barcode) {
        for (Book book : books) {
            if (book.getBarcode() == barcode) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    book.setDueDate(null); // Clear due date upon check-in
                    return true;
                } else {
                    return false; // Book is already available
                }
            }
        }
        return false; // Book not found
    }

    // Method to list all books in the library
    public List<Book> listAllBooks() {
        return books;
    }
}
