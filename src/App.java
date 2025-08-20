import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import engine.Manager;
import game.Surfaces.gameboy.Gameboy;
import game.Surfaces.intro.Intro;

class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaxDungeon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(664, 745);
        frame.setResizable(false);

        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                Manager.get().draw(graphics);
            }
        };
        gamePanel.setDoubleBuffered(true);

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                Manager.get().onReleasedKey(e);
            }
        });

        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                gamePanel.requestFocusInWindow();
                Manager.get().onClick(e);
            }
        });

        Timer timer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.get().onTick();
                gamePanel.repaint();
            }
        });
        timer.start();

        frame.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        Manager manager = Manager.get();
        Intro intro = new Intro();
        Gameboy gameboy = new Gameboy();
        manager.add(intro, 60, 60, 0);
        manager.add(gameboy, 0, 0, 1);
  

        frame.setVisible(true);     
    }
}