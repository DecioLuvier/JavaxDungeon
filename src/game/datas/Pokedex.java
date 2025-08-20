package game.datas;

import engine.Data;
import engine.sprites.Animation;
import engine.sprites.Sprite;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public final class Pokedex extends Data{
    private static final ArrayList<Pokedex> entries = new ArrayList<>();

    private String id;
    private String name;
    private Type mainType;
    private Type secondaryType;
    private int baseHp;
    private int baseAttack;
    private int baseDefense;
    private Move fastMove;
    private Move chargedMove;
    private Move optionalMove;
    private int levelToEvolve;
    private String pokemonToEvolve;
    private int catchRate;
    private int baseExp;

    private Pokedex(String id, String name, Type mainType, Type secondaryType, int baseHp,
                    int baseAttack, int baseDefense, Move fastMove, Move chargedMove, Move optionalMove,
                    int levelToEvolve, String pokemonToEvolve, int catchRate, int baseExp) {
        this.id = id;
        this.name = name;
        this.mainType = mainType;
        this.secondaryType = secondaryType;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.fastMove = fastMove;
        this.chargedMove = chargedMove;
        this.optionalMove = optionalMove;
        this.levelToEvolve = levelToEvolve;
        this.pokemonToEvolve = pokemonToEvolve;
        this.catchRate = catchRate;
        this.baseExp = baseExp;
    }

    public static ArrayList<Pokedex> get() {
        if (entries.size() == 0) {
            NodeList pokemonList = Data.loadXML("assets/Pokedex.xml", "pokemon");
            for (int i = 0; i < pokemonList.getLength(); i++) {
                Element pokemon = (Element) pokemonList.item(i);
                Pokedex p = new Pokedex(
                    pokemon.getAttribute("id"),
                    pokemon.getAttribute("name"),
                    Type.get(pokemon.getAttribute("mainType")),
                    Type.get(pokemon.getAttribute("secondaryType")),
                    Integer.parseInt(pokemon.getAttribute("baseHp")),
                    Integer.parseInt(pokemon.getAttribute("baseAttack")),
                    Integer.parseInt(pokemon.getAttribute("baseDefense")),
                    Move.get(pokemon.getAttribute("fastMove")),
                    Move.get(pokemon.getAttribute("chargedMove")),
                    Move.get(pokemon.getAttribute("optionalMove")),
                    Integer.parseInt(pokemon.getAttribute("levelToEvolve")),
                    pokemon.getAttribute("pokemonToEvolve"), 
                    Integer.parseInt(pokemon.getAttribute("catchRate")),
                    Integer.parseInt(pokemon.getAttribute("baseExp"))
                );
                entries.add(p);
            }
        }
        return entries;
    }

    public static Pokedex get(String name) {
        if (name == null || name.isEmpty()) 
            return null;
        for (Pokedex p : get())
            if (p.name.equalsIgnoreCase(name)) 
                return p;
        return null;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Type getMainType() { return mainType; }
    public Type getSecondaryType() { return secondaryType; }
    public int getBaseHp() { return baseHp; }
    public int getBaseAttack() { return baseAttack; }
    public int getBaseDefense() { return baseDefense; }
    public Move getFastMove() { return fastMove; }
    public Move getChargedMove() { return chargedMove; }
    public Move getOptionalMove() { return optionalMove; }
    public int getLevelToEvolve() { return levelToEvolve; }
    public String getPokemonToEvolve() { return pokemonToEvolve; }
    public int getCatchRate() { return catchRate; }
    public int getBaseExp() { return baseExp; }

    public Sprite getSpriteSheet() {
        return new Animation.Builder().imageSheet("assets/pokemons/" + id + ".png").spriteHeight(56).spriteWidth(56).build();
    }
}
