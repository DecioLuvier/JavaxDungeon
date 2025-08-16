package engine;

import java.util.ArrayList;
import java.util.List;

import engine.actors.Actor;

public class Surface {
      private List<Actor> actors;
      private boolean visible;
      private int x;
      private int y;
      private int z;

      public Surface() {
          this.actors = new ArrayList<>();
          this.visible = true;
          this.x = 0;
          this.y = 0;
          this.z = 0;
      }

      public void addActor(Actor actor, int x, int y, int z) {
          actor.setPosition(this, x, y, z);
          actors.add(actor);
      }

      public void removeActor(Actor actor) { 
          actors.remove(actor); 
      }

      public List<Actor> getActors() { 
          return new ArrayList<>(actors); 
      }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
    public boolean getVisible() { return visible; }

    public void setVisible(boolean visible) { this.visible = visible; }
    public void setPosition(int x, int y, int z) {
        int deltaX = x - this.x;
        int deltaY = y - this.y;
        int deltaZ = z - this.z;
        for (Actor actor : this.getActors()) 
            actor.setPosition(actor.getX() + deltaX, actor.getY() + deltaY, actor.getZ() + deltaZ);
        this.x = x;
        this.y = y;
        this.z = z;
    }
}