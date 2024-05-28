    package ec.edu.uce.TrabajoGrupal.Model;

    import jakarta.persistence.*;
    import org.springframework.data.annotation.Id;

    @Entity
    @Table (name = "player_user")
    public class Player {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        @Column
        private String nameUser;
        @Column
        private int ageUser;
        @Column
        private int scoreUser;
        @Column
        private int levelUser;

    public Player(){

    }
        public Player(long id, String nameUser, int ageUser, int scoreUser, int levelUser) {
            this.id = id;
            this.nameUser = nameUser;
            this.ageUser = ageUser;
            this.scoreUser = scoreUser;
            this.levelUser = levelUser;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
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

