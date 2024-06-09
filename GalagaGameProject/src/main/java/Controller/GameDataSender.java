/**
 * @author KevinPozo
 * @author BrayanLoya
 * @author JordyChamba
 * Title: Proyecto Galaga (Game).
 */
package Controller;

import Model.Hero;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GameDataSender {

    private static final String SERVER_URL = "http://localhost:8080/api/game/saveScore";

    public static void extractGameData(Hero hero, GameController gameController) {
        String heroName = hero.getUsername();
        int score = hero.getScore();
        int level = gameController.getLevel();
        int health = hero.getCurrentHealth();
        sendScore(heroName, score, level, health);
    }

    private static void sendScore(String heroName, int score, int level, int health) {
        try {
            String urlParameters = "username=" + URLEncoder.encode(heroName, "UTF-8") +
                    "&score=" + score +
                    "&level=" + level +
                    "&health=" + health;

            URL url = new URL(SERVER_URL + "?" + urlParameters);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Score sent successfully!");
            } else {
                System.out.println("Failed to send score. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
