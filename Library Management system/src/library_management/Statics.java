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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class Statics extends JFrame {

    private Container c;
    private JDateChooser calender;
    private ImageIcon icon, searchIcon;
    private JLabel head, head1, issueDate, search4, IssueTotal, returnTotal;
    private JTable table, table1;
    private JScrollPane scroll, scroll1;
    private Font f, f1, f2, f3;
    private Date date;
    private Connection con6;
    private DefaultTableModel model, model1;
    private PreparedStatement insert6;
    private int count = 0;
    private int count1 = 0;
    private String[] cols = {"Book ID", "Student ID", "Issue Date", "Return Date"};

    private String[] cols1 = {"Book ID", "Student ID", "Issue Date", "Return Date"};

    Statics() {
        initComponents();
        cActionListener();
        table_update1();
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
        setTitle("Statics                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public void initComponents() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.PLAIN, 18);
        f1 = new Font("Algerian", Font.BOLD, 26);
        f2 = new Font("Bernard MT Condensed", Font.PLAIN, 23);
        f3 = new Font("Arial", Font.PLAIN, 18);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);

        search4 = new JLabel(searchIcon);
        search4.setBounds(320, 15, 30, 30);
        search4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search4);

        IssueTotal = new JLabel();
        IssueTotal.setBounds(1000, 70, 250, 30);
        IssueTotal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        IssueTotal.setFont(f3);
        c.add(IssueTotal);
        returnTotal = new JLabel();
        returnTotal.setBounds(987, 390, 250, 30);
        returnTotal.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnTotal.setFont(f3);
        c.add(returnTotal);

        date = new Date();
        issueDate = new JLabel("Date: ");
        issueDate.setBounds(50, 10, 70, 30);
        issueDate.setFont(f2);
        c.add(issueDate);

        calender = new JDateChooser();
        calender.setBounds(100, 10, 200, 35);
        calender.setDate(date);
        calender.setDateFormatString("dd/MM/yyyy");
        calender.setFont(f);
        c.add(calender);

        //heading
        head = new JLabel("Issue Book");
        head.setBounds(550, 50, 97, 30);
        head.setFont(f2);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        c.add(head);
        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);
        table = new JTable(model);
        table.setModel(model);
        table.setEnabled(false);
        table.setRowHeight(30);
        table.setFont(f);
        scroll = new JScrollPane(table);
        scroll.setBounds(50, 100, 1157, 260);

        c.add(scroll);

        //return book
        head1 = new JLabel("Return Book");
        head1.setBounds(545, 369, 110, 30);
        head1.setFont(f2);
        head1.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.black));
        c.add(head1);
        model1 = new DefaultTableModel();
        model1.setColumnIdentifiers(cols1);
        table1 = new JTable(model1);
        table1.setEnabled(false);
        table1.setModel(model1);
        table1.setRowHeight(30);
        table1.setFont(f);
        scroll1 = new JScrollPane(table1);
        scroll1.setBounds(50, 420, 1157, 260);

        c.add(scroll1);

    }

    private void table_update1() {

        for (int i = count1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }

        Date date = calender.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String issue_date = dateFormat.format(date);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con6 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
            insert6 = con6.prepareStatement("SELECT * FROM `return_book` WHERE `Return_Date` LIKE '%" + issue_date + "%'");
            ResultSet rs = insert6.executeQuery();

            while (rs.next()) {
                String bookId = rs.getString("Book_ID");
                String stuId = rs.getString("Student_ID");
                String issueDate = rs.getString("Issue_Date");
                String returnDate = rs.getString("Return_Date");

                model1.addRow(new Object[]{bookId, stuId, issueDate, returnDate});
            }
            count1 = model1.getRowCount();
            returnTotal.setText("Today's Return Books : " + count1);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Statics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void table_update() {
        for (int i = count - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        Date date = calender.getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String issue_date = dateFormat.format(date);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con6 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
            insert6 = con6.prepareStatement("SELECT * FROM `issue_book` WHERE `Issue_Date` LIKE '%" + issue_date + "%'");
            ResultSet rs = insert6.executeQuery();

            while (rs.next()) {
                String bookId = rs.getString("Book_ID");
                String stuId = rs.getString("Student_ID");
                String issueDate = rs.getString("Issue_Date");
                String returnDate = rs.getString("Return_Date");

                model.addRow(new Object[]{bookId, stuId, issueDate, returnDate});
            }
            count = model.getRowCount();
            IssueTotal.setText("Today's Issue Books : " + count);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Statics.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Statics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cActionListener() {
        search4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                table_update();
                table_update1();

            }
        });
    }

    public static void main(String[] args) {
        Statics frame = new Statics();
        frame.createFram();
    }
}
