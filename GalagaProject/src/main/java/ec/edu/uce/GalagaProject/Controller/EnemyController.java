package ec.edu.uce.GalagaProject.Controller;

import ec.edu.uce.GalagaProject.Model.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.awt.Graphics;

@Controller
public class EnemyController {

    @Autowired
    @Qualifier("enemy")
    private Enemy enemy;

    public EnemyController() {
    }

    // Método para dibujar al enemigo utilizando el objeto Graphics
    public void draw(Graphics g) {
        enemy.draw(g);
    }

    // Método para mover al enemigo
    public void moveEnemy(int dx, int dy) {
        enemy.move(dx, dy);
    }

    // Método para que el enemigo muera
    public void enemyDie() {
        enemy.die();
    }

    // Método para verificar si el enemigo está muerto
    public boolean isEnemyDead() {
        return enemy.isDead();
    }

    // Método para obtener la puntuación del enemigo
    public int getEnemyScore() {
        return enemy.getScore();
    }

    // Método para aumentar la puntuación del enemigo
    public void increaseEnemyScore(int points) {
        enemy.increaseScore(points);
    }

    // Getters adicionales para las propiedades de Enemy si es necesario
    public int getEnemyX() {
        return enemy.getX();
    }

    public int getEnemyY() {
        return enemy.getY();
    }

    public int getEnemyWidth() {
        return enemy.getWidth();
    }

    public int getEnemyHeight() {
        return enemy.getHeight();
    }
}

