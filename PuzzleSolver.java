import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * this is where the main function resides. This is where all the instances of the algorithms are created and their solve() is called.
 * @author nesh2013
 *
 */
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
		
		System.out.println("*********************************************************************************************");
		System.out.println("Please enter the number of the configuration you would like to run from the following options:");
		System.out.println("");
		System.out.println("0. To exit out of program");
		System.out.println("");
		System.out.println("1. A* with Hamming Distance on EASY");
		System.out.println("");
		System.out.println("2. A* with Hamming Distance on MEDIUM");
		System.out.println("");
		System.out.println("3. A* with Hamming Distance on Hard");
		System.out.println("");
		System.out.println("4. A* with Hamming Distance on WORST");
		System.out.println("");
		
		System.out.println("5. A* with Manhattan Distance on EASY");
		System.out.println("");
		System.out.println("6. A* with Manhattan Distance on MEDIUM");
		System.out.println("");
		System.out.println("7. A* with Manhattan Distance on Hard");
		System.out.println("");
		System.out.println("8. A* with Manhattan Distance on WORST");
		System.out.println("");
		
		System.out.println("9. IDA* with Manhattan Distance on EASY");
		System.out.println("");
		System.out.println("10. IDA* with Manhattan Distance on MEDIUM");
		System.out.println("");
		System.out.println("11. IDA* with Manhattan Distance on Hard");
		System.out.println("");
		System.out.println("12. IDA* with Manhattan Distance on WORST");
		System.out.println("");
		
		System.out.println("13. DFBB with Manhattan Distance on EASY");
		System.out.println("");
		System.out.println("14. DFBB with Manhattan Distance on MEDIUM");
		System.out.println("");
		System.out.println("15. DFBB with Manhattan Distance on Hard");
		System.out.println("");
		System.out.println("16. DFBB with Manhattan Distance on WORST");
		System.out.println("");
		System.out.println("*********************************************************************************************");
		
		//read user input
		Scanner scan = new Scanner(System.in);
		int option= scan.nextInt();	

		//hard coded multidim array that represent EASY, MEDIUM, HARD, WORST start states
		int[][] easy= { {1,3,4}, {8,6,2}, {7,0,5} };	//0 is used as a blank.
		int[][] medium= { {2,8,1}, {0,4,3}, {7,6,5} };
		int[][] hard= { {2,8,1}, {4,6,3}, {0,7,5} };
		int[][] worst= { {5,6,7}, {4,0,8}, {3,2,1} };
		
		//goal state
		int[][] goal= { {1,2,3}, {8,0,4}, {7,6,5} };
		
		//Setup goal board
		Board goalBoard=new Board(goal);
		
		//declare intial board variable
		Board initialBoard;
		
		//choose the Heuristic
		HammingDistance h=new HammingDistance();
		ManhattanDistance m=new ManhattanDistance();
		
		//declare instances of Algorithms
		AStar aStarSolver;
		IDAStar idAStarSolver;
		DepthFirstBranchBound dfbbSolver;
		
		//timer variables
		double start;
		double runtime;
		
		switch(option){
		
			case 0:	//exit
				System.exit(0);
				break;
				
			case 1:	//"1. A* with Hamming Distance on EASY"
				//setup easy
				initialBoard=new Board(easy);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,h);
				System.out.println("Results for A* hamming on EASY");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 2:	//"2. A* with Hamming Distance on MEDIUM"
				//setup easy
				initialBoard=new Board(medium);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,h);
				System.out.println("Results for A* hamming on MEDIUM");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 3:	//"1. A* with Hamming Distance on HARD"
				//setup easy
				initialBoard=new Board(hard);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,h);
				System.out.println("Results for A* hamming on HARD");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 4:	//"1. A* with Hamming Distance on WORST"
				//setup easy
				initialBoard=new Board(worst);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,h);
				System.out.println("Results for A* hamming on WORST");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
			
			case 5:	//"1. A* with Manhattan Distance on EASY"
				//setup easy
				initialBoard=new Board(easy);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,m);
				aStarSolver=new AStar(initialBoard,goalBoard,h);
				System.out.println("Results for A* Manhattan on EASY");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 6:	//"2. A* with Manhattan Distance on MEDIUM"
				//setup easy
				initialBoard=new Board(medium);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,m);
				System.out.println("Results for A* Manhattan on MEDIUM");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 7:	//"1. A* with Manhattan Distance on HARD"
				//setup easy
				initialBoard=new Board(hard);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,m);
				System.out.println("Results for A* Manhattan on HARD");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 8:	//"1. A* with Manhattan Distance on WORST"
				//setup easy
				initialBoard=new Board(worst);
				//create AStar instance and solve
				aStarSolver=new AStar(initialBoard,goalBoard,m);
				System.out.println("Results for A* Manhattan on WORST");
				start=System.currentTimeMillis();
				aStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 9:	//"1. IDA* with Manhattan Distance on EASY"
				//setup easy
				initialBoard=new Board(easy);
				//create AStar instance and solve
				idAStarSolver=new IDAStar(initialBoard,goalBoard,m);
				System.out.println("Results for IDA* Manhattan on EASY");
				start=System.currentTimeMillis();
				idAStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 10:	//"2. IDA* with manhattan Distance on MEDIUM"
				initialBoard=new Board(medium);
				//create AStar instance and solve
				idAStarSolver=new IDAStar(initialBoard,goalBoard,m);
				System.out.println("Results for IDA* Manhattan on MEDIUM");
				start=System.currentTimeMillis();
				idAStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 11:	//"1. IDA* with Manhattan Distance on HARD"
				//setup easy
				initialBoard=new Board(hard);
				//create AStar instance and solve
				idAStarSolver=new IDAStar(initialBoard,goalBoard,m);
				System.out.println("Results for IDA* Manhattan on HARD");
				start=System.currentTimeMillis();
				idAStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 12:	//"1. IDA* with Manhattan Distance on WORST"
				//setup easy
				initialBoard=new Board(worst);
				//create AStar instance and solve
				idAStarSolver=new IDAStar(initialBoard,goalBoard,m);
				System.out.println("Results for IDA* Manhattan on WORST");
				start=System.currentTimeMillis();
				idAStarSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 13:	//DFBB with Manhattan Distance on EASY
				initialBoard=new Board(easy);
				dfbbSolver=new DepthFirstBranchBound(initialBoard,goalBoard,m);
				System.out.println("Results for DFBB Manhattan on EASY");
				start=System.currentTimeMillis();
				dfbbSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 14:	//DFBB with Manhattan Distance on MEDIUM
				initialBoard=new Board(medium);
				dfbbSolver=new DepthFirstBranchBound(initialBoard,goalBoard,m);
				System.out.println("Results for DFBB Manhattan on MEDIUM");
				start=System.currentTimeMillis();
				dfbbSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 15:	//DFBB with Manhattan Distance on Hard
				initialBoard=new Board(hard);
				dfbbSolver=new DepthFirstBranchBound(initialBoard,goalBoard,m);
				System.out.println("Results for DFBB Manhattan on HARD");
				start=System.currentTimeMillis();
				dfbbSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;
				
			case 16:	//DFBB with Manhattan Distance on WORST
				initialBoard=new Board(worst);
				dfbbSolver=new DepthFirstBranchBound(initialBoard,goalBoard,m);
				System.out.println("Results for DFBB with Manhattan Distance on WORST");
				start=System.currentTimeMillis();
				dfbbSolver.solve();
				runtime=System.currentTimeMillis()-start;
				System.out.println("RUN TIME= "+ runtime+ " milliseconds");
				break;	
		}

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
System.out.println(x);	//hand calculated md for easy=5, med=7




		
IDAStar idaSolver=new IDAStar(initialBoard,goalBoard,m);
double start=System.currentTimeMillis();
idaSolver.solve();
double end=System.currentTimeMillis();
System.out.println(end-start);

//DepthFirstBranchBound dfbb=new DepthFirstBranchBound(initialBoard,goalBoard,m);
//dfbb.solve();
		
		
*/