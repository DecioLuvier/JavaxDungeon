package game.surfaces.battle;

import java.awt.Color;

import engine.Surface;
import engine.actors.Actor;
import engine.actors.Button;
import engine.sprites.Sprite;
import engine.sprites.Text;
import game.Pokemon;
import game.rooms.Battle;

public class ActionMenu extends Surface {
    private final static Sprite background = new Sprite("assets/gui/battle/Actions.png");
    private final Button fightButton = new Button(new Text("FIGHT", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);
    private final Button switchButton = new Button(new Text("PKM", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);
    private final Button bagButton = new Button(new Text("BAG", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);
    private final Button runButton = new Button(new Text("RUN", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK), null);
    private final Text topText = new Text("What will", "assets/fonts/FontPKMN.ttf", 20, Color.BLACK);
    private final Text bottomText = new Text("assets/fonts/FontPKMN.ttf", 20, Color.BLACK); 

    public ActionMenu(Battle battle, Pokemon pokemon) {
        addActor(new Actor(background), 0, 0, 0);        
        addActor(new Actor(this.topText), 20, 40, 0);
        addActor(new Actor(this.bottomText), 20, 80, 0);
        addActor(this.fightButton, 290, 35, 0);
        addActor(this.switchButton, 410, 35, 0);
        addActor(this.runButton, 410, 80, 0);
        addActor(this.bagButton, 290, 80, 0);

        switchButton.setAction(() -> {
            this.setVisible(false);
            PartyMenu partyMenu = battle.getPartyMenu();
            partyMenu.setVisible(true);
        });

        fightButton.setAction(() -> {
            MovesMenu movesMenu = battle.getMovesMenu();
            movesMenu.setVisible(true);
            this.setVisible(false);
        });

        runButton.setAction(() -> {
            this.setVisible(false);
        });

        setPokemon(pokemon);
    }

    public void setPokemon(Pokemon pokemon){
        this.bottomText.setText(pokemon.getStats().getName() + " do?");
    }
}
