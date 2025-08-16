package game;

import game.datas.Type;

public class Tile {
    Pokemon pokemon;
    Type type;
    int col;
    int row;
    
    public Tile(Pokemon pokemon, Type type, int col, int row){
        this.pokemon = pokemon;
        this.type = type;
        this.col = col;
        this.row = row;
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
}
