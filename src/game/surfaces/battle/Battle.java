package game.surfaces.battle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import engine.Room;
import engine.Surface;
import engine.actors.TextActor;
import engine.actors.VisualActor;
import engine.graphics.Animation;
import engine.graphics.SpriteSheet;
import game.pokemon.Pokemon;
import game.pokemon.Type;
import game.surfaces.board.Floor;
import game.trainer.Trainer;
import game.pokemon.Move;

public class Battle extends Surface {
    private static SpriteSheet background = new SpriteSheet("src/game/surfaces/battle/Background.png", 1, 0, 0, 0);

    private Trainer trainer;
    private Floor floor;

    private TextActor opponentNameText;
    private TextActor opponentLevelText;
    private TextActor opponentHpText;
    private Pokemon opponentPokemon;

    private TextActor mainNameText;
    private TextActor mainLevelText;
    private TextActor mainHpText;
    private TextActor mainFastMove;
    private TextActor mainChargedMove;

    public Battle(int offsetX, int offsetY, int offsetZ, Trainer trainer, Pokemon pokemon, Floor floor) {
        super(offsetX, offsetY, offsetZ);
        this.trainer = trainer;
        this.opponentPokemon = pokemon;
        this.floor = floor;   

        opponentNameText = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        opponentLevelText = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        opponentHpText = new TextActor("",19, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainNameText = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainLevelText = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainHpText = new TextActor("",19, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainFastMove = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainChargedMove = new TextActor("",24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
    }

    @Override
    public void onCreate(Room room) {
        // STATIC GUI
        create(room, new VisualActor(new Animation(background, 1, 1)), 70, 148, 0);

        // GUI
        create(room, opponentNameText, 106, 103, 5);
        create(room, opponentLevelText, 206, 128, 5);
        create(room, opponentHpText, 163, 160, 5);

        create(room, mainNameText, 356, 338, 5);
        create(room, mainLevelText, 456, 363, 5);
        create(room, mainHpText, 389, 396, 5);
        
        // 

        setOpponent(room, opponentPokemon);
        setMain(room, trainer.getPokemon(0));
        setBench(room, trainer.getPokemon(1));

        super.onCreate(room);
    }

    @Override
    public void onPressedKey(Room room, KeyEvent event) {
        Pokemon mainPokemon = trainer.getPokemon(0);
        if(mainPokemon.getCurrentCD() == 0){
            switch (event.getKeyCode()) {
                case KeyEvent.VK_F: 
                    Move fastMove = mainPokemon.getStats().getFastMove();
                    attack(room, mainPokemon, opponentPokemon, fastMove, opponentHpText);
                    break;
                case KeyEvent.VK_C: 
                    Move chargedMove = mainPokemon.getStats().getChargedMove();
                    if (mainPokemon.getCurrentMP() >= chargedMove.getEnergy()) 
                        attack(room, mainPokemon, opponentPokemon, chargedMove, opponentHpText);
                    break;
            }
        }
        super.onPressedKey(room, event);
    }

    @Override
    public void onTick(Room room) {
        Pokemon mainPokemon = trainer.getPokemon(0); 
        if(mainPokemon.getCurrentCD() > 0){
            mainPokemon.setCurrentCD(mainPokemon.getCurrentCD() - 1);
            if(opponentPokemon.getCurrentCD() > 0)
                opponentPokemon.setCurrentCD(opponentPokemon.getCurrentCD() - 1);

            if(opponentPokemon.getCurrentCD() == 0){
                Move chargedMove = opponentPokemon.getStats().getChargedMove();
                Move fastMove = opponentPokemon.getStats().getFastMove();
                if (opponentPokemon.getCurrentMP() >= chargedMove.getEnergy())
                    attack(room, opponentPokemon, mainPokemon, chargedMove, mainHpText);
                else 
                    attack(room, opponentPokemon, mainPokemon, fastMove, mainHpText);
            }
        }
        super.onTick(room);
    }

    public void swapMainWithBench(Room room) {
        trainer.swapPokemon(0, 1);
        setMain(room, trainer.getPokemon(0));
        setBench(room, trainer.getPokemon(1));
    }

    public void setOpponent(Room room, Pokemon pokemon) {
        move(room, pokemon, 350, 85, 1);
        pokemon.getAnimation().setXScale(4);
        pokemon.getAnimation().setYScale(4);
        pokemon.getAnimation().setHorizontalOrientation(true);
        opponentNameText.setText(pokemon.getStats().getName());
        opponentLevelText.setText(":" + pokemon.getLevel());
        opponentHpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
    }

    public void setMain(Room room, Pokemon pokemon) {
        move(room, pokemon, 90, 220, 1);
        pokemon.getAnimation().setXScale(4);
        pokemon.getAnimation().setYScale(4);
        pokemon.getAnimation().setHorizontalOrientation(false);
        mainNameText.setText(pokemon.getStats().getName());
        mainLevelText.setText(":" + pokemon.getLevel());
        mainHpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
        mainFastMove.setText(pokemon.getStats().getFastMove().getName());
        mainChargedMove.setText(pokemon.getStats().getChargedMove().getName());
    }

    public void setBench(Room room, Pokemon pokemon) {
        move(room, pokemon, 87, 463, 1);
        pokemon.getAnimation().setXScale(2);
        pokemon.getAnimation().setYScale(2);
        pokemon.getAnimation().setHorizontalOrientation(false);
    }

    public static void attack(Room room, Pokemon attacker, Pokemon target, Move move, TextActor targetHpText) {
        int damage = calculateDamage(attacker, target, move);
        int newHP = Math.max(0, target.getCurrentHP() - damage);
        target.setCurrentHP(newHP);
        targetHpText.setText(target.getCurrentHP() + "/" + target.getHp());

        System.out.println(attacker.getStats().getName() + " used " + move.getName() + " on " + target.getStats().getName() + ", dealing " + damage + " damage. " + target.getStats().getName() + " HP: " + newHP + "/" + target.getHp());

        if (move.isCharged()) 
            attacker.setCurrentMP(attacker.getCurrentMP() - move.getEnergy());
        else 
            attacker.setCurrentMP(attacker.getCurrentMP() + move.getEnergy());

        attacker.setCurrentCD(move.getCooldown());
    }

    public static void tryCapture(Room room, Trainer trainer, Pokemon target) {
        double captureChance = calculateCaptureChance(target);        
        Random random = room.getManager().getRandom();

        // End Battle
        if (random.nextDouble() <= captureChance) {

        }
    }

    public static int calculateDamage(Pokemon attacker, Pokemon target, Move move) {
        int power = move.getPower();
        int attackStat = attacker.getAttack();
        int defenseStat = target.getDefense();
        double modifier = calculateTypeMultiplier(move.getType(), target.getStats().getMainType(), target.getStats().getSecondaryType());

        double stab;
        if (move.getType().equals(attacker.getStats().getMainType()) || move.getType().equals(attacker.getStats().getSecondaryType())) 
            stab = 1.2;
        else
            stab = 1.0;

        double trainerBonus;
        if (target.getTrainer() != null) 
            trainerBonus = 1.3;
        else
            trainerBonus = 1.0;

        return (int) (0.5 * power * (attackStat / (double) defenseStat) * modifier * stab * trainerBonus) + 1;
    }

    public static int calculateXP(Pokemon pokemon) {
        int baseXP = pokemon.getStats().getBaseExp(); 
        int level = pokemon.getLevel();

        double trainerBonus;
        if (pokemon.getTrainer() != null) 
            trainerBonus = 1.5;
        else
            trainerBonus = 1.0;

        return (int) Math.round((baseXP * level / 7.0) * trainerBonus);
    }

    public static double calculateTypeMultiplier(Type attack, Type defenderMain, Type defenderSecondary) {
        final double[][] TYPE_EFFECTIVENESS = {
            //NOR,  FIR,  WAT,  ELE,  GRA,  ICE,  FIG,  POI,  GRO,  FLY,  PSY,  BUG,  ROC,  GHO,  DRA,  DAR,  STE,  FAI,  NONE
            { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.63, 0.39, 1.0,  1.0,  0.63, 1.0,  1.0 }, //NORMAL
            { 1.0,  0.63, 0.63, 1.0,  1.6,  1.6,  1.0,  1.0,  1.0,  1.0,  1.0,  1.6,  0.63, 1.0,  0.63, 1.0,  1.6,  1.0,  1.0 }, //FIRE
            { 1.0,  1.6,  0.63, 1.0,  0.63, 1.0,  1.0,  1.0,  1.6,  1.0,  1.0,  1.0,  1.6,  1.0,  0.63, 1.0,  1.0,  1.0,  1.0 }, //WATER
            { 1.0,  1.0,  1.6,  0.63, 0.63, 1.0,  1.0,  1.0,  0.39, 1.6,  1.0,  1.0,  1.0,  1.0,  0.63, 1.0,  1.0,  1.0,  1.0 }, //ELECTRIC
            { 1.0,  0.63, 1.6,  1.0,  0.63, 1.0,  1.0,  0.63, 1.6,  0.63, 1.0,  0.63, 1.6,  1.0,  0.63, 1.0,  0.63, 1.0,  1.0 }, //GRASS
            { 1.0,  0.63, 0.63, 1.0,  1.6,  0.63, 1.0,  1.0,  1.6,  1.6,  1.0,  1.0,  1.0,  1.0,  1.6,  1.0,  0.63, 1.0,  1.0 }, //ICE
            { 1.6,  1.0,  1.0,  1.0,  1.0,  1.6,  1.0,  0.63, 1.0,  0.63, 0.63, 0.63, 1.6,  0.39, 1.0,  1.6,  1.6,  0.63, 1.0 }, //FIGHTING
            { 1.0,  1.0,  1.0,  1.0,  1.6,  1.0,  1.0,  0.63, 0.63, 1.0,  1.0,  1.0,  0.63, 0.63, 1.0,  1.0,  0.39, 1.6,  1.0 }, //POISON
            { 1.0,  1.6,  1.0,  1.6,  0.63, 1.0,  1.0,  1.6,  1.0,  0.39, 1.0,  0.63, 1.6,  1.0,  1.0,  1.0,  1.6,  1.0,  1.0 }, //GROUND
            { 1.0,  1.0,  1.0,  0.63, 1.6,  1.0,  1.6,  1.0,  1.0,  1.0,  1.0,  1.6,  0.63, 1.0,  1.0,  1.0,  0.63, 1.0,  1.0 }, //FLYING
            { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.6,  1.6,  1.0,  1.0,  0.63, 1.0,  1.0,  1.0,  1.0,  0.39, 0.63, 1.0,  1.0 }, //PSYCHIC
            { 1.0,  0.63, 1.0,  1.0,  1.6,  1.0,  0.63, 0.63, 1.0,  0.63, 1.6,  1.0,  1.0,  0.63, 1.0,  1.6,  0.63, 0.63, 1.0 }, //BUG
            { 1.0,  1.6,  1.0,  1.0,  1.0,  1.6,  0.63, 1.0,  0.63, 1.6,  1.0,  1.6,  1.0,  1.0,  1.0,  1.0,  0.63, 1.0,  1.0 }, //ROCK
            { 0.39, 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.63, 1.0,  1.0,  1.6,  1.0,  1.0,  1.6,  1.0,  0.63, 1.0,  1.0,  1.0 }, //GHOST
            { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.6,  1.0,  0.63, 0.39, 1.0 }, //DRAGON
            { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  0.63, 1.0,  1.0,  1.0,  1.6,  1.0,  1.0,  1.6,  1.0,  0.63, 1.0,  0.63, 1.0 }, //DARK
            { 1.0,  0.63, 0.63, 0.63, 1.0,  1.6,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.6,  1.0,  1.0,  1.0,  0.63, 1.6,  1.0 }, //STEEL
            { 1.0,  0.63, 1.0,  1.0,  1.0,  1.0,  1.6,  0.63, 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.6,  1.6,  0.63, 1.0,  1.0 }, //FAIRY
            { 1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0,  1.0 }  //NONE
        };

      double multiplier1 = TYPE_EFFECTIVENESS[attack.ordinal()][defenderMain.ordinal()];
      double multiplier2 = TYPE_EFFECTIVENESS[attack.ordinal()][defenderSecondary.ordinal()];
      return multiplier1 * multiplier2;
    }

    public static double calculateCaptureChance(Pokemon pokemon) {
        int HPmax = pokemon.getHp();
        int HPcurrent = pokemon.getCurrentHP();
        double catchRate = pokemon.getStats().getCatchRate();

        return Math.min(1.0, (((3.0 * HPmax - 2.0 * HPcurrent) / (3.0 * HPmax))  * catchRate) / 255.0);
    }
}