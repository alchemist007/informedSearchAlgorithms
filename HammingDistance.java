
public class HammingDistance implements Heuristics {
	
	public int getDistance(Board current, Board goal){
		int cost = 0;
		int[][] currArr= current.array;
		int[][] goalArr = goal.array;
		for(int i=0; i<3; i++){
			for(int j = 0; j<3; j++){
					if(currArr[i][j]!=0 && currArr[i][j] != goalArr[i][j])
						cost++;
			}
		}
		return cost;
	}

}
