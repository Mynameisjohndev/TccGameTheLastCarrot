package Entitys;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class AtaqueRight extends Entity {

	public static BufferedImage ataqueS = Game.spritsheet.getSprite(32, 80, 16, 16);
	public double life = 0, maxlife = 2;
	public double speed = 1.05;
	
	public AtaqueRight(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}


	public void tick() {
		x+=speed;
		
		if(this.getX() < Game.player.getX()) {
			//x+=Game.player.speed;
		}
		if(this.getX() > Game.player.getX()) {
			//x-=Game.player.speed;
		}
		if(this.getY() < Game.player.getY()) {
			//y+=Game.player.speed;
		}
		if(this.getY() > Game.player.getY()) {
			//y-=Game.player.speed;
		}
		
		life+=0.5;
		if(life == maxlife) {
			Game.ataquesr.remove(this);
		}
		
	
	
	}
	
	
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.BLUE);
		//g.fillRect(this.getX() + 8 - Camera.x, this.getY()-Camera.y + 0, 8, 16);
		g.drawImage(ataqueS, this.getX()-Camera.x, this.getY()-Camera.y, null, null);
	}
	
}
