package Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	protected int x;
	protected int y;
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
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.Width;
	}
	
	public int getHeight() {
		return this.Height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}
	
}
