/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;

/**
 *
 * @author Menios
 */

public class ButtonsShape extends JButton {

    public ButtonsShape(int x, int y, String g) 
    {
          
       this.setBounds(x, y, 30, 30);   
       this.setText(g);
       this.setFont(new Font("Arial", Font.PLAIN, 15));
       this.setMargin(new Insets(0, 0, 0, 0));
    }   
}
