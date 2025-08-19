import java.awt.BorderLayout;
import javax.swing.JFrame;

import engine.Manager;
import engine.actors.Room;
import game.actors.Gameboy;
import game.actors.Intro;

class App {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JavaxDungeon");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(664, 900);
        frame.setResizable(false);
        frame.add(Manager.get(), BorderLayout.CENTER);


        Intro intro = new Intro(new Room.Builder());
        Gameboy gameboy = new Gameboy(new Room.Builder());

        Room mainRoom = Manager.get().getRoom();
        mainRoom.addActor(intro, 60, 60, 0);
        mainRoom.addActor(gameboy, 0, 0, 1);
        //mainRoom.addActor(world, 59, 59, 0);
        //mainRoom.addActor(battle, 60, 60, 0);

        frame.setVisible(true);     
    }
}