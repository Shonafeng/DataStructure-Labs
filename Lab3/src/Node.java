/**
 * this class create node for linked list
 * @author fengxiao
 *
 */
public class Node
{
    int row; // the row this node is in
    int col; // the column this node is in
    double value; // the value to be stored
    Node down; //a pointer pointing to the next element below it or a trailer dummy node
    Node right; // a pointer pointing the the next element to the right of it or a trailer dummy node
    
    
    public Node( int row, int col, double value, Node down , Node right)
    {
        this.row = row;
        this.col = col;
        this.value = value;
        this.down = down;
        this.right = right;
    }

}
