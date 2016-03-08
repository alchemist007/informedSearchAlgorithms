import java.util.ArrayList;
import java.util.Arrays;

/**
 * this class is the representation of the board state
 * @author nesh2013
 *
 */
public class Board implements Comparable<Board>{
	
	int rows=3;
	int cols=3;
	private Board previousState=null;
	public int[][] array;
	
	//heuristic functions
	int f;
	int g;
	int h;
	
	/**
	 * this instantiates the board's array
	 * @param array
	 */
	public Board(int[][] array){
		this.array=new int[rows][cols];	
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				this.array[i][j]=array[i][j];
			}
		}
	}
	
	/**
	 * getter for f
	 * @return
	 */
	public int getF(){
		return f;
	}
	/**
	 * setter for f
	 * @param f
	 */
	public void setF(int f){
		this.f=f;
	}
	/**
	 * getter for g
	 * @return
	 */
	public int getG(){
		//to get the current board's g, keep going up to the previous node, until you reach initial state whose 
		//getPrevious should return null, since thats what its set to.
		int x=0;
		Board b=this.getPreviousState();
		while(b!=null){
			x++;
			b=b.getPreviousState();
		}
		this.g=x;
		return g;
	}
	/**
	 * getter for h
	 */
	public int getH(){
		return h;
	}
	
	/**
	 * gets the previous board state. used for printing all moves
	 * @return
	 */
	public Board getPreviousState(){
		return this.previousState;
	}
	
	/**
	 * setter for the previousState AKA parent 
	 * @param b
	 */
	public void setPreviousState(Board b){
		previousState=b;
	}
	
	/**
	 * checks if a board is equal to passed goal
	 * @param goal the board to check
	 * @return
	 */
	public boolean equals(Board goal){
		
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(this.array[i][j]!=goal.array[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * used to expand nodes. we consider the right,down,left and up
	 * @return
	 */
	public ArrayList<Board> getSuccessors(){
		ArrayList<Board> successors=new ArrayList<Board>();
		int blankSpaceRow=getBlankIndex()[0];	//row of space
		int blankSpaceCol=getBlankIndex()[1];	//col of space
		
		int[][] boardArray;
		
		//move blankspace right
		if(blankSpaceCol<2){
			//not sure why there is a if statement to check for -1 here.
			
			boardArray=copyArray(this.array);
			boardArray[blankSpaceRow][blankSpaceCol]=boardArray[blankSpaceRow][blankSpaceCol+1];	//swap values
			boardArray[blankSpaceRow][blankSpaceCol+1]=0;
			successors.add(new Board(boardArray));
			
		}
		//move down
		if(blankSpaceRow<2){
			boardArray=copyArray(this.array);
			boardArray[blankSpaceRow][blankSpaceCol]=boardArray[blankSpaceRow+1][blankSpaceCol];
			boardArray[blankSpaceRow+1][blankSpaceCol]=0;
			successors.add(new Board(boardArray));
			
		}
		//move left
		if(blankSpaceCol>0){
			//not sure why there is a if statement to check for -1 here.
			boardArray=copyArray(this.array);
			boardArray[blankSpaceRow][blankSpaceCol]=boardArray[blankSpaceRow][blankSpaceCol-1];	//swap values
			boardArray[blankSpaceRow][blankSpaceCol-1]=0;
			successors.add(new Board(boardArray));
			
		}
		//move up
		if(blankSpaceRow>0){
			boardArray=copyArray(this.array);
			boardArray[blankSpaceRow][blankSpaceCol]=boardArray[blankSpaceRow-1][blankSpaceCol];
			boardArray[blankSpaceRow-1][blankSpaceCol]=0;
			successors.add(new Board(boardArray));
			
		}
		
		//set the previous for each of the boards. **this is set in algo too. so double check later**
		for(Board b: successors){
			b.setPreviousState(this);
		}
		
		return successors;
	}
	
	/**
	 * gives the index of the blankspace.used by getSuccessors()
	 * @return
	 */
	public int[] getBlankIndex(){ 
		int[] index=new int[2];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				if(this.array[i][j]==0){
					index[0]=i;
					index[1]=j;
				}
			}
		}
		return index;
	}
	/**
	 * this function just copies a two dim array.used by getSuccessors()
	 * @param matrix
	 * @return
	 */
	public int[][] copyArray(int[][] arr)
	{
		int[][] copiedArr= new int[this.rows][this.cols];
		for (int i = 0 ;i<this.rows; i++)
			for (int j = 0; j<this.cols; j++)
				copiedArr[i][j] = arr[i][j];
		return copiedArr;
	}

	/**
	 * prints out board
	 */
	public void printBoard(){		
		for (int i = 0; i<rows; i++)
		{
			for (int j = 0 ;j<cols; j++)
			{
				System.out.print(this.array[i][j]+"   ");
			}
			System.out.println();
		}
		
	}
	
	/**
	 * compare function for priority queue
	 */
	@Override
	//Comparator for priority queue
	public int compareTo(Board b) {
		if(this.f<b.getF())
			return -1;
		else if(this.f>b.getF())
			return 1;
		else return 0;
	}
}
