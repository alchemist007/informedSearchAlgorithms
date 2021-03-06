import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;




/**
 * implements the iterative deepening A star algorithm
 * @author nesh2013
 *
 */
public class IDAStar {
	
	private Board initialState;
	private Board goalState;
	private Heuristics heuristic;	//this is for setting the Heuristic: Manhattan or Hamming
	
	public IDAStar(Board i, Board g, Heuristics h){
		initialState=i;
		goalState=g;
		this.heuristic=h;
	}
	
	public void solve(){
		
		//set the limit. at first its just gonna be the initial states h().
		initialState.setF(heuristic.getDistance(this.initialState,this.goalState));	
		int limit=initialState.getF();
		
		while(true){	//keep running until solution is found
			
			//for detecting already explored nodes
			ArrayList<Board> explored=new ArrayList<Board>();
			//for frontier nodes.ordered according to f
			PriorityQueue<Board> queue=new PriorityQueue<Board>();
			
			//add initialBoard to queue.
			queue.add(initialState);
			//add to explored.
			
			//holds the min value of f for the successors
			int min_val=10000;	//really big number.
			
			while(!queue.isEmpty()){
				Board currentBoard=queue.poll();
				
				//not sure if this is needed
				explored.add(currentBoard);	//add to explored 
				
				//check if b is the goal state
				if(currentBoard.equals(this.goalState)){
					
					//create a stack to hold the previous nodes/states so we can print it.
					Stack<Board> previousStates=new Stack<Board>();
					Board temp=currentBoard;	//start for the while loop
					while(!temp.equals(this.initialState)){	//keep looping until you dont get to initial state
						previousStates.push(temp);
						temp=temp.getPreviousState();
					}
					
					
					//you can add code here to get stack info to print out
					int numOfMoves=previousStates.size();
					System.out.println("-----INITIAL STATE------");
					initialState.printBoard();
					System.out.println("------------------------");
					//print stack
					while(!previousStates.isEmpty()){
						(previousStates.pop()).printBoard();
						System.out.println();
					}
					System.out.println("Total number of moves= "+numOfMoves);
					System.out.println("Total number of nodes explored= "+explored.size());
					
					return;
				}
				
				
				
				//get the successors()
				ArrayList<Board> successors = currentBoard.getSuccessors();
				
				for(int i=0; i<successors.size();i++){
					
					
					Board board=successors.get(i);
					
					//check to see that the fetched sucessor board is not in the queue 
					if(!queue.contains(board)){	
						int h=heuristic.getDistance(board,this.goalState);
						int g=board.getG();
						board.setF(g+h);
						board.setPreviousState(currentBoard);
						
						
						//Update the cutoff for the next iterative deepening
						if(board.getF()>limit){
							if(board.getF()<min_val){
								min_val=board.getF();	//set new minimun for now
								//System.out.println(min_val);
							}
							continue;	//this just skips over that particular board because the f is greater than limit. so we dont expand it.
						}
						
						queue.add(board);
						
					}
					else{	//complete this later
						//iterate over the queue to see if var board is there already
				        Iterator iterator = queue.iterator(); 				
					}
	
				}
				
			}
			limit=min_val;	//set new limit.
			
		}

		
		
		
	}
}