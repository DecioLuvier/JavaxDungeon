package engine.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class Listener implements Serializable {
    public void onReleasedKey(KeyEvent event) {}
    public void onClick(MouseEvent event) {}
    public void onTick() {}
}