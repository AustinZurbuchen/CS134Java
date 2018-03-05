import java.util.ArrayList;

public class Enemy {
	
	public int x;
	public int y;
	public int z;
	public int xIndex;
	public int yIndex;
	public int width;
	public int height;
	public int currentImage;
	public int image;
	public int[] rightImages;
	public int[] leftImages;
	public double imageIndex = 0;
	public double imageSpeed = 0.15;
	public int shadowOffset = 0;
	public int direction;
	public String dir;
	public int health;
	public int lives;
	public boolean topCollide;
	public boolean bottomCollide;
	public boolean rightCollide;
	public boolean leftCollide;
	public boolean reachedDestination;
	public boolean toRemove;
	public String decision = "attack";
	public double decisionTimer;
	public ArrayList<BackgroundNode> curPath;
	public BackgroundNode curNode;
	public BackgroundNode curDestination;
	
	public Enemy(int X, int Y, int[] spriteSize, int img ,int[] rightImg, int[] leftImg, int dir, int hp, int lives){
		
		this.x = X;
		this.y = Y;
		this.z = 0;
		this.width = spriteSize[0];
		this.height = spriteSize[1];
		this.xIndex = (int) Math.floor((X + (width / 2)) / width);
		this.yIndex = (int) Math.floor((Y + (height / 2)) / height);
		this.image = img;
		this.rightImages = rightImg;
		this.leftImages = leftImg;
		this.currentImage = image;
		this.health = hp;
		this.lives = lives;
		this.toRemove = false;
	}

	public int getX(){return x;}
	public int getY(){return y;}
	public int getZ(){return z;}
	public int getXIndex(){return xIndex;}
	public int getYIndex(){return yIndex;}
	public int getWidth(){return width;}
	public int getHeight(){return height;}
	public int getCurrentImage(){return currentImage;}
	public int getDirection(){return direction;}
	public String getDir(){return dir;}
	public int getHealth(){return health;}
	public int getShadowOffset(){return shadowOffset;}
	public boolean getTopCollide(){return topCollide;}
	public boolean getBottomCollide(){return bottomCollide;}
	public boolean getRightCollide(){return rightCollide;}
	public boolean getLeftCollide(){return leftCollide;}
	public boolean reachedDestination(){return reachedDestination;}
	public ArrayList<BackgroundNode> getCurPath(){return curPath;}
	public BackgroundNode getCurNode(){return curNode;}
	public BackgroundNode getCurDestination(){return curDestination;}
	
	public void setX(int a){x = a;}
	public void setY(int b){y = b;}
	public void setZ(int newZ){z = newZ;}
	public void setWidth(int w){width = w;}
	public void setHeight(int h){height = h;}
	public void setCurrentImage(int img){currentImage = img;}
	public void setDirection(int dir){direction = dir;}
	public void setDir(String dir){this.dir = dir;}
	public void setHealth(int hp){health = hp;}
	public void setShadowOffset(int offset){shadowOffset = offset;}
	public void setTopCollide(boolean col){topCollide = col;}
	public void setBottomCollide(boolean col){bottomCollide = col;}
	public void setRightCollide(boolean col){rightCollide = col;}
	public void setLeftCollide(boolean col){leftCollide = col;}
	public void setReachedDestination(boolean val){reachedDestination = val;}
	public void setToRemove(boolean val){this.toRemove = val;}
	public void setDecision(String newDecision){this.decision = decision;}
	public void setCurNode(BackgroundNode newestNode){curNode = newestNode;}
	public void setCurDestination(BackgroundNode newDestination){curDestination = newDestination;}
	public void setCurPath(ArrayList<BackgroundNode> curPath){this.curPath = new ArrayList<BackgroundNode>(curPath);}
	
	public void update(int X, int Y, int hp, int lives, boolean toRemove){
		x = X;
		y = Y;
		xIndex = (int) Math.floor((X + (width / 2)) / width);
		yIndex = (int) Math.floor((Y + (height / 2)) / height);
		imageIndex += imageSpeed;
		if(imageIndex >= rightImages.length){
			imageIndex = 0;
		}
		if(direction == 1 || direction == 2){
			currentImage = leftImages[(int)imageIndex];
		}
		else if(direction == 0 || direction == 3){
			currentImage = rightImages[(int)imageIndex];
		} else {
			currentImage = image;
		}
		health = hp;
		this.lives = lives;
		this.toRemove = toRemove;
	}
	
	public void updateCurPath(PathFinder pathFinder){
		curPath = new ArrayList<BackgroundNode>(pathFinder.jumpPointSearch());
	}
	
