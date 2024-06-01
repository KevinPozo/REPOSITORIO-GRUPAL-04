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
import java.awt.Polygon;

import Interfaces.IDieable;
import Interfaces.IDrawable;
import Interfaces.ILife;
import Interfaces.IMovable;
import Interfaces.IScore;
import Interfaces.IShootable;
import Interfaces.IUsername;

public class Hero implements IDrawable, IMovable, IShootable, IDieable, IUsername, IScore , ILife{
    private int x, y; 
    private int maxHealth;
    private int currentHealth;
    private int width, height; 
    private int score; 
    private String username; 
    private boolean dead = false;
    private GameContainer gameContainer; 
    
    public Hero(int x, int y, int width, int height, String username, GameContainer gameContainer) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = 0; 
        this.username = username;
        this.gameContainer = gameContainer;
        this.maxHealth = 100;
        this.currentHealth = this.maxHealth;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        int[] xPoints = {x, x - width / 2, x + width / 2};
        int[] yPoints = {y - height, y + height, y + height};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);
        g.fillPolygon(triangle);
        
        Font font = new Font("Comic Sans MS", Font.BOLD, 16);
        g.setFont(font);

        g.drawString("Player: " + getUsername(), 10, 20);
        
        g.drawString("Score: " + getScore(), 10, 40);
        
        int barWidth = 100; 
        int barHeight = 10; 
        int barX = 10; 
        int barY = 60; 
        double healthPercentage = (double) currentHealth / maxHealth; 
        
        g.setColor(Color.BLACK);
        g.drawRect(barX, barY, barWidth, barHeight);
        
        g.setColor(Color.RED);
        g.fillRect(barX, barY, (int) (barWidth * healthPercentage), barHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        int textWidth = g.getFontMetrics().stringWidth("Life: " + getCurrentHealth());
        int textX = barX + (barWidth - textWidth) / 2;
        int textY = barY + barHeight + 15;
        g.drawString("Life: " + getCurrentHealth(), textX, textY);
    }


    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public void shoot() {
        int bulletX = this.x + this.width / 2; 
        int bulletY = this.y;
        int bulletWidth = 5; 
        int bulletHeight = 10; 
        int bulletSpeed = 10;

        Bullet bullet = new Bullet(bulletX, bulletY, bulletWidth, bulletHeight, bulletSpeed);
        gameContainer.addBullet(bullet);
    }

    @Override
    public void die() {
        dead = true;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore(int points) {
        score += points;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
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
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void decreaseHealth(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }


    @Override
    public void increaseHealth(int amount) {
        currentHealth += amount;
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }
}
