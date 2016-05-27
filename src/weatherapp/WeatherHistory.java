//A university project
package weatherapp;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;  
import org.jsoup.Jsoup;  
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WeatherHistory extends WeatherObservation implements Database {
    BufferedWriter writer = null;
    BufferedReader reader = null;
    File myFile = new File("observations.txt");
    FileReader fileReader  = null;
    float tempReadIn;
    int humidReadIn;
    int UVReadIn;
    int windSpeedReadIn;
    int addLineCounter = 0;
    String objectName;
    int index;
    
public   WeatherHistory(){    
}


    
private ArrayList<WeatherObservation> list = new ArrayList<>();



//Add elements to the list
public void add(WeatherObservation item){
    list.add(item);
}

public void indexAdd(int index, WeatherObservation item){
    list.add(index, item);
}

//remove elements from the list
public void remove(WeatherObservation item){
    list.remove(item);
}

public  void indexRemove(int index){
    list.remove(index);
}

//update element
public void set(int index, WeatherObservation item){
    list.set(index, item);
}

//Return index of an object
public int indexOf(WeatherObservation item){
    return list.indexOf(item);
}

//return object at an idex

public WeatherObservation get(int index){
    return list.get(index);    
}

//Get size of array list
public int size(){
    return list.size();
}

//search list
public boolean search(WeatherObservation item){
    return list.contains(item);
}


//Print list
@Override
public ArrayList getObservations(){
    return list;
//    for(int counter = 0; counter < list.size(); counter++){
//        System.out.println(list.get(counter));
//    } 
}    
        

//Method for adding weather observations from text file  
    public void add(){       
         
        try{
            
            fileReader = new FileReader(myFile);
            reader = new BufferedReader(fileReader);

            //Skip over the first line to get to the data
            reader.readLine();
            String line = null;

            //read text doc line by line
            while((line = reader.readLine()) !=null){
            addLineCounter ++;

                    //create array of data
                    String[ ] data = line.split(" ");

                    //creat new object each line
                    WeatherObservation readIn = new WeatherObservation();

                    //get place place      
                    readIn.place(data[0]);
                    
                    //if loop to check and account for places with 2 words for name
                    String testForNext = data[1];                    
                    char c = testForNext.charAt(0);
                    if (c >= '0' && c <= '9'){ 
                    
                    //get date from array    
                    readIn.dateDay(data[1]);
                    
                    //convert String from array to needed formats       
                    try{
                    tempReadIn = Float.valueOf(data[2]);
                    humidReadIn = Integer.parseInt(data[3]);
                    UVReadIn = Integer.parseInt(data[4]);
                    windSpeedReadIn = Integer.parseInt(data[5]);
        
                    }   catch (NumberFormatException nfe){
                        Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                            null, nfe);
                    }         
                    readIn.temp(tempReadIn);
                    readIn.humid(humidReadIn);
                    readIn.uvIndex(UVReadIn);
                    readIn.windSpeed(windSpeedReadIn);
        
                //Add each new object to the array list
                list.add(readIn);
                    }
                    
                    //get place when it has 2 words
                    else{ readIn.place(data[0] + " " + data[1]);
                    readIn.dateDay(data[2]);
                    //convert String from array to needed formats       
                    try{
                    tempReadIn = Float.valueOf(data[3]);
                    humidReadIn = Integer.parseInt(data[4]);
                    UVReadIn = Integer.parseInt(data[5]);
                    windSpeedReadIn = Integer.parseInt(data[6]);
        
                    }   catch (NumberFormatException nfe){
                        Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                            null, nfe);
                    }         
                    readIn.temp(tempReadIn);
                    readIn.humid(humidReadIn);
                    readIn.uvIndex(UVReadIn);
                    readIn.windSpeed(windSpeedReadIn);
        
                //Add each new object to the array list
                list.add(readIn);
                        
                    }        
            }    
        }   catch (IOException ex){
                Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);        
        
        }   finally{
                try{
                    if(reader !=null){
                    reader.close();
                    }
        }   catch (IOException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);
        }       
        }  
    }    
    
    
