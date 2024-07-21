
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages a collection of Book objects and interacts with a database to perform various operations.
 * Provides methods to add, remove, and list books, as well as check in and check out books.
 * Handles database operations related to the library's book collection and manages book availability.
 */
public class LibraryManager {
    private Connection connection;

    /**
     * Constructs a LibraryManager and establishes a connection to the database.
     * Initializes the database connection using the specified URL, user, and password.
     */
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

    /**
     * Adds books to the database from a specified file.
     * The file should contain book information in the format: title, author.
     * @param filePath the path to the file containing book information.
     * @return true if at least one book was added, false otherwise.
     */
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

    /**
     * Adds a single book to the database.
     * @param title the title of the book.
     * @param author the author of the book.
     */
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

    /**
     * Removes a book from the database by its barcode.
     * @param barcode the barcode of the book to be removed.
     * @return true if the book was removed, false otherwise.
     */
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

    /**
     * Removes a book from the database by its title.
     * @param title the title of the book to be removed.
     * @return true if the book was removed, false otherwise.
     */
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

    /**
     * Removes a book from the database by its ID.
     * @param bookId the ID of the book to be removed.
     * @return true if the book was removed, false otherwise.
     */
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

    /**
     * Checks out a book from the library by its barcode.
     * Updates the book's availability to false and sets the due date.
     * @param barcode the barcode of the book to be checked out.
     * @return true if the book was checked out successfully, false otherwise.
     */
    public boolean checkOutBookByBarcode(int barcode) {
        return updateBookAvailability(barcode, false);
    }

    /**
     * Checks out a book from the library by its ID.
     * Updates the book's availability to false and sets the due date.
     * @param bookId the ID of the book to be checked out.
     * @return true if the book was checked out successfully, false otherwise.
     */
    public boolean checkOutBookById(int bookId) {
        return updateBookAvailabilityById(bookId, false);
    }

    /**
     * Checks in a book to the library by its barcode.
     * Updates the book's availability to true and clears the due date.
     * @param barcode the barcode of the book to be checked in.
     * @return true if the book was checked in successfully, false otherwise.
     */
    public boolean checkInBookByBarcode(int barcode) {
        return updateBookAvailability(barcode, true);
    }

    /**
     * Checks in a book to the library by its ID.
     * Updates the book's availability to true and clears the due date.
     * @param bookId the ID of the book to be checked in.
     * @return true if the book was checked in successfully, false otherwise.
     */
    public boolean checkInBookById(int bookId) {
        return updateBookAvailabilityById(bookId, true);
    }

    /**
     * Updates the availability status and due date of a book by its barcode.
     * @param barcode the barcode of the book to be updated.
     * @param available the new availability status of the book (true for available, false for checked out).
     * @return true if the book's availability was updated successfully, false otherwise.
     */
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

    /**
     * Updates the availability status and due date of a book by its ID.
     * @param bookId the ID of the book to be updated.
     * @param available the new availability status of the book (true for available, false for checked out).
     * @return true if the book's availability was updated successfully, false otherwise.
     */
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

    /**
     * Lists all books currently in the library database.
     * @return a list of all books, each represented by a Book object.
     */
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

    /**
     * Closes the database connection.
     * Ensures the connection is properly closed if it is open.
     */
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

    /**
     * Checks out a book from the library by its title.
     * Updates the book's availability to false and sets the due date.
     * @param title the title of the book to be checked out.
     * @return true if the book was checked out successfully, false otherwise.
     */
    public boolean checkOutBookByTitle(String title) {
        return updateBookAvailabilityByTitle(title, false);
    }

    /**
     * Checks in a book to the library by its title.
     * Updates the book's availability to true and clears the due date.
     * @param title the title of the book to be checked in.
     * @return true if the book was checked in successfully, false otherwise.
     */
    public boolean checkInBookByTitle(String title) {
        return updateBookAvailabilityByTitle(title, true);
    }

    /**
     * Updates the availability status and due date of a book by its title.
     * @param title the title of the book to be updated.
     * @param available the new availability status of the book (true for available, false for checked out).
     * @return true if the book's availability was updated successfully, false otherwise.
     */
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
