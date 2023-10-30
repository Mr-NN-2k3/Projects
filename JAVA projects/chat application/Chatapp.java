import java.io.*;
import java.util.*;
import java.net.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Server extends JFrame implements ActionListener
{  
       int PortNo=2912;
       String name,lname,message,fname,flname,filename; 
       ServerSocket server;
       Socket socket;
       DataInputStream in;
       DataOutputStream out;

       Date date = new Date();
       SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
       String CurrentDate = formatter.format(date); 

       JLabel heading = new JLabel("Java Chat Application");
       JTextArea messageArea = new JTextArea();
       JTextArea progressArea = new JTextArea();
       JTextField messageInput = new JTextField();

       JMenuBar mb = new JMenuBar();
       JMenu fs;
       JMenuItem S1,S2,S3,S4,S5,S6;

       JPanel panel1 =new JPanel();
       JFrame jf1=new JFrame();

       Chatapp ca=new Chatapp();
       ServerIP sip=new ServerIP();
       void gui()
       {
            Font font = new Font("ROBOTO", Font.PLAIN, 20);
            heading.setFont(font);
            messageArea.setFont(font);
            messageInput.setFont(font);
                            
            this.setTitle(name+" "+lname);    
            this.setSize(500,500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setJMenuBar(mb);
          
            fs = new JMenu("Font Style");
            mb.add(fs);

            
            S1 = new JMenuItem("SERIF");
            S2 = new JMenuItem("ARIAL BLACK");
            S3 = new JMenuItem("IMPACT");
            S4 = new JMenuItem("BOOK ANTIQUA");
            S5 = new JMenuItem("TIMES NEW ROMAN");
            S6 = new JMenuItem("COMIC SANS MS");
           
            fs.add(S1);
            fs.add(S2);
            fs.add(S3);
            fs.add(S4);
            fs.add(S5);
            fs.add(S6);

            S1.addActionListener(this);
            S2.addActionListener(this);
            S3.addActionListener(this);
            S4.addActionListener(this);
            S5.addActionListener(this);
            S6.addActionListener(this);

            messageArea.setEditable(false);

            heading.setHorizontalAlignment(SwingConstants.CENTER);
            
            this.setLayout(new BorderLayout());
            JScrollPane scroll=new JScrollPane(messageArea);
            this.add(heading,BorderLayout.NORTH);
            this.add(scroll,BorderLayout.CENTER);
            this.add(messageInput,BorderLayout.SOUTH);

            this.setVisible(true);                                 
          } 
          @Override
          public void actionPerformed(ActionEvent e)
          {
                    if(e.getSource()==S1) 
                    {
                     Font font0 = new Font("SERIF", Font.ITALIC, 20);
                     heading.setFont(font0);
                     messageArea.setFont(font0);
                     messageInput.setFont(font0);
                    }
                    if(e.getSource()==S2)
                    {
                     Font font1 = new Font("ARIAL BLACK", Font.PLAIN, 20);
                     heading.setFont(font1);
                     messageArea.setFont(font1);
                     messageInput.setFont(font1);
                    }
                    if(e.getSource()==S3)
                    {
                     Font font2 = new Font("IMPACT", Font.PLAIN, 20);
                     heading.setFont(font2);
                     messageArea.setFont(font2);
                     messageInput.setFont(font2);
                    }
                    if(e.getSource()==S4)
                    {
                     Font font3 = new Font("BOOK ANTIQUA", Font.PLAIN, 20);
                     heading.setFont(font3);
                     messageArea.setFont(font3);
                     messageInput.setFont(font3);
                    }
                    if(e.getSource()==S5)
                    {
                     Font font4 = new Font("TIMES NEW ROMAN", Font.PLAIN, 20);
                     heading.setFont(font4);
                     messageArea.setFont(font4);
                     messageInput.setFont(font4); 
                    }
                    if(e.getSource()==S6)
                    {
                     Font font5 = new Font("COMIC SANS MS", Font.PLAIN, 20);
                     heading.setFont(font5);
                     messageArea.setFont(font5);
                     messageInput.setFont(font5);
                    }
          }   
          void login()
          {
            jf1.setTitle("Server Login Page");
            jf1.setSize(400,200);
            jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jf1.add(panel1);

            panel1.setLayout(null);

            JLabel label = new JLabel("Enter your name ");
            JLabel label1 = new JLabel("Enter your last name ");
            JLabel label2 = new JLabel("Enter your friend name ");
            JLabel label3 = new JLabel("Enter your friend's last name ");

            JTextField user = new JTextField();
            JTextField luser = new JTextField();
            JTextField friend = new JTextField();
            JTextField lfriend = new JTextField();
            JButton login = new JButton("Login");
            JButton cancle = new JButton("Cancle");

            label.setBounds(10,20,140,25);
            panel1.add(label);
            user.setBounds(190,20,180,25);
            panel1.add(user);

            label1.setBounds(10,40,140,25);
            panel1.add(label1);
            luser.setBounds(190,40,180,25);
            panel1.add(luser);

            label2.setBounds(10,70,140,25);
            panel1.add(label2);
            friend.setBounds(190,70,180,25);
            panel1.add(friend);

            label3.setBounds(10,90,165,25);
            panel1.add(label3);
            lfriend.setBounds(190,90,180,25);
            panel1.add(lfriend);

            login.setBounds(289,130,80,20);
            panel1.add(login);

            cancle.setBounds(189,130,80,20);
            panel1.add(cancle);

            cancle.addActionListener(e->{
                  jf1.dispose();
                  ca.mainPageGUI();
            });

            login.addActionListener(e->{

            name=user.getText();
            lname=luser.getText();
            fname=friend.getText();
            flname=lfriend.getText();
            acceptRequest();
            jf1.dispose();
            });
            jf1.setResizable(false);
            jf1.setVisible(true);

          }
          void acceptRequest() 
          {     
              Runnable R1=()->{
              try{
              sip.serverIP();
              server = new ServerSocket(PortNo);
              socket = server.accept();
              if(!socket.isClosed())
              {
               sip.close();
               in= new DataInputStream(socket.getInputStream());
               out = new DataOutputStream(socket.getOutputStream()); 
               jf1.dispose();
               gui();
               writing();    
               reading(); 
              } 
              }catch(Exception e)
              {
                  JOptionPane.showMessageDialog(this, "Please wait for client request");
              }
              };
              new Thread(R1).start();
          }  
          void reading()
          {
          Runnable reading=()->{
                try{ 
                  while (!socket.isClosed()) 
                  {
                    message = in.readUTF();
                    if (socket.isClosed()) 
                    {
                      messageArea.setEnabled(false);
                      socket.close();
                      break;
                    }
                    else
                    {
                    messageArea.append(message+"\n");
                    messageArea.setCaretPosition(messageArea.getDocument().getLength());                 
                    FileOutputStream fos = new FileOutputStream(new File(CurrentDate+" "+name.toUpperCase()+" "+lname.toUpperCase()+" and "+fname.toUpperCase()+" "+flname.toUpperCase()+"History.txt"), true);
                    PrintStream p = new PrintStream(fos);
                    p.println(message);
                    p.close();
                    fos.close();
                    }
                  } 
                 }catch(Exception e)
                 {
                     JOptionPane.showMessageDialog(this, "Conection Closed");
                 }    
             };
             new Thread(reading).start();
            }
            void writing()
            {    
             messageInput.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent e) {

             }
             @Override
             public void keyPressed(KeyEvent e) {

             }
             @Override
          
             public void keyReleased(KeyEvent e) {
             if (e.getKeyCode() == 10) {
             try{
               SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");
	             Date now = new Date();
               message = messageInput.getText();
               messageArea.append("["+dtf.format(now)+"]  "+name + " : " + message+"\n");
               out.writeUTF("["+dtf.format(now)+"]  "+name + " : " + message);
               out.flush();
               messageInput.setText("");
               messageInput.requestFocus();
              FileOutputStream fos = new FileOutputStream(new File(CurrentDate+" "+name.toUpperCase()+" "+lname.toUpperCase()+" and "+fname.toUpperCase()+" "+flname.toUpperCase()+"History.txt"), true);
               PrintStream p = new PrintStream(fos);
               p.println("["+dtf.format(now)+"]  "+name + " : " + message);
               p.close();
               fos.close();
            }catch(Exception ex)
            {
                 System.out.println("Connection Closed");
            } 
          }
        }
      }); 
      }
      void server() 
      {        
            JPanel panel =new JPanel();
            JFrame jf=new JFrame(); 
            jf.setTitle("Server Main Page");
            jf.setSize(285,300);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jf.add(panel);
            
            panel.setLayout(null); 
    
            JButton  sendmessage= new JButton("Send Message");
            JButton  history= new JButton("Show History");
            JButton  sendfile= new JButton("Send File");
            JButton  receivefile= new JButton("Receive File");
            JButton back = new JButton("Back");


            sendmessage.setBounds(60,40,150,20);
            panel.add(sendmessage);

            history.setBounds(60,80,150,20);
            panel.add(history);

            sendfile.setBounds(60,120,150,20);
            panel.add(sendfile);

            receivefile.setBounds(60,160,150,20);
            panel.add(receivefile);

            back.setBounds(60,200,150,20);
            panel.add(back);

            sendmessage.addActionListener(e->{  
                jf.dispose();
                login();
            });
            history.addActionListener(e->{
                jf.dispose();  
               History h1=new History();
                h1.historyLogin();
            });
             sendfile.addActionListener(e->{
                jf.dispose(); 
                FileSharing fs=new FileSharing();
                fs.sendFile();
            });
            receivefile.addActionListener(e->{
                jf.dispose();
                FileSharing fs=new FileSharing();
                fs.ip();
            });
            back.addActionListener(e->{
                  jf.dispose();
                  Chatapp ca=new Chatapp();
                  ca.mainPageGUI();
            });

            jf.setResizable(false);
            jf.setVisible(true);
          
        }
}
class Client extends JFrame implements ActionListener
{
          int PortNo=2912;
          String name,lname,message,IP,fname,flname,user,filename; 
          Socket socket;
          DataInputStream in;
          DataOutputStream out; 

