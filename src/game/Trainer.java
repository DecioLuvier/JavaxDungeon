package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Trainer implements Serializable {
    private static final int MAX_TEAM_SIZE = 6;
    private ArrayList<Pokemon> team;
    private final boolean isNpc;
    private boolean isOnBattle;

    public Trainer(boolean isNpc) {
        this.team = new ArrayList<>(MAX_TEAM_SIZE);
        this.isNpc = isNpc;
        this.isOnBattle = false;
    }

    public boolean isTeamFull() {
        return team.size() >= MAX_TEAM_SIZE;
    }

    public void swapPokemon(int indexA, int indexB) {
        Pokemon temp = team.get(indexA);
        team.set(indexA, team.get(indexB));
        team.set(indexB, temp);
    }

    public Pokemon getPokemon(int index) {
        if (index >= 0 && index < team.size()) 
            return team.get(index);
        return null;
    }

    public void addPokemon(Pokemon pokemon) {
        if (!isTeamFull()) 
            team.add(pokemon);
        pokemon.setTrainer(this);
    }

    public void removePokemon(Pokemon pokemon) {
        team.remove(pokemon);
    }

    public int getScore() {
        int score = 0;
        for (Pokemon pokemon : team) 
            score += pokemon.getScore();
        return score;
    }

    public boolean isNpc() {
        return isNpc;
    }

    public boolean isOnBattle() {
        return isOnBattle;
    }

    public void setOnBattle(boolean isOnBattle) {
        this.isOnBattle = isOnBattle;
    }

    public ArrayList<Pokemon> getTeam() {
        return new ArrayList<>(team);
    }
}