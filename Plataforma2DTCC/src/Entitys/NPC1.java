package Entitys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class NPC1  extends Entity{

	public int movimentacao = 0;
	public int frames = 0, maxFrames = 16, index = 0, maxIndex = 1;
	private BufferedImage npc[];
	private int maskx = 0, masky = 0, maskw = 16, maskh = 16;
	
	public boolean colisao = false;
	public boolean chat = false;
	public boolean finalChat = false;
	public static boolean enter = false;
	public String[] frases = new String [5];
	public String[] options = {"1","2","3"};
	public int currentOption = 0;
	public int maxOption = options.length-1;
	
	public NPC1(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		npc = new BufferedImage[2];
		
		for (int i = 0; i < 2; i++) {
			npc[i] = Game.spritsheet.getSprite(80 + (16*i), 128, 16, 16);
		}
		
		frases[0] = "Olá, bem vindo ao jogo : )";
		frases[1] = "Sua missão é derrotar estes inimigos : )";
	}
	
	
	public void tick() {
		
		if(!colidingSolido((int)x, (int)(y+1))) {
			y+=2;
		}
		
		if(enter) {
			enter = false;
			currentOption++;
			if(currentOption >maxOption) {
				currentOption = maxOption;
			}
		}
		
		if(movimentacao == 0) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		if(coliding(this.getX(), this.getY())) {
			colisao = true;
		}
		
		if(!coliding(this.getX(), this.getY())) {
			chat = false;
			currentOption = 0;
		}
		
	}
	
	public boolean colidingSolido(int nextx, int nexty) {
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
	
	public boolean coliding(int nextx, int nexty) {
		Rectangle player = new Rectangle(nextx + maskx, nexty + masky, maskw, maskh);
		for(int i = 0; i < Game.entidades.size(); i++) {
			Entity entidade = Game.entidades.get(i);
			if(entidade instanceof Player) {
				Rectangle solido = new Rectangle(entidade.getX() + maskx, entidade.getY() + masky, maskw,maskh);
				if(player.intersects(solido)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(npc[index], this.getX()-Camera.x, this.getY()-Camera.y, null);
		
		if(colisao == true && chat == false) {
			g.drawImage(Entity.pressc, this.getX()-Camera.x, this.getY()-Camera.y - 12, null);			
		}
		
		if(coliding(this.getX(), this.getY()) && chat == true && currentOption < maxOption) {
			Game.player.chat = true;
			g.setColor(new Color(0,0,0,100));
			g.fillRect(10, 71, Game.WIDTH-20, 58);
			
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.black);
			g.drawString(frases[currentOption], 15,80);		
			if(currentOption == 1 && Game.player.missao == 0) {
				Game.player.missao = 1;
				System.out.println("new mission");
			}
		}else {
			Game.player.chat = false;
		}
		
	}

}
