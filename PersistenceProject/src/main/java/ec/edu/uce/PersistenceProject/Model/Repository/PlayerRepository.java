package ec.edu.uce.PersistenceProject.Model.Repository;

import ec.edu.uce.PersistenceProject.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
