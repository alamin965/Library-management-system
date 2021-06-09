package library_management;

import java.awt.Color;
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class StudentsList extends JFrame {

    private Container c;
    private ImageIcon icon, searchIcon;
    private JLabel head, head1, head2, head3, head4, search, title, author;
    private JTextField searchBox, titlebox, authorBox;
    private JTable table, table1;
    private JScrollPane scroll, scroll1;
    private Font f, f1;
    private JComboBox dbox;
    private Connection con5, con8;
    private DefaultTableModel model, model1;
    private PreparedStatement insert5, insert8;
    private int count = 0;
    private String[] dept = {"CSE", "EEE", "BBA", "CIVIL", "NFE", "SWE", "Textile"};
    private String[] cols = {"Student ID", "Full Name", "Email", "Mobile No.", "Department", "Gender"};
    private String[] cols1 = {"Student ID", "Full Name", "Email", "Mobile No.", "Department", "Gender"};

    StudentsList() {
        initComponents();
        createAction();
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
        setTitle("Student Details                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public void initComponents() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Bell MT", Font.PLAIN, 22);
        f1 = new Font("Bernard MT Condensed", Font.PLAIN, 23);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));// get image
        this.setIconImage(icon.getImage());

        head3 = new JLabel("Student List");
        head3.setBounds(550, 250, 100, 30);
        head3.setFont(f1);
        head3.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));
        c.add(head3);
        head3 = new JLabel("Search Result");
        head3.setBounds(540, 70, 120, 30);
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
        table1.getColumnModel().getColumn(0).setPreferredWidth(180);
        table1.getColumnModel().getColumn(1).setPreferredWidth(300);
        table1.getColumnModel().getColumn(2).setPreferredWidth(320);
        table1.getColumnModel().getColumn(3).setPreferredWidth(150);
        table1.getColumnModel().getColumn(4).setPreferredWidth(110);
        table1.getColumnModel().getColumn(5).setPreferredWidth(100);

        scroll1 = new JScrollPane(table1);
        scroll1.setBounds(50, 110, 1157, 120);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        c.add(scroll1);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(cols);

        table = new JTable(model);
        table.setEnabled(false);
        table.setRowHeight(25);
        table.setFont(f);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(180);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(320);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(4).setPreferredWidth(110);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);

        scroll = new JScrollPane(table);
        scroll.setBounds(50, 290, 1157, 390);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        c.add(scroll);

        //heading
        head = new JLabel("Student ID:");
        head.setBounds(60, 20, 100, 30);
        head.setFont(f1);
        c.add(head);

        searchBox = new JTextField();
        searchBox.setBounds(160, 20, 200, 33);
        searchBox.setFont(f);
        c.add(searchBox);

        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search = new JLabel(searchIcon);
        search.setBounds(370, 20, 40, 40);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search);

        head1 = new JLabel("Name:");
        head1.setBounds(473, 20, 100, 30);
        head1.setFont(f1);
        c.add(head1);

        titlebox = new JTextField();
        titlebox.setBounds(537, 20, 200, 33);
        titlebox.setFont(f);
        c.add(titlebox);
        searchIcon = new ImageIcon(temp_img);
        title = new JLabel(searchIcon);
        title.setBounds(745, 20, 40, 40);
        title.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(title);

        head2 = new JLabel("Department:");
        head2.setBounds(830, 20, 140, 30);
        head2.setFont(f1);
        c.add(head2);

        dbox = new JComboBox(dept);
        dbox.setBounds(950, 20, 200, 33);
        dbox.setBackground(Color.WHITE);
        dbox.setFont(f);
        dbox.setEditable(true);
        c.add(dbox);

        searchIcon = new ImageIcon(temp_img);
        author = new JLabel(searchIcon);
        author.setBounds(1160, 20, 40, 40);
        author.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(author);

    }

    private void table_update() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con5 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
            insert5 = con5.prepareStatement("select * from student_registration");
            ResultSet rs = insert5.executeQuery();

            while (rs.next()) {
                String bookid = rs.getString("Student_ID");
                String title = rs.getString("Full_Name");
                String author = rs.getString("Email");
                String edition = rs.getString("Mobile_No");
                String Category = rs.getString("Department");
                String price = rs.getString("Gender");

                model.addRow(new Object[]{bookid, title, author, edition, Category, price});

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StudentsList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StudentsList.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createAction() {
        search.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = count - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }

                String id = searchBox.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `student_registration` WHERE `Student_ID` LIKE '%" + id + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Student_ID");
                        String title = rs.getString("Full_Name");
                        String author = rs.getString("Email");
                        String edition = rs.getString("Mobile_No");
                        String Category = rs.getString("Department");
                        String price = rs.getString("Gender");

                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price});

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
                String id = titlebox.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `student_registration` WHERE `Full_Name` LIKE '%" + id + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Student_ID");
                        String title = rs.getString("Full_Name");
                        String author = rs.getString("Email");
                        String edition = rs.getString("Mobile_No");
                        String Category = rs.getString("Department");
                        String price = rs.getString("Gender");

                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price});

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

                String depart = dbox.getSelectedItem().toString();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `student_registration` WHERE `Department` LIKE '%" + depart + "%'");
                    ResultSet rs = insert8.executeQuery();
                    while (rs.next()) {

                        String bookid = rs.getString("Student_ID");
                        String title = rs.getString("Full_Name");
                        String author = rs.getString("Email");
                        String edition = rs.getString("Mobile_No");
                        String Category = rs.getString("Department");
                        String price = rs.getString("Gender");

                        model1.addRow(new Object[]{bookid, title, author, edition, Category, price});

                    }
                    count = model1.getRowCount();
                    dbox.setSelectedItem("");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(IssueBook.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public static void main(String[] args) {
        StudentsList frame = new StudentsList();
        frame.createFram();
    }
}
