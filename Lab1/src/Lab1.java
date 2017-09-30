import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 * This program is a driver class for lab1
 * It contains one main class
 * input are args[0] - input file path ; args[1] - output file path
 * It uses buffer reader to read information from input file 
 * It passes input to Languael class to check qualification for any language type
 * It uses buffer wirter to write result to output file
 * 
 * @author fengxiao
 *
 */
public class Lab1
{

    public static void main(String[] args)
    {
        
        String input = null; //initiate input variable to store from input file line by line
        String result;       //initiate result variable to store result of input string
        
        //exception handling in case input/output file path are invalid
        try(BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
            BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));)
        {
            //pass input to LanguageL class print out result in result file             
            while((input=inFile.readLine())!=null)
            {
                
                LanguageL lc = new  LanguageL(input.trim().toUpperCase().replaceAll("\\s",""));                
                result = lc.consolidResult();
                result = input + result;                
                
                outFile.write(result + "\n");
            }

        }
        catch (IOException e)
        {    //Get message if file dose not exist
            System.out.println("I/O Exception: " + e.getMessage());
            System.out.println("Sorry file name or file path dose not exist");
        }
       

    }

}
