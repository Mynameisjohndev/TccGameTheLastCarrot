package Entitys;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;


public class Player extends Entity {

	public boolean right,left,up,down ;
	public double speed = 1;
	
	public int direita = 1, esquerda = 0;
	public int direcaoAtual = direita;
	public int movimentacao = 0;
	public int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private BufferedImage rightplayer[];
	private BufferedImage leftplayer[];
	 
	public Player(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		rightplayer = new BufferedImage[4];
		leftplayer = new BufferedImage[4];
		
		for (int i = 0; i < 4; i++) {
		rightplayer[i] = Game.spritsheet.getSprite(0 + (16*i), 0, 16, 16);
		}
		
		for (int i = 0; i < 4; i++) {
		leftplayer[i] = Game.spritsheet.getSprite(48 - (16*i), 16, 16, 16);
		}		
	}

	public void tick() {
		movimentacao = 0;
		if(right) {
			x+=speed;
			movimentacao = 1;
			direcaoAtual = direita;
		}
		if(left) {
			x-=speed;
			movimentacao = 1;
			direcaoAtual = esquerda;
		}
		if(down) {
			y+=speed;
		}
		if(up) {
			y-=speed;
		}
		
		if(movimentacao == 1) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.Level.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.Level.HEIGHT*16 - Game.HEIGHT);
		
	}
	
	public void render(Graphics g) {
		
		if(direcaoAtual == direita && movimentacao == 1) {
			g.drawImage(rightplayer[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}if(direcaoAtual == direita && movimentacao == 0) {
			g.drawImage(rightplayer[0], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
		if(direcaoAtual == esquerda && movimentacao == 1) {
			g.drawImage(leftplayer[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}if(direcaoAtual == esquerda && movimentacao == 0) {
			g.drawImage(leftplayer[0], this.getX()-Camera.x, this.getY()-Camera.y, null);
		}
		
		
	}
	
	
	
}
