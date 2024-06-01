package ec.edu.uce.GameProject.Model.DB;

import jakarta.persistence.*;

@Entity
@Table(name = "player_db")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nameUser;

    @Column
    private int ageUser;

    @Column
    private int scoreUser;

    @Column
    private int levelUser;

    public Player() {
    }

    public Player(long id, String nameUser, int ageUser, int scoreUser, int levelUser) {
        this.id = id;
        this.nameUser = nameUser;
        this.ageUser = ageUser;
        this.scoreUser = scoreUser;
        this.levelUser = levelUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getAgeUser() {
        return ageUser;
    }

    public void setAgeUser(int ageUser) {
        this.ageUser = ageUser;
    }

    public int getScoreUser() {
        return scoreUser;
    }

    public void setScoreUser(int scoreUser) {
        this.scoreUser = scoreUser;
    }

    public int getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(int levelUser) {
        this.levelUser = levelUser;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nameUser='" + nameUser + '\'' +
                ", ageUser=" + ageUser +
                ", scoreUser=" + scoreUser +
                ", levelUser=" + levelUser +
                '}';
    }
}
