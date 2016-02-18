
public class ManhattanDistance implements Heuristics {
	
	public int getDistance(Board init, Board goal){
		int distance=0;
		int[][] initialArr=init.array;
		int[][] goalArr=goal.array;
		for(int i=0;i<initialArr.length;i++){
			for(int j=0;j<initialArr.length;j++){
				if(initialArr[i][j]!=0){
					int tile=initialArr[i][j];
					loop:
						for(int goalRow=0;goalRow<goalArr.length;goalRow++){
							for(int goalCol=0;goalCol<goalArr.length;goalCol++){
								if(goalArr[goalRow][goalCol]==tile){
									distance+= Math.abs(goalRow-i) + Math.abs(goalCol-j);
									break loop;
									
								}
							}
						}
				}
			}
		}
		return distance;
	}

}
