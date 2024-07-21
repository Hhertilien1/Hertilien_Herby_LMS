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
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private Connection connection;

    // Constructor: Establishes database connection
    public LibraryManager() {
        try {
            // Replace these with your MySQL database credentials and schema name
            String url = "jdbc:mysql://127.0.0.1:3306/LMS_db";
            String user = "root";
            String password = "Herby!123";

            // Initialize database connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database.");
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to add books from a file to the database
    public boolean addBookFromFile(String filePath) {
        boolean addedAtLeastOneBook = false;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] bookInfo = line.split(",");
                if (bookInfo.length >= 2) {
                    String title = bookInfo[0].trim();
                    String author = bookInfo[1].trim();
                    addBook(title, author); // Add book to database
                    addedAtLeastOneBook = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return addedAtLeastOneBook;
    }

    // Method to add a book to the database
    private void addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author, available, due_date) VALUES (?, ?, true, null)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    // Method to remove a book by its barcode from the database
    public boolean removeBookByBarcode(int barcode) {
        String sql = "DELETE FROM books WHERE barcode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, barcode);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error removing book: " + e.getMessage());
            return false;
        }
    }

    // Method to remove a book by its title from the database
    public boolean removeBookByTitle(String title) {
        String sql = "DELETE FROM books WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error removing book: " + e.getMessage());
            return false;
        }
    }

    // Method to remove a book by its ID from the database
    public boolean removeBookById(int bookId) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error removing book: " + e.getMessage());
            return false;
        }
    }

    // Method to check out a book by its barcode
    public boolean checkOutBookByBarcode(int barcode) {
        return updateBookAvailability(barcode, false);
    }

    // Method to check out a book by its ID
    public boolean checkOutBookById(int bookId) {
        return updateBookAvailabilityById(bookId, false);
    }

    // Method to check in a book by its barcode
    public boolean checkInBookByBarcode(int barcode) {
        return updateBookAvailability(barcode, true);
    }

    // Method to check in a book by its ID
    public boolean checkInBookById(int bookId) {
        return updateBookAvailabilityById(bookId, true);
    }

    // Method to update book availability by barcode
    private boolean updateBookAvailability(int barcode, boolean available) {
        String sql = "UPDATE books SET available = ?, due_date = ? WHERE barcode = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, available);
            if (available) {
                statement.setNull(2, Types.DATE); // Clear due date if available
            } else {
                statement.setDate(2, Date.valueOf(LocalDate.now().plusDays(14))); // Example: Due date 14 days from now
            }
            statement.setInt(3, barcode);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating book availability: " + e.getMessage());
            return false;
        }
    }

    // Method to update book availability by ID
    private boolean updateBookAvailabilityById(int bookId, boolean available) {
        String sql = "UPDATE books SET available = ?, due_date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, available);
            if (available) {
                statement.setNull(2, Types.DATE); // Clear due date if available
            } else {
                statement.setDate(2, Date.valueOf(LocalDate.now().plusDays(14))); // Example: Due date 14 days from now
            }
            statement.setInt(3, bookId);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating book availability: " + e.getMessage());
            return false;
        }
    }

    // Method to list all books from the database
    public List<Book> listAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int barcode = resultSet.getInt("barcode");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Date dueDate = resultSet.getDate("due_date");
                LocalDate localDueDate = (dueDate != null) ? dueDate.toLocalDate() : null;
                books.add(new Book(id, barcode, title, author, localDueDate));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving books: " + e.getMessage());
        }
        return books;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Example usage:
        LibraryManager libraryManager = new LibraryManager();

        // Add books from a file (example file path)
        libraryManager.addBookFromFile("C:/path/to/your/books.txt");

        // List all books
        List<Book> allBooks = libraryManager.listAllBooks();
        System.out.println("All Books:");
        for (Book book : allBooks) {
            System.out.println(book);
        }

        // Check out a book by barcode
        int barcodeToCheckOut = 123; // Replace with a valid barcode
        if (libraryManager.checkOutBookByBarcode(barcodeToCheckOut)) {
            System.out.println("Book with barcode " + barcodeToCheckOut + " checked out successfully.");
        } else {
            System.out.println("Failed to check out book with barcode " + barcodeToCheckOut + ".");
        }

        // Check in a book by ID
        int bookIdToCheckIn = 1; // Replace with a valid book ID
        if (libraryManager.checkInBookById(bookIdToCheckIn)) {
            System.out.println("Book with ID " + bookIdToCheckIn + " checked in successfully.");
        } else {
            System.out.println("Failed to check in book with ID " + bookIdToCheckIn + ".");
        }

        // Remove a book by title
        String titleToRemove = "Book Title"; // Replace with a valid book title
        if (libraryManager.removeBookByTitle(titleToRemove)) {
            System.out.println("Book with title '" + titleToRemove + "' removed successfully.");
        } else {
            System.out.println("Failed to remove book with title '" + titleToRemove + "'.");
        }

        // Close the connection when done
        libraryManager.closeConnection();
    }

    // Method to check out a book by its title
    public boolean checkOutBookByTitle(String title) {
        return updateBookAvailabilityByTitle(title, false);
    }

    // Method to check in a book by its title
    public boolean checkInBookByTitle(String title) {
        return updateBookAvailabilityByTitle(title, true);
    }

    // Helper method to update book availability by title
    private boolean updateBookAvailabilityByTitle(String title, boolean available) {
        String sql = "UPDATE books SET available = ?, due_date = ? WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, available);
            if (available) {
                statement.setNull(2, Types.DATE); // Clear due date if available
            } else {
                statement.setDate(2, Date.valueOf(LocalDate.now().plusDays(14))); // Example: Due date 14 days from now
            }
            statement.setString(3, title);
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating book availability: " + e.getMessage());
            return false;
        }
    }
}
