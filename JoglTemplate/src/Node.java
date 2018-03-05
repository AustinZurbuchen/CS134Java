
public abstract class Node implements Comparable{

	private Node parentNode;
	private boolean isPassable = true;
	
	public abstract int getFScore();
	
	public Node getParent(){
		return this.parentNode;
	}
	
	public void setParent(Node parent){
		this.parentNode = parent;
	}
	
	public boolean isPassable(){
		return this.isPassable;
	}
	
	public void setPassable(boolean val){
		this.isPassable = val;
	}
	
	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Node)){
			return 0;
		}
		Node other = (Node)o;
		if(getFScore() < other.getFScore()){
			return -1;
		} else if(getFScore() > other.getFScore()){
			return 1;
		} else {
			return 0;
		}
	}

}
