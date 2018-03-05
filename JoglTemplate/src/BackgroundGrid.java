
public class BackgroundGrid {
	
	private BackgroundNode[][] myNodes;
	private BackgroundNode start;
	private BackgroundNode end;
	private PathFinder pathFinder;
	
	public BackgroundGrid(int width, int height){
		this.myNodes = new BackgroundNode[width][height];
		pathFinder = new PathFinder(this, start, end);
		//pathFinder.jumpPointSearch();
	}
	
	public BackgroundNode getNode(int x, int y){
		if(x >= this.myNodes.length || x < 0 || y >= this.myNodes[0].length || y < 0){
			return null;
		}
		return this.myNodes[x][y];
	}
	
	public boolean isPassable(int x, int y){
		if(x >= this.myNodes.length || x < 0 || y >= this.myNodes[0].length || y < 0){
			return false;
		}
		return this.myNodes[x][y].isPassable();
	}
	
	public void fillNodes(BackgroundNode[][] background, Enemy enemy){
		int a = 0;
		int b = 0;
		for(int i = enemy.getYIndex() - 15; i < enemy.getYIndex() + 15; i++){
			b = 0;
			for(int j = enemy.getXIndex() - 15; j < enemy.getXIndex() + 15; j++){
				myNodes[a][b] = background[i][j];
				b++;
			}
			a++;
		}
	}

}
