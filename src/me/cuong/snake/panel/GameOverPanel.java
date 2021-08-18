package me.cuong.snake.panel;

import me.cuong.snake.frame.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {
    Frame frame;
    int frameWidth, frameHeight, score;

    public GameOverPanel(Frame f, int w, int h, int s) {
        this.frame = f;
        this.frameWidth = w;
        this.frameHeight = h;
        this.score = s;

        if(score > frame.getHighScore()){
            frame.setHighScore(s);
        }

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setLayout(null);
        this.setBackground(Color.black);

        addPlayAgainButton();
    }

    private void addPlayAgainButton() {
        JButton b = new JButton("Play Again");
        b.setBackground(Color.green.darker());
        b.setBorder(BorderFactory.createBevelBorder(0));
        b.setFocusPainted(false);
        b.setFont(new Font("Ink Free", Font.BOLD, 18));
        b.setBounds((frameWidth / 2) - (350 / 2), this.frameHeight / 2 + 100, 350, 30);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.playAgain();
            }
        });
        this.add(b);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics m = g.getFontMetrics(g.getFont());
        g.drawString("Game Over!", (frameWidth - m.stringWidth("Game Over!")) / 2, frameHeight / 2);

        String text = "Your Score: " + score + " | Highscore: " + frame.getHighScore();

        g.setFont(new Font("Ink Free", Font.BOLD, 35));
        FontMetrics m2 = g.getFontMetrics(g.getFont());
        g.drawString(text, (frameWidth - m2.stringWidth(text)) / 2, frameHeight / 2 + 40);

    }
}
