/**
 * 
 * @author KevinPozo
 * Title: Inversión de Dependencia y Responsabilidad Única.
 * 
 * 
 * */
package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Interfaces.IDieable;
import Interfaces.IDrawable;
import Interfaces.ILife;
import Interfaces.IMovable;

public class GameContainer {
	private static final int HEIGHT = 600;
	private List<IDrawable> drawables;
	private List<IMovable> movables;
	private List<IDrawable> bullets;
	private List<IDieable> deadElements;

	private static final int PLAYER_TOP_LIMIT = 2 * HEIGHT / 3 + 21;
	private static final int LINE_Y_POSITION = 2 * HEIGHT / 3;
	private boolean gameOver = false;
	private Hero hero;
	private ILife lifeHero;

	public GameContainer() {
		drawables = new ArrayList<>();
		movables = new ArrayList<>();
		bullets = new ArrayList<>();
		deadElements = new ArrayList<>();

		hero = new Hero(400, 450, 50, 25, "Walker77", this);
		addDrawable(hero);
		addMovable(hero);
		createEnemies();
		addLife(hero);
	}

	private void createEnemies() {
		int enemyWidth = 50;
		int enemyHeight = 50;
		int startX = 50;
		int startY = 50;
		int gapX = 100;
		int numEnemies = 7;

		for (int i = 0; i < numEnemies; i++) {
			Enemy enemy = new Enemy(startX + i * gapX, startY, enemyWidth, enemyHeight, 25);
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
		checkBulletEnemyCollision();
	}

	public void render(Graphics g) {
		Line line = new Line(0, LINE_Y_POSITION, 800, LINE_Y_POSITION);
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

		if (gameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
			g.drawString("GAME OVER", 300, 300);

			g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
			g.drawString("Your Health: " + hero.getCurrentHealth(), 300, 350);
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
	}

	private void moveEnemiesDown() {
		for (IMovable movable : movables) {
			if (movable instanceof Enemy) {
				movable.move(0, 1);
			}
		}
	}

	public void heroShoot() {
		int heroX = hero.getX();
		int heroY = hero.getY();

		Bullet bullet = new Bullet((heroX + hero.getWidth() / 2) - 27, heroY - 15, 5, 10, 10);
		addShootable(bullet);
	}

	public void moveHero(int dx, int dy) {
		if (hero != null) {
			int newX = (hero.getX() + dx) - 25;
			int newY = hero.getY() + dy;

			int playerBottom = newY + hero.getHeight();
			if (newX >= 0 && newX + hero.getWidth() <= 800 && newY >= 0 && playerBottom <= HEIGHT
					&& newY >= PLAYER_TOP_LIMIT) {
				hero.move(dx, dy);
			}
		}
	}

	private void checkBulletEnemyCollision() {
		Iterator<IDrawable> bulletIterator = bullets.iterator();
		while (bulletIterator.hasNext()) {
			Bullet bullet = (Bullet) bulletIterator.next();
			bullet.move(0, -bullet.getSpeed());
			if (bullet.getY() == HEIGHT) {
				bulletIterator.remove();
			} else {
				Iterator<IDrawable> drawableIterator = drawables.iterator();
				while (drawableIterator.hasNext()) {
					IDrawable drawable = drawableIterator.next();
					if (drawable instanceof Enemy) {
						Enemy enemy = (Enemy) drawable;
						Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(),
								bullet.getHeight());
						Rectangle enemyRect = new Rectangle(enemy.getX(), enemy.getY(), enemy.getWidth(),
								enemy.getHeight());
						if (bulletRect.intersects(enemyRect)) {
							bulletIterator.remove();
							enemy.die();
							hero.increaseScore(25);
							drawableIterator.remove();
							addDieable(enemy);
							break;
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
		}
	}
}
