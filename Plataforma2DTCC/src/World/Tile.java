package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tile {

	public static BufferedImage chao = Game.spritsheet.getSprite(0, 112, 16, 16);
	public static BufferedImage empty =  Game.spritsheet.getSprite(32, 144, 16, 16);
	
	public BufferedImage sprite;
	public int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
	
}
