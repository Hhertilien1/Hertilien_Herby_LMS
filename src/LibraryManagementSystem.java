import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The LibraryManagementSystem class implements a graphical user interface (GUI) for a Library Management System (LMS).
 * It allows users to add books from a file, remove books by barcode, ID, or title, check out and check in books,
 * list all books in the library, and exit the program.
 * <p>
 * The class uses Java Swing for the GUI components and communicates with the LibraryManager class
 * to perform library management operations.
 * </p>
 *
 * <p>Author: Herby Heertlien</p>
 * <p>Course: CEN 3024C-33022</p>
 * <p>Instructor: Prof. Walauskis</p>
 * <p>Date: 5/19/24</p>
 */
public class LibraryManagementSystem {
    private static final Color SUCCESS_COLOR = new Color(0x4CAF50);
    private static final Color REMOVE_COLOR = new Color(0xF44336);
    private static final Color CHECKOUT_COLOR = new Color(0x2196F3);
    private static final Color LIST_COLOR = new Color(0xFF9800);
    private static final Color EXIT_COLOR = new Color(0x9E9E9E);
    private static final Font TEXT_AREA_FONT = new Font("Monospaced", Font.PLAIN, 14);
    private static final Color TEXT_AREA_BG_COLOR = new Color(0xFAFAFA);

    private LibraryManager libraryManager;
    private JFrame frame;
    private JTextArea textAreaBooks;
    private JTextField textFieldRemoveById;

    /**
     * Constructs a new LibraryManagementSystem and initializes the GUI.
     */
    public LibraryManagementSystem() {
        libraryManager = new LibraryManager();
        frame = new JFrame("Library Management System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        initUI();
    }

    /**
     * Initializes the GUI components and layout.
     */
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
        JButton buttonAddBooks = createButton("Add Books from File", SUCCESS_COLOR);

        JLabel labelBarcodeRemove = new JLabel("Barcode:");
        JTextField textFieldBarcodeRemove = new JTextField(10);
        JButton buttonRemoveBookByBarcode = createButton("Remove Book by Barcode", REMOVE_COLOR);

        JLabel labelIdRemove = new JLabel("Book ID:");
        textFieldRemoveById = new JTextField(10);
        JButton buttonRemoveBookById = createButton("Remove Book by ID", REMOVE_COLOR);

        JLabel labelTitleRemove = new JLabel("Title:");
        JTextField textFieldTitleRemove = new JTextField(20);
        JButton buttonRemoveBookByTitle = createButton("Remove Book by Title", REMOVE_COLOR);

        JLabel labelBarcodeCheckOut = new JLabel("Barcode:");
        JTextField textFieldBarcodeCheckOut = new JTextField(10);
        JButton buttonCheckOutBookByBarcode = createButton("Check Out Book by Barcode", CHECKOUT_COLOR);

        JLabel labelIdCheckOut = new JLabel("Book ID:");
        JTextField textFieldIdCheckOut = new JTextField(10);
        JButton buttonCheckOutBookById = createButton("Check Out Book by ID", CHECKOUT_COLOR);

        JLabel labelTitleCheckOut = new JLabel("Book Title:");
        JTextField textFieldTitleCheckOut = new JTextField(20);
        JButton buttonCheckOutBookByTitle = createButton("Check Out Book by Title", CHECKOUT_COLOR);

        JLabel labelBarcodeCheckIn = new JLabel("Barcode:");
        JTextField textFieldBarcodeCheckIn = new JTextField(10);
        JButton buttonCheckInBookByBarcode = createButton("Check In Book by Barcode", CHECKOUT_COLOR);

        JLabel labelIdCheckIn = new JLabel("Book ID:");
        JTextField textFieldCheckInById = new JTextField(10);
        JButton buttonCheckInBookById = createButton("Check In Book by ID", CHECKOUT_COLOR);

        JLabel labelTitleCheckIn = new JLabel("Title:");
        JTextField textFieldTitleCheckIn = new JTextField(20);
        JButton buttonCheckInBookByTitle = createButton("Check In Book by Title", CHECKOUT_COLOR);

        JButton buttonListBooks = createButton("List All Books", LIST_COLOR);
        textAreaBooks = new JTextArea(20, 50);
        JScrollPane scrollPaneBooks = new JScrollPane(textAreaBooks);
        textAreaBooks.setFont(TEXT_AREA_FONT);
        textAreaBooks.setBackground(TEXT_AREA_BG_COLOR);
        textAreaBooks.setEditable(false);

        JButton buttonExit = createButton("Exit", EXIT_COLOR);

        // Adding components to the panel using GridBagLayout
        addComponentToPanel(panel, gbc, labelFile, 0, 0);
        addComponentToPanel(panel, gbc, textFieldFile, 1, 0);
        addComponentToPanel(panel, gbc, buttonAddBooks, 2, 0);

        addComponentToPanel(panel, gbc, labelBarcodeRemove, 0, 1);
        addComponentToPanel(panel, gbc, textFieldBarcodeRemove, 1, 1);
        addComponentToPanel(panel, gbc, buttonRemoveBookByBarcode, 2, 1);

        addComponentToPanel(panel, gbc, labelTitleRemove, 0, 2);
        addComponentToPanel(panel, gbc, textFieldTitleRemove, 1, 2);
        addComponentToPanel(panel, gbc, buttonRemoveBookByTitle, 2, 2);

        addComponentToPanel(panel, gbc, labelIdRemove, 0, 3);
        addComponentToPanel(panel, gbc, textFieldRemoveById, 1, 3);
        addComponentToPanel(panel, gbc, buttonRemoveBookById, 2, 3);

        addComponentToPanel(panel, gbc, labelTitleCheckOut, 0, 4);
        addComponentToPanel(panel, gbc, textFieldTitleCheckOut, 1, 4);
        addComponentToPanel(panel, gbc, buttonCheckOutBookByTitle, 2, 4);

        addComponentToPanel(panel, gbc, labelIdCheckOut, 0, 5);
        addComponentToPanel(panel, gbc, textFieldIdCheckOut, 1, 5);
        addComponentToPanel(panel, gbc, buttonCheckOutBookById, 2, 5);

        addComponentToPanel(panel, gbc, labelBarcodeCheckOut, 0, 6);
        addComponentToPanel(panel, gbc, textFieldBarcodeCheckOut, 1, 6);
        addComponentToPanel(panel, gbc, buttonCheckOutBookByBarcode, 2, 6);

        addComponentToPanel(panel, gbc, labelBarcodeCheckIn, 0, 7);
        addComponentToPanel(panel, gbc, textFieldBarcodeCheckIn, 1, 7);
        addComponentToPanel(panel, gbc, buttonCheckInBookByBarcode, 2, 7);

        addComponentToPanel(panel, gbc, labelTitleCheckIn, 0, 8);
        addComponentToPanel(panel, gbc, textFieldTitleCheckIn, 1, 8);
        addComponentToPanel(panel, gbc, buttonCheckInBookByTitle, 2, 8);

        addComponentToPanel(panel, gbc, labelIdCheckIn, 0, 9);
        addComponentToPanel(panel, gbc, textFieldCheckInById, 1, 9);
        addComponentToPanel(panel, gbc, buttonCheckInBookById, 2, 9);

        addComponentToPanel(panel, gbc, buttonListBooks, 0, 10, 3, 1);
        addComponentToPanel(panel, gbc, buttonExit, 0, 11, 3, 1);

        // Adding panel and text area to the frame
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPaneBooks, BorderLayout.CENTER);

