package me.cuong.snake.panel;

import me.cuong.snake.frame.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayPanel extends JPanel implements ActionListener {
    Frame frame;
    int frameWidth, frameHeight;
    int appleX, appleY;

    char direction = 'R';
    int snakeLength = 6;
    int OBJ_SIZE = 20;
    int score = 0;
    int delay = 125;
    boolean isPlaying = false;

    int frameSlot;

    int[] x;
    int[] y;

    Timer t;
    Random r;

    int count = 0;

    public PlayPanel(Frame f, int w, int h) {
        this.frame = f;
        this.frameWidth = w;
        this.frameHeight = h;
        this.frameSlot = (frameWidth * frameHeight)/((int) Math.pow(OBJ_SIZE, 2));
        this.x = new int[frameSlot];
        this.y = new int[frameSlot];
        r = new Random();

        this.setPreferredSize(new Dimension(frameWidth, frameHeight));
        this.setLayout(null);
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new KeyBoard());

        start();
    }

    private void start() {
        locateApple();
        isPlaying = true;
        t = new Timer(delay, this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        if(isPlaying) {
            if(count != 1) {
                count++;
            }

            fixAppleLocate();

            g.setColor(Color.red);
            g.fillOval(appleX, appleY, OBJ_SIZE, OBJ_SIZE);

            for(int i = 0; i < snakeLength; i++) {
                if(i != 0) {
                    g.setColor(Color.green.darker());
                } else {
                    g.setColor(Color.green);
                }

                if((snakeLength % 10 == 0) && (snakeLength != 6)) {
                    t.setDelay(50);
                    g.setColor(new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256)));
                } else {
                    t.setDelay(delay);
                }

                g.fillRect(x[i], y[i], OBJ_SIZE, OBJ_SIZE);

                drawScore(g);
            }
        } else {
            frame.gameOver(new GameOverPanel(frame, frameWidth, frameHeight, score));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isPlaying) {
            move();
            checkCollision();
            checkApple();
        }
        repaint();
    }

    private void locateApple() {
        appleX = r.nextInt((frameWidth / OBJ_SIZE)) * OBJ_SIZE;
        appleY = r.nextInt((frameHeight / OBJ_SIZE)) * OBJ_SIZE;
    }

    private void fixAppleLocate() {
        for(int i = snakeLength; i >= 0; i--) {
            if((appleX == x[i]) && (appleY == y[i])) {
                locateApple();
            }
        }
    }

    private void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            snakeLength ++;
            score ++;
            locateApple();
        }
    }

    private void checkCollision() {
        for(int i = snakeLength; i > 0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i]) && (count != 0)) {
                isPlaying = false;
            }
        }

        if((x[0] < 0) || (x[0] >= frameWidth) || (y[0] < 0) || (y[0] >= frameHeight)) {
            isPlaying = false;
        }

        if(!isPlaying) {
            t.stop();
        }
    }

    private void move() {
        for(int i = snakeLength; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'R':
                x[0] += OBJ_SIZE;
                break;
            case 'L':
                x[0] -= OBJ_SIZE;
                break;
            case 'U':
                y[0] -= OBJ_SIZE;
                break;
            case 'D':
                y[0] += OBJ_SIZE;
                break;
        }
    }

    private void drawSlot(Graphics g) {
        for(int i = 0; i <= (frameHeight / OBJ_SIZE); i++) {
            g.drawLine(0, i * OBJ_SIZE, frameWidth, i * OBJ_SIZE);
        }

        for(int i = 0; i <= (frameWidth / OBJ_SIZE); i++) {
            g.drawLine(i * OBJ_SIZE, 0, i * OBJ_SIZE, frameHeight);
        }
    }

    private void drawScore(Graphics g) {
        String text = "Score: " + score + " | Highscore: " + frame.getHighScore();
        g.setColor(Color.green);
        g.setFont(new Font("Ink Free", Font.BOLD, 45));
        FontMetrics m = getFontMetrics(g.getFont());
        g.drawString(text, (frameWidth - m.stringWidth(text)) / 2, (frameHeight / 2) - 225);
    }

    class KeyBoard extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_A:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
            }
        }
    }
}
