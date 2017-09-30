

/**
 * The purpose of the class is to check if input String met any type of language 
 * 6 languages are validated in the class as 6 methods (isL1, isL2, isL3, isL4, isL5, isL6)
 * an additional method to assist language4 validation (compareStackL4)
 * lastly, result from 6 languages are consolidated into one string line
 * 
 * @author fengxiao
 *
 */

public class LanguageL
{
    static char[] x; //Declare  char array
    
    /**
     * constructor to transfer input into char array
     * @param input
     */
    public LanguageL(String input)
    {
        x = input.toCharArray();
    }
    
    /**
     * This method is to check if input belongs to Language 1: equal numbers of A's and B's (in any order) and no other characters (n>=0)
     * @param x
     * @return boolean true - if input qualify for language2; false - if input not qualify for language2
     */
    public static boolean isL1(char[] x)
    {
        Stack a = new Stack(x.length);  //initiate stack a to store As
        Stack b = new Stack(x.length);  //initiate stack b to store Bs
        boolean abOnly = true;          //flag to indicate if input only consist of As and Bs        
        boolean noExceptionCatch = true; // flag of stack exception been catched
        
        try
        {
            for(int i=0; i<x.length; i++)
            {
                if(x[i]=='A')
                {
                    a.push(x[i]);
                }
                else if(x[i] == 'B')
                {               
                    b.push(x[i]);
                }
                else
                {
                    abOnly =false; //true if no other Letters identified except A and B
                }
                           
            }
            while (!a.isEmpty() && !b.isEmpty())//compare number of As and Bs in stack a and b
            {
                a.pop();
                b.pop();            
            } 
        }
        catch (StackException e)
        {
            System.out.println("Error detected: " + e.getMessage());
            e.printStackTrace();
            noExceptionCatch = false;
        }
        
                
            return a.isEmpty() && b.isEmpty() && abOnly && noExceptionCatch;              
    }
    
    /**
     * This method is to check if input belongs to Language 2: A^nB^n (
     * @param x
     * @return boolean true - if input qualify for language2; false - if input not qualify for language2
     */
    
    
    public static boolean isL2(char[] x)
    {
        Stack a = new Stack(x.length); //initiate stack 
        boolean abOnly = true;         //flag to indicate if input only consist of As and Bs
        boolean bAppear = false;        //flag to indicate if B has appeared
        boolean noExceptionCatch = true; // flag of stack exception been catched
        try
        {
            for(int i =0; i<x.length; i++) 
            {
                if(x[i] == 'A' && !bAppear)//push As on Stack before first B appears
                {
                    a.push(x[i]); 
                }
                else if(x[i]=='B' && !a.isEmpty()) // once B appears, pop As for every B comes in
                {
                    a.pop();
                    bAppear = true;
                }
                else
                {
                    abOnly = false; //true if no other Letters identified except A and B
                }                
            } 
        }
        catch (StackException e)
        {
            System.out.println("Error detected: " + e.getMessage());
            e.printStackTrace();
            noExceptionCatch = false;
        }
              
                
        return a.isEmpty() && abOnly && noExceptionCatch;
        
    }
    
    
    /**
     * This method is to check if input belongs to Language 3: A^nB^2n
     * @param x
     * @return boolean true - if input qualify for language3; false - if input not qualify for language3
     */
    
    
    public static boolean isL3(char[] x)
    {
        Stack a = new Stack(x.length); //initiate stack
        boolean abOnly = true;         //check if input only contain As and Bs
        boolean bAppear = false;       //flag to indicate if B has appeared
        boolean noExceptionCatch = true; // flag of stack exception been catched
        try
        {
            for(int i =0; i<x.length; i++)
            {
                if(x[i] == 'A' && !bAppear)//push A twice for every A comes in on Stack before first B appears
                {
                    a.push(x[i]);
                    a.push(x[i]);
                }
                else if(x[i]=='B' && !a.isEmpty()) // pop stack once for every B comes in 
                {
                    a.pop();
                    bAppear = true;
                }
                else
                {
                    abOnly = false;
                }                
            }
        }
        catch (StackException e)
        {
            System.out.println("Error detected: " + e.getMessage());
            e.printStackTrace();
            noExceptionCatch = false;
        }
               
            
        return a.isEmpty() && abOnly && noExceptionCatch ;
    
    }
    
    
    /**
     * This method is to compare if a stack is a recursion of a base letter set
     * @param a - base letter set
     * @param all - validation stack
     * @return true if stack is recursion of the base letter set, false - if not
     */
    
    public static boolean compareStackL4(char[] a, Stack all)
    {        
        
        
        boolean qualify = true; // initiate qualification flag
        int i = 0;              // initiage base array pointer
        boolean noExceptionCatch = true; // flag of stack exception been catched
        
        for(int n=0; n< a.length; n++)//Get non-null char count in an array
        {
            if(a[n] != 0)
            {
                i++;
            }
        }
        
        int length =i;   //initiate number of non-null items of array
        
        try
        {
            while(!all.isEmpty() && length !=0 )
            {   
                          
                while(i>=1)
                {
                    if( a[i-1] == all.peek())
                    {
                        all.pop();    //compare top of stack with the last char in base array, if match, pop          
                        i--;
                    }   
                    else
                    {                      
                        qualify = false; //false if the stack top dose not match with last char in base array
                        break;
                    }
                }
                i=length;   //reset array pointer to the last char in array
                if(!qualify)
                {
                    break; // exit loop if qualify flag is false
                }
                
            } 
        }
        catch (StackException e)
        {
            System.out.println("Error detected: " + e.getMessage());
            e.printStackTrace();
            noExceptionCatch = false;
        }
        
        
       
        return all.isEmpty() && qualify && noExceptionCatch;
    }
    
