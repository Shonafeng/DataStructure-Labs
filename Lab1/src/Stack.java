/**
 * Define stack class
 * stack is implemented using char Array (char[])
 * Methods include: constructor, isEmpty(), push(char), pop(), display(), peek(), length()
 * @author fengxiao
 *
 */
public class Stack 
{
    private int top; //point to the stack top
    int size;        //size of the array to store stack
    char[] stack;   //declear stack as char array
    
    /**
     * constructor
     * @param arraySize
     */
    public Stack(int arraySize)
    {
        size = arraySize;
        stack = new char[size];
        top = -1;
    }
    
    /**
     * check if stack is empty
     * @return
     */
    public boolean isEmpty()
    {
        return top == -1;
    }
    
    
    /**
     * push a new char on stack
     * @param chara
     */
    public void push(char chara) throws StackException
    {
        if(top == size-1)
        {
            throw new StackException("Stack overflow");
            
        }
        else
        {
            top = top + 1;
            stack[top] = chara;
        }
        
    }
    
    
    /**
     * pop the last char on stack
     */
    public void pop() throws StackException
    {
        if(!isEmpty())
        {
            top = top -1;
        }
        else 
        {
            throw new StackException("Stack underflow");//System.out.println("Stack underflow, can't pop value");
        }
    }
    
    /**
     * display all items on the stack
     */
    public void display()
    {
        for(int i=0; i<= top; i++)
        {
            System.out.println(stack[i] + " ");
        }
        System.out.println();
    }
    
    
    /**
     * Retrieve the last item on the stack
     * @return the char on the top
     */
    public char peek()
    {
        return stack[top];
    }
    
    /**
     * get number of items on the stack
     * @return count of current items on the stack
     */
    public int length()
    {
        return top+1;
    }
    
}
