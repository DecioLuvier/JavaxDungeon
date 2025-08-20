package game.Surfaces.battle;

import engine.Manager;
import engine.listeners.Actor;
import engine.listeners.Surface;
import game.Pokemon;
import game.Trainer;
import game.Tile;
import game.Surfaces.battle.surfaces.*;
import game.Surfaces.world.World;
import game.datas.Move;

public class Battle extends Surface {
    //Game-Related
    private World world;
    private Trainer trainer;
    private Pokemon opponentPokemon;
    private int DELAY_TICKS = 60;
    private int turnCD;
    //Surfaces
    private final ActionTexts actionTexts = new ActionTexts();
    private final ActionMenu actionMenu = new ActionMenu(this);
    //Actors
    private final Actor mainBattleSprite  = new Actor.Builder().scale(-4, 4).build();
    private final PokemonInfo mainPokemonInfo = new PokemonInfo();
    private final Actor opponentBattleSprite  = new Actor.Builder().scale(4, 4).build();
    private final PokemonInfo opponentPokemonInfo = new PokemonInfo();

    public Battle(World world, Trainer trainer, Pokemon opponentPokemon) {
        super(new Builder());
        this.world = world;
        this.trainer = trainer;
        this.opponentPokemon = opponentPokemon;

        add(actionMenu, 13, 385, 0);
        add(actionTexts, 13, 0, 0);

        add(mainBattleSprite, 21, 161, 0);
        add(mainPokemonInfo, 271, 350, 0);
        add(opponentPokemonInfo, 19, 126, 0);
        add(opponentBattleSprite, 270, 96, 0);

        actionTexts.setTopText(ActionTexts.getEncounterText(opponentPokemon));
        actionTexts.setBottomText(" ");

        opponentPokemon.setCurrentCD(1);
        getMainPokemon().setCurrentCD(1);

        actionMenu.update();
        update();
    }

    public Pokemon getMainPokemon() { return trainer.getPokemon(0); }
    public Trainer getTrainer() { return trainer; }
    public ActionMenu getActionMenu() { return actionMenu; }
    public Pokemon getOpponentPokemon() { return opponentPokemon; }
    public PokemonInfo getOpponentPokemonInfo() { return opponentPokemonInfo; }
    public ActionTexts getActionTexts() { return actionTexts; }

    public void update() {
        Pokemon mainPokemon = trainer.getPokemon(0);
        mainPokemonInfo.setPokemon(mainPokemon);
        mainBattleSprite.setSprite(mainPokemon.getStats().getSpriteSheet());
        opponentPokemonInfo.setPokemon(opponentPokemon);
        opponentBattleSprite.setSprite(opponentPokemon.getStats().getSpriteSheet());
    }

    public void swap(int partyIndex) {
        if (trainer == null || partyIndex <= 0)
            return;
        if (trainer.getPokemon(0).getCurrentCD() != 0)
            return;
        Pokemon pokemon = trainer.getPokemon(partyIndex);
        if (pokemon == null || pokemon.getCurrentHP() <= 0)
            return;
        trainer.swapPokemon(0, partyIndex);
        Pokemon mainPokemon = trainer.getPokemon(0);
        mainPokemon.setCurrentCD(1);
        mainBattleSprite.setSprite(mainPokemon.getStats().getSpriteSheet());
        mainPokemonInfo.setPokemon(mainPokemon);
        actionMenu.update();

        actionMenu.setVisible(true);
    }

    public void exit() {
        for (Pokemon pokemon : trainer.getTeam()) {
            pokemon.setCurrentCD(0);
            pokemon.setCurrentMP(0);
            pokemon.setCurrentHP(pokemon.getHp());
        }

        opponentPokemon.setCurrentCD(0);
        opponentPokemon.setCurrentMP(0);
        opponentPokemon.setCurrentHP(opponentPokemon.getHp());
        if (!trainer.isNpc())
            world.setVisible(true);

        Manager.get().remove(this);
        trainer.setOnBattle(false);
        opponentPokemon.setOnBattle(false);

        world.getBoard().update();
        world.getScore().update();
        world.getParty().update();
    }

    public static void attack(Pokemon attacker, Pokemon target, Move move) {
        if (move.isCharged())
            attacker.setCurrentMP(attacker.getCurrentMP() - move.getEnergy());
        else
            attacker.setCurrentMP(attacker.getCurrentMP() + move.getEnergy());
        attacker.setCurrentCD(move.getCooldown() + 1);

        int damage = Pokemon.calculateDamage(attacker, target, move);
        int newHP = Math.max(0, target.getCurrentHP() - damage);
        target.setCurrentHP(newHP);
    }

