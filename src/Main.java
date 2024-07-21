/**
 * Herby Heertlien
 * CEN 3024C-33022
 * Prof. Walauskis
 * 5/19/24
 * Software Development One
 *
 * Main Class: Provides the user interface for interacting with the library management system.
 * It includes a menu for adding books from a file, removing books, listing all books, and exiting the program.
 *
 * Relation to Overall Program: Acts as the entry point of the program.
 * It initializes the LibraryManager and uses it to perform operations based on user input.
 * This class orchestrates the flow of the program by responding to user commands and invoking appropriate methods in the LibraryManager.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static LibraryManager libraryManager = new LibraryManager();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main method that starts the program and provides a menu for user interaction.
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBookFromFile();
                    break;
                case 2:
                    removeBookById();
                    break;
                case 3:
                    removeBookByBarcode();
                    break;
                case 4:
                    removeBookByTitle();
                    break;
                case 5:
                    checkOutBookById();
                    break;
                case 6:
                    checkOutBookByBarcode();
                    break;
                case 7:
                    checkOutBookByTitle();
                    break;
                case 8:
                    checkInBookById();
                    break;
                case 9:
                    checkInBookByBarcode();
                    break;
                case 10:
                    checkInBookByTitle();
                    break;
                case 11:
                    listAllBooks();
                    break;
                case 12:
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    /**
     * Prints the menu options for the user.
     * Provides a list of actions the user can choose to perform on the library system.
     */
    private static void printMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Add books from file");
        System.out.println("2. Remove a book by ID");
        System.out.println("3. Remove a book by barcode");
        System.out.println("4. Remove a book by title");
        System.out.println("5. Check out a book by ID");
        System.out.println("6. Check out a book by barcode");
        System.out.println("7. Check out a book by title");
        System.out.println("8. Check in a book by ID");
        System.out.println("9. Check in a book by barcode");
        System.out.println("10. Check in a book by title");
        System.out.println("11. List all books");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    /**
     * Gets the user's choice from the menu.
     * @return the chosen menu option as an integer.
     */
    private static int getUserChoice() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    /**
     * Adds books to the library from a specified file.
     * Prompts the user for the file name and attempts to add books from that file.
     */
    private static void addBookFromFile() {
        System.out.print("Enter the file name: ");
        String fileName = scanner.next();
        if (libraryManager.addBookFromFile(fileName)) {
            System.out.println("Books added successfully.");
        } else {
            System.out.println("No books were added.");
        }
    }

    /**
     * Removes a book from the library by its ID.
     * Prompts the user for the book ID and attempts to remove the book with that ID.
     */
    private static void removeBookById() {
        System.out.print("Enter the book ID to remove: ");
        int bookId = getValidInteger();
        if (libraryManager.removeBookById(bookId)) {
            System.out.println("Book with ID " + bookId + " removed successfully.");
        } else {
            System.out.println("Failed to remove book with ID " + bookId + ".");
        }
    }

    /**
     * Removes a book from the library by its barcode.
     * Prompts the user for the book barcode and attempts to remove the book with that barcode.
     */
    private static void removeBookByBarcode() {
        System.out.print("Enter the book barcode to remove: ");
        int barcode = getValidInteger();
        if (libraryManager.removeBookByBarcode(barcode)) {
            System.out.println("Book with barcode " + barcode + " removed successfully.");
        } else {
            System.out.println("Failed to remove book with barcode " + barcode + ".");
        }
    }

    /**
     * Removes a book from the library by its title.
     * Prompts the user for the book title and attempts to remove the book with that title.
     */
    private static void removeBookByTitle() {
        System.out.print("Enter the book title to remove: ");
        String title = scanner.next();
        if (libraryManager.removeBookByTitle(title)) {
            System.out.println("Book with title '" + title + "' removed successfully.");
        } else {
            System.out.println("Failed to remove book with title '" + title + "'.");
        }
    }

    /**
     * Checks out a book from the library by its ID.
     * Prompts the user for the book ID and attempts to check out the book with that ID.
     */
    private static void checkOutBookById() {
        System.out.print("Enter the book ID to check out: ");
        int bookId = getValidInteger();
        if (libraryManager.checkOutBookById(bookId)) {
            System.out.println("Book with ID " + bookId + " checked out successfully.");
        } else {
            System.out.println("Failed to check out book with ID " + bookId + ".");
        }
    }

    /**
     * Checks out a book from the library by its barcode.
     * Prompts the user for the book barcode and attempts to check out the book with that barcode.
     */
    private static void checkOutBookByBarcode() {
        System.out.print("Enter the book barcode to check out: ");
        int barcode = getValidInteger();
        if (libraryManager.checkOutBookByBarcode(barcode)) {
            System.out.println("Book with barcode " + barcode + " checked out successfully.");
        } else {
            System.out.println("Failed to check out book with barcode " + barcode + ".");
        }
    }

    /**
     * Checks out a book from the library by its title.
     * Prompts the user for the book title and attempts to check out the book with that title.
     */
    private static void checkOutBookByTitle() {
        System.out.print("Enter the book title to check out: ");
        String title = scanner.next();
        if (libraryManager.checkOutBookByTitle(title)) {
            System.out.println("Book with title '" + title + "' checked out successfully.");
        } else {
            System.out.println("Failed to check out book with title '" + title + "'.");
        }
    }

    /**
     * Checks in a book to the library by its ID.
     * Prompts the user for the book ID and attempts to check in the book with that ID.
     */
    private static void checkInBookById() {
        System.out.print("Enter the book ID to check in: ");
        int bookId = getValidInteger();
        if (libraryManager.checkInBookById(bookId)) {
            System.out.println("Book with ID " + bookId + " checked in successfully.");
        } else {
            System.out.println("Failed to check in book with ID " + bookId + ".");
        }
    }

    /**
     * Checks in a book to the library by its barcode.
     * Prompts the user for the book barcode and attempts to check in the book with that barcode.
     */
    private static void checkInBookByBarcode() {
        System.out.print("Enter the book barcode to check in: ");
        int barcode = getValidInteger();
        if (libraryManager.checkInBookByBarcode(barcode)) {
            System.out.println("Book with barcode " + barcode + " checked in successfully.");
        } else {
            System.out.println("Failed to check in book with barcode " + barcode + ".");
        }
    }

    /**
     * Checks in a book to the library by its title.
     * Prompts the user for the book title and attempts to check in the book with that title.
     */
    private static void checkInBookByTitle() {
        System.out.print("Enter the book title to check in: ");
        String title = scanner.next();
        if (libraryManager.checkInBookByTitle(title)) {
            System.out.println("Book with title '" + title + "' checked in successfully.");
        } else {
            System.out.println("Failed to check in book with title '" + title + "'.");
        }
    }

    /**
     * Lists all books currently in the library.
     * Displays a list of all books and their details. If no books are available, notifies the user.
     */
    private static void listAllBooks() {
        System.out.println("Listing all books:");
        var books = libraryManager.listAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (var book : books) {
                System.out.println(book);
            }
        }
    }

    /**
     * Gets a valid integer input from the user.
     * Continuously prompts the user until a valid integer is entered.
     * @return the valid integer entered by the user.
     */
    private static int getValidInteger() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}
