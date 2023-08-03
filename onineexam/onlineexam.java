

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Login extends JFrame implements ActionListener {
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;
    Login() {
        userLabel = new JLabel();
        userLabel.setText("    Username :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("    Password :");
        textField2 = new JPasswordField(8);
        b1 = new JButton("   LOGIN  ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        setTitle("Login Form ");
    }
    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if (!passValue.equals("")) {
            OnlineTestBegin testBegin = new OnlineTestBegin(userValue);
            testBegin.setSize(600,350);
            testBegin.setVisible(true);
            dispose(); 
        } else {
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel l;
    JLabel l1;
    JRadioButton jb[] = new JRadioButton[6];
    JButton b1, b2, log, updateButton; 
    ButtonGroup bg;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer; 
    int secondsRemaining = 180; 

    OnlineTestBegin(String s) {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        updateButton = new JButton("Update Profile"); 
        log = new JButton("Logout"); 
        b1.addActionListener(this);
        b2.addActionListener(this);
        updateButton.addActionListener(this); 
        log.addActionListener(this); 
        add(b1);
        add(b2);
        add(updateButton); 
        add(log); 
        set();
        l.setBounds(30, 40, 450, 20);
        l1.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        updateButton.setBounds(420, 20, 150, 30); 
        log.setBounds(420, 60, 150, 30); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

        
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                secondsRemaining--;
                l1.setText("Time Remaining: " + formatTime(secondsRemaining));
                if (secondsRemaining <= 0) {
                    timer.stop();
                    JOptionPane.showMessageDialog(OnlineTestBegin.this, "Time's up!");
                    showResult();
                }
            }
        });
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if (check())
                count = count + 1;
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        } else if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 60 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9)
                b2.setText("Result");
            setVisible(false);
            setVisible(true);
        } else if (e.getActionCommand().equals("Result")) {
            if (check())
                count = count + 1;
            showResult();
        } else if (e.getSource() == updateButton) {
            String newUsername = JOptionPane.showInputDialog(this, "Enter new username");
            if (newUsername != null && !newUsername.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username updated successfully!");
                setTitle("Welcome, " + newUsername);
            }
        } else if (e.getSource() == log) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                dispose(); // Close the exam interface
                Login loginForm = new Login();
                loginForm.setSize(400, 150);
                loginForm.setVisible(true);
            }
        } else {
            for (int i = 0, y = 1; i < x; i++, y++) {
                if (e.getActionCommand().equals("Review" + y)) {
                    if (check())
                        count = count + 1;
                    now = current;
                    current = m[y];
                    set();
                    ((JButton) e.getSource()).setEnabled(false);
                    current = now;
                }
            }
        }
    }

    void set() {
        jb[4].setSelected(true);
        if (current == 0) {
            l.setText("Que1: Who invented java programming ?");
            jb[0].setText("Charles Babbage");
            jb[1].setText("James Gosling");
            jb[2].setText("M.P. Java");
            jb[3].setText("Blais Pascal");
        } else if (current == 1) {
            l.setText("Que2:which of these are selection statements in java  ?");
            jb[0].setText("break");
            jb[1].setText("contiue");
            jb[2].setText("for()");
            jb[3].setText("if()");
        } else if (current == 2) {
            l.setText("Que3: Where is the system class defined?");
            jb[0].setText("java.lang.package");
            jb[1].setText("java.util.package");
            jb[2].setText("java.lo.package");
            jb[3].setText("None");
        }
else if(current==3)
{
l.setText("Ques4:which of the folloing is not a java feature?");
jb[0].setText("object-oriented");
jb[1].setText("Use of pointers");
jb[2].setText("Portable");
jb[3].setText("dynamic");
}
else if(current==4)
{
l.setText("Ques5:which of these cannot be used for a variable name in java?");
jb[0].setText("identifer and keyword");
jb[1].setText("identifer");
jb[2].setText("keyword");
jb[3].setText("None");
}
else if(current ==5)
{
 l.setText("Ques 6:what is the extension of java code file");
jb[0].setText(".js");
jb[1].setText(".txt");
jb[2].setText(".class");
jb[3].setText(".java");
}
else if(current ==6)
{
l.setText("Ques7:Which of the following is not a OOPS concept?");
jb[0].setText("Polymorphism");
jb[1].setText("Inheritance");
jb[2].setText("Compilation");
jb[3].setText("Encapsulation");
}
else if(current ==7)
{
l.setText("Ques8:which of the following keyword is used for define the interfaces in java?");
jb[0].setText("Inf");
jb[1].setText("Intf");
jb[2].setText("interface");
jb[3].setText("Interface");
}
else if(current ==8)
{
l.setText("Ques9:which of the following is a superclass of every class in java");
jb[0].setText("Arraylist");
jb[1].setText("Abstract class");
jb[2].setText("Object class");
jb[3].setText("String");
}
else if (current ==9)
{
l.setText("Ques10:which of these keywords are used for the block to be examined for exceptions");
jb[0].setText("check");
jb[1].setText("throw");
jb[2].setText("catch");
jb[3].setText("try");
}

              l.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i <= 90; i += 30, j++)
            jb[j].setBounds(50, 80 + i, 200, 20);
    }

    boolean check() {
        
if (current == 0)
            return (jb[1].isSelected());
        if (current == 1)
            return (jb[3].isSelected());
        if (current == 2)
            return (jb[2].isSelected());
if(current ==3)
   return(jb[1].isSelected());
        if (current == 4)
            return (jb[2].isSelected());
        if (current == 5)
            return (jb[3].isSelected());
        if (current == 6)
            return (jb[2].isSelected());
        if (current == 7)
            return (jb[2].isSelected());
        if (current == 8)
            return (jb[2].isSelected());
        if (current == 9)
            return (jb[3].isSelected());

        return false;

    }

    String formatTime(int seconds) {
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    void showResult() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Score = " + count);
        System.exit(0);
    }
}

class OnlineExam {
    public static void main(String args[]) {
        try {
            Login form = new Login();
            form.setSize(400, 400);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
