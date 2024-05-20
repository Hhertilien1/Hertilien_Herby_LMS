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



public class Book {

    private int id;
    private String title;
    private String author;


    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    // Getter method for the book's ID
    public int getId() {
        return id;
    }

    // Getter method for the book's title
    public String getTitle() {
        return title;
    }

    // Getter method for the book's author
    public String getAuthor() {
        return author;
    }


    @Override
    public String toString() {
        // Return a string in the format "id,title,author"
        return id + "," + title + "," + author;
    }
}
