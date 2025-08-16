package game.surfaces.world;

import engine.Surface;
import engine.actors.Button;
import game.Tile;
import game.rooms.World;

public class Board extends Surface {
    private final Button[][] gridButtons  = new Button[8][8];

    public Board(World world) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                final int x = col;
                final int y = row;
                gridButtons[row][col] = new Button(null, () -> world.search(world.getPlayer(), y, x));
                addActor(gridButtons[row][col], col * 56, row * 56, 0);
            }
        }
        update(world);
    };

    public void update(World world) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                gridButtons[row][col].setVisible(false);
                gridButtons[row][col].setSprite(null);
            }
        }

        for (Tile tile : world.getTiles()) {
            Button button = gridButtons[tile.getRow()][tile.getCol()];
            button.setVisible(true);
            button.setSprite(tile.getType().getSprite());
        }
    }
}