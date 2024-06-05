package ec.edu.uce.PersistenceProject.Controller;

import ec.edu.uce.PersistenceProject.Model.Player;
import ec.edu.uce.PersistenceProject.Model.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class PlayerController {

    @Autowired
    private PlayerService heroService;

    @PostMapping
    public Player createHero(@RequestBody Player hero) {
        return heroService.saveHero(hero);
    }
    @GetMapping
    public List<Player> getAllHeroes() {
        return heroService.getAllHeroes();
    }
}

