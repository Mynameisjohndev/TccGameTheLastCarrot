package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
					
						tiles[x+(y*WIDTH)] = new Empty(x*16,y*16,Tile.empty);
					if(pixelAtual == 0xFF5fcde4) {
						Game.player.setX(x*16);
						Game.player.setY(y*16);
					}else if(pixelAtual == 0xFF663931) {
						tiles[x+(y*WIDTH)] = new Flor(x*16,y*16,Tile.chao);
					}
					
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		
		for (int xx = 0; xx < WIDTH; xx++) {
			for (int yy = 0; yy < HEIGHT; yy++) {
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
