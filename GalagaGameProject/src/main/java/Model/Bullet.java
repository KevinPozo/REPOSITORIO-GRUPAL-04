/**
 *
 * @author KevinPozo
 * @author BrayanLoya
 * @author JordyChamba
 * Title: Proyecto Galaga (Game).
 *
 *
 * */
package Model;

import java.awt.Color;
import java.awt.Graphics;

import Model.Interfaces.IDrawable;
import Model.Interfaces.IMovable;

public class Bullet implements IDrawable, IMovable {
    private int x, y;
    private int width, height;
    private int speed;
    private boolean isEnemyBullet;

    public Bullet(int x, int y, int width, int height, int speed, boolean isEnemyBullet) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.isEnemyBullet = isEnemyBullet;
    }

    @Override
    public void draw(Graphics g) {
        if (isEnemyBullet) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.WHITE);
        }
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

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }
}