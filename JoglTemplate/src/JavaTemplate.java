import java.awt.MouseInfo;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;

public class JavaTemplate {
	// Set this to true to make the game loop exit.
	private static boolean shouldExit;

	// The previous frame's keyboard and mouse state.
	private static boolean kbPrevState[] = new boolean[256];
	private static boolean mbPrevState[] = new boolean[14];

	// The current frame's keyboard and mouse state.
	private static boolean kbState[] = new boolean[256];
	private static boolean mbState[] = new boolean[14];

	// Position of the sprite.
	private static int[] spritePos = new int[] { 10, 50 };

	// Font Object
	private static Font font;

	// PathFinder Object
	private static PathFinder pathFinder;

	private static boolean gamePaused = true;
	private static boolean winScreen = false;
	
	// Texture for the sprite.
	private static int spriteDirt;
	private static int spriteMagma;
	private static int spriteTex;
	private static int enemySpriteTex;
	private static int playerHead;
	private static int spriteShadow;
	private static int spriteTexRight1;
	private static int spriteTexRight2;
	private static int spriteTexRight3;
	private static int spriteTexLeft1;
	private static int spriteTexLeft2;
	private static int spriteTexLeft3;
	private static int enemySpriteTexRight1;
	private static int enemySpriteTexRight2;
	private static int enemySpriteTexRight3;
	private static int enemySpriteTexLeft1;
	private static int enemySpriteTexLeft2;
	private static int enemySpriteTexLeft3;
	private static int spriteBullet;
	private static int healthBar1;
	private static int healthBar2;
	private static int healthBar3;
	private static int healthBar4;
	private static int healthBar5;
	private static int healthBar6;
	private static int healthBar7;
	private static int healthpack;
	private static int trapdoor;
	private static int menuBackground;

	// Font Textures
	private static int[] alphabet;
	private static int A;
	private static int B;
	private static int C;
	private static int D;
	private static int E;
	private static int F;
	private static int G;
	private static int H;
	private static int I;
	private static int J;
	private static int K;
	private static int L;
	private static int M;
	private static int N;
	private static int O;
	private static int P;
	private static int Q;
	private static int R;
	private static int S;
	private static int T;
	private static int U;
	private static int V;
	private static int W;
	private static int X;
	private static int Y;
	private static int Z;
	private static int num0;
	private static int num1;
	private static int num2;
	private static int num3;
	private static int num4;
	private static int num5;
	private static int num6;
	private static int num7;
	private static int num8;
	private static int num9;
	private static int colon;
	private static int exclamation;
	private static int question;

	// Mouse coordinates
	private static Point mouse;

	// Create sprite object/arrays
	public static Player player;
	public static Sprite shadow;
	public static Sprite trapDoor;
	public static ArrayList<Sprite> healthPacks;
	public static ArrayList<Sprite> healthPackShadows;

	// Create collision object
	public static Collision collision;

	// Create arraylist to load sprites
	public static ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	// Texture Arrays
	private static int[] rightArray;
	private static int[] leftArray;
	private static int[] enemyRightArray;
	private static int[] enemyLeftArray;

	// Size of the sprite.
	private static int[] spriteSize = new int[2];
	private static int[] textSize = new int[2];
	private static int[] smallTextSize = new int[2];
	private static int[] menuSize = new int[2];
	
	// Clip Player
	private static ClipPlayer clipPlayer = new ClipPlayer();

	public static void main(String[] args) {
		GLProfile gl2Profile;

		try {
			// Make sure we have a recent version of OpenGL
			gl2Profile = GLProfile.get(GLProfile.GL2);
		} catch (GLException ex) {
			System.out.println("OpenGL max supported version is too low.");
			System.exit(1);
			return;
		}

		// Create the window and OpenGL context.
		GLWindow window = GLWindow.create(new GLCapabilities(gl2Profile));
		window.setSize(800, 600);
		window.setTitle("Java Template");
		window.setVisible(true);
		window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
		window.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				if (keyEvent.isAutoRepeat()) {
					return;
				}
				kbState[keyEvent.getKeyCode()] = true;
			}

