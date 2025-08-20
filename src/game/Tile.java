package game;

import java.io.Serializable;

import game.datas.Type;

public class Tile implements Serializable{
    Pokemon pokemon;
    Type type;
    int col;
    int row;
    boolean showPokemon;

    public Tile(Pokemon pokemon, Type type, int col, int row) {
        this.pokemon = pokemon;
        this.type = type;
        this.col = col;
        this.row = row;
        this.showPokemon = false;
    }

    public Pokemon getPokemon() {
        return this.pokemon;
    }

    public Type getType() {
        return this.type;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isShowPokemon() {
        return this.showPokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setShowPokemon(boolean showPokemon) {
        this.showPokemon = showPokemon;
    }
}