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
	private int maskxi =0, maskyi = 0, maskWi = 16, maskHi = 16;
	public double life = 10;
	
	public Goblin(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
	}

	
	public void tick() {
		
		if (life <= 0) {
			Game.goblin.remove(this);
			Game.entidades.remove(this);
		}
		
		//VERIFICAO PARA ATAQUE DA DIREITA
		if(this.colisaoataquer((int)(x+speed), this.getY()) && !coliding((int)(x+speed+3), this.getY())) {
		x+=4;
		life -=3;
		System.out.println("Missão completada");
		}else if(this.colisaoataquer((int)(x+speed), this.getY())) {
		life -=3;	
		System.out.println("Missão completada");
		}
				
		//VERIFICAO PARA ATAQUE DA ESQUERDA 
		if(this.colisaoataque((int)(x-speed), this.getY()) && !coliding((int)(x-speed-3), this.getY())) {
		x-=4;	
		life -=3;
		System.out.println("Missão completada");
		}else if(this.colisaoataque((int)(x-speed), this.getY())) {
		life -=3;
		System.out.println("Missão completada");
		}
		
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
	
	public boolean colisaoataquer(int nextX, int nextY) {
		Rectangle ataque = new Rectangle(nextX + maskxi,nextY + maskyi,maskWi-10,maskHi);	
		for(int i = 0; i < Game.ataquesr.size();i++ ) {
			AtaqueRight e = Game.ataquesr.get(i);
			if(e instanceof AtaqueRight) {
			Rectangle inimigo = new Rectangle(e.getX()+maskx,e.getY()+masky,maskw,maskh);
		 if(ataque.intersects(inimigo)) {
			 return true;	
		 }
		 }
		}
		return false;
		
	}
	
	public boolean colisaoataque(int nextX, int nextY) {
		Rectangle ataque = new Rectangle(nextX + maskxi+10,nextY + maskyi,maskWi-10,maskHi);	
		for(int i = 0; i < Game.ataques.size();i++ ) {
			AtaqueLeft e = Game.ataques.get(i);
			if(e instanceof AtaqueLeft) {
			Rectangle inimigo = new Rectangle(e.getX()+maskx,e.getY()+masky,maskw,maskh);
		 if(ataque.intersects(inimigo)) {
			 return true;	
		 }
		 }
		}
		return false;
		
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