			@Override
			public void keyReleased(KeyEvent keyEvent) {
				if (keyEvent.isAutoRepeat()) {
					return;
				}
				kbState[keyEvent.getKeyCode()] = false;
			}
		});

		// Get mouse coordinates
		mouse = getMouseLocation(window);

		// Mouse listener
		window.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub
				if (mouseEvent.isAutoRepeat()) {
					return;
				}
				mbState[mouseEvent.getButton()] = true;
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub
				if (mouseEvent.isAutoRepeat()) {
					return;
				}
				mbState[mouseEvent.getButton()] = false;
			}

			@Override
			public void mouseClicked(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseWheelMoved(MouseEvent mouseEvent) {
				// TODO Auto-generated method stub

			}
		});

		// Setup OpenGL state.
		window.getContext().makeCurrent();
		GL2 gl = window.getGL().getGL2();
		gl.glViewport(0, 0, 800, 600);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glOrtho(0, 800, 600, 0, 0, 100);
		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		// Game initialization goes here.
		spriteDirt = glTexImageTGAFile(gl, "backgrounds\\minecraft_dirt.tga", spriteSize);
		spriteMagma = glTexImageTGAFile(gl, "backgrounds\\minecraft_magma.tga", spriteSize);
		spriteTex = glTexImageTGAFile(gl, "sprites\\MegamanSprite.tga", spriteSize);
		enemySpriteTex = glTexImageTGAFile(gl, "sprites\\EnemyMegamanSprite.tga", spriteSize);
		spriteShadow = glTexImageTGAFile(gl, "sprites\\SpriteShadow.tga", spriteSize);
		spriteTexRight1 = glTexImageTGAFile(gl, "sprites\\animation\\right\\MegamanRightFrame1.tga", spriteSize);
		spriteTexRight2 = glTexImageTGAFile(gl, "sprites\\animation\\right\\MegamanRightFrame2.tga", spriteSize);
		spriteTexRight3 = glTexImageTGAFile(gl, "sprites\\animation\\right\\MegamanRightFrame3.tga", spriteSize);
		spriteTexLeft1 = glTexImageTGAFile(gl, "sprites\\animation\\left\\MegamanLeftFrame1.tga", spriteSize);
		spriteTexLeft2 = glTexImageTGAFile(gl, "sprites\\animation\\left\\MegamanLeftFrame2.tga", spriteSize);
		spriteTexLeft3 = glTexImageTGAFile(gl, "sprites\\animation\\left\\MegamanLeftFrame3.tga", spriteSize);
		enemySpriteTexRight1 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\right\\EnemyMegamanRightFrame1.tga",
				spriteSize);
		enemySpriteTexRight2 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\right\\EnemyMegamanRightFrame2.tga",
				spriteSize);
		enemySpriteTexRight3 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\right\\EnemyMegamanRightFrame3.tga",
				spriteSize);
		enemySpriteTexLeft1 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\left\\EnemyMegamanLeftFrame1.tga",
				spriteSize);
		enemySpriteTexLeft2 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\left\\EnemyMegamanLeftFrame2.tga",
				spriteSize);
		enemySpriteTexLeft3 = glTexImageTGAFile(gl, "sprites\\enemyanimation\\left\\EnemyMegamanLeftFrame3.tga",
				spriteSize);
		spriteBullet = glTexImageTGAFile(gl, "projectile\\Bullet.tga", spriteSize);
		healthBar1 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar1.tga", spriteSize);
		healthBar2 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar2.tga", spriteSize);
		healthBar3 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar3.tga", spriteSize);
		healthBar4 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar4.tga", spriteSize);
		healthBar5 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar5.tga", spriteSize);
		healthBar6 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar6.tga", spriteSize);
		healthBar7 = glTexImageTGAFile(gl, "sprites\\healthBar\\HealthBar6.tga", spriteSize);
		healthpack = glTexImageTGAFile(gl, "sprites\\HealthPack\\healthpack.tga", spriteSize);
		trapdoor = glTexImageTGAFile(gl, "sprites\\doors\\trapdoor.tga", spriteSize);
		menuBackground = glTexImageTGAFile(gl, "menu\\backgroundgray.tga", spriteSize);
		playerHead = glTexImageTGAFile(gl, "sprites\\playerhead.tga", spriteSize);

		// initialize font
		font = new Font();
		textSize[0] = spriteSize[0] / 2;
		textSize[1] = spriteSize[1] / 2;
		smallTextSize[0] = spriteSize[0] / 4;
		smallTextSize[1] = spriteSize[1] / 4;

		// initialize menu size
		menuSize[0] = spriteSize[0] * 9;
		menuSize[1] = spriteSize[1] * 2;

		// Load Font Textures
		A = glTexImageTGAFile(gl, "sprites\\font\\A.tga", spriteSize);
		B = glTexImageTGAFile(gl, "sprites\\font\\B.tga", spriteSize);
		C = glTexImageTGAFile(gl, "sprites\\font\\C.tga", spriteSize);
		D = glTexImageTGAFile(gl, "sprites\\font\\D.tga", spriteSize);
		E = glTexImageTGAFile(gl, "sprites\\font\\E.tga", spriteSize);
		F = glTexImageTGAFile(gl, "sprites\\font\\F.tga", spriteSize);
		G = glTexImageTGAFile(gl, "sprites\\font\\G.tga", spriteSize);
		H = glTexImageTGAFile(gl, "sprites\\font\\H.tga", spriteSize);
		I = glTexImageTGAFile(gl, "sprites\\font\\I.tga", spriteSize);
		J = glTexImageTGAFile(gl, "sprites\\font\\J.tga", spriteSize);
		K = glTexImageTGAFile(gl, "sprites\\font\\K.tga", spriteSize);
		L = glTexImageTGAFile(gl, "sprites\\font\\L.tga", spriteSize);
		M = glTexImageTGAFile(gl, "sprites\\font\\M.tga", spriteSize);
		N = glTexImageTGAFile(gl, "sprites\\font\\N.tga", spriteSize);
		O = glTexImageTGAFile(gl, "sprites\\font\\O.tga", spriteSize);
		P = glTexImageTGAFile(gl, "sprites\\font\\P.tga", spriteSize);
		Q = glTexImageTGAFile(gl, "sprites\\font\\Q.tga", spriteSize);
		R = glTexImageTGAFile(gl, "sprites\\font\\R.tga", spriteSize);
		S = glTexImageTGAFile(gl, "sprites\\font\\S.tga", spriteSize);
		T = glTexImageTGAFile(gl, "sprites\\font\\T.tga", spriteSize);
		U = glTexImageTGAFile(gl, "sprites\\font\\U.tga", spriteSize);
		V = glTexImageTGAFile(gl, "sprites\\font\\V.tga", spriteSize);
		W = glTexImageTGAFile(gl, "sprites\\font\\W.tga", spriteSize);
		X = glTexImageTGAFile(gl, "sprites\\font\\X.tga", spriteSize);
		Y = glTexImageTGAFile(gl, "sprites\\font\\Y.tga", spriteSize);
		Z = glTexImageTGAFile(gl, "sprites\\font\\Z.tga", spriteSize);

		num0 = glTexImageTGAFile(gl, "sprites\\font\\0.tga", spriteSize);
		num1 = glTexImageTGAFile(gl, "sprites\\font\\1.tga", spriteSize);
		num2 = glTexImageTGAFile(gl, "sprites\\font\\2.tga", spriteSize);
		num3 = glTexImageTGAFile(gl, "sprites\\font\\3.tga", spriteSize);
		num4 = glTexImageTGAFile(gl, "sprites\\font\\4.tga", spriteSize);
		num5 = glTexImageTGAFile(gl, "sprites\\font\\5.tga", spriteSize);
		num6 = glTexImageTGAFile(gl, "sprites\\font\\6.tga", spriteSize);
		num7 = glTexImageTGAFile(gl, "sprites\\font\\7.tga", spriteSize);
		num8 = glTexImageTGAFile(gl, "sprites\\font\\8.tga", spriteSize);
		num9 = glTexImageTGAFile(gl, "sprites\\font\\9.tga", spriteSize);
		colon = glTexImageTGAFile(gl, "sprites\\font\\colon.tga", spriteSize);
		exclamation = glTexImageTGAFile(gl, "sprites\\font\\exclamation.tga", spriteSize);
		question = glTexImageTGAFile(gl, "sprites\\font\\question.tga", spriteSize);
		alphabet = new int[] { A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9, colon, exclamation, question };
		
		playerHead = glTexImageTGAFile(gl, "sprites\\playerhead.tga", spriteSize);

		// Fill texture arrays
		rightArray = new int[] { spriteTexRight1, spriteTexRight2, spriteTexRight3, spriteTexRight3 };
		leftArray = new int[] { spriteTexLeft1, spriteTexLeft2, spriteTexLeft3, spriteTexLeft3 };
		enemyRightArray = new int[] { enemySpriteTexRight1, enemySpriteTexRight2, enemySpriteTexRight3,
				enemySpriteTexRight3 };
		enemyLeftArray = new int[] { enemySpriteTexLeft1, enemySpriteTexLeft2, enemySpriteTexLeft3,
				enemySpriteTexLeft3 };

		// Setting up variables
		Camera camera = new Camera(0, 0, 800, 600);
		int startTileX = 0;
		int startTileY = 0;
		int endTileX = 0;
		int endTileY = 0;
		int counter = 0;
		int direction = 4;
		int bDirection = 0;
		double elapsedTime = 0;
		int currentImage = 0;
		int spriteWidth = spriteSize[0];
		int spriteHeight = spriteSize[1];
		int x = (camera.getW() / 2) - spriteWidth / 2;
		int y = (camera.getH() / 2) - spriteHeight / 2;
		int z = 0;
		int gameWidth = 100 * spriteWidth;
		int gameHeight = 100 * spriteHeight;
		boolean isGrounded = true;
		double zVelocity = 0;
		int jumpVelocity = 12;
		double zGravity = -1;
		int shadowOffset = 0;
		int bVelocity = 15;
		int playerXIndex = 0;
		int playerYIndex = 0;
		boolean topCollide = false;
		boolean rightCollide = false;
		boolean leftCollide = false;
		boolean bottomCollide = false;
		boolean dead = false;
		boolean pause = false;
		boolean gameOver = false;
		boolean atStart = true;
		int respawnTime = -1;
		int enemyChooseTimer = -1;
		int playerHealth = healthBar1;
		int playerLives = 3;
		boolean levelOver = false;
		int levelNumber = 0;
		ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
		ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<EnemyProjectile>();
		player = new Player(x, y, spriteSize, spriteTex, playerHealth);
		shadow = new Sprite(x, y, spriteSize, spriteShadow);
		trapDoor = new Sprite(0, 0, spriteSize, trapdoor);
		healthPacks = new ArrayList<Sprite>();
		healthPackShadows = new ArrayList<Sprite>();
		collision = new Collision();
		Sprite menuTest = new Sprite(100, 100, menuSize, menuBackground);
		int ex = 0;
		int ey = 0;
		int newX = 0;
		int newY = 0;

		// Initializing background array
		Background bg = new Background();
		int[][] background = bg.levels.get(levelNumber);

		// Setting background array of objects
		BackgroundTile[][] backgroundTiles = new BackgroundTile[100][100];
		// BackgroundNode[][] backgroundNodes = new BackgroundNode[100][100];
		// BackgroundGrid backgroundGrid = new BackgroundGrid(40, 40);
		bg.updateBackgroundTiles(backgroundTiles, levelNumber, spriteWidth, spriteHeight);

		// Adding 5 enemies and shadows
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < 5; i++) {
			enemies.add(new Enemy((int) (Math.random() * (10 * spriteWidth) + (45 * spriteWidth)),
					(int) (Math.random() * (10 * spriteHeight) + (45 * spriteHeight)), spriteSize, enemySpriteTex,
					enemyRightArray, enemyLeftArray, 0, healthBar1, 0));
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
		}
		ArrayList<Sprite> enemyShadows = new ArrayList<Sprite>();
		for (int i = 0; i < enemies.size(); i++) {
			enemyShadows.add(new Sprite(enemies.get(i).getX(), enemies.get(i).getY(), spriteSize, spriteShadow));
		}

		// Find original spot for the healthpacks
		for (int i = 0; i < 5; i++) {
			healthPacks.add(new Sprite(0, 0, spriteSize, healthpack));
			healthPacks.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
			healthPackShadows
					.add(new Sprite(healthPacks.get(i).getX(), healthPacks.get(i).getY(), spriteSize, spriteShadow));
		}

		// Load sound clips
		Clip shoot = null;
		Clip jump = null;
		Clip heal = null;
		Clip music = null;
		try {
			shoot = clipPlayer.loadClip("sounds\\pew.wav");
			jump = clipPlayer.loadClip("sounds\\jump.wav");
			heal = clipPlayer.loadClip("sounds\\healed.wav");
			music = clipPlayer.loadClip("sounds\\music.wav");
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
			e.printStackTrace();
		}
		playMusic("sounds\\music.wav");

		// The game loop
		long lastFrameNS;
		long curPhysicsFrameMS;
		long physicsDeltaMS = 10;
		long curFrameNS = System.nanoTime();
		long curFrameMS = curFrameNS / 1000000;
		long lastPhysicsFrameMS = curFrameMS;
		double shootTimer = 0;
		double enemyShootTimer = 0;
		double hitTimer = 0;
		long deltaTimeMS;
		
		while (!shouldExit) {
			System.arraycopy(kbState, 0, kbPrevState, 0, kbState.length);
			System.arraycopy(mbState, 0, mbPrevState, 0, mbState.length);
			lastFrameNS = curFrameNS;
			curFrameNS = System.nanoTime();
			deltaTimeMS = (curFrameNS - lastFrameNS) / 1000000;
			playerXIndex = (int) Math.floor((player.getX() + (spriteWidth / 2)) / spriteWidth);
			playerYIndex = (int) Math.floor((player.getY() + (spriteHeight / 2)) / spriteHeight);

			// Update mouse stuffs
			mouse = getMouseLocation(window);

			// Actually, this runs the entire OS message pump.
			window.display();

			if (!window.isVisible()) {
				shouldExit = true;
				break;
			}

			if(atStart){
				if (kbState[KeyEvent.VK_ESCAPE]) {
					shouldExit = true;
				}
				if(kbState[KeyEvent.VK_ENTER]){
					atStart = false;
					gamePaused = false;
					//dead = false;
				} else {
					atStart = true;
					//dead = true;
				}
				glDrawSprite(gl, menuBackground, 0, 0, 800, 600);
				drawText(gl, "THE MAZE OF DESTINY", 380 + camera.X, 290 + camera.Y, camera, spriteSize);
				drawText(gl, "PRESS ENTER", 380 + camera.X, 340 + camera.Y, camera, textSize);
				//System.out.println(gameOver);
			}
			if(!atStart){
				// Physics Update
				do {
					curPhysicsFrameMS = System.nanoTime() / 1000000;
					// 1. Physics movement
					// Jump
					z += zVelocity;
					zVelocity += zGravity;
					if (z <= 0) {
						z = 0;
						zVelocity = 0;
						isGrounded = true;
					}
	
					// 2. Physics collision detection/resolution
					for (int j = 0; j < enemies.size(); j++) {
						for (int i = 0; i < projectiles.size(); i++) {
							if (collision.AABBIntersect(enemies.get(j), projectiles.get(i))) {
								projectiles.remove(i);
								if (enemies.get(j).getHealth() == healthBar6) {
									newX = (int) (Math.random() * 100);
									newY = (int) (Math.random() * 100);
									if (enemies.get(j).lives != 0) {
										enemies.get(j).findSpace(backgroundTiles, newX, newY);
										enemies.get(j).update(enemies.get(j).getX(), enemies.get(j).getY(), healthBar1, enemies.get(j).lives - 1,
												enemies.get(j).toRemove);
									} else {
										enemies.get(j).setToRemove(true);
									}
								} else {
									enemies.get(j).update(enemies.get(j).getX(), enemies.get(j).getY(),
											enemies.get(j).getHealth() + 1, enemies.get(j).lives, enemies.get(j).toRemove);
								}
							}
							if (enemies.get(j).toRemove) {
								enemies.remove(j);
								enemyShadows.remove(j);
								break;
							}
						}
					}
	
					// Enemy projectiles collide with player
					for (int i = 0; i < enemyProjectiles.size(); i++) {
						if (collision.AABBIntersect(player, enemyProjectiles.get(i), z)) {
							enemyProjectiles.remove(enemyProjectiles.get(i));
							playerHealth += 1;
						}
					}
	
					// Test player collision
					if (isGrounded && playerYIndex != 0 && backgroundTiles[playerYIndex - 1][playerXIndex].isCollidable()
							&& collision.AABBIntersect(player, backgroundTiles[playerYIndex - 1][playerXIndex])) {
						topCollide = true;
					} else {
						topCollide = false;
					}
					if (isGrounded && playerXIndex != 99 && backgroundTiles[playerYIndex][playerXIndex + 1].isCollidable()
							&& collision.AABBIntersect(player, backgroundTiles[playerYIndex][playerXIndex + 1])) {
						rightCollide = true;
					} else {
						rightCollide = false;
					}
					if (isGrounded && playerXIndex != 0 && backgroundTiles[playerYIndex][playerXIndex - 1].isCollidable()
							&& collision.AABBIntersect(player, backgroundTiles[playerYIndex][playerXIndex - 1])) {
						leftCollide = true;
					} else {
						leftCollide = false;
					}
					if (isGrounded && playerYIndex != 99 && backgroundTiles[playerYIndex + 1][playerXIndex].isCollidable()
							&& collision.AABBIntersect(player, backgroundTiles[playerYIndex + 1][playerXIndex])) {
						bottomCollide = true;
					} else {
						bottomCollide = false;
					}
					if (isGrounded && player.getHealth() != healthBar1) {
						for (int i = 0; i < healthPacks.size(); i++) {
							if (collision.AABBIntersect(player, healthPacks.get(i))) {
								playerHealth -= 1;
								clipPlayer.playClip(heal, false);
								healthPacks.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100),
										(int) (Math.random() * 100));
							}
						}
					}
	
					// Test Enemy Collision
					for (int i = 0; i < enemies.size(); i++) {
						if (enemies.get(i).getYIndex() > 0 && enemies.get(i).getYIndex() < 99
								&& enemies.get(i).getXIndex() < 99 && enemies.get(i).getXIndex() > 0) {
							if (backgroundTiles[enemies.get(i).getYIndex() - 1][enemies.get(i).getXIndex()].isCollidable()
									&& collision.AABBIntersect(enemies.get(i),
											backgroundTiles[enemies.get(i).getYIndex() - 1][enemies.get(i).getXIndex()])) {
								enemies.get(i).setTopCollide(true);
							} else {
								enemies.get(i).setTopCollide(false);
							}
							if (backgroundTiles[enemies.get(i).getYIndex()][enemies.get(i).getXIndex() + 1].isCollidable()
									&& collision.AABBIntersect(enemies.get(i),
											backgroundTiles[enemies.get(i).getYIndex()][enemies.get(i).getXIndex() + 1])) {
								enemies.get(i).setRightCollide(true);
							} else {
								enemies.get(i).setRightCollide(false);
							}
							if (backgroundTiles[enemies.get(i).getYIndex()][enemies.get(i).getXIndex() - 1].isCollidable()
									&& collision.AABBIntersect(enemies.get(i),
											backgroundTiles[enemies.get(i).getYIndex()][enemies.get(i).getXIndex() - 1])) {
								enemies.get(i).setLeftCollide(true);
							} else {
								enemies.get(i).setLeftCollide(false);
							}
							if (backgroundTiles[enemies.get(i).getYIndex() + 1][enemies.get(i).getXIndex()].isCollidable()
									&& collision.AABBIntersect(enemies.get(i),
											backgroundTiles[enemies.get(i).getYIndex() + 1][enemies.get(i).getXIndex()])) {
								enemies.get(i).setBottomCollide(true);
							} else {
								enemies.get(i).setBottomCollide(false);
							}
						}
					}
	
					// Enemies collide with each other
					for (int i = 0; i < enemies.size(); i++) {
						for (int j = 0; j < enemies.size(); j++) {
							if (i != j) {
								Enemy a = enemies.get(i);
								Enemy b = enemies.get(j);
								if (distance(a.x, a.y, b.x, b.y) < 27) {
									collision.AABBIntersect(enemies.get(i), enemies.get(j), physicsDeltaMS);
								}
							}
						}
					}
	
					lastPhysicsFrameMS += physicsDeltaMS;
				} while (lastPhysicsFrameMS + physicsDeltaMS < curFrameMS);
	
				// Game logic goes here.
				if (kbState[KeyEvent.VK_ESCAPE]) {
					shouldExit = true;
					// pause = true;
				}
				if (!gamePaused) {
					if (kbState[KeyEvent.VK_W]) {
						direction = 0;
						bDirection = 0;
						if (!topCollide) {
							y -= (1 * deltaTimeMS) / 4;
						}
					}
					if (kbState[KeyEvent.VK_A]) {
						direction = 1;
						bDirection = 2;
						if (!leftCollide) {
							x -= (1 * deltaTimeMS) / 4;
						}
					}
					if (kbState[KeyEvent.VK_S]) {
						direction = 2;
						bDirection = 4;
						if (!bottomCollide) {
							y += (1 * deltaTimeMS) / 4;
						}
					}
					if (kbState[KeyEvent.VK_D]) {
						direction = 3;
						bDirection = 6;
						if (!rightCollide) {
							x += (1 * deltaTimeMS) / 4;
						}
					}
					if (kbState[KeyEvent.VK_SPACE]) {
						if (!kbPrevState[KeyEvent.VK_SPACE] && isGrounded) {
							zVelocity = jumpVelocity;
							isGrounded = false;
							clipPlayer.playClip(jump, false);
						}
					}
					if (mbState[MouseEvent.BUTTON1]) {
						if (!mbPrevState[MouseEvent.BUTTON1]) {
							Projectile bullet = new Projectile(player.getX(), player.getY() - z,
									Math.atan2(mouse.getY() + camera.getY() - player.getY(),
											mouse.getX() + camera.getX() - player.getX()),
									spriteSize, spriteBullet);
							projectiles.add(bullet);
							clipPlayer.playClip(shoot, false);
						} else if (shootTimer <= 0) {
							shootTimer = 150;
							Projectile bullet = new Projectile(player.getX(), player.getY() - z,
									Math.atan2(mouse.getY() + camera.getY() - player.getY(),
											mouse.getX() + camera.getX() - player.getX()),
									spriteSize, spriteBullet);
							projectiles.add(bullet);
							clipPlayer.playClip(shoot, false);
						}
					}
				}
	
				if (!gamePaused) {
					if (enemyChooseTimer <= 0) {
						enemyChooseTimer = 10000;
						for (int i = 0; i < enemies.size(); i++) {
							enemies.get(i).makeDecision();
						}
					}
					// Move enemies
					for (int i = 0; i < enemies.size(); i++) {
						enemies.get(i).moveCaller(player, deltaTimeMS);
					}
				}
	
				if (!gamePaused) {
					
					// FIRE EVERYTHING!!!!!!!!!!
					if (enemyShootTimer < 0) {
						enemyShootTimer = 1000.0;
						for (int i = 0; i < enemies.size(); i++) {
							if (enemies.get(i).decision.equals("attack")) {
								EnemyProjectile projectile = new EnemyProjectile(enemies.get(i).getX(),
										enemies.get(i).getY(), Math.atan2(player.getY() - enemies.get(i).getY(),
												player.getX() - enemies.get(i).getX()),
										spriteSize, spriteBullet);
								enemyProjectiles.add(projectile);
							}
						}
					}
	
					// Get hurt by lava
					if (backgroundTiles[player.getYIndex()][player.getXIndex()].isCollidable() && hitTimer <= 0
							&& isGrounded) {
						hitTimer = 900;
						playerHealth += 1;
						
					}
	
					// Keep sprite on screen
					if (x < 0) {
						x = 0;
					}
					if (x > (gameWidth - (spriteWidth))) {
						x = (gameWidth - (spriteWidth));
					}
					if (y < 0) {
						y = 0;
					}
					if (y > (gameHeight - (spriteHeight))) {
						y = (gameHeight - (spriteHeight));
					}
	
					if (!gamePaused) {
						// Camera follows sprite
						camera.setX(x + spriteWidth - camera.getW() / 2);
						camera.setY(y + spriteHeight - camera.getH() / 2);
						if (camera.getX() < 0) {
							camera.setX(0);
						}
						if (camera.getY() < 0) {
							camera.setY(0);
						}
						if (camera.getX() > (gameWidth - camera.getW())) {
							camera.setX((gameWidth - camera.getW()));
						}
						if (camera.getY() > (gameHeight - camera.getH())) {
							camera.setY((gameHeight - camera.getH()));
						}
					}
	
					// Animation Logic
					if (counter > 2) {
						counter = 0;
					} else if (elapsedTime > 150) {
						counter++;
						elapsedTime = 0;
					}
					if (direction == 0 || direction == 3) {
						player.setCurrentImage(rightArray[counter]);
						// currentImage = rightArray[counter];
						shadowOffset = 2;
					} else if (direction == 1 || direction == 2) {
						player.setCurrentImage(leftArray[counter]);
						// currentImage = leftArray[counter];
						shadowOffset = 2;
					} else {
						player.setCurrentImage(spriteTex);
						// currentImage = spriteTex;
						shadowOffset = 0;
					}
				}
	
				gl.glClearColor(0, 0, 0, 1);
				gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
	
				if (enemies.size() == 0 && !levelOver) {
					levelOver = true;
					trapDoor.findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
					trapDoor.update(trapDoor.getX(), trapDoor.getY(), trapdoor);
				}
				if (levelOver) {
					if (collision.AABBIntersect(player, trapDoor)) {
						if(levelNumber == bg.levels.size() - 1){
							winScreen = true;
							gamePaused = true;
						}
						levelNumber += 1;
						levelOver = false;
						if (levelNumber != bg.levels.size()) {
							for (int i = 0; i < projectiles.size(); i++) {
								projectiles.remove(i);
							}
							for (int i = 0; i < enemyProjectiles.size(); i++) {
								enemyProjectiles.remove(i);
							}
							for (int i = 0; i < healthPacks.size(); i++) {
								healthPacks.remove(i);
							}
							for (int i = 0; i < healthPackShadows.size(); i++) {
								healthPackShadows.remove(i);
							}
							background = bg.levels.get(levelNumber);
							backgroundTiles = bg.updateBackgroundTiles(backgroundTiles, levelNumber, spriteWidth,
									spriteHeight);
							for (int i = 0; i < 5; i++) {
								enemies.add(new Enemy((int) (Math.random() * 100),
										(int) (Math.random() * 100), spriteSize,
										enemySpriteTex, enemyRightArray, enemyLeftArray, 0, healthBar1, 0));
								enemies.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
							}
							for (int i = 0; i < enemies.size(); i++) {
								enemyShadows.add(
										new Sprite(enemies.get(i).getX(), enemies.get(i).getY(), spriteSize, spriteShadow));
							}
							for (int i = 0; i < 5; i++) {
								healthPacks.add(new Sprite(0, 0, spriteSize, healthpack));
								healthPacks.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
								healthPackShadows
										.add(new Sprite(healthPacks.get(i).getX(), healthPacks.get(i).getY(), spriteSize, spriteShadow));
							}
						}
					}
				}
	
				if (gameOver) {
					for (int i = 0; i < enemies.size(); i++) {
						enemies.remove(i);
					}
					for (int i = 0; i < enemyShadows.size(); i++) {
						enemyShadows.remove(i);
					}
					for (int i = 0; i < projectiles.size(); i++) {
						projectiles.remove(i);
					}
					for (int i = 0; i < enemyProjectiles.size(); i++) {
						enemyProjectiles.remove(i);
					}
					for (int i = 0; i < healthPacks.size(); i++) {
						healthPacks.remove(i);
					}
					for (int i = 0; i < healthPackShadows.size(); i++) {
						healthPackShadows.remove(i);
					}
				}
	
				if (!gameOver) {
					// Draw background
					startTileX = (int) Math.floor(camera.getX() / spriteSize[0]);
					startTileY = (int) Math.floor(camera.getY() / spriteSize[1]);
					endTileX = (int) Math.ceil(((camera.getX() + camera.getW()) / spriteSize[0]));
					endTileY = (int) Math.ceil(((camera.getY() + camera.getH()) / spriteSize[1]));
	
					for (int i = Math.max(0, startTileY); i <= Math.min(endTileY, 99); i++) {
						for (int j = Math.max(0, startTileX); j <= Math.min(endTileX, 99); j++) {
							drawSprite(gl, background[i][j], (j * spriteWidth), (i * spriteHeight), spriteWidth,
									spriteHeight, camera);
						}
					}
				}
	
				if (!gamePaused) { //dead && !gameOver
					// Update player
					player.update(x, y, player.getCurrentImage(), playerHealth);
	
					// Update shadow
					shadow.update(x, y, spriteShadow);
	
					// Update enemies
					for (int i = 0; i < enemies.size(); i++) {
						enemies.get(i).update(enemies.get(i).getX(), enemies.get(i).getY(), enemies.get(i).getHealth(),
								enemies.get(i).lives, enemies.get(i).toRemove);
					}
	
					// Update enemy shadows
					for (int i = 0; i < enemyShadows.size(); i++) {
						enemyShadows.get(i).update(enemies.get(i).getX(), enemies.get(i).getY(), spriteShadow);
					}
	
					// Remove/Update Projectiles
					for (int i = 0; i < projectiles.size(); i++) {
						if (collision.AABBIntersect(projectiles.get(i), camera)) {
							projectiles.get(i).update(projectiles.get(i).getX(), projectiles.get(i).getY(), spriteBullet);
						} else {
							projectiles.remove(projectiles.get(i));
						}
					}
	
					// Update Enemy Projectiles
					for (int i = 0; i < enemyProjectiles.size(); i++) {
						if (collision.AABBIntersect(enemyProjectiles.get(i), camera)) {
							enemyProjectiles.get(i).update(enemyProjectiles.get(i).getX(), enemyProjectiles.get(i).getY(),
									enemyProjectiles.get(i).getCurrentImage());
						} else {
							enemyProjectiles.remove(enemyProjectiles.get(i));
						}
					}
				} else if (respawnTime > 0) {
					respawnTime -= deltaTimeMS * .001;
				}
	
				if (!gameOver) {
	
					// Draw health and shadows
					for (int i = 0; i < healthPacks.size(); i++) {
						healthPackShadows.get(i).update(healthPacks.get(i).getX(), healthPacks.get(i).getY(),
								healthPackShadows.get(i).getCurrentImage());
						drawSprite(gl, healthPackShadows.get(i).getCurrentImage(), healthPackShadows.get(i).getX(),
								healthPackShadows.get(i).getY(), spriteWidth, spriteHeight, camera);
						drawSprite(gl, healthPacks.get(i).getCurrentImage(), healthPacks.get(i).getX(),
								healthPacks.get(i).getY(), spriteWidth, spriteHeight, camera);
					}
	
					if (levelOver) {
						if (collision.AABBIntersect(trapDoor, camera)) {
							drawSprite(gl, trapdoor, trapDoor.getX(), trapDoor.getY(), spriteWidth, spriteHeight, camera);
						}
					}
		
					// Draw the player's shadow
					drawSprite(gl, spriteShadow, shadow.getX(), shadow.getY() + shadowOffset, spriteWidth, spriteHeight,
							camera);
	
					// Draw Player
					drawSprite(gl, player.getCurrentImage(), player.getX(), player.getY() - z, spriteWidth, spriteHeight,
							camera);
	
					// Draw Enemy Shadows
					for (int i = 0; i < enemyShadows.size(); i++) {
						if (collision.AABBIntersect(enemyShadows.get(i), camera)) {
							drawSprite(gl, spriteShadow, enemyShadows.get(i).getX(),
									enemyShadows.get(i).getY() + enemies.get(i).getShadowOffset(), spriteWidth,
									spriteHeight, camera);
						}
					}
	
					// Draw Enemy and health bar if on screen
					for (int i = 0; i < enemies.size(); i++) {
						if (collision.AABBIntersect(enemies.get(i), camera)) {
							drawSprite(gl, enemies.get(i).getCurrentImage(), enemies.get(i).getX(), enemies.get(i).getY(),
									spriteWidth, spriteHeight, camera);
							drawSprite(gl, enemies.get(i).getHealth(), enemies.get(i).getX(),
									enemies.get(i).getY() - spriteHeight, spriteWidth, spriteHeight, camera);
						}
					}
	
					// Draw projectiles
					for (int i = 0; i < projectiles.size(); i++) {
						drawSprite(gl, projectiles.get(i).getCurrentImage(), projectiles.get(i).getX(),
								projectiles.get(i).getY(), projectiles.get(i).getWidth(), projectiles.get(i).getHeight(),
								camera);
					}
	
					// Draw Enemy Projectiles
					for (int i = 0; i < enemyProjectiles.size(); i++) {
						drawSprite(gl, enemyProjectiles.get(i).getCurrentImage(), enemyProjectiles.get(i).getX(),
								enemyProjectiles.get(i).getY(), enemyProjectiles.get(i).getWidth(),
								enemyProjectiles.get(i).getHeight(), camera);
					}
	
					// draw "player health" and lives
					drawText(gl, "PLAYER HEALTH", camera.getX() + 55 + ((spriteWidth * 5) / 2), camera.getY() + 10, camera, textSize);
					drawSprite(gl, player.getHealth(), camera.getX() + 5, camera.getY() - 20, spriteWidth * 5,
							spriteHeight * 5, camera);
					if (levelOver) {
						drawText(gl, "FIND THE TRAPDOOR TO GO", camera.getX() + 400, camera.getY() + 35,
								camera, textSize);
						drawText(gl, "TO THE NEXT LEVEL!", camera.getX() + 400, camera.getY() + 55,
								camera, textSize);
						System.out.println("X: " + trapDoor.getX() + " | Y: " +
						 trapDoor.getY());
					}
					for (int i = 0; i < playerLives; i++) {
						glDrawSprite(gl, playerHead, 742 - (20 * i), 0, spriteWidth * 2, spriteHeight * 2);
					}
	

					//IF LIVES < 0 SHOW GAME OVER SCREEN
					if (playerLives < 0)
					{
						gameOver = true;
						gamePaused = true;
					}
					
					//IF YOUR HEALTH IS ALL THE WAY DOWN, JUMP TO START POSITION
					if (player.getHealth() >= healthBar7) {
						gamePaused = true;
						
						if (kbState[KeyEvent.VK_X] || playerLives == 0)
						{
						gamePaused = false;
						//dead = true;
						respawnTime = 500;
						x = (13 * spriteWidth);
						y = (11 * spriteHeight);
						playerHealth = healthBar1;
						playerLives -= 1;
						}
						
						drawText(gl, "YOU DIED", player.getX() + (spriteWidth / 2), player.getY() - 30, camera, textSize);
						drawText(gl, "PRESS X TO RESPAWN", player.getX() + (spriteWidth / 2), player.getY() + 30, camera, textSize);
					}
					
					if(winScreen && gamePaused){
						glDrawSprite(gl, menuBackground, 0, 0, 800, 600);
						drawText(gl, "YOU WIN!", camera.X + 400, camera.Y + 290, camera, spriteSize);
						drawText(gl, "PRESS R TO PLAY AGAIN", camera.X + 400, camera.Y + 340, camera, textSize);
						//RESTART BUTTON
						if (kbState[KeyEvent.VK_R]) {
							winScreen = false;
							gameOver = false;
							//dead = false;
							levelOver = false;
							atStart = true;
							x = (13 * spriteWidth);
							y = (11 * spriteHeight);
							playerHealth = healthBar1;
							playerLives = 3;
							respawnTime = 0;
							levelNumber = 0;
							player.update(x, y, player.getCurrentImage(), playerHealth);
							background = bg.levels.get(0);
							backgroundTiles = bg.updateBackgroundTiles(backgroundTiles, levelNumber, spriteWidth, spriteHeight);
							for (int i = 0; i < 5; i++) {
								enemies.add(new Enemy((int) (Math.random() * 100),
										(int) (Math.random() * 100), spriteSize,
										enemySpriteTex, enemyRightArray, enemyLeftArray, 0, healthBar1, 0));
								enemies.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
							}
							for (int i = 0; i < enemies.size(); i++) {
								enemyShadows.add(
										new Sprite(enemies.get(i).getX(), enemies.get(i).getY(), spriteSize, spriteShadow));
							}
							glDrawSprite(gl, menuBackground, 0, 0, 800, 600);
							drawText(gl, "THE MAZE OF DESTINY", 380 + camera.X, 290 + camera.Y, camera, spriteSize);
							drawText(gl, "PRESS ENTER", 380 + camera.X, 340 + camera.Y, camera, textSize);
						}
					}
					
					// Draw YOU DIED
					//if (dead && !gameOver) {
					//}
				}
	
				if (gameOver) {
					camera.X = 0;
					camera.Y = 0;
					drawText(gl, "GAME OVER", 400, 290, camera, spriteSize);
					drawText(gl, "PRESS R TO TRY AGAIN", 400, 340, camera, textSize);
						//RESTART BUTTON
						if (kbState[KeyEvent.VK_R]) {
							gameOver = false;
							//dead = false;
							levelOver = false;
							atStart = true;
							x = (13 * spriteWidth);
							y = (11 * spriteHeight);
							playerHealth = healthBar1;
							playerLives = 3;
							respawnTime = 0;
							levelNumber = 0;
							player.update(x, y, player.getCurrentImage(), playerHealth);
							background = bg.levels.get(0);
							backgroundTiles = bg.updateBackgroundTiles(backgroundTiles, levelNumber, spriteWidth, spriteHeight);
							for (int i = 0; i < 5; i++) {
								enemies.add(new Enemy((int) (Math.random() * 100),
										(int) (Math.random() * 100), spriteSize,
										enemySpriteTex, enemyRightArray, enemyLeftArray, 0, healthBar1, 0));
								enemies.get(i).findSpace(backgroundTiles, (int) (Math.random() * 100), (int) (Math.random() * 100));
							}
							for (int i = 0; i < enemies.size(); i++) {
								enemyShadows.add(
										new Sprite(enemies.get(i).getX(), enemies.get(i).getY(), spriteSize, spriteShadow));
							}
						}
				}
	
				lastFrameNS = curFrameNS;
				elapsedTime += (1 + deltaTimeMS);
				direction = 4;
				shootTimer -= deltaTimeMS;
				enemyShootTimer -= deltaTimeMS;
				enemyChooseTimer -= deltaTimeMS;
				hitTimer -= deltaTimeMS;
			}
		}
	}
	
	public static void playMusic(String fname) //Clip sound
    {
        ClipPlayer cp = clipPlayer;
        try{
            Clip newClip = cp.loadClip(fname);
            newClip.loop(Clip.LOOP_CONTINUOUSLY);
            cp.playClip(newClip, true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	public static void drawSprite(GL2 gl, int tex, int x, int y, int w, int h, Camera cam) {
		glDrawSprite(gl, tex, (x - cam.getX()), (y - cam.getY()), w, h);
	}

	public static void drawText(GL2 gl, String text, int x, int y, Camera cam, int[] textSize) {
		int textLength = (text.length() * textSize[0]) / 2;
		ArrayList<Sprite> Text = new ArrayList<>(font.getTextures(alphabet, text, x, y, textSize));
		for (int i = 0; i < Text.size(); i++) {
			drawSprite(gl, Text.get(i).getCurrentImage(), Text.get(i).getX() - (textLength) - (spriteSize[0]), Text.get(i).getY(), Text.get(i).getWidth(), Text.get(i).getHeight(), cam);
		}
	}

	public static double distance(int ax, int ay, int bx, int by) {
		return Math.sqrt(Math.pow((ax - bx), 2) + Math.pow((ay - by), 2));
	}

	public static Point getMouseLocation(GLWindow window) {
		int x = MouseInfo.getPointerInfo().getLocation().x - window.getX();
		int y = MouseInfo.getPointerInfo().getLocation().y - window.getY();
		return new Point(x, y);
	}

	// Load a file into an OpenGL texture and return that texture.
	public static int glTexImageTGAFile(GL2 gl, String filename, int[] out_size) {
		final int BPP = 4;

		DataInputStream file = null;
		try {
			// Open the file.
			file = new DataInputStream(new FileInputStream(filename));
		} catch (FileNotFoundException ex) {
			System.err.format("File: %s -- Could not open for reading.", filename);
			return 0;
		}

		try {
			// Skip first two bytes of data we don't need.
			file.skipBytes(2);

			// Read in the image type. For our purposes the image type
			// should be either a 2 or a 3.
			int imageTypeCode = file.readByte();
			if (imageTypeCode != 2 && imageTypeCode != 3) {
				file.close();
				System.err.format("File: %s -- Unsupported TGA type: %d", filename, imageTypeCode);
				return 0;
			}

			// Skip 9 bytes of data we don't need.
			file.skipBytes(9);

			int imageWidth = Short.reverseBytes(file.readShort());
			int imageHeight = Short.reverseBytes(file.readShort());
			int bitCount = file.readByte();
			file.skipBytes(1);

			// Allocate space for the image data and read it in.
			byte[] bytes = new byte[imageWidth * imageHeight * BPP];

			// Read in data.
			if (bitCount == 32) {
				for (int it = 0; it < imageWidth * imageHeight; ++it) {
					bytes[it * BPP + 0] = file.readByte();
					bytes[it * BPP + 1] = file.readByte();
					bytes[it * BPP + 2] = file.readByte();
					bytes[it * BPP + 3] = file.readByte();
				}
			} else {
				for (int it = 0; it < imageWidth * imageHeight; ++it) {
					bytes[it * BPP + 0] = file.readByte();
					bytes[it * BPP + 1] = file.readByte();
					bytes[it * BPP + 2] = file.readByte();
					bytes[it * BPP + 3] = -1;
				}
			}

			file.close();

			// Load into OpenGL
			int[] texArray = new int[1];
			gl.glGenTextures(1, texArray, 0);
			int tex = texArray[0];
			gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
			gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, imageWidth, imageHeight, 0, GL2.GL_BGRA,
					GL2.GL_UNSIGNED_BYTE, ByteBuffer.wrap(bytes));
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
			gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

			out_size[0] = imageWidth;
			out_size[1] = imageHeight;
			return tex;
		} catch (IOException ex) {
			System.err.format("File: %s -- Unexpected end of file.", filename);
			return 0;
		}
	}

	public static void glDrawSprite(GL2 gl, int tex, int x, int y, int w, int h) {
		gl.glBindTexture(GL2.GL_TEXTURE_2D, tex);
		gl.glBegin(GL2.GL_QUADS);
		{
			gl.glColor3ub((byte) -1, (byte) -1, (byte) -1);
			gl.glTexCoord2f(0, 1);
			gl.glVertex2i(x, y);
			gl.glTexCoord2f(1, 1);
			gl.glVertex2i(x + w, y);
			gl.glTexCoord2f(1, 0);
			gl.glVertex2i(x + w, y + h);
			gl.glTexCoord2f(0, 0);
			gl.glVertex2i(x, y + h);
		}
		gl.glEnd();
	}
}