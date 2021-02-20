package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Entitys.Carrot;
import Entitys.Entity;
import Entitys.Goblin;
import Entitys.Heart;
import Entitys.Player;
import Entitys.Sky;
import World.Level;
import graphics.Spritsheet;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;

	public JFrame jframe;
	public static int WIDTH = 260;
	public static int HEIGHT = 140;
	private static int SCALE = 4;
	
	private Thread thread;
	private boolean isRunning = true;
	
	private BufferedImage background;
	public static List<Entity> entidades;
	public static Spritsheet spritsheet;
	public static Player player;
	public static Level world;
	public static List<Sky> sky;
	public static Spritsheet skySprite;
	
	public static List<Carrot> carrot;
	public static List<Heart> heart;
	public static List<Goblin> goblin; 
	
	public playerInterface lifeBar;
	
	public int level = 1, maxLevel = 2;
	
	public Game() {
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		lifeBar = new playerInterface();
		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entidades = new ArrayList<Entity>();
		spritsheet = new Spritsheet("/spriteSheet.png");
		sky = new ArrayList<Sky>();
		skySprite = new Spritsheet("/ceusprite.png");
		
		carrot = new ArrayList<Carrot>();
		heart = new ArrayList<Heart>();
		goblin = new ArrayList<Goblin>();
		
		player = new Player(0,0,16,16,spritsheet.getSprite(0,0,16,16));
		entidades.add(player);
		world = new Level("/level1.png");
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
		for(int i =0; i < sky.size(); i++) {
			Sky skyE = sky.get(i);
			skyE.tick();
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

		for(int i =0; i < entidades.size(); i++) {
			Entity entidade = entidades.get(i);
			entidade.render(g);
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
		
		lifeBar.render(g);
		
		g = buffer.getDrawGraphics();
		g.drawImage(background, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
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
	
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		    player.jump = false;
		    player.quantidadeDePulos+=1;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
		if(e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump = true;
	    }
		
	}

}
