/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;

/**
 *
 * @author Menios
 */
public class Pawn {
    
    Map<String, JButton> position = new HashMap();
    Map<String, Integer> endmap = new HashMap();
    Map<String, Integer> gia_kinhsh = new HashMap();
    private static int b;
    
    public Pawn (){
        
    }
    
    public void deletePawn(String s){
        position.remove(s);
        endmap.remove(s);
        gia_kinhsh.remove(s);
    }
    
    public String findmap(int x)
    {
        Set<Map.Entry<String, Integer>> entries = gia_kinhsh.entrySet();
        for (Map.Entry<String, Integer> e : entries)  {
            if( e.getValue()==x)
                return e.getKey();
        }
        return "";
    }
    
    public void setPawn(JButton x, int kappa){
        b++; 
        position.put("pioni"+b,x);
        gia_kinhsh.put("pioni"+b, kappa);
        endmap.put("pioni"+b, 0);
    }
    
        
    public void refreshPawn(String key, JButton neo, int future, int dice){
        position.replace(key,neo);
        gia_kinhsh.replace(key,future);
        endmap.replace(key,endmap.get(key)+dice);
    }
    
    public int findPawnsInLocation(int thesh){
        int value=0;
        Set<Map.Entry<String, Integer>> entries = gia_kinhsh.entrySet();
        for (Map.Entry<String, Integer> e : entries)  {
            if(e.getValue() == thesh)
                value++;
        }
        return value;
    }
    
    
    public int movePawn(int dice, JButton neo, int future, int tp){
        //vriskei to current gia to refreshpawn 
        // kinhsh termatismoy
            for(Map.Entry<String,Integer> pair : endmap.entrySet()){ 
                if(pair.getValue()>50  && future>52)
                {
                    if(pair.getValue()+dice+2==58)
                    {
                        JButton temp = position.get(pair.getKey());
                        temp.setText("");
                        deletePawn(pair.getKey());
                        return -1; // termatise 
                    }
                    else 
                        return -2; // prepei na fereis akribos tin zargia     
                }
                else if(pair.getValue()+dice+2==future){    
                    JButton temp;
                    temp = position.get(pair.getKey());
                    if(findPawnsInLocation(pair.getValue()) == 1 ){
                        temp.setText("");
                    }
                    refreshPawn(pair.getKey(), neo, future, dice);
                    if(future == 58){
                        deletePawn(pair.getKey());
                        return -1;
                    }
                    else
                        return 1;      
                }
                if(pair.getValue()>=50)
                    return 0;
            } 
        if(tp==0) // fast travel
        {
            int x;
            if(future!=12)
            {
                x=future-13;
            }
            else 
                x=51;
            
            for(Map.Entry<String,Integer> pair : endmap.entrySet()){
                if(pair.getValue()+13> 50) //anti gia dice vale x
                {
                    return -4;
                }
            }
            
            for(Map.Entry<String,Integer> pair : gia_kinhsh.entrySet())
            { 
                if(pair.getValue() == x) //anti gia dice vale x
                {
                    JButton temp;
                    temp = position.get(pair.getKey());
                    if(findPawnsInLocation(pair.getValue()) == 1 ){
                        temp.setText("");
                    }
                    refreshPawn(pair.getKey(), neo, future, dice);
                    return x;
                }           
            }
            
        }
        
        int x;
        
        x = future-dice;
        
        if(x <= 0)
            x = 52 + x;
       
        for(Map.Entry<String,Integer> pair : gia_kinhsh.entrySet()){ 
            if(pair.getValue() == x && future<=52) //anti gia dice vale x
            {
                JButton temp;
                temp = position.get(pair.getKey());
                if(findPawnsInLocation(pair.getValue()) == 1 ){
                        temp.setText("");
                    }
                refreshPawn(pair.getKey(), neo, future, dice);
                return x;
            }           
        }
        return 0;
    }     
}
