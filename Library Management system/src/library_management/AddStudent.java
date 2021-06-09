package library_management;
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
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class AddStudent extends JFrame {

    private Container c;
    private JLabel search3, head, student, email, mobile, dp, id, gender;
    private JTextField student1, email1, mobile1, dp1, id1, gender1;
    private JRadioButton male, female, others;
    private ButtonGroup grp;
    private JButton bt, bt2, update, delete;
    private Font f, f1, f2, f3;
    private ImageIcon icon, searchIcon;
    private String genderName;
    private JComboBox dbox;
    private Connection con3, con8, con30;
    private PreparedStatement insert3, insert8, insert30;
    private String[] dept = {"CSE", "EEE", "BBA", "CIVIL", "NFE", "SWE", "Textile"};

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

        setTitle("New Student                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
        setLocationRelativeTo(null);

    }

    AddStudent() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.BOLD, 20);
        f2 = new Font("Arial", Font.PLAIN, 20);
        f3 = new Font("Arial", Font.PLAIN, 18);
        f1 = new Font("Arial", Font.BOLD, 24);
        grp = new ButtonGroup();
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        head = new JLabel("New Student Registration");
        head.setBounds(205, 20, 300, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.black));
        head.setFont(f1);
        c.add(head);
        //search student id
        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search3 = new JLabel(searchIcon);
        search3.setBounds(600, 103, 30, 30);
        search3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search3);
        //student id

        id = new JLabel("Student ID: ");
        id.setBounds(70, 100, 150, 30);
        id.setFont(f);
        c.add(id);

        id1 = new JTextField();
        id1.setBounds(190, 100, 400, 35);
        id1.setFont(f2);
        c.add(id1);

        //name
        student = new JLabel("Name: ");
        student.setBounds(70, 160, 150, 30);
        student.setFont(f);
        c.add(student);

        student1 = new JTextField();
        student1.setBounds(190, 160, 400, 35);
        student1.setFont(f2);
        c.add(student1);

        //email
        email = new JLabel("Email: ");
        email.setBounds(70, 220, 150, 30);
        email.setFont(f);
        c.add(email);

        email1 = new JTextField();
        email1.setBounds(190, 220, 400, 35);
        email1.setFont(f2);
        c.add(email1);

        //mobile
        mobile = new JLabel("Mobile: ");
        mobile.setBounds(70, 280, 150, 30);
        mobile.setFont(f);
        c.add(mobile);

        mobile1 = new JTextField();
        mobile1.setBounds(190, 280, 400, 35);
        mobile1.setFont(f2);
        c.add(mobile1);

        //department
        dp = new JLabel("Department: ");
        dp.setBounds(70, 340, 150, 30);
        dp.setFont(f);
        c.add(dp);

        dbox = new JComboBox(dept);
        dbox.setBounds(190, 340, 400, 35);
        dbox.setBackground(Color.WHITE);
        dbox.setFont(f2);
        dbox.setEditable(true);
        c.add(dbox);

        //book price
        gender = new JLabel("Gender: ");
        gender.setBounds(70, 400, 150, 30);
        gender.setFont(f);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setBounds(200, 400, 80, 30);
        male.setFont(f);
        c.add(male);
        female = new JRadioButton("Female");
        female.setBounds(320, 400, 100, 30);
        female.setFont(f);
        c.add(female);
        others = new JRadioButton("Others");
        others.setBounds(460, 400, 100, 30);
        others.setFont(f);
        c.add(others);
        grp.add(male);
        grp.add(female);
        grp.add(others);

        Handler handler = new Handler();
        male.addActionListener(handler);
        female.addActionListener(handler);
        others.addActionListener(handler);

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

    class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == male) {
                genderName = "Male";

            } else if (e.getSource() == female) {
                genderName = "Female";

            } else {
                genderName = "Others";

            }
        }

    }

    public void actionListener() {

        bt2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                id1.setText("");
                student1.setText("");
                email1.setText("");
                mobile1.setText("");
                dbox.setSelectedItem("");
            }
        });

        bt.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = student1.getText();

                String email = email1.getText();

                String mobile = mobile1.getText();

                String depart = dbox.getSelectedItem().toString();

                String ID = id1.getText();

                String gender = genderName;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con3 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert3 = con3.prepareStatement("insert into student_registration(Student_ID,Full_Name,Email,Mobile_No,Department,Gender)values(?,?,?,?,?,?)");
                    insert3.setString(1, ID);
                    insert3.setString(2, name);
                    insert3.setString(3, email);
                    insert3.setString(4, mobile);
                    insert3.setString(5, depart);
                    insert3.setString(6, gender);
                    insert3.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Addeddd");

                    student1.setText("");
                    email1.setText("");
                    mobile1.setText("");
                    id1.setText("");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        search3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String stuid = id1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con8 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert8 = con8.prepareStatement("SELECT * FROM `student_registration` WHERE `Student_ID` LIKE '%" + stuid + "%'");
                    ResultSet rs = insert8.executeQuery();
                    if (rs.next()) {

                        String name = rs.getString("Full_Name");
                        String email = rs.getString("Email");
                        String phone = rs.getString("Mobile_No");
                        String de = rs.getString("Department");
                        String ge = rs.getString("Gender");

                        student1.setText(name);
                        email1.setText(email);
                        mobile1.setText(phone);
                        dbox.setSelectedItem(de);

                        if (ge.equals("Male")) {
                            male.setSelected(true);

                        } else if (ge.equals("Female")) {
                            female.setSelected(true);

                        } else {
                            others.setSelected(true);

                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Recored Not Found");
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        delete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String stuid = id1.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con30 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert30 = con30.prepareStatement("DELETE FROM student_registration WHERE Student_ID='" + stuid + "'");
                    insert30.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Delete successfully");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name = student1.getText();

                String email = email1.getText();

                String mobile = mobile1.getText();

                String depart = dbox.getSelectedItem().toString();

                String ID = id1.getText();

                String gender = genderName;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con3 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert3 = con3.prepareStatement("UPDATE student_registration SET Full_Name='" + name + "',Email='" + email + "',Mobile_No='" + mobile + "',Department='" + depart + "',Gender='" + gender + "' WHERE Student_ID='" + ID + "';");
                    insert3.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Recored Updated");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

    }

    public static void main(String[] args) {
        AddStudent frame = new AddStudent();
        frame.createFrame();

    }

}
