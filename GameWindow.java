import javax.swing.JFrame;

public class GameWindow extends JFrame {

    public GameWindow() {
        setTitle("Batman: The Gotham Rescue");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameCanvas canvas = new GameCanvas();
        add(canvas);
        pack();
        setVisible(true);

    }

}
