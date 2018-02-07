
package searchalgorithms;

import java.util.ArrayList;
import java.util.Scanner;

//Class Tree to represent the governorate or the state

public class Tree {
    
    String name;         //State or Governorate name
    Node root;          //Root node of the tree
    Stack DFSAstack;        //A Stack used by DFS Algorithm
    ArrayList<String> visitedDFSA;           //A list to store visited items by DFS Algorithm
    ArrayList<String> visitedBFSA;          //A list to store visited items by BFS Algorithm
    ArrayList<String> visitedGA;           //A list to store visited items by Greedy Algorithm
    ArrayList<String> visitedASA;         //A list to store visited items by A* Algorithm
    ArrayList<String> citiesNames;       //A list used to store the names of each city added
    ArrayList<Location> citiesLocations;        //A list to store the location of each city added
    Scanner s = new Scanner(System.in);

    public Tree(String governorateName , Node root)         //Tree constructor  
    {
        name = governorateName;
        this.root = root;
        citiesNames = new ArrayList<>();
        citiesLocations = new ArrayList<>();
        this.citiesNames.add(root.name);                 //Add name of root city
        this.citiesLocations.add(root.location);        //Add location of root city
        DFSAstack = new Stack(20);
        visitedDFSA = new ArrayList<>();
        visitedBFSA = new ArrayList<>();
        visitedGA = new ArrayList<>();
        visitedASA = new ArrayList<>();
    }
    
    Node traverse(Node start , String name)         //Traverse function takes name of a city and returns its node
    {
        if(start.name.equals(name))
            return start;
        else if(start.leftChild != null && start.leftChild.name.equals(name))
            return start.leftChild;
        else if(start.rightChild != null && start.rightChild.name.equals(name))
            return start.rightChild;
        else if(start.middleChild != null && start.middleChild.name.equals(name))
            return start.middleChild;
        else;
  
        if(start.leftChild != null)
        {
            if(traverse(start.leftChild , name) != null)
                return traverse(start.leftChild , name);
        }
            
        if(start.rightChild != null)
        {
            if(traverse(start.rightChild , name) != null)
                return traverse(start.rightChild , name);
        }
            
        if(start.middleChild != null)
        {
            if(traverse(start.middleChild , name) != null)
                return traverse(start.middleChild , name);
        }
            
        return null;    
    }
    
    boolean addNode(String parentName , String NodeName /*, double distance*/ , int longitude , int latitude)       //AddNode function adds city to the state or the governorate
    {
        boolean added = false;
        Node parentNode = this.traverse(root, parentName);
        Node newNode = new Node(NodeName , parentNode , longitude , latitude);
        //newNode.parentDistance = distance;
            if(parentNode.leftChild == null)
            {
                parentNode.leftChild = newNode;
                //parentNode.leftChildDistance = distance;
                added =  true;
            }
            else if(parentNode.middleChild == null)
            {
                parentNode.middleChild = newNode;
                //parentNode.middleChildDistance = distance;
                added = true;
            }
            else if(parentNode.rightChild == null)
            {
                parentNode.rightChild = newNode;
                //parentNode.rightChildDistance = distance;
                added =  true;
            }
            if(added)
            {
                if(!this.citiesNames.contains(NodeName))
                {
                    this.citiesNames.add(NodeName);
                    this.citiesLocations.add(newNode.location);
                }   
            }
            return added;
    }
    
    /*
    void GetGovernorateMapDescriptionFromUser()
    {
        Queue cities = new Queue(20);
        System.out.println("Please , describe the map of " + name + " Menofia");
        System.out.print("State the name of the first city : ");
        String currentCity = s.next();
        citiesNames.add(currentCity);
        System.out.print("State the location of the city(longitude , latitude) : ");
        int longitude = s.nextInt();
        int latitude = s.nextInt();
        Node root = new Node(currentCity , longitude , latitude);
        this.root = root;
        String yesOrNo;
        boolean another = false;
        String cityAdded;
        double distance;
        Outer : do
        {
            if(another)
                System.out.print("Does " + currentCity + " have another adjacent(Y/N) : ");
            else
                System.out.print("Does " + currentCity + " have any adjacent(Y/N) : ");
            do
            {
                yesOrNo = s.next();
                if(yesOrNo.equals("Y") || yesOrNo.equals("y"))
                {
                    another = true;
                    System.out.print("State the name of this adjacent city : ");
                    cityAdded = s.next();
                    if(!citiesNames.contains(cityAdded))
                        citiesNames.add(cityAdded);
                    cities.enqueue(cityAdded);
                    System.out.print("State the location of the city(longitude , latitude) : ");
                    longitude = s.nextInt();
                    latitude = s.nextInt();
                    System.out.print("What is the distance between " + currentCity + " and " + cityAdded + " ?");
                    distance = s.nextDouble();
                    if(!traverse(this.root , currentCity).addNode(cityAdded , distance , longitude , latitude))
                        System.out.println("Error .. Can not add city");
                }
                else if(yesOrNo.equals("N") || yesOrNo.equals("n"))
                {
                    another = false;
                    if(cities.back != cities.front)
                        currentCity = cities.dequeue();
                    else
                        break Outer;
                }
                else
                    System.out.print("Please , enter Y or N : ");
            }
            while(!yesOrNo.equals("Y") && !yesOrNo.equals("y") && !yesOrNo.equals("N") && !yesOrNo.equals("n"));
        }
        while(yesOrNo.equals("Y") || yesOrNo.equals("y") || yesOrNo.equals("N") || yesOrNo.equals("n"));
        System.out.println("\nMap built successfully\n");
    }
    */    
    
