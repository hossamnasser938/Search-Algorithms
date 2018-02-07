
package searchalgorithms;

//Class location to represent the location of the city

public class Location {
    //City coordinates
    int longitude;
    int latitude;
    
    Location(int longitude , int latitude)        //Location constructor
    {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    double distance(Location destination)       //Distance function gets the distance between two cities
    {
        return Math.sqrt(Math.pow(longitude - destination.longitude , 2) + Math.pow(latitude - destination.latitude , 2));
    }
    
}
