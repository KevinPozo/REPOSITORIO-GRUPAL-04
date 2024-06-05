package ec.edu.uce.PersistenceProject.Model.Service;
import ec.edu.uce.PersistenceProject.Model.Player;
import ec.edu.uce.PersistenceProject.Model.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository heroRepository;

    public Player saveHero(Player pl) {
        return heroRepository.save(pl);
    }

    public List<Player> getAllHeroes() {
        return heroRepository.findAll();
    }
}

