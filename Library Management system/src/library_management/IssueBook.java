package library_management;

import com.toedter.calendar.JDateChooser;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class IssueBook extends JFrame {

    private Container c;
    private JLabel head, BookID, id, issueDate, dueDate, search4, search3, bookname, studentDetail;
    private JTextField BookID1, id1, issueDate1, dueDate1;
    private JButton bt, bt2, clear;
    private Font f, f1, f2, f3, f4, f5;
    private ImageIcon icon, searchIcon;
    private JTable table, table2;
    private Date date;
    private String currentDate;
    private JDateChooser calender;
    private JScrollPane scroll, scroll2;
    private DefaultTableModel model3, model4;
    private Connection con4, con8;
    private PreparedStatement insert4, insert8, insert12;
    private String[] cols = {"Book ID", "Title", "Author", "Available Copy"};
    private String[] cols1 = {"Student ID", "Name", "Department"};

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

        setSize(700, 650);

        setTitle("Issue Book                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
        setLocationRelativeTo(null);

    }

    IssueBook() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.BOLD, 22);
        f4 = new Font("Arial", Font.PLAIN, 14);
        f2 = new Font("Arial", Font.PLAIN, 22);
        f3 = new Font("Arial", Font.PLAIN, 18);
        f5 = new Font("Arial", Font.BOLD, 18);
        f1 = new Font("Arial", Font.BOLD, 26);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = dateFormat.format(date);

        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        head = new JLabel("Issues Of Books");
        head.setBounds(248, 20, 204, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black));
        head.setFont(f1);
        c.add(head);

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

        //stuid search
        search4 = new JLabel(searchIcon);
        search4.setBounds(600, 164, 30, 30);
        search4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search4);
        //student id
        id = new JLabel("Student ID: ");
        id.setBounds(70, 160, 150, 30);
        id.setFont(f);
        c.add(id);

        id1 = new JTextField();
        id1.setBounds(190, 160, 400, 35);
        id1.setFont(f2);
        c.add(id1);

        //issue date
        issueDate = new JLabel("Issue Date: ");
        issueDate.setBounds(70, 220, 150, 30);
        issueDate.setFont(f);
        c.add(issueDate);

        issueDate1 = new JTextField(currentDate);
        issueDate1.setBounds(190, 220, 400, 35);
        issueDate1.setEditable(false);
        issueDate1.setBackground(Color.WHITE);
        issueDate1.setFont(f2);
        c.add(issueDate1);

        //due date
        dueDate = new JLabel("Due Date: ");
        dueDate.setBounds(70, 280, 150, 30);
        dueDate.setFont(f);
        c.add(dueDate);
        calender = new JDateChooser();
        calender.setBounds(190, 280, 400, 35);
        calender.setDateFormatString("dd/MM/yyyy");
        calender.setFont(f2);
        c.add(calender);

        //table
        model3 = new DefaultTableModel();
        model3.setColumnIdentifiers(cols);
        table = new JTable(model3);
        table.setEnabled(false);
        table.setModel(model3);
        table.setRowHeight(30);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(90);
        table.setFont(f4);
        table.setBackground(new Color(235, 245, 251));
        scroll = new JScrollPane(table);
        scroll.setBounds(15, 400, 328, 100);

        c.add(scroll);
        //table2
        model4 = new DefaultTableModel();
        model4.setColumnIdentifiers(cols1);
        table2 = new JTable(model4);
        table2.setEnabled(false);
        table2.setModel(model4);

        table2.setRowHeight(30);
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table2.getColumnModel().getColumn(0).setPreferredWidth(90);
        table2.getColumnModel().getColumn(1).setPreferredWidth(160);
        table2.getColumnModel().getColumn(2).setPreferredWidth(90);

        table2.setFont(f4);
        table2.setBackground(new Color(235, 245, 251));
        scroll2 = new JScrollPane(table2);
        scroll2.setBounds(348, 400, 328, 100);

        c.add(scroll2);
        //book name
        bookname = new JLabel("Book Details");
        bookname.setBounds(120, 350, 110, 30);
        bookname.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        bookname.setFont(f5);
        c.add(bookname);

        studentDetail = new JLabel("Student Details");
        studentDetail.setBounds(450, 350, 135, 30);
        studentDetail.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        studentDetail.setFont(f5);
        c.add(studentDetail);

        bt = new JButton("Submit");
        bt.setBackground(new Color(51, 255, 87));
        bt.setBounds(150, 550, 90, 30);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt.setForeground(Color.white);
        bt.setFont(f3);
        c.add(bt);

        clear = new JButton("Clear");
        clear.setBackground(new Color(51, 255, 189));
        clear.setBounds(300, 550, 90, 30);
        clear.setForeground(Color.white);
        clear.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        clear.setFont(f3);
        c.add(clear);

        bt2 = new JButton("Exit");
        bt2.setBackground(new Color(250, 128, 114));
        bt2.setBounds(450, 550, 90, 30);
        bt2.setForeground(Color.white);
        bt2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt2.setFont(f3);
        c.add(bt2);

    }

    public void actionListener() {

        bt2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String bookid = BookID1.getText();

                String stuid = id1.getText();

                String issue_date = issueDate1.getText();
                Date date = calender.getDate();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String return_date = dateFormat.format(date);

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con4 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert4 = con4.prepareStatement("insert into issue_book(Book_ID,Student_ID,Issue_Date,Return_Date)values(?,?,?,?)");
                    insert12 = con4.prepareStatement("insert into live_issue_book(Book_ID,Student_ID,Issue_Date,Return_Date)values(?,?,?,?)");
                    insert4.setString(1, bookid);
                    insert4.setString(2, stuid);
                    insert4.setString(3, issue_date);
                    insert4.setString(4, return_date);

                    insert4.executeUpdate();

                    insert12.setString(1, bookid);
                    insert12.setString(2, stuid);
                    insert12.setString(3, issue_date);
                    insert12.setString(4, return_date);

                    insert12.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Addeddd");

                    BookID1.setText("");
                    id1.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String id = BookID1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `book_list` WHERE `Book_ID` LIKE '%" + id + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {
                        String bookId = rs.getString("Book_ID");
                        String title = rs.getString("Book_Title");
                        String author = rs.getString("Author_Name");
                        String Copy = rs.getString("Copy");
                        model3.addRow(new Object[]{bookId, title, author, Copy});

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String stuid = id1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `student_registration` WHERE `Student_ID` LIKE '%" + stuid + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {
                        String studentId = rs.getString("Student_ID");
                        String name = rs.getString("Full_Name");
                        String dept = rs.getString("Department");

                        model4.addRow(new Object[]{studentId, name, dept});

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model3.removeRow(0);
                model4.removeRow(0);
                BookID1.setText("");
                id1.setText("");
            }
        });
    }

    public static void main(String[] args) {
        IssueBook frame = new IssueBook();
        frame.createFrame();
    }
}
