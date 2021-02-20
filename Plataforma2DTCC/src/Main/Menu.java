package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Menu {

	public  String[] options = {"NOVO JOGO","CARREGAR JOGO","SAIR"};
	public  boolean pause = false;
	public  int currentOption = 0;
	public int maxOption = options.length-1;
	public boolean up,down,enter;
	
	public void tick() {
		System.out.println("tick");
		if(up) {
			System.out.println("cima");
			up = false;
			currentOption--;
			if(currentOption < 0) 
				currentOption =	maxOption;
			
		}
		
		if(down) {
			System.out.println("baixo");
			down = false;
			currentOption++;
			if(currentOption > maxOption) 
				currentOption =	0;
			
		}
		if(enter) {
			enter = false;
			if(options[currentOption] == "NOVO JOGO" || options[currentOption] == "CONTINUAR") {
				Game.gameState = "Normal";
				pause = false;
				
			}else if(options[currentOption] == "CARREGAR JOGO") {
				
			}else if(options[currentOption] == "SAIR"){
				System.exit(1);
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,28));
		g.drawString("GAME MENU", Game.WIDTH*Game.SCALE/2 - 100, Game.HEIGHT*Game.SCALE/2 - 30);
		
		if(pause == false) 
		g.drawString("NEW GAME", Game.WIDTH*Game.SCALE/2 - 90, Game.HEIGHT*Game.SCALE/2 + 10);
		else
		g.drawString("CONTINUE", Game.WIDTH*Game.SCALE/2 - 85, Game.HEIGHT*Game.SCALE/2 + 10);	
			
		g.drawString("LOAD GAME", Game.WIDTH*Game.SCALE/2 - 95, Game.HEIGHT*Game.SCALE/2 + 50);
		
		g.drawString("QUIT GAME", Game.WIDTH*Game.SCALE/2 - 90, Game.HEIGHT*Game.SCALE/2 + 90);
		
		if(options[currentOption] == "NOVO JOGO") {
			g.drawString(">", Game.WIDTH*Game.SCALE/2 - 110, Game.HEIGHT*Game.SCALE/2 + 10);	
		}else if(options[currentOption] == "CARREGAR JOGO") {
			g.drawString(">", Game.WIDTH*Game.SCALE/2 - 115, Game.HEIGHT*Game.SCALE/2 + 50);
		}else if(options[currentOption] == "SAIR"){
			g.drawString(">", Game.WIDTH*Game.SCALE/2 - 110, Game.HEIGHT*Game.SCALE/2 + 90);
		}
		
	}
	
	
}