    /**
     * This method is to check if input belongs to Language 4: (A^nB^m)^p (n,m,p>=0)
     * @param x
     * @return boolean true - if input qualify for language4; false - if input not qualify for language4
     */
    public static boolean isL4(char[] x)
    {
        Stack all = new Stack(x.length);
        char current = 'A';
        char next;
        int counter = 0 ;
        boolean abOnly = true; 
        char[] first = new char[x.length]; //initiate an array to store A^nB^m
        boolean noExceptionCatch = true; // flag of stack exception been catched
        
        try
        {
            for(int i =0; i<x.length; i++)//load input on a stack 
            {
                all.push(x[i]);
            }
            
            
            int i;
            
            for(i=0; i<x.length; i++)// identify first A^nB^m, load on array  
            {
                if(x[0] != 'A')
                {
                    abOnly = false;
                }
                if(counter < 2 && x[0] == 'A')
                {                
                    next = x[i];
                    if( current== next )
                    {
                        
                        first[i]=next;
                    }
                    else if (next != 'A' && next != 'B')
                    {
                        abOnly = false;
                    }
                    else if(current != next )
                    { 
                        if (next == 'B')
                        {
                            
                            first[i] = next;
                        }
                        current = next;
                        counter++;
                    }
                }
                else
                {

                    break;
                }                          
            }            
        }
        catch (StackException e)
        {
            System.out.println("Error detected: " + e.getMessage());
            e.printStackTrace();
            noExceptionCatch = false;
        }
        
     // use compareStackL4 to determine if first A^nB^m are repeated in the whole string line        
        return (compareStackL4(first,all) && abOnly && noExceptionCatch) ;
    }
    
    
    
    /**
     * This method is to check if input belongs to Language 5: symmetrical with any letter
     * example: ABVVBA, AABBCCCCBBAA, sieggeis are language 5
     *          AABBA, ABCA, A are not language 5 
     * @param x
     * @return boolean true - if input qualify for language5; false - if input not qualify for language5
     */
    
    public static boolean isL5(char[] x)
    {//Mirror
        Stack a = new Stack(x.length); //initiate stack
        int i =0;
        boolean qualify = true;
        boolean noExceptionCatch = true; // flag of stack exception been catched
        if (x.length %2 == 0)
        {
            try
            {
                while(i < x.length/2) //load first half of string on to stack
                {
                    a.push(x[i]);
                    i++;
                }
                
                while(i<x.length && !a.isEmpty() ) 
                {
                    if (x[i] == a.peek()) // compare char on the stack with the next half of char of string
                    {
                        a.pop();        //pop stack if match
                        i++;
                    }
                    else
                    {
                        break;
                    }
                }
            
            }
            catch (StackException e)
            {
                System.out.println("Error detected: " + e.getMessage());
                e.printStackTrace();
                noExceptionCatch = false;
                
            }
            
        }
        else 
        {
            qualify = false;    //false if number of char from input not even
        }
       
        return a.isEmpty() && i==x.length && qualify && noExceptionCatch; 
        
    }
    
    /**
     * This method is to check if input belongs to Language 6: A^nB^2nA^n
     * @param x
     * @return boolean true - if input qualify for language6; false - if input not qualify for language6
     */
    
    public static boolean isL6(char[] x)
    {
        Stack a = new Stack(x.length);
        int i ;
        boolean qualify = true;
        char current = 'A';
        char next = 0 ;
        boolean noExceptionCatch = true; // flag of stack exception been catched
        
        if (x.length %2 == 0 && x[0] == 'A')
            try
            {
                for(i=0; i<x.length/2; i++)
                {
                    next = x[i]; 
                    if(current == next )
                    {
                        a.push(next);
                        
                    }
                    else if(current != next && (next == 'B' || next == 'A') )
                    {
                        a.push(next);
                        current = next;
                        
                    }
                    else
                    {
                        qualify = false;
                        break;
                    }
              
                }
                
                while(i<x.length)
                {
                    if(x[i] == a.peek())
                    {
                        a.pop();
                        i++;
                    }
                    else
                    {
                        qualify = false;
                        break;
                    }
                }
              
            } catch (StackException e)
            {
                System.out.println("Error detected: " + e.getMessage());
                e.printStackTrace();
                noExceptionCatch = false;
            }
        else
        {
            qualify = false;
        }
        return a.isEmpty() && qualify && noExceptionCatch;
        
        
    } 
    
    /**
     * This method is to check through language l1 to l6, consolidate result
     * @return - result of a string with only languages qualified
     */
    public String consolidResult()
    {
        String result = " is: ";
        
        if(x.length != 0)
        {
            if(isL1(x))
            {
                result += "Language L1   ";
            }
            
            if(isL2(x))
            {
                result += "Language L2   ";
            }
            
            if(isL3(x))
            {
                result += "Language L3   ";
            }
            
            if(isL4(x))
            {
                
                result += "Language L4   ";
            }
           
            if(isL5(x))
            {
                result += "Language L5   ";
            }
            
            
            if(isL6(x))
            {
                result += "Language L6   ";
            }
            
        }
        else
        {
            result += "input contains 0 As and 0 Bs, Language L1, Language L2, Language L3, Language L4, Language L6 ";
        }
               
        return result;
    }
    

}
