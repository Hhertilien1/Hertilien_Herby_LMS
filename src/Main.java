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
                    removeBook();
                    break;
                case 3:
                    checkOutBook();
                    break;
                case 4:
                    checkInBook();
                    break;
                case 5:
                    listAllBooks();
                    break;
                case 6:
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
        System.out.println("2. Remove a book");
        System.out.println("3. Check out a book");
        System.out.println("4. Check in a book");
        System.out.println("5. List all books");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBookFromFile() {
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        libraryManager.addBookFromFile(fileName);
    }

    private static void removeBook() {
        System.out.print("Enter the book title or ID to remove: ");
        String input = scanner.nextLine();
        try {
            int bookId = Integer.parseInt(input);
            libraryManager.removeBookById(bookId);
        } catch (NumberFormatException e) {
            libraryManager.removeBookByTitle(input);
        }
    }

    private static void checkOutBook() {
        System.out.print("Enter the book title to check out: ");
        String checkOutTitle = scanner.nextLine();
        libraryManager.checkOutBook(checkOutTitle);
    }

    private static void checkInBook() {
        System.out.print("Enter the book title to check in: ");
        String checkInTitle = scanner.nextLine();
        libraryManager.checkInBook(checkInTitle);
    }

    private static void listAllBooks() {
        libraryManager.listAllBooks();
    }
}
