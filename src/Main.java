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
        // Create an instance of LibraryManager to manage the library operations
        LibraryManager libraryManager = new LibraryManager();
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Add books from the initial file named "books.txt"
        libraryManager.addBookFromFile("books.txt");

        // Variable to control the main loop of the user interaction
        boolean running = true;

        // Main loop for user interaction
        while (running) {
            // Display the menu options
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add new book from file");
            System.out.println("2. Remove a book");
            System.out.println("3. List all books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            // Read the user's choice
            int choice = scanner.nextInt();

            // Handle the user's choice using a switch statement
            switch (choice) {
                case 1:
                    // Option to add new books from a file specified by the user
                    System.out.print("Enter the file path: ");
                    String filePath = scanner.next();
                    libraryManager.addBookFromFile(filePath);
                    break;
                case 2:
                    // Option to remove a book by its ID
                    System.out.print("Enter the ID of the book to remove: ");
                    int idToRemove = scanner.nextInt();
                    libraryManager.removeBookById(idToRemove);
                    break;
                case 3:
                    // Option to list all books currently in the library
                    libraryManager.listAllBooks();
                    break;
                case 4:
                    // Option to exit the program
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}