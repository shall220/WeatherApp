//A university project
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Drawing extends JPanel implements ActionListener{
    
    Timer t = new Timer(50, this);
    int x; 
    int velX;
    int widthHolder;
    //for starting animation in the correct direction after stopping
    boolean forward = true;
    
    
    
    public void Mypicture(){        
        repaint();
    }
    
    //methods to stop and start animation
    public void stopAnimation(){
        velX = 0;
    }
    
    public void startAnimation(){        
        if(forward == true){
            velX = 2;
        }else{velX = -2;}
            
        }
        
    
    
    
    

    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
        
        //variable to make sure animated cloud stays inside window during resizing
        widthHolder = getWidth()-getWidth()/6 -150;
        //Variables for cloud and sun sizing for window resizing
        int sun = getHeight()/5;
        int xCloud = getWidth()/6;
        int yCloud = getHeight()/15;
        int xCloud1 = getWidth()/10;
        int yCloud1 = getHeight()/10;
        int yCloud2 = getHeight()/3;
        
        
        //drawing background, sun and cloud
        g.setColor(new Color(150,210,252,248));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.orange);
        g.fillOval(100, sun, xCloud, xCloud);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x, yCloud2, xCloud, yCloud);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+150, yCloud2, xCloud, yCloud);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+90, yCloud2, xCloud, yCloud);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+40, yCloud2, xCloud1, yCloud1);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+70, yCloud2, xCloud1, yCloud1);
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+110, yCloud2, xCloud1, yCloud1);
        
        
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x+150, yCloud2, xCloud1, yCloud1);       
        t.start(); 

    }
    
    //animating cloud
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(x < 0 ){
           forward = true;           
           velX = 2;
        }else if(x > widthHolder){
           forward = false;           
           velX = -velX;
        }         
            
        
        x = x + velX;

        
        repaint();
    }
//    public int holderWidth(){
//        return x + 150;
//    }
    
    
}
    

