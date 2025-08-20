package engine;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import engine.listeners.Surface;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Manager extends Surface {
    private static Manager instance = null;
    private transient Random random;

    private Manager() {
        super(new Builder().visible(true).position(0, 0, 0)); 
        this.random = new Random();
    }

    public static Manager get() {
        if (instance == null) 
            instance = new Manager();
        return instance;
    }

    public Random getRandom() {
        return this.random;
    }

    public void saveState() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Game State");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Game State Files (*.sav)", "sav"));
        
        int userSelection = fileChooser.showSaveDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".sav")) {
                filePath += ".sav";
            }
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
                out.writeObject(this);
                System.out.println("Game state saved to " + filePath);
            } catch (Exception e) {
                System.err.println("Error saving game state: " + e.getMessage());
            }
        } else {
            System.out.println("Save operation cancelled by user.");
        }
    }

    public static Manager loadState() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Game State");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Game State Files (*.sav)", "sav"));
        
        int userSelection = fileChooser.showOpenDialog(null);
        
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
                Manager loaded = (Manager) in.readObject();
                instance = loaded;
                System.out.println("Game state loaded from " + filePath);
                return instance;
            } catch (Exception e) {
                System.err.println("Error loading game state: " + e.getMessage());
                return get(); 
            }
        } else {
            System.out.println("Load operation cancelled by user.");
            return get();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.random = new Random(); 
    }
}