          Date date = new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
          String CurrentDate = formatter.format(date); 
          
          JLabel heading = new JLabel("Java Chat Application");
          JTextArea messageArea = new JTextArea();
          JTextArea progressArea = new JTextArea();
          JTextField messageInput = new JTextField();

          JMenuBar mb = new JMenuBar();
          JMenu fs;
          JMenuItem S1,S2,S3,S4,S5,S6;

          //login
          JPanel panel1 =new JPanel();
          JFrame jf1=new JFrame();
         
          //ip
          JPanel panel2 =new JPanel();
          JFrame jf2=new JFrame();
         
          Chatapp ca=new Chatapp();
          ServerIP sip=new ServerIP();
          void gui()
          {
            Font font = new Font("ROBOTO", Font.PLAIN, 20);
            heading.setFont(font);
            messageArea.setFont(font);
            messageInput.setFont(font);
                            
            this.setTitle(name+" "+lname);    
            this.setSize(500,500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setJMenuBar(mb);
          
            fs = new JMenu("Font Style");
            mb.add(fs);

            
            S1 = new JMenuItem("SERIF");
            S2 = new JMenuItem("ARIAL BLACK");
            S3 = new JMenuItem("IMPACT");
            S4 = new JMenuItem("BOOK ANTIQUA");
            S5 = new JMenuItem("TIMES NEW ROMAN");
            S6 = new JMenuItem("COMIC SANS MS");
           
            fs.add(S1);
            fs.add(S2);
            fs.add(S3);
            fs.add(S4);
            fs.add(S5);
            fs.add(S6);

            S1.addActionListener(this);
            S2.addActionListener(this);
            S3.addActionListener(this);
            S4.addActionListener(this);
            S5.addActionListener(this);
            S6.addActionListener(this);

            messageArea.setEditable(false);

            heading.setHorizontalAlignment(SwingConstants.CENTER);
            
            this.setLayout(new BorderLayout());
            JScrollPane scroll=new JScrollPane(messageArea);
            this.add(heading,BorderLayout.NORTH);
            this.add(scroll,BorderLayout.CENTER);
            this.add(messageInput,BorderLayout.SOUTH);

            this.setVisible(true);                                 
          } 
          @Override
          public void actionPerformed(ActionEvent e)
          {
                    if(e.getSource()==S1) 
                    {
                     Font font0 = new Font("SERIF", Font.ITALIC, 20);
                     heading.setFont(font0);
                     messageArea.setFont(font0);
                     messageInput.setFont(font0);
                    }
                    if(e.getSource()==S2)
                    {
                     Font font1 = new Font("ARIAL BLACK", Font.PLAIN, 20);
                     heading.setFont(font1);
                     messageArea.setFont(font1);
                     messageInput.setFont(font1);
                    }
                    if(e.getSource()==S3)
                    {
                     Font font2 = new Font("IMPACT", Font.PLAIN, 20);
                     heading.setFont(font2);
                     messageArea.setFont(font2);
                     messageInput.setFont(font2);
                    }
                    if(e.getSource()==S4)
                    {
                     Font font3 = new Font("BOOK ANTIQUA", Font.PLAIN, 20);
                     heading.setFont(font3);
                     messageArea.setFont(font3);
                     messageInput.setFont(font3);
                    }
                    if(e.getSource()==S5)
                    {
                     Font font4 = new Font("TIMES NEW ROMAN", Font.PLAIN, 20);
                     heading.setFont(font4);
                     messageArea.setFont(font4);
                     messageInput.setFont(font4); 
                    }
                    if(e.getSource()==S6)
                    {
                     Font font5 = new Font("COMIC SANS MS", Font.PLAIN, 20);
                     heading.setFont(font5);
                     messageArea.setFont(font5);
                     messageInput.setFont(font5);
                    }
          }
          void login()
          {
            jf1.setTitle("Client Login Page");
            jf1.setSize(400,200);
            jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jf1.add(panel1);

            panel1.setLayout(null);

            JLabel label = new JLabel("Enter your name ");
            JLabel label1 = new JLabel("Enter your last name ");
            JLabel label2 = new JLabel("Enter your friend name ");
            JLabel label3 = new JLabel("Enter your friend's last name ");

            JTextField user = new JTextField();
            JTextField luser = new JTextField();
            JTextField friend = new JTextField();
            JTextField lfriend = new JTextField();
            JButton login = new JButton("Login");
            JButton cancle = new JButton("Cancle");

            label.setBounds(10,20,140,25);
            panel1.add(label);
            user.setBounds(190,20,180,25);
            panel1.add(user);

            label1.setBounds(10,40,140,25);
            panel1.add(label1);
            luser.setBounds(190,40,180,25);
            panel1.add(luser);

            label2.setBounds(10,70,140,25);
            panel1.add(label2);
            friend.setBounds(190,70,180,25);
            panel1.add(friend);

            label3.setBounds(10,90,165,25);
            panel1.add(label3);
            lfriend.setBounds(190,90,180,25);
            panel1.add(lfriend);

            login.setBounds(289,130,80,20);
            panel1.add(login);

            cancle.setBounds(189,130,80,20);
            panel1.add(cancle);

            cancle.addActionListener(e->{
                  jf1.dispose();
                  ca.mainPageGUI();
            });

            login.addActionListener(e->{

            name=user.getText();
            lname=luser.getText();
            fname=friend.getText();
            flname=lfriend.getText();
            ip();
            jf1.dispose();
            });
            jf1.setResizable(false);
            jf1.setVisible(true);

          }
          void ip()
          {
            jf2.setTitle("IP");
            jf2.setSize(400,150);

            jf2.add(panel2);

            panel2.setLayout(null); 
            JLabel label = new JLabel("Enter your frinds IP:");
            JTextField Ip = new JTextField();
            JButton login = new JButton("Login");
            JButton cancle = new JButton("Cancle");

            label.setBounds(10,20,140,25);
            panel2.add(label);
            Ip.setBounds(190,20,180,25);
            panel2.add(Ip);

            login.setBounds(289,70,80,20);
            panel2.add(login);

            cancle.setBounds(189,70,80,20);
            panel2.add(cancle);

            login.addActionListener(e->{
            IP=Ip.getText();
               sendRequest(); 
               jf2.dispose(); 
            });
            cancle.addActionListener(e->{
                  sip.exit();
            });

            jf2.setResizable(false);
            jf2.setVisible(true);
          }
          void sendRequest()
          {            
            try {
              socket = new Socket(IP, PortNo);
              if(!socket.isClosed())
              {
              in= new DataInputStream(socket.getInputStream());
              out = new DataOutputStream(socket.getOutputStream()); 
              gui();
              writing();    
              reading();
              } 
              } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Request not accepted");
                ca.mainPageGUI();
              }     
          }            
          void reading()
          {
          Runnable reading=()->{
                try{ 
                  while (!socket.isClosed()) 
                  {
                    message = in.readUTF();
                    if (socket.isClosed()) 
                    {
                      messageArea.setEnabled(false);
                      socket.close();
                      break;
                    }
                    else
                    {
                    messageArea.append(message+"\n");
                    messageArea.setCaretPosition(messageArea.getDocument().getLength());                 
                    FileOutputStream fos = new FileOutputStream(new File(CurrentDate+" "+name.toUpperCase()+" "+lname.toUpperCase()+" and "+fname.toUpperCase()+" "+flname.toUpperCase()+"History.txt"), true);
                    PrintStream p = new PrintStream(fos);
                    p.println(message);
                    p.close();
                    fos.close();
                    }
                  } 
                 }catch(Exception e)
                 {
                     JOptionPane.showMessageDialog(this, "Conection Closed");
                 }    
             };
             new Thread(reading).start();
            }
            void writing()
            {    
             messageInput.addKeyListener(new KeyListener() {

             @Override
             public void keyTyped(KeyEvent e) {

             }
             @Override
             public void keyPressed(KeyEvent e) {

             }
             @Override
          
             public void keyReleased(KeyEvent e) {
             if (e.getKeyCode() == 10) {
             try{
               SimpleDateFormat dtf = new SimpleDateFormat("HH:mm:ss");
	             Date now = new Date();
               message = messageInput.getText();
               messageArea.append("["+dtf.format(now)+"]  "+name + " : " + message+"\n");
               out.writeUTF("["+dtf.format(now)+"]  "+name + " : " + message);
               out.flush();
               messageInput.setText("");
               messageInput.requestFocus();
              FileOutputStream fos = new FileOutputStream(new File(CurrentDate+" "+name.toUpperCase()+" "+lname.toUpperCase()+" and "+fname.toUpperCase()+" "+flname.toUpperCase()+"History.txt"), true);
               PrintStream p = new PrintStream(fos);
               p.println("["+dtf.format(now)+"]  "+name + " : " + message);
               p.close();
               fos.close();
            }catch(Exception ex)
            {
                 System.out.println("Connection Closed");
            } 
          }
        }
      }); 
      }
      void client() 
      { 
            JPanel panel =new JPanel();
            JFrame jf=new JFrame();
            jf.setTitle("Client Main Page");
            jf.setSize(285,300);
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jf.add(panel);

            panel.setLayout(null); 
    
            JButton  sendmessage= new JButton("Send Message");
            JButton  history= new JButton("Show History");
            JButton  sendfile= new JButton("Send File");
            JButton  receivefile= new JButton("Receive File");
            JButton back = new JButton("Back");


            sendmessage.setBounds(60,40,150,20);
            panel.add(sendmessage);

            history.setBounds(60,80,150,20);
            panel.add(history);

            sendfile.setBounds(60,120,150,20);
            panel.add(sendfile);

            receivefile.setBounds(60,160,150,20);
            panel.add(receivefile);

            back.setBounds(60,200,150,20);
            panel.add(back);

            sendmessage.addActionListener(e->{
                jf.dispose(); 
                login();
                
            });
            history.addActionListener(e->{
                jf.dispose();   
                History h1=new History();
                h1.historyLogin();
            });
            sendfile.addActionListener(e->{
                jf.dispose(); 
                FileSharing fs=new FileSharing();
                fs.sendFile();
            });
            receivefile.addActionListener(e->{
                jf.dispose();
                FileSharing fs=new FileSharing();
                fs.ip();
            });
            back.addActionListener(e->{
                jf.dispose();
                Chatapp ca=new Chatapp();
                ca.mainPageGUI();
            });
            jf.setResizable(false);
            jf.setVisible(true); 
      }
}

