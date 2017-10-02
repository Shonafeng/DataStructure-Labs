
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the driver class for lab3
 * It contains one main class
 * input are args[0] - input file path ; args[1] - output file path
 * It uses buffer reader to read information from input file 
 * It read input into Matrix and calculate determinant of the matrix
 * It uses buffer writer to write result into output file
 * @author fengxiao
 *
 */
public class Lab3 {
	
	static String error =""; //initiate error as String
	
	/**
	 * this method is to count number of integers in a scanner line
	 * @param input
	 * @return
	 */
	public static int intCounter(String line)
	{
		int count =0;
		if(line != null)
		{
			String [] array = line.split(" ");
			count = array.length;			
		}
		else
		{
			error += "input line is missing" ; //error handling
		}
		return count;
	}
	
	/**
	 * this method is to validate matrix order and update size of next matrix
	 * @param input
	 */
	public static int matrixOrderValidation(Scanner input)
	{
		int temp;
		int size = 0;
		
		if(input.hasNextInt() && (temp =input.nextInt())>0)
		{
			size = temp;
			
        }
        else if(input.hasNextInt() && (temp =input.nextInt())<=0)
        {
        	//error handling: matrix order must be greater than 0
        	error += "invalid matrix order, matrix order must be greater than 0\n";        	
        	
        }
        else //error handling: if input line is empty 
        {
        	error += "invalid matrix order, matrix order must be greater than 0\n";
   	
        }
		return size;
		
	}
	
	/**
	 * this method is to calculate determinant of a matrix
	 * @param a- input matrix a
	 * @return
	 */
	public static  double det(MatrixList a)
    {
        double sum =0; //initiate sum of det
        int count =0; // initiate count of sum element
        int factor = 1;
        int size = a.getSize();
        if(size==1) // stopping point: matrix is 1*1
        {
            return a.tlcorner.value;
        }
        else if(size ==2)//stopping point: matrix is 2*2
        {
            return a.tlcorner.value*a.tlcorner.right.down.value-a.tlcorner.right.value*a.tlcorner.down.value;
            
        }
        else
        {
          
          MatrixList minorA = new MatrixList();                  
          while(count < size)
          {
              for(int i=1; i<size; i++)
              {
                  for(int j=0; j<size; j++)
                  {
                      if(j<count) //matrix[i][j] are at left side of column = count
                      {
                          minorA.setValue(a.getValue(i, j), i-1, j);
                      }
                      else if(j>count)//matrix[i][j] are at right side of column = count
                      {
                          minorA.setValue(a.getValue(i, j), i-1, j-1);
                      }
                      else
                      {
                          //don't do any thing
                      }
                  }
     
              }
              
              sum += factor*a.getValue(0, count)*det(minorA);//sum determinant for every element in row1 
              factor = factor*(-1);
              count ++;              
          }
          
        }
       
        return sum;
    }

	

	public static void main(String[] args) {
		
		long startTime = System.nanoTime(); //track of start time
		long endTime; //track of end time
		long runTime; //executing time duration 
		
	    boolean colflag = true;
		boolean rowflag = true;
		double determinat;

		
		try(BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
	            BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));)
	        {
				int rows =0;				
				Scanner input; // declare a scanner to read line by line
				int size1=0; //initiate size of a matrix
				String line;
				
				
				//read first line of the file
				input = new Scanner(line=inFile.readLine());
				size1 = matrixOrderValidation(input);	
				outFile.write("An order " + size1 + " Matrix: \n"); 
									
				MatrixList matrix= new MatrixList(); //initiate matrix
				
	            //read through rest of the file             
	            while((line=inFile.readLine())!=null)
	            {
	            	
	                input = new Scanner(line);
	                int count = intCounter(line);
	                
	                
	                if(count==size1 && rowflag  && colflag )
	                {
	                	
	                	int columns = 0;
	                	while(input.hasNext()) // write value into matrix
	                	{
	                	    
	                		matrix.setValue(input.nextDouble(), rows, columns);	                		
	                		columns++;//track columns
	                		
	                		
	                	}
	                	rows++; // track row count
	                	
	                }
	  
	                
	             // load all rows with no error, read order for of next line
	                else if(rowflag == false && count == 1 && colflag == true && error.equals("")) // already load all rows, read order for of next line
	                {
	                    if(rows==size1)
	                    {
	                        
	                        outFile.write(matrix.toString());// write matrix to output file                        
	                        determinat = det(matrix); //write determinant value to output
	                        outFile.write("Determinant of the above matrix is: " + determinat +"\n");
	                        outFile.write("\n\n");
	                    }
	                    else
	                    {
	                        error += "rows count exceed or less than matrix size\n";
	                        outFile.write(error + "\n");
	                    }
	                	
	                	
	                	//start to read order of next matrix	                	
	                	size1 = matrixOrderValidation(input);
	                	outFile.write("An order " + size1 + " Matrix: \n");
	                	
	                	//initiate matrix
	    				matrix= new MatrixList();
	    				rows=0;
	    				rowflag = true;
	    				colflag = true;
	                }
	                
	                
	                //complete matrix reading, but matrix contains error
	                else if(count == 1 && !error.equals(""))
	                {
	                    outFile.write(error + "\n");
	                    error=""; //reset error
	                    
	                  //start to read order of next matrix                       
                        size1 = matrixOrderValidation(input);
                        outFile.write("An order " + size1 + " Matrix: \n");               
                        
                        
                        //check order reading error
                        if(error.equals(""))
                        {
                            matrix= new MatrixList();
                            rows=0;
                            rowflag = true;
                            colflag = true;
                        }
                        else
                        {
                            outFile.write(error + "\n");
                            error="";
                        }
	                    
	                }
	                //invalid matrix order, skip this entire matrix
	                else if(size1<=0 && count>0)
                    {
	                    rowflag = false; //skip this line
                    }
	                //if current row item count dosen't match with size
	                else if(count !=size1)
	                {
	               	
	                    error += "row " + rows +" exceed or less than matrix column size \n";                	
	                	colflag = false;
	                	rows++;
	                }
	                else
	                {
	                    rows++;
	                }
	               //once rows count equals size, a flag to indicate                
	                if(rows == size1)
	                {
	                	rowflag = false;
	                }
	                
	                
	            }
	            //outside of while loop to control the last test case
	            if(error.equals("") && rows==size1)
	            {
	                outFile.write(matrix.toString());// write matrix to output file                        
	                determinat = det(matrix); //write determinant value to output
	                outFile.write("Determinant of the above matrix is: " + determinat +"\n");
	                outFile.write("\n\n");
	                
	            }
	            else
	            {
	                error += "rows count exceed or less than matrix size\n";
	                outFile.write(error + "\n");
	            }
               
	            

	        }
	        catch (IOException e)
	        {    //Get message if file dose not exist
	            System.out.println("I/O Exception: " + e.getMessage());
	            System.out.println("Sorry file name or file path dose not exist");
	        }
		
		endTime = System.nanoTime(); //track end system time
		runTime = endTime - startTime;
		
		System.out.println("\n Program ran sucessfully, please check the output" + "\n Program took " + runTime + " nano seconds");

	}

}
