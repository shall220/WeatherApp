//A university project

//known issues
//animation does not scale as intended
//animation can go out of bounds during window resizing and glitch
//search validation is incomplete 
//memory leak somewhere
//get list returns results surrounded with [] because of using array[], needs to be changed to arrayList
//NOTE:being new to programming some of these issues are beyound my knowledge 
    //and i ran out of time to research and fix them.


package GUI;


import weatherapp.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.Dimension;
import java.util.ArrayList;



public class GUI {
    
     public static void main(String[] args) {
         
        
         
        Drawing cloud = new Drawing();      
        
        
        Dimension sizingAnimation = new Dimension(800, 500);
        Dimension jfSizing = new Dimension(400, 700);
        
         
        JFrame jf = new JFrame();        
        JPanel p = new JPanel(new BorderLayout(0,6));
        JPanel pTop = new JPanel(new BorderLayout());
        JPanel pAnimation = new JPanel(new BorderLayout());
        JPanel pAnimationDrawing = new JPanel(new BorderLayout());
        JPanel pAnimationButtons = new JPanel(new GridLayout(1,2));       
        JPanel pBottom = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        TitledBorder titled = new TitledBorder("Weather Observations");
        
        jf.setMinimumSize(jfSizing);

        //Add animation to animation panel and set starting size
        cloud.setPreferredSize(sizingAnimation);
        pAnimationDrawing.add(cloud);         
        pAnimation.add(pAnimationDrawing, BorderLayout.NORTH);
        cloud.Mypicture();

        
        //animation buttons
        JButton b5 = new JButton("Start Animation");
        JButton b6 = new JButton("Stop Animation");        
        pAnimationButtons.add(b5);
        pAnimationButtons.add(b6);
        pAnimation.add(pAnimationButtons, BorderLayout.SOUTH);
        
        
        p.add(pAnimation, BorderLayout.NORTH); 
        
        JTextArea textArea = new JTextArea(5,6);
        JScrollPane scrollPane = new JScrollPane(textArea); 
        textArea.setEditable(false);
        pTop.add(scrollPane, BorderLayout.CENTER);

        
        
        jf.setSize(800, 900);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        
 
       
        
        p.setBorder(titled);        
  
        
        p.add(pAnimation, BorderLayout.NORTH);
        p.add(pTop, BorderLayout.CENTER);
        p.add(pBottom, BorderLayout.SOUTH);        
        jf.add(p);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        Database db = new WeatherHistory();
        
        
        //buttons for weather obervations
        JButton b1 = new JButton("Load HTML"); 
        
        c.gridx = 0;
        c.gridy = 1; 
        c.weightx = 0.5;
        pBottom.add(b1, c);
        
        JButton b2 = new JButton("Get List");
        
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.5;
        pBottom.add(b2, c);
        
        JButton b3 = new JButton("Clear");
        c.gridx = 2;
        c.gridy = 1; 
        c.weightx = 0.5;
        pBottom.add(b3, c);
        
        JButton b4 = new JButton("Search by Date");
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.5;
        pBottom.add(b4, c);
        
        JTextField tf = new JTextField();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.weightx = 0.5;
        pBottom.add(tf, c);        
        tf.setPreferredSize(b4.getPreferredSize());
        tf.setText("Add date here Use dd/mm/yyyy, remove all leading zeros.");
       
        
        

        
        //listeners for buttons
        b1.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){          
             db.loadObservationsFromHTMLFile();
             textArea.setText("List populated");
            }
        });
        
 
        b2.addActionListener(new ActionListener(){ 
            
            @Override
            public void actionPerformed(ActionEvent e){               
            String obsList = db.getObservations().toString();
            int temp = obsList.length();
            int j = 2;
            if(j == temp){
                textArea.setText("There is no List Loaded, Please Populate the list");                
            }else{
            String[] observationList = obsList.split("Location");
            String newline = System.getProperty("line.separator");    
            int count = observationList.length;
            int i;
            textArea.setText(observationList[0] + newline);
            for(i = 1; i < count; i++){            
            textArea.append(observationList[i] + newline);
            }
        } 
            }
        });    
        
        
        b3.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
            textArea.setText("");
            }
        });
        
        
        tf.addFocusListener(new FocusListener() {
            boolean toggleFocus = false;
            
            @Override
            public void focusGained(FocusEvent e) {
                //removes settext not user entered text
                if(toggleFocus == false){
                tf.setText("");
                toggleFocus = true;
                }
             
            }

            public void focusLost(FocusEvent e) {

            
            }
        });
        
        
        
        
        b4.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
            String dateToSearch = tf.getText();
            String newline = System.getProperty("line.separator"); 
            String searchDate = db.getObservations().toString();
            int j = 2;
            char[] charArray = dateToSearch.toCharArray();
            //arraylist to hold results
            ArrayList<String> results = new ArrayList<String>();
            
            
            
            //some validation
            if(j == searchDate.length()){
            textArea.setText("There is no List Loaded, Please Populate the list");                
            }else if(dateToSearch.trim().length() < 1){
                textArea.setText("Search feild is empty!");
            }else if(charArray[0] == '0'){
                textArea.setText("Please remove all leading zeros for correct search");
            }else if(dateToSearch.trim().length() < 8){
             textArea.setText("Incorrect Format - please use dd/mm/yyyy");   
            }else{            
            String[] observationList = searchDate.split("Location");
            
                for (int i = 0; i < observationList.length; i++){
                String temp3 = observationList[i];
                    if(temp3.contains(dateToSearch)){
                    results.add(temp3);

            // print results or lack of          
                    }
                }if(results.size() < 1){
                    textArea.setText("No results for your search.");
                    }else {
                    textArea.setText("");
                    for(int i = 0; i < results.size();i++ )                    
                        textArea.append(results.get(i) + newline); 
                    }
                }    
            }
        });

        
        
        
        
        b5.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
            cloud.startAnimation();
            }
        });
        
        
        
        
        b6.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
            cloud.stopAnimation();
            }
        });
        
        
        
       

     }
    
}
