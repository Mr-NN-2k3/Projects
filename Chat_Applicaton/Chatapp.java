import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chatapp {

    public static void main(String[] args) {
        Chatapp ca = new Chatapp();
        ca.mainPageGUI();
    }

    void mainPageGUI() {
        Server s = new Server();
        Client c = new Client();
        JPanel panel3 = new JPanel();
        JFrame jf3 = new JFrame();
        jf3.setTitle("Main Page");
        jf3.setSize(400, 150);
        jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf3.add(panel3);
        panel3.setLayout(null);

        JButton server = new JButton("Server");
        JButton client = new JButton("Client");
        JButton cancel = new JButton("Cancel");

        server.setBounds(60, 45, 80, 20);
        panel3.add(server);

        client.setBounds(160, 45, 80, 20);
        panel3.add(client);

        cancel.setBounds(260, 45, 80, 20);
        panel3.add(cancel);

        server.addActionListener(e -> {
            jf3.dispose();
            s.server();
        });

        client.addActionListener(e -> {
            jf3.dispose();
            c.client();
        });

        cancel.addActionListener(e -> {
            System.exit(0);
        });

        jf3.setResizable(false);
        jf3.setVisible(true);
    }
}
