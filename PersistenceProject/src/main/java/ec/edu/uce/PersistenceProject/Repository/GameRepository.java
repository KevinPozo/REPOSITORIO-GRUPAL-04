/**
 * @author KevinPozo
 * @author BrayanLoya
 * @author JordyChamba
 * Title: Proyecto Persistence (Springboot).
 */
package ec.edu.uce.PersistenceProject.Repository;

import ec.edu.uce.PersistenceProject.Model.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
    GameEntity findByUsername(String username);
}
