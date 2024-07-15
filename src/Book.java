/*Herby Heertlien
 * CEN 3024C-33022
 * Prof. Walauskis
 * 5/19/24
 * Softwarre Development One
 *
 * Book Class: Represents a single book with an ID, title, and author.
 * Relation to Overall Program: Serves as a data model to store and manage book information within the library system.
 * Each Book object encapsulates the details of one book.
 */


import java.time.LocalDate;
import java.util.Random;

public class Book {
    private int barcode;
    private String title;
    private int id;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate;

    public Book(int id, int barcode, String title, String author, String dueDate) {
    }

    // Method to generate a random 3-digit barcode
    private int generateRandomBarcode() {
        Random random = new Random();
        return random.nextInt(900) + 100; // Generates a random number between 100 and 999
    }

    // Constructor
    public Book(String title, String author) {
        this.barcode = generateRandomBarcode(); // Auto-assign barcode
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Newly added books are available by default
        this.dueDate = null; // Due date initially set to null
        this.id = generateBookId(); // Generate a book ID
    }


    private int generateBookId() {
    Random random = new Random();
    return random.nextInt(1000); // Generates a random number between 0 and 999
    }


// toString method to represent the book's details
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Checked Out";
        String dueDateString = (dueDate != null) ? ", Due Date: " + dueDate : "";
        return barcode + ", " + title + ", " + author + ", " + status + dueDateString;
    }

    // Getters and Setters
    public int getBarcode() {
        return barcode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getBookId() {
        return barcode;
    }
}
