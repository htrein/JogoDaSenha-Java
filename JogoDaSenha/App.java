import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
                game.start();
            }
        });
    }
}