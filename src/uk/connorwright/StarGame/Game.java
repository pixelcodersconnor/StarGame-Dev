// Game made by Connor Wright
package uk.connorwright.StarGame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

import uk.connorwright.StarGame.audio.Sound;
import uk.connorwright.StarGame.entities.EntityA;
import uk.connorwright.StarGame.entities.EntityB;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	// define the variables
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;
	public final String TITLE = "StarGame - BETA v2";
	public boolean running = false;
	private static Thread thread;
	private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private Player p;
	private Controller c;
	private Textures tex;
	private BufferedImage background = null;
	private BufferedImage launcherbg = null;
	private boolean is_shooting = false;
	BufferedImageLoader loader = new BufferedImageLoader();
	private int enemy_count = 1;
	private int enemy_kill = 0;
	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;
	private Menu menu;
	public static int HEALTH = 100 * 2;
	public static final String FONT_LOCATION = "/font/";
	public static boolean lost = false;
	private Font font2;
	private int score;

	public static enum STATE {
		MENU, GAME, LAUNCHER, BUGS,
	};

	public static STATE State = STATE.GAME;

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_kill() {
		return enemy_kill;
	}

	public void setEnemy_kill(int enemy_kill) {
		this.enemy_kill = enemy_kill;
	}

	// temp draw to screen
	private BufferedImage player;

	// begin init method
	public void init() {

		requestFocus();
		Game.loadFonts();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		tex = new Textures(this);
		c = new Controller(tex, this);
		p = new Player(200, 200, tex, this, c);
		menu = new Menu();

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		ea = c.getEntityA();
		eb = c.getEntityB();

		c.addEnemy(enemy_count);

	}

	public static void loadFonts() {
		Fonts.addFont(new Fonts("/SpaceBang.ttf"));
	}

	// begin start loop
	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	// begin stop loop
	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.exit(1);

	}

	// begin run method
	@Override
	public void run() {
		init();
		while (running) {
			long lastTime = System.nanoTime();
			final double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			int updates = 0;
			int frames = 0;
			long timer = System.currentTimeMillis();
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				if (delta > -1) {
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;

				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println(updates + " Ticks, FPS " + frames);
					updates = 0;
					frames = 0;
				}

			}

		}
		stop();

	}

	// begin tick method
	private void tick() {
		if (State == STATE.GAME) {
			p.tick();
			c.tick();

		}
		if (enemy_kill >= enemy_count) {
			enemy_count += 2;
			score = enemy_kill;
			enemy_kill = 0;
			c.addEnemy(enemy_count);
		}

		if (HEALTH <= 0) {
			Sound.loseSound.play();
			State = STATE.MENU;
			enemy_count = 0;
			enemy_kill = 0;
			Game.HEALTH = 200;
		}

	}

	// begin render method
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {

			createBufferStrategy(2);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		// ///////////////<RENDERING>/////////////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		g.drawImage(player, 100, 100, this);

		g.drawImage(background, 0, 0, null);

		if (State == STATE.GAME) {
			p.render(g);
			c.render(g);

			g.setColor(Color.white);
			g.drawString("Health: " + HEALTH, 25, 40);

			g.setColor(Color.white);
			g.drawString("Score: " + score, 565, 40);
		} else if (State == STATE.MENU) {
			menu.render(g);
		}

		if (State == STATE.MENU) {
			drawSplash(g);
		}
		g.dispose();
		bs.show();
	}

	private void drawSplash(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font2);
		g.drawString(itemDisplayed, 150, 120);

	}

	// ///////////////</RENDERING>/////////////////////

	private String SPLASHTEXTS[] = { "Buggy", "New Game", "Roll up roll up",
			"Made from scratch", "Awesome", "is epic", "Hello", "BETA", "Woof",
			"Meow", "Oink", "SHAUN", "Easter eggs galore", "Do a barrell roll",
			"All your base are belong to us", "FTW", "SNAKE", "null",
			"undefined", "Hello world", };

	private String itemDisplayed = chooseItem();

	private String chooseItem() {
		Random r = new SecureRandom();
		int i = r.nextInt(SPLASHTEXTS.length);
		return SPLASHTEXTS[i];
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
				Sound.laserFired.play();
			} else if (key == KeyEvent.VK_0) {
				Game.HEALTH = 0;
			} else if (key == KeyEvent.VK_ESCAPE) {
				// Bring up the menu GUI
				uk.connorwright.StarGame.menu.Menu.main(null);
			} else if (key == KeyEvent.VK_I) {
				HEALTH = 1000;
			} else if (key == KeyEvent.VK_E) {
				Random random1 = new Random();
				int result = random1.nextInt(2 + 1);

				if (result != 1 && result != 2) {
					result = 1;
				}
				if (result == 1) {
					Sound.shaunEgg1.play();
				}

				if (result == 2) {
					Sound.shaunEgg2.play();

				}
			} else if (key == KeyEvent.VK_T) {
				System.out.println("Command");
				uk.connorwright.StarGame.window.Window.makeFrame();
			}

		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_D) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_A) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_S) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_W) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;

		}
	}

	// begin main method
	public static void main(String[] args) {
		startGame();

	}

	// initialise the splash screen
	public static void Splash() {
		SplashScreen splash = new SplashScreen(3000);
		splash.showSplash();
		launcher();
	}

	// launcher
	public static void launcher() {
		startGame();
	}

	// start game
	public static void startGame() {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	// begin bufferedimage method
	public BufferedImage getSpriteSheet() {
		return spriteSheet;

	}
}
