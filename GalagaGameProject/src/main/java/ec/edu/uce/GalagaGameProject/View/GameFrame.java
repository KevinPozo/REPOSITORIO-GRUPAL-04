package ec.edu.uce.GalagaGameProject.View;

import ec.edu.uce.GalagaGameProject.Controller.GameContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class GameFrame extends JFrame implements KeyListener {
    private JPanel gamePanel;
    private final GameContainer gameContainer;
    private Timer gameTimer;
    private final int delay = 1000 / 60;
    private final int speed = 5;

    @Autowired
    public GameFrame(GameContainer gameContainer) {
        this.gameContainer = gameContainer;

        setTitle("Galaga Game KevinPozo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.BLACK);
                gameContainer.render(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(800, 600));
        add(gamePanel);

        addKeyListener(this);

        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        gamePanel.addKeyListener(this);

        gameTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameContainer.update();
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
                gameContainer.heroShoot();
                break;
            default:
                break;
        }

        gameContainer.moveHero(dx, dy);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
