import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Background {
	public ArrayList<int[][]> levels = new ArrayList<int[][]>();
	public File level01 = new File("levels\\level01.txt");
	public File level02 = new File("levels\\level02.txt");	
	
	public Background(){
		try {
			loadFromFile(level01);
			loadFromFile(level02);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFromFile(File fileToLoad) throws FileNotFoundException{
		Scanner in = new Scanner(fileToLoad);
		int[][] temp = new int[100][100];
		in.useDelimiter(",\\s*");
		while(in.hasNext()){
			for(int i = 0; i < 100; i++){
				for(int j = 0; j < 100; j++){
					if(in.hasNextInt()){
						temp[i][j] = in.nextInt();
					} else {
						temp[i][j] = 1;
						in.next();
					}
				}
			}
		}
		levels.add(temp);
		in.close();		
	}
	
	public BackgroundTile[][] updateBackgroundTiles(BackgroundTile[][] backgroundTiles, int levelNum, int spriteWidth, int spriteHeight){
		for(int i = 0; i < levels.get(levelNum).length; i++){
			for(int j = 0; j < levels.get(levelNum)[i].length; j++){
				switch (levels.get(levelNum)[i][j]){
					case 1: backgroundTiles[i][j] = new BackgroundTile(levels.get(levelNum)[i][j], false, i, j, spriteWidth, spriteHeight);
							//backgroundNodes[i][j] = new BackgroundNode(j, i, true);
						break;
					case 2: backgroundTiles[i][j] = new BackgroundTile(levels.get(levelNum)[i][j], true, i, j, spriteWidth, spriteHeight);
							//backgroundNodes[i][j] = new BackgroundNode(j, i, false);
						break;
					default: break;
				}
			}
		}
		return backgroundTiles;
	}
}