    double getDistanceBetweenTwoCities(String city1 , String city2)      
    {
        Node city1Node = traverse(root , city1);
        Node city2Node = traverse(root , city2);
        return city1Node.location.distance(city2Node.location);
    }
    
   
    void depthFirstSearchAlgorithm(Node startNode , String source , String destination)       
    {   
        Node start = startNode;
        if(start.name.equals(source) && !visitedDFSA.contains(source))
        {
            DFSAstack.push(start.name);
            visitedDFSA.add(start.name); 
        }   
        if(DFSAstack.sp != -1)
        {
            if(start.parent != null && !visitedDFSA.contains(start.parent.name))
            {
                start = start.parent;
                DFSAstack.push(start.name);
                visitedDFSA.add(start.name);
                if(start.name.equals(destination))
                {
                    DFSAstack.sp = 0;
                    depthFirstSearchAlgorithm(traverse(root , DFSAstack.pop()) , source , destination);
                }
                else
                {
                    depthFirstSearchAlgorithm(start , source , destination);
                }
            }
            else if(start.leftChild != null && !visitedDFSA.contains(start.leftChild.name))
            {
                start = start.leftChild;
                DFSAstack.push(start.name);
                visitedDFSA.add(start.name);
                if(start.name.equals(destination))
                {
                    DFSAstack.sp = 0;
                    depthFirstSearchAlgorithm(traverse(root , DFSAstack.pop()) , source , destination);   
                }
                else
                {
                    depthFirstSearchAlgorithm(start , source , destination);
                }
            }
            else if(start.middleChild != null && !visitedDFSA.contains(start.middleChild.name))
            {
                start = start.middleChild;
                DFSAstack.push(start.name);
                visitedDFSA.add(start.name);
                if(start.name.equals(destination))
                {               
                    DFSAstack.sp = 0;
                    depthFirstSearchAlgorithm(traverse(root , DFSAstack.pop()) , source , destination);
                }
                else
                {
                    depthFirstSearchAlgorithm(start , source , destination);
                }
            }
            else if(start.rightChild != null && !visitedDFSA.contains(start.rightChild.name))
            {
                start = start.rightChild;
                DFSAstack.push(start.name);
                visitedDFSA.add(start.name);
                if(start.name.equals(destination))
                {
                    DFSAstack.sp = 0;
                    depthFirstSearchAlgorithm(traverse(root , DFSAstack.pop()) , source , destination);
                }
                else
                {
                    depthFirstSearchAlgorithm(start , source , destination);
                }
            }
            else
            {
                DFSAstack.pop();
                if(DFSAstack.sp != -1)
                    depthFirstSearchAlgorithm(traverse(root , DFSAstack.peak()) , source , destination);
            }
        }
    }
    
    void breadthFirstSearchAlgorithm(String source , String destination)
    {
        Queue BFSAqueue = new Queue(20);
        Node start = traverse(root , source);
        BFSAqueue.enqueue(start.name);
        visitedBFSA.add(source);
        while(BFSAqueue.back != BFSAqueue.front)
        {
            start = traverse(root , BFSAqueue.dequeue());
            if(start.parent != null && !visitedBFSA.contains(start.parent.name))
            {
                visitedBFSA.add(start.parent.name);
                if(start.parent.name.equals(destination))
                    break;
                else
                    BFSAqueue.enqueue(start.parent.name);
            }
            if(start.leftChild != null && !visitedBFSA.contains(start.leftChild.name))
            {
                visitedBFSA.add(start.leftChild.name);
                if(start.leftChild.name.equals(destination))
                    break;
                else
                    BFSAqueue.enqueue(start.leftChild.name);
            }
            if(start.middleChild != null && !visitedBFSA.contains(start.middleChild.name))
            {
                visitedBFSA.add(start.middleChild.name);
                if(start.middleChild.name.equals(destination))
                    break;
                else
                    BFSAqueue.enqueue(start.middleChild.name);
            }
            if(start.rightChild != null && !visitedBFSA.contains(start.rightChild.name))
            {
                visitedBFSA.add(start.rightChild.name);
                if(start.rightChild.name.equals(destination))
                    break;
                else
                    BFSAqueue.enqueue(start.rightChild.name);
            }
        }
    }
    
