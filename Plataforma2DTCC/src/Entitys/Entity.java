package Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Entity {

	public static BufferedImage chao = Game.spritsheet.getSprite(0, 112, 16, 16);
	public static BufferedImage empty =  Game.spritsheet.getSprite(32, 144, 16, 16);	
	
	protected double x;
	protected double y;
	protected int Width;
	protected int Height;
	protected BufferedImage sprite;
	
	public Entity(int x, int y, int Width, int Height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.Width = Width;
		this.Height = Height;
		this.sprite = sprite;
	}
	
	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}
	
	public int getWidth() {
		return this.Width;
	}
	
	public int getHeight() {
		return this.Height;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}
	
	public void setWidth(int newWidth) {
		this.Width = newWidth;
	}
	

	public void setHeight(int newHeight) {
		this.Height = newHeight;
	}
	
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()-Camera.x, this.getY()-Camera.y, null);
	}
	
}
