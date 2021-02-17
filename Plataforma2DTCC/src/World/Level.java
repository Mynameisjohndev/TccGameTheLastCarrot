package World;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Level {

	public Level(String path) {
		try {
			BufferedImage level = ImageIO.read(getClass().getResource(path));
			int [] pixels = new int[level.getWidth() * level.getHeight()];
			level.getRGB(0, 0, level.getWidth(), level.getHeight(), pixels, 0, level.getWidth());
		
			for(int i = 0; i < pixels.length; i++) {
				if(pixels[i] == 0xFF5fcde4) {
					System.out.println("Cor");
				}
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
