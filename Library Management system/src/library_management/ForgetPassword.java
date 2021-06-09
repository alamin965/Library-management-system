package library_management;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ForgetPassword extends JFrame {

    private Container c;
    private JLabel head, name, username, pass, cpass, securityLabel, answer, search;
    private JTextField tname, tusername, ansbox, tsecurity;
    private JButton bt, bt2, update, delete;
    private JPasswordField passbox;
    private Font f, f1, f2;
    private ImageIcon icon, searchIcon;
    private Connection con;
    private PreparedStatement insert;
    private String password, SecurityAnswer;
    private JCheckBox show;

    public void createFrame() {

        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(500, 650);

        setTitle("SignUp");
        setLocationRelativeTo(null);

    }

    ForgetPassword() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();//container
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));//background color
        f = new Font("Arial", Font.PLAIN, 18);//font 
        f1 = new Font("Arial", Font.BOLD, 24);//font
        f2 = new Font("Arial", Font.BOLD, 15);//font
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());//set icon in jframe

        //heading label
        head = new JLabel("Forgot Password");
        head.setBounds(150, 70, 200, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.black));
        head.setFont(f1);
        c.add(head);

        //search icon
        searchIcon = new ImageIcon(getClass().getResource("searchIcon.png"));
        Image img = searchIcon.getImage();
        Image temp_img = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        searchIcon = new ImageIcon(temp_img);
        search = new JLabel(searchIcon);
        search.setBounds(455, 143, 30, 30);
        search.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(search);

        //username label
        username = new JLabel("Username: ");
        username.setBounds(30, 140, 100, 30);
        username.setFont(f);
        c.add(username);
        //username textbox
        tusername = new JTextField();
        tusername.setBounds(124, 140, 326, 35);
        tusername.setFont(f);
        c.add(tusername);

        //name label
        name = new JLabel("Name: ");
        name.setBounds(30, 210, 70, 30);
        name.setFont(f);
        c.add(name);
        //name textbox
        tname = new JTextField();
        tname.setBounds(124, 210, 326, 35);
        tname.setFont(f);
        c.add(tname);

        //security
        securityLabel = new JLabel("Security Question:");
        securityLabel.setBounds(30, 280, 200, 30);
        securityLabel.setFont(f);
        c.add(securityLabel);

        tsecurity = new JTextField();
        tsecurity.setBounds(190, 280, 260, 35);
        tsecurity.setFont(f);
        c.add(tsecurity);

        answer = new JLabel("Asnwer: ");
        answer.setBounds(30, 350, 110, 30);
        answer.setFont(f);
        c.add(answer);
        ansbox = new JTextField();
        ansbox.setBounds(190, 350, 260, 35);
        ansbox.setFont(f);
        c.add(ansbox);

        pass = new JLabel("Password: ");
        pass.setBounds(30, 420, 110, 30);
        pass.setFont(f);
        c.add(pass);
        passbox = new JPasswordField();
        passbox.setBounds(190, 420, 260, 35);
        passbox.setFont(f);
        c.add(passbox);

        show = new JCheckBox("Show");
        show.setBounds(185, 460, 80, 20);
        show.setFont(f2);

        c.add(show);

        bt = new JButton("Retrieve");
        bt.setBackground(new Color(24, 106, 59));
        bt.setBounds(40, 550, 85, 30);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt.setForeground(Color.white);
        bt.setFont(f);
        c.add(bt);

        update = new JButton("Update");
        update.setBackground(new Color(40, 116, 166));
        update.setBounds(150, 550, 85, 30);
        update.setForeground(Color.white);
        update.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        update.setFont(f);
        c.add(update);

        delete = new JButton("Delete");
        delete.setBackground(new Color(250, 128, 114));
        delete.setBounds(260, 550, 85, 30);
        delete.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        delete.setForeground(Color.white);
        delete.setFont(f);
        c.add(delete);

        bt2 = new JButton("Exit");
        bt2.setBackground(new Color(100, 149, 237));
        bt2.setBounds(375, 550, 85, 30);
        bt2.setForeground(Color.white);
        bt2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt2.setFont(f);
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
                String tpass = ansbox.getText();
                String tuser = tusername.getText();

                if (tpass.equals(SecurityAnswer)) {
                    passbox.setText(password);
                } else if (tuser.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter your Username!");
                } else {
                    JOptionPane.showMessageDialog(null, "Informaton Not Matched!");
                }

            }
        });

        search.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String username = tusername.getText();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert = con.prepareStatement("SELECT * FROM `registration_form` WHERE `Username` LIKE '%" + username + "%'");
                    ResultSet rs = insert.executeQuery();
                    if (rs.next()) {

                        String name = rs.getString("Name");

                        String phone = rs.getString("Security_Question");
                        SecurityAnswer = rs.getString("Answer");
                        password = rs.getString("Password");

                        tname.setText(name);
                        tsecurity.setText(phone);

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

        update.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String username = tusername.getText();
                String name = tname.getText();
                String security = tsecurity.getText();
                String ans = ansbox.getText();
                String password = passbox.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert = con.prepareStatement("UPDATE registration_form SET Name='" + name + "',Security_Question='" + security + "',Answer='" + ans + "',Password='" + password + "' WHERE Username='" + username + "';");
                    insert.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Account Updated");

                    tusername.setText("");
                    tname.setText("");
                    tsecurity.setText("");
                    ansbox.setText("");
                    passbox.setText("");

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        show.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (show.isSelected()) {
                    passbox.setEchoChar((char) 0);
                } else {
                    passbox.setEchoChar('*');
                }

            }

        });

        delete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String username = tusername.getText();
                String tpass = ansbox.getText();
                if (password.equals(passbox.getText()) && tpass.equals(SecurityAnswer)) {
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                        insert = con.prepareStatement("DELETE FROM registration_form WHERE Username='" + username + "'");
                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Delete successfully");

                        tusername.setText("");
                        tname.setText("");
                        tsecurity.setText("");
                        ansbox.setText("");
                        passbox.setText("");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AddStudent.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password Or Security Question!");
                    passbox.setText("");
                    ansbox.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        ForgetPassword Sframe = new ForgetPassword();
        Sframe.createFrame();
    }
}
