/**
 *
 * @author KevinPozo
 * @author BrayanLoya
 * @author JordyChamba
 * Title: Proyecto Galaga (Game).
 *
 *
 * */
package Controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import Model.Enemy;
import Model.Hero;
import Model.Interfaces.IDieable;
import Model.Interfaces.IDrawable;
import Model.Interfaces.ILife;
import Model.Interfaces.IMovable;
import Model.Bullet;
import Model.Line;
public class GameController {
	private static final int HEIGHT = 600;
	private static final int WIDTH = 800;
	private List<IDrawable> drawables;
	private List<IMovable> movables;
	private List<IDrawable> bullets;
	private List<IDieable> deadElements;
	private int level;
	private Random random;
	private int enemyShootCooldown = 75;
	private int enemyShootTimer = enemyShootCooldown;
	private static final int PLAYER_TOP_LIMIT = 2 * HEIGHT / 3 + 21;
	private static final int LINE_Y_POSITION = 2 * HEIGHT / 3;
	private boolean gameOver = false;
	private Hero hero;
	private ILife lifeHero;

	public GameController() {
		drawables = new ArrayList<>();
		movables = new ArrayList<>();
		bullets = new ArrayList<>();
		deadElements = new ArrayList<>();
		random = new Random();
		level = 1;
		hero = new Hero(400, 450, 50, 25, "Walker77", this);
		addDrawable(hero);
		addMovable(hero);
		createEnemies();
		addLife(hero);
	}

	private void createEnemies() {
		int enemyWidth = 50;
		int enemyHeight = 50;
		int startX = 170;
		int startY = 50;
		int gapX = 100;
		int enemySpeedFactor = 1;

		// Level-specific enemy configurations (modified for clarity and efficiency)
		int numEnemies;
		int enemyScore;
		switch (level) {
			case 1:
				numEnemies = 5;
				enemyScore = 5;
				break;
			case 2:
				numEnemies = 10;
				enemyScore = 10;
				break;
			case 3:
				numEnemies = 1;
				enemyScore = 15;
				enemyWidth *= 2.5;
				enemyHeight *= 2.5;
				startX = 200;
				break;
			default:
				return;
		}
		for (int i = 0; i < numEnemies; i++) {
			Enemy enemy = new Enemy(startX + (i % 5) * gapX, startY + (i / 5) * gapX, enemyWidth, enemyHeight, enemyScore, this);
			enemy.setSpeedFactor(enemySpeedFactor);
			addDrawable(enemy);
			addMovable(enemy);
		}
	}
	public void addDrawable(IDrawable drawable) {
		drawables.add(drawable);
	}
	public void addMovable(IMovable movable) {
		movables.add(movable);
	}
	public void addBullet(IDrawable bullet) {
		bullets.add(bullet);
	}
	public void addShootable(IDrawable shootable) {
		if (shootable instanceof Bullet) {
			bullets.add(shootable);
		}
	}
	public void addDieable(IDieable element) {
		deadElements.add(element);
	}
	public void addLife(ILife life) {
		this.lifeHero = life;
		addDrawable((IDrawable) life);
		addMovable((IMovable) life);
	}
	public void update() {
		for (IMovable movable : movables) {
			movable.move(0, 0);
		}
		moveEnemiesDown();
		handleDeadElements();
		checkEnemiesCrossedLine();
		checkGameOver();
		checkBulletCollision();
		enemyShootTimer--;
		if (!gameOver && enemyShootTimer <= 0 && level == 1) {
			enemyShoot();
			enemyShootTimer = enemyShootCooldown;
		}else if(!gameOver && enemyShootTimer <= 0 && level == 2){
			enemyShoot();
			enemyShootTimer = enemyShootCooldown;
		}else if(!gameOver && enemyShootTimer <= 0 && level == 3){
			enemyShoot();
			enemyShootTimer += enemyShootCooldown;
		}
	}
	public void render(Graphics g) {
		Line line = new Line(0, LINE_Y_POSITION, WIDTH, LINE_Y_POSITION);
		line.draw(g);

		for (IDrawable drawable : drawables) {
			drawable.draw(g);
		}

		for (IDrawable bullet : bullets) {
			bullet.draw(g);
		}

		int barWidth = 100;
		int barHeight = 10;
		int barX = 10;
		int barY = 60;
		double healthPercentage = (double) hero.getCurrentHealth() / hero.getMaxHealth();

		g.setColor(Color.BLACK);
		g.drawRect(barX, barY, barWidth, barHeight);

		g.setColor(Color.RED);
		g.fillRect(barX, barY, (int) (barWidth * healthPercentage), barHeight);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		int textWidth = g.getFontMetrics().stringWidth("Life: " + hero.getCurrentHealth());
		int textX = barX + (barWidth - textWidth) / 2;
		int textY = barY + barHeight + 15;
		g.drawString("Life: " + hero.getCurrentHealth(), textX, textY);
		g.drawString("Level: " + level, WIDTH - 100, 20);
		if (gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
			g.drawString("GAME OVER", 300, 300);

			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			g.drawString("Your Health: " + hero.getCurrentHealth(), 300, 340);

			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			g.drawString("Your Score: " + hero.getScore(), 300, 375);
		}

		// Dibuja la barra de vida del super enemigo en el nivel 3
		if (level == 3) {
			for (IDrawable drawable : drawables) {
				if (drawable instanceof Enemy) {
					Enemy enemy = (Enemy) drawable;
					if (enemy.getMaxHealth() == 100) { // Si es el super enemigo
						int barWidth1 = 100;
						int barHeight1 = 10;
						int barX1 = enemy.getX();
						int barY1 = enemy.getY() - 25; // Ajusta la posición de la barra de vida
						double healthPercentage1 = (double) enemy.getCurrentHealth() / enemy.getMaxHealth();

						g.setColor(Color.BLACK);
						g.drawRect(barX1, barY1, barWidth1, barHeight1);

						g.setColor(Color.RED);
						g.fillRect(barX1, barY1, (int) (barWidth1 * healthPercentage1), barHeight1);
					}
				}
			}
		}
	}

