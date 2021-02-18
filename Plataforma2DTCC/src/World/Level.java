package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Entitys.Entity;
import Entitys.Solido;
import Main.Game;


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
						Game.player.setX(x*16);
						Game.player.setY(y*16);
					}else if(pixelAtual == 0xFF663931) {
						Solido grama = new Solido(x*16,y*16,16,16,Entity.chao);
						Game.entidades.add(grama);
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
	
}
