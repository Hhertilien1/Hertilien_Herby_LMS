import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryManagementSystem {
    private LibraryManager libraryManager;
    private JFrame frame;
    private JTextArea textAreaBooks;

    public LibraryManagementSystem() {
        libraryManager = new LibraryManager();
        frame = new JFrame("Library Management System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(0xF0F0F0));

        JLabel labelFile = new JLabel("File Path:");
        JTextField textFieldFile = new JTextField();
        JButton buttonAddBooks = new JButton("Add Books from File");

        JLabel labelBarcode = new JLabel("Barcode:");
        JTextField textFieldBarcode = new JTextField();
        JButton buttonRemoveBookByBarcode = new JButton("Remove Book by Barcode");

        JLabel labelTitleRemove = new JLabel("Title:");
        JTextField textFieldTitleRemove = new JTextField();
        JButton buttonRemoveBookByTitle = new JButton("Remove Book by Title");

        JLabel labelTitleCheckOut = new JLabel("Title:");
        JTextField textFieldTitleCheckOut = new JTextField();
        JButton buttonCheckOutBook = new JButton("Check Out Book");

        JLabel labelTitleCheckIn = new JLabel("Title:");
        JTextField textFieldTitleCheckIn = new JTextField();
        JButton buttonCheckInBook = new JButton("Check In Book");

        JButton buttonListBooks = new JButton("List All Books");
        textAreaBooks = new JTextArea();
        JScrollPane scrollPaneBooks = new JScrollPane(textAreaBooks);
        textAreaBooks.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textAreaBooks.setBackground(new Color(0xFAFAFA));
        textAreaBooks.setEditable(false);

        JButton buttonExit = new JButton("Exit");

        // Set button colors
        buttonAddBooks.setBackground(new Color(0x4CAF50));
        buttonAddBooks.setForeground(Color.WHITE);
        buttonRemoveBookByBarcode.setBackground(new Color(0xF44336));
        buttonRemoveBookByBarcode.setForeground(Color.WHITE);
        buttonRemoveBookByTitle.setBackground(new Color(0xF44336));
        buttonRemoveBookByTitle.setForeground(Color.WHITE);
        buttonCheckOutBook.setBackground(new Color(0x2196F3));
        buttonCheckOutBook.setForeground(Color.WHITE);
        buttonCheckInBook.setBackground(new Color(0x2196F3));
        buttonCheckInBook.setForeground(Color.WHITE);
        buttonListBooks.setBackground(new Color(0xFF9800));
        buttonListBooks.setForeground(Color.WHITE);
        buttonExit.setBackground(new Color(0x9E9E9E));
        buttonExit.setForeground(Color.WHITE);

        // Set label fonts
        labelFile.setFont(new Font("Arial", Font.BOLD, 14));
        labelBarcode.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleRemove.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleCheckOut.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitleCheckIn.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(labelFile);
        panel.add(textFieldFile);
        panel.add(buttonAddBooks);

        panel.add(labelBarcode);
        panel.add(textFieldBarcode);
        panel.add(buttonRemoveBookByBarcode);

        panel.add(labelTitleRemove);
        panel.add(textFieldTitleRemove);
        panel.add(buttonRemoveBookByTitle);

        panel.add(labelTitleCheckOut);
        panel.add(textFieldTitleCheckOut);
        panel.add(buttonCheckOutBook);

        panel.add(labelTitleCheckIn);
        panel.add(textFieldTitleCheckIn);
        panel.add(buttonCheckInBook);

        panel.add(buttonListBooks);
        panel.add(buttonExit);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPaneBooks, BorderLayout.CENTER);

        buttonAddBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = textFieldFile.getText();
                boolean success = libraryManager.addBookFromFile(filePath);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Books added from file successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (libraryManager.isFileAlreadyProcessed(filePath)) {
                        JOptionPane.showMessageDialog(frame, "Books from this file have already been added.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "No books were added. Please check the file and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
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
                    JOptionPane.showMessageDialog(frame, "Invalid barcode format.", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem());
    }
}
