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
import java.time.LocalDate;

import java.util.Scanner;

public class Main {
    private static LibraryManager libraryManager = new LibraryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {



        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

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

    private static void addBookFromFile() {
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        libraryManager.addBookFromFile(fileName);
    }

    private static void removeBookById() {
        System.out.print("Enter the book ID to remove: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.removeBookById(bookId);
    }

    private static void removeBookByBarcode() {
        System.out.print("Enter the book barcode to remove: ");
        int barcode = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.removeBookByBarcode(barcode);
    }

    private static void removeBookByTitle() {
        System.out.print("Enter the book title to remove: ");
        String title = scanner.nextLine();
        libraryManager.removeBookByTitle(title);
    }

    private static void checkOutBookById() {
        System.out.print("Enter the book ID to check out: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.checkOutBookById(bookId);
    }

    private static void checkOutBookByBarcode() {
        System.out.print("Enter the book barcode to check out: ");
        int barcode = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.checkOutBookByBarcode(barcode);
    }

    private static void checkOutBookByTitle() {
        System.out.print("Enter the book title to check out: ");
        String title = scanner.nextLine();
        libraryManager.checkOutBookByTitle(title);
    }

    private static void checkInBookById() {
        System.out.print("Enter the book ID to check in: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.checkInBookById(bookId);
    }

    private static void checkInBookByBarcode() {
        System.out.print("Enter the book barcode to check in: ");
        int barcode = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        libraryManager.checkInBookByBarcode(barcode);
    }

    private static void checkInBookByTitle() {
        System.out.print("Enter the book title to check in: ");
        String title = scanner.nextLine();
        libraryManager.checkInBookByTitle(title);
    }

    private static void listAllBooks() {
        libraryManager.listAllBooks();
    }
}
