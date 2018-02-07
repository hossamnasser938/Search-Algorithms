
package searchalgorithms;

//Stack Class

public class Stack {
    
    int sp;                    //Stack pointer to point to the last item in the stack array
    int size;                 //Size of the stack array
    String[] stackArray;     //Stack array to store items
    
    public Stack(int size)      //Stack constructor
    {
        sp = -1;
        this.size = size;
        stackArray = new String[size];
    }
    
    void push(String city)              //Push function
    {
        if(sp == size - 1)              //if the stack is full inform user and end function
        {
            System.out.println("Can not push .. Stack is full");
            return;
        }
        stackArray[++sp] = city;        //if it is not full push the item
    }
    
    String pop()                //Pop function
    {
        if(sp == -1)           //if the stack is empty inform user and return null
        {
            System.out.println("Can not pop .. Stack is empty");
            return null;
        }
        return stackArray[sp--];        //if it is not empty pop the last item
    }
    
    String peak()                //Peak function
    {
        if(sp == -1)           //if the stack is empty inform user and return null
        {
            System.out.println("Can not peak .. Stack is empty");
            return null;
        }
        return stackArray[sp - 1];        //if it is not empty pop the last item
    }
    
}