    public void capture() {
        Pokemon mainPokemon = trainer.getPokemon(0);
        if (mainPokemon.getCurrentCD() > 0)
            return;

        mainPokemon.setCurrentCD(1);

        if (opponentPokemon.getTrainer() != null) {
            actionTexts.setTopText(String.format("%s already has a trainer!", opponentPokemon.getStats().getName()));
            return;
        }

        double captureChance = Pokemon.calculateCaptureChance(opponentPokemon);
        double randomValue = Manager.get().getRandom().nextDouble();
        if (randomValue > captureChance) {
            actionTexts.setTopText(ActionTexts.getCaptureFailureMessage(opponentPokemon, captureChance, randomValue));
        } else {
            trainer.addPokemon(opponentPokemon);
            actionTexts.setTopText(ActionTexts.getCaptureMessage(opponentPokemon));
            actionTexts.setBottomText(String.format(""));
            // Remove the captured Pokémon from the world tile
            synchronized (world.getTiles()) {
                for (Tile tile : world.getTiles()) {
                    if (tile.getPokemon() == opponentPokemon) {
                        tile.setPokemon(null);
                        break;
                    }
                }
            }
            world.getBoard().update();
        }
    }

    @Override
    public void onTick() {
        if (turnCD == DELAY_TICKS) {
            turnCD = 0;
            if (opponentPokemon.getCurrentHP() == 0) {
                for (Pokemon pokemon : trainer.getTeam())
                    pokemon.addExperience(Pokemon.calculateXP(opponentPokemon));
                // Remove the knocked-out Pokémon from the world tile
                synchronized (world.getTiles()) {
                    for (Tile tile : world.getTiles()) {
                        if (tile.getPokemon() == opponentPokemon) {
                            tile.setPokemon(null);
                            break;
                        }
                    }
                }
                exit();
                return;
            }

            if (opponentPokemon.getTrainer() == trainer) {
                exit();
                return;
            }

            Pokemon mainPokemon = trainer.getPokemon(0);

            if (mainPokemon.getCurrentHP() == 0) {
                for (int i = 1; i < trainer.getTeam().size(); i++) {
                    Pokemon potentialSwap = trainer.getPokemon(i);
                    if (potentialSwap != null && potentialSwap.getCurrentHP() > 0) {
                        swap(i);
                        break;
                    }
                }
                if (trainer.getPokemon(0).getCurrentHP() == 0) {
                    exit();
                    return;
                }
            }

            if (mainPokemon.getCurrentCD() > 0)
                mainPokemon.setCurrentCD(mainPokemon.getCurrentCD() - 1);
            if (opponentPokemon.getCurrentCD() > 0)
                opponentPokemon.setCurrentCD(opponentPokemon.getCurrentCD() - 1);

            actionTexts.setTopText(ActionTexts.getActionText(mainPokemon));
            actionTexts.setBottomText(ActionTexts.getActionText(opponentPokemon));

            if (trainer.isNpc()) {
                double randomValue = Manager.get().getRandom().nextDouble();
                if (randomValue < 0.5 && mainPokemon.getCurrentCD() == 0) {
                    capture();
                } else if (mainPokemon.getCurrentCD() == 0) {
                    Move chargedMove = mainPokemon.getStats().getChargedMove();
                    Move fastMove = mainPokemon.getStats().getFastMove();
                    if (mainPokemon.getCurrentMP() >= chargedMove.getEnergy()) {
                        attack(mainPokemon, opponentPokemon, chargedMove);
                        actionTexts.setBottomText(ActionTexts.getAttackText(mainPokemon, chargedMove));
                    } else {
                        attack(mainPokemon, opponentPokemon, fastMove);
                        actionTexts.setBottomText(ActionTexts.getAttackText(mainPokemon, fastMove));
                    }
                }
                opponentPokemonInfo.setPokemon(opponentPokemon);
            }

            if (opponentPokemon.getCurrentCD() == 0) {
                Move chargedMove = opponentPokemon.getStats().getChargedMove();
                Move fastMove = opponentPokemon.getStats().getFastMove();
                if (opponentPokemon.getCurrentMP() >= chargedMove.getEnergy()) {
                    attack(opponentPokemon, mainPokemon, chargedMove);
                    actionTexts.setBottomText(ActionTexts.getAttackText(opponentPokemon, chargedMove));
                } else {
                    attack(opponentPokemon, mainPokemon, fastMove);
                    actionTexts.setBottomText(ActionTexts.getAttackText(opponentPokemon, fastMove));
                }
                mainPokemonInfo.setPokemon(mainPokemon);
            }
        }
        turnCD++;
        super.onTick();
    }
}