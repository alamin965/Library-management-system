package library_management;

import com.toedter.components.JSpinField;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class AddBook extends JFrame {

    private Container c;
    private JLabel head, search3, BookID, Title, Author, Edition, category, bookPrice, bookcopy;
    private JTextField BookID1, Title1, Author1, Edition1, category1, bookPrice1, bookcopy1;

    private JButton bt, bt2, update, delete;
    private Font f, f1, f2, f3;
    private ImageIcon icon, searchIcon;
    private Connection con2, con30, con32;
    private JComboBox dbox;
    private PreparedStatement insert2, insert15, insert30, insert31, insert32;
    private JSpinField spin;
    private String[] cate = {"Civil Engineering", "English", "Computer Science", "Electrical Engineering", "Novel", "Mathematics", "Philosophy", "Business Administration", "Poetry", "Action And adventure", "History", "Comic Book", "Drama", "Graphic novel", "Mystery"};

    public void createFrame() {
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(new Color(21, 67, 96)));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        JFrame.setDefaultLookAndFeelDecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(700, 620);

        setTitle("New Book                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
        setLocationRelativeTo(null);

    }

    AddBook() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.BOLD, 22);
        f2 = new Font("Arial", Font.PLAIN, 22);
        f3 = new Font("Arial", Font.PLAIN, 18);
        f1 = new Font("Arial", Font.BOLD, 26);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        head = new JLabel("New Book Registration");
        head.setBounds(205, 20, 289, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black));
        head.setFont(f1);
        c.add(head);

        //search bookid
        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search3 = new JLabel(searchIcon);
        search3.setBounds(600, 103, 30, 30);
        search3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search3);

        //book id
        BookID = new JLabel("Book ID: ");
        BookID.setBounds(70, 100, 150, 30);
        BookID.setFont(f);
        c.add(BookID);

        BookID1 = new JTextField();
        BookID1.setBounds(190, 100, 400, 35);
        BookID1.setFont(f2);
        c.add(BookID1);

        //title
        Title = new JLabel("Title: ");
        Title.setBounds(70, 150, 150, 30);
        Title.setFont(f);
        c.add(Title);

        Title1 = new JTextField();
        Title1.setBounds(190, 150, 400, 35);
        Title1.setFont(f2);
        c.add(Title1);

        //author
        Author = new JLabel("Author: ");
        Author.setBounds(70, 200, 150, 30);
        Author.setFont(f);
        c.add(Author);

        Author1 = new JTextField();
        Author1.setBounds(190, 200, 400, 35);
        Author1.setFont(f2);
        c.add(Author1);

        //edition
        Edition = new JLabel("Edition: ");
        Edition.setBounds(70, 250, 150, 30);
        Edition.setFont(f);
        c.add(Edition);

        Edition1 = new JTextField();
        Edition1.setBounds(190, 250, 400, 35);
        Edition1.setFont(f2);
        c.add(Edition1);

        //category
        category = new JLabel("Category: ");
        category.setBounds(70, 300, 150, 30);
        category.setFont(f);
        c.add(category);

        dbox = new JComboBox(cate);
        dbox.setBounds(190, 300, 400, 35);
        dbox.setBackground(Color.WHITE);
        dbox.setFont(f3);
        dbox.setEditable(true);
        c.add(dbox);

        //book price
        bookPrice = new JLabel("Book Price: ");
        bookPrice.setBounds(70, 350, 150, 30);
        bookPrice.setFont(f);
        c.add(bookPrice);

        bookPrice1 = new JTextField();
        bookPrice1.setBounds(190, 350, 400, 35);
        bookPrice1.setFont(f2);
        c.add(bookPrice1);

        //book copy
        bookcopy = new JLabel("Book Copy: ");
        bookcopy.setBounds(70, 400, 150, 30);
        bookcopy.setFont(f);
        c.add(bookcopy);

        spin = new JSpinField();
        spin.setBounds(190, 400, 400, 35);
        spin.setHorizontalAlignment(SwingConstants.LEFT);
        spin.setFont(f2);
        c.add(spin);

        bt = new JButton("Submit");
        bt.setBackground(new Color(24, 106, 59));
        bt.setBounds(100, 510, 90, 30);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt.setForeground(Color.white);
        bt.setFont(f3);
        c.add(bt);
        update = new JButton("Update");
        update.setBackground(new Color(40, 116, 166));
        update.setBounds(240, 510, 90, 30);
        update.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        update.setForeground(Color.white);
        update.setFont(f3);
        c.add(update);
        delete = new JButton("Delete");
        delete.setBackground(new Color(250, 128, 114));
        delete.setBounds(380, 510, 90, 30);
        delete.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        delete.setForeground(Color.white);
        delete.setFont(f3);
        c.add(delete);

        bt2 = new JButton("Clear");
        bt2.setBackground(new Color(51, 255, 189));
        bt2.setBounds(520, 510, 90, 30);
        bt2.setForeground(Color.white);
        bt2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt2.setFont(f3);
        c.add(bt2);

    }

    public void actionListener() {

        bt2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                BookID1.setText("");
                Title1.setText("");

                Author1.setText("");
                Edition1.setText("");
                dbox.setSelectedItem("");
                bookPrice1.setText("");
                spin.setValue(0);
            }
        });

        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String Tbookid = BookID1.getText();
                String Ttitle = Title1.getText();
                String Tauthor = Author1.getText();
                String Tedition = Edition1.getText();
                String Tcategory = dbox.getSelectedItem().toString();
                String TbookPrice = bookPrice1.getText();
                String bookCopy = String.valueOf(spin.getValue());

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con2 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert2 = con2.prepareStatement("insert into book_list(Book_ID,Book_Title,Author_Name,Edition,Book_Category,Price,Copy)values(?,?,?,?,?,?,?)");
                    insert15 = con2.prepareStatement("insert into book_list2(Book_ID,Book_Title,Author_Name,Edition,Book_Category,Price,Copy)values(?,?,?,?,?,?,?)");
                    insert2.setString(1, Tbookid);
                    insert2.setString(2, Ttitle);
                    insert2.setString(3, Tauthor);
                    insert2.setString(4, Tedition);
                    insert2.setString(5, Tcategory);
                    insert2.setString(6, TbookPrice);
                    insert2.setString(7, bookCopy);
                    insert2.executeUpdate();

                    insert15.setString(1, Tbookid);
                    insert15.setString(2, Ttitle);
                    insert15.setString(3, Tauthor);
                    insert15.setString(4, Tedition);
                    insert15.setString(5, Tcategory);
                    insert15.setString(6, TbookPrice);
                    insert15.setString(7, bookCopy);
                    insert15.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Addeddd");

                    BookID1.setText("");
                    Title1.setText("");
                    Author1.setText("");
                    Edition1.setText("");

                    bookPrice1.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String bookid = BookID1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con32 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert32 = con32.prepareStatement("SELECT * FROM `book_list` WHERE `Book_ID` LIKE '%" + bookid + "%'");
                    ResultSet rs = insert32.executeQuery();
                    if (rs.next()) {

                        String book = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String edition = rs.getString("Edition");
                        String category = rs.getString("Book_Category");
                        String price = rs.getString("Price");
                        String copy = rs.getString("Copy");
                        int c = Integer.parseInt(copy);
                        Title1.setText(book);
                        Author1.setText(author);
                        Edition1.setText(edition);
                        dbox.setSelectedItem(category);
                        bookPrice1.setText(price);
                        spin.setValue(c);

                    } else {
                        JOptionPane.showMessageDialog(null, "Recored Not Found");
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        delete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String bookid = BookID1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con30 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert30 = con30.prepareStatement("DELETE FROM book_list WHERE Book_ID='" + bookid + "'");

                    insert31 = con30.prepareStatement("DELETE FROM book_list2 WHERE Book_ID='" + bookid + "'");
                    insert30.executeUpdate();
                    insert31.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete successfully");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String Tbookid = BookID1.getText();
                String Ttitle = Title1.getText();
                String Tauthor = Author1.getText();
                String Tedition = Edition1.getText();
                String Tcategory = dbox.getSelectedItem().toString();
                String TbookPrice = bookPrice1.getText();
                String bookCopy = String.valueOf(spin.getValue());

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con2 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert2 = con2.prepareStatement("UPDATE book_list SET Book_Title='" + Ttitle + "',Author_Name='" + Tauthor + "',Edition='" + Tedition + "',Book_Category='" + Tcategory + "',Price='" + TbookPrice + "',Copy='" + bookCopy + "' WHERE Book_ID='" + Tbookid + "';");
                    insert2.executeUpdate();
                    insert15 = con2.prepareStatement("UPDATE book_list2 SET Book_Title='" + Ttitle + "',Author_Name='" + Tauthor + "',Edition='" + Tedition + "',Book_Category='" + Tcategory + "',Price='" + TbookPrice + "',Copy='" + bookCopy + "' WHERE Book_ID='" + Tbookid + "';");
                    insert15.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Updated");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        AddBook frame = new AddBook();
        frame.createFrame();
    }
}
