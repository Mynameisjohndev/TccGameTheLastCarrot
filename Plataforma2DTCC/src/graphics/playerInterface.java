package graphics;

import java.awt.Color;
import java.awt.Graphics;

import Main.Game;


public class playerInterface {

	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(19, 19, 52, 7);
		
		g.setColor(Color.red);
		g.fillRect(20, 20, 50, 5);
		
		g.setColor(Color.green);
		g.fillRect(20, 20, (int)((Game.player.life/Game.player.maxLife)*50), 5);
		
		
		g.setColor(Color.black);
		g.fillRect(19, 28, 52, 7);
		
		g.setColor(Color.red);
		g.fillRect(20, 29, 50, 5);
		
		g.setColor(Color.blue);
		g.fillRect(20, 29, (int)((Game.player.mana/Game.player.maxMana)*50), 5);
		
		
	}
	
}
