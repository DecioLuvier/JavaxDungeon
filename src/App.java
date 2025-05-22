import javax.swing.*;

import game.engine.Manager;

class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setSize(1280, 720);

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Manager(frame);
    }
}