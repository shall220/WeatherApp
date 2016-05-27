//A university project
package weatherapp;

public class WeatherObservation {
    
    private String identifier;
    private String place;
    private String dateDay;
    private String dateMonth;
    private int dateNumber;
    private float temp;
    private int humid;
    private int uvIndex;
    private int windSpeed;

    
    
      WeatherObservation(){
         
}    
    
    

      WeatherObservation(String identifier, String place, String dateDay,
                        String dateMonth, int dateNumber, float temp, int humid, int uvIndex,
            int windSpeed){
            this.identifier = identifier;
            this.place = place;
            this.dateDay = dateDay;
            this.dateMonth= dateMonth;
            this.dateNumber = dateNumber;
            this.temp = temp;
            this.humid = humid;
            this.uvIndex = uvIndex;
            this.windSpeed = windSpeed;
            
}
    
              
        
        //Set and Get methods
        
        //identifier
        public String getIdentifier(){
            return identifier;
        }
        
        public void identifier(String identifier){
            this.identifier = identifier;   
        }
        
        //place
        public String getPlace(){
            return place;
        }
        
        public void place(String place){
            this.place = place;
        }
        
        //dateDay
        public String getDateDay(){
            return dateDay;
        }
        
        public void dateDay(String dateDay){
            this.dateDay = dateDay;
        }
        
        //dateMonth
        public String getDateMonth(){
            return dateMonth;
        }
        
        public void dateMonth(String dateMonth){
            this.dateMonth = dateMonth;
        }
        
        //date
        public int getDateNumber(){
            return dateNumber;
        }
        
        public void dateNumber(int dateNumber){
            this.dateNumber = dateNumber;
        }
        
        //Full date 
        public String getDate(){
            return dateDay +", "+ dateMonth +", "+ dateNumber;
        }
        
        public void date(String dateDay, String dateMonth, int dateNumber){
            this.dateDay = dateDay;
            this.dateMonth = dateMonth;
            this.dateNumber = dateNumber;
            
        }
        
        //temp
        public float getTemp(){
            return temp;
        }
        
        public void temp(float temp){
            this.temp = temp;
        }
        
        //humid
        public int getHumid(){
            return humid;
        }
        
        public void humid(int humid){
            this.humid = humid;
        }
        
        //uvIndex
        public int getUvIndex(){
            return uvIndex;
        }
        
        public void uvIndex(int uvIndex){
            this.uvIndex = uvIndex;
        }
        
        //WindSpeed
        public int getWindSpeed(){
            return windSpeed;
        }
        
        public void windSpeed(int windSpeed){
        this.windSpeed = windSpeed;
        }
        
        //toString over ride for arraylist
        // this one has only the variables needed for the application
               @Override
        public String toString(){
            return "Location " + place 
                    + ", Date: " + getDateDay() + ", Temperature: " + temp +
                    "C, Relative Humidity: " + humid + "%, UVIndex: " + uvIndex
                    + ", Wind Speed: " + windSpeed + "km/h.";      
    }
        
        //This is includes all variables
//       @Override
//        public String toString(){
//            return "Identifier: " + identifier + ". Location " + place 
//                    + ", Date: " + getDate() + " Temperature: " + temp +
//                    "C, Relative Humidity: " + humid + "%, UVIndex: " + uvIndex
//                    + ", Wind Speed: " + windSpeed + "km/h.";      
//    }
        
        
            
        
        
}
