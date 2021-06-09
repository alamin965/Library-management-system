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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class Alamin extends JFrame {

    private Container c;
    private JLabel name, email, id, section, alamin;

    private Font f, f1;
    private ImageIcon icon, palamin;

    public void createFrame() {
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(new Color(242, 243, 244)));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        JFrame.setDefaultLookAndFeelDecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 450);
        setTitle("                                                                                                                                                                                                                                                                                                                                                                                                                                                          ");
        setLocationRelativeTo(null);

    }

    Alamin() {
        initComponent();

    }

    public void initComponent() {
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(new Color(242, 243, 244));
        f = new Font("Arial", Font.PLAIN, 24);
        f1 = new Font("Arial", Font.PLAIN, 22);

        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        palamin = new ImageIcon(getClass().getResource("alamin.png"));
        Image img1 = palamin.getImage();
        Image temp_img1 = img1.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        palamin = new ImageIcon(temp_img1);
        alamin = new JLabel(palamin);
        alamin.setBounds(225, 40, 150, 150);
        alamin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.add(alamin);

        name = new JLabel("Md. Al Amin Miah");
        name.setBounds(200, 200, 300, 20);

        name.setFont(f);
        c.add(name);
        id = new JLabel("ID: 193-15-2965");
        id.setBounds(207, 230, 250, 20);

        id.setFont(f);
        c.add(id);
        section = new JLabel("Section: PC-A");
        section.setBounds(215, 260, 250, 20);

        section.setFont(f);
        c.add(section);

        email = new JLabel("Email: alamin15-2965@diu.edu.bd");
        email.setBounds(105, 290, 500, 20);

        email.setFont(f);
        c.add(email);

    }

    public static void main(String[] args) {
        Alamin frame = new Alamin();
        frame.createFrame();
    }

}