//Method for adding weather observations from HTML file 
    @Override
    public void loadObservationsFromHTMLFile(){
                            try{
                Document doc = Jsoup.connect("http://rengland.spinetail.cdu.edu.au/observations/").get(); 
                
                Element table = doc.select("table").get(0);
                Elements nuMOfRows = table.select("tr"); 
                int countLimit = nuMOfRows.size();
                int i = 1;
                while(i < countLimit){                
                Element row = table.select("tr").get(i);                
                String newInput = row.text();
                String[] data = newInput.split(" ");
                WeatherObservation readIn = new WeatherObservation();

                //get place and date from array       
                readIn.place(data[0]);
                String testForNext = data[1];                    
                char c = testForNext.charAt(0);
                if (c >= '0' && c <= '9'){ 
                    
                    //get date from array    
                    readIn.dateDay(data[1]);
                    
                    //convert String from array to needed formats       
                    try{
                    tempReadIn = Float.valueOf(data[2]);
                    humidReadIn = Integer.parseInt(data[3]);
                    UVReadIn = Integer.parseInt(data[4]);
                    windSpeedReadIn = Integer.parseInt(data[5]);
        
                    }   catch (NumberFormatException nfe){
                        Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                            null, nfe);
                    }         
                    readIn.temp(tempReadIn);
                    readIn.humid(humidReadIn);
                    readIn.uvIndex(UVReadIn);
                    readIn.windSpeed(windSpeedReadIn);
        
                //Add each new object to the array list
                list.add(readIn);
                i++;
                    } else{ readIn.place(data[0] + " " + data[1]);
                    readIn.dateDay(data[2]);
                    //convert Strings from array to needed formats       
                    try{
                    tempReadIn = Float.valueOf(data[3]);
                    humidReadIn = Integer.parseInt(data[4]);
                    UVReadIn = Integer.parseInt(data[5]);
                    windSpeedReadIn = Integer.parseInt(data[6]);
        
                    }   catch (NumberFormatException nfe){
                        Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                            null, nfe);
                    }         
                    readIn.temp(tempReadIn);
                    readIn.humid(humidReadIn);
                    readIn.uvIndex(UVReadIn);
                    readIn.windSpeed(windSpeedReadIn);
        
                //Add each new object to the array list
                list.add(readIn);
                i++;
                }
                
                }
                
                } catch (IOException ex){
                    Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);                     
                }
}


    
    
    
   
//Buffered reader wirter
public void bufferedReadWrite(){ 
File file = new File("bufferedinput.txt"); 
boolean checkFile = false;            
try{
    if(file.exists() && !file.isDirectory()) { 
        checkFile = true;
        }
        if (checkFile == false){
        file.createNewFile(); 
        }
    FileWriter fw = new FileWriter("buffereoutput.txt");
    FileReader fr = new FileReader("bufferedinput.txt");
    writer = new BufferedWriter(fw);
    reader = new BufferedReader(fr);
    String line = null;
    while((line = reader.readLine()) !=null){
        writer.write(line);
    }} 
catch (IOException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);        
        
       }finally{
    try{
        reader.close();
        writer.close();
} 
catch (IOException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);        
       }
        
}
}
    //Objectstream
    public void saveToFile(){
            try{
        ObjectOutputStream arrList = new 
        ObjectOutputStream(new FileOutputStream("arrList.ser"));
        System.out.println("Saved arrList");
        arrList.writeObject(list);
        arrList.close();
        }    
        catch (IOException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);        
        }
        }


public void readFromFile(){
        try{
        ObjectInputStream is = new
            ObjectInputStream(new FileInputStream("arrList.ser"));
        System.out.println("Read object in..");
        list = (ArrayList<WeatherObservation>) is.readObject();
        System.out.println(list);
        
        }
        catch (IOException|ClassNotFoundException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                    null, ex);            
        }
}


//file reader writer

public void fileReaderWriter(){
    
FileReader in = null;
FileWriter out = null;
int charac;
boolean checkFile = false;
File file = new File("input.txt");
    try{
        if(file.exists() && !file.isDirectory()) { 
        checkFile = true;
        }
        if (checkFile == false){
        file.createNewFile(); 
        }
        
        in = new FileReader("input.txt"); 
        out = new FileWriter("output.txt");
        
        
        while ((charac = in.read()) != -1) {    
               out.write(charac);  
               }
               if (in != null) {
               in.close();
               out.close();                
               }
        }  catch (FileNotFoundException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
            null, ex);
        } catch (IOException ex){
            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
            null, ex);            
        }
    finally { try {
                        if (in != null) {
                            in.close();
                            out.close();
                        }
                        } catch (IOException ex) {
                            Logger.getLogger(WeatherHistory.class.getName()).log(Level.SEVERE,
                            null, ex);

                        }
                }
}
    
    
}
      
  
        






    

