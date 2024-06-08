package ec.edu.uce.PersistenceProject.Service;

import ec.edu.uce.PersistenceProject.Model.GameEntity;
import ec.edu.uce.PersistenceProject.Repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void saveScore(String username, int score, int level, int health) {
        try {
            System.out.println("Saving score for username: "
                    + username + " with score: " + score
                    + " Nivel que llego: "+ level + " health: " + health);
            GameEntity gameState = new GameEntity(username, score, level, health);
            gameRepository.save(gameState);
        } catch (Exception e) {
            System.err.println("Error in GameService: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public GameEntity getScoreByUsername(String username) {
        return gameRepository.findByUsername(username);
    }

    public List<GameEntity> getAllScores() {
        return gameRepository.findAll();
    }
}
