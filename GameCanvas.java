import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;

class GameCanvas extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    public int playerX = 200;
    public int playerY = 400;
    private int velocityX = 2;
    private int civilianX = 100;
    private int civilianY = 0;
    private int civilianSpeed = 4;
    private int score = 0;
    private int lives = 3;
    private boolean isGameOver = false;
    private int gameState = 0; // 0: Start, 1: Playing, 2: Game Over

    public GameCanvas() {
        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        timer = new Timer(16, this); // Approximately 60 FPS
        timer.start();
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameState == 0) {
            g.setColor(new Color(15, 15, 20)); // Dark Gotham night background
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 32));
            g.drawString("BAT CAVE ARCADE", 95, 180);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Use LEFT / RIGHT arrows to steer", 125, 240);

            g.setColor(Color.CYAN);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            g.drawString("Press ENTER to Launch", 145, 310);
        } else if (gameState == 2) {
            if (isGameOver) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 36));
                g.drawString("Mission Failed", 135, 250);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 20));
                g.drawString("Civilians Saved: " + score, 180, 290);
                g.drawString("Gotham has fallen, sir.", 160, 320);
            }
        } else if (gameState == 1) {
            g.setColor(Color.YELLOW);
            g.fillRect(playerX, playerY, 50, 20);

            g.setColor(Color.CYAN);
            g.fillRect(civilianX, civilianY, 30, 30);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Civilians Saved: " + score + " | Lives: " + lives, 20, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (gameState == 1) {

            if (playerX < 0 || playerX > getWidth() - 50) {
                velocityX = -velocityX; // Reverse direction on hitting the edge
            }

            civilianY += civilianSpeed;
            Rectangle playerRect = new Rectangle(playerX, playerY, 50, 20);
            Rectangle civilianRect = new Rectangle(civilianX, civilianY, 30, 30);

            if (playerRect.intersects(civilianRect)) {
                // System.out.println("Alfred: Civilian secured, sir");
                score++;
                // playerX = 200;
                // playerY = 400;
                civilianY = 0;
                civilianX = (int) (Math.random() * (getWidth() - 50));

            }
            if (civilianY > 420) {
                lives--;
                civilianY = 0;
                civilianX = (int) (Math.random() * 450);
                if (lives <= 0) {
                    isGameOver = true;
                    gameState = 2; // Switch to game over state
                    // System.out.println("Alfred: Critical failure. Gotham has fallen.");
                    timer.stop();
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (gameState == 0 && key == KeyEvent.VK_ENTER) {
            gameState = 1; // Start the game when any key is pressed
        }
        if (gameState == 2 && key == KeyEvent.VK_ENTER) {
            // Reset the game
            score = 0;
            lives = 3;
            isGameOver = false;
            civilianY = 0;
            civilianX = (int) (Math.random() * (getWidth() - 50));
            gameState = 0; // Go back to start state
            timer.start();
        }

        if (gameState == 1) {
            if (key == KeyEvent.VK_LEFT) {
                playerX -= 15;
                if (playerX < 0) {
                    playerX = 0; // Prevent moving out of bounds
                }
            }
            if (key == KeyEvent.VK_RIGHT) {
                playerX += 15;
                if (playerX > getWidth() - 50) {
                    playerX = getWidth() - 50; // Prevent moving out of bounds
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No action needed on key release for now
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No action needed on key typed for now
    }

}
