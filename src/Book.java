/**
 * Herby Heertlien
 * CEN 3024C-33022
 * Prof. Walauskis
 * 5/19/24
 * Software Development One
 */

/**
 * Represents a single book with an ID, title, and author.
 * Each Book object encapsulates the details of one book.
 * Serves as a data model to store and manage book information within the library system.
 */
import java.time.LocalDate;
import java.util.Random;

public class Book {
    private static int bookCounter = 1; // Static variable to ensure unique and incremental IDs
    private int barcode;
    private String title;
    private int id;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate;

    /**
     * Constructor to create a new Book object.
     * @param id the ID of the book.
     * @param barcode the barcode of the book.
     * @param title the title of the book.
     * @param author the author of the book.
     * @param localDueDate the due date of the book.
     */
    public Book(int id, int barcode, String title, String author, LocalDate localDueDate) {
        this.barcode = generateRandomBarcode(); // Auto-assign barcode
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Newly added books are available by default
        this.dueDate = null; // Due date initially set to null
        this.id = generateBookId(); // Generate a unique book ID
    }

    /**
     * Generates a random 3-digit barcode.
     * @return a random number between 100 and 999.
     */
    private int generateRandomBarcode() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Generates a random number between 100 and 999
    }

    /**
     * Generates a unique book ID starting from 1.
     * @return a unique book ID.
     */
    private int generateBookId() {
        return bookCounter++;
    }

    /**
     * Returns a string representation of the book's details.
     * @return a string containing the book's details.
     */
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Checked Out";
        String dueDateString = (dueDate != null) ? ", Due Date: " + dueDate : "";
        return barcode + ", " + title + ", " + author + ", " + status + dueDateString;
    }

    // Getters and Setters

    /**
     * Gets the barcode of the book.
     * @return the barcode of the book.
     */
    public int getBarcode() {
        return barcode;
    }

    /**
     * Gets the title of the book.
     * @return the title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the author of the book.
     * @return the author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Checks if the book is available.
     * @return true if the book is available, false otherwise.
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability status of the book.
     * @param available the availability status to set.
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    /**
     * Gets the due date of the book.
     * @return the due date of the book.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date of the book.
     * @param dueDate the due date to set.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the ID of the book.
     * @return the ID of the book.
     */
    public int getBookId() {
        return id;
    }
}
