/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author zed
 */
public class Players {
    
    private String name;
    private String symbol;
    private String color;
    public Pawn pioni = new Pawn();
    // object gia to pawn
    
    public Players(String name,String color){
        setName(name);
        setColor(color);
    }
    
    private void setName(String Name){
        this.name = Name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getColor(){
        return this.color;
    }
    
    public String getSymbol(){
        return this.symbol;
    }
    
    private void setColor(String color){
        if(color.toLowerCase().equals("red"))
            this.symbol = "@";
        else if(color.toLowerCase().equals("green"))
            this.symbol = "#";
        else if(color.toLowerCase().equals("blue"))
            this.symbol = "%";
        else
            this.symbol = "$";
        this.color = color.toUpperCase();
    }
}
