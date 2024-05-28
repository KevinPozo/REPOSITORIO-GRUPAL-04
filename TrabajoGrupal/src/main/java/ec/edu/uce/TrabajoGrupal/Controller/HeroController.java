package ec.edu.uce.TrabajoGrupal.Controller;

import ec.edu.uce.TrabajoGrupal.Model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
public class HeroController {

    @Autowired
    @Qualifier("hero")
    private Hero hero;

    public HeroController() {
    }

    public void draw(Graphics g) {
        this.hero.draw(g);
    }

    // Agregar métodos adicionales para manejar las interacciones del héroe
    public void moveHero(int dx, int dy) {
        hero.move(dx, dy);
    }

    public void heroShoot() {
        hero.shoot();
    }

    public int getHeroScore() {
        return hero.getScore();
    }

    public void increaseHeroScore(int points) {
        hero.increaseScore(points);
    }

    public int getHeroHealth() {
        return hero.getCurrentHealth();
    }

    public void decreaseHeroHealth(int amount) {
        hero.decreaseHealth(amount);
    }

    public void increaseHeroHealth(int amount) {
        hero.increaseHealth(amount);
    }
}
