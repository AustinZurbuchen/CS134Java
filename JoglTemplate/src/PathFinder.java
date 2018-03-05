import java.util.ArrayList;

public class PathFinder {
	//private int[][] background;
	//private Background[][] backgroundTiles;
	private BackgroundGrid backgroundGrid;
	private BackgroundNode start, end;
	private BinaryHeap openList;
	private ArrayList<BackgroundNode> closedList;
	
	public PathFinder(BackgroundGrid backgroundGrid, BackgroundNode start, BackgroundNode end){
		this.backgroundGrid = backgroundGrid;
		this.start = start;
		this.end = end;
	}
	
	public void addNeighbor(BackgroundNode node, boolean curCorners){
		int x = node.getX();
		int y = node.getY();
		for(int dy = -1; dy <= 1; dy++){
			for(int dx = 1; dy >= -1; dx--){
				BackgroundNode neighbor = backgroundGrid.getNode(x + dx, y + dy);
				if(isValidNeighbor(node, neighbor)){
					if(!openList.contains(neighbor)){
						calculateNodeScore(neighbor);
						openList.add(neighbor);
					} else if(calculateGScore(node, neighbor) + node.getGScore() < neighbor.getGScore()){
						neighbor.setParent(node);
						calculateNodeScore(neighbor);
					}
				}
			}
		}
	}
	
	public void calculateNodeScore(BackgroundNode node){
		int manhattan = Math.abs(end.getX() - node.getX()) * 10 + Math.abs(end.getY() - node.getY()) * 10;
		node.setHScore(manhattan);
		BackgroundNode parent = (BackgroundNode)node.getParent();
		node.setGScore(parent.getGScore() + calculateGScore(node, parent));
	}
	
	public int calculateGScore(BackgroundNode newNode, BackgroundNode oldNode){
		int dx = newNode.getX() - oldNode.getX();
		int dy = newNode.getY() - oldNode.getY();
		
		if(dx == 0 || dy == 0){
			return Math.abs(10 * Math.max(dx,  dy));
		} else {
			return Math.abs(14 * Math.max(dx,  dy));
		}
	}
	
	public ArrayList<BackgroundNode> jumpPointSearch(){
		closedList = new ArrayList<>();
		openList = new BinaryHeap(start);
		
		while(!openList.isEmpty()){
			BackgroundNode curNode = (BackgroundNode)openList.pop();
			if(curNode.equals(end)){
				return backTrace(curNode);
			}
			identifySuccessors(curNode);
			closedList.add(curNode);
		}
		return null;
	}
	
	public void identifySuccessors(BackgroundNode curNode){
		for(int dx = -1; dx <= 1; dx++){
			for(int dy = -1; dy <= 1; dy++){
				if(dx == 0 && dy == 0){
					continue;
				}
				if(isValidNeighbor(curNode, backgroundGrid.getNode(curNode.getX() + dx, curNode.getY() + dy))){
					BackgroundNode jumpNode = jump(curNode, dx, dy);
					if(jumpNode != null){
						if(!openList.contains(jumpNode) && !closedList.contains(jumpNode)){
							jumpNode.setParent(curNode);
							calculateNodeScore(jumpNode);
							openList.add(jumpNode);
						}
					}
				}
			}
		}
	}
	
	public BackgroundNode jump(BackgroundNode curNode, int dx, int dy){
		int nextX = curNode.getX() + dx;
		int nextY = curNode.getY() + dy;
		BackgroundNode nextNode = backgroundGrid.getNode(nextX, nextY);
		if(nextNode == null || !nextNode.isPassable()){
			return null;
		}
		if(nextNode.equals(end)){
			return nextNode;
		}
		if(dx != 0 && dy != 0){
			if(backgroundGrid.getNode(nextX - dx, nextY) != null && backgroundGrid.getNode(nextX - dx, nextY + dy) != null){
				if(!backgroundGrid.getNode(nextX - dx, nextY).isPassable() && backgroundGrid.getNode(nextX - dx, nextY + dy).isPassable()){
					return nextNode;
				}
			}
			if(backgroundGrid.getNode(nextX, nextY - dy) != null && backgroundGrid.getNode(nextX + dx, nextY - dy) != null){
                if(!backgroundGrid.getNode(nextX, nextY - dy).isPassable() && backgroundGrid.getNode(nextX + dx, nextY - dy).isPassable()){
                    return nextNode;
                }
			}
            
            if(jump(nextNode, dx, 0) != null || jump(nextNode, 0, dy) != null){
                return nextNode;
			}
		} else {
			if(dx != 0){
				 if(backgroundGrid.isPassable(nextX + dx, nextY) && !backgroundGrid.isPassable(nextX, nextY + 1)){
	                    if(backgroundGrid.isPassable(nextX + dx, nextY + 1)){
	                        return nextNode;
	                    }
				 }
	                
	             if(backgroundGrid.isPassable(nextX + dx, nextY) && !backgroundGrid.isPassable(nextX, nextY - 1)){
	                    if(backgroundGrid.isPassable(nextX + dx, nextY - 1)){
	                        return nextNode;
	                    }
	             } else {
	            	 	if(backgroundGrid.isPassable(nextX, nextY + dy) && !backgroundGrid.isPassable(nextX + 1, nextY)){
	            	 		if(backgroundGrid.isPassable(nextX + 1, nextY + dy)){
	            	 			return nextNode;
	            	 		}
	            	 	}
	                
	            	 	if(backgroundGrid.isPassable(nextX, nextY + dy) && !backgroundGrid.isPassable(nextX - 1, nextY)){
	            	 		if(backgroundGrid.isPassable(nextX - 1, nextY + dy)){
	            	 			return nextNode;
	            	 		}
	            	 	}
	            }
	        }
		}
	    return jump(nextNode, dx, dy);
	}
	
	public ArrayList<BackgroundNode> backTrace(BackgroundNode theNode){
		ArrayList<BackgroundNode> thePath = new ArrayList<>();
		BackgroundNode parent = theNode;
		while(parent != null){
			thePath.add(parent);
			parent = (BackgroundNode)parent.getParent();
		}
		return thePath;
	}
	
	public boolean isValidNeighbor(BackgroundNode node, BackgroundNode neighbor){
		return neighbor != null && neighbor.isPassable() && !closedList.contains(neighbor) && !neighbor.equals(node);
	}

}
