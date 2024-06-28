import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends JFrame implements ActionListener, Runnable {
    JTextField messageInput;
    JTextArea messageArea;
    JPanel panel;
    JButton sendButton, sendFileButton;
    ServerSocket serverSocket;
    Socket socket;
    DataOutputStream dout;
    DataInputStream din;

    public void server() {
        // Setup server to accept connections
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle font changes and message sending
    }

    @Override
    public void run() {
        // Handle incoming messages and file transfers from clients
    }
}
