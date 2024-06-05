/**
 *
 * @author KevinPozo
 * @author BrayanLoya
 * @author JordyChamba
 * Title: Proyecto Galaga (Game).
 *
 *
 * */
package View;

import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameView extends JFrame implements KeyListener {
    private JPanel gamePanel;
    private GameController gameController;
    private Timer gameTimer;
    private int delay = 1000 / 60;
    private int speed = 6;
    private boolean gameStarted = false;

    public GameView() {
        setTitle("Galaga Game Group 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);
                if (gameStarted) {
                    gameController.render(g);
                } else {
                    showStartScreen(g);
                }
            }
        };
        gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel);
        gameController = new GameController();
        addKeyListener(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.addKeyListener(this);
        gameTimer = new Timer(delay, e -> {
            if (gameStarted) {
                gameController.update();
                gamePanel.repaint();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gameTimer.start();
    }

    private void showStartScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.RED);
        Font font = new Font("Comic Sans MS", Font.BOLD, 24);
        g.setFont(font);

        String welcomeText = "Welcome to Galaga!";
        String startText = "Press any key to start";

        int welcomeTextWidth = g.getFontMetrics(font).stringWidth(welcomeText);
        int startTextWidth = g.getFontMetrics(font).stringWidth(startText);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        g.drawString(welcomeText, centerX - welcomeTextWidth / 2, centerY - 50);
        g.drawString(startText, centerX - startTextWidth / 2, centerY + 50);

        if (!gameStarted) {
            String heroName = JOptionPane.showInputDialog(this, "Enter Hero Name:");
            if (heroName != null) {
                gameController.setUserName(heroName);
                gameStarted = true;
                gamePanel.requestFocus();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameStarted) {
            gameStarted = true;
        } else {
            int keyCode = e.getKeyCode();
            int dx = 0, dy = 0;
            switch(keyCode) {
                case KeyEvent.VK_W: dy = -speed; break;
                case KeyEvent.VK_A: dx = -speed; break;
                case KeyEvent.VK_S: dy = speed; break;
                case KeyEvent.VK_D: dx = speed; break;
                case KeyEvent.VK_H: gameController.heroShoot(); break;
                case KeyEvent.VK_P: gameController.togglePause(); break;
                default: break;
            }
            gameController.moveHero(dx, dy, 2);
        }
    }
    @Override public void keyReleased(KeyEvent e) { }
    @Override public void keyTyped(KeyEvent e) { }

    public static void startGame(){
        new GameView();
    }
}