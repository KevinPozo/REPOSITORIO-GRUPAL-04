package ec.edu.uce.PersistenceProject.Controller;

import ec.edu.uce.PersistenceProject.Model.GameEntity;
import ec.edu.uce.PersistenceProject.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/saveScore")
    public String saveScore(@RequestParam String username, @RequestParam int score,
                            @RequestParam int level, @RequestParam int health, Model model) {
        gameService.saveScore(username, score, level, health);
        List<GameEntity> scores = gameService.getAllScores();
        model.addAttribute("scores", scores);
        model.addAttribute("message", "Score saved successfully!");
        return "game_scores";
    }


    @GetMapping("/getScore")
    public GameEntity getScore(@RequestParam String username) {
        return gameService.getScoreByUsername(username);
    }

    @GetMapping("/getAllScores")
    public String getAllScores(Model model) {
        List<GameEntity> scores = gameService.getAllScores();
        model.addAttribute("scores", scores);
        return "game_scores";
    }
}
