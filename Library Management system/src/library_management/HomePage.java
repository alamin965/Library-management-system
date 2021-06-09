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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

public class HomePage extends JFrame {

    private Container c;
    private ImageIcon background, addBook, BookList, Statics, icon, addStudents, statics, issueBook, returnBook, searchBook, team;
    private JLabel heading, headingicon, image, addStu1, addStu2, addBook1, addBook2, BookList1, BookList2, statics1, statics2, issueBook1, issueBook2, returnBook1, returnBook2, searchBook1, searchBook2, team1, team2;
    private JPanel Panel, meno;
    private Font f, f1;

    HomePage() {
        initComponents();
        createActionListener();
    }

    public void createFram() {

        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(2);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1266, 720);
        setTitle("Home                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ");
        setLocationRelativeTo(null);
        setResizable(false);

    }

    public void initComponents() {
        c = this.getContentPane();
        c.setLayout(null);
        f = new Font("Arial", Font.BOLD, 22);
        f1 = new Font("Algerian", Font.BOLD, 22);
        Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBounds(0, 0, 1266, 760);
        Panel.setBackground(new Color(0, 0, 0, 0));
        c.add(Panel);

        meno = new JPanel();
        meno.setLayout(null);
        meno.setBounds(0, 0, 1266, 15);
        meno.setBackground(new Color(0, 0, 0, 0));
        c.add(meno);

        background = new ImageIcon(getClass().getResource("background.jpg"));
        addStudents = new ImageIcon(getClass().getResource("addStudent.png"));
        addBook = new ImageIcon(getClass().getResource("addBook.png"));
        BookList = new ImageIcon(getClass().getResource("student1.png"));
        statics = new ImageIcon(getClass().getResource("static.png"));
        issueBook = new ImageIcon(getClass().getResource("issue.png"));
        returnBook = new ImageIcon(getClass().getResource("return.png"));
        searchBook = new ImageIcon(getClass().getResource("search.png"));
        team = new ImageIcon(getClass().getResource("devIcon.png"));

        icon = new ImageIcon(getClass().getResource("icon.png"));// get image
        this.setIconImage(icon.getImage());

        Image img = background.getImage();

        Image temp_img = img.getScaledInstance(1266, 760, Image.SCALE_SMOOTH);
        background = new ImageIcon(temp_img);
        image = new JLabel("", background, JLabel.CENTER);
        image.setBounds(0, 0, 1266, 760);

        c.add(image);
        Image img0 = icon.getImage();
        Image temp_img0 = img0.getScaledInstance(140, 100, Image.SCALE_SMOOTH);
        icon = new ImageIcon(temp_img0);
        headingicon = new JLabel(icon);
        headingicon.setBounds(543, 10, 180, 130);
        headingicon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(headingicon);
        heading = new JLabel("Library Management System");
        heading.setBounds(443, 130, 400, 20);
        heading.setForeground(Color.WHITE);

        heading.setFont(f1);
        Panel.add(heading);

        //add students
        Image img2 = addStudents.getImage();
        Image temp_img2 = img2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        addStudents = new ImageIcon(temp_img2);
        addStu1 = new JLabel(addStudents);
        addStu1.setBounds(100, 200, 150, 150);
        addStu1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(addStu1);
        addStu2 = new JLabel("New Student");
        addStu2.setBounds(105, 365, 150, 20);
        addStu2.setForeground(Color.white);
        addStu2.setFont(f);
        Panel.add(addStu2);

        //add book
        Image img3 = addBook.getImage();
        Image temp_img3 = img3.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        addBook = new ImageIcon(temp_img3);
        addBook1 = new JLabel(addBook);
        addBook1.setBounds(400, 200, 150, 150);
        addBook1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(addBook1);
        addBook2 = new JLabel("New Book");
        addBook2.setBounds(420, 365, 150, 20);
        addBook2.setForeground(Color.white);
        addBook2.setFont(f);
        Panel.add(addBook2);

        //book list
        Image img4 = BookList.getImage();
        Image temp_img4 = img4.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        BookList = new ImageIcon(temp_img4);
        BookList1 = new JLabel(BookList);
        BookList1.setBounds(700, 200, 150, 150);
        BookList1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(BookList1);
        BookList2 = new JLabel("Student Details");
        BookList2.setBounds(695, 365, 155, 20);
        BookList2.setForeground(Color.white);
        BookList2.setFont(f);
        Panel.add(BookList2);

        //statics
        Image img5 = statics.getImage();
        Image temp_img5 = img5.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        statics = new ImageIcon(temp_img5);
        statics1 = new JLabel(statics);
        statics1.setBounds(1000, 200, 150, 150);
        statics1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(statics1);
        statics2 = new JLabel("Statics");
        statics2.setBounds(1040, 365, 150, 20);
        statics2.setForeground(Color.white);
        statics2.setFont(f);
        Panel.add(statics2);

        //issue book
        Image img6 = issueBook.getImage();
        Image temp_img6 = img6.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        issueBook = new ImageIcon(temp_img6);
        issueBook1 = new JLabel(issueBook);
        issueBook1.setBounds(100, 440, 150, 150);
        issueBook1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(issueBook1);
        issueBook2 = new JLabel("Issue Book");
        issueBook2.setBounds(120, 605, 150, 20);
        issueBook2.setForeground(Color.white);
        issueBook2.setFont(f);
        Panel.add(issueBook2);

        //return book
        Image img7 = returnBook.getImage();
        Image temp_img7 = img7.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        returnBook = new ImageIcon(temp_img7);
        returnBook1 = new JLabel(returnBook);
        returnBook1.setBounds(400, 440, 150, 150);
        returnBook1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(returnBook1);
        returnBook2 = new JLabel("Return Book");
        returnBook2.setBounds(410, 605, 150, 20);
        returnBook2.setForeground(Color.white);
        returnBook2.setFont(f);
        Panel.add(returnBook2);

        //search book
        Image img8 = searchBook.getImage();
        Image temp_img8 = img8.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        searchBook = new ImageIcon(temp_img8);
        searchBook1 = new JLabel(searchBook);
        searchBook1.setBounds(700, 440, 150, 150);
        searchBook1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(searchBook1);
        searchBook2 = new JLabel("Book Details");
        searchBook2.setBounds(710, 605, 150, 20);
        searchBook2.setForeground(Color.white);
        searchBook2.setFont(f);
        Panel.add(searchBook2);

        //team
        Image img9 = team.getImage();
        Image temp_img9 = img9.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        team = new ImageIcon(temp_img9);
        team1 = new JLabel(team);
        team1.setBounds(1000, 440, 150, 150);
        team1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Panel.add(team1);
        team2 = new JLabel("Developer");
        team2.setBounds(1020, 605, 150, 20);
        team2.setForeground(Color.white);
        team2.setFont(f);
        Panel.add(team2);

    }

    public void createActionListener() {

        addStu1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AddStudent frame = new AddStudent();
                frame.createFrame();

            }
        });

        addBook1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                AddBook frame = new AddBook();
                frame.createFrame();

            }
        });

        BookList1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                StudentsList frame = new StudentsList();
                frame.createFram();

            }
        });

        statics1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Statics frame = new Statics();
                frame.createFram();

            }
        });
        issueBook1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                IssueBook frame = new IssueBook();
                frame.createFrame();

            }
        });

        returnBook1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                ReturnBook frame = new ReturnBook();
                frame.createFrame();

            }
        });

        searchBook1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SearchBook frame = new SearchBook();
                frame.createFram();

            }
        });

        team1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Alamin frame = new Alamin();
                frame.createFrame();

            }
        });

        headingicon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(headingicon, "You are successfully logged out");
                LoginForm frame = new LoginForm();
                frame.createFrame();
                dispose();

            }
        });

    }

    public static void main(String[] args) {
        HomePage Hframe = new HomePage();
        Hframe.createFram();
    }

}
