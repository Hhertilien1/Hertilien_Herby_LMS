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

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate; // Add due date field

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.dueDate = null;
    }

    // Getters and setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    @Override
    public String toString() {
        return id + ", " + title + ", " + author + ", " + (isAvailable ? "Available" : "Checked Out") + ", Due Date: " + dueDate;
    }
}
