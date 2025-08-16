package game.rooms;

import engine.Manager;
import engine.Room;
import game.Pokemon;
import game.Trainer;
import game.datas.Move;
import game.surfaces.Gameboy;
import game.surfaces.battle.ActionInfo;
import game.surfaces.battle.ActionMenu;
import game.surfaces.battle.BattleSprite;
import game.surfaces.battle.MovesMenu;
import game.surfaces.battle.PartyMenu;
import game.surfaces.battle.PokemonInfo;

public class Battle extends Room {
    //Constructor
    private static final int DELAY_TICKS = 60;
    private int turnCD;
    private final Manager manager;
    private final World world;
    private final Trainer trainer;
    private final Pokemon opponentPokemon;

    private final Gameboy gameboy;
    //Surfaces for trainer
    private final PokemonInfo mainPokemonInfo;
    private final BattleSprite mainBattleSprite;
    //Surfaces for opponent
    private final PokemonInfo opponentPokemonInfo;
    private final BattleSprite opponentBattleSprite;
    //Surfaces for trainer menu
    private final ActionInfo actionInfo;
    private final ActionMenu actionMenu;
    private final MovesMenu movesMenu;
    private final PartyMenu partyMenu;


    public Battle(Manager manager, World world, Trainer trainer, Pokemon opponentPokemon) {
        this.manager = manager;
        this.world = world;
        this.trainer = trainer;
        this.opponentPokemon = opponentPokemon;
        //this.turnCD = 0;
        this.gameboy = new Gameboy();
        //Main
        Pokemon mainPokemon = trainer.getPokemon(0);
        this.mainPokemonInfo = new PokemonInfo(mainPokemon);
        this.mainBattleSprite = new BattleSprite(mainPokemon, true);
        //Opponent
        this.opponentPokemonInfo = new PokemonInfo(opponentPokemon);
        this.opponentBattleSprite = new BattleSprite(opponentPokemon, false);
        //Menus
        this.actionInfo = new ActionInfo(mainPokemon, opponentPokemon);
        this.actionMenu = new ActionMenu(this, mainPokemon);
        this.movesMenu = new MovesMenu(this);
        this.partyMenu = new PartyMenu(this);

        addSurface(gameboy, 0, 0, 0);
        addSurface(actionInfo, 73, 64, 0);
        addSurface(mainPokemonInfo, 330, 410, 0);
        addSurface(mainBattleSprite, 80, 220, 0);
        addSurface(opponentPokemonInfo, 78, 185, 0);
        addSurface(opponentBattleSprite, 330, 155, 0);
        addSurface(actionMenu, 73, 445, 0);
        addSurface(movesMenu, 73, 445, 0);
        addSurface(partyMenu, 73, 445, 1);
    }

    public Manager getManager() { return manager; }
    public World getWorld() { return world; }
    public Trainer getTrainer() { return trainer; }
    public Pokemon getOpponentPokemon() { return opponentPokemon; }
    public PokemonInfo getMainPokemonInfo() { return mainPokemonInfo; }
    public BattleSprite getMainBattleSprite() { return mainBattleSprite; }
    public PokemonInfo getOpponentPokemonInfo() { return opponentPokemonInfo; }
    public BattleSprite getOpponentBattleSprite() { return opponentBattleSprite; }
    public ActionInfo getActionInfo() { return actionInfo; }
    public ActionMenu getActionMenu() { return actionMenu; }
    public MovesMenu getMovesMenu() { return movesMenu; }
    public PartyMenu getPartyMenu() { return partyMenu; }

    @Override
    public void onTick(Manager manager) {

        //Pokemon mainPokemon = trainer.getPokemon(0);
//
        //if (opponentPokemon.getCurrentHP() == 0) {
        //    manager.setCurrentRoom(world);
        //    return;
        //}
        /* 
        if (turnCD == DELAY_TICKS) {
            turnCD = 0;

            if (mainPokemon.getCurrentCD() >= 0)
                mainPokemon.setCurrentCD(mainPokemon.getCurrentCD() - 1);
            if (opponentPokemon.getCurrentCD() >= 0)
                opponentPokemon.setCurrentCD(opponentPokemon.getCurrentCD() - 1);

            mainActionInfo.setPokemon(mainPokemon, true);
            mainActionInfo.setPokemon(opponentPokemon, false);

            if (!trainer.isNpc() && mainPokemon.getCurrentCD() < 0) {
                Move chargedMove = mainPokemon.getStats().getChargedMove();
                Move fastMove = mainPokemon.getStats().getFastMove();
                if (mainPokemon.getCurrentMP() >= chargedMove.getEnergy())
                    attack(mainPokemon, opponentPokemon, chargedMove, opponentPokemonInfo, mainActionInfo, true);
                else
                    attack(mainPokemon, opponentPokemon, fastMove, opponentPokemonInfo, mainActionInfo, true);
            }

            if (opponentPokemon.getCurrentCD() < 0) {
                Move chargedMove = opponentPokemon.getStats().getChargedMove();
                Move fastMove = opponentPokemon.getStats().getFastMove();
                if (opponentPokemon.getCurrentMP() >= chargedMove.getEnergy())
                    attack(opponentPokemon, mainPokemon, chargedMove, mainPokemonInfo, mainActionInfo, false);
                else
                    attack(opponentPokemon, mainPokemon, fastMove, mainPokemonInfo, mainActionInfo, false);
            }
        }
        */
        turnCD++;
        super.onTick(manager);
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

        mainBattleSprite.setPokemon(mainPokemon);
        mainPokemonInfo.setPokemon(mainPokemon);
        actionMenu.setPokemon(mainPokemon);
        movesMenu.refresh(this);
        partyMenu.setVisible(false);
        actionMenu.setVisible(true);
    }


    public static void attack(Pokemon attacker, Pokemon target, Move move) {
        int damage = Pokemon.calculateDamage(attacker, target, move);
        int newHP = Math.max(0, target.getCurrentHP() - damage);
        if (move.isCharged())
            attacker.setCurrentMP(attacker.getCurrentMP() - move.getEnergy());
        else
            attacker.setCurrentMP(attacker.getCurrentMP() + move.getEnergy());
        attacker.setCurrentCD(move.getCooldown());
        target.setCurrentHP(newHP);
    }
}