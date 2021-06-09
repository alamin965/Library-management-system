package library_management;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class SearchBook extends JFrame {

    private Container c;
    private ImageIcon icon, searchIcon;
    private JLabel head, head1, head2, head3, head4, head5, search, title, author, category, totalBook;
    private JTextField searchBox, titlebox, authorBox;
    private JTable table, table1;
    private JComboBox dbox;
    private JScrollPane scroll, scroll1;
    private Font f, f1, f3;
    private int count = 0, count1 = 0;
    private Connection con5, con8;
    private DefaultTableModel model, model1;
    private PreparedStatement insert5, insert8;
    private String[] cate = {"Civil Engineering", "English", "Computer Science", "Electrical Engineering", "Novel", "Mathematics", "Philosophy", "Business Administration", "Poetry", "Action And adventure", "History", "Comic Book", "Drama", "Graphic novel", "Mystery"};
    private String[] cols = {"Book ID", "Title", "Author", "Edition", "Category", "Book Price", "Copy"};
    private String[] cols1 = {"Book ID", "Title", "Author", "Edition", "Category", "Book Price", "Copy"};

    SearchBook() {
        initComponents();
        createActionListener();
        table_update();
    }

    public void createFram() {

        UIDefaults uiDefaults = UIManager.getDefaults();

        if (uiDefaults.get("Table.alternateRowColor") == null) {
            uiDefaults.put("Table.alternateRowColor", new Color(235, 245, 251));
        }
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(new Color(21, 67, 96)));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        JFrame.setDefaultLookAndFeelDecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(2);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1266, 720);
        setTitle("Search Book                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public void initComponents() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Bell MT", Font.PLAIN, 20);
        f1 = new Font("Bernard MT Condensed", Font.PLAIN, 21);
        f3 = new Font("Arial", Font.PLAIN, 18);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));// get image
        this.setIconImage(icon.getImage());

        head3 = new JLabel("Book List");
        head3.setBounds(595, 250, 75, 30);
        head3.setFont(f1);
        head3.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
        c.add(head3);
        head3 = new JLabel("Search Result");
        head3.setBounds(580, 57, 105, 30);
        head3.setFont(f1);
        head3.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
        c.add(head3);

        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(cols1);

        table1 = new JTable(model1);
        table1.setEnabled(false);
        table1.setRowHeight(25);
        table1.setFont(f);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table1.getColumnModel().getColumn(0).setPreferredWidth(80);
        table1.getColumnModel().getColumn(1).setPreferredWidth(340);
        table1.getColumnModel().getColumn(2).setPreferredWidth(310);
        table1.getColumnModel().getColumn(3).setPreferredWidth(70);
        table1.getColumnModel().getColumn(4).setPreferredWidth(220);
        table1.getColumnModel().getColumn(5).setPreferredWidth(65);
        table1.getColumnModel().getColumn(6).setPreferredWidth(69);
        scroll1 = new JScrollPane(table1);
        scroll1.setBounds(50, 95, 1157, 150);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        c.add(scroll1);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        table = new JTable(model);
        table.setEnabled(false);
        table.setRowHeight(25);
        table.setFont(f);

        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(340);
        table.getColumnModel().getColumn(2).setPreferredWidth(310);
        table.getColumnModel().getColumn(3).setPreferredWidth(70);
        table.getColumnModel().getColumn(4).setPreferredWidth(220);
        table.getColumnModel().getColumn(5).setPreferredWidth(65);
        table.getColumnModel().getColumn(6).setPreferredWidth(69);

        scroll = new JScrollPane(table);
        scroll.setBounds(50, 290, 1157, 390);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        c.add(scroll);

        //heading
        head = new JLabel("Book ID:");
        head.setBounds(50, 10, 100, 30);
        head.setFont(f1);
        c.add(head);

        searchBox = new JTextField();
        searchBox.setBounds(130, 10, 150, 33);
        searchBox.setFont(f);
        c.add(searchBox);

        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search = new JLabel(searchIcon);
        search.setBounds(285, 10, 40, 40);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search);

        head1 = new JLabel("Title:");
        head1.setBounds(335, 10, 100, 30);
        head1.setFont(f1);
        c.add(head1);

        titlebox = new JTextField();
        titlebox.setBounds(385, 10, 150, 33);
        titlebox.setFont(f);
        c.add(titlebox);
        searchIcon = new ImageIcon(temp_img);
        title = new JLabel(searchIcon);
        title.setBounds(540, 10, 40, 40);
        title.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(title);

        head2 = new JLabel("Author:");
        head2.setBounds(600, 10, 100, 30);
        head2.setFont(f1);
        c.add(head2);

        authorBox = new JTextField();
        authorBox.setBounds(670, 10, 150, 33);
        authorBox.setFont(f);
        c.add(authorBox);
        searchIcon = new ImageIcon(temp_img);
        author = new JLabel(searchIcon);
        author.setBounds(825, 10, 40, 40);
        author.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(author);

        head5 = new JLabel("Category:");
        head5.setBounds(880, 10, 100, 30);
        head5.setFont(f1);
        c.add(head5);

        dbox = new JComboBox(cate);
        dbox.setBounds(970, 10, 220, 33);
        dbox.setBackground(Color.WHITE);
        dbox.setFont(f);
        dbox.setEditable(true);
        c.add(dbox);

        category = new JLabel(searchIcon);
        category.setBounds(1200, 10, 40, 40);
        category.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(category);

        //total book count
        totalBook = new JLabel();
        totalBook.setBounds(1070, 263, 250, 30);
        totalBook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        totalBook.setFont(f3);
        c.add(totalBook);

    }

    private void table_update() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con5 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
            insert5 = con5.prepareStatement("select * from book_list");
            ResultSet rs = insert5.executeQuery();

            while (rs.next()) {
                String bookid = rs.getString("Book_ID");
                String title = rs.getString("Book_Title");
                String author = rs.getString("Author_Name");
                String edition = rs.getString("Edition");
                String Category = rs.getString("Book_Category");
                String price = rs.getString("Price");
                String copy = rs.getString("Copy");
                model.addRow(new Object[]{bookid, title, author, edition, Category, price, copy});
                count1 = model.getRowCount();
                totalBook.setText("Total Books : " + count1);

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SearchBook.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createActionListener() {
        search.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = count - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }

                String id = searchBox.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `book_list` WHERE `Book_ID` LIKE '%" + id + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Book_ID");
                        String title = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String edition = rs.getString("Edition");
                        String Category = rs.getString("Book_Category");
                        String price = rs.getString("Price");
                        String copy = rs.getString("Copy");
                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price, copy});

                    }
                    count = model1.getRowCount();
                    searchBox.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        title.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = count - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }

                String title1 = titlebox.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `book_list` WHERE `Book_Title` LIKE '%" + title1 + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Book_ID");
                        String title = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String edition = rs.getString("Edition");
                        String Category = rs.getString("Book_Category");
                        String price = rs.getString("Price");
                        String copy = rs.getString("Copy");
                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price, copy});

                    }
                    count = model1.getRowCount();
                    titlebox.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        author.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = count - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }

                String title1 = authorBox.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `book_list` WHERE `Author_Name` LIKE '%" + title1 + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Book_ID");
                        String title = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String edition = rs.getString("Edition");
                        String Category = rs.getString("Book_Category");
                        String price = rs.getString("Price");
                        String copy = rs.getString("Copy");
                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price, copy});

                    }
                    count = model1.getRowCount();
                    authorBox.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        category.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = count - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }

                String Tcategory = dbox.getSelectedItem().toString();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `book_list` WHERE `Book_Category` LIKE '%" + Tcategory + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Book_ID");
                        String title = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String edition = rs.getString("Edition");
                        String Category = rs.getString("Book_Category");
                        String price = rs.getString("Price");
                        String copy = rs.getString("Copy");
                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price, copy});

                    }
                    count = model1.getRowCount();

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public static void main(String[] args) {
        SearchBook frame = new SearchBook();
        frame.createFram();
    }
}
