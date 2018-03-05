
public class Camera {
	
	public int X = 0;
	public int Y = 0;
	public int W = 0;
	public int H = 0;
	
	public Camera(int startX, int startY, int startW, int startH){
		X = startX;
		Y = startY;
		W = startW;
		H = startH;
	}
	
	public int getX(){return X;}
	public int getY(){return Y;}
	public int getW(){return W;}
	public int getH(){return H;}
	
	public void setX(int newX){X = newX;}
	public void setY(int newY){Y = newY;}
	public void setW(int newW){W = newW;}
	public void setH(int newH){H = newH;}

}
