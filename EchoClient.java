/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author MourlakiaS
 */
public class EchoClient {
    
    private Socket echoSocket;
    
    private static int kappa;
    
    private JFrame frame = new JFrame();
    
    String[] info = new String[4];
    
    JTextField t1 = new JTextField(5);
    JTextField t2 = new JTextField(5);
    
    List<String> colorsAvailable = new ArrayList<>(); 
    
    public EchoClient(){
        
        colorsAvailable.add("RED");
        colorsAvailable.add("GREEN");
        colorsAvailable.add("BLUE");
        colorsAvailable.add("YELLOW");
        
        try{ 
            this.echoSocket = new Socket("localhost", 7777);
            
            frame = new CreateWindowInput().parframe;
            frame.setTitle("User Info: Payer-> 1" );
            frame.setVisible(true);
            
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        
    }
    
    
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new EchoClient(); // Let the constructor do the job
         }
      });
    }
    
    private class CreateWindowInput extends JFrame{
        
        public JFrame parframe = new JFrame();
        
        public CreateWindowInput(){
        
            


            parframe.setTitle("User info");
            //frame.setSize(300, 200);
            parframe.setMinimumSize(new Dimension(300,200)); 
            parframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel panel = new JPanel();

            panel.setLayout(new GridLayout(0, 1));

            JPanel cp1 = new JPanel();
            cp1.add(new JLabel("Name:"));
            cp1.add(t1);
            panel.add(cp1);

            JPanel cp2 = new JPanel();
            cp2.add(new JLabel("Color:"));
            cp2.add(t2);
            panel.add(cp2);

            JButton btn = new JButton("Submit");
            panel.add(btn);
            btn.addActionListener(new HandleInfo());

            parframe.setLayout(new FlowLayout(FlowLayout.CENTER));  
            parframe.add(panel);
            //frame.setVisible(true);
        }        
    }
    
    private class HandleInfo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(!t1.getText().equals("") && colorsAvailable.contains(t2.getText().toUpperCase())){
                info[kappa] = t1.getText() + " " + t2.getText();
                colorsAvailable.remove(t2.getText().toUpperCase());
                t1.setText("");
                t2.setText("");
                frame.dispose();
                if(kappa == 3){
                    //mesa sthn if tha kaleitai to javaapplication6
                    try(ObjectOutputStream toServer = new ObjectOutputStream(echoSocket.getOutputStream());
                        ObjectInputStream fromServer = new ObjectInputStream(echoSocket.getInputStream());){


                        toServer.writeObject(info);
                        toServer.flush();

                        String approved = (String) fromServer.readObject();
                        System.out.println(approved);
                        if(approved.equals("OK")){
                            new Javaapplication6(info);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.exit(0);
                }
                else{

                    kappa++;

                    frame = new CreateWindowInput().parframe;
                    frame.setTitle("User Info: Payer-> " + (kappa+1));
                    frame.setVisible(true);
                }
            }
            else{
                t1.setText("");
                t2.setText("");
                frame.dispose();
                frame = new CreateWindowInput().parframe;
                frame.setTitle("User Info: Payer-> " + (kappa+1));
                frame.setVisible(true);
            }
        }
    }
}