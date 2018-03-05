
public class BackgroundTile {
	
	public int image;
	public boolean collidable;
	public int yIndex;
	public int xIndex;
	public int width;
	public int height;
	
	public BackgroundTile(int img, boolean col, int yindex, int xindex, int Width, int Height){
		image = img;
		collidable = col;
		yIndex = yindex;
		xIndex = xindex;
		width = Width;
		height = Height;
	}

	public int getImg(){return image;}
	public boolean isCollidable(){return collidable;}
	public int getYIndex(){return yIndex;}
	public int getXIndex(){return xIndex;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	
	public void setImg(int newImg){image = newImg;}
	public void setIsCollidable(boolean col){collidable = col;}
	public void setYIndex(int newYIndex){yIndex = newYIndex;}
	public void setXIndex(int newXIndex){xIndex = newXIndex;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
}
