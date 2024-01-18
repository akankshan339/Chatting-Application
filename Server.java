package chatting.application;

 import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Server implements ActionListener{
 //public class Server extends JFrame implements ActionListener{    //OR
   static  JFrame f=new JFrame();
   JTextField text;
   JPanel p2;
   static Box verticle=Box.createVerticalBox();
   static DataOutputStream dout;
   
Server()
    {
        f.setLayout(null);
        JPanel p1=new JPanel();
        p1.setBounds(0,0,450,70);
     // p1.setBackground(Color.GREEN);
        p1.setBackground(new Color(7,94,84));
        p1.setLayout(null);
        f.add(p1);
        
      //  ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("C:\\Users\\hp\\Documents\\NetBeansProjects\\Chatting Application\\src\\icons"));
      ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/3.png")); 
      Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel back =new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);
        
        back.addMouseListener(new MouseAdapter() {
            @Override
        public void mouseClicked(MouseEvent ae)
        {
            System.exit(0);
        }
        });
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/akanksha.png"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel profile =new JLabel(i6);
        profile.setBounds(40,10,50,50);
        p1.add(profile);
        
//        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/vc.png"));
//        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
//        ImageIcon i9=new ImageIcon(i8);
//        JLabel vedio =new JLabel(i9);
//        profile.setBounds(300,20,30,30);
//        p1.add(vedio);

        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/Call.png"));
        Image i11=i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone =new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);

        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
        Image i14=i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel morebt =new JLabel(i15);
        morebt.setBounds(420,20,10,25);
        p1.add(morebt); 

        JLabel name =new JLabel("Akanksha");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("SEN-SERIF",Font.BOLD,18));
        p1.add(name);
        
        JLabel status =new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("SEN-SERIF",Font.BOLD,14));
        p1.add(status);

      //  JPanel p2=new JPanel();
        p2=new JPanel();
        p2.setBounds(5,75,440,525); //570
        f.add(p2);
        
        //JTextField text=new JTextField();
        text=new JTextField();
        text.setBounds(5,600,310,40); //655
        text.setFont(new Font("SEN-SERIF",Font.PLAIN,16));
        f.add(text);
        
        JButton send=new JButton("Send");
        send.setBounds(320,600,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        send.setFont(new Font("SEN-SERIF",Font.PLAIN,16));
        f.add(send);
        
        f.setSize(450,700);
        f.setLocation(200,30);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);
    }
@Override
public void actionPerformed(ActionEvent ae)
{
    try{
    String out=text.getText();
   // System.out.println(out);
   //JLabel output=new JLabel(out);
  // JPanel p3=new JPanel();
   JPanel p3=formatLabel(out);
   //p3.add(output);
   
   p2.setLayout(new BorderLayout());
   JPanel right=new JPanel( new BorderLayout());
   right.add(p3,BorderLayout.LINE_END);
   verticle.add(right);
   verticle.add(Box.createVerticalStrut(15));
   p2.add(verticle,BorderLayout.PAGE_START);
   
   dout.writeUTF(out);
   text.setText("");
   
   f.repaint();
   f.invalidate();
   f.validate();
   }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}
public static JPanel formatLabel(String out)
{
    JPanel panel=new JPanel();
    panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
  //  JLabel output=new JLabel(out);  //OR
    JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
    output.setFont(new Font("Tahoma",Font.PLAIN,16));
    output.setBackground(new Color(7,94,84));
    output.setForeground(Color.WHITE);
  // output.setBackground(new Color(37,211,102));
    output.setOpaque(true);
    output.setBorder(new EmptyBorder(15,15,15,50));
    panel.add(output);
    Calendar cal=Calendar.getInstance();
    SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
    
    JLabel time=new JLabel();
    time.setText(sdf.format(cal.getTime()));
    panel.add(time);
    
   
    return panel;
    
}
    public static void main(String args[])
    {
        Server serv=new Server();
        try{
            ServerSocket skt=new ServerSocket(6001);
              while(true)
              {
                Socket so=skt.accept();
                DataInputStream din=new DataInputStream(so.getInputStream());
                 dout=new DataOutputStream(so.getOutputStream());
                 while(true)
                 {
                    String msg=din.readUTF();
                    JPanel panel=formatLabel(msg);
                    JPanel left=new JPanel(new BorderLayout());
                    left.add(panel,BorderLayout.LINE_START);
                    verticle.add(left);
                    f.validate();
                  }
              }
         }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}

    

