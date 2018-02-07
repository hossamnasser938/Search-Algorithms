
package searchalgorithms;

//Queue class

public class Queue {
    
    int front;                      //front to point at the first item added
    int back;                      //back to point to the last item added
    int size;                     //Size of the queue array
    String[] queueArray;         //Queue array to store items
    
    Queue(int size)             //Queue constructor
    {
        this.size = size;   
        queueArray = new String[size];
        front = 0; 
        back = 0;
    }
    
    void enqueue(String city)          //Enqueue function
    {
        if((back == size -1 && front == 0) || front - back == 1)     //if the queue is full inform user and end function
            System.out.println("Can not enqueue .. Queue is full");
        else if(back == size - 1 && front != 1)        //if last place of the queue is reached and the first place is empty add the item to the last place and forward to the first place
        {
            queueArray[back] = city;
            back = 0;
        }
        else
            queueArray[back++] = city;          //if the queue is not full add the item
    }
    
    String dequeue()                //Dequeue function
    {
        if(front == back)          //if the queue is empty inform user and return null
        {
            System.out.println("Can not dequeue .. Queue is empty");
            return null;
        }
        else if(front == size - 1)      //if last place is reached return the item within it and forward to the first place
        {
            int temp = front;
            front = 0;
            return queueArray[temp];
        }
        else
            return queueArray[front++];                 //if the queue is not empty return the first item added
    }
    
}
