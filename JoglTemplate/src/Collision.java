import java.awt.Point;

public class Collision {
	
	public Collision(){
		
	}
	
	public boolean AABBIntersect(Sprite a, Sprite b){
		if(a.getX() > b.getX() + b.getWidth()){
			return false;
		}
		if(a.getX() + a.getWidth() < b.getX()){
			return false;
		}
		if(a.getY() > b.getY() + b.getHeight()){
			return false;
		}
		if(a.getY() + a.getHeight() < b.getY()){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Sprite a, Projectile p){
		if(a.getX() > p.getX() + p.getWidth() - 8){
			return false;
		}
		if(a.getX() + a.getWidth() < p.getX() + 8){
			return false;
		}
		if(a.getY() > p.getY() + p.getHeight() - 5){
			return false;
		}
		if(a.getY() + a.getHeight() < p.getY() + 5){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Enemy a, Projectile p){
		if(a.getX() > p.getX() + p.getWidth() - 8){
			return false;
		}
		if(a.getX() + a.getWidth() < p.getX() + 8){
			return false;
		}
		if(a.getY() > p.getY() + p.getHeight() - 5){
			return false;
		}
		if(a.getY() + a.getHeight() < p.getY() + 5){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Sprite a, Camera c){
		if(a.getX() > c.getX() + c.getW()){
			return false;
		}
		if(a.getX() + a.getWidth() < c.getX()){
			return false;
		}
		if(a.getY() > c.getY() + c.getH()){
			return false;
		}
		if(a.getY() + a.getHeight() < c.getY()){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Enemy a, Camera c){
		if(a.getX() > c.getX() + c.getW()){
			return false;
		}
		if(a.getX() + a.getWidth() < c.getX()){
			return false;
		}
		if(a.getY() > c.getY() + c.getH()){
			return false;
		}
		if(a.getY() + a.getHeight() < c.getY()){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Sprite a, BackgroundTile b){
		if(a.getX() > (b.getXIndex() * b.getWidth()) + b.getWidth()){
			return false;
		}
		if(a.getX() + a.getWidth() < (b.getXIndex() * b.getWidth())){
			return false;
		}
		if(a.getY() > (b.getYIndex() * b.getHeight()) + b.getHeight()){
			return false;
		}
		if(a.getY() + a.getHeight() < (b.getYIndex() * b.getHeight())){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Enemy a, BackgroundTile b){
		if(a.getX() > (b.getXIndex() * b.getWidth()) + b.getWidth()){
			return false;
		}
		if(a.getX() + a.getWidth() < (b.getXIndex() * b.getWidth())){
			return false;
		}
		if(a.getY() > (b.getYIndex() * b.getHeight()) + b.getHeight()){
			return false;
		}
		if(a.getY() + a.getHeight() < (b.getYIndex() * b.getHeight())){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Projectile a, Camera c){
		if(a.getX() > c.getX() + c.getW()){
			return false;
		}
		if(a.getX() + a.getWidth() < c.getX()){
			return false;
		}
		if(a.getY() > c.getY() + c.getH()){
			return false;
		}
		if(a.getY() + a.getHeight() < c.getY()){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(EnemyProjectile a, Camera c){
		if(a.getX() > c.getX() + c.getW()){
			return false;
		}
		if(a.getX() + a.getWidth() < c.getX()){
			return false;
		}
		if(a.getY() > c.getY() + c.getH()){
			return false;
		}
		if(a.getY() + a.getHeight() < c.getY()){
			return false;
		}
		return true;
	}
	
	public boolean AABBIntersect(Sprite a, EnemyProjectile p, int z){
		if(a.getX() > p.getX() + p.getWidth() - 8){
			return false;
		}
		if(a.getX() + a.getWidth() < p.getX() + 8){
			return false;
		}
		if(a.getY() - z > p.getY() + p.getHeight() - 5){
			return false;
		}
		if(a.getY() - z + a.getHeight() < p.getY() + 5){
			return false;
		}
		return true;
	}
	
	public void AABBIntersect(Enemy a, Enemy b, long deltaTimeMS){
		if(a.getX() < b.getX()){
			if(a.leftCollide){
				b.x += deltaTimeMS * .1;
			} else {
				a.x -= deltaTimeMS * .1;
			}
		}
		if(a.getX() > b.getX()){
			if(a.rightCollide){
				b.x -= deltaTimeMS * .1;
			} else {
				a.x += deltaTimeMS * .1;
			}
		}
		if(a.getY() < b.getY()){
			if(a.topCollide){
				b.y += deltaTimeMS * .1;
			} else {
				a.y -= deltaTimeMS * .1;
			}
		}
		if(a.getY() > b.getY()){
			if(a.bottomCollide){
				b.y -= deltaTimeMS * .1;
			} else {
				a.y += deltaTimeMS * .1;
			}
		}
	}
	
	public boolean MouseCollide(Point mouse, int x, int y, int w, int h){
		if(mouse.x > x && mouse.x < x + w && mouse.y > y && mouse.y < y + h){
			return true;
		} else {
			return false;
		}
	}
}
