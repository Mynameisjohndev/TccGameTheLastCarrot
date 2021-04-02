package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Entity {

	private int maskx = 0, masky = 0, maskw = 16, maskh = 16;
	 
	
	public static BufferedImage chao = Game.spritsheet.getSprite(0, 144, 16, 16);
	public static BufferedImage chaoGrama = Game.spritsheet.getSprite(16, 144, 16, 16);
	public static BufferedImage grass = Game.spritsheet.getSprite(0, 128, 16, 16);
	public static BufferedImage empty =  Game.spritsheet.getSprite(32, 144, 16, 16);	
	public static BufferedImage goblin = Game.spritsheet.getSprite(144, 144, 16, 16);
	public static BufferedImage carrot = Game.spritsheet.getSprite(80, 32, 16, 16);
	public static BufferedImage heart = Game.spritsheet.getSprite(96, 32, 16, 16);
	public static BufferedImage NPC1 = Game.spritsheet.getSprite(80, 128, 16, 16);
	public static BufferedImage pressc = Game.spritsheet.getSprite(96, 96, 32, 16);
	
	///sprites diferntes
	public static BufferedImage hud = Game.hud.getSprite(0, 0, 130, 32);
	public static BufferedImage sky =  Game.skySprite.getSprite(0, 0, 2080, 520);
	public static BufferedImage background =  Game.backgroundSprite.getSprite(0, 0, 2080, 320);
	
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
	
	public static boolean isColidding(Entity e1,Entity e2){
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx,e1.getY()+e1.masky,e1.maskw,e1.maskh);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx,e2.getY()+e2.masky,e1.maskw,e1.maskh);
		return e1Mask.intersects(e2Mask);
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()-Camera.x, this.getY()-Camera.y, null);
	}
	
}
