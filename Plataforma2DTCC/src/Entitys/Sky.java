package Entitys;

import java.awt.image.BufferedImage;

import Main.Game;


public class Sky extends Entity{

	public Sky(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
	}

	public void tick() {
		if(Game.player.right && Game.player.ceuright == true) {
			x-=Game.player.speed - 0.75;
		}
		
		if(Game.player.left && Game.player.ceuleft == true ) {
			x+=Game.player.speed - 0.75;	
		}
		
	}
	
}
