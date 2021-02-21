package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Entitys.Player;



public class Menu {

	public  String[] options = {"NOVO JOGO","CARREGAR JOGO","SAIR"};
	public static  boolean pause = false;
	public  int currentOption = 0;
	public int maxOption = options.length-1;
	public boolean up,down,enter;
	
	public static boolean saveExist = false;
	public static boolean saveGame = false;
	
	public void tick() {
		
		File file = new File("save.txt");
		if(file.exists()) {
			saveExist = true;
			}else{
			saveExist = false;
			}
		
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0) 
				currentOption =	maxOption;
			
		}
		
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
				file = new File("save.txt");
				//file.delete();
			}else if(options[currentOption] == "CARREGAR JOGO") {	
				System.out.println("carregar");
				file = new File("save.txt");
				if(file.exists()) {
					String save = carregarGame(10);
					aplaySave(save);
				}
			}else if(options[currentOption] == "SAIR"){
				System.exit(1);
			}
		}
		
	}
	
	public static void aplaySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0]) {
			case "level":
				World.Level.newLevel("level"+spl2[1]+".png");
				Game.gameState = "Normal";
				pause = false;
				break;
			case "life":
				Game.player.life = Double.parseDouble(spl2[1]);
				break;
			}
		}	
	}
	
	public static String carregarGame(int encode) {
		String line="";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
			String singleLine = null;
			BufferedReader reader = new	BufferedReader(new FileReader("save.txt"));
			try {
			while((singleLine = reader.readLine()) != null){
				String[] trans = singleLine.split(":");
				char[] val = trans[1].toCharArray();
				trans[1] = "";
				for(int i = 0; i < val.length; i++) {
					val[i]-=encode;
					trans[1]+=val[i];
				}
				line+=trans[0];
				line+=":";
				line+=trans[1];
				line+="/";
			}
			}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void saveGame(String[] val1, int[] val2, int encode) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("save.txt"));	
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < val1.length; i++) {
			String current = val1[i];
			current+=":";
			char [] value = Integer.toString(val2[i]).toCharArray();	
			for (int n = 0; n < value.length; n++) {
				value[n]+=encode;
				current+=value[n];
			}
			try {
				write.write(current);
				if(i < val1.length -1)
					write.newLine();
			}catch(IOException e) {}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {}
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
