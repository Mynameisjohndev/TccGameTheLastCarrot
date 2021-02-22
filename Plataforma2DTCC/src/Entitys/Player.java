package Entitys;

import java.awt.Graphics;
import java.awt.Rectangle;
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
	
	private int maskx = 0, masky = 0, maskw = 15, maskh = 16;
	 
	public boolean jump = false;
	public boolean isJump = false;
	public int jumpHeigth = 30;
	public int jumpFrames = 0;
	public int quantidadeDePulos = 0; 
	
	public static double life = 100, maxLife = 100;
	public static int mana = 100, maxMana = 100;
	
	public Goblin goblinAtual; 
	
	public boolean ceuright = false;
	public boolean ceuleft = false;
	
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
		
		if(!coliding((int)x, (int)(y+1)) && isJump == false) {
			y+=2;
			for(int i =0; i< Game.goblin.size(); i++) {
				Goblin e = Game.goblin.get(i);
				if(e instanceof Goblin) {
					if(damage(this.getX(), this.getY())) {
						isJump = true;
						goblinAtual.life--;
						if(goblinAtual.life == 0) {
							Game.goblin.remove(goblinAtual);
							break;
						}
					}
				}
				break;
			}
			
		}
		
		if(coliding((int)(x+speed), this.getY())) {
			ceuright = false;	
		}
		if(coliding((int)(x-speed), this.getY())) {
			ceuleft = false;	
		}	
		
		if(right && !coliding((int)(x+speed), this.getY())) {
			x+=speed;
			movimentacao = 1;
			direcaoAtual = direita;
			ceuright = true;
		}
		
		if(left && !coliding((int)(x-speed), this.getY())) {
			x-=speed;
			movimentacao = 1;
			direcaoAtual = esquerda;
			ceuleft = true;
		}
		
		if(quantidadeDePulos >= 0 && quantidadeDePulos <=1) { 
			   if(jump) {
			   	if(coliding(this.getX(), this.getY()+1) || !coliding(this.getX(), this.getY()+1)) {
				  
				   isJump = true;
			   	}
		   	}
		   }
		
		if(isJump) {
			   if(!coliding(this.getX(), this.getY()-2)) {
				   y-=2;
				   jumpFrames+=2;
				   //Jump animation sao as particulas que saem do personagem no pulo}
				   if(jumpFrames == jumpHeigth) {
					   //gravidade+=1;
					   isJump = false;
					   jump = false;
					   jumpFrames = 0;
				   }
			   }else {
				   isJump = false;
				   jump = false;
				   jumpFrames = 0;  
			   }
		   }
		
		if(coliding(this.getX(), this.getY()+1)) {
			   quantidadeDePulos = 0;
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
		
		if(life > maxLife) {
			life = 100;
		}
		if(damage(this.getX(), this.getY())) {
			life-=0.75;
		}
		if(life <= 0) {;
			Game.gameState = "gameOver";
		}
		
		if(heart(this.getX(), this.getY())) {
			
		}
		
			Camera.x = Camera.clamp(this.getX()-30 - (Game.WIDTH/2), 0, World.Level.WIDTH*16 - Game.WIDTH);
			Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.Level.HEIGHT*16 - Game.HEIGHT);
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
	
	public boolean damage(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.goblin.size(); i++) {
			Goblin goblin = Game.goblin.get(i);
			if(goblin instanceof Goblin) {
				Rectangle solido = new Rectangle(goblin.getX() + maskx, goblin.getY() + masky, maskw,maskh);
				if(player.intersects(solido)) {
					goblinAtual = goblin;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean heart(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.heart.size(); i++) {
			Heart heart = Game.heart.get(i);
			if(heart instanceof Heart) {
				Rectangle solido = new Rectangle(heart.getX() + maskx, heart.getY() + masky, maskw,maskh);
				if(player.intersects(solido)) {
					if(life < 100) {
						Game.heart.remove(heart);
						life +=5;
					}
					return true;
				}
			}
		}
		return false;
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
