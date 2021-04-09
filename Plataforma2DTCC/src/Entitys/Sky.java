package Entitys;

import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.Level;


public class Sky extends Entity{

	public Sky(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
	}

	public void tick() {
		if(Game.player.right && Game.player.ceuright == true && Game.player.chat == false 
				&& Game.player.getX() <= (Level.WIDTH*16)-16) {
			x-=Game.player.speed - 0.75;
		}
		
		if(Game.player.left && Game.player.ceuleft == true && Game.player.chat == false
				&& Game.player.getX() >= 0) {
			x+=Game.player.speed - 0.75;	
		}
		
	}
	
}
