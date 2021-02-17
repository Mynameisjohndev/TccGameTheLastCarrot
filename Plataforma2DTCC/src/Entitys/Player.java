package Entitys;

import java.awt.image.BufferedImage;

public class Player extends Entity {

	public boolean right,left,up,down ;
	public double speed = 1;
	
	public Player(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
	}

	public void tick() {
		if(right) {
			x+=speed;
		}
		if(left) {
			x-=speed;
		}
		if(down) {
			y+=speed;
		}
		if(up) {
			y-=speed;
		}
	}
	
	public void render() {
		
	}
	
	
	
}
