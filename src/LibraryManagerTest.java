import java.util.List;

/**
 * A test class for the {@link LibraryManager} class.
 * <p>
 * This class demonstrates how to use the {@link LibraryManager} to perform various operations such as
 * adding books from a file, listing all books, removing books by title, checking out books by barcode,
 * and checking in books by ID.
 * </p>
 */
public class LibraryManagerTest {

    /**
     * The main method that runs the test operations on the {@link LibraryManager}.
     * <p>
     * This method performs the following tasks:
     * <ul>
     *     <li>Adds books from a specified file.</li>
     *     <li>Lists all books in the library.</li>
     *     <li>Removes a book by its title.</li>
     *     <li>Checks out a book by its barcode.</li>
     *     <li>Checks in a book by its ID.</li>
     * </ul>
     * </p>
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // Path to the file containing book data
        String filePath = "C:\\Users\\Herby\\OneDrive\\Desktop\\books.txt";

        try {
            // Add books from file
            boolean booksAdded = libraryManager.addBookFromFile(filePath);
            if (booksAdded) {
                System.out.println("Books added successfully from file.");
            } else {
                System.out.println("Failed to add books from file.");
            }

            // List all books in the library
            List<Book> allBooks = libraryManager.listAllBooks();
            System.out.println("All books in the library:");
            if (allBooks.isEmpty()) {
                System.out.println("No books available.");
            } else {
                for (Book book : allBooks) {
                    System.out.println(book);
                }
            }

            // Example: Remove a book by title
            String titleToRemove = "1984"; // Use actual title from your data
            boolean removedByTitle = libraryManager.removeBookByTitle(titleToRemove);
            if (removedByTitle) {
                System.out.println("Book with title '" + titleToRemove + "' removed.");
            } else {
                System.out.println("Book with title '" + titleToRemove + "' not found or could not be removed.");
            }

            // Example: Check out a book by barcode
            int barcodeToCheckout = 123; // Replace with an actual barcode from your data
            boolean checkedOut = libraryManager.checkOutBookByBarcode(barcodeToCheckout);
            if (checkedOut) {
                System.out.println("Book with barcode '" + barcodeToCheckout + "' checked out successfully.");
            } else {
                System.out.println("Book with barcode '" + barcodeToCheckout + "' not found or could not be checked out.");
            }

            // Example: Check in a book by ID
            int bookIdToCheckIn = 1; // Replace with an actual book ID from your data
            boolean checkedIn = libraryManager.checkInBookById(bookIdToCheckIn);
            if (checkedIn) {
                System.out.println("Book with ID '" + bookIdToCheckIn + "' checked in successfully.");
            } else {
                System.out.println("Book with ID '" + bookIdToCheckIn + "' not found or could not be checked in.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
