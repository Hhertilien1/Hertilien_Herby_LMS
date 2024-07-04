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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryManager {
    private List<Book> books;
    private Set<String> processedFiles;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.processedFiles = new HashSet<>();
    }

    public boolean addBookFromFile(String filePath) {
        if (processedFiles.contains(filePath)) {
            return false;
        }

        boolean added = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 3) continue; // Skip invalid lines
                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                String author = parts[2].trim();
                Book book = new Book(id, title, author);
                if (!isBookIdDuplicate(id)) {
                    books.add(book);
                    added = true;
                }
            }
            if (added) {
                processedFiles.add(filePath);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return added;
    }

    public boolean removeBookById(int id) {
        return books.removeIf(book -> book.getId() == id);
    }

    public boolean removeBookByTitle(String title) {
        return books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

    public List<Book> listAllBooks() {
        return books;
    }

    public boolean checkOutBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && book.isAvailable()) {
                book.setAvailable(false);
                return true;
            }
        }
        return false;
    }

    public boolean checkInBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title) && !book.isAvailable()) {
                book.setAvailable(true);
                return true;
            }
        }
        return false;
    }

    private boolean isBookIdDuplicate(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isFileAlreadyProcessed(String filePath) {
        return processedFiles.contains(filePath);
    }
}
