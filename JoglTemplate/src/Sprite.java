
public class Sprite {
	
	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;
	public int currentImage;
	
	public Sprite(int X, int Y, int[] spriteSize, int img){
		x = X;
		y = Y;
		width = spriteSize[0];
		height = spriteSize[1];
		currentImage = img;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getCurrentImage(){return currentImage;}
	
	public void setX(int a){x = a;}
	public void setY(int b){y = b;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
	public void setCurrentImage(int img){currentImage = img;}
	
	public void update(int X, int Y, int img){
		x = X;
		y = Y;
		currentImage = img;
	}
	
	public void findSpace(BackgroundTile[][] background, int X, int Y){
		if(background[Y][X].getImg() == 2){
			findSpace(background, (int) (Math.random() * 100), (int) (Math.random() * 100));
		} else {
			x = X * width;
			y = Y * height;
		}
	}
	
}