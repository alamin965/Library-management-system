package library_management;

import com.sun.glass.ui.Cursor;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class LoginForm extends JFrame {

    private Container c;
    private ImageIcon background, icon, photo, admin, submitIcon, cancelIcon, passwordIcon, userIcon;
    private JLabel x, image, heading, headingIcon, bg, title, Jadmin, adminLogin, label1, label2, forget;
    private JPanel headingPanel, panel3, componentPanel, adminPanel, xP;
    private Font f, f1, f2;
    private JTextField username;
    private JPasswordField password;
    private JButton login, signup;
    private BorderFactory border;
    private Connection con6;
    private PreparedStatement insert6;

    LoginForm() {
        initComponents();
        createActionListener();

    }

    public void createFrame() {

        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1166, 668);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void initComponents() {

        c = this.getContentPane();
        c.setLayout(null);
        f = new Font("Algerian", Font.BOLD, 24);
        f1 = new Font("Arial", Font.BOLD, 24);

        xP = new JPanel();
        xP.setLayout(null);
        xP.setBounds(1100, 0, 66, 25);
        xP.setBackground(new Color(0, 0, 0, 0));//
        c.add(xP);
        headingPanel = new JPanel();
        headingPanel.setLayout(null);
        headingPanel.setBounds(373, 10, 420, 200);
        headingPanel.setBackground(new Color(0, 0, 0, 0));
        c.add(headingPanel);

        componentPanel = new JPanel();
        componentPanel.setLayout(null);
        componentPanel.setBounds(373, 200, 420, 260);
        componentPanel.setBackground(new Color(0, 0, 0, 0));//
        c.add(componentPanel);

        adminPanel = new JPanel();
        adminPanel.setLayout(null);
        adminPanel.setBounds(373, 140, 420, 180);//
        adminPanel.setBackground(new Color(0, 0, 0, 0));
        c.add(adminPanel);

        panel3 = new JPanel();
        panel3.setLayout(null);
        panel3.setBounds(373, 200, 420, 260);//
        panel3.setBackground(Color.GREEN);
        c.add(panel3);

        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());//set icon 
        Image img5 = icon.getImage();

        Image temp_img5 = img5.getScaledInstance(150, 90, Image.SCALE_SMOOTH);
        icon = new ImageIcon(temp_img5);
        //System.out.println((((screenSize.width-200)*13)/100));

        background = new ImageIcon(getClass().getResource("background.jpg"));//get background image
        photo = new ImageIcon(getClass().getResource("photo.png"));//get background image

        Image img4 = photo.getImage();

        Image temp_img4 = img4.getScaledInstance(420, 260, Image.SCALE_SMOOTH);
        photo = new ImageIcon(temp_img4);

        Image img = background.getImage();

        Image temp_img = img.getScaledInstance(1166, 668, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp_img);
        image = new JLabel("", background, JLabel.CENTER);
        image.setBounds(0, 0, 1166, 668);

        c.add(image);

        //get background image
        admin = new ImageIcon(getClass().getResource("admin.png"));//get background image

        Image img3 = admin.getImage();
        Image temp_img3 = img3.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        admin = new ImageIcon(temp_img3);
        Jadmin = new JLabel("", admin, JLabel.CENTER);
        Jadmin.setBounds(163, 0, 100, 100);
        adminPanel.add(Jadmin);
        adminLogin = new JLabel("Admin Login");
        adminLogin.setBounds(135, 97, 150, 32);
        adminLogin.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK));
        adminLogin.setFont(f1);

        adminPanel.add(adminLogin);

        headingIcon = new JLabel(icon);
        headingIcon.setBounds(130, 0, 151, 90);

        headingPanel.add(headingIcon);
        heading = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        heading.setBounds(15, 86, 384, 50);
        heading.setForeground(Color.white);
        heading.setFont(f);
        headingPanel.add(heading);

        bg = new JLabel(photo);
        bg.setBounds(0, 0, 420, 260);
        panel3.add(bg);
        x = new JLabel("X");
        x.setBounds(40, 5, 20, 20);
        x.setForeground(Color.red);

        x.setFont(f1);
        xP.add(x);
        username = new JTextField();
        username.setBounds(90, 80, 250, 30);

        username.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        componentPanel.add(username);
        userIcon = new ImageIcon(getClass().getResource("userIcon.png"));
        label1 = new JLabel(userIcon);
        label1.setBounds(57, 83, 25, 25);
        componentPanel.add(label1);

        password = new JPasswordField();

        password.setBounds(90, 130, 250, 30);
        password.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.white));
        componentPanel.add(password);
        passwordIcon = new ImageIcon(getClass().getResource("passIcon.png"));
        label2 = new JLabel(passwordIcon);
        label2.setBounds(55, 130, 28, 28);
        componentPanel.add(label2);

        login = new JButton("Login");
        login.setBounds(120, 190, 80, 25);
        login.setBackground(new Color(46, 67, 105));
        login.setForeground(Color.white);
        login.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        componentPanel.add(login);

        signup = new JButton("Sign Up");
        signup.setBounds(230, 190, 80, 25);
        signup.setBackground(new Color(46, 67, 105));
        signup.setForeground(Color.white);

        signup.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        componentPanel.add(signup);

        forget = new JLabel("Forget password");
        forget.setBounds(160, 220, 150, 25);
        componentPanel.add(forget);

    }

    public void createActionListener() {

        x.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);

            }
        });

        username.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (username.getText().equals("Enter username")) {
                    username.setText("");
                    username.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (username.getText().equals("")) {
                    username.setText("Enter username");
                    username.setForeground(Color.black);
                }
            }

        });
        password.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                if (password.getText().equals("Enter password")) {
                    password.setText("");
                    password.setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent fe) {
                if (password.getText().equals("")) {
                    password.setText("Enter password");
                    password.setForeground(Color.black);
                }
            }

        });

        login.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String UserName = username.getText();
                String Password1 = password.getText();

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    con6 = DriverManager.getConnection("jdbc:mysql://localhost/library_management1", "root", "");
                    insert6 = con6.prepareStatement("SELECT Username,Password FROM `registration_form` WHERE Username=? AND Password=?");
                    insert6.setString(1, UserName);
                    insert6.setString(2, Password1);
                    ResultSet rs = insert6.executeQuery();

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(null, "You are successfully logged in");

                        HomePage Hframe = new HomePage();
                        Hframe.createFram();
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "Worng Username or Password");
                        username.setText("");
                        password.setText("");
                    }

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        forget.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ForgetPassword Sframe = new ForgetPassword();
                Sframe.createFrame();

            }
        });

        signup.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SignUpPage Sframe = new SignUpPage();
                Sframe.createFrame();

            }
        });

    }

    public static void main(String[] args) {

        LoginForm frame = new LoginForm();
        frame.createFrame();

    }
}
