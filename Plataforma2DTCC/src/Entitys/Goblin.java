package Entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;

public class Goblin extends Entity {

	public double speed = 0.3;
	public int direita = 1, esquerda = 0;
	public int direcaoAtual = direita;
	public int movimentacao = 0;
	public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private int maskx = 0, masky = 0, maskw = 15, maskh = 16;
	
	public Goblin(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
	}

	
	public void tick() {
		if(!coliding((int)x, (int)(y+1))) {
			y+=2;
		}
		
		if(Game.player.getX() < this.getX() && !coliding((int)(x-speed), this.getY())) {
			x-=speed;
		}
		
		if(Game.player.getX() > this.getX() && !coliding((int)(x+speed), this.getY())) {
			x+=speed;
		}
	}
	
	public void render() {
		
	}
	
	public boolean coliding(int nextx, int nexty) {
		Rectangle goblin = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.entidades.size(); i++) {
			Entity entidade = Game.entidades.get(i);
			if(entidade instanceof Solido) {
				Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw,maskh);
				if(goblin.intersects(solido)) {
					return true;
				}
			}
		}
		return false;
	}
	
}
