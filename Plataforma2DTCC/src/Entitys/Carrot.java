package Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;

public class Carrot extends Entity{

	
	private int maskx = 0, masky = 0, maskw = 15, maskh = 16;
	
	public Carrot(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		if(!coliding(this.getX(), this.getY()+1) ) {
			y+=1;
		}
		
		
		
	}
	
	public boolean coliding(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.entidades.size(); i++) {
			Entity entidade = Game.entidades.get(i);
			if(entidade instanceof Solido) {
				Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw,maskh);
				if(player.intersects(solido)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