class History extends JFrame 
{
      String name,lname,fname,flname,HistoryDate,message;

      JLabel heading = new JLabel("Java Chat Application");
      JTextArea messageArea = new JTextArea();
      JTextArea historyArea = new JTextArea();
      JTextArea progressArea = new JTextArea();

      JPanel panel3 =new JPanel();
      JFrame jf3=new JFrame();

      Chatapp ca=new Chatapp();
      
      void historyLogin()
      {
            jf3.setTitle("History Login Page");
            jf3.setSize(400,240);
            jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jf3.add(panel3);

            panel3.setLayout(null);

            JLabel label = new JLabel("Enter your name ");
            JLabel label1 = new JLabel("Enter your last name ");
            JLabel label2 = new JLabel("Enter your friend name ");
            JLabel label3 = new JLabel("Enter your friend's last name ");
            JLabel label4 = new JLabel("Enter History Date:");
            JLabel label5 = new JLabel("(dd-mm-yyyy)");

            JTextField user = new JTextField();
            JTextField luser = new JTextField();
            JTextField friend = new JTextField();
            JTextField lfriend = new JTextField();
            JTextField date = new JTextField();
            JButton login = new JButton("Login");
            JButton cancle = new JButton("Cancle");

            label.setBounds(10,20,140,25);
            panel3.add(label);
            user.setBounds(190,20,180,25);
            panel3.add(user);

            label1.setBounds(10,40,140,25);
            panel3.add(label1);
            luser.setBounds(190,40,180,25);
            panel3.add(luser);

            label2.setBounds(10,70,140,25);
            panel3.add(label2);
            friend.setBounds(190,70,180,25);
            panel3.add(friend);

            label3.setBounds(10,90,165,25);
            panel3.add(label3);
            lfriend.setBounds(190,90,180,25);
            panel3.add(lfriend);

            label4.setBounds(10,120,180,25);
            panel3.add(label4);
            date.setBounds(190,120,180,25);
            panel3.add(date);
          
            label5.setBounds(10,135,165,25);
            panel3.add(label5);

            login.setBounds(289,160,80,20);
            panel3.add(login);

            cancle.setBounds(189,160,80,20);
            panel3.add(cancle);

            login.addActionListener(e->{

            name=user.getText();
            lname=luser.getText();
            fname=friend.getText();
            flname=lfriend.getText();
            HistoryDate=date.getText();
            history();
            jf3.dispose();
            });
            cancle.addActionListener(e->{
                 jf3.dispose();
                 ca.mainPageGUI(); 
            });
            jf3.setResizable(false);
            jf3.setVisible(true);       
      }
      void historyGUI()
      {
            JLabel date=new JLabel("Date:"+HistoryDate);
            Font font = new Font("ROBOTO", Font.PLAIN, 20);
            heading.setFont(font);
            historyArea.setFont(font);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            this.setTitle("History");    
            this.setSize(500,500);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            historyArea.setEditable(false);

            heading.setHorizontalAlignment(SwingConstants.CENTER);
            
            this.setLayout(new BorderLayout());
            JScrollPane scroll=new JScrollPane(historyArea);
            this.add(heading,BorderLayout.NORTH);
            this.add(scroll,BorderLayout.CENTER);
            this.add(date,BorderLayout.SOUTH);

            this.setVisible(true);              
      }
      void history()
      {
             try
             {
             FileReader fr=new FileReader(HistoryDate+" "+name.toUpperCase()+" "+lname.toUpperCase()+" and "+fname.toUpperCase()+" "+flname.toUpperCase()+"History.txt");
             BufferedReader br=new BufferedReader(fr);
             historyGUI();
			        while((message=br.readLine())!=null)
			        {
				             historyArea.append(message+"\n");
			        }
              br.close();
             }catch(Exception e)
             {  
                  JOptionPane.showMessageDialog(this, "File not found");
                  ca.mainPageGUI();
             }
        }
}
class ServerIP
{
       JPanel panel6=new JPanel();
       JFrame jf6=new JFrame();

