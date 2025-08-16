package game.datas;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import engine.sprites.Sheet;
import engine.sprites.Sprite;

public class Pokedex {
    private static NodeList pokemonList = Data.loadXML("assets/Pokemons.xml", "pokemon");
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
    private Pokedex pokemonToEvolve;
    private int catchRate;
    private int baseExp;

    private Pokedex(String id, String name, Type mainType, Type secondaryType, int baseHp,
                   int baseAttack, int baseDefense, Move fastMove, Move chargedMove, Move optionalMove,
                   int levelToEvolve, Pokedex pokemonToEvolve, int catchRate, int baseExp) {
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
    public Pokedex getPokemonToEvolve() { return pokemonToEvolve; }
    public int getCatchRate() { return catchRate; }
    public int getBaseExp() { return baseExp; }
    public Sprite getSpriteSheet() { return new Sheet("assets/pokemons/" + id + ".png", 56, 56, 6, 0, 0); }

    public static Pokedex get(String name) {
        if(name.isEmpty() || name == null)
            return null;
        for (int i = 0; i < pokemonList.getLength(); i++) {
            Element pokemon = (Element) pokemonList.item(i);
            String pokemonName = pokemon.getAttribute("name");
            if (pokemonName.equals(name)) {
                return new Pokedex(
                    pokemon.getAttribute("id"),
                    pokemonName,
                    Type.get(pokemon.getAttribute("mainType")),
                    Type.get(pokemon.getAttribute("secondaryType")),
                    Integer.parseInt(pokemon.getAttribute("baseHp")),
                    Integer.parseInt(pokemon.getAttribute("baseAttack")),
                    Integer.parseInt(pokemon.getAttribute("baseDefense")),
                    Move.get(pokemon.getAttribute("fastMove")),
                    Move.get(pokemon.getAttribute("chargedMove")),
                    Move.get(pokemon.getAttribute("optionalMove")),
                    Integer.parseInt(pokemon.getAttribute("levelToEvolve")),
                    get(pokemon.getAttribute("pokemonToEvolve")),
                    Integer.parseInt(pokemon.getAttribute("catchRate")),
                    Integer.parseInt(pokemon.getAttribute("baseExp"))
                );
            }
        }
        System.err.println("Pokémon with name " + name + " not found.");
        return null;
    }
}