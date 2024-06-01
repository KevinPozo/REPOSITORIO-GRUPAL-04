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

public class Bullet implements IDrawable, IMovable {
    private int x, y; 
    private int width, height; 
    private int speed; 

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
