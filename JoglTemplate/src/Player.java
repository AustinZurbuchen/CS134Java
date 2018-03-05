
public class Player extends Sprite{
	
	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;
	public int xIndex = 0;
	public int yIndex = 0;
	public int currentImage;
	public int health = 0;
	
	public Player(int X, int Y, int[] spriteSize, int img, int hp){
		super(X, Y, spriteSize, img);
		
		x = X;
		y = Y;
		width = spriteSize[0];
		height = spriteSize[1];
		xIndex = (int) Math.floor((X + (width / 2)) / width);
		yIndex = (int) Math.floor((Y + (height / 2)) / height);
		currentImage = img;
		health = hp;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getXIndex(){return xIndex;}
	public int getYIndex(){return yIndex;}
	public int getCurrentImage(){return currentImage;}
	public int getHealth(){return health;}
	
	public void setX(int a){x = a;}
	public void setY(int b){y = b;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
	public void setXIndex(int newXIndex){xIndex = newXIndex;}
	public void setYIndex(int newYIndex){yIndex = newYIndex;}
	public void setCurrentImage(int img){currentImage = img;}
	public void setHealth(int hp){health = hp;}
	
	public void update(int X, int Y, int img, int hp){
		x = X;
		y = Y;
		xIndex = (int) Math.floor((X + (width / 2)) / width);
		yIndex = (int) Math.floor((Y + (height / 2)) / height);
		currentImage = img;
		health = hp;
	}

}
