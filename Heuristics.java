/**
 * this interface is implemented by the two heuristics function. This allows for polymorphic call of the getDistance().
 * @author nesh2013
 *
 */
public interface Heuristics {
	
	public int getDistance(Board init, Board goal);
	


}
