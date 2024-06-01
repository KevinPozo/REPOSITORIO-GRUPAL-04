package ec.edu.uce.GameProject;

import ec.edu.uce.GameProject.Model.DB.Player;
import ec.edu.uce.GameProject.Model.DB.PlayerService;
import ec.edu.uce.GameProject.View.GameFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameProjectApplication implements CommandLineRunner {
	@Autowired
	PlayerService service;
	public static void main(String[] args) {
		SpringApplication.run(GameProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.save(new Player(2, "Walker77", 0, 23, 1));
	}
}
