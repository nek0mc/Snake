package me.cuong.snake.panel;

import me.cuong.snake.frame.Frame;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    Frame frame;
    int frameWidth, frameHeight;

    public MenuPanel(Frame f, int w, int h) {
        this.frame = f;
        this.frameWidth = w;
        this.frameHeight = h;

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setBackground(Color.black);
        this.setLayout(null);

        drawButton();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        String title = "SNAKE GAME";
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics m = g.getFontMetrics(g.getFont());
        g.drawString(title, (frameWidth - m.stringWidth(title)) / 2, frameHeight / 2);
    }

    private void drawButton() {
        JButton button = new JButton("Start Game!");
        button.setFont(new Font("Ink Free", Font.BOLD, 18));
        button.setBackground(Color.green.darker());
        button.setBorder(BorderFactory.createBevelBorder(0));
        button.setFocusPainted(false);
        button.setBounds((frameWidth / 2) - (350  / 2), frameHeight / 2 + 100, 350, 30);

        button.addActionListener(e -> frame.startGame());

        this.add(button);
    }
}
