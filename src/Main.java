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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        // Adding books from file
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        libraryManager.addBookFromFile(fileName);

        // Listing all books
        System.out.println("\nPrinting the database:");
        libraryManager.listAllBooks();

        // Removing a book by ID
        System.out.print("\nEnter the book ID to remove: ");
        int bookId = Integer.parseInt(scanner.nextLine());
        libraryManager.removeBookById(bookId);
        System.out.println("Updated database:");
        libraryManager.listAllBooks();

        // Removing a book by title
        System.out.print("\nEnter the book title to remove: ");
        String bookTitle = scanner.nextLine();
        libraryManager.removeBookByTitle(bookTitle);
        System.out.println("Updated database:");
        libraryManager.listAllBooks();

        // Checking out a book
        System.out.print("\nEnter the book title to check out: ");
        String checkOutTitle = scanner.nextLine();
        libraryManager.checkOutBook(checkOutTitle);
        System.out.println("Updated database:");
        libraryManager.listAllBooks();

        // Checking in a book
        System.out.print("\nEnter the book title to check in: ");
        String checkInTitle = scanner.nextLine();
        libraryManager.checkInBook(checkInTitle);
        System.out.println("Updated database:");
        libraryManager.listAllBooks();

        scanner.close();
    }
}
