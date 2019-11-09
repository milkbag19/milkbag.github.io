
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client
{
    private static Socket socket;
    private static JFrame mainFrame = new JFrame();
    private static JTextArea chatArea = new JTextArea();
    private static JTextField inputArea = new JTextField();
    private static JScrollPane chatScrollPane;
    public static void main(String args[]) throws Exception{

        //start Connection to the server through a socket
        socket = new Socket("174.115.21.188", 33676);
        //get both input and output for the socket to the server
        //So that we will be able to communicate with it.
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner scan = new Scanner(System.in);

        JFrame nameFrame = new JFrame("Name selection");
        JTextField nameInputArea = new JTextField();
        TitledBorder titled = new TitledBorder("Nickname");
        //nameInputArea.setBorder(titled);
        nameInputArea.setPreferredSize(new Dimension(300,30));
        nameFrame.add(nameInputArea);
        nameFrame.pack();
        nameFrame.setVisible(true);

        nameInputArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String name = nameInputArea.getText();
                    dos.writeUTF(name);
                    nameFrame.setVisible(false);
                    mainFrame.setVisible(true);

                    //The client chat GUI
                    mainFrame.setSize(400,400);
                    chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.PAGE_AXIS));
                    chatArea.setEditable(false);
                    chatScrollPane = new JScrollPane(chatArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                    chatScrollPane.setPreferredSize(new Dimension(500,260));
                    inputArea.setPreferredSize(new Dimension(500,40));
                    JBox mainBox = JBox.vbox(chatScrollPane,inputArea);
                    mainFrame.add(mainBox);
                    mainFrame.pack();
                    mainFrame.setTitle(name);

                    //adding a window listener so that when the user closes their window,
                    //the client sends the logout message to the server, informing it to remove us from its database.
                    mainFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try {
                                dos.writeUTF("logout#" + name);
                                System.exit(0);
                            }catch(Exception f){}
                        }
                        @Override
                        public void windowClosed(WindowEvent e) {
                            try {
                                dos.writeUTF("logout#" + name);
                                System.exit(0);
                            }catch(Exception f){}
                        }
                    });

                    //creating a new thread so that messages may be sent without being interrupted by other threads.
                    Thread sendMessage = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating an actionListener for the text input are so that whenever a user presses enter,
                            //when the action listener is activated, the message in the text field will be send through the socket back to the server.
                            //the server will then read the message, and send it to all users
                            inputArea.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent actionEvent) {
                                    try{
                                        //checking if the message sent is the logout keyword
                                        //if true, the jframe will close and the logout keyword will be sent to the server
                                        //the server will then recognize the keyword and shutdown the thread associated with this client
                                        if(inputArea.getText().toLowerCase().equals("logout")){
                                            dos.writeUTF(inputArea.getText()+"#"+name);
                                            socket.close();
                                            System.exit(0);
                                        }

                                        dos.writeUTF(inputArea.getText()+"#"+name);
                                        chatArea.append("You : "+inputArea.getText()+"\n");
                                        chatArea.setCaretPosition(chatArea.getDocument().getLength());
                                        inputArea.setText("");
                                    }catch(Exception e){}
                                }
                            });
                        }
                    });

                    //creating new thread for reading the messages from the server so that it does not get interrupted by other threads.
                    Thread readMessage = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(true) {
                                String message;
                                try {
                                    message = dis.readUTF();
                                    chatArea.append(message+"\n");
                                    chatArea.setCaretPosition(chatArea.getDocument().getLength());
                                } catch (Exception e) {
                                }
                            }
                        }
                    });

                    //starting each thread for sending and receiving messages from/to the server.
                    sendMessage.start();
                    readMessage.start();
                }catch(Exception e){}
            }
        });
        //The first thing the server does when the client connects is tell you which client you are.
        //Therefore we must read what the server has sent and use it in the client code so we know who we are.



    }
}