       JPanel panel7=new JPanel();
       JFrame jf7=new JFrame(); 
       String IP;

       void serverIP()
       {    
           try{
              IP=InetAddress.getLocalHost().getHostAddress();
            }
            catch(Exception e)
            {}
            jf6.setTitle("Server IP");
            jf6.setSize(300,130);

            jf6.add(panel6);
            JLabel label = new JLabel(IP);
            JButton cancle = new JButton("Cancle");
            label.setBounds(20,20,140,25);
            panel6.add(label);

            cancle.setBounds(194,90,80,20);
            panel6.add(cancle);

            cancle.addActionListener(e->{
                 exit();
            });
            jf6.setResizable(false);
            jf6.setVisible(true);
       }
       void exit()
       {
            jf7.setSize(400,150);
            jf7.add(panel7);

            panel7.setLayout(null); 
            JLabel label = new JLabel("Do you really want to exit from this appliction?");
            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");

            label.setBounds(10,20,400,25);
            panel7.add(label);
            yes.setBounds(289,70,80,20);
            panel7.add(yes);

            no.setBounds(189,70,80,20);
            panel7.add(no);

            yes.addActionListener(e->{
               System.exit(0);
            });
            no.addActionListener(e->{
                  jf7.dispose();
            });

            jf7.setResizable(false);
            jf7.setVisible(true);
       }
       void close()
       {
          jf6.dispose();
       }
}
class FileSharing extends JFrame
{
   String filename,IP;
   File file;
   int PortNo=3214;
   
