
package searchalgorithms;

//Node class to represent the city

public class Node {
    
    String name;                //City name
    //Adjacent Cities connected to the city and each one's distance
    Node parent;
    //double parentDistance;
    Node leftChild;
    //double leftChildDistance;
    Node middleChild;
    //double middleChildDistance;
    Node rightChild;
    //double rightChildDistance;
    //Location of the city
    Location location; 
    
    public Node(String name , Node parent , int longitude , int latitude)       //Node constructor
    {
        this.name = name;
        this.parent = parent;
        //parentDistance = 0;
        leftChild = null;
        //leftChildDistance = 0;
        middleChild = null;
        //middleChildDistance = 0;
        rightChild = null;
        //rightChildDistance = 0;
        location = new Location(longitude , latitude);
    }
    boolean isConnected(String city)             //IsConnected function determines whether there is a direct way between two cities or not
    {
        if(parent != null && parent.name.equals(city))
            return true;
        else if(leftChild != null && leftChild.name.equals(city))
            return true;
        else if(middleChild != null && middleChild.name.equals(city))
            return true;
        else if(rightChild != null && rightChild.name.equals(city))
            return true;
        else
            return false;
    }
    
}
