

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * This class contains the AStar algorithm implementation
 * @author nesh2013
 *
 */

public class AStar {
	
	private Board initialState;	
	private Board goalState;
	private Heuristics heuristic;	//this is for setting the Heuristic: Manhattan or Hamming
	
	
	/**
	 * The constructor for AStar that initilizes the instance with initial state,goal state and heuristic to be used.
	 * @param i initialstate
	 * @param g goal state
	 * @param h heurisitc either hamming or manhattan
	 */
	public AStar(Board i, Board g, Heuristics h){
		initialState=i;
		goalState=g;
		this.heuristic=h;
	}
	
	
	/**
	 * this is the implementation of the Astar algorithm.
	 */
	public void solve(){
		
		//for detecting already explored nodes
		ArrayList<Board> explored=new ArrayList<Board>();
		//for frontier nodes. Ordered according to f value. the compareTo method for Board is overrided
		PriorityQueue<Board> queue=new PriorityQueue<Board>();
		
		//set f for initial state. intially g=0, this f=0+h
		initialState.setF(heuristic.getDistance(this.initialState,this.goalState));	// the correct heuristic.getDistance(),  
																					//is called depending on what is passed.
		//add initial to queue
		queue.add(this.initialState);
		
		//as long as queue is not empty we know that there are nodes that must be explored.
		while(!queue.isEmpty()){
			
			Board currentBoard=queue.poll(); 	//poll returns and removes element from queue.
			
			explored.add(currentBoard);	//add to explored.
			
			//check if b is the goal state
			if(currentBoard.equals(this.goalState)){
				
				//we have found the goalState, so we just need to print out path and we are done.
				
				//create a stack to hold the previous nodes so that we can print the solution easily.
				Stack<Board> previousStates=new Stack<Board>();
				Board temp=currentBoard;	//start for the while loop
				while(!temp.equals(this.initialState)){	//keep looping until you get to initial state
					previousStates.push(temp);
					temp=temp.getPreviousState();	//each Board stores its previous state. so we can recursively get the previous states.
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
				System.out.println("-------------------------------");
				System.out.println("Total number moves= "+numOfMoves);
				System.out.println("Total number of nodes explored= "+explored.size());
				System.out.println("-------------------------------");
				return;	//no need to do anything else so return 
			}
			
			//if code comes here. we know its not goal state so get the successors
			ArrayList<Board> successors = currentBoard.getSuccessors();
			
			
			for(int i=0; i<successors.size();i++){
				
				//for each successor do the following
				Board board=successors.get(i);
				
				//check to see that the fetched sucessor board is not in the queue or in explored
				if(!queue.contains(board) && !explored.contains(board)){	
					int h=heuristic.getDistance(board,this.goalState);	//set h according to the heuristic
					int g=board.getG();	//set g, which is basically the depth.
					board.setF(g+h);	//set the f value for the board.
					board.setPreviousState(currentBoard); //must set the previousState here for my implementation. to be able to print the moves
					
					//add to queue
					queue.add(board);
				}
				else if(explored.contains(board)){	//if the board is in explored
					//iterate over the explored to get the board.
			        Iterator<Board> iterator = explored.iterator();
			        
			        Board foundBoard=null;
			        while(iterator.hasNext()){
			        	foundBoard=iterator.next();
			        	if(foundBoard.equals(board)) { break; }
			        }
			        if(foundBoard.getG()<board.getG()){	//update 
			        	explored.remove(board);	//remove old board with higher g() from explored and add new to queue
			        	queue.add(foundBoard);    	
			        }    
				}
				
				else if(queue.contains(board)){	//board is in queue
					//iterate over the explored to get the board.
			        Iterator<Board> iterator = queue.iterator();
			        
			        Board foundBoard=null;
			        while(iterator.hasNext()){
			        	foundBoard=iterator.next();
			        	if(foundBoard.equals(board)) { break; }
			        }
			        if(foundBoard.getG()<board.getG()){	//found lowercost path node
			        	queue.remove(board);	//remove from queue and add new boardto queue
			        	queue.add(foundBoard);    	
			        }	
			        
				}else {}
				
			}
		}
		//out of the while loop thus no solution.considered all possibilites 
		System.out.println("NO SOLUTION EXISTS");
	}
}
