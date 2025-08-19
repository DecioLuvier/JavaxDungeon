package game.actors.battle;

import engine.Manager;
import engine.actors.Actor;
import engine.actors.Room;
import game.Pokemon;
import game.Trainer;
import game.actors.world.World;
import game.datas.Move;


public class Battle extends Room {
    //Constructor
    private static final int DELAY_TICKS = 60;
    private final World world;
    private final Trainer trainer;
    private final Pokemon opponentPokemon;
    private int turnCD;
    // Static constants

    private final ActionTexts actionTexts = new ActionTexts(new Room.Builder());
    private final ActionMenu actionMenu = new ActionMenu(new Room.Builder(), this);
    private final MovesMenu movesMenu = new MovesMenu(new Room.Builder(), this);
    private final PartyMenu partyMenu = new PartyMenu(new Room.Builder(), this);
    //
    private final Actor mainBattleSprite  = new Actor.Builder().scale(-4, 4).build();
    private final PokemonInfo mainPokemonInfo = new PokemonInfo(new Room.Builder());
    private final Actor opponentBattleSprite  = new Actor.Builder().scale(4, 4).build();
    private final PokemonInfo opponentPokemonInfo = new PokemonInfo(new Room.Builder());

    public Battle(Builder builder, World world, Trainer trainer, Pokemon opponentPokemon) {
        super(builder);
        this.world = world;
        this.trainer = trainer;
        this.opponentPokemon = opponentPokemon;
        this.turnCD = 0;

        addActor(movesMenu, 13, 385, 0);
        addActor(actionMenu, 13, 385, 0);
        addActor(partyMenu, 13, 385, 0);
        addActor(actionTexts, 13, 0, 0);
        addActor(mainBattleSprite, 21, 161, 0);
        addActor(mainPokemonInfo, 271, 350, 0);
        addActor(opponentPokemonInfo, 19, 126, 0);
        addActor(opponentBattleSprite, 270, 96, 0);

        //Player Pokemon
        Pokemon mainPokemon = trainer.getPokemon(0);
        mainPokemonInfo.setPokemon(mainPokemon);
        mainBattleSprite.setSprite(mainPokemon.getStats().getSpriteSheet());
        //Opponent Pokemon
        opponentPokemonInfo.setPokemon(opponentPokemon);
        opponentBattleSprite.setSprite(opponentPokemon.getStats().getSpriteSheet());



        actionTexts.setTopText(ActionTexts.getEncounterText(opponentPokemon));
        actionTexts.setBottomText(" ");

        actionMenu.setText(ActionMenu.getIdleText(mainPokemon));

        movesMenu.refresh(this);
        partyMenu.refresh(this);

        actionMenu.setVisible(true);
        movesMenu.setVisible(false);
        partyMenu.setVisible(false);
   
        opponentPokemon.setCurrentCD(1);
        mainPokemon.setCurrentCD(1);
    }

    @Override
    public void onTick() {
        if (turnCD == DELAY_TICKS) {
            turnCD = 0;
            if (opponentPokemon.getCurrentHP() == 0) {
                for (Pokemon pokemon : trainer.getTeam()) 
                    pokemon.addExperience(Pokemon.calculateXP(opponentPokemon));
                exit();
                return;
            }

            if(opponentPokemon.getTrainer() == trainer){
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

            if (opponentPokemon.getCurrentCD() == 0) {
                Move chargedMove = opponentPokemon.getStats().getChargedMove();
                Move fastMove = opponentPokemon.getStats().getFastMove();
                if (opponentPokemon.getCurrentMP() >= chargedMove.getEnergy()){
                    attack(opponentPokemon, mainPokemon, chargedMove);
                    actionTexts.setBottomText(ActionTexts.getAttackText(opponentPokemon, chargedMove));
                }
                else{
                    attack(opponentPokemon, mainPokemon, fastMove);
                    actionTexts.setBottomText(ActionTexts.getAttackText(opponentPokemon, fastMove));
                }
                mainPokemonInfo.setPokemon(mainPokemon);
            }
        }
        turnCD++;
        super.onTick();
    }

    public Trainer getTrainer() { return trainer; }
    public Pokemon getOpponentPokemon() { return opponentPokemon; }
    public ActionMenu getActionMenu() { return actionMenu; }
    public PokemonInfo getOpponentPokemonInfo() { return opponentPokemonInfo; }
    public ActionTexts getActionTexts() { return actionTexts; }
    public PartyMenu getPartyMenu() { return partyMenu; }
    public MovesMenu getMovesMenu() { return movesMenu; }

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
        actionMenu.setText(ActionMenu.getIdleText(mainPokemon));
        movesMenu.refresh(this);
        partyMenu.setVisible(false);
        actionMenu.setVisible(true);
    }

    public void capture() {
        Pokemon mainPokemon = trainer.getPokemon(0);
        if(mainPokemon.getCurrentCD() > 0)
            return;
            
        mainPokemon.setCurrentCD(1);

        if (opponentPokemon.getTrainer() != null) {
            actionTexts.setTopText(String.format("%s already has a trainer!", opponentPokemon.getStats().getName()));
            return;
        }
        
        double captureChance = Pokemon.calculateCaptureChance(opponentPokemon);
        double randomValue = Manager.get().getRandom().nextDouble();
        if (randomValue > captureChance) 
            actionTexts.setTopText(ActionTexts.getCaptureFailureMessage(opponentPokemon, captureChance, randomValue));
        else{
            trainer.addPokemon(opponentPokemon);
            actionTexts.setTopText(ActionTexts.getCaptureMessage(opponentPokemon));
            actionTexts.setBottomText(String.format(""));
        }
    }

    public void exit(){
        for(Pokemon pokemon : trainer.getTeam()){
            pokemon.setCurrentCD(0);
            pokemon.setCurrentMP(0);
            pokemon.setCurrentHP(pokemon.getHp());
        }

        opponentPokemon.setCurrentCD(0);
        opponentPokemon.setCurrentMP(0);
        opponentPokemon.setCurrentHP(opponentPokemon.getHp());
        if(!trainer.isNpc())
            world.setVisible(true);

        Room mainRoom = Manager.get().getRoom();
        mainRoom.removeActor(this);
    }
}