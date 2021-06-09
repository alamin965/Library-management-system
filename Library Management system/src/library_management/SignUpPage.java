package library_management;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

public class SignUpPage extends JFrame {

    private Container c;
    private JLabel head, name, username, pass, cpass, securityLabel, answer;
    private JTextField tname, tusername, ansbox;
    private JPasswordField tpass, tcpass;
    private JButton bt, bt2, clear;
    private Font f, f1, f2;
    private ImageIcon icon;
    private JCheckBox show;
    private JComboBox cbox;
    private Connection con1;
    private PreparedStatement insert;
    private String[] proLan = {"Your favourite food.", "Your favourite pet.", "Your first school name.",
        "Your favorite bird.", "Your favourite writer.", "Your favourite book.", "Your favourite teacher name."};

    public void createFrame() {

        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(500, 650);

        setTitle("SignUp");
        setLocationRelativeTo(null);

    }

    SignUpPage() {
        initComponent();
        actionListener();
    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.PLAIN, 18);
        f1 = new Font("Arial", Font.BOLD, 24);
        f2 = new Font("Arial", Font.BOLD, 15);
        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        head = new JLabel("Registration Form");
        head.setBounds(146, 40, 207, 30);
        head.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, Color.BLACK));
        head.setFont(f1);
        c.add(head);

        name = new JLabel("Name: ");
        name.setBounds(30, 100, 70, 30);
        name.setFont(f);
        c.add(name);

        tname = new JTextField();
        tname.setBounds(124, 100, 326, 35);
        tname.setFont(f);
        c.add(tname);

        username = new JLabel("Username: ");
        username.setBounds(30, 170, 100, 30);
        username.setFont(f);
        c.add(username);

        tusername = new JTextField();
        tusername.setBounds(124, 170, 326, 35);
        tusername.setFont(f);
        c.add(tusername);
        //combobox

        securityLabel = new JLabel("Security Question:");
        securityLabel.setBounds(30, 240, 200, 30);
        securityLabel.setFont(f);
        c.add(securityLabel);

        cbox = new JComboBox(proLan);
        cbox.setBounds(190, 240, 260, 35);
        cbox.setEditable(true);
        c.add(cbox);

        answer = new JLabel("Asnwer: ");
        answer.setBounds(30, 310, 110, 30);
        answer.setFont(f);
        c.add(answer);
        ansbox = new JTextField();
        ansbox.setBounds(190, 310, 260, 35);
        ansbox.setFont(f);
        c.add(ansbox);

        pass = new JLabel("Password: ");
        pass.setBounds(30, 380, 110, 30);
        pass.setFont(f);
        c.add(pass);
        tpass = new JPasswordField();
        tpass.setBounds(190, 380, 260, 35);
        tpass.setEchoChar('*');
        tpass.setFont(f);
        c.add(tpass);

        cpass = new JLabel("Confirm Password: ");
        cpass.setBounds(30, 450, 160, 30);

        cpass.setFont(f);
        c.add(cpass);

        tcpass = new JPasswordField();
        tcpass.setBounds(190, 450, 260, 35);
        tcpass.setEchoChar('*');
        tcpass.setFont(f);
        c.add(tcpass);

        //show password
        show = new JCheckBox("Show");
        show.setBounds(190, 490, 70, 20);
        show.setFont(f2);
        c.add(show);

        bt = new JButton("Create");
        bt.setBackground(new Color(46, 139, 87));
        bt.setBounds(60, 550, 90, 30);
        bt.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        bt.setFont(f);
        c.add(bt);

        clear = new JButton("Clear");
        clear.setBackground(new Color(51, 255, 189));
        clear.setBounds(206, 550, 90, 30);
        clear.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        clear.setFont(f);
        c.add(clear);

        bt2 = new JButton("Exit");
        bt2.setBackground(new Color(250, 128, 114));
        bt2.setBounds(350, 550, 90, 30);
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
                String name = tname.getText();
                String userName = tusername.getText();
                String security = cbox.getSelectedItem().toString();
                String security_ans = ansbox.getText();
                String password = tpass.getText();
                String cpassword = tcpass.getText();

                if (password.equals(cpassword)) {

                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        con1 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                        insert = con1.prepareStatement("insert into registration_form(Name,Username,Security_Question,Answer,Password)values(?,?,?,?,?)");

                        insert.setString(1, name);
                        insert.setString(2, userName);
                        insert.setString(3, security);
                        insert.setString(4, security_ans);
                        insert.setString(5, password);
                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account Created!");

                        tname.setText("");
                        tusername.setText("");
                        ansbox.setText("");
                        tpass.setText("");
                        tcpass.setText("");
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(SignUpPage.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Password Not matched!");
                    tpass.setText("");
                    tcpass.setText("");
                }
            }
        });

        show.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (show.isSelected()) {
                    tpass.setEchoChar((char) 0);
                    tcpass.setEchoChar((char) 0);
                } else {
                    tpass.setEchoChar('*');
                    tcpass.setEchoChar('*');
                }

            }

        });

        clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tname.setText("");
                tusername.setText("");
                cbox.setSelectedItem("");
                ansbox.setText("");
                tpass.setText("");
                tcpass.setText("");
            }

        });

    }

    public static void main(String[] args) {
        SignUpPage Sframe = new SignUpPage();
        Sframe.createFrame();
    }

}
