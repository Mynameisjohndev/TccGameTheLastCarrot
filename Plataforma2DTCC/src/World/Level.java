package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Entitys.Carrot;
import Entitys.Entity;
import Entitys.Goblin;
import Entitys.Grass;
import Entitys.Heart;
import Entitys.Hud;
import Entitys.Player;
import Entitys.Sky;
import Entitys.Solido;
import Main.Game;
import graphics.Spritsheet;


public class Level {

	private Tile[] tiles;
	public static int WIDTH;
	public static int HEIGHT;
	
	public Level(String path) {
		try {
			BufferedImage level = ImageIO.read(getClass().getResource(path));
			int [] pixels = new int[level.getWidth() * level.getHeight()];
			WIDTH = level.getWidth();
			HEIGHT = level.getHeight();
			tiles = new Tile[level.getWidth()*level.getHeight()];
			level.getRGB(0, 0, level.getWidth(), level.getHeight(), pixels, 0, level.getWidth());
			for(int x = 0; x < level.getWidth(); x++) {
				for(int y = 0; y < level.getHeight(); y++) {
					int pixelAtual = pixels[x + (y * level.getWidth())]; 
					
					tiles[x+(y*WIDTH)] = new Flor(x*16,y*16,Entity.empty);
					if(pixelAtual == 0xFF5fcde4) {
						//PLAYER
						Game.player.setX(x*16);
						Game.player.setY(y*16);
					}else if(pixelAtual == 0xFF663931) {
						Solido chao = new Solido(x*16,y*16,16,16,Entity.chao);
						Game.entidades.add(chao);
					}else if(pixelAtual == 0xFF4b692f) {
						Solido chaoGrama = new Solido(x*16,y*16,16,16,Entity.chaoGrama);
						Game.entidades.add(chaoGrama);
					}else if(pixelAtual == 0xFF11bf22) {
						//GRASS
						Grass chaoGrama = new Grass(x*16,y*16,16,16,Entity.grass);
						Game.entidades.add(chaoGrama);
					}
					else if(pixelAtual == 0xFFff0000) {
						Goblin goblin = new Goblin(x*16,y*16,16,16,Entity.goblin);
						Game.goblin.add(goblin);
					}else if(pixelAtual == 0xFF00ff36) {
						Heart heart = new Heart(x*16,y*16,16,16,Entity.heart);
						Game.heart.add(heart);
					}else if(pixelAtual == 0xFFff5100) {
						Carrot carrot = new Carrot(x*16,y*16,16,16,Entity.carrot);
						Game.carrot.add(carrot);
					}else if(pixelAtual == 0xFF0b1a9b) {
						//CEU
						Sky ceu = new Sky(x*16,y*16,16,16,Entity.sky);
						Game.sky.add(ceu);
						ceu.setX(-50);
					}	
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x/16;
		int ystart = Camera.y/16;
		
		int xfinal = xstart + (Game.WIDTH/16);
		int yfinal = ystart + (Game.HEIGHT/16);
		
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if(xx <0 || yy <0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
	public static void newLevel(String level) {
		Game.entidades = new ArrayList<Entity>();
		Game.spritsheet = new Spritsheet("/spriteSheet.png");
		Game.sky = new ArrayList<Sky>();
		Game.skySprite = new Spritsheet("/ceusprite.png");
		
		Game.carrot = new ArrayList<Carrot>();
		Game.heart = new ArrayList<Heart>();
		Game.goblin = new ArrayList<Goblin>();
		
		Game.player = new Player(0,0,16,16,Game.spritsheet.getSprite(0,0,16,16));
		Game.entidades.add(Game.player);
		Game.world = new Level("/"+level);
	}
	
}
