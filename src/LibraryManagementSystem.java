/**
 * Herby Heertlien
 * CEN 3024C-33022
 * Prof. Walauskis
 *  5/19/24
 * Softwarre Development One
 * This class implements a graphical user interface (GUI) for a Library Management System (LMS).
 * It allows users to add books from a file, remove books by barcode or title, check out and check in books,
 * list all books in the library, and exit the program.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LibraryManagementSystem {
    private LibraryManager libraryManager; // Instance of LibraryManager to manage books
    private JFrame frame; // Main window frame of the GUI
    private JTextArea textAreaBooks; // Text area to display books in the library

    /**
     * Constructs the GUI for the Library Management System.
     * Initializes the library manager, sets up the main window frame, and initializes UI components.
     */
    public LibraryManagementSystem() {
        libraryManager = new LibraryManager();
        frame = new JFrame("Library Management System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        initUI(); // Initialize UI components
    }

    /**
     * Initializes the graphical user interface (GUI) components.
     * Sets up labels, text fields, buttons, and event listeners for interacting with the library.
     */
    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0xF0F0F0)); // Light gray background

        // Labels, text fields, and buttons for adding books from file
        JLabel labelFile = new JLabel("File Path:");
        JTextField textFieldFile = new JTextField();
        JButton buttonAddBooks = new JButton("Add Books from File");

        // Labels, text fields, and buttons for removing books by barcode
        JLabel labelBarcode = new JLabel("Barcode:");
        JTextField textFieldBarcode = new JTextField();
        JButton buttonRemoveBookByBarcode = new JButton("Remove Book by Barcode");

        // Labels, text fields, and buttons for removing books by title
        JLabel labelTitleRemove = new JLabel("Title:");
        JTextField textFieldTitleRemove = new JTextField();
        JButton buttonRemoveBookByTitle = new JButton("Remove Book by Title");

        // Labels, text fields, and buttons for checking out books
        JLabel labelTitleCheckOut = new JLabel("Title:");
        JTextField textFieldTitleCheckOut = new JTextField();
        JButton buttonCheckOutBook = new JButton("Check Out Book");

        // Labels, text fields, and buttons for checking in books
        JLabel labelTitleCheckIn = new JLabel("Title:");
        JTextField textFieldTitleCheckIn = new JTextField();
        JButton buttonCheckInBook = new JButton("Check In Book");

        // Button to list all books in the library
        JButton buttonListBooks = new JButton("List All Books");
        textAreaBooks = new JTextArea();
        JScrollPane scrollPaneBooks = new JScrollPane(textAreaBooks);
        textAreaBooks.setFont(new Font("Monospaced", Font.PLAIN, 14)); // Monospaced font for readability
        textAreaBooks.setBackground(new Color(0xFAFAFA)); // Light gray background
        textAreaBooks.setEditable(false); // Read-only text area

        // Button to exit the program
        JButton buttonExit = new JButton("Exit");

        // Set button colors
        buttonAddBooks.setBackground(new Color(0x4CAF50)); // Green
        buttonAddBooks.setForeground(Color.WHITE);
        buttonRemoveBookByBarcode.setBackground(new Color(0xF44336)); // Red
        buttonRemoveBookByBarcode.setForeground(Color.WHITE);
        buttonRemoveBookByTitle.setBackground(new Color(0xF44336)); // Red
        buttonRemoveBookByTitle.setForeground(Color.WHITE);
        buttonCheckOutBook.setBackground(new Color(0x2196F3)); // Blue
        buttonCheckOutBook.setForeground(Color.WHITE);
        buttonCheckInBook.setBackground(new Color(0x2196F3)); // Blue
        buttonCheckInBook.setForeground(Color.WHITE);
        buttonListBooks.setBackground(new Color(0xFF9800)); // Orange
        buttonListBooks.setForeground(Color.WHITE);
        buttonExit.setBackground(new Color(0x9E9E9E)); // Gray
        buttonExit.setForeground(Color.WHITE);

        // Set label fonts
        labelFile.setFont(new Font("Arial", Font.BOLD, 14));
        labelBarcode.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleRemove.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleCheckOut.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleCheckIn.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to the panel
        panel.add(labelFile);
        panel.add(textFieldFile);
        panel.add(buttonAddBooks);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.add(labelBarcode);
        panel.add(textFieldBarcode);
        panel.add(buttonRemoveBookByBarcode);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.add(labelTitleRemove);
        panel.add(textFieldTitleRemove);
        panel.add(buttonRemoveBookByTitle);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.add(labelTitleCheckOut);
        panel.add(textFieldTitleCheckOut);
        panel.add(buttonCheckOutBook);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.add(labelTitleCheckIn);
        panel.add(textFieldTitleCheckIn);
        panel.add(buttonCheckInBook);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.add(buttonListBooks);
        panel.add(buttonExit);

        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPaneBooks, BorderLayout.CENTER);

        // Action listeners for buttons
        buttonAddBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = textFieldFile.getText();
                boolean result = libraryManager.addBookFromFile(filePath);
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Books added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Books from file '" + filePath + "' were already added.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRemoveBookByBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int barcode = Integer.parseInt(textFieldBarcode.getText());
                    boolean result = libraryManager.removeBookById(barcode);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with barcode '" + barcode + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid barcode number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRemoveBookByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textFieldTitleRemove.getText();
                boolean result = libraryManager.removeBookByTitle(title);
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Book with title '" + title + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckOutBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textFieldTitleCheckOut.getText();
                boolean result = libraryManager.checkOutBook(title);
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Book checked out successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Book with title '" + title + "' not available for checkout.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckInBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textFieldTitleCheckIn.getText();
                boolean result = libraryManager.checkInBook(title);
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Book checked in successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Book with title '" + title + "' not found or already checked in.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonListBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaBooks.setText("");
                java.util.List<Book> books = libraryManager.listAllBooks();
                if (books.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No books in the collection.", "Information", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    for (Book book : books) {
                        textAreaBooks.append(book + "\n");
                    }
                }
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    /**
     * Main method to start the Library Management System GUI application.
     * Creates an instance of LibraryManagementSystem and initializes the GUI.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}
