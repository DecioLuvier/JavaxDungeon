package game.data;

import engine.graphic.SpriteSheet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Pokedex {
    private static Map<String, Pokedex> entries = load();
    private String id;
    private String name;
    private Type mainType;
    private Type secondaryType;
    private int baseHp;
    private int baseAttack;
    private int baseDefense;
    private Move fastMove;
    private Move chargedMove;
    private int levelToEvolve;
    private Pokedex pokemonToEvolve; 
    private int catchRate;
    private int baseExp;
    private SpriteSheet spriteSheet;

    private Pokedex(String id, String name, Type mainType, Type secondaryType, int baseHp,
                   int baseAttack, int baseDefense, Move fastMove, Move chargedMove,
                   int levelToEvolve, Pokedex pokemonToEvolve, int catchRate, int baseExp,
                   int spriteCount) {
        this.id = id;
        this.name = name;
        this.mainType = mainType;
        this.secondaryType = secondaryType;
        this.baseHp = baseHp;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
        this.fastMove = fastMove;
        this.chargedMove = chargedMove;
        this.levelToEvolve = levelToEvolve;
        this.pokemonToEvolve = pokemonToEvolve;
        this.catchRate = catchRate;
        this.baseExp = baseExp;
        this.spriteSheet = new SpriteSheet("images/pokemons/" + id + ".png", spriteCount, 6, 0, 0);
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
    public int getLevelToEvolve() { return levelToEvolve;}
    public Pokedex getPokemonToEvolve() { return pokemonToEvolve; }
    public int getCatchRate() { return catchRate; }
    public int getBaseExp() { return baseExp; }
    public SpriteSheet getSpriteSheet() {return spriteSheet;}

    private static Map<String, Pokedex> load() {
        Map<String, Pokedex> entries = new HashMap<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File("Pokemons.xml"));
            doc.getDocumentElement().normalize();
            NodeList pokemonList = doc.getElementsByTagName("pokemon");

            for (int i = 0; i < pokemonList.getLength(); i++) {
                Element pokemon = (Element) pokemonList.item(i);
                String name = pokemon.getAttribute("name");
    
                Pokedex pokedex = new Pokedex(
                    pokemon.getAttribute("id"),
                    name,
                    Type.get(pokemon.getAttribute("mainType")),
                    Type.get(pokemon.getAttribute("secondaryType")),
                    Integer.parseInt(pokemon.getAttribute("baseHp")),
                    Integer.parseInt(pokemon.getAttribute("baseAttack")),
                    Integer.parseInt(pokemon.getAttribute("baseDefense")),
                    Move.get(pokemon.getAttribute("fastMove")),
                    Move.get(pokemon.getAttribute("chargedMove")),
                    Integer.parseInt(pokemon.getAttribute("levelToEvolve")),
                    null, 
                    Integer.parseInt(pokemon.getAttribute("catchRate")),
                    Integer.parseInt(pokemon.getAttribute("baseExp")),
                    Integer.parseInt(pokemon.getAttribute("spriteCount"))
                );
                entries.put(name, pokedex);
            }

            for (int i = 0; i < pokemonList.getLength(); i++) {
                Element pokemon = (Element) pokemonList.item(i);
                String name = pokemon.getAttribute("name");
                String evolveToName = pokemon.getAttribute("pokemonToEvolve");
                if (!evolveToName.isEmpty()) 
                    entries.get(name).pokemonToEvolve = entries.get(evolveToName);
            }

        } catch (Exception e) {
            System.err.println("Error loading Pokedex from XML: " + e.getMessage());
            e.printStackTrace();
        }

        return entries;
    }

    public static Pokedex get(String name) {
        Pokedex pokemon = entries.get(name);
        if (pokemon == null)
            System.err.println("Pokémon with name " + name + " not found.");
        return pokemon;
    }
}