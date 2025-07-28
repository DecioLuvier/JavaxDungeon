import javax.swing.*;

import engine.Manager;

class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaxDungeon");
        frame.setSize(664, 900);
        frame.setResizable(false);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new Manager(frame);

        frame.setVisible(true);
    }
}