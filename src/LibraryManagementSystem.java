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
import java.util.List;

public class LibraryManagementSystem {
    private LibraryManager libraryManager;
    private JFrame frame;
    private JTextArea textAreaBooks;
    private JTextField textFieldRemoveById;

    // Constructor
    public LibraryManagementSystem() {
        libraryManager = new LibraryManager();
        frame = new JFrame("Library Management System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        initUI();
    }

    // Method to initialize the GUI components
    private void initUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0xF0F0F0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels, TextFields, and Buttons for various operations
        JLabel labelFile = new JLabel("File Path:");
        JTextField textFieldFile = new JTextField(20);
        JButton buttonAddBooks = new JButton("Add Books from File");

        JLabel labelBarcodeRemove = new JLabel("Barcode:");
        JTextField textFieldBarcodeRemove = new JTextField(10);
        JButton buttonRemoveBookByBarcode = new JButton("Remove Book by Barcode");

        JLabel labelIdRemove = new JLabel("Book ID:");
        textFieldRemoveById = new JTextField(10);
        JButton buttonRemoveBookById = new JButton("Remove Book by ID");

        JLabel labelTitleRemove = new JLabel("Title:");
        JTextField textFieldTitleRemove = new JTextField(20);
        JButton buttonRemoveBookByTitle = new JButton("Remove Book by Title");

        JLabel labelBarcodeCheckOut = new JLabel("Barcode:");
        JTextField textFieldBarcodeCheckOut = new JTextField(10);
        JButton buttonCheckOutBookByBarcode = new JButton("Check Out Book by Barcode");

        JLabel labelIdCheckOut = new JLabel("Book ID:");
        JTextField textFieldIdCheckOut = new JTextField(10);
        JButton buttonCheckOutBookById = new JButton("Check Out Book by ID");

        JLabel labelTitleCheckOut = new JLabel("Book Title:");
        JTextField textFieldTitleCheckOut = new JTextField(20);
        JButton buttonCheckOutBookByTitle = new JButton("Check Out Book by Title");

        JLabel labelBarcodeCheckIn = new JLabel("Barcode:");
        JTextField textFieldBarcodeCheckIn = new JTextField(10);
        JButton buttonCheckInBookByBarcode = new JButton("Check In Book by Barcode");

        JLabel labelIdCheckIn = new JLabel("Book ID:");
        JTextField textFieldCheckInById = new JTextField(10);
        JButton buttonCheckInBookById = new JButton("Check In Book by ID");

        JLabel labelTitleCheckIn = new JLabel("Title:");
        JTextField textFieldTitleCheckIn = new JTextField(20);
        JButton buttonCheckInBookByTitle = new JButton("Check In Book by Title");

        JButton buttonListBooks = new JButton("List All Books");
        textAreaBooks = new JTextArea(20, 50);
        JScrollPane scrollPaneBooks = new JScrollPane(textAreaBooks);
        textAreaBooks.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textAreaBooks.setBackground(new Color(0xFAFAFA));
        textAreaBooks.setEditable(false);

        JButton buttonExit = new JButton("Exit");

        // Setting colors and fonts for components
        buttonAddBooks.setBackground(new Color(0x4CAF50));
        buttonAddBooks.setForeground(Color.WHITE);
        buttonRemoveBookByBarcode.setBackground(new Color(0xF44336));
        buttonRemoveBookByBarcode.setForeground(Color.WHITE);
        buttonRemoveBookById.setBackground(new Color(0xF44336));
        buttonRemoveBookById.setForeground(Color.WHITE);
        buttonRemoveBookByTitle.setBackground(new Color(0xF44336));
        buttonRemoveBookByTitle.setForeground(Color.WHITE);
        buttonCheckOutBookByBarcode.setBackground(new Color(0x2196F3));
        buttonCheckOutBookByBarcode.setForeground(Color.WHITE);
        buttonCheckOutBookById.setBackground(new Color(0x2196F3));
        buttonCheckOutBookById.setForeground(Color.WHITE);
        buttonCheckOutBookByTitle.setBackground(new Color(0x2196F3));
        buttonCheckOutBookByTitle.setForeground(Color.WHITE);
        buttonCheckInBookByBarcode.setBackground(new Color(0x2196F3));
        buttonCheckInBookByBarcode.setForeground(Color.WHITE);
        buttonCheckInBookById.setBackground(new Color(0x2196F3));
        buttonCheckInBookById.setForeground(Color.WHITE);
        buttonCheckInBookByTitle.setBackground(new Color(0x2196F3));
        buttonCheckInBookByTitle.setForeground(Color.WHITE);
        buttonListBooks.setBackground(new Color(0xFF9800));
        buttonListBooks.setForeground(Color.WHITE);
        buttonExit.setBackground(new Color(0x9E9E9E));
        buttonExit.setForeground(Color.WHITE);

        // Adding components to the panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(labelFile, gbc);

        gbc.gridx = 1;
        panel.add(textFieldFile, gbc);

        gbc.gridx = 2;
        panel.add(buttonAddBooks, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(labelBarcodeRemove, gbc);

        gbc.gridx = 1;
        panel.add(textFieldBarcodeRemove, gbc);

        gbc.gridx = 2;
        panel.add(buttonRemoveBookByBarcode, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(labelTitleRemove, gbc);

        gbc.gridx = 1;
        panel.add(textFieldTitleRemove, gbc);

        gbc.gridx = 2;
        panel.add(buttonRemoveBookByTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(labelIdRemove, gbc);

        gbc.gridx = 1;
        panel.add(textFieldRemoveById, gbc);

        gbc.gridx = 2;
        panel.add(buttonRemoveBookById, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(labelTitleCheckOut, gbc);

        gbc.gridx = 1;
        panel.add(textFieldTitleCheckOut, gbc);

        gbc.gridx = 2;
        panel.add(buttonCheckOutBookByTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(labelIdCheckOut, gbc);

        gbc.gridx = 1;
        panel.add(textFieldIdCheckOut, gbc);

        gbc.gridx = 2;
        panel.add(buttonCheckOutBookById, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(labelBarcodeCheckOut, gbc);

        gbc.gridx = 1;
        panel.add(textFieldBarcodeCheckOut, gbc);

        gbc.gridx = 2;
        panel.add(buttonCheckOutBookByBarcode, gbc);

        // Adding "Check In Book by Barcode" button
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(labelBarcodeCheckIn, gbc);

        gbc.gridx = 1;
        panel.add(textFieldBarcodeCheckIn, gbc);

        gbc.gridx = 2;
        panel.add(buttonCheckInBookByBarcode, gbc);  // Add the button here

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(labelTitleCheckIn, gbc);

        gbc.gridx = 1;
        panel.add(textFieldTitleCheckIn, gbc);

        gbc.gridx = 2;
        panel.add(buttonCheckInBookByTitle, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        panel.add(buttonListBooks, gbc);

        gbc.gridy = 10;
        panel.add(buttonExit, gbc);

        // Adding panel and text area to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPaneBooks, BorderLayout.CENTER);

        // Action listeners for buttons
        buttonAddBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String filePath = textFieldFile.getText();
                    boolean result = libraryManager.addBookFromFile(filePath);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Books added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Books from file '" + filePath + "' were already added.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error adding books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRemoveBookByBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int barcode = Integer.parseInt(textFieldBarcodeRemove.getText());
                    boolean result = libraryManager.removeBookByBarcode(barcode);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with barcode '" + barcode + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid barcode input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRemoveBookByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = textFieldTitleRemove.getText();
                    boolean result = libraryManager.removeBookByTitle(title);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error removing books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonRemoveBookById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(textFieldRemoveById.getText());
                    boolean result = libraryManager.removeBookById(bookId);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid book ID input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckOutBookByBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int barcode = Integer.parseInt(textFieldBarcodeCheckOut.getText());
                    boolean result = libraryManager.checkOutBookByBarcode(barcode);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book checked out successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with barcode '" + barcode + "' is not available for checkout or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid barcode input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckOutBookById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(textFieldIdCheckOut.getText());
                    boolean result = libraryManager.checkOutBookById(bookId);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' checked out successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' is not available for checkout or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid book ID input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckOutBookByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = textFieldTitleCheckOut.getText();
                    boolean result = libraryManager.checkOutBookByTitle(title);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' checked out successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' are not available for checkout or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error checking out books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckInBookByBarcode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int barcode = Integer.parseInt(textFieldBarcodeCheckIn.getText());
                    boolean result = libraryManager.checkInBookByBarcode(barcode);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book checked in successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with barcode '" + barcode + "' is not available for check-in or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid barcode input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckInBookById.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookId = Integer.parseInt(textFieldCheckInById.getText());
                    boolean result = libraryManager.checkInBookById(bookId);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' checked in successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book with ID '" + bookId + "' is not available for check-in or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid book ID input.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckInBookByTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String title = textFieldTitleCheckIn.getText();
                    boolean result = libraryManager.checkInBookByTitle(title);
                    if (result) {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' checked in successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        updateBookList();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Books with title '" + title + "' are not available for check-in or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error checking in books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonListBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookList();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    // Method to update the text area with the list of books
    private void updateBookList() {
        List<Book> books = libraryManager.listAllBooks();
        StringBuilder sb = new StringBuilder();
        sb.append("ID\t| Barcode\t| Title\t| Author\t| Status\n");
        sb.append("------------------------------------------------------------\n");
        for (Book book : books) {
            sb.append(book.getBookId()).append("\t| ").append(book.getBarcode()).append("\t\t| ").append(book.getTitle()).append("\t| ").append(book.getAuthor()).append("\t| ").append(book.getDueDate()).append("\n");
        }
        textAreaBooks.setText(sb.toString());
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagementSystem();
            }
        });
    }
}
