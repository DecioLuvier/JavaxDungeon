package game.prefabs.characters;

import game.assets.Character;

public class Ground extends Character {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/ground.png";
    private static int SPRITE_DEPTH = 0;

    public Ground(int row, int col) {
        super(SPRITE_PATH, SPRITE_DEPTH, row, col);
    }
}
