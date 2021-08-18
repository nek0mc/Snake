package me.cuong.snake.frame;

import me.cuong.snake.panel.GameOverPanel;
import me.cuong.snake.panel.MenuPanel;
import me.cuong.snake.panel.PlayPanel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    int frameWidth = 800;
    int frameHeight = 600;
    int highscore = 0;

    JPanel content;
    CardLayout contentLayout = new CardLayout();

    MenuPanel menu = new MenuPanel(this, frameWidth, frameHeight);
    PlayPanel play;
    GameOverPanel gameOver;

    public Frame() {
        content = new JPanel();
        content.add(menu);
        content.setLayout(contentLayout);
        this.add(content);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void startGame() {
        play = new PlayPanel(this, frameWidth, frameHeight);
        content.add(play);
        contentLayout.next(content);
        content.remove(menu);
        play.requestFocusInWindow();
    }

    public void gameOver(GameOverPanel gameOverPanel) {
        gameOver = gameOverPanel;
        content.add(gameOverPanel);
        contentLayout.next(content);
        content.remove(play);
    }

    public void playAgain() {
        play = new PlayPanel(this, frameWidth, frameHeight);
        content.add(play);
        contentLayout.next(content);
        content.remove(gameOver);
        play.requestFocusInWindow();
    }

    public int setHighScore(int s) {
        return this.highscore = s;
    }

    public int getHighScore() {
        return highscore;
    }
}
