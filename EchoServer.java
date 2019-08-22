/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MourlakiaS
 */
public class EchoServer {
    
  
    
    
    public static void main(String[] args) throws ClassNotFoundException{
        try (ServerSocket serverSocket = new ServerSocket(7777);
             Socket clientSocket = serverSocket.accept();
             ObjectOutputStream toClient = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream fromClient = new ObjectInputStream(clientSocket.getInputStream()); ) {
            
                String[] information = (String[]) fromClient.readObject();
                for(int i=0;i<information.length;i++)
                    System.out.println(information[i]);
                
                String approved = "OK";
                
                toClient.writeObject(approved);
                toClient.flush();
            
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        catch (ClassNotFoundException ex){
            System.err.println(ex);
        }
    }
}
