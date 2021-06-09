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

public class ReturnBook extends JFrame {

    private Container c;
    private JLabel head, BookID, id, issueDate, dueDate, search, bookname;
    private JTextField BookID1, id1, issueDate1, dueDate1;
    private Date date;
    private String currentDate;
    private JDateChooser calender;
    private JButton bt, bt2, clear;
    private Font f, f1, f2, f3;
    private ImageIcon icon, searchIcon;
    private JTable table;
    private JScrollPane scroll;
    private Connection con7, con10, con30;
    private DefaultTableModel model;
    private PreparedStatement insert7, insert10, insert30;
    private String[] cols = {"Book ID", "Student ID", "Issue Date", "Due Date"};

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

        setTitle("Return Book                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
        setLocationRelativeTo(null);

    }

    ReturnBook() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.BOLD, 20);
        f2 = new Font("Arial", Font.PLAIN, 22);
        f3 = new Font("Arial", Font.PLAIN, 18);
        f1 = new Font("Arial", Font.BOLD, 26);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        date = new Date();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = dateFormat.format(date);

        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        head = new JLabel("Return Book");
        head.setBounds(270, 20, 160, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black));
        head.setFont(f1);
        c.add(head);

        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search = new JLabel(searchIcon);
        search.setBounds(600, 103, 30, 30);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search);

        //book id
        BookID = new JLabel("Book ID: ");
        BookID.setBounds(70, 100, 150, 30);
        BookID.setFont(f);
        c.add(BookID);

        BookID1 = new JTextField();
        BookID1.setBounds(190, 100, 400, 35);
        BookID1.setFont(f2);
        c.add(BookID1);

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
        issueDate = new JLabel("Issued Date: ");
        issueDate.setBounds(70, 220, 150, 30);
        issueDate.setFont(f);
        c.add(issueDate);

        issueDate1 = new JTextField();
        issueDate1.setBounds(190, 220, 400, 35);
        issueDate1.setFont(f2);
        issueDate1.setBackground(Color.WHITE);
        issueDate1.setEditable(false);
        c.add(issueDate1);

        //due date  
        dueDate = new JLabel("Return Date: ");
        dueDate.setBounds(70, 280, 150, 30);
        dueDate.setFont(f);
        c.add(dueDate);

        dueDate1 = new JTextField(currentDate);
        dueDate1.setBounds(190, 280, 400, 35);
        dueDate1.setFont(f2);
        dueDate1.setBackground(Color.WHITE);
        dueDate1.setEditable(false);
        c.add(dueDate1);

        /* calender=new JDateChooser();
        calender.setBounds(190, 280, 400, 35);
        calender.setDateFormatString("dd/MM/yyyy");
        calender.setFont(f2);
        c.add(calender);*/
        //table
        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table = new JTable(model);
        table.setEnabled(false);
        table.setModel(model);

        table.setRowHeight(30);

        table.setFont(f);
        table.setBackground(new Color(235, 245, 251));
        scroll = new JScrollPane(table);
        scroll.setBounds(70, 400, 550, 100);

        c.add(scroll);
        //book name
        bookname = new JLabel("Search Result");
        bookname.setBounds(262, 350, 175, 30);
        bookname.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        bookname.setFont(f1);
        c.add(bookname);

        bt = new JButton("Submit");
        bt.setBackground(new Color(21, 67, 96));
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

    public void Update(String book1) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con30 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");

            insert30 = con30.prepareStatement("DELETE FROM live_issue_book WHERE Book_ID='" + book1 + "';");
            insert30.executeUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
        }

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

                String return_date = dueDate1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con7 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert7 = con7.prepareStatement("insert into return_book(Book_ID,Student_ID,Issue_Date,Return_Date)values(?,?,?,?)");
                    insert7.setString(1, bookid);
                    insert7.setString(2, stuid);
                    insert7.setString(3, issue_date);
                    insert7.setString(4, return_date);
                    insert7.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Addeddd");
                    Update(bookid);
                    BookID1.setText("");
                    id1.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        search.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String bookid = BookID1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con10 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert10 = con10.prepareStatement("SELECT * FROM `live_issue_book` WHERE `Book_ID` LIKE '%" + bookid + "%'");
                    ResultSet rs = insert10.executeQuery();
                    while (rs.next()) {
                        String studentId = rs.getString("Book_ID");
                        String name = rs.getString("Student_ID");
                        String dept = rs.getString("Issue_Date");
                        String due = rs.getString("Return_Date");
                        model.addRow(new Object[]{studentId, name, dept, due});
                        id1.setText(name);
                        issueDate1.setText(due);

                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ReturnBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        ReturnBook frame = new ReturnBook();
        frame.createFrame();
    }
}
