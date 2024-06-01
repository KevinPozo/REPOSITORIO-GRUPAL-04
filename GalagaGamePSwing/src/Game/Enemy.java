/**
 * 
 * @author KevinPozo
 * Title: Inversión de Dependencia y Responsabilidad Única.
 * 
 * 
 * */
package Game;

import java.awt.Color;
import java.awt.Graphics;

import Interfaces.IDieable;
import Interfaces.IDrawable;
import Interfaces.IMovable;
import Interfaces.IScore;

public class Enemy implements IDrawable, IMovable, IDieable, IScore {
    private int x, y; 
    private int width, height; 
    private boolean dead = false;
    private int score;

    public Enemy(int x, int y, int width, int height, int score) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void increaseScore(int points) {
	    if (!isDead()) {
	        score += points;
	        die();
	    }
	}

    
}
