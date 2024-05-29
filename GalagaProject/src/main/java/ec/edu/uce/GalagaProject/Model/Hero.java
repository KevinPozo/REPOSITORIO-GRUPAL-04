package ec.edu.uce.GalagaProject.Model;

import ec.edu.uce.GalagaProject.Model.Interfaces.*;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;

@Service("hero")
public class Hero implements ILife, IShootable, IDieable, IUsername, IScore {
    private int x, y;
    private int maxHealth;
    private int currentHealth;
    private int width, height;
    private int score;
    private String username;
    private boolean dead = false;

    public Hero() {
        this(50, 50, 20, 20, "Walker77");
    }

    public Hero(int x, int y, int width, int height, String username) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = 0;
        this.username = username;
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
        // Implementar lógica de disparo
        System.out.println("Disparo realizado desde posición (" + x + ", " + y + ")");
    }

    @Override
    public void die() {
        dead = true;
        System.out.println("El héroe " + this.username + " ha muerto.");
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
            die();
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

    // Métodos getter adicionales para x, y, width y height si es necesario.
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
}