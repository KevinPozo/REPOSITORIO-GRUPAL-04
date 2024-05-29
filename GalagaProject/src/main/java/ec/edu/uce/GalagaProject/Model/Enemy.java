package ec.edu.uce.GalagaProject.Model;

import ec.edu.uce.GalagaProject.Model.Interfaces.IDieable;
import ec.edu.uce.GalagaProject.Model.Interfaces.IDrawable;
import ec.edu.uce.GalagaProject.Model.Interfaces.IMovable;
import ec.edu.uce.GalagaProject.Model.Interfaces.IScore;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.Graphics;

@Service("enemy")
public class Enemy implements IDrawable, IMovable, IDieable, IScore {
    private int x, y;
    private int width, height;
    private boolean dead = false;
    private int score;

    // Constructor predeterminado
    public Enemy() {
        this(100, 100, 30, 30, 50);
    }

    // Constructor con parámetros
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

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void increaseScore(int points) {
        score += points;
    }

    // Getters adicionales para las propiedades de Enemy
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
