import java.util.ArrayList;
import java.util.Arrays;


public class PuzzleSolver {
	/*most likely not needed for now because this is just a main class
	static Board initialState;
	static Board goalState;
	
	public PuzzleSolver(Board initialState, Board goalState){
		this.initialState=initialState;
		this.goalState=goalState;
	}*/
	

	
	/**
	 * main function
	 * @param args
	 */
	public static void main(String[] args) {
		
		//hard coded multidim array that represent EASY, MEDIUM, HARD, WORST start states
		int[][] easy= { {1,3,4}, {8,6,2}, {7,0,5} };	//0 is used as a blank.
		int[][] medium= { {2,8,1}, {0,4,3}, {7,6,5} };
		int[][] hard= { {2,8,1}, {4,6,3}, {0,7,5} };
		int[][] worst= { {5,6,7}, {4,0,8}, {3,2,1} };
		
		//gaol state
		int[][] goal= { {1,2,3}, {8,0,4}, {7,6,5} };
		
		//setup boards
		Board initialBoard=new Board(worst);
		Board goalBoard=new Board(goal);
		
		//choose the Heuristic
		HammingDistance h=new HammingDistance();
		ManhattanDistance m=new ManhattanDistance();
		
		//create AStar instance and solve
		//AStar solver=new AStar(initialBoard,goalBoard,m);
		//solver.solve();
		
		IDAStar idaSolver=new IDAStar(initialBoard,goalBoard,m);
		idaSolver.solve();

	}

}

/*code used for testing the different functions
System.out.println("-------Testing printBoard()---------");
Board b=new Board(easy);
System.out.println("easy Board");
b.printBoard();

System.out.println("-------Testing copyArray()---------");
int[][] copiedArr=b.copyArray(b.array);
System.out.println("easy Board array Copy");
Board cp=new Board(copiedArr);
cp.printBoard();

System.out.println("-------Testing getBlankIndex()---------");
System.out.println("INDEX I OF BLANK= "+b.getBlankIndex()[0]);
System.out.println("INDEX J OF BLANK= "+b.getBlankIndex()[1]);

System.out.println("-------Testing getSuccessors()---------");
ArrayList<Board> successors=new ArrayList<Board>();
Board c=new Board(worst);
successors=c.getSuccessors();
for (int i = 0; i < successors.size(); i++) {
	System.out.println("\n");
    successors.get(i).printBoard();
}

System.out.println("-------Testing Heuristics.HammingDistance()---------");
Board b=new Board(worst);
Board g=new Board(goal);
int cost=Heuristics.HammingDistance(b,g);
System.out.println("Hamming distance "+cost);

System.out.println("-------Testing isGoal()---------");
System.out.println("The two boards are equal is "+b.equals(b));

System.out.println("--------------Testing ManhattanDistance-------------");
System.out.println(m.getDistance(initialBoard, goalBoard));

System.out.println("--------------Testing ManhattanDistance-------------");
int x=m.getDistance(initialBoard, goalBoard);
System.out.println(x);



*/