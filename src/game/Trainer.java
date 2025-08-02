package game;

import engine.actors.Actor;

import java.util.ArrayList;

public class Trainer extends Actor {
    private static final int MAX_TEAM_SIZE = 4;
    private ArrayList<Pokemon> team;

    public Trainer() {
        this.team = new ArrayList<>(MAX_TEAM_SIZE);
    }

    public boolean isTeamFull() {
        return team.size() >= MAX_TEAM_SIZE;
    }

    public void swapPokemon(int indexA, int indexB) {
        Pokemon temp = team.get(indexA);
        team.set(indexA, team.get(indexB));
        team.set(indexB, temp);
    }

    public void capturePokemon(Pokemon pokemon) {
        pokemon.setTrainer(this);
    }

    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < team.size()) 
            return team.get(index);
        return null;
    }

    public void addPokemon(Pokemon pokemon) {
        if (!isTeamFull() && pokemon != null) 
            team.add(pokemon);
    }

    public void removePokemon(Pokemon pokemon) {
        if (pokemon != null) 
            team.remove(pokemon);
    }
}