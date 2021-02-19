package Main;

import java.awt.Color;
import java.awt.Graphics;


public class playerInterface {

	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(19, 19, 52, 7);
		
		g.setColor(Color.red);
		g.fillRect(20, 20, 50, 2);
		
		g.setColor(Color.green);
		g.fillRect(20, 20, (int)((Game.player.life/Game.player.maxLife)*50), 5);
	}
	
}
