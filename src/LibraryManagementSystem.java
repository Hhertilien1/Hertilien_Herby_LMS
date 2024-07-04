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
        JPanel panel = new JPanel(new GridLayout(7, 2));

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

        JButton buttonExit = new JButton("Exit");

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
                try {
                    libraryManager.addBookFromFile(filePath);
                    JOptionPane.showMessageDialog(frame, "Books added from file.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
                        JOptionPane.showMessageDialog(frame, "Book removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(frame, "Book removed.", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                    JOptionPane.showMessageDialog(frame, "Book checked out.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Book with title '" + title + "' is not available or not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCheckInBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = textFieldTitleCheckIn.getText();
                boolean result = libraryManager.checkInBook(title);
                if (result) {
                    JOptionPane.showMessageDialog(frame, "Book checked in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Book with title '" + title + "' is not checked out or not found.", "Error", JOptionPane.ERROR_MESSAGE);
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