	private void handleDeadElements() {
		Iterator<IDieable> deadIterator = deadElements.iterator();
		while (deadIterator.hasNext()) {
			IDieable deadElement = deadIterator.next();
			if (deadElement instanceof IDrawable) {
				drawables.remove(deadElement);
			}
			if (deadElement instanceof IMovable) {
				movables.remove(deadElement);
			}
			deadIterator.remove();
		}
		if (!gameOver && drawables.stream().noneMatch(d -> d instanceof Enemy)) {
			levelUp();
		}
	}
	private void moveEnemiesDown() {
		for (IMovable movable : movables) {
			if (movable instanceof Enemy) {
				movable.move(0, 1);
			}
		}
	}
	private void enemyShoot() {
		for (IMovable movable : movables) {
			if (movable instanceof Enemy) {
				((Enemy) movable).shoot();
			}
		}
	}
	public void heroShoot() {
		int heroX = hero.getX();
		int heroY = hero.getY();
		Bullet bullet = new Bullet((heroX + hero.getWidth() / 2) - 27, heroY - 15, 5, 10, 10, false);
		addShootable(bullet);
	}
	public void moveHero(int dx, int dy, int speed) {
		if (hero != null) {
			int newX = (hero.getX() + dx * speed) - 25;
			int newY = hero.getY() + dy * speed;

			int playerBottom = newY + hero.getHeight();
			if (newX >= 0 && newX + hero.getWidth() <= WIDTH && newY >= 0 && playerBottom <= HEIGHT
					&& newY >= PLAYER_TOP_LIMIT) {
				hero.move(dx * speed, dy * speed);
			}
		}
	}
	private void checkBulletCollision() {
		Iterator<IDrawable> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = (Bullet) bulletIterator.next();
			if (bullet.isEnemyBullet()) {
				bullet.move(0, bullet.getSpeed());
			} else {
				bullet.move(0, -bullet.getSpeed());
			}

			if (bullet.getY() == HEIGHT || bullet.getY() == 0) {
				bulletIterator.remove();
			} else {
				if (!bullet.isEnemyBullet()) {
					Iterator<IDrawable> drawableIterator = drawables.iterator();
					while (drawableIterator.hasNext()) {
						IDrawable drawable = drawableIterator.next();
						if (drawable instanceof Enemy) {
							Enemy enemy = (Enemy) drawable;
							Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
							Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight());
							if (bulletRect.intersects(enemyRect)) {
								bulletIterator.remove();
								if (level == 3 && enemy.getMaxHealth() == 100) { // Super enemigo del nivel 3
									if (hero.getCurrentHealth() < 50) {
										enemy.decreaseHealth(enemy.getScore());
									} else if (hero.getCurrentHealth() >= 50 && hero.getCurrentHealth() <= 75) {
										enemy.decreaseHealth(enemy.getScore());
									} else {
										enemy.decreaseHealth(enemy.getScore());
									}
									if (enemy.isDead()) {
										hero.increaseScore(enemy.getScore());
										drawableIterator.remove();
										addDieable(enemy);
									}
								} else {
									enemy.increaseShotsReceived();
									if ((level == 1 && enemy.getShotsReceived() >= 1) || (level == 2 && enemy.getShotsReceived() >= 2)
											|| (level == 3 && enemy.getShotsReceived() >= 20)) {
										enemy.die();
										if (level == 1) {
											hero.increaseScore(enemy.getScore());
										} else if (level == 2) {
											hero.increaseScore(enemy.getScore());
										} else if (level == 3) {
											hero.increaseScore(enemy.getScore());
										}
										drawableIterator.remove();
										addDieable(enemy);
									}
								}
								break;
							}
						}
					}
				} else {
					Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
					Rectangle heroRect = new Rectangle(hero.getX(), hero.getY(), hero.getWidth(), hero.getHeight());
					if (bulletRect.intersects(heroRect)) {
						bulletIterator.remove();
						if (level == 1) {
							lifeHero.decreaseHealth(5);
						} else if (level == 2) {
							lifeHero.decreaseHealth(10);
						} else if (level == 3) {
							lifeHero.decreaseHealth(15);
						}
						if (lifeHero.getCurrentHealth() <= 0) {
							gameOver = true;
						}
					}
				}
			}
		}
	}
	public void checkEnemiesCrossedLine() {
		Iterator<IDrawable> iterator = drawables.iterator();
		int totalDamage = 0;

		while (iterator.hasNext()) {
			IDrawable drawable = iterator.next();
			if (drawable instanceof Enemy) {
				Enemy enemy = (Enemy) drawable;
				if (enemy.getY() >= LINE_Y_POSITION) {
					enemy.die();
					handleDeadElements();
					totalDamage += 25;
					iterator.remove();
				}
			}
		}
		lifeHero.decreaseHealth(totalDamage);
		if (lifeHero.getCurrentHealth() <= 0) {
			gameOver = true;
		}
	}
	private void checkGameOver() {
		if (lifeHero.getCurrentHealth() <= 0) {
			gameOver = true;
		}else if(level>=4){
			gameOver = true;
		}
	}
	private void levelUp() {
		level++;
		createEnemies();
	}
}


