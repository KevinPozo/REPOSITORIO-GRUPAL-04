package ec.edu.uce.PersistenceProject.Repository;

import ec.edu.uce.PersistenceProject.Model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    GameEntity findByUsername(String username);
}