   ServerSocket ss;
   Socket s;
  
   ServerIP sip=new ServerIP();

   JPanel panel4=new JPanel();
   JFrame jf4=new JFrame();

   JPanel panel5=new JPanel();
   JFrame jf5=new JFrame();

   JPanel panel9=new JPanel();
   JFrame jf9=new JFrame();

   JProgressBar jb=new JProgressBar();

   void ip()
   {
            jf4.setTitle("IP");
            jf4.setSize(400,150);

            jf4.add(panel4);

            panel4.setLayout(null); 
            JLabel label = new JLabel("Enter your frinds IP:");
            JTextField Ip = new JTextField();
            JButton login = new JButton("Login");
            JButton cancle = new JButton("Cancle");

            label.setBounds(10,20,140,25);
            panel4.add(label);
            Ip.setBounds(190,20,180,25);
            panel4.add(Ip);

            login.setBounds(289,70,80,20);
            panel4.add(login);

            cancle.setBounds(189,70,80,20);
            panel4.add(cancle);

            login.addActionListener(e->{
            IP=Ip.getText();  
            jf4.dispose();
            recieveFile(); 
            });
            cancle.addActionListener(e->{
                  sip.exit();
            });
   
            jf4.setResizable(false);
            jf4.setVisible(true);
     } 
    void progressBar()
    {
        jb.setBounds(18,40,350,30); 
        jf9.setTitle("Progress");  
        jf9.add(jb);
        jf9.add(panel9);
        jf9.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jf9.setSize(400,150);
        
        JButton cancle = new JButton("Cancle");

        cancle.setBounds(300,100,80,20);
        panel9.add(cancle);

        cancle.addActionListener(e->{
                  sip.exit();
        });
        jf9.setVisible(true);  
        jf9.setResizable(false);

    }
     void sendFile()
     {  
          Runnable R1=()->{
           try{
             sip.serverIP();
             ss=new ServerSocket(PortNo);
             s=ss.accept();
             if(!s.isClosed())
             { 
             sip.close();
             DataOutputStream out1 = new DataOutputStream(s.getOutputStream()); 
             JFileChooser fileChooser =new JFileChooser();
             int response = fileChooser.showOpenDialog(null);
             if(response == JFileChooser.APPROVE_OPTION)
             {
               filename = fileChooser.getSelectedFile().getName();
               file = new File(fileChooser.getSelectedFile().getAbsolutePath());
             }
             out1.writeUTF(filename);
             FileInputStream fin=new FileInputStream(file);
             DataOutputStream dout=new DataOutputStream(s.getOutputStream());
             int r;
             progressBar();
             while((r=fin.read())!=-1) 
             {  
                  jb.setValue(r);
                  dout.write(r);
             }
             jf9.dispose();
               JOptionPane.showMessageDialog(this, "File transfer Complet");
               jf5.dispose();
               s.close();
               ss.close();
               fin.close();
               System.exit(0);
              }
             } catch (Exception e) {
               JOptionPane.showMessageDialog(this, "Connection is closed");    
               System.exit(0);
            }
          };
          new Thread(R1).start();
      }
      void recieveFile()
      {
          try{
             s=new Socket(IP,PortNo);
             if(!s.isClosed())
             {
             JOptionPane.showMessageDialog(this, "Request accepted please click on 'OK' button and wait for file");
             DataInputStream br1 = new DataInputStream(s.getInputStream());
             filename = br1.readUTF();
             String username=System.getProperty("user.name");
             FileOutputStream fout=new FileOutputStream("C:\\Users\\"+username+"\\Downloads\\"+filename);
             DataInputStream dout=new DataInputStream(s.getInputStream());
             int r;
             
               while((r=dout.read())!=-1) 
               {
                  
                  fout.write((char)r);
               }
               JOptionPane.showMessageDialog(this, "File received");
               s.close();
               fout.close();
               System.exit(0);
             }
             } catch (Exception e) {
               JOptionPane.showMessageDialog(this, "Connection is closed");  
               System.exit(0);
             }
       }
}
class Chatapp
{
   void mainPageGUI()
   {
      Server s=new Server();
      Client c=new Client(); 
      JPanel panel3 =new JPanel();
      JFrame jf3=new JFrame();
      jf3.setTitle("Main Page");
      jf3.setSize(400,150);
      jf3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      jf3.add(panel3);
      panel3.setLayout(null); 
    
      JButton server = new JButton("Server");
      JButton client = new JButton("client");
      JButton cancle = new JButton("Cancle");

      server.setBounds(60,45,80,20);
      panel3.add(server);

      client.setBounds(160,45,80,20);
      panel3.add(client);

      cancle.setBounds(260,45,80,20);
      panel3.add(cancle);

      server.addActionListener(e->{ 
      jf3.dispose();
      s.server();
      });
            
      client.addActionListener(e->{
      jf3.dispose();
      c.client();  
      });
            
      cancle.addActionListener(e->{
      System.exit(0);
      });

      jf3.setResizable(false);
      jf3.setVisible(true);
      
   }     
   public static void main(String arg[])
   {
      Chatapp ca=new Chatapp();
      ca.mainPageGUI();
   } 
}