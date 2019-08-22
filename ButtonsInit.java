/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import javax.swing.JButton;

/**
 *
 * @author zed
 */
public class ButtonsInit extends JButton{
    private int stringnum;
    
    public ButtonsInit(int x){
        
        JButton button = new JButton();
        setValue(x);
    }
    
    private void setValue(int x){
        this.stringnum = x;
    }
    
    public int getValue(){
        return this.stringnum;
    }
   
}
