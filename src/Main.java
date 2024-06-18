/*Herby Heertlien
* CEN 3024C-33022
* Prof. Walauskis
* 5/19/24
* Softwarre Development One
*
*Main Class: Provides the user interface for interacting with the library management system.
* It includes a menu for adding books from a file, removing books, listing all books, and exiting the program.
* Relation to Overall Program: Acts as the entry point of the program.
* It initializes the LibraryManager and uses it to perform operations based on user input.
* This class orchestrates the flow of the program by responding to user commands and invoking appropriate methods in the LibraryManager.
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        // Ask the user for a file name and add books to the LMS database
        System.out.println("Enter the file name to load books:");
        String fileName = scanner.nextLine();
        libraryManager.addBookFromFile(fileName);

        // Print the database
        System.out.println("Current books in the database:");
        libraryManager.listAllBooks();

        // Remove a book by barcode (ID)
        System.out.println("Enter the ID of the book to remove:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        libraryManager.removeBookById(id);
        System.out.println("Book removed. Current books in the database:");
        libraryManager.listAllBooks();

        // Remove a book by title
        System.out.println("Enter the title of the book to remove:");
        String title = scanner.nextLine();
        libraryManager.removeBookByTitle(title);
        System.out.println("Book removed. Current books in the database:");
        libraryManager.listAllBooks();

        // Check out a book by title
        System.out.println("Enter the title of the book to check out:");
        title = scanner.nextLine();
        libraryManager.checkOutBook(title);
        System.out.println("Book checked out. Current books in the database:");
        libraryManager.listAllBooks();

        // Check in a book by title
        System.out.println("Enter the title of the book to check in:");
        title = scanner.nextLine();
        libraryManager.checkInBook(title);
        System.out.println("Book checked in. Current books in the database:");
        libraryManager.listAllBooks();
    }
}
