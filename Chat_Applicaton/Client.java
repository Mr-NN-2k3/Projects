import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class Client extends JFrame implements ActionListener, Runnable {
    JTextField messageInput;
    JTextArea messageArea;
    JPanel panel;
    JButton sendButton, sendFileButton;
    Socket socket;
    DataOutputStream dout;
    DataInputStream din;

    public void client() {
        // Setup client GUI with text areas and buttons
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle font changes and message sending
    }

    @Override
    public void run() {
        // Handle connection to server and sending messages/files
    }

    void login() {
        JFrame jf1 = new JFrame();
        JPanel panel1 = new JPanel();
        jf1.setTitle("Client Login Page");
        jf1.setSize(400, 200);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf1.add(panel1);
        panel1.setLayout(null);

        JLabel label = new JLabel("Enter your name ");
        JLabel label1 = new JLabel("Enter your last name ");
        JLabel label2 = new JLabel("Enter your friend's name ");
        JLabel label3 = new JLabel("Enter your friend's last name ");

        JTextField user = new JTextField();
        JTextField luser = new JTextField();
        JTextField friend = new JTextField();
        JTextField lfriend = new JTextField();
        JButton login = new JButton("Login");
        JButton cancel = new JButton("Cancel");

        label.setBounds(10, 20, 140, 25);
        panel1.add(label);
        user.setBounds(190, 20, 180, 25);
        panel1.add(user);

        label1.setBounds(10, 40, 140, 25);
        panel1.add(label1);
        luser.setBounds(190, 40, 180, 25);
        panel1.add(luser);

        label2.setBounds(10, 70, 140, 25);
        panel1.add(label2);
        friend.setBounds(190, 70, 180, 25);
        panel1.add(friend);

        label3.setBounds(10, 90, 165, 25);
        panel1.add(label3);
        lfriend.setBounds(190, 90, 180, 25);
        panel1.add(lfriend);

        login.setBounds(289, 130, 80, 20);
        panel1.add(login);

        cancel.setBounds(189, 130, 80, 20);
        panel1.add(cancel);

        cancel.addActionListener(e -> {
            jf1.dispose();
            Chatapp ca = new Chatapp();
            ca.mainPageGUI();
        });

        login.addActionListener(e -> {
            // Logic for handling login
        });

        jf1.setResizable(false);
        jf1.setVisible(true);
    }
}