        // Action listeners for buttons
        buttonAddBooks.addActionListener(e -> handleAddBooks(textFieldFile));
        buttonRemoveBookByBarcode.addActionListener(e -> handleRemoveBookByBarcode(textFieldBarcodeRemove));
        buttonRemoveBookByTitle.addActionListener(e -> handleRemoveBookByTitle(textFieldTitleRemove));
        buttonRemoveBookById.addActionListener(e -> handleRemoveBookById(textFieldRemoveById));
        buttonCheckOutBookByBarcode.addActionListener(e -> handleCheckOutBookByBarcode(textFieldBarcodeCheckOut));
        buttonCheckOutBookById.addActionListener(e -> handleCheckOutBookById(textFieldIdCheckOut));
        buttonCheckOutBookByTitle.addActionListener(e -> handleCheckOutBookByTitle(textFieldTitleCheckOut));
        buttonCheckInBookByBarcode.addActionListener(e -> handleCheckInBookByBarcode(textFieldBarcodeCheckIn));
        buttonCheckInBookById.addActionListener(e -> handleCheckInBookById(textFieldCheckInById));
        buttonCheckInBookByTitle.addActionListener(e -> handleCheckInBookByTitle(textFieldTitleCheckIn));
        buttonListBooks.addActionListener(e -> handleListBooks());
        buttonExit.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    /**
     * Creates a JButton with specified text and background color.
     *
     * @param text  The text to display on the button.
     * @param color The background color of the button.
     * @return The created JButton.
     */
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    /**
     * Adds a component to a JPanel with specified constraints.
     *
     * @param panel     The JPanel to add the component to.
     * @param gbc       The GridBagConstraints for positioning the component.
     * @param component The component to add.
     * @param x         The grid x position.
     * @param y         The grid y position.
     */
    private void addComponentToPanel(JPanel panel, GridBagConstraints gbc, Component component, int x, int y) {
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(component, gbc);
    }

