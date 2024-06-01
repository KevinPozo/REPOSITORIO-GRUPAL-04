package ec.edu.uce.GameProject.Model.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository repository;
    public void save(Player player){
        repository.save(player);
    }
}
