import java.util.List;

public class LibraryManagerTest {

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // Add books from file
        String filePath = "C:\\Users\\Herby\\OneDrive\\Desktop\\books.txt";
        boolean booksAdded = libraryManager.addBookFromFile(filePath);
        if (booksAdded) {
            System.out.println("Books added successfully from file.");
        } else {
            System.out.println("Failed to add books from file.");
        }

        // List all books in the library
        List<Book> allBooks = libraryManager.listAllBooks();
        System.out.println("All books in the library:");
        for (Book book : allBooks) {
            System.out.println(book);
        }

        // Example: Remove a book by title
        String titleToRemove = "1984";
        boolean removedByTitle = libraryManager.removeBookByTitle(titleToRemove);
        if (removedByTitle) {
            System.out.println("Book with title '" + titleToRemove + "' removed.");
        } else {
            System.out.println("Book with title '" + titleToRemove + "' not found or could not be removed.");
        }

        // Example: Check out a book by barcode
        int barcodeToCheckout = 123; // Replace with an actual barcode from your file
        boolean checkedOut = libraryManager.checkOutBookByBarcode(barcodeToCheckout);
        if (checkedOut) {
            System.out.println("Book with barcode '" + barcodeToCheckout + "' checked out successfully.");
        } else {
            System.out.println("Book with barcode '" + barcodeToCheckout + "' not found or could not be checked out.");
        }

        // Example: Check in a book by ID
        int bookIdToCheckIn = 1; // Replace with an actual book ID from your file
        boolean checkedIn = libraryManager.checkInBookById(bookIdToCheckIn);
        if (checkedIn) {
            System.out.println("Book with ID '" + bookIdToCheckIn + "' checked in successfully.");
        } else {
            System.out.println("Book with ID '" + bookIdToCheckIn + "' not found or could not be checked in.");
        }
    }
}
