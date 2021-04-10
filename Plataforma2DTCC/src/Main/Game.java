package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Entitys.AtaqueLeft;
import Entitys.AtaqueRight;
import Entitys.Background;
import Entitys.Carrot;
import Entitys.Entity;
import Entitys.Goblin;
import Entitys.Heart;
import Entitys.NPC1;
import Entitys.Player;
import Entitys.Sky;
import World.Level;
import graphics.Spritsheet;
import graphics.playerInterface;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	public JFrame jframe;
	public static int WIDTH = 260;
	public static int HEIGHT = 140;
	public static int SCALE = 4;
	
	private Thread thread;
	private boolean isRunning = true;
	
	private BufferedImage background;
	public static List<Entity> entidades;
	public static Spritsheet spritsheet;
	public static Player player;
	public static Level world;
	
	//PARALAX
	public static List<Sky> sky;
	public static Spritsheet skySprite;
	
	public static List<Background> backgroundF;
	public static Spritsheet backgroundSprite;
	
	public static List<Carrot> carrot;
	public static List<Heart> heart;
	public static List<Goblin> goblin; 
	
	public playerInterface lifeBar;
	public int level = 1;

	public static int maxLevel = 2;
	
	public static String gameState = "Menu";
	public boolean messageGameOver = true;
	public int messageGameOverFrames = 0;
	public boolean restartGame = false;
	
	public Menu menu;
	public boolean saveGame = false;
	
	public static Spritsheet hud;
	public static List<NPC1> npc1;
	public static NPC1 ncpteste;
	
	public static List<AtaqueLeft> ataques;
	public static List<AtaqueRight> ataquesr;
	
	public Game() {
		Sounds.music.loop();
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		lifeBar = new playerInterface();
		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entity>();
		spritsheet = new Spritsheet("/spriteSheet.png");
		//PARALAX
		sky = new ArrayList<Sky>();
		skySprite = new Spritsheet("/ceusprite.png");
		backgroundF = new ArrayList<Background>();
		backgroundSprite = new Spritsheet("/background.png");
		
		hud = new Spritsheet("/hud.png");
		carrot = new ArrayList<Carrot>();
		heart = new ArrayList<Heart>();
		goblin = new ArrayList<Goblin>();
		npc1 = new ArrayList<NPC1>();
		ataques = new ArrayList<AtaqueLeft>();
		ataquesr = new ArrayList<AtaqueRight>();
		player = new Player(0,0,16,16,spritsheet.getSprite(0,0,16,16));
		entidades.add(player);
		world = new Level("/level1.png");
		menu = new Menu();
	}
	public void initFrame() {
		jframe = new JFrame("TCC");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
		if(gameState == "Normal") {
			restartGame = false;
			
			if(saveGame) {
				saveGame = false;
				String[]opt1 = {"level","life"};
				int[]opt2 = {this.level, (int)Player.life};
				Menu.saveGame(opt1,opt2,10);
				System.out.println("Jogo salvo");
				
			}
			
			if(goblin.size() == 0) {
				level++;
				if(level > maxLevel) {
					level = 1;
				}
				String Level = "level"+level+".png";
				World.Level.newLevel(Level);
			}
			
			for(int i =0; i < goblin.size(); i++) {
				Goblin go = goblin.get(i);
				go.tick();
			}
			for(int i =0; i < heart.size(); i++) {
				Heart h = heart.get(i);
				h.tick();
			}
			for(int i =0; i < carrot.size(); i++) {
				Carrot c = carrot.get(i);
				c.tick();
			}
			for(int i =0; i < entidades.size(); i++) {
				Entity entidade = entidades.get(i);
				entidade.tick();
			}
			for(int i =0; i < npc1.size(); i++) {
				NPC1 npc = npc1.get(i);
				npc.tick();
			}
			for(int i =0; i < sky.size(); i++) {
				Sky skyE = sky.get(i);
				skyE.tick();
			}
			
			for(int i =0; i < backgroundF.size(); i++) {
				Background backgroundE = backgroundF.get(i);
				backgroundE.tick();
			}
			
			
			for(int i =0; i < ataques.size(); i++) {
				AtaqueLeft a = ataques.get(i);
				a.tick();
			}
			
			for(int i =0; i < ataquesr.size(); i++) {
				AtaqueRight a = ataquesr.get(i);
				a.tick();
			}
		
			}else if(gameState == "gameOver"){
				messageGameOverFrames++;
				if(messageGameOverFrames == 25) {
					messageGameOverFrames = 0;
					if(messageGameOver) 
						messageGameOver = false;
					else 
						messageGameOver = true;
				}
				if(restartGame) {
					Game.player.life = 100;
					restartGame = false;
					gameState = "Normal";		
					this.level = 1;
					String newLevel = "level"+level+".png";
					World.Level.newLevel(newLevel);
				}
			}else if(gameState == "Menu") {
				menu.tick();
			}
	}
	
	public void render() {
		BufferStrategy buffer = this.getBufferStrategy();
		if(buffer == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = background.getGraphics();
		g.setColor(new Color(20, 20, 20));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		world.render(g);
		
		
		for(int i =0; i < sky.size(); i++) {
			Sky skyE = sky.get(i);
			skyE.render(g);
		}
		
		for(int i =0; i < backgroundF.size(); i++) {
			Background backgroundE = backgroundF.get(i);
			backgroundE.render(g);
		}
		
		for(int i =0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.render(g);
		}
		for(int i =0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			if(entidade instanceof Player) {				
				entidade.render(g);
			}
		}
		for(int i =0; i < npc1.size(); i++) {
			NPC1 npc = npc1.get(i);
			npc.render(g);
		}
				
		for(int i =0; i < goblin.size(); i++) {
			Goblin go = goblin.get(i);
			go.render(g);
		}
		for(int i =0; i < heart.size(); i++) {
			Heart h = heart.get(i);
			h.render(g);;
		}
		for(int i =0; i < carrot.size(); i++) {
			Carrot c = carrot.get(i);
			c.render(g);
		}
		
		for(int i =0; i < ataques.size(); i++) {
			AtaqueLeft a = ataques.get(i);
			a.render(g);
		}
		
		for(int i =0; i < ataquesr.size(); i++) {
			AtaqueRight a = ataquesr.get(i);
			a.render(g);
		}
		
		lifeBar.render(g);
		g = buffer.getDrawGraphics();
		g.drawImage(background, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		
		if(gameState == "gameOver"){
			Graphics2D g2 = (Graphics2D) g;
			 g2.setColor(new Color(0,0,0,100));
			 g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			 g.setFont(new Font("arial",Font.BOLD,28));
			 g.setColor(Color.WHITE);
			 g.drawString("GAME OVER", WIDTH*SCALE/2 - 100, HEIGHT*SCALE/2 - 30);
			 if(messageGameOver)
		     g.drawString("PRESS ENTER TO RESTART", WIDTH*SCALE/2 - 200, HEIGHT*SCALE/2 + 40);
		}else if(gameState == "Menu") {
			 menu.render(g);
		 }
		
		buffer.show();
	}
	
	@Override
	public void run() {
		long LastTime = System.nanoTime();
		double amountOfTicks = 60.0f;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {	
			long now = System.nanoTime();
			delta += ( now - LastTime ) / ns;
			LastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			    }
				if(System.currentTimeMillis() - timer >= 1000){
					//System.out.println("FPS: "+ frames);
					frames = 0;
					timer+=1000;
				}
			}
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		//MOVIMENTAÇÃO
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		//JUMP
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		    player.jump = false;
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(gameState == "gameOver") {				
				restartGame = true;
			}
			if(gameState == "Menu") {				
				menu.enter = true;
			}
			if(player.chat == true) {
				for(int i = 0; i < npc1.size();i++) {	
					NPC1 n = npc1.get(i);
					n.enter = true;
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	    	gameState = "Menu";
	    	menu.pause = true;
	    }
		
		//SALVAR
		if(e.getKeyCode() == KeyEvent.VK_T) {
	    	saveGame = true;
	    }
		
		//CONVERSA NPC
		if(e.getKeyCode() == KeyEvent.VK_C) {
			for(int i = 0; i < npc1.size();i++) {	
				NPC1 n = npc1.get(i);
				n.chat = true;
			}
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		//ATACK
		if(e.getKeyCode() == KeyEvent.VK_F) {
			Player.atack = true;
	    }	
		
		//MOVIMENTAÇÃO
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = true;
	    }
		
		//MENU
		if(e.getKeyCode() == KeyEvent.VK_S) {
			if(gameState == "Menu") {
				menu.down = true;
			}
		}else if(e.getKeyCode() == KeyEvent.VK_W) {
			if(gameState == "Menu") {
				menu.up = true;
			}
		}	
		
	}

}
