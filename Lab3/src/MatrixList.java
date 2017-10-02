/**
 * this class is to implement a 2D array to store input matrix
 * @author fengxiao
 *
 */
public class MatrixList {
	
    private int size; //rows in matrix
	
	private String matrixString ="";
	Node tlcorner; //points to the top left corner 
	//int maxRow; //number of rows
	//int maxCol; //number of columns
	
	/**
	 * constructor: to initiate a new matrix with one node  
	 */
	public MatrixList( )
	{
	    
	    tlcorner = new Node(0, 0, 0, null, null);
		//this.size = size;		
	}
	
	/**
	 * constructor: deep copy an existing matrix
	 * @param a
	 */
	public MatrixList(MatrixList a )
	{
	    tlcorner = new Node(0, 0, 0, null, null);
	    size = a.getSize();
	    for(int i=0; i<size; i++)
        {
            for(int j=0; j<size; j++)
            {
                setValue(a.getValue(i,j),i,j);
            }
        }
	}
	
	
	/**
	 * this method is to get size of matrix
	 * @return size of matrix
	 */
	public int getSize()
	{
	    int count = 1;
	    Node curr = tlcorner;
	    while(curr.right != null)
	    {
	        curr = curr.right;
	        count++;
	    }
	    return count;
	}
	
	
	/**
	 * this method is to set value at matrix[i][j]
	 * @param x- value
	 * @param i- x index
	 * @param j- y index
	 */
	public void setValue(double newValue,int row, int col)
	{
		Node above = null; //initialize node above new node
		Node left = null; //new Node(0,0,0,null,null) ; //initialize node left to new node
		Node curr; 
		
		Node newNode = new Node(row, col, newValue, null, null);
		curr = tlcorner;
		if (row == 0 && col == 0) // if new node is at left top corner 
		{
		    tlcorner.value = newValue;
		}
		else if (row == 0)
		{
		    
		    while (curr.col< col-1)
		    {
		       
		        curr = curr.right;
		        
		    }
		    curr.right = newNode;
		}
		else if (col == 0)
		{
		    
		    while (curr.row < row-1)
		    {
		        //above = curr;
		        curr = curr.down;
		    }
		    curr.down = newNode;
		}
		else
		{
		    
		    /*while (curr.row <row)
	        {
	            above = curr;
	            curr = curr.down;
	        }
	        while (curr.col <col)
	        {
	            left = curr;
	            curr = curr.right;
	            above = curr.right;
	        }
	        */
		    
		    above = findNode(row-1, col);
		    left = findNode(row, col-1);
	        left.right = newNode;
	        above.down = newNode;
		}		
		
	}
	
	
	/**
	 * this method is to return node at a specified row and column location
	 * @param row
	 * @param col
	 * @return the Node specified or null if not found
	 */
	private Node findNode(int row, int col)
	{
	    Node current = tlcorner;
	    
	    Node found = null; //initiate node
	    
	    while (current.row < row) // move row to the row desired
	    {
	        current = current.down;
	    }
	    while (current.col < col) // move column to the column desired
	    {
	        current = current.right;
	    }
	    if(current.row == row && current.col == col) //after traverse, if row and column are both as desired, return found
	    {
	        found = current;
	    }
	    
	    return found;
	       
	     
	}
	
	
	

	   
	
	
	
	
	/**
	 * this method is to retrieve value at specified row and column
	 * @param row
	 * @param colmn
	 * @return value at specified
	 */
	public double getValue(int row, int col)
	{
	    Node current; //the node that will traverse the matrix
	    double value = 0.0;
	    
	    current = findNode(row, col);
	    if (current != null) // if a node was found
	    {
	        value = current.value;
	    }
	    return value;
	   
	}
	
	
	
	
	
	/**
	 * This method is to transfer matrix to String format 
	 */
	public String toString()
	{
	    Node curr = tlcorner;
	    Node rowNode;
	    boolean lastflag = false;
	    
	    if (curr.right == null) // if only one item in the matrix
	    {
	        matrixString = String.valueOf(curr.value);
	        matrixString += "\n";
	    }
	    
	    while(curr.down != null)
	    {
	        rowNode = curr;
	        while(curr.right != null)
	        {
	            matrixString += String.valueOf(curr.value);
	            matrixString += " ";
	            curr = curr.right;  
	            
	        }
	        matrixString += String.valueOf(curr.value);
	        matrixString += " ";
	        matrixString += "\n";
	        
	        
	        curr = rowNode.down;
	        if(curr.down == null)
	        {
	           lastflag = true;
	        }
	    }
	    
	    if(lastflag) // if last line has value not been printed
	    {
	        while(curr.right != null ) // print out the last line
	        {
	            matrixString += String.valueOf(curr.value);
	            matrixString += " ";
	            curr = curr.right;            
	        }
	        matrixString += String.valueOf(curr.value);
	        matrixString += "\n";
	    }
		
		return matrixString;
	}

}
