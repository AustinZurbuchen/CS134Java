
public class BackgroundNode extends Node{

	private int myX, myY;
	private int gScore, hScore;
	
	public BackgroundNode(int x, int y, boolean isPassable){
		this.myX = x;
		this.myY = y;
		this.setPassable(isPassable);
		this.gScore = gScore;
		this.hScore = hScore;
	}
	
	public void setLocation(int x, int y){
		this.myX = x;
		this.myY = y;
	}
	
	public int getX(){
		return this.myX;
	}
	
	public int getY(){
		return this.myY;
	}
	
	@Override
	public int getFScore() {
		return hScore + gScore;
	}
	
	public void setHScore(int hScore){
		this.hScore = hScore;
	}
	
	public void setGScore(int gScore){
		this.gScore = gScore;
	}
	
	public int getGScore(){
		return this.gScore;
	}
	
	public String toString(){
		return "x: " + this.myX + " Y: " + this.myY + " F: " + getFScore() + " G: " + getGScore();
	}

	
	
//	public Background backgroundTile;
//	public BackgroundNode parent;
//	public BackgroundNode child;
//	public int nodeScore;
//	public int gScore, hScore;
//	
//	public BackgroundNode(Background backgroundTile){
//		this.backgroundTile = backgroundTile;
//	}
//	
//	public Background getBackgroundTile(){return backgroundTile;}
//	public BackgroundNode getParent(){return parent;}
//	public BackgroundNode getChild(){return child;}
//	public int getNodeScore(){return nodeScore;}
//	public int getGScore(){return gScore;}
//	public int getHScore(){return hScore;}
//	public int getFScore(){return hScore + gScore;}
//	
//	public void setBackgroundTile(Background newTile){this.backgroundTile = newTile;}
//	public void setParent(BackgroundNode newParent){this.parent = newParent;}
//	public void setChild(BackgroundNode newChild){this.child = newChild;}
//	public void setNodeScore(int newNodeScore){this.nodeScore = newNodeScore;}
//	public void setGScore(int newGScore){this.nodeScore = newGScore;}
//	public void setHScore(int newHScore){this.hScore = newHScore;}
}
