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

public class LibraryManager {

    private List<Book> books;


    public LibraryManager() {
        this.books = new ArrayList<>();
    }

    // Method to add books from a file
    public void addBookFromFile(String filePath) {
        // Use try-with-resources to ensure the BufferedReader is closed after use
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line into parts using comma as a delimiter
                String[] parts = line.split(",");
                // Parse the book details from the parts
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                // Create a new Book object
                Book book = new Book(id, title, author);
                // Check for duplicate book ID
                if (!isBookIdDuplicate(id)) {
                    // Add the book to the list if the ID is not a duplicate
                    books.add(book);
                    System.out.println("Book added successfully.");
                } else {
                    // Print a message if the book ID is a duplicate
                    System.out.println("Book with ID " + id + " already exists. Skipped.");
                }
            }
        } catch (IOException e) {
            // Handle potential IO exceptions
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to remove a book by its ID
    public void removeBookById(int id) {
        // Remove the book with the given ID using removeIf and a lambda expression
        books.removeIf(book -> book.getId() == id);
        System.out.println("Book removed successfully.");
    }

    // Method to list all books in the collection
    public void listAllBooks() {
        // Check if the book list is empty
        if (books.isEmpty()) {
            System.out.println("No books in the collection.");
            return;
        }
        // Print a message followed by the details of each book
        System.out.println("Books in the collection:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Private method to check for duplicate book IDs
    private boolean isBookIdDuplicate(int id) {
        // Iterate through the book list to check for the given ID
        for (Book book : books) {
            if (book.getId() == id) {
                return true; // Return true if a duplicate ID is found
            }
        }
        return false; // Return false if no duplicate ID is found
    }
}
