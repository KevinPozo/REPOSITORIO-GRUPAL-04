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

import java.awt.*;

import Controller.GameController;
import Model.Interfaces.IDieable;
import Model.Interfaces.IDrawable;
import Model.Interfaces.IMovable;
import Model.Interfaces.IScore;
import Model.Interfaces.IShootable;

public class Enemy implements IDrawable, IMovable, IDieable, IScore, IShootable {
    private int x, y;
    private int width, height;
    private boolean dead = false;
    private int score;
    private GameController gameController;
    private int speedFactor;
    private int shotsReceived;

    public Enemy(int x, int y, int width, int height, int score, GameController gameController) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.score = score;
        this.gameController = gameController;
        this.speedFactor = 1; // Inicialmente establecido en 1
        this.shotsReceived = 0;
    }

    @Override
    public void draw(Graphics g) {
        int[] xPoints = {x, x + width, x + width, x, x + width / 2};
        int[] yPoints = {y, y, y + height, y + height, y + height / 2};
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        int[] rotatedXPoints = rotateXRight(xPoints, yPoints, centerX, centerY);
        int[] rotatedYPoints = rotateYRight(xPoints, yPoints, centerX, centerY);
        Polygon nave = new Polygon(rotatedXPoints, rotatedYPoints, xPoints.length);
        g.setColor(Color.GREEN);
        g.fillPolygon(nave);
    }

    @Override
    public void move(int dx, int dy) {
        int pixelsToMoveX = dx * speedFactor;
        int pixelsToMoveY = dy * speedFactor;
        this.x += pixelsToMoveX;
        this.y += pixelsToMoveY;
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
        if (!isDead()) {
            score += points;
            die();
        }
    }

    @Override
    public void shoot() {
        int bulletX = this.x + this.width / 2;
        int bulletY = this.y + this.height;
        int bulletWidth = 5;
        int bulletHeight = 10;
        int bulletSpeed = 10;
        Bullet bullet = new Bullet(bulletX, bulletY, bulletWidth, bulletHeight, bulletSpeed, true);
        gameController.addBullet(bullet);
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
    private int[] rotateXRight(int[] xPoints, int[] yPoints, int centerX, int centerY) {
        int[] rotatedX = new int[xPoints.length];
        for (int i = 0; i < xPoints.length; i++) {
            rotatedX[i] = centerX + (yPoints[i] - centerY);
        }
        return rotatedX;
    }
    private int[] rotateYRight(int[] xPoints, int[] yPoints, int centerX, int centerY) {
        int[] rotatedY = new int[yPoints.length];
        for (int i = 0; i < yPoints.length; i++) {
            rotatedY[i] = centerY - (xPoints[i] - centerX);
        }
        return rotatedY;
    }
    public void setSpeedFactor(int speedFactor) {
        this.speedFactor = speedFactor;
    }
    public void increaseShotsReceived() {
        this.shotsReceived++;
    }
    public int getShotsReceived() {
        return shotsReceived;
    }
}
