import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Stack;


public class DepthFirstBranchBound {
	
	private Board initialState;	
	private Board goalState;
	private Heuristics heuristic;	//this is for setting the Heuristic: Manhattan or Hamming
	
	
	public DepthFirstBranchBound(Board i, Board g, Heuristics h){
		initialState=i;
		goalState=g;
		this.heuristic=h;
	}
	
	public void solve(){
		
		//for detecting already explored nodes
		ArrayList<Board> explored=new ArrayList<Board>();
		//for frontier nodes. Ordered according to f value
		PriorityQueue<Board> queue=new PriorityQueue<Board>();
		
		//set f for initial state. intially g=0, this f=0+h
		initialState.setF(heuristic.getDistance(this.initialState,this.goalState));	// the correct heuristic.getDistance(),  
																					//is called depending on what is passed.
		//add initial to queue
		queue.add(this.initialState);
		
		int L=1000;
		
		int counter=0;
		Board solutionBoard=null;
		while(!queue.isEmpty() /*&& counter<10000*/){
			//counter++;
			
			//debug
			System.out.println("QUEUE SIZE "+queue.size());
			
			
			Board currentBoard=queue.poll(); 	//poll returns and removes element from queue.
			//new code
			queue.clear();
			explored.add(currentBoard);	//add to explored so that we dont explore again.
			
			if(currentBoard.equals(goalState)){
				solutionBoard=currentBoard;
				//compute f()
				int h=heuristic.getDistance(currentBoard,this.goalState);	//set h according to the heuristic
				int g=currentBoard.getG();	//set g, which is basically the depth.
				currentBoard.setF(g+h);	//set the f value for the board.
				//set L
				if(currentBoard.getF()<L){ 
					L=currentBoard.getF(); 
					System.out.println("Current L Value "+L);
				}
			}
			
			else{
				
				//if code comes here. we know its not goal state so get the successors
				ArrayList<Board> successors = currentBoard.getSuccessors();
				
				//debugging. print successrs.
//				System.out.println("SUCCESSORS RIGHT NOW");
//				for(int i=0; i<successors.size();i++){
//					
//					successors.get(i).printBoard();
//				}
				
				//remove the nodes already present in explored
				for(int i=0; i<successors.size();i++){
					Board board=successors.get(i);
					if(explored.contains(board)){	
						successors.remove(board);	
					}
				}
				//debugging. print successrs.
//				System.out.println("SUCCESSORS AFTER REMOVING ANY PRESENT IN EXPLORED");
//				for(int i=0; i<successors.size();i++){
//					
//					successors.get(i).printBoard();
//				}
				
				
				//evaluate f, check if its > L, and ifso discard n
				for(int i=0; i<successors.size();i++){
					Board board=successors.get(i);
					int h=heuristic.getDistance(board,this.goalState);	//set h according to the heuristic
					int g=board.getG();	//set g, which is basically the depth.
					board.setF(g+h);	//set the f value for the board
					board.setPreviousState(currentBoard); //must set the previousState here for my implementation.
					if(board.getF()>L){
						successors.remove(board);
					}
				}
				
				//debugging. print successrs.
//				System.out.println("SUCCESSORS AFTER REMOVING THOSE WHOSE F() IS GREATER THAN L");
//				for(int i=0; i<successors.size();i++){
//					
//					successors.get(i).printBoard();
//				}
				//add remaining to queue.
				for(int i=0; i<successors.size();i++){
					Board board=successors.get(i);
					queue.add(board);
				}

			}
			
		}
		System.out.println("end of while loop");
		
		//print solution
		//create a stack to hold the previous nodes so that we can print the solution easily.
		Stack<Board> previousStates=new Stack<Board>();
		Board temp=solutionBoard;	//start for the while loop
		while(!temp.equals(this.initialState)){	//keep looping until you dont get to initial state
			previousStates.push(temp);
			temp=temp.getPreviousState();	//each Board stores its previous state. so we can recursively get the previous states.
		}
		
		//you can add code here to get stack info to print out
		int numOfMoves=previousStates.size();
		
		System.out.println("-----INITIAL STATE-----");
		initialState.printBoard();
		System.out.println("------------------------");
		//print stack
		while(!previousStates.isEmpty()){
			(previousStates.pop()).printBoard();
			System.out.println();
		}
		
		System.out.println("Total number moves= "+numOfMoves);
		System.out.println("Total number of nodes explored= "+explored.size());
		
	}
	
}
