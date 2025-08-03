package game.surfaces.battle;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

import engine.Manager;
import engine.actors.TextActor;
import engine.actors.VisualActor;
import engine.graphic.Animation;
import engine.graphic.SpriteSheet;
import engine.surfaces.Surface;
import game.Pokemon;
import game.Trainer;
import game.data.Move;
import game.data.Type;
import game.surfaces.board.Floor;

public class Battle extends Surface {
    private static SpriteSheet background = new SpriteSheet("src/game/surfaces/battle/Background.png", 1, 0, 0, 0);
    private static SpriteSheet score = new SpriteSheet("src/game/surfaces/menu/Score.png", 1, 0, 0, 0);

    private static final int TURN_DURATION = 60; // Number of ticks per turn (e.g., 60 ticks = 1 second at 60 FPS)
    private int turnTimer = 0; // Tracks ticks in the current turn

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

    private TextActor teste;
    private TextActor teste2;


    public Battle(int offsetX, int offsetY, int offsetZ, Trainer trainer, Pokemon pokemon, Floor floor) {
        super(offsetX, offsetY, offsetZ);
        this.trainer = trainer;
        this.opponentPokemon = pokemon;
        this.floor = floor;   

        opponentNameText = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        opponentLevelText = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        opponentHpText = new TextActor("", 19, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainNameText = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainLevelText = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainHpText = new TextActor("", 19, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainFastMove = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        mainChargedMove = new TextActor("", 24, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        teste = new TextActor("Waiting for action...", 22, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
        teste2 = new TextActor("", 22, "src/game/FontPKMN.ttf", new Color(0, 0, 0));
    }

    @Override
    public void onCreate(Manager manager) {
        // STATIC GUI
        addActor(manager, new VisualActor(new Animation(background, 1, 1)), 70, 172, 0);

        // GUI
        addActor(manager, opponentNameText, 106, 153, 5);
        addActor(manager, opponentLevelText, 280, 153, 5);
        addActor(manager, opponentHpText, 163, 185, 5);

        addActor(manager, mainNameText, 356, 370, 5);
        addActor(manager, mainLevelText, 530, 370, 5);
        addActor(manager, mainHpText, 389, 401, 5);
        
        addActor(manager, teste, 80, 75, 5);
        addActor(manager, teste2, 80, 100, 5);
        // 

        addActor(manager, new VisualActor(new Animation(score, 1, 1)), 59, 59, 1);

        setOpponent(manager, opponentPokemon);
        setMain(manager, trainer.getPokemon(0));
        setBench(manager, trainer.getPokemon(1));

        super.onCreate(manager);
    }

    @Override
    public void onPressedKey(Manager manager, KeyEvent event) {
        Pokemon mainPokemon = trainer.getPokemon(0);
        if (mainPokemon.getCurrentCD() == 0) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_F: 
                    Move fastMove = mainPokemon.getStats().getFastMove();
                    attack(manager, mainPokemon, opponentPokemon, fastMove, opponentHpText);
                    break;
                case KeyEvent.VK_C: 
                    Move chargedMove = mainPokemon.getStats().getChargedMove();
                    if (mainPokemon.getCurrentMP() >= chargedMove.getEnergy()) 
                        attack(manager, mainPokemon, opponentPokemon, chargedMove, opponentHpText);
                    break;
            }
            turnTimer = 0;
        }
        super.onPressedKey(manager, event);
    }

    @Override
    public void onTick(Manager manager) {
        turnTimer++; 
        if (turnTimer >= TURN_DURATION) {
            turnTimer = 0; 

            Pokemon mainPokemon = trainer.getPokemon(0); 

            if (mainPokemon.getCurrentCD() > 0) {
                mainPokemon.setCurrentCD(mainPokemon.getCurrentCD() - 1);
                if (opponentPokemon.getCurrentCD() > 0)
                    opponentPokemon.setCurrentCD(opponentPokemon.getCurrentCD() - 1);

                if (opponentPokemon.getCurrentCD() == 0) {
                    Move chargedMove = opponentPokemon.getStats().getChargedMove();
                    Move fastMove = opponentPokemon.getStats().getFastMove();
                    if (opponentPokemon.getCurrentMP() >= chargedMove.getEnergy())
                        attack(manager, opponentPokemon, mainPokemon, chargedMove, mainHpText);
                    else 
                        attack(manager, opponentPokemon, mainPokemon, fastMove, mainHpText);
                }
            }
            else{
                teste.setText("Waiting for action...");
                teste2.setText("");
            }
        }
        super.onTick(manager);
    }

    public void swapMainWithBench(Manager manager) {
        trainer.swapPokemon(0, 1);
        setMain(manager, trainer.getPokemon(0));
        setBench(manager, trainer.getPokemon(1));
    }

    public void setOpponent(Manager manager, Pokemon pokemon) {
        setActorPosition(pokemon, 330, 150, 1);
        pokemon.getAnimation().setXScale(4);
        pokemon.getAnimation().setYScale(4);
        pokemon.getAnimation().setHorizontalOrientation(true);
        opponentNameText.setText(pokemon.getStats().getName());
        opponentLevelText.setText(":" + pokemon.getLevel());
        opponentHpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
    }

    public void setMain(Manager manager, Pokemon pokemon) {
        setActorPosition(pokemon, 90, 220, 1);
        pokemon.getAnimation().setXScale(4);
        pokemon.getAnimation().setYScale(4);
        pokemon.getAnimation().setHorizontalOrientation(false);
        mainNameText.setText(pokemon.getStats().getName());
        mainLevelText.setText(":" + pokemon.getLevel());
        mainHpText.setText(pokemon.getCurrentHP() + "/" + pokemon.getHp());
        mainFastMove.setText(pokemon.getStats().getFastMove().getName());
        mainChargedMove.setText(pokemon.getStats().getChargedMove().getName());
    }

    public void setBench(Manager manager, Pokemon pokemon) {
        setActorPosition(pokemon, 87, 463, 1);
        pokemon.getAnimation().setXScale(2);
        pokemon.getAnimation().setYScale(2);
        pokemon.getAnimation().setHorizontalOrientation(false);
    }

    public void attack(Manager manager, Pokemon attacker, Pokemon target, Move move, TextActor targetHpText) {
        int damage = calculateDamage(attacker, target, move);
        int newHP = Math.max(0, target.getCurrentHP() - damage);
        target.setCurrentHP(newHP);
        targetHpText.setText(target.getCurrentHP() + "/" + target.getHp());


        teste.setText(attacker.getStats().getName() + " used " + move.getName() + ".");
        teste2.setText("Dealing " + damage + " damage.");


        System.out.println(attacker.getStats().getName() + " used " + move.getName() + " on " + target.getStats().getName() + ", dealing " + damage + " damage. " + target.getStats().getName() + " HP: " + newHP + "/" + target.getHp());

        if (move.isCharged()) 
            attacker.setCurrentMP(attacker.getCurrentMP() - move.getEnergy());
        else 
            attacker.setCurrentMP(attacker.getCurrentMP() + move.getEnergy());

        attacker.setCurrentCD(move.getCooldown());
    }

    public static void tryCapture(Manager manager, Trainer trainer, Pokemon target) {
        double captureChance = calculateCaptureChance(target);        
        Random random = manager.getRandom();

        // End Battle
        if (random.nextDouble() <= captureChance) {
            // Capture logic here
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
        String attackTypeName = attack.getName();
        double multiplierMain = defenderMain.getResistances().getOrDefault(attackTypeName, 1.0);
        double multiplierSecondary = defenderSecondary.getResistances().getOrDefault(attackTypeName, 1.0);
        return multiplierMain * multiplierSecondary;
    }

    public static double calculateCaptureChance(Pokemon pokemon) {
        int HPmax = pokemon.getHp();
        int HPcurrent = pokemon.getCurrentHP();
        double catchRate = pokemon.getStats().getCatchRate();

        return Math.min(1.0, (((3.0 * HPmax - 2.0 * HPcurrent) / (3.0 * HPmax)) * catchRate) / 255.0);
    }
}