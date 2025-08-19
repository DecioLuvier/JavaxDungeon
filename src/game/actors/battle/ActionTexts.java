package game.actors.battle;

import engine.actors.Actor;
import engine.actors.Room;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.datas.Move;

public class ActionTexts extends Room {
    private static final Sprite background = new Sprite.Builder().image("assets/gui/battle/TextsBox.png").build();
    private final Text topText = new Text.Builder().fontSize(19).build(); 
    private final Text bottomText = new Text.Builder().fontSize(19).build(); 
    
    public ActionTexts(Builder builder) {
        super(builder);
        addActor(new Actor.Builder().sprite(background).build(), 0, 0, 0);
        addActor(new Actor.Builder().sprite(topText).build(), 18, 18, 0);
        addActor(new Actor.Builder().sprite(bottomText).build(), 18, 46, 0);
    }

    public void setTopText(String text) {
        topText.setText(text);
    }

    public void setBottomText(String text){
        bottomText.setText(text);
    }

    public static String getActionText(Pokemon pokemon) {
        String pokemonName = pokemon.getStats().getName();
        int currentCD = pokemon.getCurrentCD();
        if (currentCD == 0)
            return String.format("%s is waiting...", pokemonName);
        else
            return String.format("%s is resting...CD:%d", pokemonName, currentCD);
    }

    public static String getCaptureMessage(Pokemon pokemon) {
        return String.format("%s has been captured!", pokemon.getStats().getName());
    }

    public static String getCaptureFailureMessage(Pokemon pokemon, double captureChance, double randomValue) {
        String pokemonName =  pokemon.getStats().getName();
        double difference = randomValue - captureChance;
        if (difference > 0.5) 
            return String.format("%s broke free easily!", pokemonName);
        if (difference > 0.1) 
            return String.format("%s resisted and escaped!", pokemonName);
        return String.format("%s was almost captured!", pokemonName);
    }

    public static String getAttackText(Pokemon attacker, Move move) {
        return String.format("%s used %s!", attacker.getStats().getName(), move.getName());
    }

    public static String getEncounterText(Pokemon pokemon){
        if(pokemon.getTrainer() == null)
            return String.format("A wild %s appears!", pokemon.getStats().getName());
        return String.format("A %s challenges you.", pokemon.getStats().getName());
    }
}