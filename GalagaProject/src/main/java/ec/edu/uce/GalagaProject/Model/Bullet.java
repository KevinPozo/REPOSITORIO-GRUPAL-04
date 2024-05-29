package ec.edu.uce.GalagaProject.Model;

import ec.edu.uce.GalagaProject.Model.Interfaces.IDrawable;
import ec.edu.uce.GalagaProject.Model.Interfaces.IMovable;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.awt.Graphics;

@Service("bullet")
public class Bullet implements IDrawable, IMovable {
    private int x, y;
    private int width, height;
    private int speed;

    // Constructor predeterminado
    public Bullet() {
        this(0, 0, 5, 10, 5); // Valores de ejemplo
    }

    // Constructor con parámetros
    public Bullet(int x, int y, int width, int height, int speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    // Getters adicionales
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

    public int getSpeed() {
        return speed;
    }
}

