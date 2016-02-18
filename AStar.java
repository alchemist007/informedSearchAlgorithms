import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


public class AStar {
	
	private Board initialState;
	private Board goalState;
	private Heuristics heuristic;	//this is for setting the Heuristic: Manhattan or Hamming
	
	public AStar(Board i, Board g, Heuristics h){
		initialState=i;
		goalState=g;
		this.heuristic=h;
	}
	
	public void solve(){
		//for detecting already explored nodes
		ArrayList<Board> explored=new ArrayList<Board>();
		//for frontier nodes.ordered according to f
		PriorityQueue<Board> queue=new PriorityQueue<Board>();
		
		//set f for initial state. intially g=0, this f=0+h
		initialState.setF(heuristic.getDistance(this.initialState,this.goalState));	// the correct heuristic.getDistance(),  
																					//is called depending on what is passed.
		//add initial to queue
		queue.add(this.initialState);
		
		//go through queue and expand lowest cost nodes
		while(!queue.isEmpty()){
			Board currentBoard=queue.poll(); 	//poll removes from queue.
			explored.add(currentBoard);	//add to explored so that we dont explore again.
			
			//check if b is the goal state
			if(currentBoard.equals(this.goalState)){
				
				//create a stack to hold the previous nodes/states so we can print it.
				Stack<Board> previousStates=new Stack<Board>();
				Board temp=currentBoard;	//start for the while loop
				while(!temp.equals(this.initialState)){	//keep looping until you dont get to initial state
					previousStates.push(temp);
					temp=temp.getPreviousState();
				}
				previousStates.push(initialState);	//now stack has the entire path from initial state to gaol state
				
				//you can add code here to get stack info to print out
				int numOfMoves=previousStates.size();
				
				//print stack
				while(!previousStates.isEmpty()){
					(previousStates.pop()).printBoard();
					System.out.println();
				}
				
				System.out.println("Total number moves= "+numOfMoves);
				System.out.println("Total number of nodes explored= "+explored.size());
				
				return;
			}
			
			//if code comes here. we know its not goal state so get the successors
			ArrayList<Board> successors = currentBoard.getSuccessors();
			
			//
			for(int i=0; i<successors.size();i++){
				
				
				Board board=successors.get(i);
				
				//check to see that the fetched sucessor board is not in the queue or in explored
				if(!queue.contains(board) && !explored.contains(board)){	
					int h=heuristic.getDistance(board,this.goalState);
					int g=board.getG();
					board.setF(g+h);
					board.setPreviousState(currentBoard);
					
					//add to queue
					queue.add(board);
				}
				else{	//complete this later
					//iterate over the queue to see if var board is there already
			        Iterator iterator = queue.iterator();
			        
					
				}
				
			}
		}
		//out of the while loop thus no solution
		System.out.println("NO SOLUTION EXISTS");
	}
}
