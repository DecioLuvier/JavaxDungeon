package Game.Prefabs.Characters;

import Game.Assets.Character;

public class Ground extends Character {
    private static String SPRITE_PATH = "src/game/assets/sprites/ground.png";
    private static int SPRITE_DEPTH = 0;

    public Ground(int row, int col) {
        super(SPRITE_PATH, SPRITE_DEPTH, row, col);
    }
}
