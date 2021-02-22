package graphics;

import java.awt.Color;
import java.awt.Graphics;

import Entitys.Entity;
import Main.Game;


public class playerInterface {

	public void render(Graphics g) {
		
		
		g.setColor(new Color(28,28,28));
		g.fillRect(22, 20, 50, 7);
		g.setColor(new Color(220,20,60));
		g.fillRect(22, 20, (int)((Game.player.life/Game.player.maxLife)*50), 7);
		g.setColor(Color.white);
		g.fillRect(22, 21, (int)((Game.player.life/Game.player.maxLife)*50), 1);
	
		g.setColor(new Color(28,28,28));
		g.fillRect(22, 33, 50, 7);
		g.setColor(new Color(108, 150, 228));
		g.fillRect(22, 33, (int)((Game.player.mana/Game.player.maxMana)*50), 7);
		g.setColor(Color.white);
		g.fillRect(22, 34, (int)((Game.player.mana/Game.player.maxMana)*50), 1);
		
		g.drawImage(Entity.hud,17,17, null);
		
		
	}
	
}