    void greedySearchAlgorithm(String source , String destination)
    {
        Double parentHeuristic = 0.0 , leftChildHeuristic = 0.0 , middleChildHeuristic = 0.0 , rightChildHeuristic = 0.0;
        ArrayList<Double> heuristic = new ArrayList<>();
        Stack GAstack = new Stack(20);
        Node start = traverse(root , source);
        GAstack.push(start.name);
        while(GAstack.sp != -1)
        {
            heuristic.add(0.0);
            heuristic.add(0.0);
            heuristic.add(0.0);
            heuristic.add(0.0);
            start = traverse(root , GAstack.pop());
            visitedGA.add(start.name);
            if(start.parent != null && !visitedGA.contains(start.parent.name))
            {
                if(start.parent.name.equals(destination))
                {
                    visitedGA.add(start.parent.name);
                    break;   
                }
                else
                {
                    parentHeuristic = getDistanceBetweenTwoCities(start.parent.name , destination);
                    heuristic.set(0 , parentHeuristic);
                }   
            }
            if(start.leftChild != null && !visitedGA.contains(start.leftChild.name))
            {
                if(start.leftChild.name.equals(destination))
                {
                    visitedGA.add(start.leftChild.name);
                    break;   
                }
                else
                {
                    leftChildHeuristic = getDistanceBetweenTwoCities(start.leftChild.name , destination);
                    heuristic.set(1 , leftChildHeuristic);
                }   
            }
            if(start.middleChild != null && !visitedGA.contains(start.middleChild.name))
            {
                if(start.middleChild.name.equals(destination))
                {
                    visitedGA.add(start.middleChild.name);
                    break;   
                }
                else
                {
                    middleChildHeuristic = getDistanceBetweenTwoCities(start.middleChild.name , destination);
                    heuristic.set(2 , middleChildHeuristic);
                }   
            }
            if(start.rightChild != null && !visitedGA.contains(start.rightChild.name))
            {
                if(start.rightChild.name.equals(destination))
                {
                    visitedGA.add(start.rightChild.name);
                    break;   
                }
                else
                {
                    rightChildHeuristic = getDistanceBetweenTwoCities(start.rightChild.name , destination);
                    heuristic.set(3 , rightChildHeuristic);
                }
            }
            
            pushMax(GAstack , start , heuristic);
            heuristic.clear();
        }
    }
    
    void AStarSearchAlgorithm(String source , String destination)
    {
        Double parentEvaluation = 0.0 , leftChildEvaluation = 0.0 , middleChildEvaluation = 0.0 , rightChildEvaluation = 0.0;
        ArrayList<Double> evaluation = new ArrayList<>();
        Stack ASAstack = new Stack(20);
        Node start = traverse(root , source);
        ASAstack.push(start.name);
        while(ASAstack.sp != -1)
        {
            evaluation.add(0.0);
            evaluation.add(0.0);
            evaluation.add(0.0);
            evaluation.add(0.0);
            start = traverse(root , ASAstack.pop());
            visitedASA.add(start.name);
            if(start.parent != null && !visitedASA.contains(start.parent.name))
            {
                if(start.parent.name.equals(destination))
                {
                    visitedASA.add(start.parent.name);
                    break;   
                }
                else
                {
                    parentEvaluation = getDistanceBetweenTwoCities(start.parent.name , destination) + getDistanceBetweenTwoCities(start.name , start.parent.name)/*start.parentDistance*/;
                    evaluation.set(0 , parentEvaluation);
                }
                    
            }
            if(start.leftChild != null && !visitedASA.contains(start.leftChild.name))
            {
                if(start.leftChild.name.equals(destination))
                {
                    visitedASA.add(start.leftChild.name);
                    break;   
                }
                else
                {
                    leftChildEvaluation = getDistanceBetweenTwoCities(start.leftChild.name , destination) + getDistanceBetweenTwoCities(start.name , start.leftChild.name)/*start.leftChildDistance*/;
                    evaluation.set(1 , leftChildEvaluation);
                }
                    
            }
            if(start.middleChild != null && !visitedASA.contains(start.middleChild.name))
            {    
                if(start.middleChild.name.equals(destination))
                {
                    visitedASA.add(start.middleChild.name);
                    break;   
                }
                else
                {
                    middleChildEvaluation = getDistanceBetweenTwoCities(start.middleChild.name , destination) + getDistanceBetweenTwoCities(start.name , start.middleChild.name)/*start.middleChildDistance*/;
                    evaluation.set(2 , middleChildEvaluation);
                }
                    
            }
            if(start.rightChild != null && !visitedASA.contains(start.rightChild.name))
            {
                if(start.rightChild.name.equals(destination))
                {
                    visitedASA.add(start.rightChild.name);
                    break;   
                }
                else
                {
                    rightChildEvaluation = getDistanceBetweenTwoCities(start.rightChild.name , destination) + getDistanceBetweenTwoCities(start.name , start.rightChild.name)/*start.rightChildDistance*/;
                    evaluation.set(3 , rightChildEvaluation);
                }
                    
            }
            
            pushMax(ASAstack , start , evaluation);
            evaluation.clear();
        }
    }
    
