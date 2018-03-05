
public class Projectile extends Sprite{
	
	public int x = 0;
	public int y = 0;
	public int width = 0;
	public int height = 0;
	public int currentImage;
	public double direction = 0;
	public int bVelocity = 9;
	public double yVelocity = 0;
	public double xVelocity = 0;
	
	public Projectile(int X, int Y, double dir, int[] spriteSize, int img){
		super(X, Y, spriteSize, img);
		
		x = X;
		y = Y;
		direction = dir;
		width = spriteSize[0];
		height = spriteSize[1];
		xVelocity = Math.cos(dir) * bVelocity;
		yVelocity = Math.sin(dir) * bVelocity;
		currentImage = img;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public double getDirection(){return direction;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getCurrentImage(){return currentImage;}
	
	public void setX(int a){x = a;}
	public void setY(int b){y = b;}
	public void setDirection(int dir){direction = dir;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
	public void setCurrentImage(int img){currentImage = img;}
	
	public void update(int X, int Y, int img){
		x = X;
		y = Y;
		//direction = dir;
		currentImage = img;
		x += xVelocity;
		y += yVelocity;
		
		/*switch (dir){
			case 0: y -= bVelocity;
				break;
			case 1: x -= bVelocity;
					y -= bVelocity;
				break;
			case 2: x -= bVelocity;
				break;
			case 3: x -= bVelocity;
					y += bVelocity;
				break;
			case 4:	y += bVelocity;
				break;
			case 5:	x += bVelocity;
					y += bVelocity;
				break;
			case 6:	x += bVelocity;
				break;
			case 7:	x += bVelocity;
					y -= bVelocity;
				break;
			default: break;
		}*/
	}

}