    /**
     * Adds a component to a JPanel with specified constraints, width, and height.
     *
     * @param panel     The JPanel to add the component to.
     * @param gbc       The GridBagConstraints for positioning the component.
     * @param component The component to add.
     * @param x         The grid x position.
     * @param y         The grid y position.
     * @param width     The grid width of the component.
     * @param height    The grid height of the component.
     */
    private void addComponentToPanel(JPanel panel, GridBagConstraints gbc, Component component, int x, int y, int width, int height) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        panel.add(component, gbc);
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
    }

    /**
     * Handles the action of adding books from a file.
     *
     * @param textFieldFile The JTextField containing the file path.
     */
    private void handleAddBooks(JTextField textFieldFile) {
        String filePath = textFieldFile.getText().trim();
        try {
            libraryManager.addBookFromFile(filePath);
            textAreaBooks.setText("Books added successfully from file: " + filePath + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error adding books from file: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of removing a book by barcode.
     *
     * @param textFieldBarcode The JTextField containing the barcode.
     */
    private void handleRemoveBookByBarcode(JTextField textFieldBarcode) {
        String barcode = textFieldBarcode.getText().trim();
        try {
            libraryManager.removeBookByBarcode(Integer.parseInt(barcode));
            textAreaBooks.setText("Book removed successfully with barcode: " + barcode + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error removing book with barcode: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of removing a book by title.
     *
     * @param textFieldTitle The JTextField containing the title.
     */
    private void handleRemoveBookByTitle(JTextField textFieldTitle) {
        String title = textFieldTitle.getText().trim();
        try {
            libraryManager.removeBookByTitle(title);
            textAreaBooks.setText("Book removed successfully with title: " + title + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error removing book with title: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of removing a book by ID.
     *
     * @param textFieldId The JTextField containing the ID.
     */
    private void handleRemoveBookById(JTextField textFieldId) {
        String id = textFieldId.getText().trim();
        try {
            libraryManager.removeBookById(Integer.parseInt(id));
            textAreaBooks.setText("Book removed successfully with ID: " + id + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error removing book with ID: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking out a book by barcode.
     *
     * @param textFieldBarcode The JTextField containing the barcode.
     */
    private void handleCheckOutBookByBarcode(JTextField textFieldBarcode) {
        String barcode = textFieldBarcode.getText().trim();
        try {
            libraryManager.checkOutBookByBarcode(Integer.parseInt(barcode));
            textAreaBooks.setText("Book checked out successfully with barcode: " + barcode + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking out book with barcode: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking out a book by ID.
     *
     * @param textFieldId The JTextField containing the ID.
     */
    private void handleCheckOutBookById(JTextField textFieldId) {
        String id = textFieldId.getText().trim();
        try {
            libraryManager.checkOutBookById(Integer.parseInt(id));
            textAreaBooks.setText("Book checked out successfully with ID: " + id + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking out book with ID: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking out a book by title.
     *
     * @param textFieldTitle The JTextField containing the title.
     */
    private void handleCheckOutBookByTitle(JTextField textFieldTitle) {
        String title = textFieldTitle.getText().trim();
        try {
            libraryManager.checkOutBookByTitle(title);
            textAreaBooks.setText("Book checked out successfully with title: " + title + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking out book with title: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking in a book by barcode.
     *
     * @param textFieldBarcode The JTextField containing the barcode.
     */
    private void handleCheckInBookByBarcode(JTextField textFieldBarcode) {
        String barcode = textFieldBarcode.getText().trim();
        try {
            libraryManager.checkInBookByBarcode(Integer.parseInt(barcode));
            textAreaBooks.setText("Book checked in successfully with barcode: " + barcode + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking in book with barcode: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking in a book by ID.
     *
     * @param textFieldId The JTextField containing the ID.
     */
    private void handleCheckInBookById(JTextField textFieldId) {
        String id = textFieldId.getText().trim();
        try {
            libraryManager.checkInBookById(Integer.parseInt(id));
            textAreaBooks.setText("Book checked in successfully with ID: " + id + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking in book with ID: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of checking in a book by title.
     *
     * @param textFieldTitle The JTextField containing the title.
     */
    private void handleCheckInBookByTitle(JTextField textFieldTitle) {
        String title = textFieldTitle.getText().trim();
        try {
            libraryManager.checkInBookByTitle(title);
            textAreaBooks.setText("Book checked in successfully with title: " + title + "\n");
        } catch (Exception e) {
            textAreaBooks.setText("Error checking in book with title: " + e.getMessage() + "\n");
        }
    }

    /**
     * Handles the action of listing all books in the library.
     */
    private void handleListBooks() {
        try {
            List<Book> books = libraryManager.listAllBooks();
            StringBuilder sb = new StringBuilder();
            for (Book book : books) {
                sb.append(book.toString()).append("\n");
            }
            textAreaBooks.setText(sb.toString());
        } catch (Exception e) {
            textAreaBooks.setText("Error listing books: " + e.getMessage() + "\n");
        }
    }

    /**
     * The main method to run the Library Management System application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryManagementSystem::new);
    }
}