    void pushMax(Stack stack , Node current , ArrayList<Double> evaluation)        //pushMax function pushes to the stack nodes children and parent in a descending order for Greedy and A* Algorithm
    {
        Double max = evaluation.get(0);
        int count = 0;
        for(int i = 1 ; i < evaluation.size() ; i++)
        {
            if(max < evaluation.get(i))
            {
                max = evaluation.get(i);
                count = i;
            }    
        }
        if(max != 0.0)
        {
            switch(count)
            {
            case 0 : stack.push(current.parent.name);
                     evaluation.set(0, 0.0);
                     break;
            case 1 : stack.push(current.leftChild.name);
                     evaluation.set(1, 0.0);
                     break;
            case 2 : stack.push(current.middleChild.name);
                     evaluation.set(2, 0.0);
                     break;
            case 3 : stack.push(current.rightChild.name);
                     evaluation.set(3, 0.0);
                     break;
            }
            pushMax(stack , current , evaluation);
        }
    }
    
    /*
    void showSolution (String source , String destination , String algorithmUsed , ArrayList visited)
    {
        if(visited.contains(destination))
        {
            System.out.println("To go from \'" + source + "\' to \'" + destination + "\' Using " + algorithmUsed + " Algorithm You should follow this path");
            for(int i = 0 ; i < visited.size() ; i++)
            {
                if(i == visited.size() - 1)
                    System.out.println(visited.get(i));
                else
                    System.out.print(visited.get(i) + " ---> ");
            }
        }
        else
            System.out.println("No path from \'" + source + "\' to \'" + destination + "\' using " + algorithmUsed + " Algorithm");
    }
    */
    
    /*
    void userChoices()
    {
        String source , destination;
        String anotherSearch;
        int algorithmToBeUsed = 0;
        Outer : do
        {
            System.out.print("What is your source city? ");
            do
            {
                source = s.next();
                if(citiesNames.contains(source))
                    break;
                else
                    System.out.println(source + " does not exist .. Enter an exixting city please");
            }while(true);
            System.out.print("What is your destination city? ");
            do
            {
                destination = s.next();
                if(citiesNames.contains(destination))
                    break;
                else
                    System.out.println(destination + " does not exist .. Enter an exixting city please");
            }while(true);
            System.out.println("Choose one of these algorithms Algorithm\n1 Depth First Search Algorithm\n2 Breadth First Search Algorithm\n3 Greedy Search Algorithm\n4 A Star Search Algorithm");
            do
            {
                algorithmToBeUsed = s.nextInt();
                if(algorithmToBeUsed == 1)
                {
                    visitedDFSA.clear();
                    depthFirstSearchAlgorithm(traverse(root, source) , source , destination);
                    showSolution(source , destination , "Depth First Search" , visitedDFSA);
                }
                else if(algorithmToBeUsed == 2)
                {
                    visitedBFSA.clear();
                    breadthFirstSearchAlgorithm(source , destination);
                    showSolution(source , destination , "Breadth First Search" , visitedBFSA);
                }
                else if(algorithmToBeUsed == 3)
                {
                    visitedGA.clear();
                    greedySearchAlgorithm(source , destination);
                    showSolution(source , destination , "Greedy Search" , visitedGA);
                }
                else if(algorithmToBeUsed == 4)
                {
                    visitedASA.clear();
                    AStarSearchAlgorithm(source , destination);
                    showSolution(source , destination , "A Star Search" , visitedASA);
                }
                else
                    System.out.println("Enter 1 or 2 or 3 or 4 please");
            }
            while(algorithmToBeUsed != 1 && algorithmToBeUsed != 2 && algorithmToBeUsed != 3 && algorithmToBeUsed != 4);
            System.out.print("Do you want to make another search(Y/N)? ");
            do
            {    
                anotherSearch = s.next();
                if(anotherSearch.equals("Y") || anotherSearch.equals("y"));
                else if(anotherSearch.equals("N") || anotherSearch.equals("n"))
                    break Outer;
                else
                    System.out.println("Enter Y or N please");
            }while(!anotherSearch.equals("y") && !anotherSearch.equals("Y") && !anotherSearch.equals("n") && !anotherSearch.equals("N"));
            
        }while(anotherSearch.equals("y") || anotherSearch.equals("Y"));
    }
    */
    
}
