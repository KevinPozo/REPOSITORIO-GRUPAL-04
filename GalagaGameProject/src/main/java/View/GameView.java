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

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

public class GameView extends JFrame implements KeyListener {
    private JPanel gamePanel;
    private GameController gameController;
    private Timer gameTimer;
    private int delay = 1000 / 60; 
    private int speed = 6;
    public GameView() {
        setTitle("Galaga Game Group 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);
                gameController.render(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel);
        gameController = new GameController();
        addKeyListener(this);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.addKeyListener(this);
        gameTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.update();
                gamePanel.repaint();
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        gameTimer.start();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int dx = 0, dy = 0;
        switch(keyCode) {
            case KeyEvent.VK_W: 
                dy = -speed;
                break;
            case KeyEvent.VK_A: 
                dx = -speed;
                break;
            case KeyEvent.VK_S: 
                dy = speed;
                break;
            case KeyEvent.VK_D: 
                dx = speed;
                break;
            case KeyEvent.VK_H:
                gameController.heroShoot();
                break;
            default:
                break;
        }
        gameController.moveHero(dx, dy,2);
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    public static void main(String[] args) {
        new GameView();
    }
}
