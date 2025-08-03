import java.awt.BorderLayout;
import javax.swing.JFrame;

import engine.Manager;

class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaxDungeon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(664, 900);
        frame.setResizable(false);

        Manager camera = new Manager(12);
        frame.add(camera, BorderLayout.CENTER);

        frame.setVisible(true);     
        camera.requestFocusInWindow();
    }
}