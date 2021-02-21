package Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Grass extends Entity{

	public int movimentacao = 0;
	public int frames = 0, maxFrames = 7, index = 0, maxIndex = 3;
	private BufferedImage grass[];
	
	public Grass(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		grass = new BufferedImage[4];
		
		for (int i = 0; i < 4; i++) {
			grass[i] = Game.spritsheet.getSprite(0 + (16*i), 128, 16, 16);
		}
		
	}
	
	
	public void tick() {
		
		if(movimentacao == 0) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(grass[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
	}
	
}
