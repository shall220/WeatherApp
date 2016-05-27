//A university project
package weatherapp;


public class WeatherApp {


    public static void main(String[] args) {

//        WeatherHistory WHtest = new WeatherHistory(); 
//        WHtest.add();
//        WHtest.loadObservationsFromHTMLFile();
//        WHtest.getObservations();

        WeatherHistory WHTest = new WeatherHistory();        
        WeatherObservation test1 = new WeatherObservation("test1", "Lonsdale", 
        "Friday", "March", 25, 23, 57, 7, 19);
        
        WHTest.add(test1);
        
        WHTest.saveToFile();
        
        WHTest.remove(test1);
        
        WHTest.readFromFile();
    
    }
    
}
