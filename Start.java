import javax.swing.SwingUtilities;

public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameWindow();
        });

        /*
         * JFrame frame = new JFrame("Bat Cave Arcade");
         * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * 
         * GameCanvas canvas = new GameCanvas();
         * frame.add(canvas);
         * frame.pack();
         * frame.setLocationRelativeTo(null);
         * frame.setVisible(true);
         */

    }
}