	public void moveCaller(Player player, long deltaTimeMS){
		switch (decision){
			case "attack": 	moveAttack(player, deltaTimeMS);
				break;
			case "patrol": 	int temp = (int) Math.floor(Math.random() * 4);
							if(decisionTimer <= 0){
								decisionTimer = 1000;
								switch (temp){
									case 0:	dir = "up";
										break;
									case 1:	dir = "left";
										break;
									case 2:	dir = "down";
										break;
									case 3:	dir = "right";
										break;
									case 4: dir = "right";
										break;
									default: break;
								}
							} else {
								decisionTimer -= deltaTimeMS;
							}
							moveDirection(dir, deltaTimeMS);
				break;
			case "run": 	moveAway(player, deltaTimeMS);
				break;
			default: break;
		}
	}
	
	public void move(long deltaTimeMS){
		if(curDestination.getY() < y && !topCollide){
			y -= ((1 * deltaTimeMS) / 7);
			shadowOffset = 2;
		}
		if(curDestination.getX() < x && !leftCollide){
			x -= ((1 * deltaTimeMS) / 7);
			shadowOffset = 2;
		}
		if(curDestination.getY() > y && !bottomCollide){
			y += ((1 * deltaTimeMS) / 7);
			shadowOffset = 2;
		}
		if(curDestination.getX() > x && !rightCollide){
			x += ((1 * deltaTimeMS) / 7);
			shadowOffset = 2;
		}
	}
	
	public void moveDirection(String direction, long deltaTimeMS){
		switch (direction){
			case "up": if(!topCollide){y -= ((1 * deltaTimeMS) / 10);this.direction = 0;shadowOffset = 2;}
				break;
			case "left": if(!leftCollide){x -= ((1 * deltaTimeMS) / 10);this.direction = 1;shadowOffset = 2;}
				break;
			case "down": if(!bottomCollide){y += ((1 * deltaTimeMS) / 10);this.direction = 2;shadowOffset = 2;}
				break;
			case "right": if(!rightCollide){x += ((1 * deltaTimeMS) / 10);this.direction = 3;shadowOffset = 2;}
				break;
			default: break;
		}
	}
	
	public void moveAttack(Player player, long deltaTimeMS){
		direction = -1;
    	shadowOffset = 2;
    	if((player.getY() < y - 2) && !(topCollide)){
    		y -= (1 * deltaTimeMS) / 7;
    		direction = 0;
    		shadowOffset = 2;
    	}
    	if((player.getX() < x - 2) && !(leftCollide)){
    		x -= (1 * deltaTimeMS) / 7;
    		direction = 1;
    		shadowOffset = 2;
    	}
    	if((player.getY() > y + 2) && !(bottomCollide)){
    		y += (1 * deltaTimeMS) / 7;
    		direction = 2;
    		shadowOffset = 2;
    	}
    	if((player.getX() > x + 2) && !(rightCollide)){
    		x += (1 * deltaTimeMS) / 7;
    		direction = 3;
    		shadowOffset = 2;
    	}
	}
	
	public void moveAway(Player player, long deltaTimeMS){
		direction = -1;
		shadowOffset = 2;
		if((player.getY() < y - 2) && !(bottomCollide)){
    		y += (1 * deltaTimeMS) / 7;
    		direction = 0;
    		shadowOffset = 2;
    	}
    	if((player.getX() < x - 2) && !(rightCollide)){
    		x += (1 * deltaTimeMS) / 7;
    		direction = 1;
    		shadowOffset = 2;
    	}
    	if((player.getY() > y + 2) && !(topCollide)){
    		y -= (1 * deltaTimeMS) / 7;
    		direction = 2;
    		shadowOffset = 2;
    	}
    	if((player.getX() > x + 2) && !(leftCollide)){
    		x -= (1 * deltaTimeMS) / 7;
    		direction = 3;
    		shadowOffset = 2;
    	}
	}
	
	public boolean hasReachedDestination(){
		if(curDestination.getX() == x && curDestination.getY() == y){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean withinRange(Player player){
		if(yIndex - 15 < player.getYIndex() && player.getYIndex() < yIndex + 15 && xIndex - 15 < player.getXIndex() && player.getXIndex() < xIndex + 15){
			return true;
		} else {
			return false;
		}
	}
	
	public void makeDecision(){
		double temp = Math.random() * 10;
		if(temp <= 4){
			decision = "attack";
		} else if(temp > 4 && temp <= 8){
			decision = "patrol";
		} else {
			decision = "run";
		}
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
