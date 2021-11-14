package Entitys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.List;

import Main.Game;
import World.Camera;

public class NPC1  extends Entity{

	public int movimentacao = 0;
	public int frames = 0, maxFrames = 16, index = 0, maxIndex = 1;
	private BufferedImage npc[];
	private int maskx = 0, masky = 0, maskw = 16, maskh = 16;
	
	public  String[] respotas = {"0","1","2","3"};
	public  int current = 0;
	public int max = respotas.length-1;
	
	public boolean colisao = false;
	public boolean chat = false;
	public boolean finalChat = false;
	public static boolean enter = false;
	public String[] frases = new String [4];
	public String[] options = {"1","2","3","4"};
	public static int currentOption = 0;
	public int maxOption = options.length-1;
	public static boolean right;
	public static boolean left;
	public static boolean respota;
	public static boolean colisaoplayer;
	
	Random gerador = new Random();
	List<Integer> numeros = new ArrayList<Integer>();
	public static boolean missaoAceita = false;
	public static int totalColetar = 0;
	
	public NPC1(int x, int y, int Width, int Height, BufferedImage sprite) {
		super(x, y, Width, Height, sprite);
		npc = new BufferedImage[2];
		
		for (int i = 0; i < 2; i++) {
			npc[i] = Game.spritsheet.getSprite(80 + (16*i), 128, 16, 16);
		}
		
		int rand = gerador.nextInt(3);
		if(rand == 0) {
			frases[0] = "Olá, bem vindo ao jogo!";
			frases[1] = "Resolva esta equação para passar de fase";
			frases[2] = "Qual a multiplicação de 2+6?";
			totalColetar = 8;
			numeros.add(13);
			numeros.add(5);
			numeros.add(3);
			numeros.add(8);
		}else if(rand == 1) {
			frases[0] = "Olá, bem vindo ao jogo!";
			frases[1] = "Resolva esta equação para passar de fase";
			frases[2] = "Qual a multiplicação de 2x3?";
			totalColetar = 6;
			numeros.add(1);
			numeros.add(0);
			numeros.add(6);
			numeros.add(13);
		}else if(rand == 2) {
			frases[0] = "Olá, bem vindo ao jogo!";
			frases[1] = "Resolva esta equação para passar de fase";
			frases[2] = "Qual a multiplicação de 7-2?";
			totalColetar = 5;
			numeros.add(5);
			numeros.add(13);
			numeros.add(3);
			numeros.add(9);
		}
		//Collections.shuffle(numeros);
		
	}
	
	
	public void tick() {
		
		this.colisaoplayer = false;
		
		if(left == true ) {
			left = false;
			current--;
			if(current < 0) 
				current =	max;
		}
		
		if(right == true) {
			right = false;
			current++;
			if(current > max) 
				current =	0;
		}
		
		if(!colidingSolido((int)x, (int)(y+1))) {
			y+=2;
		}
		
		if(enter && coliding(this.getX(), this.getY())) {
			enter = false;
			currentOption++;
			if(currentOption > maxOption) {
				currentOption = maxOption;
			}
			System.out.println(currentOption);
			if(numeros.get(current) == totalColetar && currentOption == maxOption) {
				missaoAceita = true;
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
					colisaoplayer = true;
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
			
			g.setColor(Color.BLACK);
			g.fillRect(9, 85, Game.WIDTH-18, 27);
			
			
			//g.setColor(Color.WHITE);
			g.setColor(new Color(220,220,220,220));
			g.fillRect(10,86, Game.WIDTH-20, 25);
			g.setFont(new Font("Arial", Font.BOLD, 9));
			g.setColor(Color.black);
			g.drawString(frases[currentOption], 15,102);	
			
			if(currentOption == 1 && Game.player.missao == 0) {
				Game.player.missao = 1;
			}
			
			if(currentOption == 2) {
				if(respotas[current] == "0") {
					g.setColor(Color.black);
					g.fillRect(9, 114, 37, 17);
				}else if (respotas[current] == "1") {
					g.setColor(Color.black);
					g.fillRect(51, 114, 37, 17);
				}else if(respotas[current] == "2") {
					g.setColor(Color.black);
					g.fillRect(93, 114, 37, 17);
				}else if(respotas[current] == "3") {
					g.setColor(Color.black);
					g.fillRect(135, 114, 37, 17);
				}
				int [] inter= {36,76,116,156};
				int [] eixoX= {10,52,94,136};
				for(int i = 0; i<4;i++) {					
					g.setColor(new Color(220,220,220,220));
					g.fillRect(eixoX[i], 115, 35, 15);
					g.setFont(new Font("Arial", Font.BOLD, 9));
					g.setColor(Color.black);
					g.drawString(numeros.get(i).toString(), inter[i],126);
				}
			}
			
			
		}else {
			Game.player.chat = false;
		}
		
	}

}
