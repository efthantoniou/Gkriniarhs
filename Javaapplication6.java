/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author Menios
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Javaapplication6 extends JFrame{
    public DrawCanvas canvas;
    Font pioniafont = new Font("Arial", Font.PLAIN, 12);
    Players[] paixtes = new Players[4];
    
    JLabel diceresult = new JLabel();
    JLabel pinfo = new JLabel();
    
    Map<String, Integer> finishLine = new HashMap();
    
    
    ButtonsInit[][] k1 = new ButtonsInit[3][6];
    ButtonsInit[][] k2 = new ButtonsInit[3][6];
    ButtonsInit[][] k3 = new ButtonsInit[6][3];
    ButtonsInit[][] k4 = new ButtonsInit[6][3];
    ButtonsShape[] button = new ButtonsShape[16];
    JButton rolld = new JButton ("Roll Dice");
    int peri=1;
    int fasttp=0;
    int rollSixOnce =0;
    Queue<String> myQ = new LinkedList<>();
    
    public Javaapplication6(String[] information)
    {
        for(int i=0;i<4;i++){
            String[] temp = information[i].split(" "); // onoma red
            finishLine.put(temp[1].toUpperCase(),0);
            paixtes[i] = new Players(temp[0],temp[1].toUpperCase());
        }
        
       if(!myQ.offer("RED"))
            System.exit(1);
        if(!myQ.offer("GREEN"))
            System.exit(1);
        if(!myQ.offer("BLUE"))
            System.exit(1);
        if(!myQ.offer("YELLOW"))
            System.exit(1);
        
       canvas = new DrawCanvas();
        
       Container cp = getContentPane();
      
       // values of map
       int temp=5;
       int temp2=53;
       for(int i=0;i<3;i++) //green
       {
           for(int j=0;j<6;j++)
           {
                if(i==1 && j>0){ 
                    k1[i][j] = new ButtonsInit(temp2);
                    k1[i][j].setBackground(Color.green);
                    temp2++;
                }
                else{
                    if(i==0){
                        k1[i][j] = new ButtonsInit(peri+temp);
                        peri++;
                        temp-=2;
                    }
                    else if(i==2 || j==0){
                        k1[i][j] = new ButtonsInit(peri);
                        peri++;
                    }
                }
                k1[i][j].setBounds(152+i*33,0+j*25,30,20);
                k1[i][j].addActionListener(new Move());
                k1[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                k1[i][j].setMargin(new Insets(0, 0, 0, 0));
                cp.add(k1[i][j]);
           }
       }
       k1[2][1].setBackground(Color.green);
       k1[2][4].setBackground(Color.orange);
       
       int mm=0;
       temp=5;
       temp2=53;
       for(int i=0;i<6;i++) // blue
       { 
           for(int j=0;j<2;j++)
           {               
                if(i>0  && j==1)   
                {
                    k3[i][j] = new ButtonsInit(temp2);
                    k3[i][j].setBackground(Color.blue);
                    temp2++;
                }
                else if(i==0)
                {
                    k3[i][j] = new ButtonsInit(peri+temp);
                    peri++;
                }
               else {
                if(j==0 && i!=0 && mm==0)
                {
                    temp-=3;
                   k3[i][j] = new ButtonsInit(peri+temp);
                   peri++; 
                   mm++;
                }
                else if(j==0 && i!=0 && mm!=0)
                {
                    temp-=2;
                   k3[i][j] = new ButtonsInit(peri+temp); 
                   peri++; 
                 
                }
                }
                k3[i][j].setBounds(380-i*25,152+j*33,20,30);
                k3[i][j].addActionListener(new Move());
                k3[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                k3[i][j].setMargin(new Insets(0, 0, 0, 0));
                cp.add(k3[i][j]);
           }
       }
       int v=2;
       for(int i=0;i<6;i++)
       {
            k3[i][v] = new ButtonsInit(peri); 
            peri++;
            k3[i][v].setBounds(380-i*25,152+v*33,20,30);
            k3[i][v].addActionListener(new Move());
            k3[i][v].setFont(new Font("Arial", Font.PLAIN, 12));
            k3[i][v].setMargin(new Insets(0, 0, 0, 0));
            cp.add(k3[i][v]);
       }
                
       k3[1][2].setBackground(Color.blue);
       k3[4][2].setBackground(Color.orange);
       
       temp=5;
       temp2=53;
       for(int i=0;i<3;i++) //yellow
       {
           for(int j=0;j<6;j++)
           {
                if(i==1 && j>0){ 
                    k2[i][j] = new ButtonsInit(temp2);
                    k2[i][j].setBackground(Color.yellow);
                    temp2++;
                }
                else{
                    if(i==0){
                        k2[i][j] = new ButtonsInit(peri+temp);
                        peri++;
                        temp-=2;
                    }
                    else if(i==2 || j==0){
                        k2[i][j] = new ButtonsInit(peri);
                        peri++;
                    }
                }
                k2[i][j].setBounds(152+(2-i)*33,255+(5-j)*25,30,20);
                k2[i][j].addActionListener(new Move());
                k2[i][j].setFont(new Font("Arial", Font.PLAIN, 15));
                k2[i][j].setMargin(new Insets(0, 0, 0, 0));
                cp.add(k2[i][j]);    
           }
        }
        k2[2][1].setBackground(Color.yellow);
        k2[2][4].setBackground(Color.orange);
       
        mm=0;
        temp=5;
        temp2=53;
        for(int i=0;i<6;i++) // red
        { 
           for(int j=0;j<2;j++)
           {               
                if(i>0  && j==1)   
                {
                    k4[i][j] = new ButtonsInit(temp2);
                    k4[i][j].setBackground(Color.red);
                    temp2++;
                }
                else if(i==0)
                {
                    k4[i][j] = new ButtonsInit(peri+temp);
                    peri++;
                }
               else {
                if(j==0 && i!=0 && mm==0)
                {
                    temp-=3;
                   k4[i][j] = new ButtonsInit(peri+temp);
                   peri++; 
                   mm++;
                }
                else if(j==0 && i!=0 && mm!=0)
                {
                   temp-=2;
                   k4[i][j] = new ButtonsInit(peri+temp); 
                   peri++; 
                 
                }
                }
                k4[i][j].setBounds(0+i*25,218-j*33,20,30);
                k4[i][j].addActionListener(new Move());
                k4[i][j].setFont(new Font("Arial", Font.PLAIN, 12));
                k4[i][j].setMargin(new Insets(0, 0, 0, 0));
                cp.add(k4[i][j]);
           }
        }
        v=2;
        for(int i=0;i<6;i++)
        {
            k4[i][v] = new ButtonsInit(peri); 
            peri++;
            k4[i][v].setBounds(0+i*25,218-v*33,20,30);
            k4[i][v].addActionListener(new Move());
            k4[i][v].setFont(new Font("Arial", Font.PLAIN, 12));
            k4[i][v].setMargin(new Insets(0, 0, 0, 0));
            cp.add(k4[i][v]);
        }
                
        k4[1][2].setBackground(Color.red);
        k4[4][2].setBackground(Color.orange);
       
        //pionia  stin bash
       
        int i,j;
        int x = 30;
        int y = 30;
        int flag = 0;
        String[] arst = new String[4];
        arst[0] = "@";arst[1] = "#";arst[2] ="$" ;arst[3] = "%";
        for(int dh=0;dh<4;dh++)
        {
            for(i=0;i<2;i++){
                for(j=0;j<2;j++){
                   button[(dh*4) + (i+j+flag)] = new ButtonsShape(x+(i*50), y+(j*50),arst[dh]);
                   button[(dh*4) + (i+j+flag)].setBackground(Color.white);
                   cp.add(button[(dh*4) + (i+j+flag)]);
                   cp.revalidate();
                }
                flag=1;
            }
            flag=0;
            if(dh==0)
                x=280;
            else if(dh==1){
                x=30;
                y=280;
            }
            else
                x=280;
        }
        // /pionia
        cp.add(canvas);
        cp.revalidate();
       
        //  Menu
        Font customFont1 = new Font("Cursive", Font.PLAIN, 40 );
        Font customFont2 = new Font("Cursive", Font.PLAIN, 30 );
        
        Container contentPane = getContentPane();
        JPanel pane = new JPanel(new FlowLayout()); // main menu panel (pane)
        BoxLayout textBoxLayout = new BoxLayout(pane, BoxLayout.PAGE_AXIS);
        pane.setLayout(textBoxLayout);
      
        JPanel info = new JPanel(); // player's turn 
        
        //  dice
        JPanel dice = new JPanel();      
        // /dice      
        
        JLabel lb = new JLabel("MENU"); 
        lb.setFont(customFont1) ;
        pane.setPreferredSize(new Dimension(400,0));
        pane.add(lb);
        pane.add(dice);
        dice.add(rolld);
        dice.add(diceresult);
        pane.add(info,BorderLayout.CENTER);
       
        //  endturnbtn + closebtn                      
        JPanel endt = new JPanel(); 
        JButton endturnbtn = new JButton("End Turn");
        endturnbtn.addActionListener(new EndTurn());
        endturnbtn.setPreferredSize(new Dimension(400, 30));
        endt.add(endturnbtn); 
        pane.add(endt,BorderLayout.SOUTH);
        JButton close = new JButton("Close");
        close.addActionListener(new CloseListener());
        close.setPreferredSize(new Dimension(400, 30));
        endt.add(close);
       // /endturnbtn + closebtn
              
        info.add(pinfo);
               
        String top = myQ.element();
        pinfo.setText(top + " player turn");
       
        contentPane.add("East",pane);
        TriangleButton br =  new TriangleButton(1);
        br.addActionListener(new Move());
        br.setContentAreaFilled(false);
        br.setBorderPainted(false); 
        br.setBorder(null);
        contentPane.add(br);

        TriangleButton bg =  new TriangleButton(2);
        bg.addActionListener(new Move());
        bg.setContentAreaFilled(false);
        bg.setBorderPainted(false); 
        bg.setBorder(null);
        contentPane.add(bg);

        TriangleButton by =  new TriangleButton(3);
        by.addActionListener(new Move());
        by.setContentAreaFilled(false);
        by.setBorderPainted(false); 
        by.setBorder(null);
        contentPane.add(by);

        TriangleButton bb =  new TriangleButton(4);
        bb.addActionListener(new Move());
        bb.setContentAreaFilled(false);
        bb.setBorderPainted(false); 
        bb.setBorder(null);
        contentPane.add(bb);
        contentPane.add(canvas);

        setMinimumSize(new Dimension(850,450));   
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new CloseWindow());
        setSize(1000,600);
        setTitle("Gkriniarhs-QQer");  
        setVisible(true);    
       
        //listeners

        rolld.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               int x=(int) ((Math.random() * 6)+1);            
               diceresult.setText(String.valueOf(x));
               diceresult.setFont(customFont2);
               rolld.setEnabled(false);
           }
       });
   
        //base buttons kinisi prwti fora poa basi
        for(i=0;i<16;i++)  
        {
            button[i].addActionListener(new ActionListener(){        
                public void actionPerformed(ActionEvent e){ 
                           
                    if(diceresult.getText()!="" && ((JButton) e.getSource()).getText()!="" && Integer.parseInt(diceresult.getText()) == 5) ///////////////////
                    {
                        String text = ((JButton) e.getSource()).getText();
                        int id = findPlayerFromSymbol(text);
                        int flag=0;
                        
                        if(text.equals("@") && myQ.element().equals("RED"))
                        {         
                            concequenses(k4[1][2], text,k4[1][2].getValue());
                            paixtes[id].pioni.setPawn(k4[1][2],k4[1][2].getValue());
                            flag=1;
                        }
                        else if(text.equals("$") && myQ.element().equals("YELLOW"))
                        {
                            concequenses(k2[2][1], text,k2[2][1].getValue());
                            paixtes[id].pioni.setPawn(k2[2][1], k2[2][1].getValue());
                            flag=1;
                        }
                        else if(text.equals("#") && myQ.element().equals("GREEN"))
                        {
                            concequenses(k1[2][1], text,k1[2][1].getValue());
                            paixtes[id].pioni.setPawn(k1[2][1],k1[2][1].getValue());
                            flag=1;
                        }
                        else if(text.equals("%") && myQ.element().equals("BLUE"))
                        {
                            concequenses(k3[1][2], text,k3[1][2].getValue());
                            paixtes[id].pioni.setPawn(k3[1][2],k3[1][2].getValue());
                            flag=1;
                        }
                        if(flag==1){
                            rolld.setEnabled(true);
                            ((JButton) e.getSource()).setText("");
                            diceresult.setText("");
                            pinfo.setText(myQ.element() + " player turn. \n You can roll again!");
                        }
                        else{
                            pinfo.setText("You can only change values of your pawns");
                        }
                        
                    }
                    else
                        pinfo.setText("You need to roll 5 to move the pawn in the board");
                }
            });
        }
       
    }
    
    private class CloseWindow extends WindowAdapter{
        public void windowClosing(WindowEvent e)
            {
                int answer = JOptionPane.showConfirmDialog(Javaapplication6.this, "Are you sure \nyou want to close the window?", "Window closing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (answer == JOptionPane.YES_OPTION)  {
                    Javaapplication6.this.dispose();
                }
                else  {
                    // nothing
                }
            }
    }

    private class EndTurn implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String top;
            
            rolld.setEnabled(true);
            
            myQ.offer(myQ.remove());
            top = myQ.element();
            pinfo.setText(top + " player turn");
            diceresult.setText("");
            fasttp=1;
            rollSixOnce = 0;
        }
    }
    
    private class CloseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //DO SOMETHING
            System.exit(0);
        }
    }
    
    private class Move implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //DO SOMETHING         
            if(diceresult.getText()!="" || fasttp==0)
            {
                int move;
                if(fasttp==0 && diceresult.getText()=="")
                     move=13;
                else
                     move = Integer.parseInt(diceresult.getText()); // h zargia pou efere
                int x; // to koumpi pou patise o xrhsths se akaaireo             
                int y;// (prohgoumeni thesi se akaireo) an > 0
                if(move == 6 && rollSixOnce ==0)
                {
                    rollSixOnce++;
                    rolld.setEnabled(true); 
                    pinfo.setText(myQ.element() + " player turn. \n You can roll again!!");
                }
                
                Players temp = null;
                for(int i=0;i<4;i++){
                    if(paixtes[i].getColor().equalsIgnoreCase(myQ.element())){
                        temp = paixtes[i];
                        //System.out.println(paixtes[i].getColor());
                    }
                    else
                        continue;
                }
                
                if(temp!=null){
                    if(e.getSource() instanceof ButtonsInit){
                        x =((ButtonsInit) e.getSource()).getValue();
                        y = temp.pioni.movePawn(move, ((ButtonsInit) e.getSource()), x, fasttp);
                    }
                    else{ 
                        x =((TriangleButton) e.getSource()).getValue();
                        y = temp.pioni.movePawn(move, ((TriangleButton) e.getSource()), x, fasttp);
                    }

                    if( y > 0){    
                        concequenses(((ButtonsInit) e.getSource()),temp.getSymbol(),x);
                        diceresult.setText("");
                    }
                    else if(y == -1){
                        int exitWin =(int) finishLine.get(myQ.element());
                        finishLine.replace(myQ.element(),(exitWin + 1));
                        
                        if(exitWin == 3){
                            System.out.println(myQ.element() + " Player wins");
                            System.exit(0);
                        }
                        pinfo.setText(myQ.element()+ " player turn. \n One of your pawns has finished");
                    }
                    else if(y == -2)
                        pinfo.setText(myQ.element()+ " player turn. \n You need specific roll to end this pawn");
                    else if(y == -4)
                        pinfo.setText(myQ.element()+ " player turn. \n You cant fast travel this pawn");
                    else 
                        pinfo.setText(myQ.element() + " player turn. \n Incorrect input");
                    
                }
            }    
        }
    }
    
    private void concequenses(JButton newbut, String symbol ,Integer x){
        // to koumpi pou patise, poianou einai kai to kou,pi poy patise se akereo
        int i=0;
        int id=0;        
        if(newbut.getText()!=""){
            if(newbut.getText().equals("@")){
                    i=0;
                    id = findPlayerFromColor("RED");
            }
            else if(newbut.getText().equals("#")){
                    i=4;
                    id = findPlayerFromColor("GREEN");
            }
            else if(newbut.getText().equals("$")){
                    i=8;
                    id = findPlayerFromColor("YELLOW");
            }
            else if(newbut.getText().equals("%")){
                    i=12;
                    id = findPlayerFromColor("BLUE");
            }
            
            String s = paixtes[id].pioni.findmap(x);
            
            if(!symbol.equals(paixtes[id].getSymbol())){
                paixtes[id].pioni.deletePawn(s);
                for(int temp = i;temp<i+4;temp++){
                    if(button[temp].getText().equals("")){
                        button[temp].setText(paixtes[id].getSymbol());
                        break;
                    }
                }
            }
        }
        newbut.setText(symbol); 
        newbut.setFont(pioniafont);
        newbut.setMargin(new Insets(0, 0, 0, 0));
        

        if(x==12 || x==25 || x==38 || x==51) // fast travel //star
        {
             fasttp--;
             pinfo.setText(myQ.element() + " player turn. \n You can fast travel to the next orangle button\nonce per turn");    
        }
    }
    
    private int findPlayerFromColor(String color){
        for(int i =0;i<4;i++){
            if(paixtes[i].getColor().equals(color)){
                return i;
            }
        }
        return -1;
    }
    
    private int findPlayerFromSymbol(String symbol){
        for(int i=0;i<4;i++){
            if(paixtes[i].getSymbol().equals(symbol))
		return i;
	}
	return -1;
    }
    
    private class DrawCanvas extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);     // paint parent's background
            setBackground(Color.LIGHT_GRAY);
            
            g.setColor(Color.red);
            g.fillRect(0, 0, 150, 150);

            g.setColor(new Color(79,229,0));//Green
            g.fillRect(250, 0, 150, 150);

            g.setColor(new Color(255,255,0));//Yellow
            g.fillRect(0, 250, 150, 150);

            g.setColor(new Color(0,0,204));//Blue
            g.fillRect(250, 250, 150, 150);

            Polygon p = new Polygon();
            p.addPoint(150, 150);
            p.addPoint(150, 250);
            p.addPoint(200, 200);
            g.setColor(Color.red);
            g.fillPolygon(p);

            Polygon p1 = new Polygon();
            p1.addPoint(150, 150);
            p1.addPoint(250, 150);
            p1.addPoint(200, 200);
            g.setColor(Color.green);
            g.fillPolygon(p1);  

            Polygon p2 = new Polygon();
            p2.addPoint(200, 200);
            p2.addPoint(150, 250);
            p2.addPoint(250, 250);
            g.setColor(Color.yellow);
            g.fillPolygon(p2);

            Polygon p3 = new Polygon();
            p3.addPoint(250, 250);
            p3.addPoint(250, 150);
            p3.addPoint(200, 200);
            g.setColor(Color.blue);
            g.fillPolygon(p3);

            g.setColor(Color.WHITE);

            g.fillRect(30, 30, 30, 30);
            g.fillRect(30, 80, 30, 30);
            g.fillRect(80, 30, 30, 30);
            g.fillRect(80, 80, 30, 30);

            g.fillRect(280, 30, 30, 30);
            g.fillRect(280, 80, 30, 30);
            g.fillRect(330, 30, 30, 30);
            g.fillRect(330, 80, 30, 30);        

            g.fillRect(30, 280, 30, 30);
            g.fillRect(30, 330, 30, 30);
            g.fillRect(80, 280, 30, 30);
            g.fillRect(80, 330, 30, 30);

            g.fillRect(280, 280, 30, 30);
            g.fillRect(280, 330, 30, 30);
            g.fillRect(330, 280, 30, 30);
            g.fillRect(330, 330, 30, 30);

            
        }  
    }
    
    public class TriangleButton extends JButton {
        
        private int color;
        private Shape triangle;
        int txt;
        
        public  TriangleButton(int z)
        { 
            this.color=z;
            this.triangle = createTriangle(color);
            int value = 58;
            setValue(value);
        }
  
        public boolean contains(int x, int y) {
            return triangle.contains(x, y);
        }
    
        private void setValue(int x){
            this.txt = x;
        }   
        public int getValue(){
            return this.txt;
        }
    
        private Shape createTriangle(int k) {
            Polygon p = new Polygon();
            if(k==1)//red
            {
                p.addPoint(150, 150);
                p.addPoint(150, 250);
                p.addPoint(200, 200);
            }
            else if(k==2)//green
            {
                p.addPoint(150, 150);
                p.addPoint(250, 150);
                p.addPoint(200, 200);            
            }
            else if(k==3)//yellow
            {
                p.addPoint(200, 200);
                p.addPoint(150, 250);
                p.addPoint(250, 250);            
            }
            else if(k==4) //blue
            {
                p.addPoint(250, 250);
                p.addPoint(250, 150);
                p.addPoint(200, 200);           
            }
            return p;
        }
    }

}