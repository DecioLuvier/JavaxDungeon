package game.prefabs.characters;

import game.assets.Character;

public class Wall extends Character {
    private static String SPRITE_PATH = "src/game/prefabs/sprites/wall.png";
    private static int SPRITE_DEPTH = 2;

    public Wall(int row, int col) {
        super(SPRITE_PATH, SPRITE_DEPTH, row, col);
    }
}
