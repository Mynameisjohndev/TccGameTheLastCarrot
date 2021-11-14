package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Entitys.Entity;
import Entitys.NPC1;
import Main.Game;


public class playerInterface {

	public void render(Graphics g) {
		
		
		g.setColor(new Color(28,28,28));
		g.fillRect(10, 5, 50, 7);
		g.setColor(new Color(220,20,60));
		g.fillRect(10, 5, (int)((Game.player.life/Game.player.maxLife)*50), 7);
		g.setColor(Color.white);
		g.fillRect(10, 6, (int)((Game.player.life/Game.player.maxLife)*50), 1);
	
//		g.setColor(new Color(28,28,28));
//		g.fillRect(10, 18, 50, 7);
//		g.setColor(new Color(108, 150, 228));
//		g.fillRect(10, 18, (int)((Game.player.mana/Game.player.maxMana)*50), 7);
//		g.setColor(Color.white);
//		g.fillRect(10, 19, (int)((Game.player.mana/Game.player.maxMana)*50), 1);
//		
		g.drawImage(Entity.hud,5,2, null);
		g.setColor(Color.white);	
		g.setFont(new Font("arial",Font.BOLD,8));
		//g.drawString("" + Game.player.itemLife, 123,32);
		g.drawString("" + Game.player.itemCarrot, 15, 44);
		g.setFont(new Font("arial",Font.BOLD,9));
		if(NPC1.missaoAceita == true){
			g.drawString("Colete " + NPC1.totalColetarString + " cenouras", 8, 56);
		}
		
	
	}
	
}
