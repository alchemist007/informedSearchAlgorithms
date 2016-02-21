
/**
 * implements the heuristics interface. calculates the manhattan distance
 * @author nesh2013
 *
 */
public class ManhattanDistance implements Heuristics {

	/**
	 * get distance is the only method 
	 */
	public int getDistance(Board init, Board goal){
		int distance=0;
		int[][] initialArr=init.array;
		int[][] goalArr=goal.array;
		for(int row=0;row<initialArr.length;row++){
			for(int col=0;col<initialArr.length;col++){
				if(initialArr[row][col]!=0){	//if its the blank space ignore it.
					int toCompareTile=initialArr[row][col];
					search:
						for(int goalRow=0;goalRow<goalArr.length;goalRow++){
							for(int goalCol=0;goalCol<goalArr.length;goalCol++){
								if(goalArr[goalRow][goalCol]==toCompareTile){
									distance+= Math.abs(goalRow-row) + Math.abs(goalCol-col);
									break search;	//used to break the outer for loop
									
								}
							}
						}
				}
			}
		}
		return distance;
	}

}
